package com.example.demo.controller;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.AcountinfomationMapper;
import com.example.demo.pojo.Acountinfomation;

@Controller
@ResponseBody
@RequestMapping(value = "json")
public class getsonData {
	@Autowired
	AcountinfomationMapper mapper;
	
	@PostMapping(value = "/getJson")
	@ResponseBody
	public String getJson(HttpServletRequest request,
	        HttpServletResponse response) {
	    String strcont = null;
	    try {
	    	 ServletInputStream ris = request.getInputStream();  
	    	    StringBuilder content = new StringBuilder();  
	    	    byte[] b = new byte[1024];  
	    	    int lens = -1;  
	    	    while ((lens = ris.read(b)) > 0) {  
	    	        content.append(new String(b, 0, lens));  
	    	    }  
	    	    strcont = content.toString();
	    	    
	    	    List<Acountinfomation> list = new ArrayList<Acountinfomation>();  
	            list = JSONObject.parseArray(strcont, Acountinfomation.class);
	    	    for(Acountinfomation tst:list) {
	    	    	 System.out.println(tst.toString());
	    	    }
	    } catch (Exception e) {
	        System.out.println(e.toString());

	    }
	    System.out.println(strcont);
	    return strcont.toString();
	}	
	
	@PostMapping(value = "/getJsonReader")
	@ResponseBody
	public String getJsonReader(HttpServletRequest request,
	        HttpServletResponse response) {
	    StringBuffer json = new StringBuffer();
	    String line = null;
	    try {
	        BufferedReader reader = request.getReader();
	        while ((line = reader.readLine()) != null) {
	            System.out.println("line:" + line);
	            json.append(line);
	        }
    	    List<Acountinfomation> list = new ArrayList<Acountinfomation>();  
            list = JSONObject.parseArray(json.toString(), Acountinfomation.class);
    	    for(Acountinfomation tst:list) {
    	    	 System.out.println(tst.toString());
    	    }
    	    
	    } catch (Exception e) {
	        System.out.println(e.toString());

	    }
	    System.out.println(json.toString());
	    return json.toString();
	}	
	

	@PostMapping(value = "/getJsonBody")
	@ResponseBody
	public String getJsonBody(HttpServletRequest request,
	        HttpServletResponse response, @RequestBody String jsonData) {
	    try {
    	    List<Acountinfomation> list = new ArrayList<Acountinfomation>();  
            list = JSONObject.parseArray(jsonData.toString(), Acountinfomation.class);
    	    for(Acountinfomation tst:list) {
    	    	 System.out.println(tst.toString());
    	    	 //mapper.insert(tst);
    	    }
    	    mapper.insertOrUpdateAccountInforList(list);
	    } catch (Exception e) {
	        System.out.println(e.toString());
	    }
	    System.out.println(jsonData);
	    return jsonData;
	}	
	
}
