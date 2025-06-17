package fr.monkhanny.dorfromantik.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau représentant un graphique en barres pour visualiser la position d'un joueur dans un classement global.
 * Ce graphique divise les joueurs en 10 tranches égales et colore la barre correspondant à la tranche du joueur
 * en vert, tandis que les autres tranches sont colorées en rouge.
 * @version 1.0
 * @author Moncef STITI
 */
public class LeaderboardBarChartPanel extends JPanel {

    /**
     * Le nombre total de joueurs dans le classement.
     * Utilisé pour déterminer la position relative du joueur dans le graphique en barres.
     */
    private int totalPlayers;

    /**
     * Le rang actuel du joueur dans le classement.
     * Utilisé pour déterminer dans quelle tranche le joueur se situe pour le graphique en barres.
     */
    private int rank;

    /**
     * Constructeur de la classe {@code LeaderboardBarChartPanel}.
     * Initialise le panneau avec le nombre total de joueurs et le rang actuel du joueur.
     *
     * @param totalPlayers Le nombre total de joueurs dans le classement.
     * @param rank         Le rang actuel du joueur dans le classement.
     */
    public LeaderboardBarChartPanel(int totalPlayers, int rank) {
        this.totalPlayers = totalPlayers;
        this.rank = rank;
    }

    /**
     * Méthode de dessin personnalisée pour afficher un graphique en barres représentant les tranches de classement des joueurs.
     * Chaque barre représente une tranche du classement. La barre correspondant à la tranche du joueur est colorée en vert,
     * et les autres barres sont colorées en rouge.
     * <p>
     * Cette méthode est appelée chaque fois que le panneau doit être redessiné.
     * </p>
     *
     * @param g L'objet {@link Graphics} utilisé pour dessiner le graphique en barres.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int spacing = 10;  // Espacement entre les barres
        int barHeight = 20; // Hauteur de la barre
        int totalBars = 10; // Nombre de tranches
        int maxWidth = (getWidth() - (totalBars + 1) * spacing) / totalBars;

        // Dessiner chaque barre pour chaque tranche
        for (int i = 0; i < totalBars; i++) {
            int x = spacing + i * (maxWidth + spacing);
            int y = 50; // Position verticale de la barre

            // Déterminer la couleur : colorier la tranche actuelle
            if (i == (rank - 1) / (totalPlayers / 10)) {
                g.setColor(new Color(0, 255, 0)); // Vert pour la tranche actuelle
            } else {
                g.setColor(new Color(255, 0, 0)); // Rouge pour les autres tranches
            }

            // Dessiner la barre
            g.fillRect(x, y, maxWidth, barHeight);
        }
    }
}
