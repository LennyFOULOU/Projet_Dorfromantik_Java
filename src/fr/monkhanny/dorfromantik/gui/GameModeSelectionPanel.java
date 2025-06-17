package fr.monkhanny.dorfromantik.gui;

import fr.monkhanny.dorfromantik.components.Title;
import fr.monkhanny.dorfromantik.listeners.CloseButtonListener;
import fr.monkhanny.dorfromantik.listeners.GameModeFilterButtonActionListener;
import fr.monkhanny.dorfromantik.listeners.GameModeHoverEffectMouseListener;
import fr.monkhanny.dorfromantik.listeners.GameModeNextButtonActionListener;
import fr.monkhanny.dorfromantik.listeners.GameModePrevButtonActionListener;
import fr.monkhanny.dorfromantik.utils.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;

/**
 * Classe représentant le panneau de sélection du mode de jeu dans l'interface graphique.
 * Ce panneau permet à l'utilisateur de choisir une série de jeux à partir d'une liste paginée.
 * Il offre également des fonctionnalités de filtrage par dates et par créateur, ainsi qu'un champ pour entrer une seed.
 * 
 * @version 1.0
 * @author Moncef STITI, Khalid CHENOUNA, Lenny FOULOU
 */
public class GameModeSelectionPanel extends JPanel {

    /** Titre de la section de sélection des séries. */
    private JLabel titleLabel;

    /** Champ de texte pour entrer la "seed" du jeu. */
    private JTextField seedField;

    /** Bouton pour démarrer le jeu avec la seed spécifiée. */
    private JButton startButton;

    /** Liste des boutons représentant les différentes séries disponibles. */
    private List<JButton> seriesButtons;

    /** Base de données utilisée pour récupérer les séries. */
    private Database database;

    /** Panneau contenant les boutons de mode de jeu. */
    private JPanel modePanel;

    /** Bouton permettant de naviguer vers la page précédente. */
    private JButton prevButton;

    /** Bouton permettant de naviguer vers la page suivante. */
    private JButton nextButton;

    /** Page actuelle dans la pagination des séries. */
    private int currentPage = 1;

    /** Nombre d'éléments à afficher par page. */
    private int itemsPerPage = 15;

    /** Étiquette affichant la page actuelle et le nombre total de pages. */
    private JLabel pageLabel;

    /** Spinners permettant de sélectionner une plage de dates. */
    private JSpinner startDateSpinner;

    /** Spinners permettant de sélectionner une plage de dates. */
    private JSpinner endDateSpinner;

    /** Listener pour les actions des boutons. */
    private ActionListener buttonListener;

    /** Case à cocher permettant de filtrer les séries créées uniquement par les développeurs. */
    private JCheckBox developerSeriesCheckBox;

