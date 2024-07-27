package com.jeissyguimaraes.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverManager {

    public static WebDriver initializeDriver(String browserType, boolean headless) {
        WebDriver driver = null;
        ConfigLoader configLoader = new ConfigLoader();

        switch (browserType.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", configLoader.getProperty("webdriver.chrome.driver"));
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-dev-shm-usage"); // Fixes issues with shared resources in Docker
                chromeOptions.addArguments("--whitelisted-ips");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--disable-notifications");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", configLoader.getProperty("webdriver.firefox.driver"));
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", configLoader.getProperty("webdriver.edge.driver"));
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser " + browserType + " not supported.");
        }
        return driver;
    }

    public static WebDriver initializeDriver() {
        ConfigLoader configLoader = new ConfigLoader();
        String browserType = configLoader.getProperty("webdriver.browser");
        boolean headless = Boolean.parseBoolean(configLoader.getProperty("webdriver.headless"));
        return initializeDriver(browserType, headless);
    }
}
