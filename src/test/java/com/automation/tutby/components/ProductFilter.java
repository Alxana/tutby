package com.automation.tutby.components;

import com.automation.tutby.pages.abstractPages.Page;
import com.automation.tutby.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;

public class ProductFilter extends Page {

    @FindBy(css =".b-widget.widget_filter")
    private WebElement filter;

    @FindBy(css = "#toggleBrand")
    private WebElement allBrandsLink;

    @FindBy(css = "ul#brands > li")
    private List<WebElement> brands;

    @FindBy(css = "ul.param_list.m-color_list > li")
    private List<WebElement> colors;

    @FindBy(css = "div.send > input[type=submit]")
    private WebElement submitFilterBtn;

    public void setFilter(String filterName, List<String> options){
        wait.until(ExpectedConditions.visibilityOf(filter));

        switch (filterName) {
            case "Производители": {
                allBrandsLink.click();

                Stream<WebElement> selectedBrands = brands.stream().filter(el -> {
                    return options.stream().parallel().anyMatch(el.findElement(By.cssSelector("label")).getText()::contains);
                });

                selectedBrands.forEach(el -> el.click());
                break;
            }

            case "Цвет": {
                colors.stream().forEach(el -> {
                    if(options.contains(el.getText())){
                        el.click();
                    }
                 });
                break;
            }

            //any filter with dropdown to select from
            case "select": {
                WebElement label = driver.findElement(By.xpath(format("//div[@class='hdr']/a/span/label[text()='%s']", options.get(0))));
                label.click();
                WebElement dd = label.findElement(By.xpath("ancestor::div/following-sibling::div[@class='cnt']//div[@class='btn-group bootstrap-select show-tick multiple-select']"));
                ElementUtils.scrollIntoViewByJS(driver, dd);
                dd.click();
                List<WebElement> optionsToSelectFrom = label.findElements(By.xpath("ancestor::div/following-sibling::div[@class='cnt']//div[@class='dropdown-menu open']/ul/li"));

                Stream<WebElement> selectedOptions = optionsToSelectFrom.stream().filter(el -> {
                    return options.stream().skip(1).parallel().anyMatch(el.getText()::contains);
                });

                selectedOptions.forEach(el -> el.click());
                break;
            }

            // any single-row filters, contain only checkbox and filter option
            case "single-row": {
                driver.findElement(By.xpath(format("//div[contains(@class, 'cnt-mod')]/label[text()='%s']", options.get(0)))).click();
                break;
            }
        }
    }

   public void clickSubmitFilterBtn(){
        submitFilterBtn.click();
    }

    public ProductFilter(WebDriver driver) {
        super(driver);
    }
}
