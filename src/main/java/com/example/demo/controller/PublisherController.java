package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller
 * 
 * @author 陈梓平
 * @date 2017/10/24.
 */
@RestController
@RequestMapping
public class PublisherController {
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	@GetMapping(value = "pub/{id}")
	public String pubMsg(@PathVariable String id) {
		return pubMsgService(id);
	}
	public String pubMsgService(String id) {
        if ("1".equals(id)){
            stringRedisTemplate.convertAndSend("phone","18888888888");
            System.out.println("Publisher sendes Topic... ");
            return "success";
        }else {
            return "no my phone";
        }
    }
}

