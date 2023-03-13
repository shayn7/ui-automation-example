package tests;

import com.naamad.enums.Pages;
import com.naamad.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.naamad.enums.Languages.*;
import static com.naamad.enums.Translation.FROM;

public class TranslationTests extends TestSetup{


    @Test(priority = 1, description = "set the translate from language to German but type an Italian word in the translation from text area")
    public void shouldSuggestTranslationLanguage(){
        browser.startSession();
        browser.getUrl(baseUrl);
        browser.iShouldBeOnPage(Pages.HOME_PAGE);
        HomePage homePage = browser.getExpectedPage();
        homePage.setLanguage(FROM, GERMAN);
        homePage.setTextOnTranslationTextArea("ciao");
        Assert.assertEquals(homePage.getTextFromLanguageDetectionSuggestion(), "Translate from: Italian");
    }

    @Test(priority = 2, description = "enter a valid text and valid country codes in the url")
    public void shouldTranslate(){
        browser.startSession();
        browser.getUrl(baseUrl + "?sl=en&tl=de&text=how%20you%20doing");
        browser.iShouldBeOnPage(Pages.HOME_PAGE);
        HomePage homePage = browser.getExpectedPage();
        Assert.assertEquals(homePage.getTextFromTranslationResultTextArea(), "wie geht's");
    }
}
