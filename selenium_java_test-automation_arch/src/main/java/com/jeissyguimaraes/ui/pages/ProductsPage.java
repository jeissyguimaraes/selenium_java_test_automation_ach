package com.jeissyguimaraes.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.jeissyguimaraes.ui.selectors.ProductsSelectors;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // Method to add a product to the cart
    public void addFirstProductToCart() {
        WebElement addToCartButton = waitForClickability(ProductsSelectors.ADD_TO_CART_BUTTON);
        addToCartButton.click();
    }

    // Method to open the cart
    public void goToCart() {
        WebElement cartButton = waitForClickability(ProductsSelectors.CART_BUTTON);
        cartButton.click();
    }

    public void addProductToCart() {
        addFirstProductToCart();
        goToCart();
    }
}
