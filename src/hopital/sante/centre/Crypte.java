package hopital.sante.centre;

import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.caracteristiques.VIP;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;
import hopital.sante.caracteristiques.Budget;

import java.util.ArrayList;

/**
 * Classe représentant une unité de service médical appelée Crypte.
 * Cette classe hérite de la classe {@link ServiceMedical} et permet de gérer des créatures et leurs soins dans cette unité spécifique.
 */
public class Crypte extends ServiceMedical {

    /** Instance unique de Crypte (patron de conception Singleton) */
    private static ServiceMedical instance;

    /**
     * Constructeur de la classe Crypte.
     * Il initialise un service médical de type Crypte avec un certain nombre de créatures présentes.
     *
     * @param nom Le nom de l'unité Crypte.
     * @param superficie La superficie de l'unité Crypte.
     * @param max Le nombre maximal de créatures que la Crypte peut accueillir.
     * @param nbPresent Le nombre de créatures initialement présentes dans la Crypte.
     * @param creatures La liste des créatures présentes dans la Crypte.
     * @param budget Le budget associé à la Crypte pour la gestion des soins.
     */
    public Crypte( String nom,
                   int superficie,
                   int max,
                   int nbPresent,
                   ArrayList<Creature> creatures,
                   Budget budget) {
        super(nom, superficie, max);

        // Mise à jour du nombre de créatures présentes
        super.setNbPresent(super.getNbPresent() + nbPresent);
    }
}
