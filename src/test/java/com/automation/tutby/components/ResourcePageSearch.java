package com.automation.tutby.components;

import com.automation.tutby.pages.SearchResultsPage;
import com.automation.tutby.pages.abstractPages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ResourcePageSearch extends Page implements Search{

    public ResourcePageSearch(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="input[type='text'][name='str']")
    private WebElement searchInput;

    @FindBy(css="div.b-search.extended input.button.big[type='submit']")
    private WebElement submitBtn;

    @FindBy(css = "select#resource_search")
    private WebElement resourceTypeSearch;

    public Page selectResourceType(String resourceType){
        Select resourceSel = new Select(resourceTypeSearch);
        resourceSel.selectByVisibleText(resourceType);
        return this;
    }

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
        return searchInput.getAttribute("value");
    }
}
