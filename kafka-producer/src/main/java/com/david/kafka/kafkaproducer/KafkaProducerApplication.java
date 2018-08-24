package com.david.kafka.kafkaproducer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.david.kafka.kafkaproducer.model.Customer;
import com.david.kafka.kafkaproducer.model.Driver;

@SpringBootApplication
public class KafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner runner(ReplyingKafkaTemplate<String, Customer, Driver> template) {
		return args -> {

			ExecutorService executor = Executors.newFixedThreadPool(5);

			template.setReplyTimeout(10000);
			int i =1;
			for (;i < 6; i++) {
				
				Customer cust = new Customer("C100"+i, "CUST"+i);

				executor.execute(new Runnable() {

					public void run() {
						ProducerRecord<String, Customer> record = new ProducerRecord<String, Customer>("kRequests",
								cust);
						record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, "kReplies".getBytes()));
						RequestReplyFuture<String, Customer, Driver> replyFuture = template.sendAndReceive(record);
						SendResult<String, Customer> sendResult = null;
						ConsumerRecord<String, Driver> consumerRecord = null;
						try {
							sendResult = replyFuture.getSendFuture().get();
							System.out.println("=======Sent ok: ========= " + sendResult.getRecordMetadata());
							consumerRecord = replyFuture.get();
							System.out.println("=======Return value:======== " + consumerRecord.value());
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
					}
				});
			}
		};

	}
	
	@Bean
    public ProducerFactory<String, Customer> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
	}

    @Bean
    public ReplyingKafkaTemplate<String, Customer, Driver> kafkaTemplate(
            ProducerFactory<String, Customer> pf,
            KafkaMessageListenerContainer<String, Driver> replyContainer) {
        return new ReplyingKafkaTemplate<>(pf, replyContainer);
    }

    @Bean
    public KafkaMessageListenerContainer<String, Driver> replyContainer(
            ConsumerFactory<String, Driver> cf) {
        ContainerProperties containerProperties = new ContainerProperties("kReplies");
        containerProperties.setGroupId("server");
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }

    @Bean
    public NewTopic kRequests() {
        return new NewTopic("kRequests", 10, (short) 1);
    }

    @Bean
    public NewTopic kReplies() {
        return new NewTopic("kReplies", 10, (short) 1);
    }

	
	
}
