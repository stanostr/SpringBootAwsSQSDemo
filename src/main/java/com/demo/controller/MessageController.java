package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MessageController {
	
	@Value("${cloud.aws.end-point.uri}")
	private String queueEndpoint;
	
	@Autowired
	private QueueMessagingTemplate messagingTemplate;
	
	@PostMapping("/post-message")
	private Message postMessage(@RequestBody Message requestMessage)
	{
		log.info("Sending message: {}", requestMessage.getMessage());
		messagingTemplate.send(queueEndpoint, MessageBuilder.withPayload(requestMessage.getMessage()).build());
		return new Message("Message added to the queue");
	}

	@NoArgsConstructor
	public static class Message {
		private String message;

		public Message(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
	
}
