package dev.ChiChiOGames.TileGame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 100, height = 100;
	
	public static Font font28;//The 28 is for tracking the size of the font
	
	public static BufferedImage guy, dirt, grass, stone, smallTree, bigtree, player, hit;
	public static BufferedImage diamond, iron, gold, rock, wood;
	
	public static BufferedImage[] player_down, player_up, player_right, player_left;
	
	public static BufferedImage[] menu_btu;
	public static BufferedImage inventoryScreen;
	
	
	
	public static void init() {
		font28 = FontLoader.loadFont("resocurces/fonts/slkscr.ttf", 28);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spriteSheet.png"));
		SpriteSheet enemySheet = new SpriteSheet(ImageLoader.loadImage("/textures/attackSpriteSheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
	//player entities
		guy = sheet.crop(width*4+1, 1, width-2, height-1);
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
		
	//Menu images
		menu_btu = new BufferedImage[2];
		menu_btu[0]= sheet.crop(width+1, height*3+1, width-1, height-1);
		menu_btu[1]= sheet.crop(width *2+1, height*3+1, width-1, height-1);
		
	//static entities
		dirt = sheet.crop(1, 1, width-1, height-1);
		grass = sheet.crop(width+1, 1, width-1, height-1);
		stone = sheet.crop(width*2+1, 1, width-1, height-1);
		smallTree = sheet.crop(width*3+1, 1, width-1, height-1);
		bigtree = sheet.crop(1, height*3+1, width-1, height*2-2);
		diamond = sheet.crop(width*2+1, height*4 +1, width-1, height -2);
		iron = sheet.crop(width*4+1, height*4 +1, width -1, height-2);
		gold = sheet.crop(width*3 +1, height*4 +1, width-1, height-2);
		rock = sheet.crop(width+1, height*4 +1, width-1, height-2);
		wood = enemySheet.crop(width +1, height * 2+1, width-1, height-2);
		
		
		
		//attack
		hit = enemySheet.crop(1, height * 3, width-1, height-1);
		
	}

}
