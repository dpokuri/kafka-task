package com.david.kafka.kafkaconsumer.model;

public class Driver {
	
	private String driverId;
	private String driverName;
	private String custDetails;
	private String driverAddr;
	private String driverStatus;
	
	public Driver() {
	}
	
	public Driver(String driverId, String driverName, String custDetails, String driverAddr, String driverStatus) {
		super();
		this.driverId = driverId;
		this.driverName = driverName;
		this.custDetails = custDetails;
		this.driverAddr = driverAddr;
		this.driverStatus = driverStatus;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getCustDetails() {
		return custDetails;
	}
	public void setCustDetails(String custDetails) {
		this.custDetails = custDetails;
	}
	public String getDriverAddr() {
		return driverAddr;
	}
	public void setDriverAddr(String driverAddr) {
		this.driverAddr = driverAddr;
	}
	
	public String getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
	}

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", driverName=" + driverName + ", carDetails=" + custDetails
				+ ", driverAddr=" + driverAddr + "]";
	}




}
