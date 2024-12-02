package hopital;

import java.util.*;

import hopital.creatures.personnages.*;
import hopital.sante.ServiceMedical;
import hopital.sante.centre.Crypte;
import hopital.sante.centre.Quarantaine;
import hopital.sante.personnage.Medecin;
import hopital.creatures.Creature;
import hopital.sante.caracteristiques.Budget;

/**
 * Représente un hôpital dans un système de simulation.
 * Permet la gestion des services médicaux, des médecins, et des créatures présentes dans l'hôpital.
 * Fournit des méthodes pour ajouter des services, recruter des médecins, simuler des tours d'activité,
 * et gérer les actions liées aux services médicaux (examiner, soigner, réviser les budgets, etc.).
 */
public class Hopital {

    private String nom;
    private int nbMaxServices;
    private List<ServiceMedical> servicesMedicaux;
    private List<Medecin> medecins;
    private Random random;
    private Scanner scanner;

    private static final int MAX_ACTIONS_PAR_TOUR = 3;

    /**
     * Constructeur de l'hôpital.
     *
     * @param nom            Le nom de l'hôpital.
     * @param nbMaxServices Le nombre maximal de services médicaux que l'hôpital peut contenir.
     */
    public Hopital(String nom, int nbMaxServices) {
        this.nom = nom;
        this.nbMaxServices = nbMaxServices;
        this.servicesMedicaux = new ArrayList<>();
        this.medecins = new ArrayList<>();
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    // Getters et setters

    /**
     * Obtient le nom de l'hôpital.
     *
     * @return Le nom de l'hôpital.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'hôpital.
     *
     * @param nom Le nom de l'hôpital.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le nombre maximal de services médicaux que l'hôpital peut avoir.
     *
     * @return Le nombre maximal de services médicaux.
     */
    public int getNbMaxServices() {
        return nbMaxServices;
    }

    /**
     * Obtient la liste des services médicaux de l'hôpital.
     *
     * @return La liste des services médicaux.
     */
    public List<ServiceMedical> getServicesMedicaux() {
        return servicesMedicaux;
    }

    /**
     * Obtient la liste des médecins travaillant à l'hôpital.
     *
     * @return La liste des médecins.
     */
    public List<Medecin> getMedecins() {
        return medecins;
    }

    // Méthodes de gestion des services et médecins

    /**
     * Ajoute un service médical à l'hôpital.
     *
     * @param service Le service médical à ajouter.
     */
    public void ajouterService(ServiceMedical service) {
        if (servicesMedicaux.size() < nbMaxServices) {
            servicesMedicaux.add(service);
            System.out.println("Service " + service.getNom() + " ajouté avec succès.");
        } else {
            System.out.println("Impossible d'ajouter plus de services. Capacité maximale atteinte.");
        }
    }

    /**
     * Ajoute un médecin à l'hôpital.
     *
     * @param medecin Le médecin à ajouter.
     */
    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
        System.out.println("Dr. " + medecin.getNom() + " a rejoint l'hôpital.");
    }

    /**
     * Calcule le nombre total de créatures présentes dans tous les services médicaux de l'hôpital.
     *
     * @return Le nombre total de créatures.
     */
    public int getNombreCreaturesTotal() {
        int total = 0;
        for (ServiceMedical service : servicesMedicaux) {
            total += service.getNbPresent();
        }
        return total;
    }

    /**
     * Affiche l'état actuel de l'hôpital, y compris les services médicaux et les créatures présentes.
     */
    public void afficherToutesLesCreatures() {
        System.out.println("\n=== État de l'hôpital " + nom + " ===");
        for (ServiceMedical service : servicesMedicaux) {
            System.out.println("\nService: " + service.getNom());
            System.out.println(service);
        }
    }

