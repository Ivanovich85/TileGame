package dev.ChiChiOGames.TileGame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	//Static stuff start can be a class
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);//setting the id to 0 for grass
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	
	
	//end
	
	
	public static final int TILEWIDTH =64, TILEHEIGHT=64;
	
	protected BufferedImage texture;
	protected final int ID;
	
	public Tile (BufferedImage texture, int id) {
		this.texture = texture;
		this.ID = id;
		
		tiles[ID] = this;
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;//allowed to walk on it.
	}

	public int getID() {
		return ID;
	}
	
}
