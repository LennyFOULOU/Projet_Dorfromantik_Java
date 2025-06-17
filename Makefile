# Définition des variables
JC = javac
JVM = java
JVMFLAGS = -classpath ".:libs/mariadb-client.jar:build"
JARNAME = dorfromantik.jar
ENTRY = fr.monkhanny.dorfromantik.Main
PACKAGE = build/fr/monkhanny/dorfromantik
SRC = src/fr/monkhanny/dorfromantik

# Compilation des fichiers .java
${PACKAGE}/Main.class ${PACKAGE}/controller/GameModeController.class: ${SRC}/Main.java ${SRC}/controller/GameModeController.java ${PACKAGE}/Options.class ${PACKAGE}/utils/MusicPlayer.class ${PACKAGE}/controller/MainMenuResizeController.class ${PACKAGE}/controller/MainMenuButtonController.class ${PACKAGE}/enums/Musics.class ${PACKAGE}/gui/SettingsPanel.class ${PACKAGE}/controller/TutorialController.class ${PACKAGE}/gui/GameModeSelectionPanel.class ${PACKAGE}/utils/Database.class ${PACKAGE}/game/Board.class ${PACKAGE}/gui/MainMenu.class ${PACKAGE}/gui/GameModeSelectionPanel.class 
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/Main.java ${SRC}/controller/GameModeController.java


${PACKAGE}/Options.class ${PACKAGE}/gui/MainMenu.class ${PACKAGE}/controller/MainMenuMouseController.class ${PACKAGE}/gui/ButtonPanel.class ${PACKAGE}/gui/ButtonHoverAnimator.class ${PACKAGE}/utils/MusicPlayer.class: ${SRC}/Options.java ${SRC}/gui/MainMenu.java ${SRC}/controller/MainMenuMouseController.java ${SRC}/gui/ButtonPanel.java ${SRC}/gui/ButtonHoverAnimator.java ${SRC}/utils/MusicPlayer.java ${PACKAGE}/utils/FontManager.class ${PACKAGE}/utils/ImageLoader.class ${PACKAGE}/enums/Fonts.class ${PACKAGE}/components/Title.class ${PACKAGE}/gui/Leaderboard.class ${PACKAGE}/gui/LeaderboardWorldWide.class ${PACKAGE}/components/Button.class ${PACKAGE}/enums/Musics.class ${PACKAGE}/enums/Sounds.class ${PACKAGE}/utils/SoundLoader.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/Options.java ${SRC}/gui/MainMenu.java ${SRC}/controller/MainMenuMouseController.java ${SRC}/gui/ButtonPanel.java ${SRC}/gui/ButtonHoverAnimator.java ${SRC}/utils/MusicPlayer.java


################################
# Compilation des composants
${PACKAGE}/components/Button.class: ${SRC}/components/Button.java ${PACKAGE}/utils/FontManager.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/components/Button.java

${PACKAGE}/components/Title.class: ${SRC}/components/Title.java ${PACKAGE}/utils/FontManager.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/components/Title.java

################################
# -----------------------------#
################################
# Compilation des enums

${PACKAGE}/enums/Biome.class: ${SRC}/enums/Biome.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/enums/Biome.java

${PACKAGE}/enums/Fonts.class: ${SRC}/enums/Fonts.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/enums/Fonts.java

${PACKAGE}/enums/Images.class: ${SRC}/enums/Images.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/enums/Images.java

${PACKAGE}/enums/Musics.class: ${SRC}/enums/Musics.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/enums/Musics.java

${PACKAGE}/enums/Sounds.class: ${SRC}/enums/Sounds.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/enums/Sounds.java

${PACKAGE}/enums/TileOrientation.class: ${SRC}/enums/TileOrientation.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/enums/TileOrientation.java
################################
# -----------------------------#
################################
# Compilation des utilitaires

${PACKAGE}/utils/SoundLoader.class: ${SRC}/utils/SoundLoader.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/utils/SoundLoader.java

${PACKAGE}/utils/PlayerScore.class: ${SRC}/utils/PlayerScore.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/utils/PlayerScore.java

