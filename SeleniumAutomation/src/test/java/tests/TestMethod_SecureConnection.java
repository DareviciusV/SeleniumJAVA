package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import objects.HTTPSChecker;

public class TestMethod_SecureConnection {
	HTTPSChecker checker;
	WebDriver driver;

	@BeforeTest
	public void beforetest() throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		checker = new HTTPSChecker("https://www.seb.lt/verslui/finansavimas/lizingas-verslui#paragraph13563");
		if (!checker.isSecure()) {
			throw new RuntimeException("Connection is not secure");
		} else {
			String cipherSuite = checker.getCipherSuite();
			System.out.println("Connection is secure. Cipher suite: " + cipherSuite);
		}
	}

	@Test
	public void testConnectionSecurity() {
		try {
			int responseCode = checker.connection.getResponseCode();
			System.out.println("Response code: " + responseCode);
			assert responseCode == 200 : "Connection is not secure";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void aftertest() {
		driver.quit();
	}
}
