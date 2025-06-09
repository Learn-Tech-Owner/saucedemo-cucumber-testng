Feature: Processus de paiement (Checkout)

  Background:
    Given je suis connecté à SauceDemo en tant que "standard_user"
    And j'ai ajouté le "Sauce Labs Backpack" au panier
    And je suis sur la page du panier
    And je clique sur le bouton "Checkout"
    And je suis sur la page "Checkout: Your Information"

  Scenario: Remplir les informations de livraison et continuer
    When je saisis "Jane" dans le champ "First Name"
    And je saisis "Doe" dans le champ "Last Name"
    And je saisis "90210" dans le champ "Zip/Postal Code"
    And je clique sur le bouton "Continue"
    Then je devrais être redirigé vers la page "Checkout: Overview"
    And le "Sauce Labs Backpack" devrait être affiché dans le récapitulatif de la commande

  Scenario: Tentative de continuer le paiement sans informations de livraison
    When je clique sur le bouton "Continue"
    Then je devrais rester sur la page "Checkout: Your Information"
    And je devrais voir un message d'erreur "Error: First Name is required"

  Scenario: Tentative de continuer le paiement avec un nom de famille manquant
    When je saisis "John" dans le champ "First Name"
    And je saisis "" dans le champ "Last Name"
    And je saisis "12345" dans le champ "Zip/Postal Code"
    And clique sur le bouton "Continue"
    Then je devrais voir un message d'erreur "Error: Last Name is required"

  Scenario: Tentative de continuer le paiement avec un code postal manquant
    When je saisis "John" dans le champ "First Name"
    And je saisis "Doe" dans le champ "Last Name"
    And je saisis "" dans le champ "Zip/Postal Code"
    And clique sur le bouton "Continue"
    Then je devrais voir un message d'erreur "Error: Postal Code is required"