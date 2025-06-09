package com.saucedemo.qa.stepdefinitions;

import com.saucedemo.qa.pages.CartPage;
import com.saucedemo.qa.pages.InventoryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CartSteps {

    private InventoryPage inventoryPage;
    private CartPage cartPage;

    public CartSteps() {
        this.inventoryPage = new InventoryPage(Hooks.driver);
        this.cartPage = new CartPage(Hooks.driver);
    }

    @And("j'ai ajouté le {string} au panier")
    public void j_ai_ajoute_le_au_panier(String productName) {
        inventoryPage.addProductToCart(productName);
        Assert.assertTrue(inventoryPage.isProductInCart(productName), "Le produit '" + productName + "' n'a pas pu être ajouté au panier.");
    }

    @And("je suis sur la page du panier")
    public void je_suis_sur_la_page_du_panier() {
        Hooks.driver.findElement(inventoryPage.shoppingCartIcon).click();
        Assert.assertTrue(cartPage.getCurrentUrl().contains("cart.html"), "Non redirigé vers la page du panier.");
        Assert.assertEquals(cartPage.getPageTitleText(), "Your Cart", "Le titre de la page du panier n'est pas correct.");
    }

    @Then("je devrais voir le {string} dans mon panier")
    public void je_devrais_voir_le_dans_mon_panier(String productName) {
        Assert.assertTrue(cartPage.isProductDisplayedInCart(productName), "Le produit '" + productName + "' n'est pas affiché dans le panier.");
    }

    @And("mon panier devrait contenir {int} articles")
    public void mon_panier_devrait_contenir_articles(int expectedCount) {
        Assert.assertEquals(cartPage.getNumberOfItemsInCart(), expectedCount, "Le nombre d'articles dans le panier ne correspond pas.");
    }

    @When("je clique sur le bouton {string} pour {string}")
    public void je_clique_sur_le_bouton_pour(String buttonText, String productName) {
        if ("Remove".equalsIgnoreCase(buttonText)) {
            cartPage.clickRemoveButton(productName);
        } else {
            Assert.fail("Bouton non reconnu dans CartSteps: " + buttonText);
        }
    }

    @When("je clique sur le bouton pour {string}")
    public void je_clique_sur_le_bouton_pour(String buttonText) {
        if ("Continue Shopping".equalsIgnoreCase(buttonText)) {
            cartPage.clickContinueShopping();
        } else if ("Checkout".equalsIgnoreCase(buttonText)) {
            cartPage.clickCheckout();
        } else {
            Assert.fail("Bouton non reconnu dans CartSteps: " + buttonText);
        }
    }
}
