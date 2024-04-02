package com.maxi.pages.android;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.maxi.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

    AndroidDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement barTitle;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
    private WebElement alertTitle;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement closeBtn;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement sendEmailCheck;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPricesList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalPrice;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public boolean isAtCartPage() {
        wait.until(ExpectedConditions.attributeContains(barTitle, "text", "Cart"));
        return barTitle.isDisplayed();
    }

    public void longPressTerms() {
        longPressAction(termsBtn);
    }

    public String getTermsTitle() {
        wait.until(ExpectedConditions.visibilityOf(alertTitle));
        return alertTitle.getText();
    }

    public void closeClick() {
        closeBtn.click();
    }

    public void sendEmailClick() {
        sendEmailCheck.click();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public List<String> getPrices() {

        List<String> pricesList = new ArrayList<String>();

        for (WebElement productPrices : productPricesList) {
            pricesList.add(productPrices.getText());
        }

        return pricesList;
    }
}
