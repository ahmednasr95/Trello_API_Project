package org.trelloAPI.services;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.response.Response;
import org.testng.Assert;

public class ReportManager {
    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest test;
    public static void initialize(String reportOutputPath){
        ExtentSparkReporter spark = new ExtentSparkReporter(reportOutputPath);
        extent.attachReporter(spark);
    }
    public static void createTest(String testName){
        test = extent.createTest(testName);
    }
    public static void assertEqualsAndLog(String assertionName, Object expected, Object actual){
        try{
            Assert.assertEquals(actual, expected);
            test.pass(STR."\{assertionName} has passed! Expected: \{expected} | Actual: \{actual}");
        } catch (AssertionError e){
            test.fail(STR."\{assertionName} has failed! Expected: \{expected} | Actual: \{actual}");
            test.fail(e);
        }
    }
    public static void assertTrueAndLog(String assertionName, String expected, boolean actual){
        try{
            Assert.assertTrue(actual, expected);
            test.pass(STR."\{assertionName} has passed! Expected: \{expected} | Actual: \{actual}");
        } catch (AssertionError e){
            test.fail(STR."\{assertionName} has failed! Expected: \{expected} | Actual: \{actual}");
            test.fail(e);
        }
    }
    public static void logMessage(Status status, String message){
        test.log(status,message);
    }

    public static void printReport(){
        extent.flush();
    }
}
