package com.david.kafkatask.controller;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.kafkatask.model.Customer;
import com.david.kafkatask.model.Driver;

@RestController
@RequestMapping("/api")
public class CustomerDriverController {
	
	@Autowired
	ReplyingKafkaTemplate<String, Customer,Driver> kafkaTemplate;
	
	@Value("${kafka.topic.request-topic}")
	String requestTopic;
	
	@Value("${kafka.topic.requestreply-topic}")
	String requestReplyTopic;
	
	
	@PostMapping(value="/sum",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Driver> sum(@RequestBody Customer request) throws InterruptedException, ExecutionException {
		// create producer record
		ProducerRecord<String, Customer> record = new ProducerRecord<String, Customer>(requestTopic, request);
		// set reply topic in header
		record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));
		// post in kafka topic
		RequestReplyFuture<String, Customer, Driver> sendAndReceive = kafkaTemplate.sendAndReceive(record);

		// confirm if producer produced successfully
		SendResult<String, Customer> sendResult = sendAndReceive.getSendFuture().get();
		
		//print all headers
		sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));
		
		// get consumer record
		ConsumerRecord<String, Driver> consumerRecord = sendAndReceive.get();
		// return consumer value
		//return consumerRecord.value();
		return new ResponseEntity<Driver>(consumerRecord.value(), null);
	}
	
	@GetMapping("/msg")
	public String getMsg() {
		
		return "Test message";
		
	}

}
