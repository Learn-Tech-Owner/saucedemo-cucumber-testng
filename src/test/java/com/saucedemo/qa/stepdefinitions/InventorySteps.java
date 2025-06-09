package com.saucedemo.qa.stepdefinitions;

import com.saucedemo.qa.pages.LoginPage;
import com.saucedemo.qa.pages.InventoryPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InventorySteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    public InventorySteps() {
        this.loginPage = new LoginPage(Hooks.driver);
        this.inventoryPage = new InventoryPage(Hooks.driver);
    }

    @Given("je suis connecté à SauceDemo en tant que {string}")
    public void je_suis_connecte_a_sauce_demo_en_tant_que(String username) {
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword("secret_sauce"); // Le mot de passe est toujours le même sur SauceDemo
        loginPage.clickLoginButton();
        Assert.assertTrue(inventoryPage.getCurrentUrl().contains("inventory.html"), "La connexion a échoué.");
    }

    @Then("je devrais voir {int} produits affichés sur la page")
    public void je_devrais_voir_produits_affiches_sur_la_page(int expectedCount) {
        Assert.assertEquals(inventoryPage.getNumberOfProducts(), expectedCount, "Le nombre de produits affichés ne correspond pas.");
    }

    @When("j'ajoute le produit {string} au panier")
    public void j_ajoute_le_produit_au_panier(String productName) {
        inventoryPage.addProductToCart(productName);
    }

    @Then("le produit {string} devrait être ajouté au panier")
    public void le_produit_devrait_etre_ajoute_au_panier(String productName) {
        Assert.assertTrue(inventoryPage.isProductInCart(productName), "Le produit '" + productName + "' n'est pas dans le panier.");
    }

    @Then("l'icône du panier devrait afficher {string} articles")
    public void l_icone_du_panier_devrait_afficher_articles(String expectedCount) {
        Assert.assertEquals(inventoryPage.getShoppingCartCount(), expectedCount, "Le nombre d'articles dans le panier ne correspond pas.");
    }

    @Given("le {string} est dans mon panier")
    public void le_est_dans_mon_panier(String productName) {
        inventoryPage.addProductToCart(productName);
        Assert.assertTrue(inventoryPage.isProductInCart(productName), "Le produit n'a pas pu être ajouté au panier pour le setup.");
    }

    @When("je retire le produit {string} du panier")
    public void je_retire_le_produit_du_panier(String productName) {
        inventoryPage.removeProductFromCart(productName);
    }

    @Then("le {string} ne devrait plus être dans mon panier")
    public void le_ne_devrait_plus_etre_dans_mon_panier(String productName) {
        Assert.assertFalse(inventoryPage.isProductInCart(productName), "Le produit '" + productName + "' est toujours dans le panier.");
    }

    @Then("l'icône du panier ne devrait pas afficher d'articles")
    public void l_icone_du_panier_ne_devrait_pas_afficher_d_articles() {
        Assert.assertEquals(inventoryPage.getShoppingCartCount(), "0", "L'icône du panier affiche des articles.");
    }

    @When("je trie les produits par {string}")
    public void je_trie_les_produits_par(String sortOption) {
        inventoryPage.selectSortOption(sortOption);
    }

    @Then("les produits devraient être triés par nom dans l'ordre croissant")
    public void les_produits_devraient_etre_tries_par_nom_dans_l_ordre_croissant() {
        List<String> actualNames = inventoryPage.getProductNames();
        List<String> expectedNames = actualNames.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(actualNames, expectedNames, "Les produits ne sont pas triés par nom A à Z.");
    }

    @Then("les produits devraient être triés par nom dans l'ordre décroissant")
    public void les_produits_devraient_etre_tries_par_nom_dans_l_ordre_decroissant() {
        List<String> actualNames = inventoryPage.getProductNames();
        List<String> expectedNames = actualNames.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assert.assertEquals(actualNames, expectedNames, "Les produits ne sont pas triés par nom Z à A.");
    }

    @Then("les produits devraient être triés par prix dans l'ordre croissant")
    public void les_produits_devraient_etre_tries_par_prix_dans_l_ordre_croissant() {
        List<Double> actualPrices = inventoryPage.getProductPrices();
        List<Double> expectedPrices = actualPrices.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(actualPrices, expectedPrices, "Les produits ne sont pas triés par prix (bas vers haut).");
    }

    @Then("les produits devraient être triés par prix dans l'ordre décroissant")
    public void les_produits_devraient_etre_tries_par_prix_dans_l_ordre_decroissant() {
        List<Double> actualPrices = inventoryPage.getProductPrices();
        List<Double> expectedPrices = actualPrices.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assert.assertEquals(actualPrices, expectedPrices, "Les produits ne sont pas triés par prix (haut vers bas).");
    }
}
