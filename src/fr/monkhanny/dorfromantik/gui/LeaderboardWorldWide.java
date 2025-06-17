package fr.monkhanny.dorfromantik.gui;

import fr.monkhanny.dorfromantik.utils.Database;
import fr.monkhanny.dorfromantik.utils.PlayerScore;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe représentant le leaderboard mondial des meilleurs joueurs.
 * Cette classe étend {@link Leaderboard} et affiche les meilleurs joueurs en fonction de leurs scores.
 * Le leaderboard est mis à jour en récupérant les données depuis la base de données.
 * @version 1.0
 * @author Moncef STITI
 */
public class LeaderboardWorldWide extends Leaderboard {

    /**
     * Constructeur de la classe {@code LeaderboardWorldWide}.
     * Charge les données initiales du leaderboard en appelant la méthode {@link #refresh()}.
     */
    public LeaderboardWorldWide() {
        super();
        refresh(); // Charge les données initiales
    }

    /**
     * Actualise le leaderboard en récupérant les données des meilleurs joueurs.
     * Remplace le contenu existant par les informations les plus récentes sur les joueurs.
     * La méthode affiche les trois premiers joueurs avec des médailles, suivis des autres joueurs.
     */
    @Override
    public void refresh() {
        removeAll(); // Supprime tout contenu existant
        setBackground(new Color(245, 245, 245)); // Gris clair plus chaleureux

        // Panel principal pour centrer le leaderboard
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 245, 245)); // Gris clair
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre
        JLabel titleLabel = new JLabel("Records par série");
        titleLabel.setForeground(new Color(76, 175, 80)); // Vert plus doux et moderne
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 32)); // Police moderne
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Panel pour les trois premiers
        JPanel topThreePanel = new JPanel(new GridBagLayout());
        topThreePanel.setBackground(new Color(245, 245, 245)); // Gris clair
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 10);

        // Récupérer les meilleurs joueurs depuis la base de données
        Database db = null;
        List<PlayerScore> topPlayers = null;
        try {
            db = new Database();
            topPlayers = db.getTopPlayers();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }

        if (topPlayers != null && topPlayers.size() >= 3) {
            // Ajout des trois premiers joueurs avec médailles
            gbc.gridx = 0;
            gbc.weightx = 0.4;
            topThreePanel.add(createTopPlayerPanel(topPlayers.get(1).getSerieName(), topPlayers.get(1).getScore(),
                    "./ressources/images/MainMenu/Leaderboard/2.png", false), gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.5;
            topThreePanel.add(createTopPlayerPanel(topPlayers.get(0).getSerieName(), topPlayers.get(0).getScore(),
                    "./ressources/images/MainMenu/Leaderboard/1.png", true), gbc);

            gbc.gridx = 2;
            gbc.weightx = 0.4;
            topThreePanel.add(createTopPlayerPanel(topPlayers.get(2).getSerieName(), topPlayers.get(2).getScore(),
                    "./ressources/images/MainMenu/Leaderboard/3.png", false), gbc);
        }

        // Panel pour les autres joueurs
        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
        playersPanel.setBackground(new Color(255, 255, 255)); // Blanc cassé

        // Ajout des joueurs restants (de 4 à 10)
        if (topPlayers != null) {
            for (int i = 3; i < topPlayers.size(); i++) {
                PlayerScore player = topPlayers.get(i);
                playersPanel.add(createPlayerPanel(player.getSerieName(), player.getScore(), i + 1));
            }
        }

        // Ajoute tout au panneau principal
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(topThreePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(playersPanel);

        add(mainPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    /**
     * Crée un panneau affichant les informations d'un joueur, y compris son rang, son nom et son score.
     * 
     * @param playerName Le nom du joueur.
     * @param score Le score du joueur.
     * @param rank Le rang du joueur dans le leaderboard.
     * @return Un panneau affichant les informations du joueur.
     */
    private JPanel createPlayerPanel(String playerName, int score, int rank) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(250, 250, 250)); // Blanc cassé
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(220, 220, 220))); // Bordure gris clair

        JLabel rankLabel = new JLabel(rank + ". ");
        rankLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        rankLabel.setForeground(new Color(76, 175, 80)); // Vert doux pour le rang
        rankLabel.setPreferredSize(new Dimension(40, 40));

        JLabel nameLabel = new JLabel(playerName);
        nameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        nameLabel.setForeground(new Color(60, 60, 60)); // Gris foncé pour le nom

        JLabel scoreLabel = new JLabel(Integer.toString(score));
        scoreLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        scoreLabel.setForeground(new Color(255, 140, 0)); // Orange moderne pour le score

        panel.add(rankLabel, BorderLayout.WEST);
        panel.add(nameLabel, BorderLayout.CENTER);
        panel.add(scoreLabel, BorderLayout.EAST);

        return panel;
    }

    /**
     * Redimensionne l'icône spécifiée à la taille donnée.
     * 
     * @param path Le chemin de l'image à redimensionner.
     * @param width La largeur souhaitée de l'image.
     * @param height La hauteur souhaitée de l'image.
     * @return L'icône redimensionnée.
     */
    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    /**
     * Crée un panneau affichant les informations d'un des trois meilleurs joueurs, y compris leur médaille,
     * leur nom et leur score.
     * 
     * @param playerName Le nom du joueur.
     * @param score Le score du joueur.
     * @param medalPath Le chemin vers l'image de la médaille.
     * @param isFirst Indique si le joueur est premier.
     * @return Un panneau avec le joueur, sa médaille, son nom et son score.
     */
    private JPanel createTopPlayerPanel(String playerName, int score, String medalPath, boolean isFirst) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(isFirst ? new Color(255, 215, 0) : new Color(144, 238, 144)); // Or doré pour le premier, vert clair pour les autres
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Ajout de l'icône de médaille redimensionnée
        JLabel medalLabel = new JLabel(resizeIcon(medalPath, isFirst ? 80 : 60, isFirst ? 80 : 60));
        medalLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(playerName);
        nameLabel.setFont(new Font("Roboto", isFirst ? Font.BOLD : Font.PLAIN, 20));
        nameLabel.setForeground(new Color(76, 175, 80)); // Vert doux pour le nom
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel(Integer.toString(score));
        scoreLabel.setFont(new Font("Roboto", Font.BOLD, isFirst ? 32 : 28));
        scoreLabel.setForeground(new Color(60, 60, 60)); // Gris foncé pour le score
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(medalLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(nameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(scoreLabel);

        return panel;
    }
}
