package dev.ChiChiOGames.TileGame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 100, height = 100;
	
	public static BufferedImage guy, dirt, grass, stone, tree, player;
	public static BufferedImage[] player_down, player_up, player_right, player_left;
	
	
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spriteSheet.png"));
		
		//Player Moving images
		player_down = new BufferedImage[2];
		player_down[0] = sheet.crop(1, height+1, width-1, height-1);
		player_down[1] = sheet.crop(width+1, height+1, width-1, height-1);
		
		player_up = new BufferedImage[2];
		player_up[0] = sheet.crop(width*2 +1, height+1, width-1, height-1);
		player_up[1] = sheet.crop(width*3 +1, height+1, width-1, height-1);
		
		player_right = new BufferedImage[2];
		player_right[0] = sheet.crop(1, height*2+1, width-1, height-1);
		player_right[1] = sheet.crop(width+1, height*2+1, width-1, height-1);
		
		player_left = new BufferedImage[2];
		player_left[0] = sheet.crop(width*2 +1, height*2+1, width-1, height-1);
		player_left[1] = sheet.crop(width*3 +1, height*2+1, width-1, height-1);
		//Standing 
		player = sheet.crop(width*4 +1, height+1, width-2, height-1);
		
				
		//blocks without borders
		dirt = sheet.crop(1, 1, width-1, height-1);
		grass = sheet.crop(width+1, 1, width-1, height-1);
		stone = sheet.crop(width*2+1, 1, width-1, height-1);
		tree = sheet.crop(width*3+1, 1, width-1, height-1);
		guy = sheet.crop(width*4+1, 1, width-2, height-1);
		
//		//blocks with borders
//		dirt = sheet.crop(0, 0, width +1, height+1);
//		grass = sheet.crop(width, 0, width+1, height+1);
//		stone = sheet.crop(width*2, 0, width+1, height+1);
//		tree = sheet.crop(width*3, 0, width+1, height+1);
//		player = sheet.crop(width*4, 0, width, height+1);
	}

}
