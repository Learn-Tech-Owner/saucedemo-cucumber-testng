package com.saucedemo.qa.stepdefinitions;

import com.saucedemo.qa.pages.InventoryPage; // Pour la page d'inventaire
import com.saucedemo.qa.pages.LoginPage; // Pour la page de connexion
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MenuSteps {

    private InventoryPage inventoryPage;
    private LoginPage loginPage;

    public MenuSteps() {
        this.inventoryPage = new InventoryPage(Hooks.driver);
        this.loginPage = new LoginPage(Hooks.driver);
    }

    @When("je clique sur l'icône du menu burger")
    public void je_clique_sur_l_icone_du_menu_burger() {
        inventoryPage.clickMenuBurger();
    }

    @Then("le menu burger devrait être ouvert")
    public void le_menu_burger_devrait_etre_ouvert() {
        // Vérifiez la visibilité d'un élément interne au menu pour confirmer qu'il est ouvert
        Assert.assertTrue(inventoryPage.waitForElementToBeVisible(By.id("react-burger-cross-btn")).isDisplayed(), "Le menu burger n'est pas ouvert.");
    }

    @When("je clique sur le bouton {string} au niveau du menu")
    public void je_clique_sur_le_bouton_au_niveau_du_menu(String buttonText) {
        if ("Close Menu".equalsIgnoreCase(buttonText)) {
            Hooks.driver.findElement(By.id("react-burger-cross-btn")).click();
        } else if ("Back Home".equalsIgnoreCase(buttonText) || "Checkout".equalsIgnoreCase(buttonText) || "Continue Shopping".equalsIgnoreCase(buttonText)) {
            // Géré par d'autres Step Definitions si le bouton est sur une page principale
        } else {
            Assert.fail("Bouton non reconnu dans MenuSteps: " + buttonText);
        }
    }

    @Then("le menu burger devrait être fermé")
    public void le_menu_burger_devrait_etre_ferme() {
        // Vérifiez que l'icône de fermeture n'est plus visible (ou l'icône d'ouverture est visible)
        Hooks.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Désactive l'attente implicite
        try {
            Hooks.driver.findElement(By.id("react-burger-cross-btn")).isDisplayed();
            Assert.fail("Le menu burger n'est pas fermé.");
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException e) {
            // Attendu: l'élément n'est pas trouvé ou n'est plus interactif
            Assert.assertTrue(true);
        } finally {
            Hooks.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Réactive l'attente implicite
        }
    }

    @And("je clique sur le lien {string}")
    public void je_clique_sur_le_lien(String linkText) {
        //WebElement link = Hooks.driver.findElement(By.xpath("//a[text()='" + linkText + "']"));
        inventoryPage.waitForElementToBeClickable(By.xpath("//a[text()='" + linkText + "']")).click();
    }

    @Then("je devrais être redirigé vers la page {string} du menu")
    public void je_devrais_etre_redirige_vers_la_page_du_menu(String pageName) {
        if ("Products".equalsIgnoreCase(pageName)) {
            Assert.assertTrue(inventoryPage.getCurrentUrl().contains("inventory.html"), "Non redirigé vers la page Products.");
            Assert.assertEquals(inventoryPage.getPageTitleText(), pageName, "Le titre de la page Products n'est pas correct.");
        } else if ("Sauce Labs".equalsIgnoreCase(pageName)) {
            // Pour le lien "About" qui redirige vers saucelabs.com
            Assert.assertTrue(Hooks.driver.getCurrentUrl().contains("saucelabs.com"), "Non redirigé vers la page Sauce Labs.");
            // On ne peut pas facilement tester le titre car c'est un site externe
        } else if ("SauceDemo".equalsIgnoreCase(pageName)) { // Pour la déconnexion
            Assert.assertTrue(loginPage.getCurrentUrl().contains("saucedemo.com"), "Non redirigé vers la page de connexion.");
        }
        // Ajouter d'autres cas de redirection si nécessaire
    }

    @And("l'icône du panier affiche {int} article")
    public void l_icone_du_panier_affiche_article(int expectedCount) {
        Assert.assertEquals(inventoryPage.getShoppingCartCount(), String.valueOf(expectedCount), "L'icône du panier n'affiche pas le bon nombre d'articles.");
    }

    @And("le produit {string} ne devrait plus être dans mon panier \\(bouton {string})")
    public void le_produit_ne_devrait_plus_etre_dans_mon_panier_bouton(String productName, String buttonText) {
        Assert.assertFalse(inventoryPage.isProductInCart(productName), "Le produit '" + productName + "' est toujours marqué comme dans le panier.");
        // Vérifier que le bouton "Add to cart" est visible
        WebElement productElement = Hooks.driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='inventory_item']"));
        WebElement addToCartButton = productElement.findElement(By.xpath(".//button[text()='Add to cart']"));
        Assert.assertTrue(addToCartButton.isDisplayed(), "Le bouton 'Add to cart' n'est pas visible pour " + productName);
    }
}