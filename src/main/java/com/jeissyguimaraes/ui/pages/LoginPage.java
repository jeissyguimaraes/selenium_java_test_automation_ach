package com.jeissyguimaraes.ui.pages;

import com.jeissyguimaraes.ui.selectors.LoginSelectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Method to enter username
    public void enterUsername(String username) {
        WebElement usernameField = waitForVisibility(LoginSelectors.USERNAME_FIELD);
        usernameField.sendKeys(username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        WebElement passwordField = waitForVisibility(LoginSelectors.PASSWORD_FIELD);
        passwordField.sendKeys(password);
    }

    // Method to click the login button
    public void clickLoginButton() {
        WebElement loginButton = waitForClickability(LoginSelectors.LOGIN_BUTTON);
        loginButton.click();
    }

    // Method to perform login
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}