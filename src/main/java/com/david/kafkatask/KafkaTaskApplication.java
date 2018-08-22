package com.david.kafkatask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.david.kafkatask.repository.DriverRepository;

@SpringBootApplication
public class KafkaTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaTaskApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		DriverRepository.prepareDriverObjects();
	    System.out.println("Driver Map has been prepared");
	}

}
