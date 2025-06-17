package fr.monkhanny.dorfromantik.listeners;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Gestionnaire pour la fermeture de la fenêtre de jeu avec confirmation.
 * Ce gestionnaire affiche une boîte de dialogue de confirmation avant de quitter le jeu.
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class GameWindowCloseListener extends WindowAdapter {

    /**
     *  Fenêtre à gérer.
     */
    private final JFrame frame;

    /**
     * Constructeur pour initialiser le gestionnaire avec la fenêtre cible.
     *
     * @param frame La fenêtre à gérer
     */
    public GameWindowCloseListener(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque la fenêtre est en cours de fermeture.
     * Affiche une boîte de dialogue de confirmation avant de quitter le jeu.
     * 
     * @param e l'événement de fermeture de la fenêtre.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        Object[] options = {"Oui", "Non"}; // Options personnalisées en français
        int choice = JOptionPane.showOptionDialog(
            frame,
            "Êtes-vous sûr de vouloir quitter le jeu ?", // Message
            "Quitter le jeu", // Titre
            JOptionPane.YES_NO_OPTION, // Type d'option
            JOptionPane.QUESTION_MESSAGE, // Icône
            null, // Icône personnalisée (null pour l'icône par défaut)
            options, // Options de boutons personnalisées
            options[1] // Valeur par défaut ("Non")
        );
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
