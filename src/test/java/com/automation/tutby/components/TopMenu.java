package com.automation.tutby.components;

import com.automation.tutby.pages.abstractPages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TopMenu extends Page {

    public TopMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".topbar-burger")
    private WebElement hamburgerMenuButton;

    @FindBy(css = ".topbarmore-w")
    private WebElement hamburgerMenu;

    @FindBy(css = ".b-topbar-more-aside")
    private WebElement topMenuAsideBlock;

    @FindBy(css = "ul.b-topbar-more-aside > li.topbar__li > a")
    private List<WebElement> mainMenuAsideLinkList;

    @FindBy(css = ".b-topbar-more-list")
    private WebElement topMenu;

    @FindBy(css = "ul.b-topbar-more-list > li.topbar__li")
    private List<WebElement> topMenuLinkList;

    @FindBy(css = ".topbar-close")
    private WebElement menuCloseButton;

    @FindBy(css = ".topbar__li.mores")
    private WebElement allSectionsButton;

    public void clickHamburgerMenu() {
        hamburgerMenuButton.click();
    }

    public void clickMenuCloseButton(){
        menuCloseButton.click();
    }

    public void clickAllSectionsButton(){
        allSectionsButton.click();
    }

    public void clickMenuLinkByText(String text){
        for(WebElement link: topMenuLinkList){
            if (link.getText().equalsIgnoreCase(text)) {
                link.click();
                break;
            }
        }
    }

    public void clickAsideMenuLinkByText(String text){
        for(WebElement link: mainMenuAsideLinkList){
            if (link.getText().equalsIgnoreCase(text)) {
                link.click();
                break;
            }
        }
    }

    public void clickMenuLinkByIndex(){}

    public void clickAsideMenuLinkByIndex(){}

    public WebElement getTopMenuAsideBlock() {
        return topMenuAsideBlock;
    }

    public WebElement getTopMenu() {
        return topMenu;
    }

    public WebElement getMenuCloseButton() {
        return menuCloseButton;
    }

    public WebElement getAllSectionsButton() {
        return allSectionsButton;
    }

    public WebElement getHamburgerMenuButton() {
        return hamburgerMenuButton;
    }
}
