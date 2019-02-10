package com.indusos.test.testscripts;

import com.indusos.test.browserSetup.TLDriver;
import com.indusos.test.pages.*;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class AmazonTest {

    /*############################################################
     * ' Function Name: add_To_Cart
     * ' Purpose: Searching and adding a product to cart from list
     */
    @Test(priority = 1, description = "Search & add product to cart")
    public void add_To_Cart(ITestContext context) throws Exception {
        String url = context.getCurrentXmlTest().getParameter("URL");

        String productName = "Redmi Y2 (Rose Gold, 3GB RAM, 32GB Storage)";

        TLDriver.getDriver().get(url);

        Home_Page home_page = new Home_Page();
        Login_Page login_page = new Login_Page();
        Search_Page search_page = new Search_Page();
        ProductDetails_Page productDetails_page = new ProductDetails_Page();
        Cart_Page cartPage = new Cart_Page();

        home_page.wait_For_HomePage()
                .click_SignIn_Link();

        login_page.wait_For_SignIn_PageLoad()
                .type_Email_Or_PhoneNumber("USERNAME")
                .click_Continue_Button()
                .type_Password("PASSWORD")
                .click_Login_Button();

        home_page.wait_For_HomePage()
                .type_In_SearchBox("Redmi Note")
                .click_SearchButton();

        search_page.wait_For_Search_PageLoad()
                .click_Product_Link(productName);

        productDetails_page.switch_To_ProductDetails_Page("Redmi-Rose-Gold-32GB")
                .wait_For_ProductDetails_PageLoad("Redmi Y2")
                .click_With_Exchange_RadioButton()
                .click_Without_Exchange_RadioButton()
                .click_Add_To_Cart_Button();

        cartPage.wait_For_Cart_PageLoad("Amazon.in Shopping Cart")
                .validate_Product_Added_To_Cart()
                .click_Cart_Button()
                .wait_For_Cart2_PageLoad("Amazon.in Shopping Cart")
                .validate_Product_Added_To_Cart2(productName);
    }

}

