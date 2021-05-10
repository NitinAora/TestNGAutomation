package com.shop.demoqa.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shop.demoqa.base.ShopDemoQaBase;

public class CheckoutDetailsPage extends ShopDemoQaBase {

	// These are Page factory
	@FindBy(id = "billing_first_name")
	WebElement firstName;

	@FindBy(id = "billing_last_name")
	WebElement lastName;

	@FindBy(id = "billing_address_1")
	WebElement streetAddress;

	@FindBy(xpath = "//input[@id='billing_city']")
	WebElement town;

	@FindBy(id = "billing_postcode")
	WebElement pinCode;

	@FindBy(id = "billing_phone")
	WebElement phone;

	@FindBy(id = "billing_email")
	WebElement emailAddress;

	@FindBy(xpath = "//span[@class='cart-count']")
	WebElement qtyDisplayed;

	@FindBy(id = "terms")
	WebElement terms;

	@FindBy(id = "place_order")
	WebElement placeOrder;

	// Initializing the Page Objects
	public CheckoutDetailsPage() {
		PageFactory.initElements(driver, this);
	}

	// This method is used to enter the billing details
	public void enterBillingDetails(String testCase, String fName, String lName, String streetAdd, String twn,
			String pin, String phn, String email) throws InterruptedException {

		Thread.sleep(6000);
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("billing_first_name")));
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		streetAddress.sendKeys(streetAdd);

		Actions actions2 = new Actions(driver);
		actions2.moveToElement(town);
		actions2.perform();

		town.sendKeys(twn);
		pinCode.sendKeys(pin);
		phone.sendKeys(phn);
		emailAddress.sendKeys(email);
	}

	public void agreeTermsAndConditions() {
		try {
			terms.click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OrderConfirmationPage placeOrder() {
		try {
			placeOrder.click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new OrderConfirmationPage();
	}

}
