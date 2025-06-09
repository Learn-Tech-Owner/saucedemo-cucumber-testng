package com.saucedemo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(css = ".inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(css = ".product_sort_container")
    private WebElement sortDropdown;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        return waitForElementToBeVisible(By.cssSelector(".title")).getText();
    }

    public int getNumberOfProducts() {
        return inventoryItems.size();
    }

    public void addProductToCart(String productName) {
        // Localiser le bouton "Add to cart" pour un produit spécifique
        WebElement productElement = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='inventory_item']"));
        WebElement addToCartButton = productElement.findElement(By.xpath(".//button[text()='Add to cart']"));
        waitForWebElementToBeClickable(addToCartButton).click();
    }

    public boolean isProductInCart(String productName) {
        // Le bouton change en "Remove" si le produit est dans le panier
        WebElement productElement = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='inventory_item']"));
        return productElement.findElement(By.xpath(".//button")).getText().equals("Remove");
    }

    public void removeProductFromCart(String productName) {
        WebElement productElement = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='inventory_item']"));
        WebElement removeButton = productElement.findElement(By.xpath(".//button[text()='Remove']"));
        waitForWebElementToBeClickable(removeButton).click();
    }

    public void selectSortOption(String optionText) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(optionText);
    }

    public List<String> getProductNames() {
        return inventoryItems.stream()
                .map(item -> item.findElement(By.cssSelector(".inventory_item_name")).getText())
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        return inventoryItems.stream()
                .map(item -> Double.parseDouble(item.findElement(By.cssSelector(".inventory_item_price")).getText().replace("$", "")))
                .collect(Collectors.toList());
    }
}