@cart
Feature: Gestion du panier d'achat

  Background:
    Given je suis connecté à SauceDemo en tant que "standard_user"
    And j'ai ajouté le "Sauce Labs Backpack" au panier
    And j'ai ajouté le "Sauce Labs Bike Light" au panier
    And je suis sur la page du panier

  Scenario: Vérification des articles dans le panier
    Then je devrais voir le "Sauce Labs Backpack" dans mon panier
    And je devrais voir le "Sauce Labs Bike Light" dans mon panier
    And mon panier devrait contenir 2 articles

  Scenario: Retrait d'un article du panier
    When je clique sur le bouton "Remove" pour "Sauce Labs Backpack"
    Then le "Sauce Labs Backpack" ne devrait plus être dans mon panier

  Scenario: Continuer les achats depuis le panier
    When je clique sur le bouton pour "Continue Shopping"
    Then je devrais être redirigé vers la page "Products"

  Scenario: Procéder au paiement depuis le panier
    When je clique sur le bouton pour "Checkout"
    Then je devrais être redirigé vers la page "Checkout: Your Information"