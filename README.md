# Selenium Java TestNG

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
mvn clean test
### Test Method 1: Leasing Calculator
This test method opens the bank's website, fills out the leasing calculator form, and submits it. It then calculates the expected monthly payment and compares it to the actual monthly payment displayed on the website.
### Test Method 2: Leasing Calculator
This test method checks if the connection to the bank's website is secure by verifying the response code.

##Notes:
Install via TESTNG for Eclipse via Marketplace:
https://marketplace.eclipse.org/content/testng-eclipse
