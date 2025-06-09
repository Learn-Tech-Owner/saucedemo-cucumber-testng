package com.saucedemo.qa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Chemin vers les fichiers .feature
        glue = "com.saucedemo.qa.stepdefinitions", // Chemin vers les Step Definitions
        plugin = {"pretty", // Sortie console lisible
                "html:target/cucumber-reports/cucumber-html-report.html", // Rapport HTML
                "json:target/cucumber-reports/cucumber.json", // Rapport JSON
                "rerun:target/cucumber-reports/rerun.txt"}, // Pour re-exécuter les tests qui ont échoué
        //monochrome = true, // Rend la console plus lisible
        tags = "@login" // Exécution des scénarios de fonctionnalité de gestion de panier d'achat
)
public class LoginTestRunner extends AbstractTestNGCucumberTests {}