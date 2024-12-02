package hopital.sante.personnage;

import hopital.creatures.Creature;
import hopital.Sexe;
import hopital.sante.ServiceMedical;

public class Medecin extends Creature{  //TODO ne pas tomber malade
    private String nom;
    private Sexe sex;
    private int age;

    public Medecin( String nom, Sexe sex, int age) {
        super(nom, sex, age);
    }

    public String toString(ServiceMedical medicaux) {
        return medicaux.toString();
    }

    public String getNom() {
        return nom;
    }

    public Sexe getSex() {
        return sex;
    }

    public void setSex(Sexe sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void soignerMedicaux(ServiceMedical medicaux) {
        for (Creature creature : medicaux.getCreatures()) {
            medicaux.soigneCreature(creature);
        }
    }

    public void reviserBudgetMedicaux(ServiceMedical medicaux) {
        medicaux.reviserBudget(true);
    }

    public void transfertCreature(Creature creature, ServiceMedical medicaux) {
        if (medicaux.getCreatures().contains(creature)) {
            System.out.println("Le centre médical " + medicaux.getNom() + " contient déjà la créature " + creature.getName());
        } else {
            //TODO Cherche et supprime la créature de son centre médical actuel

            medicaux.getCreatures().add(creature);
            System.out.println("La créature " + creature.getName() + " a été transférée au centre médical " + medicaux.getNom());
        }
    }
}
