package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeasingPage {
	private static final String WEBSITE_URL = "https://www.seb.lt/verslui/finansavimas/lizingas-verslui#paragraph13563";
	HTTPSChecker checker;
	WebDriver driver;
	JavascriptExecutor js;

	public LeasingPage(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}

	public static String getWebsiteUrl() {
		return WEBSITE_URL;
	}

	public void maximizeWindow() {
		driver.manage().window().fullscreen();
	}

	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Boolean pageLoaded = (Boolean) wait
				.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
		if (pageLoaded) {
			System.out.println("Website successfully loaded");
		} else {
			System.out.println("Website did not load properly");
		}
	}

	public void closeCookiesModal() {
		try {
			WebElement modal = driver.findElement(By.cssSelector(".show-consent.modal-open"));
			System.out.println("Cookies modal found, trying to close it..");
			WebElement closeButton = modal.findElement(By.cssSelector(".accept-selected"));
			closeButton.click();
		} catch (NoSuchElementException e) {
			System.out.println("Cookies Modal Not found");
		}

	}

	public void openIframe() {
		WebElement iFrame = driver.findElement(By.id("calculator-frame-06"));
		driver.switchTo().frame(iFrame);
	}

	public void closeIframe() {
		driver.switchTo().defaultContent();
	}

	public void submitCalculations() {
		WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
		submitButton.click();
	}

	public List<Object> leasingCalculatorOperations() {
		String[] values = new String[6];
		values[0] = setPurchaseValue(8000, 50000);
		values[1] = setInterestRate(2, 10);
		values[2] = selectRandomPaymentTerm();
		values[3] = setFirstInstallment(1000, 5000);
		values[4] = setRemainingValue(0, 0);

		List<Object> valuesList = new ArrayList<>();
		valuesList.add(Integer.parseInt(values[0]));
		valuesList.add(Double.parseDouble(values[1]));
		valuesList.add(Integer.parseInt(values[2]));
		valuesList.add(Integer.parseInt(values[3]));
		valuesList.add(Integer.parseInt(values[4]));
		return valuesList;
	}

	public double paymentInformation() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement monthlyPayment = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='monthly_payment']")));

		return Double.parseDouble(monthlyPayment.getText().replaceAll("[^\\d.]", ""));
	}

	private String setPurchaseValue(int min, int max) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement purchaseValue = wait.until(ExpectedConditions.elementToBeClickable(By.id("f-summa")));
		purchaseValue.click();
		Random rand = new Random();
		int value = rand.nextInt((max - min) + 1) + min;
		String purchaseVal = String.valueOf(value);
		System.out.println("Setting purchase value amount " + purchaseVal + " Eur");
		purchaseValue.sendKeys(purchaseVal);
		return purchaseVal;
	}

	private String setInterestRate(int min, int max) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement interestRate = wait.until(ExpectedConditions.elementToBeClickable(By.id("f-likme")));
		interestRate.click();
		interestRate.clear();
		Random rand = new Random();
		int value = rand.nextInt((max - min) + 1) + min;
		String rate = String.valueOf(value);
		System.out.println("Setting interest rate value to " + rate + "%");
		interestRate.sendKeys(rate);
		return rate;
	}

	private String selectRandomPaymentTerm() {
		WebElement paymentTerm = driver.findElement(By.id("f-termins"));
		Select select = new Select(paymentTerm);
		List<WebElement> options = select.getOptions();
		Random rand = new Random();
		int index = rand.nextInt(options.size());
		WebElement option = options.get(index);
		System.out.println("Setting payment term to " + option.getAttribute("value") + " months");
		select.selectByVisibleText(option.getText());
		return option.getAttribute("value");
	}

	private String setFirstInstallment(int min, int max) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement firstInstallmentType = wait.until(ExpectedConditions.elementToBeClickable(By.id("f-maksa-type")));
		Select dropdown = new Select(firstInstallmentType);
		dropdown.selectByIndex(1);
		WebElement firstInstallment = wait.until(ExpectedConditions.elementToBeClickable(By.id("f-maksa")));
		firstInstallment.click();
		firstInstallment.clear();
		Random rand = new Random();
		int value = 1000 + 1000 * rand.nextInt(5);
		String installment = String.valueOf(value);
		System.out.println("Setting first installment " + installment + " Eur");
		firstInstallment.sendKeys(installment);
		return installment;
	}

	private String setRemainingValue(int min, int max) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement remainingValue = wait.until(ExpectedConditions.elementToBeClickable(By.id("f-rest")));
		remainingValue.click();
		remainingValue.clear();
		Random rand = new Random();
		int value = rand.nextInt((max - min) + 1) + min;
		String remainingval = String.valueOf(value);
		System.out.println("Setting remaining value " + remainingval + "%");
		remainingValue.sendKeys(remainingval);
		return remainingval;
	}

	public double calculateMonthlyPayment(int principal, double interestRate, int months, int firstInstallment) {
		double remainingPrincipal = principal - firstInstallment;
		double monthlyPayment = calculateMonthlyPayment(remainingPrincipal, interestRate, months);
		double rounded = (double) Math.round(monthlyPayment * 100) / 100;
		return rounded;
	}

	// Calculate the monthly payment
	static double calculateMonthlyPayment(double principal, double interestRate, int months) {
		// Calculate the monthly rate
		double monthlyRate = interestRate / 100 / 12;

		// Calculate the monthly payment
		double payment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
		double rounded = (double) Math.round(payment * 100) / 100;
		return rounded;
	}

	public void assertMonthlyPayments(double actualAmount, double expectedAmount) {
		assert expectedAmount == actualAmount : "Expected amount and actual amount are not equal";
	}
}
