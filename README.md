# DevelopmentBooks 📚

Get the best software development books with special discounts when buying multiple copies!

---

## 📖 Available Books

This series features essential reads for developers looking to enhance their skills:

- **Clean Code** by Robert Martin (2008)
- **The Clean Coder** by Robert Martin (2011)
- **Clean Architecture** by Robert Martin (2017)
- **Test Driven Development by Example** by Kent Beck (2003)
- **Working Effectively With Legacy Code** by Michael C. Feathers (2004)

---

## 🎯 Pricing & Discount Rules

Each book costs **50 EUR**. However, special discounts apply when purchasing multiple different books:

- 2 books: 5% discount

- 3 books: 10% discount

- 4 books: 20% discount

- 5 books (full set): 25% discount

💡 **Important:** If you buy multiple copies of the same title, they do not count toward the discount set. The discount applies only to different books.


---

## 🛒 Example Calculation

If a customer buys:

- 2 copies of *Clean Code*
- 2 copies of *The Clean Coder*
- 2 copies of *Clean Architecture*
- 1 copy of *Test Driven Development by Example*
- 1 copy of *Working Effectively With Legacy Code*

### Total Price Calculation:

1. **For the first 4 different books:**
   - 4 books x 50 EUR = 200 EUR  
   - Apply 20% discount:  
     200 EUR - 20% = 160 EUR

2. **For the last 4 different books:**
   - 4 books x 50 EUR = 200 EUR  
   - Apply 20% discount:  
     200 EUR - 20% = 160 EUR

### Total:  
**160 EUR + 160 EUR = 320 EUR**

---

💡 **Knowledge is priceless, but has a cost!**


## 🔧 **Tools Used**
---
[List any specific tools or libraries used in development]

### 🖥️ **Backend**
- **Java 17**  
- **Spring Boot 3.4.4**  
- **OpenAPI**  

### 🏗️ **Build Tool**
- **Maven 3.9.9**  
- **Lombok v1.18.38**  

### 📊 **Reporting**
- **Jacoco 0.8.8**  

### 🖥️ **IDE**
- **Eclipse 2025-06 M1 (4.36.0 M1)** 

# DevelopmentBook - Application Guide

## **Table of Contents**

