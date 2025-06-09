package com.saucedemo.qa.stepdefinitions;

import com.saucedemo.qa.pages.CheckoutPage;
import com.saucedemo.qa.pages.InventoryPage; // Pour la vérification du retour
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutSteps {

    private CheckoutPage checkoutPage;
    protected InventoryPage inventoryPage;

    public CheckoutSteps() {
        this.checkoutPage = new CheckoutPage(Hooks.driver);
        this.inventoryPage = new InventoryPage(Hooks.driver);
    }

    @And("je suis sur la page {string}")
    public void je_suis_sur_la_page(String pageTitle) {
        // Cette étape est réutilisée pour plusieurs pages de checkout
        Assert.assertEquals(checkoutPage.getPageTitleText(), pageTitle, "Le titre de la page ne correspond pas au '" + pageTitle + "'.");
    }

    @When("je saisis {string} dans le champ {string}")
    public void je_saisis_dans_le_champ(String value, String fieldName) {
        switch (fieldName) {
            case "First Name":
                checkoutPage.enterFirstName(value);
                break;
            case "Last Name":
                checkoutPage.enterLastName(value);
                break;
            case "Zip/Postal Code":
                checkoutPage.enterPostalCode(value);
                break;
            default:
                Assert.fail("Champ non reconnu dans CheckoutSteps: " + fieldName);
        }
    }

    @When("clique sur le bouton {string}")
    public void clique_sur_le_bouton(String buttonText) {
        if ("Continue".equalsIgnoreCase(buttonText)) {
            checkoutPage.clickContinueButton();
        } else if ("Finish".equalsIgnoreCase(buttonText)) {
            checkoutPage.clickFinishButton();
        } else if ("Back Home".equalsIgnoreCase(buttonText)) {
            // Cela devrait être géré par CheckoutCompleteSteps
        } else {
            Assert.fail("Bouton non reconnu dans CheckoutSteps: " + buttonText);
        }
    }

    @And("le {string} devrait être affiché dans le récapitulatif de la commande")
    public void le_devrait_etre_affiche_dans_le_recapitulatif_de_la_commande(String productName) {
        Assert.assertTrue(checkoutPage.isProductDisplayedInOverview(productName), "Le produit '" + productName + "' n'est pas affiché dans le récapitulatif.");
    }

    @Then("je devrais rester sur la page {string}")
    public void je_devrais_rester_sur_la_page(String expectedPage) {
        Assert.assertEquals(checkoutPage.getPageTitleText(), expectedPage, "Je ne suis pas resté sur la page " + expectedPage);
    }

    @And("j'ai passé les étapes de {string} et {string}")
    public void j_ai_passe_les_etapes_de_et(String page1, String page2) {
        // Pré-condition pour les scénarios de finalisation
        checkoutPage.enterFirstName("Test");
        checkoutPage.enterLastName("User");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinueButton();
        Assert.assertEquals(checkoutPage.getPageTitleText(), page2, "N'a pas atteint la page " + page2);
    }
}
