package com.automation.tutby.data;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.automation.tutby.data.DataConstants.*;

public class FilterDataProvider {

    @DataProvider(name = "Filters for mobile phones product list")
    public static Object[] getMobilePhoneFilterOptions() {

        Map<String, List<String>> filter1 = new HashMap<>();
        filter1.put(FILTER_BRAND, Arrays.asList(BRAND_APPLE, BRAND_SAMSUNG));
        filter1.put(FILTER_COLOR, Arrays.asList(RED, YELLOW));
        filter1.put("single-row", Arrays.asList(IOS));
        filter1.put("select", Arrays.asList(FILTER_SCREEN_RESOLUTION, SCR_RES_1, SCR_RES_2));
        filter1.put("check", Arrays.asList(BRAND_APPLE, SCR_RES_2_for_filter_check, IOS));

        Map<String, List<String>> filter2 = new HashMap<>();
        filter2.put(FILTER_BRAND, Arrays.asList(BRAND_ASUS));
        filter2.put(FILTER_COLOR, Arrays.asList(WHITE, SILVER));
        filter2.put("single-row", Arrays.asList(NFC));
        filter2.put("select", Arrays.asList(FILTER_TYPE, TYPE_SMART));
        filter2.put("check", Arrays.asList(BRAND_ASUS, NFC, TYPE_SMART));


        return new Object[][]{
                {filter1},
                {filter2}
        };
    }
}
