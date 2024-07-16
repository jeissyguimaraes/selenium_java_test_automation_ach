package com.jeissyguimaraes.ui.pages;

import com.jeissyguimaraes.ui.selectors.CartSelectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Method to click the checkout button
    public void clickCheckout() {
        WebElement checkoutButton = waitForClickability(CartSelectors.CHECKOUT_BUTTON);
        checkoutButton.click();
    }
}
