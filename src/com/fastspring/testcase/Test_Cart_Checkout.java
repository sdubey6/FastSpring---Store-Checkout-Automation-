package com.fastspring.testcase;

//This is the cart checkout test case

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fastspring.pageobjects.CartCheckoutObjects;
import com.fastspring.pageobjects.CartCheckout_DebitCard_Modal;
import com.fastspring.utilities.*;

public class Test_Cart_Checkout {
	public static WebDriver driver;
	CartCheckoutObjects cco = new CartCheckoutObjects(driver);
	CartCheckout_DebitCard_Modal ccom = new CartCheckout_DebitCard_Modal(driver);
	ExcelUtils inputdata = new ExcelUtils();
	ExcelUtils outputdata = new ExcelUtils();

	@BeforeTest
	public void launchBrowser() throws Exception {
		boolean success;
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("Cart Check out Tests");
		System.setProperty("webdriver.chrome.driver", Constants.driverPath);
		inputdata.setExcelFile(Constants.Path_TestData + Constants.File_TestData, "Cartcheckout");
		outputdata.setExcelFile(Constants.Path_OutputData + Constants.File_OutputData, "Output_Test_Cartcheckout");

		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try { 
			driver.get(Constants.URL);
			success = true;
		}		
		catch (Throwable t) {
			Log.error("Launch URL - failed");
			Log.info(t.getMessage().toString());
			outputdata.setCellData("Failed",3,1);
			throw t;
		}
		if (success) {
			Log.info("Launch URL - Complete");
			outputdata.setCellData("Pass",3,1);
		}
		

	}
  
	@Test(priority = 0)
	public void verifyPageTitle() throws Exception {
		boolean success;
		String expectedTitle = "FastSpring Checkout";
		String actualTitle = driver.getTitle();
		try {
			Assert.assertEquals(actualTitle, expectedTitle);
			success = true;
			}
			
		 catch (Throwable t) {
			Log.error("Verify Page Title - failed");
			Log.info(t.getMessage().toString());
			outputdata.setCellData("Failed",4,1);
			throw t;
		}
		if (success) {Log.info("Verify Page Title - Complete");
		outputdata.setCellData("Pass",4,1);
		}
	}

	@Test(priority = 1) // (enabled = false)
	public void verifyChangeCountry() throws Exception {
		boolean success = false;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			//driver.navigate().refresh();
		WebElement element = driver.findElement(cco.countryname);
		wait.until(ExpectedConditions.visibilityOf(element));
		driver.findElement(cco.countryname).click();
		
		//driver.findElement(cco.countryname).click();

		driver.findElement(cco.mexico).click();
		String expectedValue_MXN = inputdata.getCellData(1, 12);
		
		// WebElement test;
		wait.until(ExpectedConditions.textToBePresentInElementLocated(cco.checkoutValue_MXN, "M"));
		String actualValue_MXN = driver.findElement(cco.checkoutValue_MXN).getText();
		
			Assert.assertEquals(actualValue_MXN, expectedValue_MXN);
			 success = true;
		
		} catch (Throwable t) {
			Log.error("Change country from USA to Mexico  - failed");
			
			Log.info(t.getMessage().toString());
			Log.error("Change country from Mexico to USA  - failed");
			outputdata.setCellData("Failed",5,1);
			outputdata.setCellData("Failed",6,1);
			
			throw t;
	
		}
		if (success) {Log.info("Change country from USA to Mexico  - Complete");
		outputdata.setCellData("Pass",5,1);
		}
		try {
		driver.findElement(cco.countryname).click();

		// Changing the country Back again to USA
		driver.findElement(cco.usa).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(cco.checkoutValue_USD, "$"));
		String expectedValue_USD = inputdata.getCellData(1, 13);
		String actualValue_USD = driver.findElement(cco.checkoutValue_USD).getText();
		
			Assert.assertEquals(actualValue_USD, expectedValue_USD);
			success = true;
		} catch (Throwable t) {
			Log.error("Change country from Mexico to USA  - failed");
			Log.info(t.getMessage().toString());
			outputdata.setCellData("Failed",6,1);
			throw t;
		}
		if(success) {
			outputdata.setCellData("Pass",6,1);
			Log.info("Change country from Mexico to USA  - Complete");
		}
		
	}

