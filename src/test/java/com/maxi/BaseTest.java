package com.maxi;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.google.common.collect.ImmutableMap;
import com.maxi.pages.android.FormPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass
    public void configureAppium() {
        // code to start server
        // AndroidDriver , IOSDriver
        // Appium code - > Appium Server -> Mobile

        String initPath = "C://Users//maxis//AppData//Roaming//npm//node_modules//appium//build//lib//main.js";
        String host = "127.0.0.1"; // in this case is not require http:// 'withIPAddress'
        int port = 4723;
        // String apkPath =
        // "C://Source//appium-java//src//test//java//com//appium//resources//ApiDemos-debug.apk";
        String apkPath = "C://Source//appium-java//src//test//java//com//appium//resources//General-Store.apk";

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(initPath))
                .withIPAddress(host)
                .usingPort(port)
                .build();

        try {
            service.start();

            UiAutomator2Options options = new UiAutomator2Options();

            options.setChromedriverExecutable("C://Drivers//Chrome//chromedriver.exe");
            options.setDeviceName("Pixel 2 XL API 34"); // emulator
            // options.setDeviceName("Xiaomi 2310FPCA4G"); // device
            options.setApp(apkPath);

            driver = new AndroidDriver(new URI("http://" + host + ":" + port).toURL(), options);

            // Set the implicit wait limit for an action with an element
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            formPage = new FormPage(driver);

        } catch (MalformedURLException | URISyntaxException e) {

            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        service.stop();
    }

    @BeforeMethod
    public void preSetup() {
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
                "com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
    }

}