1. [How to Build the Application](#how-to-build-the-application)
2. [How to Run the Application](#how-to-run-the-application)
3. [How to Access the Application](#how-to-access-the-application)
4. [How to Access the Code Coverage Report](#how-to-access-the-code-coverage-report)
5. [How to Test the application](#how-to-test-the-application)

---

## 🏗️**How to Build the Application**
 
**1. Clone the Repository**
 
     bash
     git clone https://github.com/2024-DEV3-005/DevelopmentBooks


**2. Install Lombok and MapStruct Plugins in Eclipse and IntelliJ IDEA**

    Install Lombok and Mapstruct plugins to IDE(Ecllipse or Intellij) before building the project. Instruction to install the plugins are readily available over web.


**3. Troubleshooting**

    For Lombok:  
      If you encounter issues with Lombok annotations not working, ensure **annotation processing** is enabled in your IDE settings (both Eclipse and IntelliJ IDEA).

    For MapStruct:  
      Make sure **annotation processing** is enabled, as it is required for generating the MapStruct implementations.
    
    For any bean not found error please do a maven clean build
---
**4. Build the Project and Run Tests**

     mvn clean install

# 🏗️ **How to Run the Application**

By default, the application will start on **port 8080**. If you wish to run the application on a different port, such as **8082**, you have two options:

** Option 1: Passing the Port Parameter via Command Line**

When starting the application, you can pass an additional parameter to change the port number. For example, to run the application on port 8082:

     ```bash
     ./mvn spring-boot:run --server.port=8082
     
** Option 2: Updating the application.properties File**
You can also configure the port in the application.properties file, which is located in src/main/resources:

Open the application.properties file. Add or update the following line to set the port to 8082

    server.port=8082

**Running the Application**
Once the repository is cloned, dependencies are installed, and the port is configured, you can run the service with one of the following commands:

     mvn spring-boot:run
     
Or you can run as jar

     java -jar target\DevelopmentBooks-0.0.1-SNAPSHOT.jar
            
                            (or)
                            
     java -jar target\DevelopmentBooks-0.0.1-SNAPSHOT.jar --server.port=8082

     
 Once the application starts the system log will be like below
 
    2025-05-26T04:14:48.520+05:30  INFO 3068 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 1 endpoint beneath base path '/actuator'
    2025-05-26T04:14:48.712+05:30  INFO 3068 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
    2025-05-26T04:14:48.765+05:30  INFO 3068 --- [           main] com.store.book.BookStoreApplication      : Started BookStoreApplication in 9.559 seconds (process running for 10.537)
 
 
# 🖥️ **How to Access the Application **
 
 - Application end point to configure and consume
 
    http://localhost:8080/store/calculatePrice
 
 - Health check end point
 
    http://localhost:8080/actuator/info (or) http://localhost:<port>/actuator/info
    
    {"app":{"name":"Bookstore API - Buy and Save Online","version":"0.0.1-SNAPSHOT","description":"Get the best software development books with special discounts"}}
 
    http://localhost:8080/actuator/health (or) http://localhost:<port>/actuator/health
    
    {"status":"UP","components":{"diskSpace":{"status":"UP","details":{"total":209715195904,"free":65491709952,"threshold":10485760,"path":"D:\\Apps\\DevelopmentBooks\\kata\\DevelopmentBooks\\DevelopmentBooks\\.","exists":true}},"ping":{"status":"UP"},"ssl":{"status":"UP","details":{"validChains":[],"invalidChains":[]}}}}

 - OpenApi - swaggar End point
 
    http://localhost:8080/swagger-ui.html (or) http://localhost:<port>/swagger-ui.html
    
# 📊 **How to Access the Code Coverage Report **


**1. Clone the Repository**

**2. Run maven build**
      
       mvn clean install
       
**3. Find Jacoco report in the below path**

    $buildDir/target/site/jacoco/index.html
    
## 🏗️ **How to Test the application**

**1. Overview of the calculatePrice API**

The calculatePrice API is a POST service. It expects the following two parameters in the request body:

- **Serial number:** The unique identifier for a book.

- **Quantity:** The number of books selected for checkout.

You can use the serial numbers provided below for the available books.

** 2. Available Books and Their Serial Numbers**

- Clean Code 
   
         Serial Number: 1  
  
- The Clean Coder  
   
         Serial Number: 2  

- Clean Architecture  
           
         Serial Number: 3  

- Test Driven Development by Example  
       
         Serial Number: 4  

- Working Effectively With Legacy Code  
    
         Serial Number: 5  


**3. API Endpoint**
**URL:** /api/calculatePrice

**Method:** POST

**4. Sample Request**

To test the calculatePrice API, you need to send a POST request with the following JSON body:

          {
            "booksToOrder": [
              {
                 "serialNumber": "1",
                 "quantity": 1
              }]
          }

**Request Explanation:**

- **serialNumber:** Choose one of the serial numbers from the table above (e.g., "1" for "Clean Code").

- **quantity:** The number of books you want to check out (e.g., 2 books).


**5. Example Requests**

Example 1:
For the book Clean Code with serial number 1 and quantity 2, the request body would look like this:

           {
            "booksToOrder": [{
               "serialNumber": "1",
               "quantity": 2
               }]
           }

**6. Expected Response**

If the book exists and the quantity is valid, the response will contain the best possible Order summary and combination of books that can be checked out by customer.

**7. How to Make the Request**
You can use a variety of tools to test the API, including:

- **Postman:** A popular API testing tool.

- **Curl:** A command-line tool for making HTTP requests.

**Using Postman:**

Open Postman.

Set the request type to POST.

Enter the request URL (e.g., http://localhost:8080/store/calculatePrice).

Add the JSON body in the request body section.

Click Send to make the request.

**Using Curl:**

You can also use curl to send the POST request from the terminal.

     bash
    Copy
    Edit
    curl -X POST http://localhost:8080/store/calculatePrice \
    -H "Content-Type: application/json" \
    -d '{"booksToOrder": [{"serialNumber": "1", "quantity": 2}]}'
    
    
This will send the request to the calculatePrice API and return the total price, Discounted price (if any) as a response.

**8. Troubleshooting**

If you encounter any issues while testing the calculatePrice API, here are some steps you can take:

- **Invalid Serial Number:** Make sure you're using one of the valid serial numbers from the list.

- **Invalid Quantity:** Ensure the minimum quantity is selected.

- **Book Not Found:** Make sure the serial number is associated with one of the available book.

- **Duplicate Book:** Ensure request has no duplicate serial number 

- **Empty Basket:** Shopping basket should not be empty


**9. Conclusion**
By following the above steps, one should be able to test the calculatePrice API successfully. If you encounter any issues or have questions, feel free to ask!
