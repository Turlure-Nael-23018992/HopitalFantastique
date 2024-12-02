package hopital.creatures.personnages;

import hopital.Sexe;
import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.*;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe représentant un Vampire, une créature avec des capacités de régénération,
 * de contamination, et de démoralisation.
 * Hérite de la classe {@link Creature} et implémente les interfaces {@link Demoralisateur},
 * {@link Contamineur}, {@link Regenerateur} et {@link Triage}.
 */
public class Vampire extends Creature implements Demoralisateur, Contamineur, Regenerateur, Triage {

    /**
     * Constructeur de la classe Vampire.
     *
     * @param name  Nom du vampire.
     * @param sexe  Sexe du vampire.
     * @param poids Poids en kilogrammes.
     * @param taille Taille en centimètres.
     * @param age Âge en années.
     */
    public Vampire(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }

    /**
     * Méthode appelée lorsque le vampire trépasserait.
     * Elle appelle la méthode de la superclasse, puis :
     * <ul>
     *   <li>Contamine le service médical en utilisant {@link #contaminer(ServiceMedical)}</li>
     *   <li>Demoralise la créature actuelle en utilisant {@link #demoraliser(Creature)}</li>
     *   <li>Régénère le vampire en appelant {@link #regenerer()}</li>
     * </ul>
     */
    @Override
    public void trepasser() {
        super.trepasser();
        contaminer(ServiceMedical.getInstance());
        demoraliser(Creature.getInstance());
        this.regenerer();
    }
}
