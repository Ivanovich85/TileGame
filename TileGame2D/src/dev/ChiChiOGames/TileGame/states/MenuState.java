package dev.ChiChiOGames.TileGame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.ChiChiOGames.TileGame.Game;
import dev.ChiChiOGames.TileGame.Handler;
import dev.ChiChiOGames.TileGame.gfx.Assets;
import dev.ChiChiOGames.TileGame.ui.ClickListener;
import dev.ChiChiOGames.TileGame.ui.UIImageButton;
import dev.ChiChiOGames.TileGame.ui.UIManager;

public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.menu_btu, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setCurrentState(handler.getGame().gameState);
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
		
//		switching states using the mouse clicks
//		if(handler.getMouseManager().isLeftPressed()) {
//			State.setCurrentState(handler.getGame().gameState);
//			handler.getMouseManager().setLeftPressed(false);
//		}
	}

	@Override
	public void render(Graphics g) {
		
		uiManager.render(g);
		
//		draw a rectangle that follows the mouse
//		g.setColor(Color.red);
//		g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 18, 18);
	}

}
