package com.naamad.steps;

import com.naamad.enums.Browsers;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxSteps extends BrowserSteps {

    public FirefoxSteps(Browsers environment) {
        super(environment);
    }

    @Override
    protected MutableCapabilities initCapabilities() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--window-size=1920,1200");
        return options;
    }

    @Override
    public void simulateSlowNetworkConnection() {

    }
}
