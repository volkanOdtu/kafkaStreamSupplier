package com.roytuts.ordercheck.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderProcessor {

	@Input("output")
	SubscribableChannel sourceOfOrders();
	
	@Output("delivered")
	MessageChannel delivered();
	
	@Output("undelivered")
	MessageChannel undelivered();
	
}
