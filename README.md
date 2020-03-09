# Car_Rental_Application

CarRentalApplication
1.	How to run this application -
    Developed this application in Spring boot framework.So to run this project, please see below few steps - 

1.Need to clone/ download the project. 
2.import it in STS/ Eclipse/ any suitable IDE. 
3.run this project as Spring Boot Application.


2.	Technologies used - 
    Built this project with some of technologies being used in daily work on as a software developer. 
    1.Java 1.8 
    2.Spring Boot 
    3.Angular Js
    
    
3.Requirements -
  Requirements for this project were - 
  1. Car make and model can be different styles (economy, luxury) and sizes (compact, midsize, luxury). 
  2. Each car is assigned to a certain location. Each location has a certain number of parking spots (2 to 5). 
  3. A customer can choose a location and see the list of available cars for a certain date and time.
  4. He/she can reserve a car for a certain number of hours. 
  5. He/she can make reservations upto 2 weeks in advance. 
  6. When a car is reserved it does not show up in the list of available cars in the location.
  
  
4.	Functionality implemented - 
    The above given requirements are implemented as follows - 
    
i.	There are two styles of cars available - economy and luxury.
ii.	There are 2 service locations from where people can reserve the rental car - "Malvern" and "DowningTown"
iii.	All the available locations will be displayed on the UI with radio buttons. User needs to select any one location.
iv.	User also need to put From_Date and From_Time , To_Date , To_Time in the appropriate format. The format is displayed in textBoxes       for easy reference.
v.	On click of submit, A table with available cars at the selected location will be displayed. Only those cars will be displayed which     are available for the requested period, which user had provided above.
vi.	In the table, user can click on the respective reserve button to reserve a specif car model.
vii.	Once user clicks on reserve button, the car will be reserved for that particular duration. and this car will be removed from the          available cars list when user hit submit again with the same dates, user can now find less number of cars.

5. Conditions and logs for better understanding considered -
  1. There is input validation performed when user enters dates and location.
      1. Dates must be valid in the given format.
      2. dates must not be from the past / before today.
      3. requested dates must not be greater than 2 weeks from current date. This is to make sure user make reservation upto 2 weeks in          advance.
      4. Considered every location has 2 parking spots of 2 cars
      4. All the input validation errors are maintained in a list . The response object with list of errors can be seen by doing F12              console on chrome browser.
      5. Also all the exceptions are maintaind in the same list of errors and printed it in logs.
      
      
 

