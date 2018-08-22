package com.david.kafkatask.consumer;

import java.util.Map;
import java.util.Random;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.david.kafkatask.model.Customer;
import com.david.kafkatask.model.Driver;
import com.david.kafkatask.repository.DriverRepository;;

@Component
public class ReplyingKafkaConsumer {
	
	@KafkaListener(topics = "${kafka.topic.request-topic}")
	@SendTo
	public Driver listen(Customer driver) throws InterruptedException {
		Map<String, Driver> driverMap = DriverRepository.getDriverMap();

		while (true) {
			String randomDriver = "D100" + getRandomDriverId();
			if (driverMap.get(randomDriver).getDriverStatus().equals("NOT_ASSIGNED")) {
				return driverMap.get("D100" + getRandomDriverId());
			}

		}
	}

	private int getRandomDriverId() {
		int min = 1;
		int max = 10;
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}


}
