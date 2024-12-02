package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;

import java.util.ArrayList;

/**
 * Interface représentant les comportements spécifiques des créatures impliquées dans un triage.
 * Les créatures qui implémentent cette interface peuvent interagir avec d'autres créatures lors du triage,
 * et leur moral peut être influencé en fonction de leurs collègues triés.
 */
public interface Triage {

    /**
     * Méthode par défaut permettant à une créature impliquée dans un triage d'attendre en groupe.
     * Le moral de la créature est ajusté en fonction du nombre de créatures triées autour d'elle.
     * Plus il y a de créatures triées (ou des "potes"), moins la créature perd de moral.
     *
     * @param creatures Liste des créatures présentes, où l'objet actuel sera retiré.
     * @param temps Durée de l'attente (non utilisée ici mais peut être un facteur dans un futur développement).
     */
    default void attendre(ArrayList<Creature> creatures, int temps) {
        // Retirer la créature actuelle de la liste de créatures
        creatures.remove(this);

        // Compter le nombre de créatures dans le triage
        int countTriage = 1;  // La créature elle-même est déjà incluse dans le triage
        for (Creature creature : creatures) {
            // Si la créature est aussi dans un triage, elle est comptabilisée
            if (creature instanceof Triage) {
                countTriage++;
            }
        }

        // Ajuste le moral de la créature en fonction du nombre total de créatures triées
        // Plus il y a de créatures, moins la créature perd de moral (réduction avec le nombre de créatures)
        ((Creature)this).getMoral().state(false, 10 / countTriage );
    }
}
