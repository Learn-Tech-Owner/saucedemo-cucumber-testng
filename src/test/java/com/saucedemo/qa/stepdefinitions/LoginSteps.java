package com.saucedemo.qa.stepdefinitions;

import com.saucedemo.qa.pages.LoginPage;
import com.saucedemo.qa.pages.InventoryPage; // Importez la page d'inventaire car la redirection est vers elle
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage; // Pour vérifier la redirection

    public LoginSteps() {
        this.loginPage = new LoginPage(Hooks.driver);
        this.inventoryPage = new InventoryPage(Hooks.driver);
    }

    @Given("je suis sur la page de connexion de SauceDemo")
    public void je_suis_sur_la_page_de_connexion_de_sauce_demo() {
        loginPage.navigateToLoginPage();
    }

    @When("je saisis {string} dans le champ nom d'utilisateur")
    public void je_saisis_dans_le_champ_nom_utilisateur(String username) {
        loginPage.enterUsername(username);
    }

    @When("je saisis {string} dans le champ mot de passe")
    public void je_saisis_dans_le_champ_mot_de_passe(String password) {
        loginPage.enterPassword(password);
    }

    @When("je clique sur le bouton {string}")
    public void je_clique_sur_le_bouton(String buttonText) {
        if ("Login".equalsIgnoreCase(buttonText)) {
            loginPage.clickLoginButton();
        } else {
            Assert.fail("Bouton non reconnu dans LoginSteps: " + buttonText);
        }
    }

    @Then("je devrais être redirigé vers la page {string}")
    public void je_devrais_etre_redirige_vers_la_page(String pageName) {
        if ("Products".equalsIgnoreCase(pageName)) {
            Assert.assertTrue(inventoryPage.getCurrentUrl().contains("inventory.html"), "Non redirigé vers la page Products");
        } else {
            Assert.fail("Redirection vers une page non gérée dans LoginSteps: " + pageName);
        }
    }

    @Then("je devrais voir le titre de la page {string}")
    public void je_devrais_voir_le_titre_de_la_page(String expectedTitle) {
        Assert.assertEquals(inventoryPage.getPageTitleText(), expectedTitle, "Le titre de la page n'est pas correct.");
    }

    @Then("je devrais voir un message d'erreur {string}")
    public void je_devrais_voir_un_message_d_erreur(String expectedErrorMessage) {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Le message d'erreur n'est pas affiché.");
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "Le message d'erreur ne correspond pas.");
    }
}