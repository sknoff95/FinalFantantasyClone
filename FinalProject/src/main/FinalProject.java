package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javagames.util.*;
import javagames.Vectors.*;
import javagames.Sprites.*;

public class FinalProject extends SimpleFramework {

	private Vector2f mousePosition;
	private IslandMap island = new IslandMap("IslandMap.png");
	private Cottage cottage = new Cottage("Cottage.png");
	private OverworldCharacter overChar = new OverworldCharacter("OverworldCharacter.png");
	private boolean getReverse = false;
	private boolean renderHitboxes = false;
	private float gameState = 0;
	
	//Sets all of the initial variables which define the canvas space
	public FinalProject() {
		appBackground = Color.GREEN;
		appBorder = Color.BLACK;
		appFont = new Font("Courier New", Font.PLAIN, 14);
		appBorderScale = 1f;
		appFPSColor = Color.BLACK;
		
		//Starting canvas size 
		appWidth = 1280;
		appHeight = 720;
		
		appMaintainRatio = true;
		appSleep = 10L;
		appTitle = "Final Project";
		
		//Aspect ratio 
		appWorldWidth = 16.0f;
		appWorldHeight = 9.0f;
	}
	@Override
	protected void initialize() {
		super.initialize();
		island.setColor(Color.RED);
		
		//Background Hitboxes
	}

	@Override
	protected void processInput(float delta) {
		super.processInput(delta);
		
		//keyboard input for overworld character movement
		if(gameState == 0){
				overChar.setDeltaX(0);
				overChar.setDeltaY(0);
				overChar.setAction(0);
				overChar.addTime(delta);
				
				if(keyboard.keyDown(KeyEvent.VK_D)){					
					//all buttons pressed D,W,A,S, do nothing
					if(keyboard.keyDown(KeyEvent.VK_W) && keyboard.keyDown(KeyEvent.VK_A) && keyboard.keyDown(KeyEvent.VK_S)){
						overChar.setDeltaX(0);
						overChar.setAction(0);
					}
					//D,W,A
					else if(keyboard.keyDown(KeyEvent.VK_W) && keyboard.keyDown(KeyEvent.VK_A)){
						overChar.setDeltaY(2);
						overChar.setDeltaX(0);
					}
					//D,W,S
					else if(keyboard.keyDown(KeyEvent.VK_W) && keyboard.keyDown(KeyEvent.VK_S)){
						overChar.setDeltaX(2);
						getReverse = false;
					}
					//D,A,S
					else if(keyboard.keyDown(KeyEvent.VK_A) && keyboard.keyDown(KeyEvent.VK_S)){
						overChar.setDeltaY(-2);
					}
					//D,W
					else if(keyboard.keyDown(KeyEvent.VK_W)){
						overChar.setDeltaY(1.4f);
						overChar.setDeltaX(1.4f);
						getReverse = false;
					}
					//D,A
					else if(keyboard.keyDown(KeyEvent.VK_A)){
						overChar.setAction(0);
						overChar.setDeltaX(0);
					}
					//D,S
					else if(keyboard.keyDown(KeyEvent.VK_S)){
						overChar.setDeltaY(-1.4f);
						overChar.setDeltaX(1.4f);
						getReverse = false;
					}
					//D
					else{
						overChar.setAction(1);
						overChar.setDeltaX(2);
						getReverse = false;
					}
				}
				//All key combos that do not contain D
				else if(keyboard.keyDown(KeyEvent.VK_A)){
					getReverse = true;
					overChar.setAction(1);
					//A,W,S
					if(keyboard.keyDown(KeyEvent.VK_W) && keyboard.keyDown(KeyEvent.VK_S)){
						overChar.setDeltaX(-2);
					}
					//A,W
					else if(keyboard.keyDown(KeyEvent.VK_W)){
						overChar.setDeltaY(1.4f);
						overChar.setDeltaX(-1.4f);
					}
					//A,S
					else if(keyboard.keyDown(KeyEvent.VK_S)){
						overChar.setDeltaY(-1.4f);
						overChar.setDeltaX(-1.4f);
					}
					//A
					else{
						overChar.setDeltaX(-2);
					}
				}
				//Key combos that do not contain D,A
				else if(keyboard.keyDown(KeyEvent.VK_W)){
					
					//W,S
					if(keyboard.keyDown(KeyEvent.VK_S)){
						overChar.setAction(0);
						overChar.setDeltaY(0);
					}
					//W
					else{

						overChar.setAction(1);
						overChar.setDeltaY(2);
					}
				}
				//S
				else if(keyboard.keyDown(KeyEvent.VK_S)){
					overChar.setAction(1);
					overChar.setDeltaY(-2);
				}
		}
		else if(gameState == 1){
			
		}
	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
		overChar.updatePosition(delta);
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);
		Matrix3x3f worldViewport = getViewportTransform();
		
		//renders background, then everything else
		if(gameState == 0){
			island.renderBackground(g, canvas.getWidth(), canvas.getHeight());
			overChar.render(g, worldViewport, canvas.getWidth(), canvas.getHeight(), getReverse);
		}
		else if(gameState == 1){
			cottage.renderBackground(g, canvas.getWidth(), canvas.getHeight());
		}
		//only renders hitbox if b was pressed
		if(renderHitboxes == true){
			island.renderHitboxes(g, worldViewport);
			overChar.renderHitboxes(g, worldViewport);
		}
	}
	
	private void disableCursor() {
	      Toolkit tk = Toolkit.getDefaultToolkit();
	      Image image = tk.createImage( "" );
	      Point point = new Point( 0, 0 );
	      String name = "CanBeAnything";
	      Cursor cursor = tk.createCustomCursor( image, point, name );
	      setCursor( cursor );
	   }

	@Override
	protected void terminate() {
		super.terminate();
	}

	public static void main(String[] args) {
		launchApp(new FinalProject());
	}
}