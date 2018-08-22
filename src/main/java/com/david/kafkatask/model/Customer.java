package com.david.kafkatask.model;

public class Customer {
	
	private String custId;
	private String custrName;
	private String custAddr;
	
	public Customer() {
		super();
	}

	public Customer(String custId, String custrName, String custAddr) {
		super();
		this.custId = custId;
		this.custrName = custrName;
		this.custAddr = custAddr;
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

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custrName=" + custrName + ", custAddr=" + custAddr + "]";
	}

}
