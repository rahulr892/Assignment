package com.indusos.test.testscripts;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.indusos.test.utilities.ExtentUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;

public class NewsAPITest {

    @BeforeClass
    void setUp() {
        String time = new SimpleDateFormat("@dd_MM_yyyy@HH_mm_ss").format(new Date());
        ExtentUtil.createReporter("./Reports/API/report" + time + ".html");
    }

    @AfterMethod
    void tearDown(ITestResult iTestResult) {
        if (iTestResult.getStatus() == ITestResult.SUCCESS) {
            ExtentUtil.fetchTest().log(Status.PASS, MarkupHelper.createLabel("TEST PASSED", ExtentColor.GREEN));

        } else if (iTestResult.getStatus() == ITestResult.FAILURE) {
            ExtentUtil.fetchTest().log(Status.INFO, iTestResult.getThrowable());
            ExtentUtil.fetchTest().log(Status.FAIL, MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));

        } else if (iTestResult.getStatus() == ITestResult.SKIP) {
            ExtentUtil.fetchTest().log(Status.INFO, iTestResult.getThrowable());
            ExtentUtil.fetchTest().log(Status.SKIP, MarkupHelper.createLabel("TEST SKIPPED", ExtentColor.ORANGE));
        }
        ExtentUtil.saveReporter();
    }

    // NOTE: API Response has 20 parent nodes only and if iterating over 30 then it
    // throws ArrayIndexOutOfBoundException

    @DataProvider
    public static Object[][] testData() {
        return new Object[][]{
                {"Indian Cricket Team", 21},
                {"Trump", 21}};
    }


    @Test(dataProvider = "testData")
    public void top_Headlines_API_Test(String keywordToBeSearched, int rank) {
        ExtentUtil.createTest("top_Headlines_API_Test");

        ExtentUtil.fetchTest().log(Status.INFO, "KeywordToBeSearched: " + keywordToBeSearched);
        ExtentUtil.fetchTest().log(Status.INFO, "Rank: " + rank);

        Response response = given().param("country", "in")
                .param("apiKey", "e05d846ab01147b5877ae3ebe4f66257")
                .get("https://newsapi.org/v2/top-headlines");

        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> titles = jsonPathEvaluator.get("articles.title");
        List<String> descriptions = jsonPathEvaluator.get("articles.description");

        List<String> topTitles = titles.subList(0, rank - 1);
        List<String> topDescriptions = descriptions.subList(0, rank - 1);

        boolean titleMatch = false;
        for (String s : topTitles) {
            if (s.contains(keywordToBeSearched)) {
                titleMatch = true;
                break;
            }
        }
        if (titleMatch)
            System.out.println(keywordToBeSearched + " found in title inside rank " + rank);
        else
            Assert.fail(keywordToBeSearched + " not found in response");

        boolean descriptionMatch = false;
        for (String s : topDescriptions) {
            if (s.contains(keywordToBeSearched)) {
                descriptionMatch = true;
                break;
            }
        }
        if (descriptionMatch)
            System.out.println(keywordToBeSearched + " found in description inside rank " + rank);
        else
            Assert.fail(keywordToBeSearched + " not found in response");

    }

}
