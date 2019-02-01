package com.automation.tutby.pages.abstractPages;

import com.automation.tutby.components.ResourcePageSearch;
import com.automation.tutby.pages.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class ResourcePage extends PageWithTopMenu {

    public ResourcePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a.name-resource")
    private WebElement resourceName;


    public String getH1Title(){
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public String getResourceName(){
        return resourceName.getText();
    }

}
