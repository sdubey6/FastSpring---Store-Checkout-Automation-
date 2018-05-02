package com.fastspring.pageobjects;

//This class contains the object in the credit/debit card modal box
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartCheckout_DebitCard_Modal {
	 public WebDriver driver;
	   public CartCheckout_DebitCard_Modal(WebDriver driver) {       
	        this.driver = driver;
	    }

	 public By firstname = By.name("contact.firstName");
	   public By lastname = By.name("contact.lastName");
	   public By email = By.name("contact.email");
	   public  By card_number = By.name("card.number");
	   public By card_month = By.name("card.month");  
	   public  By card_year = By.name("card.year");
	    public By card_cvv = By.name("card.security");
	   public  By billingaddress1 = By.name("contact.addressline1");
	   public By billingadress_city = By.name("contact.city");
	   public By billingadress_state = By.name("contact.region");
	   public By billingadress_postalCode = By.name("contact.postalCode");
	   public By billingadress_phonenumber = By.name("contact.phoneNumber");
	   public By tax_amount = By.xpath("//*[@id=\"address\"]/div[3]/div[2]/span[2]/span[2]");
	    public By submit_button = By.xpath("//*[@type=\"submit\"]");
	    public By final_message = By.xpath("//*[@id=\"content\"]/div/div[1]/h3");
	    public By Licence_key = By.xpath("//*[@id=\"subscription-product\"]/td[2]/div[3]/p/p[2]");
}
