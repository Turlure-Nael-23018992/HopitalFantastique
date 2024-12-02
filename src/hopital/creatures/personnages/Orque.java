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
 * Classe représentant un Orque, une créature qui peut contaminer un service médical.
 * Hérite de la classe {@link Creature} et implémente les interfaces {@link Contamineur} et {@link Triage}.
 */
public class Orque extends Creature implements Contamineur, Triage {

    /**
     * Constructeur de la classe Orque.
     *
     * @param name  Nom de l'orque.
     * @param sexe  Sexe de l'orque.
     * @param poids Poids en kilogrammes.
     * @param taille Taille en centimètres.
     * @param age Âge en années.
     */
    public Orque(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }

    /**
     * Méthode appelée lorsque l'orque trépasserait.
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
