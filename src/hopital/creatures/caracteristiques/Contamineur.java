package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;
import hopital.sante.ServiceMedical;

public interface Contamineur {
    void contaminer(ServiceMedical serviceMedical);
}
