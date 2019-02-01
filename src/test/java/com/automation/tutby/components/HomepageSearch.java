package com.automation.tutby.components;

import com.automation.tutby.pages.SearchResultsPage;
import com.automation.tutby.pages.abstractPages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomepageSearch extends Page implements Search{

    public HomepageSearch(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="input#search_from_str")
    private WebElement searchInput;

    @FindBy(css="input[name='search'][type='submit']")
    private WebElement submitBtn;

    @Override
    public SearchResultsPage searchFor(String keyPhrase) {
        searchInput.clear();
        searchInput.sendKeys(keyPhrase);
        submitBtn.click();
        return new SearchResultsPage(driver);
    }

    @Override
    public void insertSearchPhrase(String keyPhrase) {
        searchInput.sendKeys(Keys.CONTROL, "a");
        searchInput.sendKeys(keyPhrase);
    }

    @Override
    public Page clickSubmitBtn() {
        submitBtn.click();
        return this;
    }

    @Override
    public void clearSearchField() {
        searchInput.clear();
    }

    @Override
    public String getPlaceholder() {
        return searchInput.getAttribute("placeholder");
    }
}
