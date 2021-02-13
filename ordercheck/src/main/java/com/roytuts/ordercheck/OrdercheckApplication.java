package com.roytuts.ordercheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.roytuts.ordercheck.processor.OrderProcessor;

@SpringBootApplication
@EnableBinding(OrderProcessor.class) //Bu interface in classi olucak ,listen ettigi ve deliver ettigi channel lar olucak
public class OrdercheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdercheckApplication.class, args);
	}

}
