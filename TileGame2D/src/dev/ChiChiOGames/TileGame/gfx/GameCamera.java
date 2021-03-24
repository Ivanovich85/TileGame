package dev.ChiChiOGames.TileGame.gfx;

import dev.ChiChiOGames.TileGame.Handler;
import dev.ChiChiOGames.TileGame.entity.Entity;
import dev.ChiChiOGames.TileGame.tiles.Tile;

public class GameCamera {
	private float xOffset, yOffset;
	private Handler handler;

	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getPositionX() - handler.getWidth() /2 + e.getWidth()/2;
		yOffset = e.getPositionY() - handler.getHeight() /2 + e.getHeight()/2;
		checkBlankSpace();
	}
	
	public void checkBlankSpace() {
		if(xOffset <0) {
			xOffset =0;
		}else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();//=the edge of the world
		}
		if(yOffset <0) {
			yOffset =0;
		}else if (yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	}
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
	
}
