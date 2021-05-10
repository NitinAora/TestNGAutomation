package com.shop.demoq.testcase;

import java.util.ArrayList;

import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.shop.demoq.util.DataDrivenUtil;

import com.shop.demoqa.base.ShopDemoQaBase;
import com.shop.demoqa.extentReportListner.ExtentTestManager;
import com.shop.demoqa.pages.CartPage;
import com.shop.demoqa.pages.CheckoutDetailsPage;
import com.shop.demoqa.pages.HomePage;
import com.shop.demoqa.pages.OrderConfirmationPage;
import com.shop.demoqa.pages.TokyoTalkiesPage;

public class PlaceOrderTest extends ShopDemoQaBase {
	HomePage homePage;
	TokyoTalkiesPage tokyoTalkiesPage;
	CartPage cartPage;
	CheckoutDetailsPage checkoutDetailsPage;
	OrderConfirmationPage orderConfirmationPage;
	//ShopDemoQaUtil shDemoQaUtil;
	
	public static Logger log = LogManager.getLogger(PlaceOrderTest.class.getName());

	public PlaceOrderTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		log.info("Initialized the set up");
		homePage = new HomePage();
	}  

	@DataProvider
	public Object[][] getTestData() throws IOException {
		
		ArrayList<ArrayList<String>> dataTemp = DataDrivenUtil.getDataTemp("EndToEnd");
		if(dataTemp.isEmpty()) { // if nothing matches
			return new Object[0][0];
		}
		Object[][] objArray = new Object[dataTemp.size()][dataTemp.get(0).size()];
		for (int i = 0; i < dataTemp.size(); i++) {
			for (int j = 0; j < dataTemp.get(0).size(); j++) {
				objArray[i][j] = dataTemp.get(i).get(j);
			} 
		}
 
		return objArray;

	}
	
	
	
/*	  @DataProvider public static Object[][] getTestData() throws IOException {
	  Object[][] data = DataDrivenUtil.getData("EndToEnd"); return data;  
	  }
	 */
	
/*	@DataProvider
	public Object[][] getTestData() {
		Object[][] data = new Object[2][7];

		data[0][0] = "Nitin";
		data[0][1] = "Arora";
		data[0][2] = "123 Street Address";
		data[0][3] = "Oakville";
		data[0][4] = "123456";
		data[0][5] = "8787878787";
		data[0][6] = "narora@gmail.com";

		data[1][0] = "Nitin";
		data[1][1] = "Arora";
		data[1][2] = "123 Street Address";
		data[1][3] = "Oakville";
		data[1][4] = "123456";
		data[1][5] = "8787878787";
		data[1][6] = "maeo@gmail.com";
		log.info("Returning the data for billing details");
		return data;

	}*/
	
	/* This test case is an end to end scenario which verify the functionality,
	 when the user navigates to the Shop Demo QA home page
	 and search the item to purchase
	 and then select the quantity and color of the product
	 add the product to the cart and then verify the product added
	 and proceeds to checkout and enter billing details
	 and places the order and confirms the orders*/
	@Test(dataProvider = "getTestData")
	public void placeOrderEndToEndScenario(String testCase, String fName, String lName, String streetAdd, String twn,
			String pin, String phn, String email, String qty, Method method) throws InterruptedException {
		ExtentTest test= ExtentTestManager.startTest(method.getName(), "Invalid Login Scenario with empty username and password.");
		
		String title = homePage.ValidateHomePageTitle();

		Assert.assertEquals(title, "ToolsQA Demo Site – ToolsQA – Demo E-Commerce Site");
		test.log(LogStatus.PASS, "Validated the HomePage Title - Passed" , "These are random details");
		log.info("Validated the HomePage Title");

		homePage.clickOnSearchIcon();
		log.info("Clicked on Search option button");
  
		homePage.enterItemForSearch("Tokyo Talkies");
		log.info("Entered the item to search");

		tokyoTalkiesPage = homePage.tapOnEnter();
		log.info("Entered the enter key to find the search results");

		tokyoTalkiesPage.selectColor();
		log.info("Selected the Color");
		
		tokyoTalkiesPage.selectQuantity(qty);
		log.info("Selected the Quantity");

		tokyoTalkiesPage.addToCart();
		log.info("Added to the Cart");

		cartPage = tokyoTalkiesPage.clickOnViewCart();
		log.info("Clicked on view the cart");

	    cartPage.verifyTotalAmountAndQuantity(qty);
		log.info("Verifed the order details and Quantity");

		checkoutDetailsPage = cartPage.ProceedToCheckOut();
		log.info("Clicked on proceed to checkout");

		checkoutDetailsPage.enterBillingDetails(testCase, fName, lName, streetAdd, twn, pin, phn, email);
		log.info("Entered the Billing Details");

		checkoutDetailsPage.agreeTermsAndConditions();
		log.info("Clicked on Agree terms and conditions");

		orderConfirmationPage = checkoutDetailsPage.placeOrder();
		orderConfirmationPage.OrderConfirmation();
		log.info("Confirmed of the order details");
		
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		log.info("Browser is closed ");
	}

}
