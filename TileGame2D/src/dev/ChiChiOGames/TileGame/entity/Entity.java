package dev.ChiChiOGames.TileGame.entity;

import java.awt.Graphics;
import java.awt.Rectangle;


import dev.ChiChiOGames.TileGame.Handler;

public abstract class Entity {
	
	public static final int DEFAULT_HEALTH = 10;
	
	protected Handler handler;
	protected Rectangle bounds;
	
	protected float positionX, positionY;
	protected int width, height;
	protected int health;
	protected boolean active = true;
//	protected boolean underAttack = false;//test code
	


	public Entity(Handler handler, float positionX, float positionY, int width, int height) {
		this.handler = handler;
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
		this.health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void die();
	
	public void hurt(int amt) {
		health -= amt;
//		underAttack = true;//test code
		if(health <=0) {
			active =false;
			die();
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e: handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(positionX + bounds.x + xOffset), (int)(positionY + bounds.y + yOffset), bounds.width, bounds.height);
	}

	public float getPositionX() {
		return positionX;
	}
	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}
	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

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
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
