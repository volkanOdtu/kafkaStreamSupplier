package com.roytuts.ordersource;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;

import com.roytuts.ordersource.model.Order;

@SpringBootApplication
@EnableBinding(Source.class) //Default interface 
public class OrdersourceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(OrdersourceApplication.class);

	List<String> names = Arrays.asList("Donald" ,"Theresa" ,"Vladimir" ,"Angela" ,"Kim");
	List<String> items = Arrays.asList("Mobile" ,"Tab" ,"Desktop" ,"Laptop" ,"Adapter");
	
	
	public static void main(String[] args) {
		SpringApplication.run(OrdersourceApplication.class, args);
	}
	
	//Random olarak hard-coded degerleri  uretiyoruz ,aslinda buraya bi application veya db den gelmeli data
	//Burda metod adini application.properties e ekleyebiliriz ,ama 1 tane bean oldugu icin destination da yazmiyoruz
	//spring.cloud.stream.function.definition =supply ,method adi "supply" yaziyoruz veya yukarida da args'a verebiliyoruz
	//1 den cok bean olursa spring.cloud.stream.function.definition =supply|foo gibi de yazabiliyoruz
	//By default Spring Cloud Stream will send/produce a message every second 
	//Farkli bi interval kullanmak istersek spring.integration.poller.fixed-delay ile yazabiliriz properties de 
	@Bean
	@InboundChannelAdapter(channel = Source.OUTPUT)
	public Supplier<Order> supply(){
		return () ->{
			String randomName = names.get(new Random().nextInt(names.size()));
			String randomItem= items.get(new Random().nextInt(items.size()));
			Order order = new Order(UUID.randomUUID().toString() ,randomName ,randomItem);
			
			System.out.println("New order:" +order.getStatus() + ":" + order.getUuid()+ ":"+ order.getItem() + ":"+ order.getName());
			return order;
		};
	}

}
