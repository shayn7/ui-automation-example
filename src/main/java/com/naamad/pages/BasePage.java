package com.naamad.pages;

import com.naamad.steps.BrowserSteps;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected BrowserSteps browser;

    public BasePage(BrowserSteps browser){
        this.browser = browser;
        PageFactory.initElements(browser.getDriver(), this);
    }

    public abstract boolean verifyPage();
}
