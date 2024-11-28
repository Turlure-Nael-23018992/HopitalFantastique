package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;

import java.util.ArrayList;
import java.util.Random;

public interface Contamineur {

    default void contaminer(ServiceMedical serviceMedical) {
        Random r = new Random();
        if (serviceMedical.getNbPresent() == 0) return;
        int chance = r.nextInt(serviceMedical.getNbPresent());
        Creature newContamine = serviceMedical.getCreatures().get(chance - 1);
        ArrayList<Maladie> maladies = ((Creature)this).getMaladies();

        if (maladies.isEmpty()) return;

        Maladie newMaladie = maladies.get(r.nextInt(maladies.size()));

        if (!newContamine.getMaladies().contains(newMaladie)) {
            newContamine.getMaladies().add(newMaladie);
        }
    }
}
