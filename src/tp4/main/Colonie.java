package tp4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Représente une colonie de lycanthropes composée de plusieurs meutes.
 * Gère les événements temporels et les interactions globales.
 */
public class Colonie {

    private final List<Meute> meutes; // Liste des meutes de la colonie.
    private final Random random;     // Générateur aléatoire pour les événements.

    /**
     * Constructeur de la classe Colonie.
     * Initialise une colonie vide.
     */
    public Colonie() {
        this.meutes = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Ajoute une nouvelle meute à la colonie.
     *
     * @param meute La meute à ajouter.
     */
    public void ajouterMeute(Meute meute) {
        meutes.add(meute);
        System.out.println("Nouvelle meute ajoutée à la colonie.");
    }

    /**
     * Affiche tous les lycanthropes de toutes les meutes de la colonie.
     */
    public void afficherLycanthropes() {
        System.out.println("\n=== Lycanthropes dans la colonie ===");
        for (Meute meute : meutes) {
            meute.afficherLycanthropes();
        }
    }

    /**
     * Gère les événements temporels de la colonie.
     * Inclut la création de nouvelles meutes, la reproduction, l'évolution des hiérarchies,
     * le vieillissement et les transformations en humains.
     */
    public void gererEvenements() {
        System.out.println("\n=== Début d'un nouvel intervalle temporel ===");

        // Déterminer si une nouvelle meute doit être créée (50% de chances).
        if (random.nextBoolean()) {
            creerNouvelleMeute();
        }

        // Déterminer si la saison des amours est arrivée (30% de chances).
        if (random.nextInt(10) < 3) {
            lancerReproduction();
        }

        // Faire évoluer les hiérarchies de chaque meute.
        evoluerHierarchies();

        // Faire vieillir certains lycanthropes.
        vieillirLycanthropes();

        // Générer des hurlements aléatoires.
        genererHurlements();

        // Transformer quelques lycanthropes en humains.
        transformerLycanthropes();
    }

    /**
     * Crée une nouvelle meute avec des lycanthropes solitaires.
     */
    private void creerNouvelleMeute() {
        System.out.println("\n=== Création d'une nouvelle meute ===");
        Meute nouvelleMeute = new Meute();
        for (Meute meute : meutes) {
            List<Lycanthrope> lycanthropes = new ArrayList<>(meute.getLycanthropes());
            for (Lycanthrope lycan : lycanthropes) {
                if (lycan.isSolitaire() && random.nextBoolean()) { // 50% de chances.
                    nouvelleMeute.ajouterLycanthrope(lycan);
                    meute.enleverLycanthrope(lycan);
                }
            }
        }
        if (!nouvelleMeute.getLycanthropes().isEmpty()) {
            ajouterMeute(nouvelleMeute);
        }
    }

    /**
     * Lance la reproduction dans chaque meute de la colonie.
     */
    private void lancerReproduction() {
        System.out.println("\n=== Lancement de la saison des amours ===");
        for (Meute meute : meutes) {
            meute.lancerReproduction();
        }
    }

    /**
     * Fait évoluer la hiérarchie dans chaque meute de la colonie.
     */
    private void evoluerHierarchies() {
        System.out.println("\n=== Évolution des hiérarchies ===");
        for (Meute meute : meutes) {
            meute.diminuerRangsDomination();
        }
    }

    /**
     * Fait vieillir certains lycanthropes de la colonie.
     */
    private void vieillirLycanthropes() {
        System.out.println("\n=== Vieillissement des lycanthropes ===");
        for (Meute meute : meutes) {
            for (Lycanthrope lycan : meute.getLycanthropes()) {
                if (random.nextBoolean()) { // 50% de chances de vieillir.
                    lycan.vieillir();
                }
            }
        }
    }

    /**
     * Génère des hurlements aléatoires dans la colonie.
     */
    private void genererHurlements() {
        System.out.println("\n=== Génération de hurlements ===");
        for (Meute meute : meutes) {
            List<Lycanthrope> lycanthropes = new ArrayList<>(meute.getLycanthropes());
            if (lycanthropes.size() >= 2) {
                Lycanthrope emetteur = lycanthropes.get(random.nextInt(lycanthropes.size()));
                Hurlement.TypeHurlement type = Hurlement.TypeHurlement.values()[random.nextInt(Hurlement.TypeHurlement.values().length)];
                String message = "Hurlement aléatoire de " + emetteur.getNom();
                Hurlement hurlement = new Hurlement(emetteur, type, message);
                meute.propagerHurlement(hurlement);
            }
        }
    }

    /**
     * Transforme certains lycanthropes en humains (20% de chances par lycanthrope).
     */
    private void transformerLycanthropes() {
        System.out.println("\n=== Transformation de lycanthropes en humains ===");
        for (Meute meute : meutes) {
            List<Lycanthrope> lycanthropes = new ArrayList<>(meute.getLycanthropes());
            for (Lycanthrope lycan : lycanthropes) {
                if (random.nextInt(10) < 2) { // 20% de chances.
                    meute.gererTransformation(lycan);
                }
            }
        }
    }
}