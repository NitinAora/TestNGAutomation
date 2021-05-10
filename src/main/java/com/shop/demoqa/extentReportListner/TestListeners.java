package com.shop.demoqa.extentReportListner;

import org.testng.ITestListener;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import com.shop.demoqa.base.ShopDemoQaBase;

public class TestListeners extends ShopDemoQaBase implements ITestListener {
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public void onStart(ITestContext iTestContext) {
		System.out.println("Inside Onstart Method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", this.driver);
	}

	public void onFinish(ITestContext iTestContext) {
		System.out.println("Inside OnFinish Method " + iTestContext.getName());
		ExtentTestManager.endTest();
		ExtentReportMain.getReporter().flush();
	}

	public void onTestStart(ITestResult iTestResult) {
		System.out.println("Test Case Started - method name is : " + getTestMethodName(iTestResult));
	}

	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("Test Case Successfully executed" + getTestMethodName(iTestResult) + " succeed");

		ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		Object testClass = iTestResult.getInstance();
		WebDriver webDriver = ((ShopDemoQaBase) testClass).getDriver();

		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	}

}
