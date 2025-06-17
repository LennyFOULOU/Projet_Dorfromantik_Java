package fr.monkhanny.dorfromantik.gui;

import fr.monkhanny.dorfromantik.utils.Database;
import fr.monkhanny.dorfromantik.listeners.GameMainMenuButtonListener;
import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.enums.Fonts;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * La classe GameOver est un panneau graphique (JPanel) affiché à la fin d'une partie.
 * Elle affiche le score final du joueur, des informations sur sa performance par rapport aux autres joueurs, 
 * un graphique en barres pour visualiser la position du joueur dans son groupe, ainsi qu'une citation humoristique.
 * Elle permet également au joueur de retourner au menu principal du jeu.
 * @version 1.0
 * @author Khalid CHENOUNA, Lenny FOULOU
 */
public class GameOver extends JPanel {

    /**
     * Le cadre principal du jeu (JFrame) qui contient tous les panneaux et composants graphiques.
     * Il permet la gestion de l'interface utilisateur du jeu et permet de naviguer entre les écrans (comme l'écran de fin de jeu).
     */
    private JFrame gameFrame;

    /**
     * Constructeur de la classe GameOver.
     * Ce constructeur crée un écran de fin de partie affichant le score du joueur et des informations supplémentaires,
     * telles que son groupe dans le classement.
     * 
     * @param gameFrame Le cadre principal du jeu (JFrame) permettant de naviguer entre les écrans.
     * @param finalScore Le score final du joueur.
     * @param database L'objet Database permettant d'interagir avec la base de données.
     * @param mainMenu Le menu principal permettant de revenir à l'accueil.
     */
    public GameOver(JFrame gameFrame, int finalScore, Database database, MainMenu mainMenu) {
        this.gameFrame = gameFrame;
        this.gameFrame.setTitle("Partie terminée - Dorfromantik");
    
        setLayout(new BorderLayout());
    
        // Background image setup
        JLabel background = new JLabel(new ImageIcon("./ressources/images/MainMenu/backgroundBlured.jpg"));
        background.setLayout(new BorderLayout());
        this.add(background, BorderLayout.CENTER);
    
        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);
        background.add(mainPanel, BorderLayout.CENTER);
    
        // Title for the Game Over message
        JLabel titleLabel = new JLabel("Partie terminée !");
        titleLabel.setFont(Fonts.TITLE.getFont(48));  // Using the TITLE font
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
    
        // Spacer
        mainPanel.add(Box.createVerticalStrut(30));
        
        // Display the final score
        JLabel scoreLabel = new JLabel("Votre score est de : " + finalScore);
        scoreLabel.setFont(Fonts.SCORE.getFont(36));  // Using the SCORE font
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Padding
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(new Color(0, 0, 0, 100)); // Background color with transparency
        mainPanel.add(scoreLabel);
    
        // Spacer
        mainPanel.add(Box.createVerticalStrut(30));
    
