package com.naamad.pages;

import com.naamad.steps.BrowserSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(BrowserSteps browser) {
        super(browser);
    }

    @Override
    public boolean verifyPage() {
        return browser.getPageTitle().equals("Google Translate");
    }

    @FindBy(css = "textarea.er8xn")
    private WebElement translateTextArea;
}
