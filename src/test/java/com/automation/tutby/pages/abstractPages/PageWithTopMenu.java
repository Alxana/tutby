package com.automation.tutby.pages.abstractPages;

import com.automation.tutby.components.TopMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public  abstract class PageWithTopMenu extends Page {

    public PageWithTopMenu(WebDriver driver) {
        super(driver);
    }

    TopMenu menu = new TopMenu(driver);

    @Step("Open top menu")
    public PageWithTopMenu openTopMenu(){
        logger.info("Opening top menu");
        menu.clickHamburgerMenu();
        return this;
    }

    @Step("Close top menu")
    public PageWithTopMenu closeMenu(){
        logger.info("Closing top menu");
        menu.clickMenuCloseButton();
        return this;
    }

    @Step("Click a link from main block of top menu")
    public PageWithTopMenu clickTopMenuLink(String text){
        logger.info("Clicking link from top menu: " + text);
        menu.clickAsideMenuLinkByText(text);
        return this;
    }

    @Step("Click a link from aside block of top menu")
    public PageWithTopMenu clickAsideBlockLink(String text){
        logger.info("Clicking link from aside block of top menu: " + text);
        menu.clickAsideMenuLinkByText(text);
        return this;
    }

    @Step("Open top menu and click link in it")
    public PageWithTopMenu openLinkFromTopMenu(String text){
        logger.info("Open top menu and click link in it: " + text);
        menu.clickHamburgerMenu();
        menu.clickMenuLinkByText(text);
        return this;
    }

    @Step("Click 'All Resources' link from top menu")
    public PageWithTopMenu openAllResources(){
        logger.info("Click 'All Resources' link from top menu");
        menu.clickAllSectionsButton();
        return this;
    }

    public TopMenu getMenu(){return menu;}

}
