package com.david.kafka.kafkaproducer.config;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.david.kafka.kafkaproducer.model.Customer;

public class CustomerSerializer implements Serializer<Customer> {
	
	private String encoding = "UTF8";

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		
		
	}

	@Override
	public byte[] serialize(String topic, Customer data) {
		int sizeOfCustId;
		int sizeOfCustName;
		
		byte[] serializedCustId;
		byte[] serializedCustName;
		try {
			if(data == null) {
				return null;
			}
			
			serializedCustId = data.getCustId().getBytes(encoding);
			sizeOfCustId = serializedCustId.length;
			serializedCustName = data.getCustrName().getBytes(encoding);
			sizeOfCustName = serializedCustName.length;
			
			ByteBuffer buf = ByteBuffer.allocate(4+4+sizeOfCustId+4+sizeOfCustName);
			buf.putInt(sizeOfCustId);
			buf.put(serializedCustId);
			buf.putInt(sizeOfCustName);
			buf.put(serializedCustName);
			
			return buf.array();
			
		}catch(Exception e){
			throw new SerializationException("Error when serializing Customer object");
		}
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	
	

}
