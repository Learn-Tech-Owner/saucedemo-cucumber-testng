package com.saucedemo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(css = ".summary_info_label")
    private WebElement paymentInformationLabel; // Exemple d'élément sur la page Overview

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        return waitForElementToBeVisible(By.cssSelector(".title")).getText();
    }

    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public String getErrorMessage() {
        return waitForElementToBeVisible(By.cssSelector("[data-test='error']")).getText();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isProductDisplayedInOverview(String productName) {
        // Vérifie si le produit est listé dans le récapitulatif
        return driver.findElements(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']")).size() > 0;
    }

    public void clickFinishButton() {
        WebElement finishButton = waitForElementToBeClickable(By.id("finish"));
        finishButton.click();
    }
}