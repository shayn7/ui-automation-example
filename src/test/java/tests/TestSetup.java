package tests;

import com.naamad.factories.BrowserFactory;
import com.naamad.steps.BrowserSteps;
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
        baseUrl = System.getProperty("baseUrl", "https://translate.google.com/");
        browser = BrowserFactory.getBrowser(System.getProperty("browser", "local_chrome"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if (browser != null) this.browser.tearDown();
    }


        /*
    maven command: mvn clean test -DbaseUrl=${endpoint} -DsuiteXmlFile=${suiteXmlFile} -Dbrowser=${environment} -Dhub=${hub}
    allure report command: allure serve allure-results
     */
}
