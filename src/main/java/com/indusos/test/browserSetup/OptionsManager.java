package com.indusos.test.browserSetup;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

/**
 * Created by Rahul Rana on 10-Mar-18.
 */
class OptionsManager {

    //Get Chrome Options
    ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("allow-running-insecure-content");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-notifications");
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability(ChromeOptions.CAPABILITY, options);
        return options;
    }

    //Get Firefox Options
    FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("moz:firefoxOptions", options);
        options.setCapability("dom.file.createInChild", options);
        options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WIN10);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, true);
        return options;
    }


}
