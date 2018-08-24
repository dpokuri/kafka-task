# Customers - Drivers task

Please follow the below steps to run the applications

1. Download the code for this git repository - there are 2 spring boot applications(Producer and Consumer)
2. Import kafka-producer app into IDE and Run as a java application. Observe console for response.
3. Import kafka-consumer app into IDE and Run as a java application. Observe console for response.
   
Explanation:
1. Here I have used Executor framework to send 5 Customer requests concurrently.
2. Sending Customer object as request and recieving Driver object as response.
3. I have created HashMap to create and store 10 Driver objects with initial status - NOT_ASSIGNED
4. Once Driver is assigned to customer then will updated driver object with status - ASSGINED-ON_DRIVE and attached corresponding customer.
5. Observe console output of both applications to get clarity.

Note: We can extend this app by adding different functionalities and checks.
