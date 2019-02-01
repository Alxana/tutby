package com.automation.tutby.pages;

import com.automation.tutby.pages.abstractPages.ResourcePage;
import com.automation.tutby.utils.ElementUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class KupiTutProductPage extends ResourcePage {

    public KupiTutProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.b-item-header h1")
    private WebElement productTitle;

    @FindBy(css = "div.item_tabs > ul > li > a")
    private List<WebElement> tabs;

    @FindBy(css = "div.item_tabs > ul > li.active")
    private WebElement activeTab;

    @FindBy(css = "div.wishlist.button")
    private WebElement wishlistBtn;

    @FindBy(css = "div.compare.button")
    private WebElement compareBtn;

    @FindBy(css = "#map")
    private WebElement map;

    @FindBy(css = "div#shops > h2")
    private WebElement tabMapTitle;

    @FindBy(css = "div#offers > h2")
    private WebElement tabPricesTitle;

    @FindBy(css = "table.param_table")
    private WebElement characteristicsTable;

    @FindBy(css = "div#marketWidget-Reviews")
    private WebElement reviewWidget;

    @FindBy(css = "div.gallery")
    private WebElement gallery;

    @FindBy(css = "div.fotorama__arr.fotorama__arr--next")
    private WebElement nextArrayGallery;

    @FindBy(css = "div.fotorama__arr.fotorama__arr--prev")
    private WebElement prevArrayGallery;

    @FindBy(css = ".popup_close")
    private WebElement closeGalleryBtn;

    @FindBy(css = ".fotorama__nav__frame.fotorama__nav__frame--thumb")
    private List<WebElement> galleryThumbs;


    public String getProductTitle(){
        return productTitle.getText();
    }

    public List<String> getTabsList(){
        return tabs.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Click tab on Product Page by name")
    public KupiTutProductPage clickTabByName(String name){
        for(WebElement tab: tabs){
            if (tab.getText().contains(name)){
                logger.info("Clicking tab on Product Page: " + name);
                ElementUtils.scrollIntoViewByJS(driver, tab);
                tab.click();
                break;
            }
        }
        return this;
    }

    @Step("Click tab on Product Page by index")
    public KupiTutProductPage clickTabByIndex(int index){
        tabs.get(index).click();
        return this;
    }

    @Step("Open photo gallery for the product")
    public KupiTutProductPage galleryOpen(){
        logger.info("Opening photo gallery");
        gallery.click();
        return this;
    }

    @Step("Going through photo gallery forward")
    public KupiTutProductPage slideGalleryForward(){
        logger.info("Going through photo gallery forward");
        for(int i=1; i<galleryThumbs.size(); i++){
            wait.until(ExpectedConditions.visibilityOf(nextArrayGallery)).click();
        }
        return this;
    }

    @Step("Going through photo gallery backward")
    public KupiTutProductPage slideGalleryBackward(){
        logger.info("Going through photo gallery backward");
        for(int i=1; i<galleryThumbs.size(); i++){
            wait.until(ExpectedConditions.visibilityOf(prevArrayGallery)).click();
        }
        return this;
    }

    @Step("Close photo gallery")
    public KupiTutProductPage closeGallery(){
        logger.info("Closing photo gallery");
        closeGalleryBtn.click();
        return this;
    }

    public String getActiveTabName(){
        return activeTab.getText();
    }

    public String getTabMapTitle(){
        return tabMapTitle.getText();
    }

    public String getTabPricesTitle(){
        return tabPricesTitle.getText();
    }

    public boolean isWishlistBtnAvailable(){
        return ElementUtils.isElementClickable(wishlistBtn);
    }

    public boolean isCompareBtnAvailable(){
        return ElementUtils.isElementClickable(compareBtn);
    }

    public boolean isMapAvailable(){
        return map.isDisplayed();
    }

    public boolean isCharacteristicsTableAvailable(){
        return characteristicsTable.isDisplayed();
    }

    public boolean isReviewWidgetAvailable(){
        return wait.until(ExpectedConditions.visibilityOf(reviewWidget)).isDisplayed();
    }
}
