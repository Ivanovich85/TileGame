package dev.ChiChiOGames.TileGame;

import dev.ChiChiOGames.TileGame.Display.Display;

public class Launcher {
	
	public static void main(String[] args) {
		Game game = new Game("Title of the Game", 400, 400);
		game.start();
	}
}
