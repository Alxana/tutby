package com.automation.tutby.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

    private static WebDriverWait wait;
    private static final int DEFAULT_EXPLICIT_WAIT = 10;

    public static boolean isElementClickable(WebElement webElement) {
        return webElement.isDisplayed() && webElement.isEnabled();
    }

    public static boolean isElementTextEquals(WebElement webElement, String text){
        return webElement.getText().equalsIgnoreCase(text);
    }

    public static void waitForVisible(WebDriver driver, WebElement webElement, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void scrollIntoViewByJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
