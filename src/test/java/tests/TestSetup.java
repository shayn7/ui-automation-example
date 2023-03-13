package tests;

import com.naamad.factories.BrowserFactory;
import com.naamad.steps.BrowserSteps;
import com.naamad.utils.ApplicationProperties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({listeners.Listeners.class})
public class TestSetup {
    protected BrowserSteps browser;
    protected String baseUrl;

    public BrowserSteps getBrowser(){
        return browser;
    }

    @BeforeMethod
    public void setup(){
        ApplicationProperties applicationProperties = new ApplicationProperties();
        baseUrl = System.getProperty("baseUrl", applicationProperties.readProperty("baseUrl"));
        browser = BrowserFactory.getBrowser(System.getProperty("browser", applicationProperties.readProperty("browser")));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if (browser != null) this.browser.tearDown();
    }


}
