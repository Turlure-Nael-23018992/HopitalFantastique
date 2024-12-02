package tp4.main;

public class Main {
    public static void main(String[] args) {
        // === Initialisation de la colonie ===
        Colonie colonie = new Colonie();

        // === Création de deux meutes ===
        Meute meute1 = new Meute();
        Meute meute2 = new Meute();

        // === Ajout de lycanthropes à la première meute ===
        meute1.ajouterLycanthrope(new Lycanthrope("Fenrir", Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.ADULTE, 50, 10, 1, 0.4, "Meute1"));
        meute1.ajouterLycanthrope(new Lycanthrope("Luna", Lycanthrope.Sexe.FEMELLE, Lycanthrope.CategorieAge.ADULTE, 45, 15, 2, 0.5, "Meute1"));
        meute1.ajouterLycanthrope(new Lycanthrope("Balto", Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.JEUNE, 30, 5, 3, 0.2, "Meute1"));

        // === Ajout de lycanthropes à la deuxième meute ===
        meute2.ajouterLycanthrope(new Lycanthrope("Selene", Lycanthrope.Sexe.FEMELLE, Lycanthrope.CategorieAge.ADULTE, 55, 12, 1, 0.6, "Meute2"));
        meute2.ajouterLycanthrope(new Lycanthrope("Ragnar", Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.VIEUX, 40, 8, 2, 0.3, "Meute2"));

        // === Ajout des meutes à la colonie ===
        colonie.ajouterMeute(meute1);
        colonie.ajouterMeute(meute2);

        // === Définition des couples α ===
        meute1.definirCoupleAlpha(meute1.getLycanthropes().get(0), meute1.getLycanthropes().get(1)); // Fenrir et Luna
        meute2.definirCoupleAlpha(meute2.getLycanthropes().get(1), meute2.getLycanthropes().get(0)); // Ragnar et Selene

        // === Affichage initial de la colonie ===
        System.out.println("\n=== État initial de la colonie ===");
        colonie.afficherLycanthropes();

        // === Simulation sur plusieurs intervalles temporels ===
        System.out.println("\n=== Simulation temporelle ===");
        for (int intervalle = 1; intervalle <= 5; intervalle++) {
            System.out.println("\n--- Intervalle temporel " + intervalle + " ---");
            colonie.gererEvenements();
            colonie.afficherLycanthropes();
        }

        // === Scénarios de domination ===
        System.out.println("\n=== Scénarios de domination ===");
        Lycanthrope fenrir = meute1.getLycanthropes().get(0); // Fenrir
        Lycanthrope luna = meute1.getLycanthropes().get(1);   // Luna

        // Fenrir tente de dominer Luna
        System.out.println("\n--- Tentative de domination de Fenrir sur Luna ---");
        fenrir.tenterDomination(luna);

        // Ragnar tente de dominer Selene
        System.out.println("\n--- Tentative de domination de Ragnar sur Selene ---");
        Lycanthrope ragnar = meute2.getLycanthropes().get(1);
        Lycanthrope selene = meute2.getLycanthropes().get(0);
        ragnar.tenterDomination(selene);

        // === Scénario de hurlements ===
        System.out.println("\n=== Scénario de hurlements ===");
        luna.hurler("Appel à la meute !");
        meute1.getLycanthropes().forEach(lycan -> {
            if (!lycan.equals(luna)) {
                lycan.repondreHurlement(new Hurlement(luna, Hurlement.TypeHurlement.MEUTE, "Appel à la meute !"));
            }
        });

        // === Scénario de transformation en humain ===
        System.out.println("\n=== Scénario de transformation en humain ===");
        System.out.println("--- Fenrir tente de se transformer en humain ---");
        meute1.gererTransformation(fenrir);

        // === Saison des amours ===
        System.out.println("\n=== Saison des amours ===");
        meute1.lancerReproduction();

        // === État final de la colonie ===
        System.out.println("\n=== État final de la colonie ===");
        colonie.afficherLycanthropes();
    }
}