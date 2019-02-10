package com.indusos.test.pages;

import com.aventstack.extentreports.Status;
import com.indusos.test.base.PageBase;
import com.indusos.test.browserSetup.TLDriver;
import com.indusos.test.utilities.ExtentUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetails_Page extends PageBase {

    @FindBy(css = "#buyBackAccordionRow > div > div[role='radio'] i")
    private WebElement radioButton_With_Exchange;
    @FindBy(css = "#newAccordionRow > div > div[role='radio'] i")
    private WebElement radioButton_Without_Exchange;
    @FindBy(id = "buyBackDropDown1Id")
    private WebElement button_BuyNow;
    @FindBy(id = "add-to-cart-button")
    private WebElement button_Add_To_Cart;
    @FindBy(css = "div.nav-logo-base.nav-sprite")
    private WebElement footer;


    public ProductDetails_Page switch_To_ProductDetails_Page(String partOfUrl) {
        switchTabsUsingPartOfUrl(partOfUrl);
        return this;
    }


    public ProductDetails_Page wait_For_ProductDetails_PageLoad(String pageTitle) {
        ExtentUtil.fetchTest().log(Status.INFO, "Wait for product details page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.titleContains(pageTitle),
                ExpectedConditions.visibilityOf(radioButton_Without_Exchange),
                ExpectedConditions.visibilityOf(footer)));
        return this;
    }


    public ProductDetails_Page click_With_Exchange_RadioButton() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking with exchange radio button");
        radioButton_With_Exchange.click();
        if (!button_BuyNow.isDisplayed())
            waitForSync(2);
        return this;
    }


    public ProductDetails_Page click_Without_Exchange_RadioButton() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking with exchange radio button");
        radioButton_Without_Exchange.click();
        if (!button_Add_To_Cart.isDisplayed())
            waitForSync(2);
        return this;
    }


    public ProductDetails_Page click_Add_To_Cart_Button() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking add to cart button");
        click(button_Add_To_Cart);
        return this;
    }


}

