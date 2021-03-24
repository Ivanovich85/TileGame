package dev.ChiChiOGames.TileGame.states;

import java.awt.Graphics;

import dev.ChiChiOGames.TileGame.Game;
import dev.ChiChiOGames.TileGame.Handler;

public abstract class State {
	//start
	//Game State Manager
	private static State currentState = null;
	
	
	
	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {
		State.currentState = currentState;
	}
	//end
	
	protected Handler handler;
	
	
	
	public State(Handler handler) {
		this.handler = handler;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	
	
}