@Test(priority = 2, dependsOnMethods = { "verifyChangeCountry" })
	public void fillCheckoutDetails() throws Exception {
		boolean success;
		try {
			driver.findElement(cco.cardbutton).click();
			// Filling all the Billing details
			driver.findElement(ccom.firstname).sendKeys(inputdata.getCellData(1, 0));
			driver.findElement(ccom.lastname).sendKeys(inputdata.getCellData(1, 1));
			driver.findElement(ccom.email).sendKeys(inputdata.getCellData(1, 2));

			driver.findElement(ccom.card_number).sendKeys(inputdata.getCellData(1, 3));
			driver.findElement(ccom.card_month).sendKeys(inputdata.getCellData(1, 4));
			driver.findElement(ccom.card_year).sendKeys(inputdata.getCellData(1, 5));
			driver.findElement(ccom.card_cvv).sendKeys(inputdata.getCellData(1, 6));
			driver.findElement(ccom.billingaddress1).sendKeys(inputdata.getCellData(1, 7));
			driver.findElement(ccom.billingadress_city).sendKeys(inputdata.getCellData(1, 8));
			driver.findElement(ccom.billingadress_state).sendKeys(inputdata.getCellData(1, 9));
			driver.findElement(ccom.billingadress_postalCode).sendKeys(inputdata.getCellData(1, 10));
			driver.findElement(ccom.billingadress_phonenumber).sendKeys(inputdata.getCellData(1, 11));
			success = true;
		}
		catch (Exception e) {
			Log.error("fillCheckoutDetails - Failed");
			Log.info(e.getMessage().toString());
			outputdata.setCellData("Failed",7,1);
			throw e;
		}
		if(success) {
			Log.info("fillCheckoutDetails - Complete");
			outputdata.setCellData("Pass",7,1);
		}

	}

	
@Test (priority = 3, dependsOnMethods = { "fillCheckoutDetails" })
	public void verifyTaxAmount() throws Exception {
		
	boolean success;
		// Check for Tax amount
		WebDriverWait wait = new WebDriverWait(driver, 3);
		// WebElement test;
		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(ccom.tax_amount, "$"));
		// test.click();
		driver.findElement(ccom.tax_amount).click();
		String actualTax = driver.findElement(By.xpath("//*[@id=\"address\"]/div[3]/div[2]/span[2]/span[2]")).getText();
		String expectedTax = inputdata.getCellData(1, 14);
		System.out.println(expectedTax);
		System.out.println(actualTax);

		
			Assert.assertEquals(actualTax, expectedTax);
			success = true;
		}			
		 catch (Throwable t) {
			Log.error("Tax verification  - Failed");
			Log.info(t.getMessage().toString());
			outputdata.setCellData("Failed",8,1);
			throw t;
		}
		if (success) {
			Log.info("Tax verification  - Completed");
			outputdata.setCellData("Pass",8,1);
		}
	}

	@Test (priority = 4, dependsOnMethods = { "verifyTaxAmount"})
	public void submitpayment() throws Exception {
		boolean success;
		String licence_number;
		try {
		// click "Pay $10"
		WebElement ele = driver.findElement(ccom.submit_button);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
		String expectedmessage = "Thank you for your order!";
		WebElement element = driver.findElement(ccom.final_message);
		String licence_text = driver.findElement(ccom.Licence_key).getText();
		String [] licence_number_array = licence_text.split(" ");
		 licence_number = licence_number_array[3];
		System.out.println(licence_number);
		
			Assert.assertEquals(element.getText(), expectedmessage);
			success = true;
		} catch (Throwable t) {
			Log.error("Submit Payment  - Failed");
			Log.info(t.getMessage().toString());
			outputdata.setCellData("Failed",9,1);
			throw t;
		}
		
		
	if (success) {
		Log.info("Submit Payment  - Passed");
		outputdata.setCellData("Pass",9,1);
		outputdata.setCellData("Order Licence Number = " + licence_number,9,2);
		
	}
	}

	@AfterTest
	public void terminateBrowser() {
		Log.endTestCase("Cart_Checkout");
		driver.close();
	}
}
