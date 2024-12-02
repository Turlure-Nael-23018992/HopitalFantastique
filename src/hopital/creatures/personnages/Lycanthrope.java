package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Contamineur;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe représentant un Lycanthrope, une créature capable de contaminer un service médical.
 * Hérite de la classe {@link Creature} et implémente les interfaces {@link Contamineur} et {@link Triage}.
 */
public class Lycanthrope extends Creature implements Contamineur, Triage {

    /**
     * Constructeur de la classe Lycanthrope.
     *
     * @param name  Nom du lycanthrope.
     * @param sexe  Sexe du lycanthrope.
     * @param poids Poids en kilogrammes.
     * @param taille Taille en centimètres.
     * @param age Âge en années.
     */
    public Lycanthrope(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }

    /**
     * Méthode appelée lorsque le lycanthrope trépasserait.
     * Elle invoque d'abord la méthode de la superclasse, puis :
     * <ul>
     *   <li>Contamine le service médical en appelant {@link #contaminer(ServiceMedical)}</li>
     * </ul>
     */
    @Override
    public void trepasser() {
        super.trepasser();
        contaminer(ServiceMedical.getInstance());
    }
}
