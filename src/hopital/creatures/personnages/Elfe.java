package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Demoralisateur;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.caracteristiques.VIP;
import hopital.sante.ServiceMedical;

import java.util.ArrayList;

/**
 * Classe représentant un Elfe, une créature capable de démoraliser d'autres créatures.
 * Hérite de la classe {@link Creature} et implémente les interfaces {@link Demoralisateur} et {@link VIP}.
 */
public class Elfe extends Creature implements Demoralisateur, VIP {

    /**
     * Constructeur de la classe Elfe.
     *
     * @param name  Nom de l'elfe.
     * @param sexe  Sexe de l'elfe.
     * @param poids Poids en kilogrammes.
     * @param taille Taille en centimètres.
     * @param age Âge en années.
     */
    public Elfe(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }

    /**
     * Méthode appelée lorsque l'elfe trépasserait.
     * Elle invoque d'abord la méthode de la superclasse, puis :
     * <ul>
     *   <li>Démoralise une créature en appelant {@link #demoraliser(Creature)}</li>
     * </ul>
     */
    @Override
    public void trepasser() {
        super.trepasser();
        demoraliser(Creature.getInstance());
    }
}
