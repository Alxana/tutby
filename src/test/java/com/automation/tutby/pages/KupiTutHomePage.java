package com.automation.tutby.pages;

import com.automation.tutby.components.ResourcePageSearch;
import com.automation.tutby.pages.abstractPages.ResourcePage;
import com.automation.tutby.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class KupiTutHomePage extends ResourcePage {

    public KupiTutHomePage(WebDriver driver) {
        super(driver);
    }

    private ResourcePageSearch search = new ResourcePageSearch(driver);

    @FindBy(css = "ul.main_cat_menu.level1 > li")
    List<WebElement> mainCatMenuLevel1Links;


    @Step("Clicking level 1 menu link on kupi.tut.by homepage")
    public KupiTutProductListPage clickLevel1MenuLinkByText(String text){
        for(WebElement link: mainCatMenuLevel1Links){
            if(link.getText().equalsIgnoreCase(text)){
                logger.info("Clicking level1 link: " + text);
                link.click();
                break;
            }
        }
        WaitUtils.forPageLoaded(driver, KupiTutProductListPage.class);
        return new KupiTutProductListPage(driver);
    }

    @Step("Starting search via main search on kupi.tu.by page")
    public SearchResultsPage makeSearchFor(String keywords){
        logger.info("Searching for: " + keywords);
        search.searchFor(keywords);
        return new SearchResultsPage(driver);
    }

}
