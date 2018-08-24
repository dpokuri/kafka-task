package com.david.kafka.kafkaproducer.model;

public class Customer{
	
	private String custId;
	private String custrName;

	
	public Customer() {
		super();
	}

	public Customer(String custId, String custrName) {
		super();
		this.custId = custId;
		this.custrName = custrName;
		
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustrName() {
		return custrName;
	}

	public void setCustrName(String custrName) {
		this.custrName = custrName;
	}

	

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custrName=" + custrName  + "]";
	}

	

}
