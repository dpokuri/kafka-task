# Customers - Drivers task

Please follow the below steps to run this task.

1. Download the code from this git repository
2. Import into your Editor and run the code from main class 'KafkaTaskApplication.java'.
    (i). Its Spring Boot application
    (ii). Kafka setup should require to run this application.
3.For Producer
  Send the request using any rest client by using the following format.
   
   POST: http://localhost:8080/api/drivers
   {
	  "custId":"CUST1001",
	  "custName":"CUST1"
   }
   
   Here we are sending customer object as request with the above attributes.
   
4. For Consumer
   
   Here we are recieving the Driver object as response.
   
   {
    "driverId": "D1005",
    "driverName": "Driver5",
    "driverStatus": "ASSIGNED - ON DRIVE"
   }
   
 Notes: 
  1. Once all drivers are assigned to customers then we will get 
     'DRIVERS ARE NOT AVAILABLE AT THIS TIME, WE WILL NOTIFY ONCE DRIVERS ARE AVAILABLE' message.
     
  2. We can extend this problem by reset the drivers status and make it available for other customers.
 
 
