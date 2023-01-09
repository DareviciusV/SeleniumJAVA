package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import objects.LeasingPage;

public class TestMethod_LeasingCalculator {
	WebDriver driver;

	@BeforeTest
	public void beforetest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(LeasingPage.getWebsiteUrl());

	}

	@Test
	public void LeasingTest() {
		LeasingPage page = new LeasingPage(driver);
		page.maximizeWindow();
		page.waitForPageLoad();
		page.closeCookiesModal();
		page.openIframe();
		List<Object> valuesList = page.leasingCalculatorOperations();
		Integer PurchaseValue = (Integer) valuesList.get(0);
		Double InterestRate = (Double) valuesList.get(1);
		Integer PaymentTerm = (Integer) valuesList.get(2);
		Integer FirstInstallment = (Integer) valuesList.get(3);
		page.submitCalculations();
		double expectedAmount = page.calculateMonthlyPayment(PurchaseValue, InterestRate, PaymentTerm,
				FirstInstallment);
		double actualAmount = page.paymentInformation();
		System.out.println("Expected monthly amount: " + expectedAmount + " Eur");
		System.out.println("Actual monthly amount " + actualAmount + " Eur");
		page.assertMonthlyPayments(expectedAmount, actualAmount);
		page.closeIframe();

	}

	@AfterTest
	public void aftertest() {
		driver.quit();
	}

}
