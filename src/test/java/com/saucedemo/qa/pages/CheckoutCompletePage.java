package com.saucedemo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(css = ".complete-header")
    private WebElement completeHeader;

    @FindBy(css = ".complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        return waitForElementToBeVisible(By.cssSelector(".title")).getText();
    }

    public String getCompleteHeaderText() {
        return waitForWebElementToBeVisible(completeHeader).getText();
    }

    public String getCompleteText() {
        return waitForWebElementToBeVisible(completeText).getText();
    }

    public void clickBackHomeButton() {
        waitForWebElementToBeClickable(backHomeButton).click();
    }
}