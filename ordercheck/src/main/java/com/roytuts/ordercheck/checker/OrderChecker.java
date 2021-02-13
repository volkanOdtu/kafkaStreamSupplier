package com.roytuts.ordercheck.checker;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;


import com.roytuts.ordercheck.model.Order;
import com.roytuts.ordercheck.model.Status;
import com.roytuts.ordercheck.processor.OrderProcessor;

@Component
public class OrderChecker {

	List<String> DISCARDED_ITEMS = Arrays.asList("Watch" ,"Clock");
	
	@Autowired
	private OrderProcessor processor;
	
	@StreamListener("output")
	public void checkAndSortOrders(Order order) {
	
		if(DISCARDED_ITEMS.contains(order.getItem())) {
			order.setStatus(Status.UNDELIVERED.name());
			processor.undelivered().send(message(order));
		}else {
			order.setStatus(Status.DELIVERED.name());
			processor.delivered().send(message(order));
		}
		System.out.println(order.getStatus() + ":" + order.getUuid() + ":" +order.getItem()+ ":" + order.getName());
	}
	
	private static final <T> Message<T> message(T val){
		return MessageBuilder.withPayload(val).build();
	}
}
