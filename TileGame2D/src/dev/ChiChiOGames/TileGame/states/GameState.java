package dev.ChiChiOGames.TileGame.states;

import java.awt.Graphics;

import dev.ChiChiOGames.TileGame.Game;
import dev.ChiChiOGames.TileGame.Handler;
import dev.ChiChiOGames.TileGame.entity.creature.player.Player;
import dev.ChiChiOGames.TileGame.gfx.Assets;
import dev.ChiChiOGames.TileGame.tiles.Tile;
import dev.ChiChiOGames.TileGame.world.World;

public class GameState extends State{

	private World world;
	
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler,"resocurces/worlds/world1.txt");
		handler.setWorld(world);
		
		
	}

	@Override
	public void tick() {
		world.tick();
		if(handler.getMouseManager().isRightPressed()) {
			State.setCurrentState(handler.getGame().menuState);
			handler.getMouseManager().setRightPressed(false);
		}
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

}
