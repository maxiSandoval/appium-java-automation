package com.maxi.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maxi.BaseTest;
import com.maxi.helper.Calculation;
import com.maxi.helper.Capitalization;
import com.maxi.helper.Formatters;
import com.maxi.pages.android.CartPage;
import com.maxi.pages.android.ProductCataloguePage;

public class EcommerceTest extends BaseTest {

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
}
