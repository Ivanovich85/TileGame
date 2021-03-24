package dev.ChiChiOGames.TileGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys, justPressed, cantPress;
	
	public boolean up, down, left, right, hide;
	public boolean aUp, aDown, aLeft, aRight;
	

	

	public KeyManager() {
		this.keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}
	
	public void tick() {
		for(int i=0; i< keys.length; i++) {
			if(cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			}else if(justPressed[i]) {
				cantPress[i] =true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
		
		
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		hide = keys[KeyEvent.VK_T];
		
//		movement with the use of arrows
//		up = keys[KeyEvent.VK_UP];
//		down = keys[KeyEvent.VK_DOWN];
//		left = keys[KeyEvent.VK_LEFT];
//		right = keys[KeyEvent.VK_RIGHT];
		
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];		
	}

	public boolean keyJustPressed(int keyCode) {
		if(keyCode <0 || keyCode >= keys.length) {
			return false;
		}
		return justPressed[keyCode];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode()>= keys.length) {
			return;
		}
		keys[e.getKeyCode()]= true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]= false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	//Setters and Getters
	public boolean[] getKeys() {
		return keys;
	}

	public void setKeys(boolean[] keys) {
		this.keys = keys;
	}

	public boolean[] getJustPressed() {
		return justPressed;
	}

	public void setJustPressed(boolean[] justPressed) {
		this.justPressed = justPressed;
	}

	public boolean[] getCantPress() {
		return cantPress;
	}

	public void setCantPress(boolean[] cantPress) {
		this.cantPress = cantPress;
	}
}
