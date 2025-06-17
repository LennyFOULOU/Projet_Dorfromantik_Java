package fr.monkhanny.dorfromantik.gui;

import fr.monkhanny.dorfromantik.components.Title;
import fr.monkhanny.dorfromantik.listeners.CloseButtonListener;
import fr.monkhanny.dorfromantik.listeners.TutorialButtonHoverListener;
import fr.monkhanny.dorfromantik.enums.Images;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panneau d'interface utilisateur pour afficher le tutoriel du jeu.
 * Permet de naviguer entre différentes étapes (texte et image) 
 * et de retourner au menu principal via un bouton de retour.
 * @version 1.0
 * @author Lenny FOULOU, Moncef STITI
 */
public class TutorialPanel extends JPanel {

    /**
     * Liste des étapes du tutoriel.
     */
    private List<Step> steps;

    /**
     * Index de l'étape actuellement affichée.
     */
    private int currentStepIndex;
       
    /**
     * Titre affiché en haut du panneau.
     */
    private Title title;
        
    /**
     * Texte décrivant l'étape actuelle.
     */
    private JLabel stepText;

    /**
     * Image associée à l'étape actuelle.
     */
    private JLabel stepImage;
        
    /**
     * Bouton pour naviguer vers l'étape suivante.
     */
    private JButton nextButton;
        
    /**
     * Bouton pour naviguer vers l'étape précédente.
     */
    private JButton prevButton;
        
    /**
     * Instance du menu principal pour gérer le retour.
     */
    private MainMenu mainMenu;
        
    /**
     * Fenêtre actuelle contenant ce panneau.
     */
    private JFrame tutorialFrame;

    /**
     * Constructeur pour initialiser le panneau avec les étapes et les composants nécessaires.
     *
     * @param steps         Liste des étapes du tutoriel.
     * @param mainMenu      Instance du menu principal pour gérer le retour.
     * @param tutorialFrame Fenêtre contenant ce panneau.
     */
    public TutorialPanel(List<Step> steps, MainMenu mainMenu, JFrame tutorialFrame) {
        this.steps = steps;
        this.currentStepIndex = 0;
        this.mainMenu = mainMenu;
        this.tutorialFrame = tutorialFrame;

        // Utiliser BorderLayout pour la disposition principale
        setLayout(new BorderLayout());

        // Création du titre centré en haut
        title = new Title("Comment jouer ?", 70f, Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setOpaque(false);

        // Panneau contenant le titre et le bouton de retour
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setOpaque(false);
        northPanel.add(title, BorderLayout.CENTER);

        // Ajouter l'icône de retour à droite du panneau nord
        JButton returnButton = createReturnButtonWithIcon();
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.setOpaque(false);
        topRightPanel.add(returnButton);
        northPanel.add(topRightPanel, BorderLayout.WEST);

        add(northPanel, BorderLayout.NORTH);

        // Conteneur principal pour les étapes, centré
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setOpaque(false); // Rendre le conteneur transparent

        // Utiliser GridBagConstraints pour centrer le contenu verticalement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Conteneur pour le texte et l'image
        JPanel stepContainer = new JPanel();
        stepContainer.setLayout(new BoxLayout(stepContainer, BoxLayout.Y_AXIS));
        stepContainer.setOpaque(false); // Transparent

        stepText = new JLabel();
        stepText.setFont(new Font("Arial", Font.BOLD, 28));
        stepText.setForeground(Color.WHITE);
        stepText.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer le texte horizontalement

        stepImage = new JLabel();
        stepImage.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer l'image horizontalement

        // Ajouter les composants au conteneur d'étapes
        stepContainer.add(stepText);
        stepContainer.add(Box.createVerticalStrut(10)); // Espace entre texte et image
        stepContainer.add(stepImage);

        // Ajouter le conteneur d'étapes au centre du panel
        centerPanel.add(stepContainer, gbc);
        add(centerPanel, BorderLayout.CENTER);

        // Panneau pour les boutons de navigation
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centrer les boutons
        buttonPanel.setOpaque(false); // Transparent

        prevButton = new JButton("Précédent");
        nextButton = new JButton("Suivant");

        // Personnalisation des boutons
        styleButton(prevButton);
        styleButton(nextButton);

        prevButton.addActionListener(e -> showPreviousStep());
        nextButton.addActionListener(e -> showNextStep());

        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        // Ajouter le panneau des boutons en bas
        add(buttonPanel, BorderLayout.SOUTH);

        // Affichage initial de l'étape
        updateStepDisplay();
    }

    /**
     * Redéfinit la méthode de dessin pour inclure un arrière-plan personnalisé.
     *
     * @param g Le contexte graphique.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Appel à super pour s'assurer que le panneau est dessiné

        // Dessin de l'image de fond pour couvrir tout le panneau
        ImageIcon backgroundImage = new ImageIcon("./ressources/images/MainMenu/backgroundBlured.jpg");
        Image image = backgroundImage.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this); // Dessiner l'image pour couvrir tout le panneau
    }

    /**
     * Met à jour l'affichage des informations pour l'étape actuelle.
     */
    private void updateStepDisplay() {
        Step currentStep = steps.get(currentStepIndex);
        String formattedText = addLineBreaks(currentStep.getText(), 10); // Limite à 10 mots par ligne
        stepText.setText(formattedText);
        stepImage.setIcon(new ImageIcon(currentStep.getImagePath()));
        stepImage.setHorizontalAlignment(JLabel.CENTER);
        stepImage.setVerticalAlignment(JLabel.CENTER);
        prevButton.setEnabled(currentStepIndex > 0);
        nextButton.setEnabled(currentStepIndex < steps.size() - 1);
    }
    
    /**
     * Applique un style personnalisé aux boutons.
     *
     * @param button Le bouton à styliser.
     */
    private void styleButton(JButton button) {
        // Police et taille
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);

        // Taille et forme des boutons
        button.setPreferredSize(new Dimension(150, 50)); // Ajuster la taille des boutons
        button.setBackground(new Color(34, 34, 34)); // Couleur de fond sombre
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Bordure blanche

        // Effet au survol
        button.setRolloverEnabled(true);
        button.setContentAreaFilled(true);
        button.setFocusPainted(false); // Pas de focus visible

        // Ajout de l'effet de survol
        button.addMouseListener(new TutorialButtonHoverListener(button, new Color(60,60,60), new Color(34,34,34)));
    }

