package com.demo.listener;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageListener {
	
	@SqsListener("my-queue")
	public void loadMessageFromQueue(String message)
	{
		log.info("Message received from queue: {}", message);
	}
}
