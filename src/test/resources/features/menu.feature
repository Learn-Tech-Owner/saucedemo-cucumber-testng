Feature: Navigation via le menu burger

  Background:
    Given je suis connecté à SauceDemo en tant que "standard_user"
    And je suis sur la page "Products"

  Scenario: Ouvrir et fermer le menu burger
    When je clique sur l'icône du menu burger
    Then le menu burger devrait être ouvert
    When je clique sur le bouton "Close Menu" au niveau du menu
    Then le menu burger devrait être fermé

  Scenario: Accéder à la page "All Items" via le menu
    When je clique sur l'icône du menu burger
    And je clique sur le lien "All Items"
    Then je devrais être redirigé vers la page "Products" du menu

  Scenario: Accéder à la page "About" via le menu
    When je clique sur l'icône du menu burger
    And je clique sur le lien "About"
    Then je devrais être redirigé vers la page "Sauce Labs" du menu

  Scenario: Réinitialiser l'état de l'application via le menu
    Given j'ai ajouté le "Sauce Labs Backpack" au panier
    And l'icône du panier affiche 1 article
    When je clique sur l'icône du menu burger
    And je clique sur le lien "Reset App State"
    Then l'icône du panier ne devrait pas afficher d'articles
    And le produit "Sauce Labs Backpack" ne devrait plus être dans mon panier (bouton "Add to cart")

  Scenario: Déconnexion via le menu
    When je clique sur l'icône du menu burger
    And je clique sur le lien "Logout"