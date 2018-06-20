package com.example.demo.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public class FwHttpUtils {

	/** 与服务器建立连接的超时时间(单位:毫秒) */
	private static final int CONNECT_TIMEOUT	= 1000 * 30;
	/** 建立连接后等待服务器返回数据的超时时间(单位:毫秒) */
	private static final int READ_TIMEOUT		= 1000 * 60;
	
	/** HTTP默认以POST方式请求： */
	private static final String METHOD_POST		= "POST";
	
	
	/**
	 * 整体发送：发送及接收返回值
	 * <br> 说明：整体发送，请求头与内容会同时发过去
	 * <br> 说明：这里连接超时默认为30秒，等待返回数据超时为60秒
	 * @param url				请求路径
	 * @param content			发送的内容
	 * @return
	 */
	public static byte[] allSend(String url, byte[] content) throws IOException {
		return allSend(url, content, CONNECT_TIMEOUT, READ_TIMEOUT);
	}
	
	/**
	 * 整体发送：发送及接收返回值
	 * <br> 说明：整体发送，请求头与内容会同时发过去
	 * @param url				请求路径
	 * @param content			发送的内容
	 * @param connectTimeout	与服务器建立连接的超时时间(单位:毫秒)
	 * @param readTimeout		建立连接后等待服务器返回数据的超时时间(单位:毫秒)
	 * @return
	 */
	public static byte[] allSend(String url, byte[] content, int connectTimeout, int readTimeout)
			throws IOException {
		
		HttpURLConnection conn = null;
		OutputStream os = null;
		
		try {
			conn = (HttpURLConnection)new URL(url).openConnection();
			
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			
			conn.setRequestMethod(METHOD_POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/octet-stream;charset=" + "UTF-8");
			
			os = conn.getOutputStream();
			
			// 将内容发送到远程服务端：
			contentSend(content, os);
			
			// 接收远程服务端传回的内容：
			return conentAccept(conn);
			
		} finally {
			if (os != null) {
				os.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
	
	/**
	 * 分步发送：发送及接收返回值
	 * <br> 说明：分步发送，请求头会先发过去，然后内容这边可一边写，服务端可一边收
	 * <br> 说明：这里连接超时默认为30秒，等待返回数据超时为60秒
	 * @param url				请求路径
	 * @param headMap			请求头键值对
	 * @param filePath			文件完整路径
	 * @param bufSize			缓冲区大小
	 * @return
	 */
	public static byte[] stepSend(String url, Map<String, String> headMap, String filePath, int bufSize)
			throws IOException {
		return stepSend(url, headMap, filePath, bufSize, CONNECT_TIMEOUT, READ_TIMEOUT);
	}
	
	/**
	 * 分步发送：发送及接收返回值
	 * <br> 说明：分步发送，请求头会先发过去，然后内容这边可一边写，服务端可一边收
	 * @param url				请求路径
	 * @param headMap			请求头键值对
	 * @param filePath			文件完整路径
	 * @param bufSize			缓冲区大小
	 * @param connectTimeout	与服务器建立连接的超时时间(单位:毫秒)
	 * @param readTimeout		建立连接后等待服务器返回数据的超时时间(单位:毫秒)
	 * @return
	 */
	public static byte[] stepSend(String url, Map<String, String> headMap, String filePath,
			int bufSize, int connectTimeout, int readTimeout) throws IOException {
		
		HttpURLConnection conn = null;
		OutputStream os = null;
		
		try {
			conn = (HttpURLConnection)new URL(url).openConnection();
			
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			
			conn.setRequestMethod(METHOD_POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/octet-stream;charset=" + "UTF-8");
			
			// 请求头键值对：
			for (String key : headMap.keySet()) {
				conn.setRequestProperty(key, headMap.get(key));
			}
			
			// 经测试，在Java这边，设置此大小后，服务端收到的总是小7B，所以为使服务端接收到bufSize，这边+7
			// 但好像VC那边不会出现此问题
			conn.setChunkedStreamingMode(bufSize + 7);
			
			// 使用ChunkedStreamingMode，执行此句会先将请求头发送过去：
			os = conn.getOutputStream();
			
			// 将内容发送到远程服务端：
			contentSend(filePath, bufSize, os);
			
			// 接收远程服务端传回的内容：
			return conentAccept(conn);
			
		} finally {
			if (os != null) {
				os.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
	
	
	// -------------------------------------------------------------------------
	// Private Methods：
	// -------------------------------------------------------------------------
	
	/** 将内容发送到远程服务端： */
	private static void contentSend(byte[] content, OutputStream os) throws IOException {
		if (content.length <= 1024 * 8) {
			os.write(content);
		} else {
			BufferedOutputStream bos = null;
			ByteArrayInputStream bais = null;
			try {
				bos = new BufferedOutputStream(os);
				bais = new ByteArrayInputStream(content);
				byte[] b = new byte[1024 * 4];
				int len;
				while ((len = bais.read(b)) != -1) {
					bos.write(b, 0, len);
				}
			} finally {
				if (bais != null) {
					bais.close();
				}
				if (bos != null) {
					bos.close();
				}
			}
		}
	}
	
	/** 将内容发送到远程服务端： */
	private static void contentSend(String filePath, int bufSize, OutputStream os) throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			byte[] b = new byte[bufSize];
			int len;
			while ((len = fis.read(b)) != -1) {
				os.write(b, 0, len);
			}
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (os != null) {
				os.close();
			}
		}
	}
	
	/** 接收远程服务端传回的内容： */
	private static byte[] conentAccept(HttpURLConnection conn) throws IOException {
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		
		try {
			bis = new BufferedInputStream(conn.getInputStream());
			baos = new ByteArrayOutputStream();
			
			byte[] b = new byte[1024 * 4];
			int len;
			while ((len = bis.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			
			return baos.toByteArray();
			
		} finally {
			if (baos != null) {
				baos.close();
			}
			if (bis != null) {
				bis.close();
			}
		}
	}
}
