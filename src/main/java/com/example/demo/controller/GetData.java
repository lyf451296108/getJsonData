package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.AcceptData;
import com.example.demo.utils.FwCodeUtils;

@Controller
public class GetData {
	@ResponseBody
	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public String execute(HttpServletRequest request,HttpServletResponse response){		
		Long startTime = System.currentTimeMillis();
		String key = "!&-join+";
		String returnStr = "";
		String jsonData ="";
		byte[] data;
		try {
			data = getData(request.getInputStream());
			
			// 先进行BASE64解码，再DES解密			
			jsonData = new String(FwCodeUtils.decrypt(key.getBytes("UTF-8"),FwCodeUtils.base64Decode(data)), "UTF-8");
			//ApiLog.main_info("接口调用开始,data==>"+jsonData);
			//String rData = apiMainService.adaptive(jsonData);
			//ApiLog.main_info("接口调用完成,返回==>"+rData+",用时(秒)="+(System.currentTimeMillis() - startTime)/1000);
			// 先进行DES加密，再BASE64加码
			//byte[] desData = FwCodeUtils.encrypt(key, rData);
			//returnStr = FwCodeUtils.base64Encode(desData).replaceAll("\r\n", "");	
			AcceptData dataOne = JSONObject.parseObject(jsonData, AcceptData.class);
			System.out.println(dataOne.toString());
			System.out.println(dataOne.getLogBean().toString());
			return jsonData;
		} catch (Exception e) {
			//ApiLog.main_error(jsonData+" 接收异常！", e);
			//return ReturnJsonUtil.returnJsonMsg(-1,jsonData+" 接收异常！");
			return null;
		}
		
	}	
	
	 public static byte[] getData(InputStream is) throws IOException {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;

        try {
            bis = new BufferedInputStream(is, 1024);
            baos = new ByteArrayOutputStream(1024);

            byte[] b = new byte[1024];
            int len;
            while ((len = bis.read(b)) != -1) {
                baos.write(b, 0, len);
            }

            return baos.toByteArray();
        } finally {
        	bis.close();
        	baos.close();
           // close(bis, baos);
        }
    }
}
