package com.automation.tutby.pages;

import com.automation.tutby.pages.abstractPages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static com.automation.tutby.data.DataConstants.KW_TO_EXCLUDE;

public class SearchResultsPage extends Page {

    @FindBy(css = "li.b-results__li:not(.m-market)")
    private List<WebElement> results;

    @FindBy(css = "div.b-results-empty")
    private WebElement emptySearchList;

    public String getResults(){
        return results.stream().map(WebElement::getText).collect(Collectors.joining("\n\n"));
    }

    // check that search results have all the keywords from search input
    public boolean allResultsContainKeywords(List<String> keywords){
        List<String> resultsText = results
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        resultsText.replaceAll(String::toLowerCase);
        keywords.replaceAll(String::toLowerCase);

        boolean areResultsCorrect =  resultsText.stream()
                .filter(res -> !KW_TO_EXCLUDE.stream().anyMatch(res::equalsIgnoreCase))
                .allMatch(res -> keywords.stream().allMatch(res::contains));

        if(areResultsCorrect == true){
            logger.info("Search results correspond to the keywords: " + keywords.stream().collect(Collectors.joining(", ")));
        } else logger.info("Search results don't correspond to the keywords: " + keywords.stream().collect(Collectors.joining(", ")));

        return areResultsCorrect;
    }

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchEmpty(){
        return emptySearchList.isDisplayed();
    }

}
