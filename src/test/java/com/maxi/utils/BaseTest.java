package com.maxi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.maxi.pages.android.FormPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "//src//main//java//com//maxi//resources//data.properties");

        prop.load(fis);
        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
                : prop.getProperty("ipAddress");
                
        int port = Integer.parseInt(prop.getProperty("port"));
        String androidDeviceName = prop.getProperty("androidDeviceName");

        // String apkPath =
        // "C://Source//appium-java-automation//src//test//java//com//maxi//resources//apps//ApiDemos-debug.apk";
        String apkPath = "C://Source//appium-java-automation//src//test//java//com//maxi//resources//apps//General-Store.apk";

        // code to start server
        service = startAppiumServer(ipAddress, port);

        UiAutomator2Options options = new UiAutomator2Options();

        options.setChromedriverExecutable("C://Drivers//Chrome//chromedriver.exe");
        options.setDeviceName(androidDeviceName); // emulator
        // options.setDeviceName("Xiaomi 2310FPCA4G"); // device
        options.setApp(apkPath);

        driver = new AndroidDriver(service.getUrl(), options);

        // Set the implicit wait limit for an action with an element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        formPage = new FormPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        service.stop();
    }

    @AfterMethod(alwaysRun = true)
    public void preSetup() {
        String packageName = ((AndroidDriver) driver).getCurrentPackage();
        driver.terminateApp(packageName);
        driver.activateApp(packageName);
    }
}