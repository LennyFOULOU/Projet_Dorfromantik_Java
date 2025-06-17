package fr.monkhanny.dorfromantik.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Classe abstraite représentant un classement (Leaderboard).
 * Cette classe sert de base pour la création de différents types de classements, tels que le classement mondial ou par tranche.
 * Elle définit la structure de base d'un leaderboard et inclut une méthode abstraite pour actualiser son contenu.
 * @version 1.0
 * @author Moncef STITI
 */
public abstract class Leaderboard extends JPanel {

    /**
     * Constructeur de la classe Leaderboard.
     * Initialise le panneau avec une disposition de type BorderLayout.
     */
    public Leaderboard() {
        setLayout(new BorderLayout());
    }

    /**
     * Méthode abstraite pour actualiser le contenu du classement.
     * Chaque implémentation concrète de cette classe devra fournir sa propre version de cette méthode
     * pour actualiser les données et l'affichage du leaderboard.
     */
    public abstract void refresh(); // Méthode pour actualiser le contenu
}
