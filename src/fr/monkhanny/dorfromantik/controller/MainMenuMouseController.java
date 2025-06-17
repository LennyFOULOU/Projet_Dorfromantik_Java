package fr.monkhanny.dorfromantik.controller;

import fr.monkhanny.dorfromantik.gui.ButtonPanel;
import fr.monkhanny.dorfromantik.gui.ButtonHoverAnimator;

import javax.swing.*;

/**
 * Contrôleur pour gérer les animations des boutons du menu principal lors des interactions avec la souris.
 * Ce contrôleur attache des listeners de survol (hover) pour animer les boutons du panneau de boutons du menu principal.
 *
 * @version 1.0
 * @author Moncef STITI, Khalid CHENOUNA
 */
public class MainMenuMouseController {

    /**
     * Référence au panneau contenant les boutons du menu principal.
     */
    private final ButtonPanel buttonPanel;

    /**
     * Constructeur qui initialise le contrôleur et attache les listeners de survol aux boutons.
     *
     * @param buttonPanel Instance du panneau de boutons à contrôler
     */
    public MainMenuMouseController(ButtonPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
        initMouseListeners();
    }

    /**
     * Initialise les listeners de survol pour les boutons du panneau.
     */
    private void initMouseListeners() {
        addButtonHoverListener(buttonPanel.getNewGameButton());
        addButtonHoverListener(buttonPanel.getHowToPlayButton());
        addButtonHoverListener(buttonPanel.getSettingsButton());
        addButtonHoverListener(buttonPanel.getExitButton());
    }

    /**
     * Ajoute un listener de survol pour un bouton spécifique.
     * Ce listener utilise un {@link ButtonHoverAnimator} pour gérer les animations.
     *
     * @param button Bouton auquel attacher le listener
     */
    private void addButtonHoverListener(JButton button) {
        ButtonHoverAnimator animator = new ButtonHoverAnimator(button);
        button.addMouseListener(new ButtonHoverListener(animator));
    }
}
