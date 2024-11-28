/*package tp4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Colonie {
    private List<Meute> meutes; // Liste des meutes de la colonie
    private Random random;

    // Constructeur
    public Colonie() {
        this.meutes = new ArrayList<>();
        this.random = new Random();
    }

    // Ajouter une meute à la colonie
    public void ajouterMeute(Meute meute) {
        this.meutes.add(meute);
        System.out.println("Nouvelle meute ajoutée à la colonie.");
    }

    // Afficher tous les lycanthropes de toutes les meutes
    public void afficherLycanthropes() {
        System.out.println("\n=== Lycanthropes dans la colonie ===");
        for (Meute meute : meutes) {
            meute.afficherLycanthropes();
        }
    }

    // Gérer les événements temporels de la colonie
    public void gererEvenements() {
        System.out.println("\n=== Début d'un nouvel intervalle temporel ===");

        // Déterminer si une nouvelle meute doit être créée
        if (random.nextBoolean()) { // 50 % de chances
            creerNouvelleMeute();
        }

        // Déterminer si la saison des amours est arrivée
        if (random.nextInt(10) < 3) { // 30 % de chances
            lancerReproduction();
        }

        // Faire évoluer la hiérarchie des meutes
        evoluerHierarchies();

        // Faire vieillir certains lycanthropes
        vieillirLycanthropes();

        // Générer des hurlements aléatoires
        genererHurlements();

        // Transformer quelques lycanthropes en humains
        transformerLycanthropes();
    }

    // Créer une nouvelle meute avec des lycanthropes solitaires
    private void creerNouvelleMeute() {
        System.out.println("\n=== Création d'une nouvelle meute ===");
        Meute nouvelleMeute = new Meute();
        for (Meute meute : meutes) {
            List<Lycanthrope> lycanthropes = new ArrayList<>(meute.getLycanthropes());
            for (Lycanthrope lycan : lycanthropes) {
                if (lycan.isSolitaire() && random.nextBoolean()) { // 50 % de chances
                    nouvelleMeute.ajouterLycanthrope(lycan);
                    meute.enleverLycanthrope(lycan);
                }
            }
        }
        if (!nouvelleMeute.getLycanthropes().isEmpty()) {
            ajouterMeute(nouvelleMeute);
        }
    }

    // Lancer la reproduction dans chaque meute
    private void lancerReproduction() {
        System.out.println("\n=== Lancement de la saison des amours ===");
        for (Meute meute : meutes) {
            meute.lancerReproduction();
        }
    }

    // Faire évoluer la hiérarchie dans chaque meute
    private void evoluerHierarchies() {
        System.out.println("\n=== Évolution des hiérarchies ===");
        for (Meute meute : meutes) {
            meute.diminuerRangsDomination();
        }
    }

    // Faire vieillir certains lycanthropes
    private void vieillirLycanthropes() {
        System.out.println("\n=== Vieillissement des lycanthropes ===");
        for (Meute meute : meutes) {
            for (Lycanthrope lycan : meute.getLycanthropes()) {
                if (random.nextBoolean()) { // 50 % de chances de vieillir
                    lycan.vieillir();
                }
            }
        }
    }

    // Générer des hurlements aléatoires
    private void genererHurlements() {
        System.out.println("\n=== Génération de hurlements ===");
        for (Meute meute : meutes) {
            List<Lycanthrope> lycanthropes = new ArrayList<>(meute.getLycanthropes());
            if (lycanthropes.size() >= 2) {
                Lycanthrope emetteur = lycanthropes.get(random.nextInt(lycanthropes.size()));
                Hurlement.TypeHurlement type = Hurlement.TypeHurlement.values()[random.nextInt(Hurlement.TypeHurlement.values().length)];
                String message = "Hurlement aléatoire de " + emetteur.getNom();
                //Hurlement hurlement = emetteur.emettreHurlement(type, message);
                //meute.propagerHurlement(hurlement);
            }
        }
    }

    // Transformer quelques lycanthropes en humains
    private void transformerLycanthropes() {
        System.out.println("\n=== Transformation de lycanthropes en humains ===");
        for (Meute meute : meutes) {
            List<Lycanthrope> lycanthropes = new ArrayList<>(meute.getLycanthropes());
            for (Lycanthrope lycan : lycanthropes) {
                if (random.nextInt(10) < 2) { // 20 % de chances
                    meute.gererTransformation(lycan);
                }
            }
        }
    }
}*/