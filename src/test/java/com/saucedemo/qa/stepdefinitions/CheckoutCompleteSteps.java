package com.saucedemo.qa.stepdefinitions;

import com.saucedemo.qa.pages.CheckoutCompletePage;
import com.saucedemo.qa.pages.InventoryPage; // Pour la vérification du retour
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutCompleteSteps {

    private CheckoutCompletePage checkoutCompletePage;
    private InventoryPage inventoryPage; // Pour vérifier le retour à la page Products

    public CheckoutCompleteSteps() {
        this.checkoutCompletePage = new CheckoutCompletePage(Hooks.driver);
        this.inventoryPage = new InventoryPage(Hooks.driver);
    }

    @Then("redirection vers la page {string}")
    public void redirection_vers_la_page(String pageName) {
        if ("Checkout: Complete!".equalsIgnoreCase(pageName)) {
            Assert.assertTrue(checkoutCompletePage.getCurrentUrl().contains("checkout-complete.html"), "Non redirigé vers la page de confirmation.");
            Assert.assertEquals(checkoutCompletePage.getPageTitleText(), pageName, "Le titre de la page ne correspond pas.");
        } else if ("Products".equalsIgnoreCase(pageName)) {
            Assert.assertTrue(inventoryPage.getCurrentUrl().contains("inventory.html"), "Non redirigé vers la page Products.");
            Assert.assertEquals(inventoryPage.getPageTitleText(), pageName, "Le titre de la page ne correspond pas.");
        } else {
            Assert.fail("Redirection vers une page non gérée dans CheckoutCompleteSteps: " + pageName);
        }
    }

    @Then("je devrais voir le message {string}")
    public void je_devrais_voir_le_message(String expectedHeader) {
        Assert.assertEquals(checkoutCompletePage.getCompleteHeaderText(), expectedHeader, "Le message du header ne correspond pas.");
    }

    @Then("je devrais voir le texte {string}")
    public void je_devrais_voir_le_texte(String expectedText) {
        Assert.assertEquals(checkoutCompletePage.getCompleteText(), expectedText, "Le texte de confirmation ne correspond pas.");
    }

    @When("je clique sur le bouton suivant {string}")
    public void je_clique_sur_le_bouton_suivant(String buttonText) {
        if ("Back Home".equalsIgnoreCase(buttonText)) {
            checkoutCompletePage.clickBackHomeButton();
        } else {
            Assert.fail("Bouton non reconnu dans CheckoutCompleteSteps: " + buttonText);
        }
    }
}