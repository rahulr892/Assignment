package com.indusos.test.pages;

import com.aventstack.extentreports.Status;
import com.indusos.test.base.PageBase;
import com.indusos.test.browserSetup.TLDriver;
import com.indusos.test.utilities.ExtentUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Search_Page extends PageBase {

    @FindBy(css = ".s-access-detail-page")
    private List<WebElement> link_productList;
    @FindBy(css = "a#nav-cart")
    private WebElement link_GoToCart;


    public Search_Page wait_For_Search_PageLoad() {
        ExtentUtil.fetchTest().log(Status.INFO, "Wait for search page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElements(link_productList),
                ExpectedConditions.visibilityOf(link_GoToCart)));
        return this;
    }


    public Search_Page click_Product_Link(String productName) throws Exception {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking " + productName + " product link");
        WebElement element = getExactMatchingTextElementFromList(link_productList, productName);
        clickByJavaScript(element);
        waitForExpectedNumberOfWindows(2, 20);
        return this;
    }


}

