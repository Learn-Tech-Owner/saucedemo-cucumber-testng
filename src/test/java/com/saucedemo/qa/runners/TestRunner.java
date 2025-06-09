package com.saucedemo.qa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features", // Chemin vers les fichiers .feature
        glue = "com.saucedemo.qa.stepdefinitions", // Chemin vers les Step Definitions
        plugin = {"pretty", // Sortie console lisible
                "html:target/cucumber-reports/cucumber-html-report.html", // Rapport HTML
                "json:target/cucumber-reports/cucumber.json", // Rapport JSON
                "rerun:target/cucumber-reports/rerun.txt"}, // Pour re-exécuter les tests qui ont échoué
        monochrome = true, // Rend la console plus lisible
        tags = "@login" // Vous pouvez utiliser des tags pour exécuter des scénarios spécifiques
)
public class TestRunner extends AbstractTestNGCucumberTests {

    // Pour l'exécution parallèle des scénarios (optionnel, mais recommandé pour les grandes suites)
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}