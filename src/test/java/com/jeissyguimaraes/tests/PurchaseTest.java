package com.jeissyguimaraes.tests;

import com.jeissyguimaraes.models.User;
import com.jeissyguimaraes.ui.pages.CartPage;
import com.jeissyguimaraes.ui.pages.CheckoutPage;
import com.jeissyguimaraes.ui.pages.LoginPage;
import com.jeissyguimaraes.ui.pages.ProductsPage;
import com.jeissyguimaraes.utils.DataLoader;
import com.jeissyguimaraes.utils.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseTest extends BaseTest {

    @Test
    public void testCompletePurchase() {
        
        DataLoader loginDataLoader = new DataLoader("login_data.json");
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        User user = DataGenerator.generateUser();
        String expectedOrderConfirmationMessage = "THANK YOU FOR YOUR ORDER";

        loginPage.login(loginDataLoader.getProperty("username"), loginDataLoader.getProperty("password"));
        productsPage.addProductToCart();
        cartPage.clickCheckout();
        checkoutPage.finishPurchase(user.getFirstName(), user.getLastName(), user.getPostalCode());

        String actualOrderConfirmationMessage = checkoutPage.getOrderConfirmationMessage();
        Assert.assertEquals(actualOrderConfirmationMessage, expectedOrderConfirmationMessage, "Order confirmation message does not match");
    }
}
