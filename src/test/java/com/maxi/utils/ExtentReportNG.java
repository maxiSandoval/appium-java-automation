package com.maxi.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    static ExtentReports extent;

    public static ExtentReports getReporterObject() {

        // ExtentReports , ExtentSparkReporter
        String path = System.getProperty("user.dir") + "//target//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Maxi");
        
        return extent;
    }
}