${PACKAGE}/utils/ImageLoader.class: ${SRC}/utils/ImageLoader.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/utils/ImageLoader.java

${PACKAGE}/utils/Hexagon.class: ${SRC}/utils/Hexagon.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/utils/Hexagon.java

${PACKAGE}/utils/FontManager.class: ${SRC}/utils/FontManager.java ${PACKAGE}/enums/Fonts.class ${PACKAGE}/utils/FontLoader.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/utils/FontManager.java

${PACKAGE}/utils/FontLoader.class: ${SRC}/utils/FontLoader.java ${PACKAGE}/enums/Fonts.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/utils/FontLoader.java

${PACKAGE}/utils/Database.class: ${SRC}/utils/Database.java ${PACKAGE}/utils/PlayerScore.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/utils/Database.java

################################
# -----------------------------#
################################
# Compilation des GUI

${PACKAGE}/gui/TutorialPanel.class: ${SRC}/gui/TutorialPanel.java ${PACKAGE}/components/Title.class ${PACKAGE}/enums/Images.class ${PACKAGE}/gui/Step.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/TutorialPanel.java

${PACKAGE}/gui/Step.class: ${SRC}/gui/Step.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/Step.java

${PACKAGE}/gui/SettingsPanel.class: ${SRC}/gui/SettingsPanel.java ${PACKAGE}/enums/Images.class ${PACKAGE}/components/Title.class ${PACKAGE}/Options.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/SettingsPanel.java

${PACKAGE}/gui/Reward.class: ${SRC}/gui/Reward.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/Reward.java

${PACKAGE}/gui/LeaderboardWorldWide.class: ${SRC}/gui/LeaderboardWorldWide.java ${PACKAGE}/utils/Database.class ${PACKAGE}/utils/PlayerScore.class ${PACKAGE}/gui/Leaderboard.class 
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/LeaderboardWorldWide.java

${PACKAGE}/gui/LeaderboardByTier.class: ${SRC}/gui/LeaderboardByTier.java ${PACKAGE}/gui/Leaderboard.class 
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/LeaderboardByTier.java

${PACKAGE}/gui/LeaderboardBarChartPanel.class: ${SRC}/gui/LeaderboardBarChartPanel.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/LeaderboardBarChartPanel.java

${PACKAGE}/gui/Leaderboard.class: ${SRC}/gui/Leaderboard.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/Leaderboard.java

${PACKAGE}/gui/GameOver.class: ${SRC}/gui/GameOver.java ${PACKAGE}/utils/Database.class ${PACKAGE}/Options.class ${PACKAGE}/enums/Fonts.class ${PACKAGE}/gui/MainMenu.class ${PACKAGE}/gui/BarChartPanel.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/GameOver.java

${PACKAGE}/gui/GameModeSelectionPanel.class: ${SRC}/gui/GameModeSelectionPanel.java ${PACKAGE}/components/Title.class ${PACKAGE}/utils/Database.class ${PACKAGE}/gui/MainMenu.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/GameModeSelectionPanel.java

${PACKAGE}/gui/GameControlsMenu.class: ${SRC}/gui/GameControlsMenu.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/GameControlsMenu.java


${PACKAGE}/gui/BarChartPanel.class: ${SRC}/gui/BarChartPanel.java 
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/gui/BarChartPanel.java

################################
# -----------------------------#
################################
# Compilation des game

${PACKAGE}/game/TilePanningTransition.class: ${SRC}/game/TilePanningTransition.java ${PACKAGE}/game/Board.class 
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/game/TilePanningTransition.java

