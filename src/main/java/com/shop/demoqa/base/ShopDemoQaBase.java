package com.shop.demoqa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.shop.demoq.util.DataDrivenUtil;

public class ShopDemoQaBase {
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static Logger log = LogManager.getLogger(ShopDemoQaBase.class.getName());

	public ShopDemoQaBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("config.properties");
			prop.load(ip);
			log.info("Located the config file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void initialization() {
		try {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			// wait =new WebDriverWait(driver, 8);
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("initialised the browser");

		driver.manage().window().maximize();
		log.info("selected the browser");

		driver.manage().deleteAllCookies();
		log.info("deleted all cookied");

		driver.manage().timeouts().pageLoadTimeout(DataDrivenUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		log.info("pageLoad timeout applied");

		driver.manage().timeouts().implicitlyWait(DataDrivenUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		log.info("implicit waits applied");

		driver.get(prop.getProperty("url"));
		log.info("loaded the page");
	}

}
