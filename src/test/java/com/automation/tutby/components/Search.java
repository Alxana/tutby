package com.automation.tutby.components;

import com.automation.tutby.pages.abstractPages.Page;

public interface Search {

    public Page searchFor(String keyPhrase);
    public void insertSearchPhrase(String keyPhrase);
    public Page clickSubmitBtn();
    public void clearSearchField();
    public String getPlaceholder();

}
