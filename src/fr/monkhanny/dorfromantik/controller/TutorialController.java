package fr.monkhanny.dorfromantik.controller;

import fr.monkhanny.dorfromantik.gui.TutorialPanel;
import fr.monkhanny.dorfromantik.gui.Step;
import fr.monkhanny.dorfromantik.enums.Images;
import fr.monkhanny.dorfromantik.gui.MainMenu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur pour gérer le panneau de tutoriel.
 * Ce contrôleur configure les étapes du tutoriel et relie le panneau de tutoriel au menu principal et à sa fenêtre dédiée.
 *
 * @version 1.0
 * @author 
 * Lenny FOULOU, Khalid CHENOUNA
 */
public class TutorialController {

    /**
     * Instance du panneau de tutoriel contenant les étapes.
     */
    private final TutorialPanel tutorialPanel;

    /**
     * Référence au menu principal.
     */
    private final MainMenu mainMenu;

    /**
     * Référence à la fenêtre contenant le tutoriel.
     */
    private final JFrame tutorialFrame;

    /**
     * Constructeur qui initialise le contrôleur et configure les étapes du tutoriel.
     *
     * @param mainMenu Référence au menu principal
     * @param tutorialFrame Fenêtre dans laquelle le tutoriel est affiché
     */
    public TutorialController(MainMenu mainMenu, JFrame tutorialFrame) {
        this.mainMenu = mainMenu;
        this.tutorialFrame = tutorialFrame;

        // Création des étapes du tutoriel avec leurs titres, descriptions et images
        List<Step> steps = new ArrayList<>();
        steps.add(new Step("Étape n°1", "Pour prévisualiser le placement d'une tuile, placez votre souris sur l'un des points rouges affichés sur le plateau.", Images.TUTORIAL_GIF1.getImagePath()));
        steps.add(new Step("Étape n°2", "Pour effectuer une rotation de la tuile, utilisez la molette de la souris. Cela permet d'orienter la tuile selon votre stratégie.", Images.TUTORIAL_GIF2.getImagePath()));
        steps.add(new Step("Étape n°3", "Pour placer une tuile, cliquez sur l'un des points rouges où la prévisualisation est visible.", Images.TUTORIAL_GIF3.getImagePath()));
        steps.add(new Step("Étape n°4", "Le nombre de tuiles restantes est affiché dans la section droite de l'écran, pour suivre votre progression dans la partie.", Images.TUTORIAL_GIF4.getImagePath()));
        steps.add(new Step("Étape n°5", "Votre score est mis à jour en temps réel et est visible en haut au milieu de l'écran.", Images.TUTORIAL_GIF5.getImagePath()));
        steps.add(new Step("Étape n°6", "En cas de besoin, vous pouvez afficher le menu d'aide en appuyant sur la touche 'T'.", Images.TUTORIAL_GIF6.getImagePath()));


        // Initialisation du panneau de tutoriel avec les étapes, le menu principal et la fenêtre associée
        tutorialPanel = new TutorialPanel(steps, this.mainMenu, this.tutorialFrame);
    }

    /**
     * Récupère le panneau de tutoriel configuré.
     *
     * @return Instance du panneau de tutoriel
     */
    public JPanel getTutorialPanel() {
        return tutorialPanel;
    }
}
