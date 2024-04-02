package com.maxi.pages.android;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.maxi.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {

    AndroidDriver driver;

    public FormPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement loginBtn;

    @AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
    private WebElement errorMessage;

    public void setNameField(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender) {
        driver.findElement(By.id("com.androidsample.generalstore:id/radio" + gender )).click();
    }

    public void setCountry(String country) {
        countryBox.click();
        scrollToText(country);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + country + "']")).click();
    }

    public ProductCataloguePage clickLogin() {
        loginBtn.click();
        return new ProductCataloguePage(driver);
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }
}
