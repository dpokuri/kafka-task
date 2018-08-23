package com.david.kafkatask.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "custId", "custName", "driverId", "driverName", "driverStatus" })
public class Model {

	
	@JsonProperty("custId")
	private String custId;
	
	@JsonProperty("custName")
	private String custName;
	
	@JsonProperty("driverId")
	private String driverId;
	
	@JsonProperty("driverName")
	private String driverName;
	
	@JsonProperty("driverStatus")
	private String driverStatus;

	public Model() {

	}

	public Model(String custId, String custName, String driverId, String driverName, String driverStatus) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.driverId = driverId;
		this.driverName = driverName;
		this.driverStatus = driverStatus;
	}

	@JsonProperty("custId")
	public String getCustId() {
		return custId;
	}

	@JsonProperty("custId")
	public void setCustId(String custId) {
		this.custId = custId;
	}

	@JsonProperty("custName")
	public String getCustName() {
		return custName;
	}

	@JsonProperty("custName")
	public void setCustName(String custName) {
		this.custName = custName;
	}

	@JsonProperty("driverId")
	public String getDriverId() {
		return driverId;
	}

	@JsonProperty("driverId")
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	@JsonProperty("driverName")
	public String getDriverName() {
		return driverName;
	}

	@JsonProperty("driverName")
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	@JsonProperty("driverStatus")
	public String getDriverStatus() {
		return driverStatus;
	}

	@JsonProperty("driverStatus")
	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
	}

	@Override
	public String toString() {
		return "Model [custId=" + custId + ", custName=" + custName + ", driverId=" + driverId + ", driverName="
				+ driverName + ", driverStatus=" + driverStatus + "]";
	}

}

