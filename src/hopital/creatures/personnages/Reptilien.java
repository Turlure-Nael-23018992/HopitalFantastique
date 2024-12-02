package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.caracteristiques.VIP;

import java.util.ArrayList;

/**
 * Classe représentant un Reptilien, une créature dotée de caractéristiques spéciales.
 * Hérite de la classe {@link Creature} et implémente l'interface {@link VIP}.
 */
public class Reptilien extends Creature implements VIP {

    /**
     * Constructeur de la classe Reptilien.
     *
     * @param name  Nom du reptilien.
     * @param sexe  Sexe du reptilien.
     * @param poids Poids en kilogrammes.
     * @param taille Taille en centimètres.
     * @param age Âge en années.
     */
    public Reptilien(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }
}
