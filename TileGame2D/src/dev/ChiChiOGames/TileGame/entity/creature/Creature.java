package dev.ChiChiOGames.TileGame.entity.creature;

import dev.ChiChiOGames.TileGame.Game;
import dev.ChiChiOGames.TileGame.Handler;
import dev.ChiChiOGames.TileGame.entity.Entity;
import dev.ChiChiOGames.TileGame.tiles.Tile;

public abstract class Creature extends Entity {
	
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float speed;
	protected float xMove, yMove;

	public Creature(Handler handler, float positionX, float positionY, int width, int height) {
		super(handler, positionX, positionY, width, height);
		
		speed = DEFAULT_SPEED;
	}

	public void move() {
		//checking if we can move to the block that we are trying to move into
		if(!checkEntityCollisions(xMove, 0f)) {
			moveX();
		}
		if(!checkEntityCollisions(0f, yMove)) {
			moveY();
		}
		

	}

	public void moveX() {
		if (xMove > 0) {// moving right
			int tx = (int) (positionX + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (positionY + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (positionY + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				positionX += xMove;
			}else {
				positionX = tx * Tile.TILEWIDTH - bounds.x - bounds.width -1;
			}

		} else if (xMove < 0) {// moving left
			int tx = (int) (positionX + xMove + bounds.x ) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (positionY + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (positionY + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				positionX += xMove;
			}else {
				positionX = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}

	}

	public void moveY() {
		if(yMove < 0 ) {//moving up
			int ty = (int)(positionY + yMove +bounds.y)/Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int)(positionX +bounds.x)/Tile.TILEWIDTH, ty)&&
					!collisionWithTile((int)(positionX +bounds.x + bounds.width)/Tile.TILEWIDTH, ty)) {
				positionY += yMove;
			}else {
				positionY = ty * Tile.TILEHEIGHT +Tile.TILEHEIGHT - bounds.y;
			}
		}else if(yMove >0) {//moving down
			int ty = (int)(positionY + yMove +bounds.y +bounds.height)/Tile.TILEHEIGHT;
			if(!collisionWithTile((int)(positionX +bounds.x)/Tile.TILEWIDTH, ty)&&
					!collisionWithTile((int)(positionX +bounds.x + bounds.width)/Tile.TILEWIDTH, ty)) {
				positionY += yMove;
			}else {
				positionY = ty * Tile.TILEHEIGHT - bounds.y - bounds.height -1;
			}
		}
		
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	// Getters and Setters

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
