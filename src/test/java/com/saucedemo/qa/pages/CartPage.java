package com.saucedemo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        return waitForElementToBeVisible(By.cssSelector(".title")).getText();
    }

    public boolean isProductDisplayedInCart(String productName) {
        return cartItems.stream()
                .anyMatch(item -> item.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName));
    }

    public int getNumberOfItemsInCart() {
        return cartItems.size();
    }

    public void clickRemoveButton(String productName) {
        WebElement productElement = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='cart_item']"));
        WebElement removeButton = productElement.findElement(By.xpath(".//button[text()='Remove']"));
        waitForWebElementToBeClickable(removeButton).click();
    }

    public void clickContinueShopping() {
        waitForWebElementToBeClickable(continueShoppingButton).click();
    }

    public void clickCheckout() {
        waitForWebElementToBeClickable(checkoutButton).click();
    }
}