package com.automation.tutby.tests;

import com.automation.tutby.pages.FinancePage;
import com.automation.tutby.pages.Homepage;
import com.automation.tutby.utils.ElementUtils;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Set;

import static com.automation.tutby.data.DataConstants.*;
import static org.testng.Assert.assertTrue;

public class TopMenuTest extends TestBase{

    Homepage homepage;
    FinancePage financePage;
    SoftAssert softAssert;

    @BeforeMethod
    public void prepareTest(){
        homepage = new Homepage(driver);
        softAssert = new SoftAssert();
    }

    @Feature("Top Menu")
    @Test(description = "Testing main elements of top menu")
    public void topMenuElementsTest(){
        softAssert.assertTrue(ElementUtils.isElementTextEquals(homepage.getMenu().getHamburgerMenuButton(), HAMBURGER_TEXT));
        homepage.openTopMenu();
        softAssert.assertTrue(ElementUtils.isElementTextEquals(homepage.getMenu().getHamburgerMenuButton(), HAMBURGER_TEXT));
        softAssert.assertTrue(ElementUtils.isElementTextEquals(homepage.getMenu().getMenuCloseButton(), HAMBURGER_TEXT));
        softAssert.assertTrue(ElementUtils.isElementClickable(homepage.getMenu().getAllSectionsButton()));
        softAssert.assertTrue(ElementUtils.isElementTextEquals(homepage.getMenu().getAllSectionsButton(), ALL_SECTIONS_LINK_TEXT));
        homepage.closeMenu();
        softAssert.assertAll();

    }

    @Feature("Top Menu")
    @Feature("Finance")
    @Test(description = "Navigating to a resource from top menu main block of links")
    public void openTopMenuLinkTest(){
        homepage.openLinkFromTopMenu(RESOURCE_NAME_FINANCE);
        financePage = new FinancePage(driver);
        String resType = financePage.getResourceName();
        assertTrue(resType.equalsIgnoreCase(RESOURCE_NAME_FINANCE),
                "Invalid page open: " + resType + ", instead of: " + RESOURCE_NAME_FINANCE);
    }

    @Feature("Top Menu")
    @Test(description = "Navigating to a resource from top menu aside block of links")
    public void openAsideMenuLinkTest(){
        String parent_window = driver.getWindowHandle();
        homepage.openTopMenu().clickAsideBlockLink(ASIDE_MENU_LINK_TAM_BY);
        Set<String> handlers = driver.getWindowHandles();
        for (String handle: handlers){
            if(!parent_window.equalsIgnoreCase(handle)){
                driver.switchTo().window(handle);
            }
        }
        assertTrue(driver.getTitle().contains(ASIDE_MENU_LINK_TAM_BY), "Incorrect page opened");
    }


    @Feature("Top Menu")
    @Test(description = "Navigating to All Resources page from top menu")
    public void openAllResourcesTest(){
        homepage.openTopMenu().openAllResources();
        assertTrue(driver.getTitle().contains(ALL_RESOURCES), "Incorrect page opened");
    }

    public void openTargetPage(){
        driver.get(baseUrl);
    }
}
