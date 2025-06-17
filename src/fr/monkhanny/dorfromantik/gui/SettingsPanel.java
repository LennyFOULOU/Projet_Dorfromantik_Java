package fr.monkhanny.dorfromantik.gui;

import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.components.Title;
import fr.monkhanny.dorfromantik.listeners.*;
import fr.monkhanny.dorfromantik.enums.Images;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeListener;


/**
 * Représente le panneau des paramètres du jeu.
 * Permet aux utilisateurs de personnaliser les options comme le volume de la musique, les effets sonores,
 * et l'activation ou la désactivation du focus automatique.
 * @version 1.0
 * @author Khalid CHENOUNA, Moncef STITI
 */
public class SettingsPanel extends JPanel {

    /**
     * Référence au menu principal pour permettre le retour à celui-ci
     * lorsque l'utilisateur clique sur le bouton de retour.
     */
    private MainMenu mainMenu;

    /**
     * Référence à la fenêtre contenant ce panneau de paramètres.
     * Utilisée pour effectuer des actions comme fermer ou redimensionner la fenêtre.
     */
    private JFrame settingsFrame;

    /**
     * Bouton permettant de revenir au menu principal.
     * Déclaré en tant qu'attribut pour permettre un accès facile depuis différentes méthodes
     * (comme la méthode pour définir sa visibilité).
     */
    private JButton returnButton; // Déclarer le bouton pour pouvoir y accéder depuis d'autres méthodes

    /**
     * Constructeur pour initialiser le panneau des paramètres.
     *
     * @param mainMenu      L'instance du menu principal à réafficher lorsque le bouton de retour est cliqué.
     * @param settingsFrame La fenêtre contenant ce panneau.
     */
    public SettingsPanel(MainMenu mainMenu, JFrame settingsFrame) {
        this.mainMenu = mainMenu;
        this.settingsFrame = settingsFrame;

        setLayout(new BorderLayout());
        initializeSettingsFrame();
        setupBackground();
        setupTopPanel(); // Nouveau panneau pour le titre et le bouton de retour
        setupMainPanel(); // Configuration du panneau principal pour les sliders et boutons
    }

    /**
     * Définit la taille minimale de la fenêtre des paramètres.
     */
    private void initializeSettingsFrame() {
        settingsFrame.setMinimumSize(Options.MINIMUM_FRAME_SIZE);
    }
    
    /**
     * Configure le fond d'écran du panneau.
     */
    private void setupBackground() {
        JLabel background = new JLabel(new ImageIcon("./ressources/images/MainMenu/backgroundBlured.jpg"));
        background.setLayout(new GridBagLayout());
        this.add(background, BorderLayout.CENTER); // Déplacer l'ajout du fond au centre
    }
    
    /**
     * Configure le panneau supérieur contenant le titre et le bouton de retour.
     */
    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        Title title = new Title("Paramètres", 70, Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(title, BorderLayout.CENTER);

        returnButton = createReturnButtonWithIcon(); // Initialiser le bouton ici
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        leftPanel.add(returnButton);
        topPanel.add(leftPanel, BorderLayout.WEST);

        this.add(topPanel, BorderLayout.NORTH);
    }

 
    /**
     * Permet de rendre le bouton de retour visible ou invisible.
     *
     * @param visible Si vrai, le bouton de retour sera visible ; sinon, il sera caché.
     */
    public void setReturnButtonVisible(boolean visible) {
        if (returnButton != null) {
            returnButton.setVisible(visible);
        }
    }