    /**
     * Affiche l'étape précédente du tutoriel si possible.
     */
    private void showPreviousStep() {
        if (currentStepIndex > 0) {
            currentStepIndex--;
            updateStepDisplay();
        }
    }

    /**
     * Affiche l'étape suivante du tutoriel si possible.
     */
    private void showNextStep() {
        if (currentStepIndex < steps.size() - 1) {
            currentStepIndex++;
            updateStepDisplay();
        }
    }

    /**
     * Crée un bouton de retour avec une icône personnalisée.
     *
     * @return Le bouton de retour configuré.
     */
    private JButton createReturnButtonWithIcon() {
        ImageIcon originalIcon = new ImageIcon(Images.EXIT_ICON.getImagePath());

        // Redimensionnement de l'image à la taille du bouton
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton returnButton = new JButton(resizedIcon);
        returnButton.setPreferredSize(new Dimension(50, 50)); // Ajuste la taille du bouton selon l'icône
        returnButton.setContentAreaFilled(false); // Bouton transparent
        returnButton.setBorderPainted(false); // Pas de bordure
        returnButton.setFocusPainted(false); // Pas de focus
        returnButton.addActionListener(new CloseButtonListener(mainMenu, tutorialFrame));

        return returnButton;
    }

    /**
     * Ajoute des retours à la ligne pour limiter le nombre de mots par ligne dans un texte.
     *
     * @param text             Texte à formater.
     * @param maxWordsPerLine  Nombre maximal de mots par ligne.
     * @return Texte formaté avec des retours à la ligne.
     */
    private String addLineBreaks(String text, int maxWordsPerLine) {
        String[] words = text.split(" ");
        StringBuilder formattedText = new StringBuilder("<html>");
        int wordCount = 0;
    
        for (String word : words) {
            formattedText.append(word).append(" ");
            wordCount++;
            if (wordCount >= maxWordsPerLine) {
                formattedText.append("<br>");
                wordCount = 0;
            }
        }
        formattedText.append("</html>");
        return formattedText.toString();
    }
    
}
