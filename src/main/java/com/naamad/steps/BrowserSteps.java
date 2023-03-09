package com.naamad.steps;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.naamad.enums.Browsers;
import com.naamad.enums.Pages;
import com.naamad.factories.DriverFactory;
import com.naamad.pages.BasePage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Getter
public abstract class BrowserSteps {

    private static final Logger log = LoggerFactory.getLogger(BrowserSteps.class);

    private final Browsers browser;
    private WebDriver driver;
    private BasePage expectedPage;
    private String parentWindow;

    protected BrowserSteps(Browsers browser) {
        this.browser = browser;
    }

    public void startSession(){
        MutableCapabilities options = initCapabilities();
        if (browser == Browsers.REMOTE) {
            driver = DriverFactory.getRemoteDriver(System.getProperty("hub"), options);
        } else {
            driver = DriverFactory.getDriver(browser, options);
        }
    }

    public void iShouldBeOnPage(Pages page){
        expectedPage = page.getPage(this);
        logToConsoleAndReporter(String.format("page URL is: %s", driver.getCurrentUrl()));
        boolean isExpectedPage = expectedPage.verifyPage();
        Assert.assertTrue(isExpectedPage, "actual page is not " + page);
        logToConsoleAndReporter(String.format("we are on page: %s as expected", page));
    }

    public <T extends BasePage> T getExpectedPage(){
        return (T) expectedPage;
    }

    @Step("getting URL: {0}")
    public void getUrl(String url){
        String currentUrl = driver.getCurrentUrl();
        String newUrl = currentUrl.replace(currentUrl, url);
        driver.get(newUrl);
        logToConsoleAndReporter(String.format("after getting the URL the page title is %s", getPageTitle()));
    }

    public void setText(WebElement element, String text){
        boolean isDisplayed = isElementDisplayed(element);
        if(isDisplayed){
            element.clear();
            element.sendKeys(text);
            logToConsoleAndReporter(String.format("entered the text: %s in the text box", text));
        }
    }

    public String getText(WebElement element){
        boolean isDisplayed = isElementDisplayed(element);
        return isDisplayed ? element.getText() : " ";
    }

    @Step("checking if element is displayed. element is: {0}")
    public boolean isElementDisplayed(WebElement element){
        try{
            waitFor(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    @Step("clicked on element: {0}")
    public void clickOnElement(WebElement element){
        boolean isClickable = isElementDisplayed(element);
        if(isClickable){
            element.click();
            logToConsoleAndReporter("clicked on element " + element);
        } else {
            logToConsoleAndReporter("wasn't able to click on element " + element);
        }
    }

    public WebElement findElementBy(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void openNewWindow() {
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
    }

    public void switchWindow(){
        parentWindow = setParentWindow();
        Set<String> windows = getAllWindows();
        for(String child_window : windows){
            if(!parentWindow.equals(child_window))
                driver.switchTo().window(child_window);
        }
    }

    public void takePageScreenshot(String name) {
        Shutterbug.shootPage(driver).withName(name).save();
    }

    public void closeWindow(){
        sleep(3);
        driver.close();
        sleep(3);
    }

    public String getParentWindow(){
        return parentWindow;
    }

    public void switchToParentWindow(String parentWindow){
        driver.switchTo().window(parentWindow);
    }

    public boolean isElementDisabled(WebElement element){
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].hasAttribute('disabled)';", element);
    }

    public void tearDown(){
        if(driver != null) driver.quit();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public ChromeDriver getChromeDriver(){
        return (ChromeDriver) driver;
    }

    private void logToConsoleAndReporter(String text) {
        log.info(text);
        saveTextLog(text);
    }

    private void sleep(int timeInSeconds){
        try {
            TimeUnit.SECONDS.sleep(timeInSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void waitFor(ExpectedCondition<WebElement> webElementExpectedCondition){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(webElementExpectedCondition);
    }

    private String setParentWindow(){
        return driver.getWindowHandle();
    }

    private Set<String> getAllWindows(){
        return driver.getWindowHandles();
    }

    @Attachment(value = "{0}", type = "text/plain")
    private String saveTextLog(String text) {
        return text;
    }


    protected abstract MutableCapabilities initCapabilities();
    public abstract void simulateSlowNetworkConnection() throws IOException;
}
