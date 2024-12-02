package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Regenerateur;
import hopital.Sexe;

/**
 * Classe représentant un Zombie, une créature capable de se régénérer après la mort.
 * Hérite de la classe {@link Creature} et implémente l'interface {@link Regenerateur}.
 */
public class Zombie extends Creature implements Regenerateur {

    /**
     * Constructeur de la classe Zombie.
     *
     * @param name  Nom du zombie.
     * @param sexe  Sexe du zombie.
     * @param poids Poids en kilogrammes.
     * @param taille Taille en centimètres.
     * @param age Âge en années.
     */
    public Zombie(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }

    /**
     * Méthode appelée lorsque le zombie trépasserait.
     * Elle invoque d'abord la méthode de la superclasse puis appelle {@link #regenerer()}.
     */
    @Override
    public void trepasser() {
        super.trepasser();
        this.regenerer();
    }
}
