package com.saucedemo.qa.stepdefinitions;

import com.beust.jcommander.Parameter;
import com.saucedemo.qa.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
//        // Configurer le WebDriver en fonction de l'environnement (local ou Selenium Grid)
        String browser = System.getProperty("browser", "chrome"); // Default to chrome
        String hubUrl = System.getProperty("hubUrl"); // URL du Selenium Grid Hub

        if (hubUrl != null && !hubUrl.isEmpty()) {
            // Exécution sur Selenium Grid
            DesiredCapabilities capabilities;
            switch (browser.toLowerCase()) {
                case "firefox":
                    capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("Firefox");
                    break;
                case "edge":
                    capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("Edge");
                    break;
                case "chrome":
                default:
                    capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("Chrome");
                    break;
            }
            try {
                driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("URL du Hub Selenium Grid invalide: " + hubUrl);
            }
        } else {
            driver = WebDriverFactory.getWebDriver(browser);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Prendre une capture d'écran en cas d'échec
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        if (driver != null) {
            driver.quit();
        }
    }
}