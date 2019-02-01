package com.automation.tutby.tests;

import com.automation.tutby.data.FilterDataProvider;
import com.automation.tutby.pages.Homepage;
import com.automation.tutby.pages.KupiTutHomePage;
import com.automation.tutby.pages.KupiTutProductListPage;
import com.automation.tutby.pages.KupiTutProductPage;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.automation.tutby.data.DataConstants.*;
import static org.testng.Assert.assertTrue;

public class KupiTutShopForMobileTest extends TestBase {

    KupiTutHomePage shopHomepage;
    KupiTutProductListPage mobilePhonesListPage;
    KupiTutProductPage mobilePhonePage;
    SoftAssert softAssert;

    @BeforeMethod
    public void prepareForTest(){
        new Homepage(driver).openLinkFromTopMenu(RESOURCE_NAME_SHOPS);
        shopHomepage = new KupiTutHomePage(driver);
        mobilePhonesListPage = shopHomepage.clickLevel1MenuLinkByText(MOBILE_PHONES_L1_LINK);
        mobilePhonesListPage = new KupiTutProductListPage(driver);
        softAssert = new SoftAssert();
    }

    @Feature("Shop")
    @Feature("Mobile Phones Shop")
    @Test(description = "Check filtering mobile phones list",
            dataProvider = "Filters for mobile phones product list", dataProviderClass = FilterDataProvider.class)
    public void filterMobilePhoneListTest(Map<String, List<String>> filter){

        mobilePhonesListPage.closeConverter();

        for(Map.Entry<String, List<String>> entry : filter.entrySet()) {
            if (!entry.getKey().equals("check")) {
                mobilePhonesListPage.setFilter(entry.getKey(), entry.getValue());
            }
        }
        mobilePhonesListPage.applyFilters();
        assertTrue(mobilePhonesListPage.areAllFiltersApplied(filter.get("check")));
    }

    @Feature("Shop")
    @Feature("Mobile Phones Shop")
    @Test(description = "Opening Mobile phone page from Mobile phones list")
    public void openMobilePhonePage(){
        String titleFromList;
        mobilePhonesListPage.closeConverter()
                .setFilter(FILTER_BRAND, Arrays.asList(BRAND_APPLE))
                .applyFilters();
        titleFromList = mobilePhonesListPage.getProductTitle(0);
        mobilePhonePage = mobilePhonesListPage.clickProductByIndex(0);

        assertTrue(titleFromList.equalsIgnoreCase(mobilePhonePage.getProductTitle()));
    }

    @Feature("Shop")
    @Feature("Mobile Phones Shop")
    @Test(description = "Check main elements of product page (mobile phone)")
    public void checkMobilePhonePageElements() {
        mobilePhonesListPage.closeConverter();
        mobilePhonePage = mobilePhonesListPage.clickProductByIndex(0);

        mobilePhonePage.galleryOpen()
                        .slideGalleryForward()
                        .slideGalleryBackward()
                        .closeGallery();

        softAssert.assertTrue(mobilePhonePage.isWishlistBtnAvailable(), "Wishlist not available");
        softAssert.assertTrue(mobilePhonePage.isCompareBtnAvailable(), "Compare button not available");
        softAssert.assertTrue(mobilePhonePage.getTabsList().containsAll(PRODUCT_TABS), "Invalid tabs");
        softAssert.assertTrue(mobilePhonePage.getActiveTabName().equalsIgnoreCase(TAB_PRICES), "Incorrect active tab by default");

        mobilePhonePage.clickTabByName(TAB_MAP);
        softAssert.assertTrue(mobilePhonePage.isMapAvailable(), "Map not available");
        softAssert.assertTrue(mobilePhonePage.getTabMapTitle().contains(TAB_MAP_TITLE), "Title for Map tab incorrect");

        mobilePhonePage.clickTabByName(TAB_CHARACTERISTICS);
        softAssert.assertTrue(mobilePhonePage.isCharacteristicsTableAvailable(), "Charaacteristics table not available");

        mobilePhonePage.clickTabByName(TAB_REVIEWS);
        softAssert.assertTrue(mobilePhonePage.isReviewWidgetAvailable(), "Review widget not available");

        mobilePhonePage.clickTabByName(TAB_PRICES);
        softAssert.assertTrue(mobilePhonePage.getTabPricesTitle().contains(TAB_PRICES_TITLE), "Prices tab title is incorrect");

        softAssert.assertAll();
    }

    @Override
    public void openTargetPage() {
        driver.get(baseUrl);
    }
}
