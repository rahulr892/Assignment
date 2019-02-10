package com.indusos.test.pages;

import com.aventstack.extentreports.Status;
import com.indusos.test.base.PageBase;
import com.indusos.test.browserSetup.TLDriver;
import com.indusos.test.utilities.ExtentUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Cart_Page extends PageBase {

    @FindBy(css = "h1.a-size-medium")
    private WebElement text_Added_To_cart;
    @FindBy(id = "hlb-view-cart-announce")
    private WebElement button_Cart;
    @FindBy(css = ".sc-product-title")
    private List<WebElement> shoppingCartList;
    @FindBy(css = "input[value='Delete']")
    private WebElement button_Delete_Item;


    public Cart_Page wait_For_Cart_PageLoad(String title) {
        ExtentUtil.fetchTest().log(Status.INFO, "Wait for cart page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.titleIs(title),
                ExpectedConditions.visibilityOf(button_Cart)));
        return this;
    }


    public Cart_Page validate_Product_Added_To_Cart() {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating product added to cart");
        Assert.assertEquals(getText(text_Added_To_cart), "Added to Cart");
        return this;
    }


    public Cart_Page click_Cart_Button() {
        click(button_Cart);
        return this;
    }


    public Cart_Page wait_For_Cart2_PageLoad(String title) {
        ExtentUtil.fetchTest().log(Status.INFO, "Wait for cart2 page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.titleIs(title),
                ExpectedConditions.visibilityOfAllElements(shoppingCartList)));
        return this;
    }


    public Cart_Page validate_Product_Added_To_Cart2(String productName) {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating product is present in shopping cart list");
        for (WebElement element : shoppingCartList) {
            if (getText(element).equals(productName)) {
                Assert.assertEquals(getText(element), productName, "Product not present in shopping cart");
            }
        }
        click(button_Delete_Item);
        return this;
    }


}

