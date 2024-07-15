package com.jeissyguimaraes.ui.selectors;

import org.openqa.selenium.By;

public class CheckoutSelectors {
    public static final By FIRST_NAME_FIELD = By.id("first-name");
    public static final By LAST_NAME_FIELD = By.id("last-name");
    public static final By POSTAL_CODE_FIELD = By.id("postal-code");
    public static final By CONTINUE_BUTTON = By.cssSelector(".btn_primary");
    public static final By FINISH_BUTTON = By.cssSelector(".btn_action");
    public static final By ITEM_TOTAL = By.cssSelector(".summary_subtotal_label");
    public static final By TAX = By.cssSelector(".summary_tax_label");
    public static final By TOTAL = By.cssSelector(".summary_total_label");
    public static final By ORDER_CONFIRMATION_MESSAGE = By.cssSelector(".complete-header");

}
