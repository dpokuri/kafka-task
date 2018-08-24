package com.david.kafka.kafkaconsumer;

import java.util.Map;
import java.util.Random;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.SimpleKafkaHeaderMapper;
import org.springframework.kafka.support.converter.MessagingMessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;

import com.david.kafka.kafkaconsumer.model.Customer;
import com.david.kafka.kafkaconsumer.model.Driver;
import com.david.kafka.kafkaconsumer.repository.DriverRepository;
import com.google.gson.Gson;


@ComponentScan(basePackages = {
        "com.david.kafka.kafkaconsumer.config"
        
    })
@SpringBootApplication
public class KafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		DriverRepository.prepareDriverObjects();
	    System.out.println("Driver Map has been prepared");
	}
	
	@KafkaListener(id="server", topics = "kRequests")
    @SendTo // use default replyTo expression
    public String listen(String in) {
        System.out.println("======Server received:======= " + in);

        Gson g = new Gson();
        Customer cust = g.fromJson(in, Customer.class);
		Map<String, Driver> driverMap = DriverRepository.getDriverMap();
		int count = 0;
		Driver driver1 = new Driver();
		while (count != 10) {
			count++;
			String randomDriver = "D100" + getRandomDriverId();
			if (driverMap.get(randomDriver).getDriverStatus().equals("NOT_ASSIGNED")) {
				driver1 = driverMap.get(randomDriver);
				driver1.setDriverStatus("ASSIGNED-ON_DRIVE");
				driver1.setCustDetails(cust.getCustId());
				driverMap.put(randomDriver, driver1);
				return g.toJson(driver1);
			}

		}
		driver1.setDriverStatus("DRIVERS ARE NOT AVAILABLE AT THIS TIME, WE WILL NOTIFY ONCE DRIVERS ARE AVAILABLE");
//		return driver1;
		return g.toJson(driver1);
    }
	
	private int getRandomDriverId() {
		int min = 1;
		int max = 10;
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}

    @Bean
    public NewTopic kRequests() {
        return new NewTopic("kRequests", 10, (short) 1);
    }

    @Bean // not required if Jackson is on the classpath
    public MessagingMessageConverter simpleMapperConverter() {
        MessagingMessageConverter messagingMessageConverter = new MessagingMessageConverter();
        messagingMessageConverter.setHeaderMapper(new SimpleKafkaHeaderMapper());
        return messagingMessageConverter;
    }

  

}