    /**
     * Simule un tour d'activité de l'hôpital. Cela inclut la gestion de la santé des créatures,
     * les modifications des services, et le tour d'action des médecins.
     */
    public void simulerTour() {
        // Simulation des actions des créatures dans chaque service
        for (ServiceMedical service : servicesMedicaux) {
            for (Creature creature : service.getCreatures()) {
                if (random.nextDouble() < 0.2) { // 20% de chance de tomber malade
                    creature.tomberMalade();
                }
                if (random.nextDouble() < 0.3) { // 30% de chance de diminuer le moral
                    creature.attendre();
                }
            }
        }

        // Modifications aléatoires des services
        for (ServiceMedical service : servicesMedicaux) {
            if (random.nextDouble() < 0.15) { // 15% de chance de modifier le budget
                service.reviserBudget(true);
            }
        }

        tourDuMedecin();
    }

    /**
     * Permet au médecin de réaliser ses actions pendant un tour, comme examiner, soigner, réviser le budget,
     * et transférer des créatures entre services.
     */
    private void tourDuMedecin() {
        if (medecins.isEmpty()) {
            System.out.println("Aucun médecin disponible dans l'hôpital!");
            return;
        }

        System.out.println("\n=== Tour du médecin ===");
        System.out.println("Choisissez un médecin (0-" + (medecins.size() - 1) + "):");
        for (int i = 0; i < medecins.size(); i++) {
            System.out.println(i + ": Dr. " + medecins.get(i).getName());
        }

        int choixMedecin = scanner.nextInt();
        if (choixMedecin >= 0 && choixMedecin < medecins.size()) {
            Medecin medecinActif = medecins.get(choixMedecin);
            int actionsRestantes = MAX_ACTIONS_PAR_TOUR;

            while (actionsRestantes > 0) {
                System.out.println("\nActions restantes: " + actionsRestantes);
                System.out.println("1. Examiner un service");
                System.out.println("2. Soigner un service");
                System.out.println("3. Réviser le budget d'un service");
                System.out.println("4. Transférer une créature");
                System.out.println("5. Terminer le tour");

                int choix = scanner.nextInt();
                switch (choix) {
                    case 1:
                        examinerService(medecinActif);
                        actionsRestantes--;
                        break;
                    case 2:
                        soignerService(medecinActif);
                        actionsRestantes--;
                        break;
                    case 3:
                        reviserBudgetService(medecinActif);
                        actionsRestantes--;
                        break;
                    case 4:
                        transfererCreature(medecinActif);
                        actionsRestantes--;
                        break;
                    case 5:
                        actionsRestantes = 0;
                        break;
                    default:
                        System.out.println("Choix invalide!");
                }
            }
        } else {
            System.out.println("Choix de médecin invalide!");
        }
    }

    /**
     * Permet au médecin d'examiner un service médical.
     *
     * @param medecin Le médecin effectuant l'examen.
     */
    private void examinerService(Medecin medecin) {
        afficherListeServices();
        System.out.println("Choisissez un service à examiner:");
        int choix = scanner.nextInt();
        if (choix >= 0 && choix < servicesMedicaux.size()) {
            System.out.println(servicesMedicaux.get(choix));
        }
    }

    /**
     * Permet au médecin de soigner un service médical.
     *
     * @param medecin Le médecin effectuant le soin.
     */
    private void soignerService(Medecin medecin) {
        afficherListeServices();
        System.out.println("Choisissez un service à soigner:");
        int choix = scanner.nextInt();
        if (choix >= 0 && choix < servicesMedicaux.size()) {
            medecin.soignerMedicaux(servicesMedicaux.get(choix));
        }
    }

    /**
     * Permet au médecin de réviser le budget d'un service médical.
     *
     * @param medecin Le médecin effectuant la révision.
     */
    private void reviserBudgetService(Medecin medecin) {
        afficherListeServices();
        System.out.println("Choisissez un service dont le budget est à réviser:");
        int choix = scanner.nextInt();
        if (choix >= 0 && choix < servicesMedicaux.size()) {
            System.out.println(servicesMedicaux.get(choix).getBudget());
            medecin.reviserBudgetMedicaux(servicesMedicaux.get(choix));
            System.out.println(servicesMedicaux.get(choix).getBudget());
        }
    }

