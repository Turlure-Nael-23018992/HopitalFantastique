package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;

public interface Triage {

    private void attendre(int temps) { //faire en sorte que plus il attend plus il perd du moral

        this.getMoral().state(false, 10 * temps);
    }
}
