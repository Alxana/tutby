package com.automation.tutby.pages;

import com.automation.tutby.components.ResourcePageSearch;
import com.automation.tutby.pages.abstractPages.ResourcePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class FinancePage extends ResourcePage {

    public FinancePage(WebDriver driver) {
        super(driver);
    }

    private ResourcePageSearch search = new ResourcePageSearch(driver);

    @Step("Starting search via main search on Finance page")
    public SearchResultsPage makeSearchFor(String keywords){
        logger.info("Searching for: " + keywords);
        search.searchFor(keywords);
        return new SearchResultsPage(driver);
    }

    @Step("Choosing search type for search on Finance page")
    public FinancePage chooseSearchType(String type){
        logger.info("Choose search type: " + type);
        search.selectResourceType(type);
        return this;
    }
}
