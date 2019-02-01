package com.automation.tutby.tests;

import com.automation.tutby.pages.FinancePage;
import com.automation.tutby.pages.Homepage;
import com.automation.tutby.pages.SearchResultsPage;
import io.qameta.allure.Feature;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.automation.tutby.data.DataConstants.*;
import static org.testng.Assert.assertTrue;

public class MainSearchTest extends TestBase{

    private String invalidKey = RandomStringUtils.random(15, true, true);

    Homepage homepage;
    FinancePage financePage;
    SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void prepareForTest(){
        homepage = new Homepage(driver);
    }

    @Feature("Search")
    @Feature("Homepage")
    @Test(description = "Test main Search from homepage - tut.by")
    public void testSearchHomepage(){

        boolean isSearchCorrect =
                homepage.makeSearchFor(KW_FOR_HOMEPAGE_SEARCH)
                        .allResultsContainKeywords(new ArrayList<>(Arrays.asList(KW_FOR_HOMEPAGE_SEARCH.split(" "))));

        assertTrue(isSearchCorrect, "Search results don't correspond to search keywords");
    }

    @Feature("Search")
    @Feature("Resource Page")
    @Feature("Finance")
    @Test(description = "Test main Search from Resource page - finance.tut.by")
    public void testSearchResourcePage(){
        homepage.openLinkFromTopMenu(RESOURCE_NAME_FINANCE);
        financePage = new FinancePage(driver);
        financePage.chooseSearchType(SEARCH_TYPE_INTERNET);


        boolean isSearchCorrect =
                financePage.makeSearchFor(KW_FOR_RESOURCE_PAGE_SEARCH)
                           .allResultsContainKeywords(new ArrayList<>(Arrays.asList(KW_FOR_RESOURCE_PAGE_SEARCH.split(" "))));

        assertTrue(isSearchCorrect, "Search results don't correspond to search keywords");
    }

    @Feature("Search")
    @Feature("Resource Page")
    @Feature("Finance")
    @Test(description = "Test main Search from Resource page - finance.tut.by - no results")
    public void testSearchResourcePageNoResult(){
        homepage.openLinkFromTopMenu(RESOURCE_NAME_FINANCE);
        financePage = new FinancePage(driver);
        searchResultsPage = financePage.makeSearchFor(invalidKey);
        assertTrue(searchResultsPage.isSearchEmpty(), "Search results are not empty for keyword: " + invalidKey);
    }

    @Override
    public void openTargetPage() {
        driver.get(baseUrl);
    }
}
