package com.automation.tutby.pages;

import com.automation.tutby.components.ProductFilter;
import com.automation.tutby.pages.abstractPages.ResourcePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KupiTutProductListPage extends ResourcePage {

    public KupiTutProductListPage(WebDriver driver) {
        super(driver);
    }

    private ProductFilter filter = new ProductFilter(driver);

    @FindBy(css = "ul.brc.brc_blue")
    protected WebElement breadcrumbs;

    @FindBy(css = "ul.brc.brc_blue > li.last")
    protected WebElement breadcrumbsLast;

    @FindBy(css = "div.popular")
    private WebElement popularBlock;

    @FindBy(css = "#currency_converter")
    private WebElement currencyConverter;

    @FindBy(css = "a.convert_link")
    private WebElement closeConverterBtn;

    @FindBy(css = ".itemList_item h3")
    List<WebElement> productTitles;

    @FindBy(css = ".itemList_item .dscr")
    List<WebElement> productDescriptions;


    public String getBreadcrumbsLast() {
        return breadcrumbsLast.getText();
    }

    public String getProductTitle(int index){
        return productTitles.get(index).getText().toLowerCase();
    }

    @Step("Set a filter")
    public KupiTutProductListPage setFilter(String filterName, List<String> options){
        logger.info("Set filter " + filterName + " to options: " + options.stream().collect(Collectors.joining(", ")));
         filter.setFilter(filterName, options);
        return this;
    }

    @Step("Apply all filters that have been set")
    public KupiTutProductListPage applyFilters(){
        logger.info("Applying selected filter options");
        filter.clickSubmitFilterBtn();
        return this;
    }

    @Step("Close Currency Converter")
    public KupiTutProductListPage closeConverter(){
        if (currencyConverter.isDisplayed()){
            logger.info("Closing currency converter");
            closeConverterBtn.click();
        }
        return this;
    }

    @Step("Open product from product list by index")
    public KupiTutProductPage clickProductByIndex(int index){
        logger.info("Clicking product from list by index: " + index);
        productTitles.get(index).click();
        return new KupiTutProductPage(driver);
    }

    // check filters that are applied to all products in list
    public boolean areAllFiltersApplied(List<String> filters){
        boolean areAllApplied = false;
        List<String> prodTitles = productTitles.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> prodDescr = productDescriptions.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> mergedProductInfo = new ArrayList<>();
        String filtersAsText = filters.stream().collect(Collectors.joining(", "));

        for(int i=0; i<prodDescr.size(); i++){
            mergedProductInfo.add(prodTitles.get(i).toLowerCase().concat(prodDescr.get(i).toLowerCase()));
        }

        filters.replaceAll(String::toLowerCase);

        areAllApplied = mergedProductInfo.stream().parallel()
                .allMatch(str -> {return filters.stream().allMatch(str::contains);});

        if (areAllApplied == true) {
            logger.info("All filters are applied: " + filtersAsText);
        } else logger.info("Not all the filters applied!");
        return areAllApplied;
    }





}
