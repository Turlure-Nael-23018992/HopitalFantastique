package hopital;

import hopital.sante.personnage.Medecin;
import hopital.sante.ServiceMedical;

import java.util.ArrayList;

public class Hopital {
    private String nom;
    private int max;
    private ArrayList<ServiceMedical> medicaux;
    private ArrayList<Medecin> medecins;

    public Hopital(String nom, int max, ArrayList<ServiceMedical> medicaux, ArrayList<Medecin> medecins) {
        this.nom = nom;
        this.max = max;
        this.medicaux = medicaux;
        this.medecins = medecins;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public ArrayList<ServiceMedical> getMedicaux() {
        return medicaux;
    }

    public void setMedicaux(ArrayList<ServiceMedical> medicaux) {
        this.medicaux = medicaux;
    }

    public ArrayList<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(ArrayList<Medecin> medecins) {
        this.medecins = medecins;
    }

    public void nombreCreatures() {
        System.out.println(medicaux.size());
    }

    public void afficherCreaturesMedicaux() {
        medicaux.forEach(med -> {
            System.out.println(med.getNom());
            System.out.println(med); // toString
        });
    }

    public void aleatoireEtatCreature() {
        //aleatoire choix creature
        //aleatoire choix de tout
    }

    public void aleatoireEtatMedicaux() {
        //aleatoire leur budget, leur isolation, leur
        //température,
    }

    public void passerLaMain(Medecin medecin) {
        //passerLaMain
    }
}
