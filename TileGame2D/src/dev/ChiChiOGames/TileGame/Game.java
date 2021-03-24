package dev.ChiChiOGames.TileGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.ChiChiOGames.TileGame.Display.Display;
import dev.ChiChiOGames.TileGame.gfx.Assets;
import dev.ChiChiOGames.TileGame.gfx.GameCamera;
import dev.ChiChiOGames.TileGame.gfx.ImageLoader;
import dev.ChiChiOGames.TileGame.gfx.SpriteSheet;
import dev.ChiChiOGames.TileGame.input.KeyManager;
import dev.ChiChiOGames.TileGame.input.MouseManager;
import dev.ChiChiOGames.TileGame.states.GameState;
import dev.ChiChiOGames.TileGame.states.MenuState;
import dev.ChiChiOGames.TileGame.states.SettingsState;
import dev.ChiChiOGames.TileGame.states.State;

//holds the start run and close everything
public class Game implements Runnable{
	
	private Display display;
	
	private int width, height;
	
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States of the game
//	private State gameState;
//	private State menuState;
//	private State settingsState;
	public State gameState;
	public State menuState;
	public State settingsState;
	
	//Inputs
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		settingsState = new SettingsState(handler);

		State.setCurrentState(menuState);
		
		
	}
	
	
	
	private void tick() {//this will be what updates the game
		keyManager.tick();//
		
		if(State.getCurrentState() != null) {
			State.getCurrentState().tick();
		}
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
		if(State.getCurrentState() != null) {
			State.getCurrentState().render(g);
		}

		
//end Draw
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;//Firms Per Second
		double timePerTick = 1000000000 / fps;
		double delta =0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks =0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;//(now - lastTime) get the amount of time that has passed since "now" was updated
			timer += now - lastTime;
			lastTime = now;
			
			
			if(delta >=1) {//checks if a second has passed if so methods are called
				tick();
				render();
				ticks++;
				delta --;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: "+ ticks);
				ticks =0;
				timer =0;
			}
		}
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
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
