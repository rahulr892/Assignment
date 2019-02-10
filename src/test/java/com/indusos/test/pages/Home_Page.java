package com.indusos.test.pages;

import com.aventstack.extentreports.Status;
import com.indusos.test.base.PageBase;
import com.indusos.test.browserSetup.TLDriver;
import com.indusos.test.utilities.ExtentUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home_Page extends PageBase {

    @FindBy(css = "#nav-link-yourAccount")
    private WebElement link_SignIn;
    @FindBy(id = "twotabsearchtextbox")
    private WebElement input_SearchBox;
    @FindBy(css = "input.nav-input[type='submit']")
    private WebElement button_Search;
    @FindBy(css = "a#nav-cart")
    private WebElement link_GoToCart;

    public Home_Page wait_For_HomePage() {
        ExtentUtil.fetchTest().log(Status.INFO, "Wait for home page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(input_SearchBox),
                ExpectedConditions.visibilityOf(link_GoToCart)));
        return this;
    }


    public Home_Page type_In_SearchBox(String searchText) {
        ExtentUtil.fetchTest().log(Status.INFO, "Type in search box");
        type(input_SearchBox, searchText);
        return this;
    }

    public Home_Page click_SignIn_Link() {
        ExtentUtil.fetchTest().log(Status.INFO, "Click sign in link");
        click(link_SignIn);
        return this;
    }

    public Home_Page click_SearchButton() {
        ExtentUtil.fetchTest().log(Status.INFO, "Click search button");
        click(button_Search);
        return this;
    }


}

