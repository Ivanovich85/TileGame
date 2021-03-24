package dev.ChiChiOGames.TileGame.entity.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.ChiChiOGames.TileGame.Handler;
import dev.ChiChiOGames.TileGame.gfx.Assets;
import dev.ChiChiOGames.TileGame.items.Item;
import dev.ChiChiOGames.TileGame.tiles.Tile;

public class Rock extends StaticEntity{

	public Rock(Handler handler, float positionX, float positionY) {
		super(handler, positionX, positionY, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x =(int)(width/5.5f);//how far right
		bounds.y =(int) ((height)/1.6f);//how far down
		bounds.width= (int)(width/1.85);//how wide
		bounds.height = (int) (height/8f);//how long
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int)positionX, (int)positionY));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock, (int)(positionX - handler.getGameCamera().getxOffset()), (int)(positionY - handler.getGameCamera().getyOffset()), width, height, null);
		
//		Code to see the collision detection rectangle
		g.setColor(Color.red);
		g.fillRect((int)(positionX+bounds.x - handler.getGameCamera().getxOffset()), 
				(int)(positionY+bounds.y - handler.getGameCamera().getyOffset()),bounds.width, bounds.height);
	}
	

}
