package javagames.battle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javagames.util.SimpleFramework;
import javagames.util.Utility;

public class Battle extends SimpleFramework {
	
	private BattleScreen swag;
	
	public Battle() {
		appBackground = Color.WHITE;
		appBorder = Color.LIGHT_GRAY;
		appFont = new Font("Courier New", Font.PLAIN, 14);
		appBorderScale = 1.0f;
		appFPSColor = Color.BLACK;
		appWidth = 1280;
		appHeight = 720;
		appMaintainRatio = true;
		appSleep = 10L;
		appTitle = "FramworkTemplate";
		appWorldWidth = 16.0f;
		appWorldHeight = 9.0f;
	}

	@Override
	protected void initialize() {
		super.initialize();
		swag = new BattleScreen(Utility.createViewport(appWorldWidth, appWorldHeight, appWidth, appHeight), appWorldHeight);
	}

	@Override
	protected void processInput(float delta) {
		super.processInput(delta);
	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
		
		swag.setViewport(getViewportTransform());
		swag.updateObjects(delta);
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);
		
		swag.render(g);
	}

	@Override
	protected void terminate() {
		super.terminate();
	}

	public static void main(String[] args) {
		launchApp(new Battle());
	}
}