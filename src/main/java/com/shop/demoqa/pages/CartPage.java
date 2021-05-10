package com.shop.demoqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.shop.demoqa.base.ShopDemoQaBase;

public class CartPage extends ShopDemoQaBase {

	// These are Page factory
	@FindBy(xpath = "//input[@class='input-text qty text']")
	WebElement qtyDisplayed;

	@FindBy(xpath = "//bdi[text()=200]")
	WebElement totalAmount;

	@FindBy(xpath = "//div[@class='wc-proceed-to-checkout']")
	WebElement proceedToCheckOut;

	// Verifying the order details and quantity
	public void verifyTotalAmountAndQuantity() {
		Assert.assertEquals(totalAmount.getText().toString().substring(1, 4), "200");
		Assert.assertEquals(qtyDisplayed.getAttribute("value"), "2");
	}

	// Initializing the Page Objects
	public CartPage() {
		PageFactory.initElements(driver, this);
	}

	// Clock on proceed to checkout button
	public CheckoutDetailsPage ProceedToCheckOut() throws InterruptedException {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(proceedToCheckOut);
			actions.perform();
			System.out.println();
			proceedToCheckOut.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CheckoutDetailsPage();
	}
}
