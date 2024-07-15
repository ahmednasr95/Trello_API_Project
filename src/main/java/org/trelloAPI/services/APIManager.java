package org.trelloAPI.services;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.trelloAPI.models.boardsAPI.Board;
import org.trelloAPI.models.boardsAPI.POJO.createBoardData;
import org.trelloAPI.models.cardsAPI.Card;
import org.trelloAPI.models.listsAPI.List;

import java.io.IOException;
import java.util.Properties;

import static org.trelloAPI.utils.JSONReader.readJsonFromFile;
import static org.trelloAPI.utils.PROPERTIESReader.getConfigs;

@Listeners(ExtentITestListenerClassAdapter.class)
public class APIManager {
    public static RequestSpecification userReqSpecs;
    public Properties config;
    public createBoardData firstBoardRequestData = readJsonFromFile("src/main/resources/Data/firstBoardSpecs.json"
            , createBoardData.class);
    public createBoardData secondBoardRequestData = readJsonFromFile("src/main/resources/Data/secondBoardSpecs.json"
            , createBoardData.class);
    public Board trelloBoard;
    public Board trelloBoard2;
    public List toTest;
    public List tested;
    public Card task1;

    public APIManager() throws IOException {
    }

    @BeforeClass
    public void initializeReportManager() {
        try
        {
            config = getConfigs("src/main/java/Config.properties");
        }
        catch(IOException e){
            System.out.println("Config File not found!!!");
        }
        ReportManager.initialize(config.getProperty("reportOutputPath"));
    }
    @BeforeMethod
    public void createUserReqSpecs(){
        userReqSpecs = RestAssured.given()
                .baseUri(config.getProperty("baseURL"))
                .basePath(config.getProperty("basePath"))
                .queryParam("key", config.getProperty("APIKey"))
                .queryParam("token", config.getProperty("APIToken"));
    }
    @AfterMethod
    public void logTestResult(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            ReportManager.logMessage(Status.FAIL, STR."Test failed: \{result.getThrowable()}");
        }
    }
    @AfterClass
    public void generateReport(){
        ReportManager.printReport();
    }
}
