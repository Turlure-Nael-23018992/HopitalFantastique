package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;

import java.util.ArrayList;
import java.util.Random;

/**
 * Interface représentant les comportements spécifiques des créatures capables de contaminer d'autres créatures.
 * Les créatures qui implémentent cette interface peuvent transmettre des maladies à d'autres créatures.
 */
public interface Contamineur {

    /**
     * Méthode par défaut permettant à une créature contaminatrice de transmettre une maladie à une autre créature.
     * La transmission est effectuée si le service médical a des créatures présentes et si la créature a des maladies à transmettre.
     * La créature cible est choisie au hasard parmi celles présentes dans le service médical.
     * La maladie est ajoutée à la liste de maladies de la créature cible si elle ne l'a pas déjà.
     *
     * @param serviceMedical Le service médical contenant les créatures susceptibles de recevoir la maladie.
     */
    default void contaminer(ServiceMedical serviceMedical) {
        Random r = new Random();

        // Vérifie qu'il y a des créatures présentes dans le service médical
        if (serviceMedical.getNbPresent() == 0) return;

        // Sélectionne une créature aléatoire dans le service médical
        int chance = r.nextInt(serviceMedical.getNbPresent());
        Creature newContamine = serviceMedical.getCreatures().get(chance - 1);

        // Récupère les maladies de la créature contaminante
        ArrayList<Maladie> maladies = ((Creature)this).getMaladies();

        // Si la créature contaminante n'a pas de maladies, rien n'est fait
        if (maladies.isEmpty()) return;

        // Sélectionne une maladie aléatoire de la créature contaminante
        Maladie newMaladie = maladies.get(r.nextInt(maladies.size()));

        // Si la créature cible n'a pas déjà cette maladie, l'ajoute à sa liste de maladies
        if (!newContamine.getMaladies().contains(newMaladie)) {
            newContamine.getMaladies().add(newMaladie);
        }
    }
}
