package com.jeissyguimaraes.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public static WebDriver initializeDriver(String browserType) {
        WebDriver driver = null;
        ConfigLoader configLoader = new ConfigLoader();
        String driverPath = configLoader.getProperty("webdriver.chrome.driver");

        switch (browserType.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", driverPath);

                // Configure options for ChromeDriver
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--start-maximized");

                driver = new ChromeDriver(options);
                break;
            // Add other browsers here if needed
            default:
                throw new IllegalArgumentException("Browser " + browserType + " not supported.");
        }
        return driver;
    }
}
