package com.automation.tutby.data;

import java.util.Arrays;
import java.util.List;

public class DataConstants {

    // top menu text elements
    public static final String HAMBURGER_TEXT = "Разделы";
    public static final String ALL_SECTIONS_LINK_TEXT = "Все разделы";
    public static final String RESOURCE_NAME_NEWS = "Новости";
    public static final String RESOURCE_NAME_FINANCE = "Финансы";
    public static final String RESOURCE_NAME_SHOPS = "Магазины";

    public static final String ASIDE_MENU_LINK_TAM_BY = "TAM.BY";

    public static final String ALL_RESOURCES = "Ресурсы портала";


    // kupi.tut.by
    // main catalog menu
    // level 1 items
    public static final String MOBILE_PHONES_L1_LINK = "Мобильные телефоны";


    // product filters and options - Mobile Phones
    public static final String FILTER_BRAND = "Производители";
    public static final String BRAND_APPLE = "Apple";
    public static final String BRAND_SAMSUNG = "Samsung";
    public static final String BRAND_ASUS = "ASUS";

    public static final String FILTER_COLOR = "Цвет";
    public static final String RED = "красный";
    public static final String YELLOW = "желтый";
    public static final String SILVER = "серебристый";
    public static final String WHITE = "белый";

    public static final String FILTER_SCREEN_RESOLUTION = "Разрешение экрана:";
    public static final String SCR_RES_1 = "1920×1152";
    public static final String SCR_RES_2 = "1334×750";
    public static final String SCR_RES_2_for_filter_check = "1334x750"; //encoding issue, different in filters and in text
    public static final String SCR_RES_3 = "2160×1080";
    public static final String SCR_RES_3_for_filter_check = "2160x1080";

    public static final String FILTER_TYPE = "Тип:";
    public static final String TYPE_SMART = "смартфон";

    public static final String IOS = "IOS";
    public static final String ANDROID = "Android";
    public static final String NFC = "NFC";

    //product page
    public static final String TAB_PRICES = "Цены в Минске";
    public static final String TAB_MAP = "На карте";
    public static final String TAB_CHARACTERISTICS = "Характеристики";
    public static final String TAB_REVIEWS = "Отзывы";
    public static final List<String> PRODUCT_TABS = Arrays.asList(TAB_PRICES, TAB_MAP, TAB_CHARACTERISTICS, TAB_REVIEWS);
    public static final String TAB_MAP_TITLE = "Магазины в Минске";
    public static final String TAB_PRICES_TITLE = "Где купить";

    //search
    public static final List<String> KW_TO_EXCLUDE = Arrays.asList("афиша","видео");
    public static final String KW_FOR_HOMEPAGE_SEARCH ="Минск расписание";
    public static final String KW_FOR_RESOURCE_PAGE_SEARCH = "строительств";
    public static final String SEARCH_TYPE_INTERNET = "Интернет";
}
