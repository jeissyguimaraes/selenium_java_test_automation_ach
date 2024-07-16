package com.jeissyguimaraes.ui.pages;

import com.jeissyguimaraes.ui.selectors.CheckoutSelectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Method to enter first name
    public void enterFirstName(String firstName) {
        WebElement firstNameField = waitForVisibility(CheckoutSelectors.FIRST_NAME_FIELD);
        firstNameField.sendKeys(firstName);
    }

    // Method to enter last name
    public void enterLastName(String lastName) {
        WebElement lastNameField = waitForVisibility(CheckoutSelectors.LAST_NAME_FIELD);
        lastNameField.sendKeys(lastName);
    }

    // Method to enter postal code
    public void enterPostalCode(String postalCode) {
        WebElement postalCodeField = waitForVisibility(CheckoutSelectors.POSTAL_CODE_FIELD);
        postalCodeField.sendKeys(postalCode);
    }

    // Method to click the continue button
    public void clickContinue() {
        WebElement continueButton = waitForClickability(CheckoutSelectors.CONTINUE_BUTTON);
        continueButton.click();
    }

    // Method to click the finish button
    public void clickFinish() {
        WebElement finishButton = waitForClickability(CheckoutSelectors.FINISH_BUTTON);
        finishButton.click();
    }

    // Method to verify the order confirmation message
    public String getOrderConfirmationMessage() {
        WebElement confirmationMessage = waitForVisibility(CheckoutSelectors.ORDER_CONFIRMATION_MESSAGE);
        return confirmationMessage.getText();
    }

    // Method to complete the purchase process
    public void finishPurchase(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
        scrollToElement(CheckoutSelectors.FINISH_BUTTON);
        clickFinish();
    }

  
}
