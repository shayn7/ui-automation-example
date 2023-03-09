package tests;

import com.naamad.enums.Pages;
import org.testng.annotations.Test;

public class UrlEntriesTests extends TestSetup{


    @Test(priority = 1, description = "a logged in user log from another tab")
    public void enterSite(){
        browser.startSession();
        browser.getUrl(baseUrl);
        browser.iShouldBeOnPage(Pages.HOME_PAGE);
    }
}
