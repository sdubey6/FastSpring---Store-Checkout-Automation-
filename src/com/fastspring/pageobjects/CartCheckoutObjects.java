package com.fastspring.pageobjects;

//This class contains the objects in the check out page 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


public class CartCheckoutObjects {

	 public WebDriver driver;
     
	    public CartCheckoutObjects(WebDriver driver) {       
	        this.driver = driver;
	    }
	    
	  public  By countryname = By.xpath("//*[@class=\"btn btn-default btn-sm dropdown-toggle\"]");
	   public By cardbutton = By.xpath("//*[@class =\"payment-icon card\"]");
	    public By mexico = By.linkText("Mexico");
	    public By asia = By.linkText("Asia");
	    public By checkoutValue_MXN = By.xpath("//*[@class = \"price-item price price-color\"]");
	    public By checkoutValue_USD = By.xpath("//*[@class = \"price-item price price-color\"]");
	    public By usa = By.linkText("United States");
	   
}
