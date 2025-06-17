package fr.monkhanny.dorfromantik.gui;

import javax.swing.*;
import java.awt.*;

// @TODO : MODIFIER CAR C'EST PAS BEAU + BDD
/**
 * Classe représentant le leaderboard affichant les joueurs par tranche de classement.
 * Cette classe étend {@link Leaderboard} et permet de visualiser dans quelle tranche se trouve un joueur
 * par rapport aux autres, en utilisant des tranches basées sur le classement global.
 * Le classement est présenté avec des informations sur le pourcentage de joueurs devant et derrière le joueur.
 * @version 1.0
 * @author Moncef STITI
 */
public class LeaderboardByTier extends Leaderboard {

    /**
     * Constructeur de la classe {@code LeaderboardByTier}.
     * Charge les données initiales du leaderboard en appelant la méthode {@link #refresh()}.
     */
    public LeaderboardByTier() {
        super();
        refresh(); // Charge les données initiales
    }

    /**
     * Actualise l'affichage du leaderboard en fonction des tranches de classement des joueurs.
     * Cette méthode affiche le classement du joueur actuel, les tranches de classement globales,
     * le pourcentage du joueur par rapport au total, et un graphique en barres représentant sa position
     * par rapport aux autres joueurs.
     */
    @Override
    public void refresh() {
        removeAll(); // Supprime tout contenu existant
        setBackground(new Color(64, 0, 128));

        // Titre
        JLabel titleLabel = new JLabel("CLASSEMENT PAR TRANCHE");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Contenu
        JPanel tierPanel = new JPanel();
        tierPanel.setLayout(new BoxLayout(tierPanel, BoxLayout.Y_AXIS));
        tierPanel.setBackground(new Color(64, 0, 128));

        // Exemple de tranche
        int totalPlayers = 1000;
        int rank = 237; // Exemple : rang du joueur
        int tierSize = totalPlayers / 10;
        int tier = (rank - 1) / tierSize + 1;

        // Label indiquant la tranche dans laquelle le joueur se trouve
        JLabel infoLabel = new JLabel("Vous êtes dans la tranche : " + tier);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Pourcentage du joueur
        double percentage = (double) rank / totalPlayers * 100;
        JLabel percentageLabel = new JLabel(String.format("Vous faites partie des %.2f%% des joueurs", percentage));
        percentageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        percentageLabel.setForeground(Color.WHITE);
        percentageLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Ajouter les labels à la JPanel
        tierPanel.add(Box.createVerticalStrut(20));
        tierPanel.add(infoLabel);
        tierPanel.add(percentageLabel);

        // Ajouter le diagramme en barres pour les tranches
        LeaderboardBarChartPanel barChartPanel = new LeaderboardBarChartPanel(totalPlayers, rank);
        tierPanel.add(Box.createVerticalStrut(20));  // Espacement
        tierPanel.add(barChartPanel);

        add(tierPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