    /**
     * Permet au médecin de transférer une créature d'un service à un autre.
     *
     * @param medecin Le médecin effectuant le transfert.
     */
    private void transfererCreature(Medecin medecin) {
        afficherListeServices();
        System.out.println("Choisissez le service source:");
        int sourceIndex = scanner.nextInt();
        System.out.println("Choisissez le service destination:");
        int destIndex = scanner.nextInt();

        if (sourceIndex >= 0 && sourceIndex < servicesMedicaux.size() &&
                destIndex >= 0 && destIndex < servicesMedicaux.size()) {

            ServiceMedical source = servicesMedicaux.get(sourceIndex);
            ServiceMedical destination = servicesMedicaux.get(destIndex);

            System.out.println("Créatures disponibles dans le service source:");
            List<Creature> creatures = source.getCreatures();
            for (int i = 0; i < creatures.size(); i++) {
                System.out.println(i + ": " + creatures.get(i));
            }

            System.out.println("Choisissez la créature à transférer:");
            int creatureIndex = scanner.nextInt();

            if (creatureIndex >= 0 && creatureIndex < creatures.size()) {
                medecin.transfertCreature( creatures.get(creatureIndex), destination);
            }
        }
    }

    /**
     * Affiche la liste des services médicaux de l'hôpital.
     */
    private void afficherListeServices() {
        System.out.println("\nListe des services:");
        for (int i = 0; i < servicesMedicaux.size(); i++) {
            System.out.println(i + ": " + servicesMedicaux.get(i).getNom());
        }
    }

    /**
     * Démarre la simulation de l'hôpital. Le processus se répète en boucle tant que l'utilisateur ne quitte pas.
     */
    public void demarrerSimulation() {
        System.out.println("Simulation de l'hôpital " + nom + " démarrée!");

        while (true) {
            System.out.println("\n=== Nouveau tour ===");
            simulerTour();

            System.out.println("\nAppuyez sur Entrée pour continuer ou tapez 'q' pour quitter");
            scanner.nextLine();
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
        }

        System.out.println("Fin de la simulation!");
        scanner.close();
    }

    /**
     * Méthode principale de la simulation de l'hôpital.
     *
     * @param args Les arguments en ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        Hopital hopital = new Hopital("Fantasy Hospital", 5);

        ArrayList<Creature> creatures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

        }

        ServiceMedical crypte = new Crypte(
                "Crypte des Non-Morts",
                200,
                10,
                0,
                creatures,
                Budget.MEDIOCRE
        );
        crypte.setBudget(Budget.MEDIOCRE);

        ServiceMedical quarantaine = new Quarantaine(
                "Zone de Quarantaine",
                300,
                10,
                0,
                new ArrayList<>(),
                Budget.MEDIOCRE
        );
        quarantaine.setBudget(Budget.MEDIOCRE);

        hopital.ajouterService(crypte);
        hopital.ajouterService(quarantaine);

        Medecin medecinElfe = new Medecin("Elrond", Sexe.MASCULIN, 2000);
        Medecin medecinVampire = new Medecin("Alucard", Sexe.FEMININ, 500);

        hopital.ajouterMedecin(medecinElfe);
        hopital.ajouterMedecin(medecinVampire);

        System.out.println("\nMédecins dans l'hôpital:");
        if (hopital.getMedecins().isEmpty()) {
            System.out.println("Aucun médecin n'a été ajouté correctement!");
        } else {
            for (Medecin medecin : hopital.getMedecins()) {
                System.out.println("Dr. " + medecin.getNom());
            }
        }
        hopital.demarrerSimulation();
    }
}
