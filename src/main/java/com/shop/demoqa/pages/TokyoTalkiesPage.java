package com.shop.demoqa.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.shop.demoqa.base.ShopDemoQaBase;

public class TokyoTalkiesPage extends ShopDemoQaBase {

	@FindBy(xpath = "//Select[@id='color']")
	WebElement colour;

	@FindBy(xpath = "//button[@class='qty-increase']")
	WebElement qty;

	@FindBy(xpath = "//button[text()='Add to cart']")
	WebElement addToCart;

	@FindBy(linkText = "View cart")
	WebElement viewCart;

	public TokyoTalkiesPage() {
		// Initializing the Page Objects
		PageFactory.initElements(driver, this);
	}

	// Verifying the Title of the Page
	public String ValidateTokyoTalkiesPageTitle() {
		return driver.getTitle();
	}

	public void selectColor() {
		Actions actions = new Actions(driver);
		actions.moveToElement(colour);
		actions.perform();

		Select color = new Select(colour);
		color.selectByVisibleText("White");
	}

	public void addToCart() {
		try {
			addToCart.click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectQuantity() {
		try {
			qty.click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CartPage clickOnViewCart() {
		try {
			viewCart.click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CartPage();

	}

}
