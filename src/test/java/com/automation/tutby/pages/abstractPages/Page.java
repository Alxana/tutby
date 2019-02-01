package com.automation.tutby.pages.abstractPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public  abstract class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        this.logger = LoggerFactory.getLogger(this.getClass());
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     //   driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public String getTitle() {
        return driver.getTitle();
    }

}