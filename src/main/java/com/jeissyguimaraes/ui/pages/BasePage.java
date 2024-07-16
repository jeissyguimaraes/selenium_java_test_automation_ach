package com.jeissyguimaraes.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.jeissyguimaraes.utils.ConfigLoader;


public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ConfigLoader configLoader;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.configLoader = new ConfigLoader();

        // Read the explicit wait timeout from config.properties
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(configLoader.getProperty("default.explicit.wait")))); 
        PageFactory.initElements(driver, this);
    }

    // Method to wait until the element is visible
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Method to wait until the element is clickable
    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

     // Method to scroll to an element
    protected void scrollToElement(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Common page manipulation methods can be added here
}
