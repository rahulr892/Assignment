package com.indusos.test.pages;

import com.aventstack.extentreports.Status;
import com.indusos.test.base.PageBase;
import com.indusos.test.browserSetup.TLDriver;
import com.indusos.test.utilities.ExtentUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_Page extends PageBase {

    @FindBy(id = "ap_email")
    private WebElement input_EmailOrPhone;
    @FindBy(id = "ap_password")
    private WebElement input_Password;
    @FindBy(css = "#continue[type='submit']")
    private WebElement button_Continue;
    @FindBy(id = "signInSubmit")
    private WebElement button_Login;


    public Login_Page wait_For_SignIn_PageLoad() {
        ExtentUtil.fetchTest().log(Status.INFO, "Wait for signin page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(input_EmailOrPhone),
                ExpectedConditions.visibilityOf(button_Continue)));
        return this;
    }


    public Login_Page type_Email_Or_PhoneNumber(String text) {
        ExtentUtil.fetchTest().log(Status.INFO, "Type email: " + text);
        type(input_EmailOrPhone, text);
        return this;
    }


    public Login_Page type_Password(String text) {
        ExtentUtil.fetchTest().log(Status.INFO, "Type password: " + input_Password);
        type(input_Password, text);
        return this;
    }


    public Login_Page click_Continue_Button() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking continue button");
        click(button_Continue);
        return this;
    }


    public Login_Page click_Login_Button() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking login button");
        click(button_Login);
        return this;
    }


}

