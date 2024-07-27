package com.jeissyguimaraes.tests;

import com.aventstack.extentreports.ExtentTest;
import com.jeissyguimaraes.utils.ConfigLoader;
import com.jeissyguimaraes.utils.DriverManager;
import com.jeissyguimaraes.utils.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public abstract class BaseTest {
    
    protected WebDriver driver;
    protected ExtentTest test;
    private ConfigLoader configLoader;

    @BeforeSuite
    public void setupExtent() {
        ExtentReportManager.setup();
        configLoader = new ConfigLoader();
    }

    @BeforeMethod
    public void setUp(ITestResult result) {
        test = ExtentReportManager.createTest(result.getMethod().getMethodName());
        initializeDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        ExtentReportManager.flush();
    }

    private void initializeDriver() {
        if (driver == null) {
            driver = DriverManager.initializeDriver();
            driver.get(configLoader.getProperty("base.url"));
        }
    }
}
