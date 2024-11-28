package hopital.creatures.caracteristiques;
import hopital.creatures.Creature;

import java.util.ArrayList;

public interface Triage {

    default void attendre(ArrayList<Creature> creatures, int temps) { //il perd de moin en moin de moral quand il y a des collegues (+ il y a des potes moin il perd du moral)
        creatures.remove(this);
        int countTriage = 1;
        for (Creature creature : creatures) {
            if (creature instanceof Triage) {
                countTriage++;
            }

        }
        ((Creature)this).getMoral().state(false, 10 / countTriage );
    }
}