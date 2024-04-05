package com.maxi.tests.ecommerce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.maxi.helpers.Calculation;
import com.maxi.helpers.Capitalization;
import com.maxi.helpers.Formatters;
import com.maxi.pages.android.CartPage;
import com.maxi.pages.android.ProductCataloguePage;
import com.maxi.utils.BaseTest;

public class EcommerceTest extends BaseTest {

    // String name, String gender, String country
    @Test(dataProvider = "getData")
    public void fillForm(HashMap<String, String> input) throws InterruptedException {
        formPage.setNameField(input.get("name"));
        formPage.setGender(Capitalization.firstCharUpperCase(input.get("gender")));
        formPage.setCountry(Capitalization.firstCharUpperCase(input.get("country")));

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

    @DataProvider
    public Object[][] getData() throws IOException {
        // return new Object[][] { { "maxi", "male", "argentina" }, { "maximus", "male",
        // "argentina" } };

        List<HashMap<String, String>> data = getJsonData(
                System.getProperty("user.dir") + "//src//test//java//com//maxi//resources//test-data//eCommerce.json");

        return new Object[][] { { data.get(0) }, { data.get(1) } };

    }
}
