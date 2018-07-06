package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/getForEntity")
    public List<String> getAll2() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:9001/getAll", List.class);
        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int code = statusCode.value();

        List<String> list = responseEntity.getBody();

        System.out.println(list.toString());
        System.out.println(headers);
        System.out.println(code);
        return list;

    }
	 @RequestMapping("/getForEntity/{id}")
	    public String getById2(@PathVariable(name = "id") String id) {

	        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:9001/get/{id}", String.class, id);
	        String userEntity = responseEntity.getBody();
	        return userEntity;
	    }
}
