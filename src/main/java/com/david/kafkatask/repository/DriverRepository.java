package com.david.kafkatask.repository;

import java.util.HashMap;
import java.util.Map;

import com.david.kafkatask.model.Driver;

public class DriverRepository {
	
static Map< String, Driver> driverMap = new HashMap<String, Driver>();
	
	public static void prepareDriverObjects() {
	
		for(int i = 1 ; i <= 10; i++ ) {
			
			Driver tempDriverObj = new Driver();
			tempDriverObj.setDriverId("D100"+i);
			tempDriverObj.setDriverName("Driver"+i);
			tempDriverObj.setCarDetails("Car"+i);
			tempDriverObj.setDriverAddr("Addr"+i);
			tempDriverObj.setDriverStatus("NOT_ASSIGNED");
			driverMap.put("D100"+i, tempDriverObj);
			
		}
	}
	
	public static Map< String, Driver> getDriverMap(){
		return driverMap;
	}


}
