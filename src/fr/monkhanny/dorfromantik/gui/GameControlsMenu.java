package fr.monkhanny.dorfromantik.gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Panneau d'affichage des contrôles du jeu, qui présente des informations sur les commandes du clavier et de la souris.
 * Le menu peut être affiché ou masqué en fonction de l'état de visibilité.
 * @version 1.0
 * @author Lenny FOULOU
 */
public class GameControlsMenu extends JPanel {

    /**
     * Indique si le menu des contrôles est visible ou non.
     * Ce champ détermine l'état actuel de visibilité du panneau des contrôles.
     */
    private boolean isVisible = true;

    // Chemin de base pour les icônes
    /**
     * Chemin de base pour les icônes utilisées dans le menu des contrôles.
     * Ce chemin est relatif à la structure du classpath du projet et spécifie où se trouvent les icônes associées aux commandes du jeu.
     * Les icônes doivent être stockées dans le dossier suivant : /ressources/images/Icone/Keyboard-Mouse/.
     */
    private static final String ICON_PATH = "/ressources/images/Icone/Keyboard-Mouse/";

    /**
     * Constructeur qui initialise et configure le panneau des contrôles du jeu.
     * Ce constructeur crée un menu avec des icônes et du texte pour chaque commande du jeu.
     */
    public GameControlsMenu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Mise en page verticale
        setBackground(new Color(0, 0, 0, 150)); // Fond semi-transparent

        // Augmenter la taille du menu
        setPreferredSize(new Dimension(350, 400)); // Taille du menu augmentée

        // Ajouter des instructions avec des icônes pour chaque touche
        add(createPanel("Cacher/Montrer ce menu", "t.png"));
        add(createPanelWithMultipleIcons("Zoom avant/arrière", "ctrl.png", "mouse-middle.png"));
        add(createPanel("Déplacer le plateau", "keyboard-arrows.png"));
        add(createPanel("Ajuster la vue", "space.png"));
        add(createPanel("Placer une tuile", "mouse-left.png"));
        add(createPanel("Afficher le menu de pause", "esc.png"));

        // Rendre le texte plus lisible
        setForeground(Color.WHITE);
    }

    // Crée un JPanel avec l'icône et le texte aligné horizontalement
    /**
     * Crée un panneau avec une icône et un texte alignés horizontalement.
     *
     * @param text     Le texte à afficher à côté de l'icône.
     * @param iconName Le nom du fichier d'icône à charger.
     * @return Un JPanel contenant l'icône et le texte.
     */
    private JPanel createPanel(String text, String iconName) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Alignement à gauche
        panel.setOpaque(false); // Transparent pour le fond

        // Ajouter l'icône redimensionnée
        JLabel iconLabel = new JLabel(loadIcon(iconName));
        panel.add(iconLabel);

        // Ajouter le texte avec une taille de police plus grande
        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Augmenter la taille de la police
        panel.add(textLabel);

        return panel;
    }

    // Crée un JPanel avec plusieurs icônes et un texte, en ajoutant un "+" entre les icônes
    /**
     * Crée un panneau avec plusieurs icônes et un texte, en ajoutant un "+" entre les icônes.
     *
     * @param text      Le texte à afficher après les icônes.
     * @param iconNames Les noms des fichiers d'icônes à charger.
     * @return Un JPanel contenant les icônes et le texte.
     */
    private JPanel createPanelWithMultipleIcons(String text, String... iconNames) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Alignement à gauche
        panel.setOpaque(false); // Transparent pour le fond

        // Ajouter les icônes et le signe "+" entre elles
        for (int i = 0; i < iconNames.length; i++) {
            JLabel iconLabel = new JLabel(loadIcon(iconNames[i]));
            panel.add(iconLabel);

            // Si ce n'est pas la dernière icône, ajouter un "+" entre les icônes
            if (i < iconNames.length - 1) {
                JLabel plusLabel = new JLabel("+");
                plusLabel.setForeground(Color.WHITE);  // Vous pouvez aussi styliser le "+"
                panel.add(plusLabel);
            }
        }

        // Ajouter le texte avec une taille de police plus grande
        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Augmenter la taille de la police
        panel.add(textLabel);

        return panel;
    }

    // Charge l'icône depuis le répertoire spécifié et la redimensionne
    /**
     * Charge une icône à partir du répertoire spécifié et la redimensionne.
     * 
     * @param iconName Le nom du fichier de l'icône à charger.
     * @return L'icône redimensionnée sous forme de {@link ImageIcon}.
     */
    private ImageIcon loadIcon(String iconName) {
        // Utilisation de getClass().getResource() pour charger l'icône depuis le classpath
        URL iconURL = getClass().getResource(ICON_PATH + iconName);
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            Image img = icon.getImage();
            // Redimensionner l'image à une taille plus grande
            Image scaledImg = img.getScaledInstance(55, 55, Image.SCALE_SMOOTH); // Augmenter la taille des icônes
            return new ImageIcon(scaledImg);
        } else {
            System.out.println("Icone non trouvée : " + iconName);
            return new ImageIcon();
        }
    }

    /**
     * Permet de basculer la visibilité du menu des contrôles.
     * Si le menu est visible, il devient invisible, et inversement.
     */
    public void toggleVisibility() {
        isVisible = !isVisible;
        setVisible(isVisible);
    }

    /**
     * Définit la visibilité du menu des contrôles.
     * 
     * @param visible Indique si le menu doit être visible (true) ou non (false).
     */
    public void setControlsMenuVisible(boolean visible) {
        isVisible = visible;
        setVisible(visible);
    }
}
