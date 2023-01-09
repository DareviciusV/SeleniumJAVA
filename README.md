Selenium Java TestNG
This repository contains an example project for testing the leasing calculator and secure connection of a bank's website using Selenium and TestNG.

Dependencies
Selenium
TestNG
WebDriverManager
Running the tests
To run the tests, use the following command:

Copy code
mvn clean test
Test Method 1: Leasing Calculator
This test method opens the bank's website, fills out the leasing calculator form, and submits it. It then calculates the expected monthly payment and compares it to the actual monthly payment displayed on the website.

Test Method 2: Secure Connection
This test method checks if the connection to the bank's website is secure by verifying the response code.

Notes
The test methods are set to run in parallel.
The website URL, as well as any other test data, can be modified in the objects package.
The ChromeDriver executable must be added to your system's PATH environment variable for the tests to run. You can also use WebDriverManager to handle the driver setup for you.
