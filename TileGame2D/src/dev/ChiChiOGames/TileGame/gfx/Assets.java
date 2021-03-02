package dev.ChiChiOGames.TileGame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 100, height = 100;
	
	public static BufferedImage player, dirt, grass, stone, tree;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spriteSheet.png"));
		
		//blocks without borders
		dirt = sheet.crop(1, 1, width-1, height-1);
		grass = sheet.crop(width+1, 1, width-1, height-1);
		stone = sheet.crop(width*2+1, 1, width-1, height-1);
		tree = sheet.crop(width*3+1, 1, width-1, height-1);
		player = sheet.crop(width*4+1, 1, width-2, height-1);
		
//		//blocks with borders
//		dirt = sheet.crop(0, 0, width +1, height+1);
//		grass = sheet.crop(width, 0, width+1, height+1);
//		stone = sheet.crop(width*2, 0, width+1, height+1);
//		tree = sheet.crop(width*3, 0, width+1, height+1);
//		player = sheet.crop(width*4, 0, width, height+1);
	}

}
