package com.naamad.pages;

import com.naamad.enums.Languages;
import com.naamad.enums.Translation;
import com.naamad.steps.BrowserSteps;
import org.openqa.selenium.Keys;
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

    public void setLanguage(Translation translation, Languages language) {
        switch (translation){
            case FROM -> browser.clickOnElementUsingJs("[jsname='s3Eaab']");
            case TO -> browser.clickOnElementUsingJs("[jsname='zumM6d']");
        }
        browser.clickOnElementUsingJs("[data-language-code='XX']".replace("XX", language.getLanguageCode()));
        pressOnEscapeButton();
    }

    private void pressOnEscapeButton() {
        browser.pressOnKeyboardKey(Keys.ESCAPE);
    }

    public void setTextOnTranslationTextArea(String text) {
        browser.setText(translationFromTextArea, text);
    }

    public String getTextFromTranslationResultTextArea(){
        return browser.getText(translationResultTextArea);
    }

    public String getTextFromLanguageDetectionSuggestion(){
        return browser.getText(detectLanguageSuggestion);
    }

    @FindBy(css = "textarea.er8xn")
    private WebElement translationFromTextArea;
    @FindBy(css = "span.ryNqvb")
    private WebElement translationResultTextArea;
    @FindBy(css = "span.hBxMjb")
    private WebElement languageToSelect;
    @FindBy(css = "div.VhOj3e")
    private WebElement detectLanguageSuggestion;
}
