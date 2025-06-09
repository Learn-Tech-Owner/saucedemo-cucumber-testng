Feature: Finalisation de la commande

  Background:
    Given je suis connecté à SauceDemo en tant que "standard_user"
    And j'ai ajouté le "Sauce Labs Backpack" au panier
    And j'ai passé les étapes de "Checkout: Your Information" et "Checkout: Overview"
    And je suis sur la page "Checkout: Overview"

  Scenario: Finalisation réussie de la commande
    When je clique sur le bouton "Finish"
    Then redirection vers la page "Checkout: Complete!"
    And je devrais voir le message "Thank you for your order!"
    And je devrais voir le texte "Your order has been dispatched, and will arrive just as fast as the pony can get there!"

  Scenario: Retour à la page d'accueil après finalisation
    When je clique sur le bouton suivant "Back Home"
    Then redirection vers la page "Products"