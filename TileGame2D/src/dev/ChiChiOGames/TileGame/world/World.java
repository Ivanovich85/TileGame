package dev.ChiChiOGames.TileGame.world;

import java.awt.Graphics;

import dev.ChiChiOGames.TileGame.Handler;
import dev.ChiChiOGames.TileGame.entity.EntityManager;
import dev.ChiChiOGames.TileGame.entity.creature.player.Player;
import dev.ChiChiOGames.TileGame.entity.statics.Rock;
import dev.ChiChiOGames.TileGame.entity.statics.Tree;
import dev.ChiChiOGames.TileGame.items.ItemManager;
import dev.ChiChiOGames.TileGame.tiles.Tile;
import dev.ChiChiOGames.TileGame.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] worldsTiles;
	//Entities
	private EntityManager entityManager;
	//Item
	private ItemManager itemManager;
	

	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 200,200));
		itemManager = new ItemManager(handler);
		
		
		entityManager.addEntity(new Tree(handler, 200, 300));
		entityManager.addEntity(new Rock(handler, 200, 500));
		entityManager.addEntity(new Tree(handler, 1200, 300));
		entityManager.addEntity(new Rock(handler, 400, 1500));
		entityManager.addEntity(new Tree(handler, 250, 300));
		entityManager.addEntity(new Rock(handler, 100, 450));
		
		loadWorld(path);
		
		entityManager.getPlayer().setPositionX(spawnX);
		entityManager.getPlayer().setPositionY(spawnY);
	}
	
	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}

	public void render(Graphics g) {
		
		int xStart = (int)Math.max(0, handler.getGameCamera().getxOffset()/ Tile.TILEWIDTH);
		int yStart = (int)Math.max(0, handler.getGameCamera().getyOffset()/ Tile.TILEHEIGHT);
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth())/ Tile.TILEWIDTH +1);
		int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight())/ Tile.TILEHEIGHT +1);
		
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x,y).render(g, (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), 
						(int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
		
	}
	
	private void loadWorld(String path) {
		
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");// "\\s+" any white space or new line"\n" character 
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		worldsTiles = new int[width][height];
		for(int y =0; y<height;y++) {
			for(int x =0; x<width;x++) {
				worldsTiles[x][y] = Utils.parseInt(tokens[(x+y*width)+4]);
			}
		}
		
	}
	
	

	public Tile getTile(int x, int y) {
		
		if(x<0||y<0||x>=width||y>=height) {// if the player is outside the map they will be standing on a grass tile
			return Tile.grassTile;
		}
		
		Tile t =  Tile.tiles[worldsTiles[x][y]];
		if(t == null) {
			return Tile.dirtTile;
		}else {
			return t;
		}
	}
	
	
	//setters and getters
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
	

}
