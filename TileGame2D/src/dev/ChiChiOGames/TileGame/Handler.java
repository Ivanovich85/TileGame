package dev.ChiChiOGames.TileGame;

import dev.ChiChiOGames.TileGame.gfx.GameCamera;
import dev.ChiChiOGames.TileGame.input.KeyManager;
import dev.ChiChiOGames.TileGame.input.MouseManager;
import dev.ChiChiOGames.TileGame.world.World;

public class Handler {

	private Game game;
	private World world;
	
	public Handler(Game game) {
		
		this.game = game;
//		this.world = world;
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	
	
	
	
}
