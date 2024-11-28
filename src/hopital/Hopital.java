package hopital;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import hopital.creatures.personnages.Elfe;
import hopital.creatures.personnages.Vampire;
import hopital.sante.ServiceMedical;
import hopital.sante.centre.Crypte;
import hopital.sante.centre.Quarantaine;
import hopital.sante.personnage.Medecin;
import hopital.creatures.Creature;
import hopital.sante.caracteristiques.Budget;
public class Hopital {
    private String nom;
    private int nbMaxServices;
    private List<ServiceMedical> servicesMedicaux;
    private List<Medecin> medecins;
    private Random random;
    private Scanner scanner;
    private static final int MAX_ACTIONS_PAR_TOUR = 3;

    public Hopital(String nom, int nbMaxServices) {
        this.nom = nom;
        this.nbMaxServices = nbMaxServices;
        this.servicesMedicaux = new ArrayList<>();
        this.medecins = new ArrayList<>();
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbMaxServices() {
        return nbMaxServices;
    }

    public List<ServiceMedical> getServicesMedicaux() {
        return servicesMedicaux;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    // Méthodes de gestion des services et médecins
    public void ajouterService(ServiceMedical service) {
        if (servicesMedicaux.size() < nbMaxServices) {
            servicesMedicaux.add(service);
            System.out.println("Service " + service.getNom() + " ajouté avec succès.");
        } else {
            System.out.println("Impossible d'ajouter plus de services. Capacité maximale atteinte.");
        }
    }

    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
        System.out.println("Dr. " + medecin.getNom() + " a rejoint l'hôpital.");
    }

    // Méthodes d'affichage
    public int getNombreCreaturesTotal() {
        int total = 0;
        for (ServiceMedical service : servicesMedicaux) {
            total += service.getNbPresent();
        }
        return total;
    }

    public void afficherToutesLesCreatures() {
        System.out.println("\n=== État de l'hôpital " + nom + " ===");
        for (ServiceMedical service : servicesMedicaux) {
            System.out.println("\nService: " + service.getNom());
            System.out.println(service);
        }
    }

    // Simulation temporelle
    public void simulerTour() {
        // 1. Modifications aléatoires des créatures
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

        // 2. Modifications aléatoires des services
        for (ServiceMedical service : servicesMedicaux) {
            if (random.nextDouble() < 0.15) { // 15% de chance de modifier le budget
                service.reviserBudget();
            }
        }

        // 3. Tour du médecin (joueur)
        tourDuMedecin();
    }

    private void tourDuMedecin() {
        if (medecins.isEmpty()) {
            System.out.println("Aucun médecin disponible dans l'hôpital!");
            return;
        }

        System.out.println("\n=== Tour du médecin ===");
        System.out.println("Choisissez un médecin (0-" + (medecins.size() - 1) + "):");
        for (int i = 0; i < medecins.size(); i++) {
            System.out.println(i + ": Dr. " + medecins.get(i).getNom());
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

    private void examinerService(Medecin medecin) {
        afficherListeServices();
        System.out.println("Choisissez un service à examiner:");
        int choix = scanner.nextInt();
        if (choix >= 0 && choix < servicesMedicaux.size()) {
           System.out.println(servicesMedicaux.get(choix));
        }
    }

    private void soignerService(Medecin medecin) {
        afficherListeServices();
        System.out.println("Choisissez un service à soigner:");
        int choix = scanner.nextInt();
        if (choix >= 0 && choix < servicesMedicaux.size()) {
            medecin.soignerMedicaux(servicesMedicaux.get(choix));
        }
    }

    private void reviserBudgetService(Medecin medecin) {
        afficherListeServices();
        System.out.println("Choisissez un service dont le budget est à réviser:");
        int choix = scanner.nextInt();
        if (choix >= 0 && choix < servicesMedicaux.size()) {
            medecin.reviserBudgetMedicaux(servicesMedicaux.get(choix));
        }
    }

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

    private void afficherListeServices() {
        System.out.println("\nListe des services:");
        for (int i = 0; i < servicesMedicaux.size(); i++) {
            System.out.println(i + ": " + servicesMedicaux.get(i).getNom());
        }
    }

    // Point d'entrée de la simulation
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
    public static void main(String[] args) {
        // Création d'un nouvel hôpital
        Hopital hopital = new Hopital("Fantasy Hospital", 5);

        ArrayList<Creature> creatures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Creature elfe = new Elfe(
                    "Elfe"+i,
                    Sexe.FEMININ,
                    10+i,
                    10+i,
                    10+i
            );
            creatures.add(elfe);
        }

        // Création des services
        ServiceMedical crypte = new Crypte(
                "Crypte des Non-Morts",
                200,
                10,
                0,
                new ArrayList<>(),
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

        // Ajouter les créatures à la quarantaine
        for (Creature creature : creatures) {
            quarantaine.addCreature(creature);
        }

        // Ajout des services à l'hôpital
        hopital.ajouterService(crypte);
        hopital.ajouterService(quarantaine);

        // Création et ajout des médecins avec vérification
        Medecin medecinElfe = new Medecin("Elrond", Sexe.MASCULIN, 2000);
        Medecin medecinVampire = new Medecin("Alucard", Sexe.FEMININ, 500);

        hopital.ajouterMedecin(medecinElfe);
        hopital.ajouterMedecin(medecinVampire);

        // Vérification de l'ajout des médecins
        System.out.println("\nMédecins dans l'hôpital:");
        if (hopital.getMedecins().isEmpty()) {
            System.out.println("Aucun médecin n'a été ajouté correctement!");
        } else {
            for (Medecin medecin : hopital.getMedecins()) {
                System.out.println("Dr. " + medecin.getNom());
            }
        }

        // Démarrage de la simulation
        hopital.demarrerSimulation();
    }
}