package com.naamad.enums;

import com.naamad.pages.BasePage;
import com.naamad.pages.HomePage;
import com.naamad.steps.BrowserSteps;

public enum Pages {
    HOME_PAGE(){
        @Override
        public BasePage getPage(BrowserSteps browser) {
            return new HomePage(browser);
        }
    };

    public abstract BasePage getPage(BrowserSteps browser);
}
