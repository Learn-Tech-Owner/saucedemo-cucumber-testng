@login
Feature: Authentification des utilisateurs sur SauceDemo

  Scenario Outline: Connexion réussie avec des identifiants valides
    Given je suis sur la page de connexion de SauceDemo
    When je saisis "<username>" dans le champ nom d'utilisateur
    And je saisis "<password>" dans le champ mot de passe
    And je clique sur le bouton "Login"
    Then je devrais être redirigé vers la page "Products"
    And je devrais voir le titre de la page "Products"

    Examples:
      | username         | password     |
      | standard_user    | secret_sauce |
      | problem_user     | secret_sauce |
      | performance_glitch_user | secret_sauce |

  Scenario: Tentative de connexion avec un utilisateur verrouillé
    Given je suis sur la page de connexion de SauceDemo
    When je saisis "locked_out_user" dans le champ nom d'utilisateur
    And je saisis "secret_sauce" dans le champ mot de passe
    And je clique sur le bouton "Login"
    Then je devrais voir un message d'erreur "Epic sadface: Sorry, this user has been locked out."

  Scenario Outline: Tentative de connexion avec des identifiants invalides
    Given je suis sur la page de connexion de SauceDemo
    When je saisis "<username>" dans le champ nom d'utilisateur
    And je saisis "<password>" dans le champ mot de passe
    And je clique sur le bouton "Login"
    Then je devrais voir un message d'erreur "<error_message>"

    Examples:
      | username    | password     | error_message                          |
      | invalid_user | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | standard_user | wrong_sauce  | Epic sadface: Username and password do not match any user in this service |
      |             | secret_sauce | Epic sadface: Username is required     |
      | standard_user |              | Epic sadface: Password is required     |