package dev.ChiChiOGames.TileGame.entity.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.ChiChiOGames.TileGame.Handler;
import dev.ChiChiOGames.TileGame.gfx.Assets;
import dev.ChiChiOGames.TileGame.items.Item;
import dev.ChiChiOGames.TileGame.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float positionX, float positionY) {
		super(handler, positionX, positionY, Tile.TILEWIDTH, Tile.TILEHEIGHT*2);
		
		bounds.x =(width/2) - (width/6);//how far right
		bounds.y =(int) ((height)/1.2f);//how far down
		bounds.width=width -50;//how wide
		bounds.height = (int) (height/8.0f);//how long
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)positionX, (int)positionY));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bigtree, (int)(positionX - handler.getGameCamera().getxOffset()), (int)(positionY - handler.getGameCamera().getyOffset()), width, height, null);
		
//		Code to see the collision detection rectangle
//		g.setColor(Color.red);
//		g.fillRect((int)(positionX+bounds.x - handler.getGameCamera().getxOffset()), 
//				(int)(positionY+bounds.y - handler.getGameCamera().getyOffset()),bounds.width, bounds.height);
	}
	

}
