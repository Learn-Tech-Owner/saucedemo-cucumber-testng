package com.saucedemo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String BASE_URL = "https://www.saucedemo.com/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Temps d'attente implicite
        PageFactory.initElements(driver, this); // Initialise les WebElements annotés avec @FindBy
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForWebElementToBeClickable(WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForWebElementToBeVisible(WebElement webElement) {
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    // Eléments communs à plusieurs pages (e.g., menu burger, icône panier)
    protected By menuBurgerButton = By.id("react-burger-menu-btn");
    public By shoppingCartIcon = By.id("shopping_cart_container");
    protected By shoppingCartBadge = By.cssSelector(".shopping_cart_badge"); // Pour le nombre d'articles dans le panier

    public void clickMenuBurger() {
        waitForElementToBeClickable(menuBurgerButton).click();
    }

    public String getShoppingCartCount() {
        try {
            return waitForElementToBeVisible(shoppingCartBadge).getText();
        } catch (Exception e) {
            return "0"; // Si le badge n'est pas visible, le panier est vide
        }
    }
}