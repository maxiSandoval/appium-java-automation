package com.maxi.tests.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maxi.helpers.Calculation;
import com.maxi.helpers.Capitalization;
import com.maxi.helpers.Formatters;
import com.maxi.pages.android.CartPage;
import com.maxi.pages.android.ProductCataloguePage;
import com.maxi.utils.BaseTest;

public class EcommerceTest2 extends BaseTest {

    @Test
    public void fillForm() throws InterruptedException {
        formPage.setNameField("maxi");
        formPage.setGender(Capitalization.firstCharUpperCase("feMale"));
        formPage.setCountry(Capitalization.firstCharUpperCase("Argentina"));

        ProductCataloguePage productCataloguePage = formPage.clickLogin();
        productCataloguePage.addItemToCartByIndex(0);
        productCataloguePage.addItemToCartByIndex(0);

        CartPage cartPage = productCataloguePage.goToCartPage();
        Assert.assertTrue(cartPage.isAtCartPage(), "Is not landing in cart page");
        cartPage.longPressTerms();
        Assert.assertEquals(cartPage.getTermsTitle(), "Terms Of Conditions");
        cartPage.closeClick();
        cartPage.sendEmailClick();

        List<Double> priceList = new ArrayList<>();

        for (String prices : cartPage.getPrices()) {
            priceList.add(Formatters.getFormattedAmount(prices));
        }

        double totalByProducts = Calculation.totalPriceCart(priceList);
        double totalPrice = Formatters.getFormattedAmount(cartPage.getTotalPrice());

        Assert.assertTrue(totalByProducts == totalPrice, "The operation is failed: Total by products: ("
                + totalByProducts + "), total price: (" + totalPrice + ") are differents");
    }

    @Test(groups= {"Smoke"})
    public void errorValidation() throws InterruptedException {

        formPage.setGender(Capitalization.firstCharUpperCase("male"));
        formPage.setCountry(Capitalization.firstCharUpperCase("argentina"));
        formPage.clickLogin();
        Assert.assertEquals(formPage.getErrorMessage(), "Please enter your name");
    }
}
