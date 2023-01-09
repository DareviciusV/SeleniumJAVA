# Selenium Java TestNG
## Prioritized test plan
* Verify that the website/ application's connection is secure
* Test the application's ability to load and function correctly on different devices
* Test the calculations for different leasing scenarios
* Testing the application's usability and user experience
* Testing the application's handling of localization and translation


To verify the functionality of the calculator, it is important to first ensure that you are connecting to a secure BANK website(Not a Phishing one). You should also check that the website loads correctly and that certain elements are present and functioning as expected. Once these initial checks have been completed, you can then proceed to test different scenarios and inputs to verify the calculator's behavior. It is also recommended to test the calculator's performance and functionality on different languages, as well as on different devices. Additionally, when testing the calculator, it is important to ensure that any buttons or functionality related to applying for a loan are working properly and that users are able to easily access them."

## Description

This repository contains an example project for testing the leasing calculator and secure connection of a bank's website using Selenium and TestNG.

## Dependencies
* Selenium
* TestNG
* WebDriverManager

## Documentation for dependencies
* Selenium - https://www.selenium.dev/documentation/
* TestNG - https://testng.org/doc/documentation-main.html
* WebDriverManager - https://bonigarcia.dev/webdrivermanager/

## Running the tests
To run the tests, use the following command:
### mvn clean test
## Test Method 1: Leasing Calculator
This test method opens the bank's website, fills out the leasing calculator form, and submits it. It then calculates the expected monthly payment and compares it to the actual monthly payment displayed on the website.
## Test Method 2: Leasing Calculator
This test method checks if the connection to the bank's website is secure by verifying the response code.

## Notes:
Install via TESTNG for Eclipse via Marketplace:
https://marketplace.eclipse.org/content/testng-eclipse
