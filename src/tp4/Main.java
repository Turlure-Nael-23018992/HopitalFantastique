/*package tp4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // === Création de la colonie ===
        Colonie colonie = new Colonie();

        // === Création de deux meutes ===
        Meute meute1 = new Meute();
        Meute meute2 = new Meute();

        // === Ajout de lycanthropes à la première meute ===
        meute1.ajouterLycanthrope(new Lycanthrope("Fenrir", Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.ADULTE, 50, 10, 2, 0.5, "Meute1"));
        meute1.ajouterLycanthrope(new Lycanthrope("Luna", Lycanthrope.Sexe.FEMELLE, Lycanthrope.CategorieAge.ADULTE, 45, 15, 1, 0.4, "Meute1"));
        meute1.ajouterLycanthrope(new Lycanthrope("Balto", Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.JEUNE, 30, 5, 3, 0.3, "Meute1"));

        // === Ajout de lycanthropes à la deuxième meute ===
        meute2.ajouterLycanthrope(new Lycanthrope("Selene", Lycanthrope.Sexe.FEMELLE, Lycanthrope.CategorieAge.ADULTE, 60, 20, 1, 0.7, "Meute2"));
        meute2.ajouterLycanthrope(new Lycanthrope("Ragnar", Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.VIEUX, 40, 8, 2, 0.2, "Meute2"));

        // === Ajout des meutes à la colonie ===
        colonie.ajouterMeute(meute1);
        colonie.ajouterMeute(meute2);

        // === Affichage initial des lycanthropes dans la colonie ===
        System.out.println("\n=== Affichage initial des lycanthropes ===");
        colonie.afficherLycanthropes();

        // === Définition du couple α dans chaque meute ===
        System.out.println("\n=== Définition des couples α ===");
        meute1.definirCoupleAlpha(meute1.getLycanthropes().get(0), meute1.getLycanthropes().get(1)); // Fenrir et Luna
        meute2.definirCoupleAlpha(meute2.getLycanthropes().get(1), meute2.getLycanthropes().get(0)); // Ragnar et Selene

        // === Simulation des événements temporels ===
        System.out.println("\n=== Début de la simulation temporelle ===");
        for (int intervalle = 1; intervalle <= 5; intervalle++) {
            System.out.println("\n--- Intervalle temporel " + intervalle + " ---");
            colonie.gererEvenements();
            colonie.afficherLycanthropes();
        }

        // === Test manuel des fonctionnalités ===
        System.out.println("\n=== Test manuel des fonctionnalités ===");

        // Tentative de domination
        System.out.println("\n--- Tentative de domination ---");
        Lycanthrope fenrir = meute1.getLycanthropes().get(0); // Récupère Fenrir
        Lycanthrope luna = meute1.getLycanthropes().get(1);   // Récupère Luna
        fenrir.tenterDomination(luna); // Fenrir tente de dominer Luna

        // Hurlements
        System.out.println("\n--- Gestion des hurlements ---");
        Hurlement hurlementMeute = fenrir.emettreHurlement(Hurlement.TypeHurlement.MEUTE, "Unissons nos forces !");
        meute1.propagerHurlement(hurlementMeute);

        // Transformation en humain
        System.out.println("\n--- Transformation en humain ---");
        meute1.gererTransformation(fenrir);

        // Reproduction
        System.out.println("\n--- Reproduction ---");
        meute2.lancerReproduction();

        // Vieillissement
        System.out.println("\n--- Vieillissement manuel des lycanthropes ---");
        for (Lycanthrope lycan : meute2.getLycanthropes()) {
            lycan.vieillir();
        }

        // Nouvelle hiérarchie après suppression de lycanthropes
        System.out.println("\n--- Suppression de lycanthropes et réorganisation ---");
        meute1.enleverLycanthrope(luna);
        meute1.creerNouvelleHierarchie(meute1.getLycanthropes());

        // === Affichage final des lycanthropes ===
        System.out.println("\n=== Affichage final des lycanthropes ===");
        colonie.afficherLycanthropes();
    }
}*/