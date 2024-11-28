package tp4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Création de lycanthropes
        Lycanthrope lycan1 = new Lycanthrope("Fenrir", Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.ADULTE, 50, 10, 2, 0.5, "solitaire");
        Lycanthrope lycan2 = new Lycanthrope("Luna", Lycanthrope.Sexe.FEMELLE, Lycanthrope.CategorieAge.ADULTE, 45, 15, 1, 0.4, "solitaire");
        Lycanthrope lycan3 = new Lycanthrope("Balto", Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.JEUNE, 30, 5, 3, 0.3, "solitaire");
        Lycanthrope lycan4 = new Lycanthrope("Selene", Lycanthrope.Sexe.FEMELLE, Lycanthrope.CategorieAge.ADULTE, 60, 20, 1, 0.7, "solitaire");
        Lycanthrope lycan5 = new Lycanthrope("Ragnar", Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.VIEUX, 40, 8, 2, 0.2, "solitaire");

        // Création d'une meute
        Meute meute = new Meute();

        // Ajout des lycanthropes à la meute
        System.out.println("\n=== Ajout de lycanthropes ===");
        meute.ajouterLycanthrope(lycan1);
        meute.ajouterLycanthrope(lycan2);
        meute.ajouterLycanthrope(lycan3);
        meute.ajouterLycanthrope(lycan4);
        meute.ajouterLycanthrope(lycan5);

        // Affichage des caractéristiques de la meute
        System.out.println("\n=== Caractéristiques de la meute ===");
        meute.afficherCaracteristiques();

        // Affichage des lycanthropes
        System.out.println("\n=== Liste des lycanthropes ===");
        meute.afficherLycanthropes();

        // Définition d'un couple α
        System.out.println("\n=== Définition d'un couple α ===");
        meute.definirCoupleAlpha(lycan1, lycan2); // Fenrir et Luna

        // Lancer une reproduction
        System.out.println("\n=== Lancer une reproduction ===");
        meute.lancerReproduction();

        // Afficher la liste des lycanthropes après reproduction
        System.out.println("\n=== Liste des lycanthropes après reproduction ===");
        meute.afficherLycanthropes();

        // Tentative de domination
        System.out.println("\n=== Tentative de domination ===");
        lycan3.tenterDomination(lycan1); // Balto tente de dominer Fenrir
        lycan1.tenterDomination(lycan4); // Fenrir tente de dominer Selene

        // Déclarer un lycanthrope ω
        System.out.println("\n=== Déclarer un lycanthrope ω ===");
        meute.declarerOmega(lycan3);

        // Diminuer les rangs de domination
        System.out.println("\n=== Diminuer les rangs de domination ===");
        meute.diminuerRangsDomination();

        // Créer une nouvelle hiérarchie avec les lycanthropes restants
        System.out.println("\n=== Créer une nouvelle hiérarchie ===");
        meute.creerNouvelleHierarchie(List.of(lycan1, lycan2, lycan4));

        // Affichage des caractéristiques de la meute après hiérarchie
        System.out.println("\n=== Caractéristiques de la meute après nouvelle hiérarchie ===");
        meute.afficherCaracteristiques();

        // Supprimer un lycanthrope de la meute
        System.out.println("\n=== Supprimer un lycanthrope ===");
        meute.enleverLycanthrope(lycan1); // Retirer Fenrir de la meute

        // Affichage final de la meute
        System.out.println("\n=== Caractéristiques finales de la meute ===");
        meute.afficherCaracteristiques();
        meute.afficherLycanthropes();

        // Gestion des hurlements
        System.out.println("\n=== Gestion des hurlements ===");

        // Fenrir émet un hurlement de meute
        Hurlement hurlementMeute = lycan1.emettreHurlement(Hurlement.TypeHurlement.MEUTE, "Unissons nos forces !");
        meute.propagerHurlement(hurlementMeute);

        // Selene émet un hurlement de domination
        Hurlement hurlementDomination = lycan4.emettreHurlement(Hurlement.TypeHurlement.DOMINATION, "Je suis la plus forte !");
        meute.propagerHurlement(hurlementDomination);

        // Balto émet un hurlement de soumission
        Hurlement hurlementSoumission = lycan3.emettreHurlement(Hurlement.TypeHurlement.SOUMISSION, "Je me rends...");
        meute.propagerHurlement(hurlementSoumission);

        // Luna émet un hurlement d'agressivité
        Hurlement hurlementAgressivite = lycan2.emettreHurlement(Hurlement.TypeHurlement.AGRESSIVITE, "Grrr... Tenez-vous à distance !");
        meute.propagerHurlement(hurlementAgressivite);

        // Transformation individuelle
        System.out.println("\n=== Transformation individuelle ===");
        meute.gererTransformation(lycan4); // Selene tente de se transformer

        // Affichage après transformation individuelle
        System.out.println("\n=== Meute après transformation individuelle ===");
        meute.afficherCaracteristiques();
        meute.afficherLycanthropes();

        // Transformation collective
        System.out.println("\n=== Transformation collective ===");
        meute.transformerMeute();

        // Affichage après transformation collective
        System.out.println("\n=== Meute après transformation collective ===");
        meute.afficherCaracteristiques();
        meute.afficherLycanthropes();
    }
}