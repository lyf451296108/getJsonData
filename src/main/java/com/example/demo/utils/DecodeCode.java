package com.example.demo.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class DecodeCode {
	
	
	
	
	/*public static void main(String[] args) {
		
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		final String text = "2018-06-06XINGFENGWEI";
		byte[] textByte;
		try {
			textByte = text.getBytes("UTF-8");
			//编码
			final String encodedText = encoder.encodeToString(textByte);
			System.out.println(encodedText);
			
			//解码
			System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static String encoder(String decoderStr) {
		
		final Base64.Decoder decoder = Base64.getDecoder();
		
		try {
			return new String(decoder.decode(decoderStr), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

/*accessKey=rkffvhjknkjnjvhgcvghcbjklnkuytfnjklhniu key=5555555555

1. md5(accessKey-nonce)
aes-key-data 
url:*/ 
