package com.indusos.test.browserSetup;

import com.indusos.test.testData.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static OptionsManager options = new OptionsManager();

    /**
     * Start browser based on input in amazon.xml and used in WebDriverListener
     *
     * @param OS_Browser fetched from xml
     * @return driver instance
     */
    public static WebDriver createInstance(String OS_Browser) {
        WebDriver driver = null;

        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();

        if ((Constants.CHROME).equalsIgnoreCase(OS_Browser)) {
            try {
                driver = new ChromeDriver(options.getChromeOptions());
            } catch (Throwable e) {
                System.out.println("Chrome Launch Failed - " + e);
            }

        } else if ((Constants.FIREFOX).equalsIgnoreCase(OS_Browser)) {
            try {
                driver = new FirefoxDriver(options.getFirefoxOptions());
            } catch (Throwable e) {
                System.out.println("Firefox Launch Failed - " + e);
            }
        } else {
            try {
                driver = new ChromeDriver(options.getChromeOptions());
            } catch (Throwable e) {
                System.out.println("Chrome Launch Failed - " + e);
            }
        }

        if (driver != null) {
            try {
                driver.manage().window().maximize();
            } catch (Exception e) {
                System.out.println("Please check browser value");
                e.printStackTrace();
            }
        }
        return driver;
    }

}

