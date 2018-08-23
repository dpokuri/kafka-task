package com.david.kafkatask.consumer;

import java.util.Map;
import java.util.Random;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.david.kafkatask.model.Driver;
import com.david.kafkatask.model.Model;
import com.david.kafkatask.repository.DriverRepository;;

@Component
public class ReplyingKafkaConsumer {
	
	@KafkaListener(topics = "${kafka.topic.request-topic}")
	 @SendTo
	 public Model listen(Model driver) throws InterruptedException {
			Map<String, Driver> driverMap = DriverRepository.getDriverMap();
			int count = 0;
			Model model = new Model();
			while (count != 10) {
				count++;
				String randomDriver = "D100" + getRandomDriverId();
				if (driverMap.get(randomDriver).getDriverStatus().equals("NOT_ASSIGNED")) {
					Driver driver1 = driverMap.get(randomDriver);
					driver1.setDriverStatus("ASSIGNED");
					driverMap.put(randomDriver, driver1);
					model.setDriverId(driver1.getDriverId());
					model.setDriverName(driver1.getDriverName());
					model.setDriverStatus("ASSIGNED");
					return model;
				}

			}
			model.setDriverStatus("DRIVERS ARE NOT AVAILABLE AT THIS TIME, WE WILL NOTIFY ONCE DRIVERS ARE AVAILABLE");
			return model;
		}

		private int getRandomDriverId() {
			int min = 1;
			int max = 10;
			Random rand = new Random();
			return rand.nextInt((max - min) + 1) + min;
		}


}