${PACKAGE}/game/ScoreManager.class ${PACKAGE}/game/Tile.class ${PACKAGE}/utils/HexagonDrawer.class ${PACKAGE}/game/Pocket.class ${PACKAGE}/game/Board.class ${PACKAGE}/game/Cell.class: ${SRC}/game/ScoreManager.java ${SRC}/game/Tile.java ${SRC}/utils/HexagonDrawer.java ${SRC}/game/Pocket.java ${SRC}/game/Board.java ${SRC}/game/Cell.java ${PACKAGE}/enums/Biome.class ${PACKAGE}/enums/TileOrientation.class ${PACKAGE}/utils/Hexagon.class ${PACKAGE}/game/Game.class ${PACKAGE}/utils/Database.class ${PACKAGE}/game/RemainingTilesIndicator.class ${PACKAGE}/gui/GameControlsMenu.class ${PACKAGE}/game/ScoreDisplay.class ${PACKAGE}/Options.class ${PACKAGE}/enums/Fonts.class ${PACKAGE}/gui/GameOver.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/game/ScoreManager.java ${SRC}/game/Tile.java ${SRC}/utils/HexagonDrawer.java ${SRC}/game/Pocket.java ${SRC}/game/Board.java ${SRC}/game/Cell.java

${PACKAGE}/game/ScoreDisplay.class: ${SRC}/game/ScoreDisplay.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/game/ScoreDisplay.java

${PACKAGE}/game/RemainingTilesIndicator.class: ${SRC}/game/RemainingTilesIndicator.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/game/RemainingTilesIndicator.java

${PACKAGE}/game/PauseGame.class: ${SRC}/game/PauseGame.java ${PACKAGE}/Options.class ${PACKAGE}/game/EscapeMenu.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/game/PauseGame.java

${PACKAGE}/game/Game.class: ${SRC}/game/Game.java
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/game/Game.java

${PACKAGE}/game/EscapeMenu.class: ${SRC}/game/EscapeMenu.java ${PACKAGE}/components/Title.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/game/EscapeMenu.java

${PACKAGE}/game/CustomMouseMotionAdapter.class: ${SRC}/game/CustomMouseMotionAdapter.java ${PACKAGE}/game/Board.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/game/CustomMouseMotionAdapter.java

JCFLAGS = -d build -classpath "libs/mariadb-client.jar:build" -sourcepath src

${PACKAGE}/game/CustomKeyAdapter.class: ${SRC}/game/CustomKeyAdapter.java ${PACKAGE}/game/Board.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/game/CustomKeyAdapter.java

################################
# -----------------------------#
################################
# Compilation des controllers

${PACKAGE}/controller/TutorialController.class: ${SRC}/controller/TutorialController.java ${PACKAGE}/gui/TutorialPanel.class ${PACKAGE}/gui/Step.class ${PACKAGE}/enums/Images.class ${PACKAGE}/gui/MainMenu.class ${PACKAGE}/gui/TutorialPanel.class ${PACKAGE}/gui/MainMenu.class 
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/controller/TutorialController.java

${PACKAGE}/controller/MainMenuResizeHandler.class: ${SRC}/controller/MainMenuResizeHandler.java ${PACKAGE}/gui/MainMenu.class ${PACKAGE}/Options.class ${PACKAGE}/gui/ButtonHoverAnimator.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/controller/MainMenuResizeHandler.java

${PACKAGE}/controller/MainMenuResizeController.class: ${SRC}/controller/MainMenuResizeController.java ${PACKAGE}/gui/MainMenu.class ${PACKAGE}/controller/MainMenuResizeHandler.class
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/controller/MainMenuResizeController.java

${PACKAGE}/controller/MainMenuButtonController.class: ${SRC}/controller/MainMenuButtonController.java ${PACKAGE}/gui/MainMenu.class ${PACKAGE}/gui/ButtonPanel.class 
	@echo "Compiling $<..."
	@${JC} ${JCFLAGS} ${SRC}/controller/MainMenuButtonController.java

# Cibles supplémentaires
run: 
	${JVM} ${JVMFLAGS} ${ENTRY}

clean:
	rm -rf build
	rm -rf docs

# Génération du fichier .jar
jar: ${PACKAGE}/Main.class
	jar cvfm ${JARNAME} Manifest.MF -C build/ . -C libs/ . ./ressources

seedoc:
	firefox ./docs/index.html &

# Documentation JavaDoc
javadoc:
	javadoc -d docs -sourcepath src -subpackages fr.monkhanny.dorfromantik -classpath libs/mariadb-client.jar

