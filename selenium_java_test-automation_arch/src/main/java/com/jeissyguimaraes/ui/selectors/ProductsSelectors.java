package com.jeissyguimaraes.ui.selectors;

import org.openqa.selenium.By;

public class ProductsSelectors {
    public static final By ADD_TO_CART_BUTTON = By.cssSelector(".inventory_item:first-of-type .btn_inventory");
    public static final By CART_BUTTON = By.className("shopping_cart_link");
}
