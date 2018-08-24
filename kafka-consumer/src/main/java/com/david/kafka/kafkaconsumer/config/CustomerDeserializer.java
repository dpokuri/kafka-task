package com.david.kafka.kafkaconsumer.config;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.david.kafka.kafkaconsumer.model.Customer;

public class CustomerDeserializer implements Deserializer {

	private String encoding = "UTF8";

	@Override
	public void configure(Map configs, boolean isKey) {
		
		
	}

	@Override
	public Object deserialize(String topic, byte[] data) {
		
		try {
			
			if(data == null) {
				System.out.println("Null recieved at deserialized");
				return null;
			}
			
			ByteBuffer buf = ByteBuffer.wrap(data);
			
			int sizeofCustId = buf.getInt();
			byte[] custIdBytes = new byte[sizeofCustId];
			buf.get(custIdBytes);
			String deserCustId = new String(custIdBytes, encoding );
			
			int sizeOfCustName = buf.getInt();
			byte[] custNameBytes = new byte[sizeOfCustName];
			buf.get(custNameBytes);
			String deserCustName = new String(custNameBytes, encoding );
			
			return new Customer(deserCustId, deserCustName);
			
			
			
			
		}catch(Exception e) {
			
		}
		
		return null;
	}

	@Override
	public void close() {
		
		
	}

	
	
	

}
