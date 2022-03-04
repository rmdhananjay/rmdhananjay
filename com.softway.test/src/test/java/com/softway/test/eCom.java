package com.softway.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eCom {
	
	static int FVal;
	static int AVal;
	static WebDriver driver;
	
	@BeforeMethod
	void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\R M DHANANJAY\\\\eclipse-workspace\\\\practice\\\\Drivers\\\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}
	
	@Test
	 void flipkart(){
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		driver.findElement(By.xpath("//input[@class='_3704LK']")).sendKeys("redmi note 8 (Neptune Blue, 32 GB) (3 GB RAM)");
		driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-id='MOBFM2Z4CTYJFWBS']/descendant::div[@class='_30jeq3 _1_WHN1']")));
		FVal=Integer.parseInt((driver.findElement(By.xpath("//div[@data-id='MOBFM2Z4CTYJFWBS']/descendant::div[@class='_30jeq3 _1_WHN1']")).getText()).replaceAll("[^0-9]", ""));
		System.out.println(FVal);
	}
	
	@Test(dependsOnMethods="flipkart")
	void amazon() {
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("redmi note 8 (Neptune Blue, 32 GB) (3 GB RAM)");
		driver.findElement(By.id("nav-search-submit-button")).click();
		AVal=Integer.parseInt((driver.findElement(By.xpath("//span[@data-component-type='s-search-results']/descendant::span[@class='a-price-whole'][1]")).getText()).replaceAll("[^0-9]", ""));
		System.out.println(AVal);
	}
	
	@AfterMethod
	void cleanUp() {
		driver.close();
	}
	
	@AfterClass
	void quiteSession() {
		driver.quit();
	}
	
	@AfterTest
	void getCheaperPrice() {
		if(FVal>AVal)System.out.println("Amazon has a cheaper price");
		else System.out.println("Flipkart has a cheaper price");
	}
}
