# **Fantasy Hospital & Simulation de Colonies de Lycanthropes**

## **Présentation**
Ce projet regroupe deux simulations distinctes et complémentaires :

1. **Fantasy Hospital** : Une simulation d’un hôpital fantastique dédié à la prise en charge de créatures comme les elfes, les nains, les vampires, et les lycanthropes.
2. **Colonies de Lycanthropes** : Une simulation détaillée des colonies de lycanthropes, centrée sur leur organisation hiérarchique, leurs interactions et leurs comportements spécifiques.

---

## **1. Fantasy Hospital**

### **Description**
L’hôpital fantastique est conçu pour gérer un large éventail de créatures, chacune ayant ses propres caractéristiques biologiques, psychologiques, et comportementales. Le but est de simuler leur traitement, leur gestion, et les interactions entre elles dans des services médicaux spécialisés.

### **Fonctionnalités principales**
- **Gestion des créatures** :
    - Chaque créature possède un nom, un sexe, un poids, une taille, un âge, un moral, et une liste de maladies.
    - Actions possibles : attendre, hurler, s’emporter, tomber malade, être soignée, trépasser.
    - Comportements spécifiques :
        - Les elfes et vampires démoralisent leur entourage à leur trépas.
        - Les créatures bestiales (orques, homme-bêtes, vampires, lycanthropes) peuvent contaminer d’autres créatures.
        - Les créatures mortes-vivantes (zombies, vampires) peuvent régénérer après trépas.

- **Maladies** :
    - Gestion des maladies avec des noms, des niveaux actuels et maximums.
    - Possibilité d’augmenter, diminuer ou vérifier la létalité d’une maladie.

- **Services médicaux** :
    - Chaque service a un type de créatures spécifique.
    - Caractéristiques : superficie, budget, nombre maximum de créatures, liste des créatures présentes.
    - Actions possibles : afficher les caractéristiques, ajouter/retirer des créatures, soigner les créatures, réviser le budget.
    - Types spécifiques :
        - **Centres de quarantaine** : Pour les créatures contagieuses, avec isolation comme caractéristique supplémentaire.
        - **Cryptes** : Pour les créatures régénérantes, avec gestion de la ventilation et de la température.

- **Médecins** :
    - Spécialisés par type de créature, ils peuvent examiner, soigner, réviser le budget et transférer des créatures entre services.

- **Simulation temporelle** :
    - À intervalle régulier, l’état des créatures et des services médicaux évolue.
    - L’utilisateur peut incarner un médecin pour gérer l’hôpital (actions limitées par intervalle).

---

## **2. Colonies de Lycanthropes**

### **Description**
Cette simulation modélise des colonies de lycanthropes, leur hiérarchie sociale, leurs interactions complexes et leur transformation en humains.

### **Fonctionnalités principales**
- **Gestion des lycanthropes** :
    - Caractéristiques : sexe, catégorie d’âge (jeune, adulte, vieux), force, facteur de domination, rang, niveau, facteur d’impétuosité, appartenance à une meute.
    - Actions possibles : hurler, répondre à des hurlements, tenter de dominer, vieillir, se transformer en humain.

- **Hiérarchie de meute** :
    - Organisation stricte par rang (α, β, γ, etc.), dirigée par un **couple α** (un mâle et une femelle).
    - Les lycanthropes peuvent devenir solitaires ou fonder une nouvelle meute.

- **Interactions** :
    - **Domination** : Un lycanthrope peut dominer un autre selon sa force et son niveau.
    - **Hurlements** : Les hurlements servent à communiquer (domination, soumission, agressivité, appartenance à une meute).

- **Reproduction** :
    - Seul le couple α peut se reproduire, avec des portées de 1 à 7 jeunes lycanthropes.

- **Transformation en humain** :
    - Certains lycanthropes peuvent quitter la meute pour devenir humains, perturbant la hiérarchie.

- **Colonie** :
    - Une colonie regroupe plusieurs meutes. Elle permet d’afficher les lycanthropes et de gérer les événements temporels :
        - Création de nouvelles meutes.
        - Début de la saison des amours (reproduction).
        - Vieillissement et évolution de la hiérarchie.
        - Génération de hurlements aléatoires.
        - Transformation de lycanthropes en humains.

---

## **3. Structure du projet**

## **1. Fantasy Hospital**

### **Packages principaux**
- **`src.hopital`** : Contient les classes principales (Hopital, Creature...).
- **`src..tests`** : Contient les tests unitaires pour valider le bon fonctionnement du projet.

### **Classes principales**
- `Creature` : Représente une créature avec ses caractéristiques et comportements. (classe abstraite)
- `Elfe, Lycanthrope...` : Hérite de Creature. Représente chaque type de créature, avec leurs comportements propres.
- `ServiceMedical` : Représente un service médical avec ses caractéristiques et comportements. (classe abstraite)
- `Crypte, Quarantaine` : Les deux types de services médicaux. Hérite de ServiceMedical.
- `Medecin` : Hérite de créature. Représente un médecin, qui est une créature.

## **2. Colonies de Lycanthropes**

### **Packages principaux**
- **`src.tp4.main`** : Contient les classes principales (Lycanthrope, Meute, Colonie, etc.).
- **`src.tp4.tests`** : Contient les tests unitaires pour valider le bon fonctionnement du projet.

### **Classes principales**
- `Lycanthrope` : Représente un lycanthrope avec ses caractéristiques et comportements.
- `Meute` : Gère les lycanthropes d’une meute et leur hiérarchie.
- `Colonie` : Coordonne plusieurs meutes et modélise l’évolution temporelle.
- `CoupleAlpha` : Définit le couple dirigeant la meute.
- `Hurlement` : Modélise les différents types de communication.

---

## **4. Installation et exécution**

### **Prérequis**
- Java 11 ou version supérieure.
- Un IDE (IntelliJ IDEA, Eclipse, etc.) ou Maven pour compiler et exécuter le projet.

### **Installation**
1. Clonez le dépôt Git :
   ```bash
   git clone https://github.com/Turlure-Nael-23018992/HopitalFantastique.git
   cd HopitalFantastique
2. Exécutez Hopital.java pour Fantasy Hospital, et Main.java pour la simulation de lycanthropes.