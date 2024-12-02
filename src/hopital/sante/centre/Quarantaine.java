package hopital.sante.centre;

import hopital.sante.ServiceMedical;
import hopital.creatures.Creature;
import hopital.sante.caracteristiques.Budget;

import java.util.ArrayList;

/**
 * Classe représentant une unité de service médical appelée Quarantaine.
 * Cette classe hérite de la classe {@link ServiceMedical} et est utilisée pour gérer des créatures qui doivent être isolées pour des raisons sanitaires.
 */
public class Quarantaine extends ServiceMedical {

    /**
     * Constructeur de la classe Quarantaine.
     * Il initialise un service médical de type Quarantaine avec un certain nombre de créatures présentes.
     *
     * @param nom Le nom de l'unité Quarantaine.
     * @param superficie La superficie de l'unité Quarantaine.
     * @param max Le nombre maximal de créatures que la Quarantaine peut accueillir.
     * @param nbPresent Le nombre de créatures initialement présentes dans la Quarantaine.
     * @param creatures La liste des créatures présentes dans la Quarantaine.
     * @param budget Le budget associé à la Quarantaine pour la gestion des soins.
     */
    public Quarantaine(String nom,
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