    /**
     * Constructeur de la classe GameModeSelectionPanel.
     * 
     * @param buttonListener ActionListener à associer aux boutons du panneau.
     * @param gameModeFrame Cadre principal de la fenêtre du jeu.
     * @param mainMenu Menu principal permettant de revenir à l'écran principal.
     */
    public GameModeSelectionPanel(ActionListener buttonListener, JFrame gameModeFrame, MainMenu mainMenu) {
        this.buttonListener = buttonListener;
    
        // Initialize database
        try {
            database = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        setLayout(new BorderLayout());
        
        // Background setup
        JLabel background = new JLabel(new ImageIcon("./ressources/images/MainMenu/backgroundBlured.jpg"));
        background.setLayout(new BorderLayout());
        this.add(background);
        
        // Top panel setup
        JPanel topPanel = createTopPanel(gameModeFrame, mainMenu);
        background.add(topPanel, BorderLayout.NORTH);
        
        // Main panel setup
        JPanel mainPanel = createMainPanel();
        background.add(mainPanel, BorderLayout.CENTER);
        
        // Title
        titleLabel = new Title("Choisissez une série", 60f, Color.WHITE);
        mainPanel.add(titleLabel, createGridBagConstraints(0, 0, 2));
        
        mainPanel.add(Box.createVerticalStrut(30), createGridBagConstraints(0, 1, 1));
        
        // Date range filter panel
        JPanel dateFilterPanel = createDateFilterPanel();
        mainPanel.add(dateFilterPanel, createGridBagConstraints(0, 2, 2));
        
        // Series buttons panel with pagination
        modePanel = new JPanel();
        modePanel.setOpaque(false);
        seriesButtons = new ArrayList<>();

        
        // Pagination panel
        JPanel paginationPanel = createPaginationPanel();
        
        // Load initial series
        loadSeriesForCurrentPage();
        
        mainPanel.add(modePanel, createGridBagConstraints(0, 3, 2));
        mainPanel.add(paginationPanel, createGridBagConstraints(0, 4, 2));
        
        mainPanel.add(Box.createVerticalStrut(30), createGridBagConstraints(0, 5, 1));
        
        // Seed input and start button anchored at the bottom
        JPanel seedPanel = createSeedPanel(buttonListener);
        background.add(seedPanel, BorderLayout.SOUTH);
    }

    /**
     * Charge les séries correspondant à la page actuelle et les affiche.
     * Applique les filtres de date et de créateur de série, et gère la pagination.
     */
    public void loadSeriesForCurrentPage() {
        // Clear existing buttons
        modePanel.removeAll();
        seriesButtons.clear();
    
        // Récupérer les dates des spinners
        Date startDate = (Date) startDateSpinner.getValue();
        Date endDate = (Date) endDateSpinner.getValue();
    
        // Calculer le lendemain de la date de fin
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date nextDay = cal.getTime();
    
        // Vérifier si la case à cocher est sélectionnée
        boolean onlyDeveloperCreated = developerSeriesCheckBox.isSelected();
    
        // Get paginated series
        List<String> series = database.getSeriesByDateRangePaginated(startDate, nextDay, currentPage, itemsPerPage, onlyDeveloperCreated);
    
        // Si aucune série n'est trouvée pour la période donnée, afficher un message
        if (series.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucune série pour la période donnée", "Avertissement", JOptionPane.WARNING_MESSAGE);
            currentPage = 1;  // Reset current page to 1 if no series is available
            updatePageLabelAndButtons(1, 1); // Mettre à jour le label et désactiver les boutons
            return;
        }
    
        // Calculate grid layout dimensions
        int buttonCount = series.size();
        int columns = Math.min(buttonCount, 5);
        int rows = (int) Math.ceil((double) buttonCount / columns);
    
        modePanel.setLayout(new GridLayout(rows, columns, 20, 10));
    
        for (String seriesName : series) {
            JButton seriesButton = createGameModeButton(seriesName, buttonListener);
            modePanel.add(seriesButton);
            seriesButtons.add(seriesButton);
        }
    
        // Update page label
        int totalSeries = database.countSeriesByDateRange(startDate, nextDay, onlyDeveloperCreated);
        int totalPages = (int) Math.ceil((double) totalSeries / itemsPerPage);
    
        // Assurez-vous que totalPages est au moins 1
        totalPages = Math.max(totalPages, 1);
        currentPage = Math.min(currentPage, totalPages); // Assurez-vous que la page actuelle ne dépasse pas le nombre total de pages
        updatePageLabelAndButtons(currentPage, totalPages);
    
        // Refresh the panel
        modePanel.revalidate();
        modePanel.repaint();
    }
    
    /**
     * Met à jour le label de la page et l'état des boutons de pagination.
     * 
     * @param currentPage Page actuelle.
     * @param totalPages Nombre total de pages disponibles.
     */
    private void updatePageLabelAndButtons(int currentPage, int totalPages) {
        pageLabel.setText("Page " + currentPage + " / " + totalPages);
    
        // Enable/disable pagination buttons
        prevButton.setEnabled(currentPage > 1);
        nextButton.setEnabled(currentPage < totalPages);
    
        // Show/Hide pagination buttons if there is only one page
        boolean multiplePages = totalPages > 1;
        prevButton.setVisible(multiplePages);
        nextButton.setVisible(multiplePages);
        pageLabel.setVisible(multiplePages);
    }
    
    
    /**
     * Crée un panneau pour le filtrage des séries par date.
     * Ce panneau permet à l'utilisateur de sélectionner une plage de dates et de filtrer les séries.
     * 
     * @return Le panneau de filtrage des dates.
     */
    private JPanel createDateFilterPanel() {
        // Création du panneau de date avec un BoxLayout vertical pour un meilleur agencement
        JPanel datePanel = new JPanel();
        datePanel.setOpaque(false);
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));
        
        // Panneau contenant les spinners de date
        JPanel dateSpinnersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        dateSpinnersPanel.setOpaque(false);
    
        JLabel startLabel = new JLabel("Date de début:");
        startLabel.setForeground(Color.WHITE);
        startLabel.setFont(new Font("Arial", Font.BOLD, 16));
    
        JLabel endLabel = new JLabel("Date de fin:");
        endLabel.setForeground(Color.WHITE);
        endLabel.setFont(new Font("Arial", Font.BOLD, 16));
    
