package dev.ChiChiOGames.TileGame.tiles;

import dev.ChiChiOGames.TileGame.gfx.Assets;

public class RockTile extends Tile{
	
	

	public RockTile(int id) {
		super(Assets.stone, id);
		
	}
	
	@Override
	public boolean isSolid() {
		return true;//Disable creaters from walking on it
	}

}