    /**
     * Dessine le composant avec un fond personnalisé.
     *
     * @param g Le contexte graphique utilisé pour dessiner le panneau.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon backgroundImage = new ImageIcon("./ressources/images/MainMenu/backgroundBlured.jpg");
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Configure le panneau principal contenant les contrôles des paramètres.
     */
    private void setupMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);

        // Section Musique
        JSlider musicSlider = new JSlider(0, 100, Options.MUSIC_MUTED ? 0 : Options.MUSIC_VOLUME);
        JPanel musicPanel = createSoundPanel("Musique", musicSlider, new MusicVolumeChangeListener(musicSlider), new MuteCheckBoxListener("Musique"));
        mainPanel.add(musicPanel, createGridBagConstraints(0, 0, 1));

        // Section SFX
        JSlider sfxSlider = new JSlider(0, 100, Options.SOUNDS_MUTED ? 0 : Options.SOUNDS_VOLUME);
        JPanel sfxPanel = createSoundPanel("SFX", sfxSlider, new SoundsVolumeChangeListener(sfxSlider), new MuteCheckBoxListener("SFX"));
        mainPanel.add(sfxPanel, createGridBagConstraints(0, 1, 1));

        // Section Auto Focus
        JPanel autoFocusPanel = createAutoFocusPanel();
        mainPanel.add(autoFocusPanel, createGridBagConstraints(0, 2, 1));

        mainPanel.add(Box.createVerticalStrut(30), createGridBagConstraints(0, 3, 1));
        this.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Crée un panneau pour les contrôles de son.
     *
     * @param labelText           Le texte du titre (ex. "Musique" ou "SFX").
     * @param volumeSlider        Le slider pour régler le volume.
     * @param sliderChangeListener Le listener pour les changements de volume.
     * @param muteCheckBoxListener Le listener pour couper ou réactiver le son.
     * @return Le panneau configuré pour le contrôle du son.
     */
    private JPanel createSoundPanel(String labelText, JSlider volumeSlider, ChangeListener sliderChangeListener, MuteCheckBoxListener muteCheckBoxListener) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Utilisation de BoxLayout pour une disposition verticale
        panel.setOpaque(false);

        // Titre de la section (ex: "Musique" ou "SFX")
        JLabel titleLabel = new JLabel(labelText);
        titleLabel.setFont(new Font("Roboto", Font.PLAIN, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alignement à gauche
        titleLabel.setForeground(Color.WHITE); // Couleur du texte
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10)); // Espacement vertical

        // Panneau pour le bouton "Couper le son"
        JPanel mutePanel = new JPanel();
        mutePanel.setLayout(new BoxLayout(mutePanel, BoxLayout.X_AXIS)); // Disposition horizontale pour inverser l'ordre
        mutePanel.setOpaque(false);

        JLabel muteLabel = new JLabel("Couper le son");
        muteLabel.setFont(new Font("Roboto", Font.PLAIN, 22)); // Augmentation de la taille du texte
        muteLabel.setForeground(Color.WHITE);
        muteLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Aligner le texte à gauche
        mutePanel.add(muteLabel);

        // Ajouter la checkbox après le texte pour qu'elle soit à droite
        JCheckBox muteCheckBox = new JCheckBox();
        muteCheckBox.setFont(new Font("Roboto", Font.PLAIN, 22)); // Optionnel, si le style du texte dans la case est souhaité
        muteCheckBox.setFocusPainted(false);
        muteCheckBox.setOpaque(false);
        muteCheckBox.setBorderPainted(false);
        muteCheckBox.setMargin(new Insets(5, 5, 5, 5));
        muteCheckBox.setSelected(!("Musique".equals(labelText) ? Options.MUSIC_MUTED : Options.SOUNDS_MUTED));
        muteCheckBox.addActionListener(muteCheckBoxListener);

        mutePanel.add(Box.createHorizontalGlue()); // Espace flexible entre le texte et la checkbox
        mutePanel.add(muteCheckBox);

        panel.add(mutePanel);
        panel.add(Box.createVerticalStrut(10)); // Espace vertical

        // Panneau pour le slider "Gérer le son"
        JPanel volumePanel = new JPanel(new BorderLayout());
        volumePanel.setOpaque(false);
        JLabel manageVolumeLabel = new JLabel("Gérer le son");
        manageVolumeLabel.setFont(new Font("Roboto", Font.PLAIN, 22));
        manageVolumeLabel.setForeground(Color.WHITE);
        volumePanel.add(manageVolumeLabel, BorderLayout.NORTH);

        // Création et ajout du slider
        volumeSlider.setPreferredSize(new Dimension(200, 50));
        volumeSlider.setMajorTickSpacing(50);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setOpaque(false);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setFont(new Font("Roboto", Font.PLAIN, 16));
        volumeSlider.addChangeListener(sliderChangeListener);
        volumePanel.add(createSliderPanel(volumeSlider), BorderLayout.CENTER);

        panel.add(volumePanel);

        return panel;
    }

    /**
     * Crée un panneau pour le contrôle de l'auto-focus.
     *
     * @return Le panneau de contrôle de l'auto-focus.
     */
    private JPanel createAutoFocusPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Disposition verticale
        panel.setOpaque(false); // Assurer que le fond est transparent

        // Titre de la section
        JLabel titleLabel = new JLabel("Focus Automatique");
        titleLabel.setFont(new Font("Roboto", Font.PLAIN, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Aligner le texte au centre
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10)); // Espacement vertical

        // Panneau contenant texte et case à cocher sur la même ligne
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.X_AXIS)); // Disposition horizontale
        checkBoxPanel.setOpaque(false); // Assurer que le fond est transparent

        // Texte explicatif avant la case à cocher
        JLabel descriptionLabel = new JLabel("Gestion du focus automatique (nécessite une bonne carte graphique) :");
        descriptionLabel.setFont(new Font("Roboto", Font.PLAIN, 22));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Aligner à gauche
        checkBoxPanel.add(descriptionLabel); // Ajouter le texte dans le panneau

        // Ajouter un espace flexible entre le texte et la case à cocher
        checkBoxPanel.add(Box.createHorizontalGlue()); // Cela pousse la case à cocher vers la droite

        // Case à cocher
        JCheckBox autoFocusCheckBox = new JCheckBox();
        autoFocusCheckBox.setFont(new Font("Roboto", Font.PLAIN, 22));
        autoFocusCheckBox.setFocusPainted(false);
        autoFocusCheckBox.setOpaque(false);
        autoFocusCheckBox.setBorderPainted(false);
        autoFocusCheckBox.setMargin(new Insets(5, 5, 5, 5));
        autoFocusCheckBox.setSelected(Options.AUTO_FOCUS); // État initial selon la valeur actuelle de AUTO_FOCUS
        autoFocusCheckBox.addActionListener(e -> {
            Options.AUTO_FOCUS = autoFocusCheckBox.isSelected(); // Mettre à jour la variable auto-focus
        });

        checkBoxPanel.add(autoFocusCheckBox); // Ajouter la case à cocher

        // Ajouter le panneau contenant texte + case à cocher
        panel.add(checkBoxPanel);

        return panel;
    }


     /**
     * Crée un panneau contenant un slider et ses libellés.
     *
     * @param volumeSlider Le slider à inclure dans le panneau.
     * @return Le panneau contenant le slider et ses libellés.
     */
    private JPanel createSliderPanel(JSlider volumeSlider) {
        JPanel sliderPanel = new JPanel(new BorderLayout());
        sliderPanel.setOpaque(false);

        JLabel lowLabel = new JLabel("Faible");
        lowLabel.setFont(new Font("Roboto", Font.PLAIN, 22));
        lowLabel.setForeground(Color.WHITE);
        sliderPanel.add(lowLabel, BorderLayout.WEST);

        sliderPanel.add(volumeSlider, BorderLayout.CENTER);

        JLabel highLabel = new JLabel("Élevé");
        highLabel.setFont(new Font("Roboto", Font.PLAIN, 22));
        highLabel.setForeground(Color.WHITE);
        sliderPanel.add(highLabel, BorderLayout.EAST);

        return sliderPanel;
    }

    /**
     * Crée des contraintes pour un élément de disposition GridBag.
     *
     * @param x         La position en colonne.
     * @param y         La position en ligne.
     * @param gridWidth La largeur en colonnes.
     * @return Les contraintes configurées.
     */
    private GridBagConstraints createGridBagConstraints(int x, int y, int gridWidth) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 30, 20, 30);
        return gbc;
    }

    /**
     * Crée un bouton de retour avec une icône.
     *
     * @return Le bouton de retour configuré.
     */
    private JButton createReturnButtonWithIcon() {
        ImageIcon originalIcon = new ImageIcon(Images.EXIT_ICON.getImagePath());

        // Redimensionner l'image à la taille du bouton
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton returnButton = new JButton(resizedIcon);
        returnButton.setPreferredSize(new Dimension(50, 50)); // Ajuster la taille du bouton selon l'icône
        returnButton.setContentAreaFilled(false); // Bouton transparent
        returnButton.setBorderPainted(false); // Pas de bordure
        returnButton.setFocusPainted(false); // Pas de focus
        returnButton.addActionListener(new CloseButtonListener(mainMenu, settingsFrame));

        return returnButton;
    }
}