        // Créer les spinners pour les dates de début et de fin
        Calendar cal = Calendar.getInstance();
        Date endDate = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, -30);
        Date startDate = cal.getTime();
    
        SpinnerDateModel startDateModel = new SpinnerDateModel(startDate, null, endDate, Calendar.DAY_OF_MONTH);
        startDateSpinner = new JSpinner(startDateModel);
        startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "dd-MM-yyyy"));
        stylizeDateSpinner(startDateSpinner);
    
        SpinnerDateModel endDateModel = new SpinnerDateModel(endDate, null, endDate, Calendar.DAY_OF_MONTH);
        endDateSpinner = new JSpinner(endDateModel);
        endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "dd-MM-yyyy"));
        stylizeDateSpinner(endDateSpinner);
    
        dateSpinnersPanel.add(startLabel);
        dateSpinnersPanel.add(startDateSpinner);
        dateSpinnersPanel.add(endLabel);
        dateSpinnersPanel.add(endDateSpinner);
        
        // Ajouter la case à cocher pour les séries créées par les développeurs
        developerSeriesCheckBox = new JCheckBox("Inclure seulement les séries créées par les développeurs");
        developerSeriesCheckBox.setOpaque(false);
        developerSeriesCheckBox.setForeground(Color.WHITE);
        developerSeriesCheckBox.setFont(new Font("Arial", Font.BOLD, 16));
        developerSeriesCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Créer un bouton de filtre
        JButton filterButton = new JButton("Filtrer");
        filterButton.setFont(new Font("Arial", Font.BOLD, 16));
        filterButton.setBackground(new Color(252, 161, 3));
        filterButton.setForeground(Color.WHITE);
        filterButton.setFocusPainted(false);
        filterButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 120), 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        filterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterButton.addActionListener(new GameModeFilterButtonActionListener(this));
    
        // Ajouter les composants au panneau principal de filtre
        datePanel.add(dateSpinnersPanel);
        datePanel.add(Box.createVerticalStrut(10));
        datePanel.add(developerSeriesCheckBox);
        datePanel.add(Box.createVerticalStrut(15));
        datePanel.add(filterButton);
    
        return datePanel;
    }
    
    /**
     * Applique une stylisation particulière aux spinners de date.
     * 
     * @param spinner Le JSpinner à styliser.
     */
    private void stylizeDateSpinner(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor defaultEditor = (JSpinner.DefaultEditor) editor;
            JTextField textField = defaultEditor.getTextField();
            textField.setFont(new Font("Arial", Font.PLAIN, 16));
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 120), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            textField.setBackground(new Color(112,128,144)); // Fond doux pour les spinners
            textField.setForeground(Color.WHITE); // Texte blanc pour une meilleure lisibilité
            textField.setCaretColor(Color.WHITE); // Curseur blanc
        }
    }
    
    /**
     * Crée le panneau de pagination avec les boutons "Précédent" et "Suivant".
     * 
     * @return Le panneau de pagination.
     */
    private JPanel createPaginationPanel() {
        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        paginationPanel.setOpaque(false);
    
        prevButton = new JButton("Précédent");
        prevButton.addActionListener(new GameModePrevButtonActionListener(this));
    
        nextButton = new JButton("Suivant");
        nextButton.addActionListener(new GameModeNextButtonActionListener(this));
    
        pageLabel = new JLabel("Page 1");
        pageLabel.setForeground(Color.WHITE);
    
        paginationPanel.add(prevButton);
        paginationPanel.add(pageLabel);
        paginationPanel.add(nextButton);
    
        return paginationPanel;
    }
    
    /**
     * Crée le panneau supérieur contenant le bouton de retour au menu principal.
     * 
     * @param gameModeFrame Cadre principal de la fenêtre du jeu.
     * @param mainMenu Menu principal permettant de revenir à l'écran principal.
     * @return Le panneau supérieur.
     */
    private JPanel createTopPanel(JFrame gameModeFrame, MainMenu mainMenu) {
        // Utilisation de BorderLayout pour aligner correctement le bouton à gauche
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
    
        // Création du bouton de retour
        JButton returnButton = createReturnButtonWithIcon(gameModeFrame, mainMenu);
        
        // Ajouter le bouton de retour à gauche (West)
        topPanel.add(returnButton, BorderLayout.WEST);
    
        return topPanel;
    }
    
    /**
     * Crée un bouton de retour avec une icône spécifique et un événement de fermeture.
     * 
     * @param gameModeFrame Cadre principal de la fenêtre du jeu.
     * @param mainMenu Menu principal permettant de revenir à l'écran principal.
     * @return Le bouton de retour.
     */
    private JButton createReturnButtonWithIcon(JFrame gameModeFrame, MainMenu mainMenu) {
        ImageIcon originalIcon = new ImageIcon("./ressources/images/Icone/ExitIcon.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
    
        JButton returnButton = new JButton(resizedIcon);
        returnButton.setPreferredSize(new Dimension(50, 50));
        returnButton.setContentAreaFilled(false);
        returnButton.setBorderPainted(false);
        returnButton.setFocusPainted(false);
        returnButton.addActionListener(new CloseButtonListener(mainMenu, gameModeFrame));
    
        return returnButton;
    }
    
    /**
     * Crée le panneau principal pour afficher les séries.
     * 
     * @return Le panneau principal.
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        return mainPanel;
    }
    
    /**
     * Crée des contraintes pour la mise en page du panneau principal.
     * 
     * @param x La position x de la cellule.
     * @param y La position y de la cellule.
     * @param gridWidth Le nombre de cellules à occuper horizontalement.
     * @return Les contraintes de mise en page.
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
     * Crée un bouton pour chaque mode de jeu (série).
     * 
     * @param modeName Nom de la série.
     * @param buttonListener ActionListener à associer au bouton.
     * @return Le bouton créé pour la série.
     */
    private JButton createGameModeButton(String modeName, ActionListener buttonListener) {
        JButton button = new JButton(modeName);
        button.setFont(new Font("Arial", Font.BOLD, 18)); // Texte clair et lisible
        button.setForeground(Color.WHITE); // Texte en blanc
        
        // Appliquer un fond dégradé
        button.setBackground(new Color(70, 130, 180)); // Fond initial
        button.setOpaque(true);
        
        // Ajouter des bordures arrondies
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 120), 2), // Bordure extérieure blanche et semi-transparente
            BorderFactory.createEmptyBorder(10, 20, 10, 20) // Espacement intérieur
        ));
        
        button.addMouseListener(new GameModeHoverEffectMouseListener(
            new Color(70, 130, 180), // Default
            new Color(100, 180, 255), // Hover
            new Color(50, 100, 160) // Click
        ));

    
        // Supprimer l'effet de focus par défaut
        button.setFocusPainted(false);
        
        // Ajout de l'action
        button.addActionListener(buttonListener);
        
        // Ajuster les dimensions du bouton
        button.setPreferredSize(new Dimension(200, 50)); // Taille standard pour tous les boutons
    
        return button;
    }

    /**
     * Retourne le numéro de la page actuelle.
     * 
     * @return Le numéro de la page actuelle.
     */
    public int getCurrentPage() {
        return currentPage;
    }
    
    /**
     * Définit le numéro de la page actuelle.
     * 
     * @param currentPage Le numéro de la page à définir.
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    /**
     * Crée et configure un panneau pour l'entrée du seed et le bouton de démarrage.
     * 
     * @param buttonListener L'écouteur d'événement pour le bouton "Démarrer".
     * @return Le panneau contenant le champ de texte pour le seed et le bouton de démarrage.
     */
    private JPanel createSeedPanel(ActionListener buttonListener) {
        JPanel seedPanel = new JPanel();
        seedPanel.setOpaque(false);
        seedPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel seedLabel = new JLabel("Entrez votre seed:");
        seedLabel.setForeground(Color.WHITE);
        seedPanel.add(seedLabel);

        seedField = new JTextField(20);
        seedField.setFont(new Font("Arial", Font.PLAIN, 18));
        seedField.setPreferredSize(new Dimension(250, 40));
        seedField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        seedPanel.add(seedField);

        startButton = new JButton("Démarrer");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBackground(new Color(0, 100, 0));
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        startButton.setPreferredSize(new Dimension(150, 50));
        startButton.addActionListener(buttonListener);
        seedPanel.add(startButton);

        return seedPanel;
    }

    /**
     * Retourne le seed sous forme de chaîne de caractères.
     * 
     * @return Le seed en tant que chaîne de caractères.
     */
    public String getStringSeed() {
        return seedField.getText();
    }

    /**
     * Retourne le seed sous forme de long. Si la chaîne de texte ne peut pas être convertie en long,
     * le timestamp actuel est utilisé comme seed.
     * 
     * @return Le seed en tant que valeur long.
     */
    public long getLongSeed(){
        try{
            return Long.parseLong(seedField.getText());
        } catch (NumberFormatException e){
            System.err.println("Invalid seed, using current time as seed");
            return System.currentTimeMillis();
        }
    }
}
