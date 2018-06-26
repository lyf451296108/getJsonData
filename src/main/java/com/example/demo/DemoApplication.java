package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx =  SpringApplication.run(DemoApplication.class, args);
		
		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        //CountDownLatch latch = ctx.getBean(CountDownLatch.class);

        System.out.println("Sending message...");
        template.convertAndSend("chat", "Hello from Redis!");

        //latch.await();

        System.exit(0);
	}
}
