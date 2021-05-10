package com.shop.demoqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.shop.demoqa.base.ShopDemoQaBase;

public class OrderConfirmationPage extends ShopDemoQaBase {

	@FindBy(xpath = "//p[@class='woocommerce-thankyou-order-received']")
	WebElement ThankyouMessage;

	@FindBy(xpath = "//li[@class='method']//strong")
	WebElement paymentMethod;

	@FindBy(xpath = "//li[@class='order']//strong")
	WebElement orderNumber;

	@FindBy(xpath = "//h2[@class='woocommerce-order-details__title']")
	WebElement orderDetails;

	// Initializing the Page Objects
	public OrderConfirmationPage() {
		PageFactory.initElements(driver, this);
	}

	public void OrderConfirmation() {
		Actions actions2 = new Actions(driver);
		actions2.moveToElement(ThankyouMessage);
		actions2.perform();

		Assert.assertEquals(ThankyouMessage.getText().toString(), "Thank you. Your order has been received.");
		Assert.assertEquals(paymentMethod.getText().toString(), "Cash on delivery");
		Assert.assertTrue(orderNumber.isDisplayed());
		Assert.assertTrue(orderDetails.isDisplayed());
	}
}