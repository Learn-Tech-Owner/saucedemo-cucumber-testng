Feature: Gestion des produits sur la page d'inventaire

  Background:
    Given je suis connecté à SauceDemo en tant que "standard_user"

  Scenario: Affichage de tous les produits
    Then je devrais voir 6 produits affichés sur la page

  Scenario Outline: Ajout d'un produit au panier depuis la page des produits
    When j'ajoute le produit "<product_name>" au panier
    Then le produit "<product_name>" devrait être ajouté au panier
    And l'icône du panier devrait afficher "<cart_count>" articles

    Examples:
      | product_name           | cart_count |
      | Sauce Labs Backpack    | 1          |
      | Sauce Labs Bike Light  | 2          |
      | Sauce Labs Bolt T-Shirt | 3          |

  Scenario: Retrait d'un produit du panier depuis la page des produits
    Given le "Sauce Labs Backpack" est dans mon panier
    When je retire le produit "Sauce Labs Backpack" du panier
    Then le "Sauce Labs Backpack" ne devrait plus être dans mon panier
    And l'icône du panier ne devrait pas afficher d'articles

  Scenario: Tri des produits par nom (A à Z)
    When je trie les produits par "Name (A to Z)"
    Then les produits devraient être triés par nom dans l'ordre croissant

  Scenario: Tri des produits par nom (Z à A)
    When je trie les produits par "Name (Z to A)"
    Then les produits devraient être triés par nom dans l'ordre décroissant

  Scenario: Tri des produits par prix (Bas vers Haut)
    When je trie les produits par "Price (low to high)"
    Then les produits devraient être triés par prix dans l'ordre croissant

  Scenario: Tri des produits par prix (Haut vers Bas)
    When je trie les produits par "Price (high to low)"
    Then les produits devraient être triés par prix dans l'ordre décroissant