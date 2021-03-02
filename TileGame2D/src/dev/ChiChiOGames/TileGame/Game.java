package dev.ChiChiOGames.TileGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.ChiChiOGames.TileGame.Display.Display;
import dev.ChiChiOGames.TileGame.gfx.Assets;
import dev.ChiChiOGames.TileGame.gfx.ImageLoader;
import dev.ChiChiOGames.TileGame.gfx.SpriteSheet;

//holds the start run and close everything
public class Game implements Runnable{
	
	private Display display;
	
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	
//	private BufferedImage testImage;//test code
//	private SpriteSheet sheet;//test code
	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		
	}
	
	private void init() {
		display = new Display(title, width, height);
		Assets.init();
		
//		testImage = ImageLoader.loadImage("/textures/faceSheet.png");// test code
//		sheet = new SpriteSheet(testImage);//test code
	}
	
	
	
	private void tick() {//this will be what updates the game
		
	}
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);//clear screen
//start Draw 
		
		g.drawImage(Assets.grass, 0,0, null);
		g.drawImage(Assets.dirt, 110,0, null);
		g.drawImage(Assets.stone, 220,0, null);
		g.drawImage(Assets.tree, 5,110, null);
		g.drawImage(Assets.player, 110,110, null);
		
//		g.drawImage(sheet.crop(40, 0, 21, 21), 20, 20, null);//test code
		
//end Draw
		bs.show();
		g.dispose();
	}
	public void run() {
		
		init();
		
		while(running) {
			tick();
			render();
		}
	}
	
	public synchronized void start() {
		if(running) {return;}//checks if the game is already running
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	public synchronized void stop() {
		if(!running) {return;}//checks if game is already stopped
		running = false;
		
		try {
			thread.join();// close the thread safely
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
