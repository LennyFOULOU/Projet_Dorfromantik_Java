# Dorfromantik 🏔️

Bienvenue sur notre projet **"Dorfromantik"**, un jeu de construction de paysages inspiré par l'univers de [Dorfromantik](https://store.steampowered.com/app/1455840/Dorfromantik/). Ce projet a été réalisé dans le cadre de la SAE 3.1 de l'IUT Sénart-Fontainebleau.

## Table des matières
- [Introduction](#introduction)
- [Structure du projet](#structure-du-projet)
- [Compilation et exécution](#compilation-et-exécution)
- [Génération de la documentation](#génération-de-la-documentation-)
- [Nettoyage des fichiers](#nettoyage-des-fichiers-)
- [Rapport d'avancement](#rapport-davancement)
- [Crédits](#crédits)

---

## Introduction

Dorfromantik est un jeu où vous devrez :
- Placer des tuiles hexagonales sur un plateau pour former des paysages cohérents.
- Maximiser votre score en reliant des poches de terrains identiques.

Ce projet a été développé en **Java** en suivant les consignes de l'IUT pour garantir modularité et maintenabilité.

---

## Structure du projet 

````
.
├── Database                # Fichiers qui contient une sauvegarde de la BDD
├── Documentation           # Diagrammes UML, Wireflow et rapport
├── libs                    # Bibliothèque MariaDB pour la connexion à la BDD
├── ressources              # Ressources : images, polices, sons
├── src                     # Code source du projet
├── dorfromantik.jar        # Archive exécutable
├── Makefile                # Automatisation de la compilation
├── LICENSE                 # Licence du projet
└── README.md               # Ce fichier
````

---

## Compilation et exécution 

### Pré-requis
- **MariaDB client** (inclus dans le répertoire `libs`)

### Compilation
Utilisez le fichier `Makefile` pour compiler le projet :
```bash
make
```

### Exécution
Pour lancer le programme, utilisez la commande suivante :
```bash
make run
```

ou 

Avec l'archive .jar :
```bash
java -cp dorfromantik.jar:libs/mariadb-client.jar fr.monkhanny.dorfromantik.Main
```

### Création du fichier `.jar`
Vous pouvez générer une archive exécutable :
```bash
make jar
```

---

## Génération de la documentation 📜

La documentation du code source est générée via **Javadoc**. Utilisez :
```bash
make javadoc
```
Les fichiers HTML seront disponibles dans le répertoire `docs`.

Pour voir la documentation facilement, utilisez : 
```bash
make seedoc
```

---

## Nettoyage des fichiers 🗑️

Pour supprimer les fichiers temporaires et la documentation générée :
```bash
make clean
```

--- 
## Rapport d'avancement 

Le rapport d'avancement du projet est disponible dans le dossier `Documentation`. Accédez-y directement ici : [Documentation/Rapport.pdf](./Documentation/Rapport.pdf).

---

## Crédits

Ce projet a été réalisé par :
- [Moncef STITI](https://grond.iut-fbleau.fr/stiti)
- [Lenny FOULOU](https://grond.iut-fbleau.fr/foulou)
- [Khalid CHENOUNA](https://grond.iut-fbleau.fr/chenouna)

Professeur : **Luc Hernandez**.