        // Grouping information and funny quote
        try {
            long seriesId = Options.SEED; // Get the correct seriesId
            database.addScore(seriesId, finalScore);
            List<Integer> allScores = database.getScoresBySeriesId(seriesId);
    
            // Check if there are enough players to form groups
            int totalPlayers = allScores.size();
            if (totalPlayers >= 100) {
                int groupSize = totalPlayers / 10;
                int playerGroup = 0;
    
                // Group data for bar chart
                List<Integer> groupAverages = new ArrayList<>();
    
                // Calculate which group the player's score falls into and the average score for each group
                for (int i = 0; i < 10; i++) {
                    int startIdx = i * groupSize;
                    int endIdx = Math.min((i + 1) * groupSize, totalPlayers);
                    if (startIdx < totalPlayers) {
                        List<Integer> groupScores = allScores.subList(startIdx, endIdx);
                        int groupSum = 0;
                        for (int score : groupScores) {
                            groupSum += score;
                        }
                        groupAverages.add(groupSum / groupScores.size());
                        if (scoreInGroup(allScores, finalScore, startIdx, endIdx)) {
                            playerGroup = i + 1;
                        }
                    }
                }
    
                // Group information panel
                JPanel groupPanel = new JPanel();
                groupPanel.setOpaque(false);
                groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
    
                JLabel groupTitleLabel = new JLabel("Vous êtes dans le groupe " + playerGroup + "/10 !");
                groupTitleLabel.setFont(Fonts.SCORE.getFont(24));
                groupTitleLabel.setForeground(Color.WHITE);
                groupTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                groupPanel.add(groupTitleLabel);
    
                BarChartPanel barChartPanel = new BarChartPanel(groupAverages, playerGroup - 1);
                barChartPanel.setPreferredSize(new Dimension(700, 400));
                barChartPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer horizontalement
                groupPanel.add(barChartPanel);
    
                groupPanel.add(Box.createVerticalStrut(30));
    
                // Show a funny quote based on the group
                String funnyQuote = getFunnyQuote(playerGroup);
                JLabel quoteLabel = new JLabel(funnyQuote);
                quoteLabel.setFont(Fonts.SCORE.getFont(24));
                quoteLabel.setForeground(Color.WHITE);
                quoteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                groupPanel.add(quoteLabel);
    
                // Add group information panel
                mainPanel.add(groupPanel);
            } else {
                // No groups, display a simple message instead
                JLabel noGroupsLabel = new JLabel("Pas mal !");
                noGroupsLabel.setFont(Fonts.SCORE.getFont(24));
                noGroupsLabel.setForeground(Color.WHITE);
                noGroupsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                mainPanel.add(noGroupsLabel);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        // Spacer
        mainPanel.add(Box.createVerticalStrut(30));
    
        // Bouton pour retourner au menu principal
        JButton returnButton = new JButton("Retour au Menu Principal");
        returnButton.setFont(Fonts.BUTTON.getFont(24)); 
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.setFocusPainted(false); // Optionnel : pour un style plus propre
        returnButton.setBackground(new Color(255, 255, 255)); // Couleur d'arrière-plan du bouton
        returnButton.setForeground(Color.BLACK); // Couleur du texte du bouton
    
        // Ajouter un listener d'action au bouton
        GameMainMenuButtonListener listener = new GameMainMenuButtonListener(gameFrame);
        returnButton.addActionListener(listener);
    
        // Ajouter le bouton au panneau principal
        mainPanel.add(returnButton);
    }
    
    /**
     * Vérifie si le score du joueur se trouve dans le groupe spécifié par les indices de départ et de fin.
     *
     * @param allScores La liste de tous les scores des joueurs.
     * @param finalScore Le score final du joueur.
     * @param startIdx L'indice de début du groupe.
     * @param endIdx L'indice de fin du groupe.
     * @return true si le score est dans le groupe, sinon false.
     */
    private boolean scoreInGroup(List<Integer> allScores, int finalScore, int startIdx, int endIdx) {
        for (int i = startIdx; i < endIdx; i++) {
            if (allScores.get(i) == finalScore) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne une citation humoristique basée sur le groupe du joueur.
     *
     * @param playerGroup Le groupe du joueur (1 à 10).
     * @return Une citation humoristique correspondant au groupe.
     */
    private String getFunnyQuote(int playerGroup) {
        // A list of funny and motivational quotes based on the group
        switch (playerGroup) {
            case 1: return "Vous êtes officiellement un génie ! Peut-être même un super-héros...!";
            case 2: return "Pas mal ! Mais vous n'êtes pas encore le roi du monde... ou du moins de ce jeu !";
            case 3: return "Vous êtes sur la bonne voie, mais vous avez encore un peu de chemin à parcourir !";
            case 4: return "Il est encore temps d'appeler un coach pour vous aider... ou de tricher !";
            case 5: return "Vous êtes dans la bonne direction, mais votre GPS semble un peu perdu !";
            case 6: return "Vous n'êtes pas loin du sommet, mais le sommet semble être dans un autre pays !";
            case 7: return "C'est un bon début ! Peut-être qu'un peu de café améliorerait encore la situation ?!";
            case 8: return "Sur le chemin de la gloire, mais vous êtes encore coincé dans les bouchons...";
            case 9: return "Félicitations ! Mais peut-être que vous voudriez réessayer sans les lunettes de soleil ?";
            case 10: return "L'important c'est de participer, non ? Mais vous pourriez essayer de jouer les yeux ouverts la prochaine fois !";
            default: return "Hé, on progresse ! Peut-être qu'un jour, vous dominerez le monde... ou du moins ce jeu !";
        }
    }
   
}