package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.caracteristiques.VIP;

import java.util.ArrayList;

/**
 * Classe représentant un Nain, une créature avec des caractéristiques spéciales.
 * Hérite de la classe {@link Creature} et implémente l'interface {@link VIP}.
 */
public class Nain extends Creature implements VIP {

    /**
     * Constructeur de la classe Nain.
     *
     * @param name  Nom du nain.
     * @param sexe  Sexe du nain.
     * @param poids Poids en kilogrammes.
     * @param taille Taille en centimètres.
     * @param age Âge en années.
     */
    public Nain(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }
}
