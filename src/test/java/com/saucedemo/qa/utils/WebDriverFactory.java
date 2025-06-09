package com.saucedemo.qa.utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class WebDriverFactory {
    public static String headlessMode = System.getProperty("headless.mode", "false"); // Récupère la propriété
    public static WebDriver driver = null;

    public static WebDriver getWebDriver(String browser) {
        switch (browser.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionsChrome = new ChromeOptions();
                System.out.println("\nValue of headless mode: "+Boolean.parseBoolean(headlessMode));
                if (Boolean.parseBoolean(headlessMode)) {
                    optionsChrome.addArguments("--headless");
                    optionsChrome.addArguments("--disable-gpu"); // Bonne pratique pour headless
                    optionsChrome.addArguments("--no-sandbox"); // Requis pour Docker/CI
                    optionsChrome.addArguments("--disable-dev-shm-usage"); // Requis pour Docker/CI
                }
                optionsChrome.addArguments("--start-maximized");
                driver = new ChromeDriver(optionsChrome);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions optionsFirefox = new FirefoxOptions();

                if (Boolean.parseBoolean(headlessMode)) {
                    optionsFirefox.addArguments("--headless");
                    optionsFirefox.addArguments("--disable-gpu"); // Bonne pratique pour headless
                    optionsFirefox.addArguments("--no-sandbox"); // Requis pour Docker/CI
                    optionsFirefox.addArguments("--disable-dev-shm-usage"); // Requis pour Docker/CI
                }
                optionsFirefox.addArguments("--start-maximized");
                driver = new FirefoxDriver(optionsFirefox);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions optionsEdge = new EdgeOptions();

                if (Boolean.parseBoolean(headlessMode)) {
                    optionsEdge.addArguments("--headless");
                    optionsEdge.addArguments("--disable-gpu"); // Bonne pratique pour headless
                    optionsEdge.addArguments("--no-sandbox"); // Requis pour Docker/CI
                    optionsEdge.addArguments("--disable-dev-shm-usage"); // Requis pour Docker/CI
                }
                optionsEdge.addArguments("--start-maximized");
                driver = new EdgeDriver(optionsEdge);
                break;
            default:
                throw new IllegalArgumentException("Navigateur non supporté : " + browser);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Attente implicite globale
        return driver;
    }
}