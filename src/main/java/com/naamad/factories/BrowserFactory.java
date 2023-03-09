package com.naamad.factories;

import com.naamad.enums.Browsers;
import com.naamad.steps.BrowserSteps;
import com.naamad.steps.ChromeSteps;
import com.naamad.steps.FirefoxSteps;

public class BrowserFactory {
    public static synchronized BrowserSteps getBrowser(String browser){
        return switch (browser) {
            case "local_chrome" -> new ChromeSteps(Browsers.LOCAL_CHROME);
            case "local_firefox" -> new FirefoxSteps(Browsers.LOCAL_FIREFOX);
            case "remote" -> new ChromeSteps(Browsers.REMOTE);
            default -> throw new RuntimeException("browser not supported!!");
        };
    }
}
