package com.shop.demoqa.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shop.demoqa.base.ShopDemoQaBase;

public class HomePage extends ShopDemoQaBase {

	// These are Page factory
	@FindBy(xpath = "//a//i[@class='icon_search']")
	WebElement searchButton;

	@FindBy(xpath = "//input[@name='s']")
	WebElement enterItemToSearch;

	@FindBy(xpath = "//input[@name='s']")
	WebElement enterHitEnter;

	// Initializing the Page Objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	// Verifying the Title of the Page

	public String ValidateHomePageTitle() {
		return driver.getTitle();
	}

	// Click on Search button
	public void clickOnSearchIcon() {
		try {
			searchButton.click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Enter the name of the item
	public void enterItemForSearch(String item) {
		try {
			enterItemToSearch.sendKeys(item);
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Click on enter key after entering the item
	public TokyoTalkiesPage tapOnEnter() {

		try {
			enterHitEnter.sendKeys(Keys.ENTER);
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new TokyoTalkiesPage();
	}
}
