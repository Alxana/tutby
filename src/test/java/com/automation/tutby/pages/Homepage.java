package com.automation.tutby.pages;

import com.automation.tutby.components.HomepageSearch;
import com.automation.tutby.pages.abstractPages.PageWithTopMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class Homepage extends PageWithTopMenu {

    public Homepage(WebDriver driver) {
        super(driver);
    }

    private HomepageSearch search = new HomepageSearch(driver);

    @Step("Starting search via main search on Tut.by homepage")
    public SearchResultsPage makeSearchFor(String key){
        logger.info("Searching for: " + key);
        return search.searchFor(key);
    }

}
