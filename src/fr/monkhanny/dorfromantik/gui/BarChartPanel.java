package fr.monkhanny.dorfromantik.gui;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

/**
 * Un composant graphique Swing pour afficher un histogramme représentant les scores moyens des groupes.
 * Chaque barre représente un groupe et est colorée différemment en fonction de s'il est mis en surbrillance.
 *
 * Les barres incluent des labels pour indiquer les scores moyens, ainsi que les noms des groupes.
 *
 * @version 1.2
 * @author Moncef STITI
 */
public class BarChartPanel extends JPanel {

    /**
     * Liste des scores moyens des groupes à afficher.
     */
    private final List<Integer> groupAverages;

    /**
     * Indice du groupe à mettre en surbrillance.
     */
    private final int highlightedGroup;

    /**
     * Couleur des barres pour le groupe du joueur.
     */
    private static final Color PLAYER_GROUP_COLOR = new Color(11, 143, 55); // Vert 

    /**
     * Couleur des barres pour les autres groupes.
     */
    private static final Color OTHER_GROUP_COLOR = new Color(11, 77, 143); // Bleu 

    /**
     * Couleur du texte.
     */
    private static final Color TEXT_COLOR = Color.BLACK;

    /**
     * Polices utilisée pour le texte de la moyenne du groupe
     */
    private static final Font AVERAGE_FONT = new Font("Arial", Font.ITALIC, 12);

    /**
     * Police utilisée pour les étiquettes de groupe
     */
    private static final Font GROUP_LABEL_FONT = new Font("Arial", Font.BOLD, 16);

    /**
     * Police utilisée pour les scores
     */
    private static final Font SCORE_FONT = new Font("Arial", Font.BOLD, 14);

    /**
     * Marge intérieure pour les barres.
     */
    private static final int PADDING = 30;

    /**
     * Crée un nouveau panneau d'histogramme avec les scores moyens des groupes et un groupe mis en surbrillance.
     * @param groupAverages Liste des scores moyens des groupes
     * @param highlightedGroup Indice du groupe à mettre en surbrillance
     */
    public BarChartPanel(List<Integer> groupAverages, int highlightedGroup) {
        this.groupAverages = Objects.requireNonNull(groupAverages, "Group averages cannot be null");
        this.highlightedGroup = highlightedGroup;

        setOpaque(false);
    }

    /**
     * Dessine les barres de l'histogramme avec les scores moyens des groupes.
     * 
     * @param g Contexte graphique pour dessiner les barres
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (groupAverages.isEmpty()) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g.create();
        try {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int barWidth = Math.max(20, width / (groupAverages.size() * 2));
            int maxScore = groupAverages.stream().mapToInt(Integer::intValue).max().orElse(1);

            for (int i = 0; i < groupAverages.size(); i++) {
                int score = groupAverages.get(i);
                int barHeight = calculateBarHeight(maxScore, height, score);
                int xPosition = PADDING + i * (barWidth * 2);

                Color barColor = (i == highlightedGroup) ? PLAYER_GROUP_COLOR : OTHER_GROUP_COLOR;

                // Dessiner la barre avec coins arrondis
                g2d.setColor(barColor);
                g2d.fillRoundRect(xPosition, height - barHeight - PADDING, barWidth, barHeight, 10, 10);

                // Ajouter le texte des scores
                g2d.setFont(SCORE_FONT);
                g2d.setColor(TEXT_COLOR);
                String scoreText = String.valueOf(score);
                drawCenteredString(g2d, scoreText, xPosition, barWidth, height - barHeight - PADDING - 5);

                // Ajouter les labels "Score moyen"
                g2d.setFont(AVERAGE_FONT);
                drawCenteredString(g2d, "Score moyen", xPosition, barWidth, height - barHeight - PADDING - 20);

                // Ajouter les noms des groupes
                g2d.setFont(GROUP_LABEL_FONT);
                drawCenteredString(g2d, "Groupe " + (i + 1), xPosition, barWidth, height - 5);
            }
        } finally {
            g2d.dispose();
        }
    }

    /**
     * Calcule la hauteur de la barre en fonction du score et de la hauteur du panneau.
     * @param maxScore Score maximum
     * @param height Hauteur du panneau
     * @param score Score du groupe
     * @return Hauteur de la barre
     */
    private int calculateBarHeight(int maxScore, int height, int score) {
        return (int) ((double) score / maxScore * (height - 2 * PADDING));
    }

    /**
     * Dessine une chaîne de texte centrée horizontalement dans un rectangle.
     * @param g2d Contexte graphique pour dessiner le texte
     * @param text Texte à dessiner
     * @param xOffset Position X du coin supérieur gauche du rectangle
     * @param sectionWidth Largeur du rectangle
     * @param y Position Y du coin supérieur gauche du rectangle
     */
    private void drawCenteredString(Graphics2D g2d, String text, int xOffset, int sectionWidth, int y) {
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textX = xOffset + (sectionWidth - textWidth) / 2;
        g2d.drawString(text, textX, y);
    }

    /**
     * Retourne la taille préférée du panneau.
     * @return Taille préférée du panneau
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
}