package dev.ChiChiOGames.TileGame.entity.creature.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.ChiChiOGames.TileGame.Game;
import dev.ChiChiOGames.TileGame.Handler;
import dev.ChiChiOGames.TileGame.entity.Entity;
import dev.ChiChiOGames.TileGame.entity.creature.Creature;
import dev.ChiChiOGames.TileGame.gfx.Animation;
import dev.ChiChiOGames.TileGame.gfx.Assets;
import dev.ChiChiOGames.TileGame.inventory.Inventory;

public class Player extends Creature{

	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	//Attack timer
	private long lastAttackTimer, attackCoolDown = 500, attackTimer = attackCoolDown;
	//Inventory
	private Inventory inventory;
//	int[] locationVals;
	
	public Player(Handler handler, float positionX, float positionY) {
		
		super(handler, positionX, positionY, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		bounds.x = 23;
		bounds.y = 32;
		bounds.width = 15;
		bounds.height = 25;
		
		//Animations
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		//Attacks
		checkAttacks();
		//Inventory
		inventory.tick();
	}
	
	@Override
	public void render(Graphics g) {
		
		
		g.drawImage(getCurrentAnimationFrame(), (int)(positionX - handler.getGameCamera().getxOffset()), 
				(int)(positionY - handler.getGameCamera().getyOffset()), width, height, null);
//		test code		
//		if(underAttack) {
//			g.drawImage(Assets.hit,locationVals[0],locationVals[1], width, height, null);
//			underAttack = false;
//		}
		
		
		
		
		
//		Code to see the collision detection rectangle
//		g.setColor(Color.red);
//		g.fillRect((int)(positionX+bounds.x - handler.getGameCamera().getxOffset()), 
//				(int)(positionY+bounds.y - handler.getGameCamera().getyOffset()),bounds.width, bounds.height);
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
//	test code	
//	public void attackLocation(int recX, int recY, int recWidth, int recHeight) {
//		locationVals = new int[] {recX, recY, recWidth, recHeight};
//		
//	} 
	@Override
	public void die() {
		System.out.println("You died try again");
	}
	
	public void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCoolDown) {
			return;
		}
		
		if(inventory.isActive()) {
			return;
		}
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height/2 - arSize/2;
		}else if(handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height/2 - arSize/2;
		}else {
//			locationVals=null;
			return;
		}
		attackTimer=0;//since player will attack we reset the attackTimer
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
//				attackLocation(ar.x, ar.y, cb.width, cb.height);//test code
				return;
			}
		}
		
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(inventory.isActive()) {
			return;
		}
		
		if(handler.getKeyManager().up) {
			yMove = -speed;
		}
		if(handler.getKeyManager().down) {
			yMove = +3;
		}
		if(handler.getKeyManager().left) {
			xMove = -3;
		}
		if(handler.getKeyManager().right) {
			xMove = +3;
		}
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove <0) {
			return animLeft.getCurrentFrame();
		}
		else if(xMove>0) {
			return animRight.getCurrentFrame();
		}else if(yMove<0) {
			return animUp.getCurrentFrame();
		}else if(yMove>0) {
			return animDown.getCurrentFrame();
		}else if(handler.getKeyManager().hide) {
			return Assets.smallTree;
		}else {
			//standing need to be updated with a standing img
			return Assets.player;
		}
	}

	//Setters and Getters
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	

}
