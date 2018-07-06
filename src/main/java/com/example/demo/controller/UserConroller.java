package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserConroller {

	    @GetMapping(value = "/getAll")
	    public List<String> getUser() {
	        List<String> list = new ArrayList<String>()	;
	        
	        list.add("abc");
	        list.add("cba");
	        list.add("qwe");
	        return list;
	    }

	    @GetMapping("/get/{id}")
	    public String getById(@PathVariable(name = "id") String id) {
	    	
	    	Map<String, String> map = new HashMap<String, String>();
	    	
	    	map.put("1", "abc");
	    	map.put("2", "cba");
	    	map.put("3", "qwe");
	    	
	        return map.get(id);
	    }


	    @RequestMapping(value = "save")
	    public String save(String user) {

	        return "保存成功";
	    }


	    @RequestMapping(value = "saveByType/{type}")
	    public String saveByType(String user,@PathVariable("type")String type) {

	        return "保存成功,type="+type;
	    }
}
