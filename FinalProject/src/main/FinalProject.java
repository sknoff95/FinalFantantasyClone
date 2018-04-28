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

	private IslandMap island = new IslandMap("IslandMap.png");
	private Cottage cottage = new Cottage("Cottage.png");
	private Cave cave = new Cave("CaveInterior.png");
	private Castle castle = new Castle("CastleInterior.png");
	private Battle battle = new Battle();
	private OverworldCharacter overChar = new OverworldCharacter("OverworldCharacter.png");
	private boolean getReverse = false;
	private boolean renderHitboxes = true;
	private int gameState = 0;
	private float xSpeed = 0;
	private float ySpeed = 0;
	private final int NUMBER_OF_GAMESTATES = 5;
	
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
		
		//overChar.addRectHitbox(new Vector2f((float)(overChar.getPosition().x+.15), (float)(overChar.getPosition().y-.04)), new Vector2f((float)(overChar.getPosition().x + .52), (float)(overChar.getPosition().y - .77)));
	}

	@Override
	protected void processInput(float delta) {
		super.processInput(delta);
		if(keyboard.keyDownOnce(KeyEvent.VK_P)){
			gameState += 1;
			if(gameState == NUMBER_OF_GAMESTATES){
				gameState = 0;
			}
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_SPACE)){
			renderHitboxes = !renderHitboxes;
		}
		
		//keyboard input for overworld character movement
		if(gameState == 0){
			xSpeed = 0;
			ySpeed = 0;
			overChar.addTime(delta);
			
			if(keyboard.keyDown(KeyEvent.VK_D)){
				xSpeed += 2;
			}
			if(keyboard.keyDown(KeyEvent.VK_A)){
				xSpeed += -2;
			}
			if(keyboard.keyDown(KeyEvent.VK_W)){
				ySpeed += 2;
			}
			if(keyboard.keyDown(KeyEvent.VK_S)){
				ySpeed += -2;
			}
				
				
		}
		else if(gameState == 1){
			//Scrolling up and down the menu options
			if(keyboard.keyDownOnce(KeyEvent.VK_W)){
				cottage.decrementSelectedOption();
			}
			if(keyboard.keyDownOnce(KeyEvent.VK_S)){
				cottage.incrementSelectedOption();
			}
			
			//Selecting an option
			if(keyboard.keyDownOnce(KeyEvent.VK_ENTER)){
				System.out.println(cottage.getSelectedOption());
				if(cottage.getSelectedOption() == 4){
					gameState = 0;
				}
			}
		}
		else if(gameState == 2){
			
		}
		else if(gameState == 3){
			
		}
		else if(gameState == 4){
	
		}
	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
		
		//Overworld character movement speed updates
		if(xSpeed != 0 && ySpeed != 0){
			xSpeed = xSpeed*0.707f;
			ySpeed = ySpeed*0.707f;
		}
		
		if(xSpeed != 0 || ySpeed != 0){
			overChar.setAction(1);
		}
		else{
			overChar.setAction(0);
		}
		
		if(xSpeed < 0){
			getReverse = true;
		}
		else if(xSpeed > 0){
			getReverse = false;
		}
		
		overChar.setDeltaX(xSpeed);
		overChar.setDeltaY(ySpeed);
		overChar.updatePosition(delta);
		
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);
		Matrix3x3f worldViewport = getViewportTransform();
		int w = canvas.getWidth();
		int h = canvas.getHeight();
		
		//renders background, then everything else
		if(gameState == 0){
			island.renderBackground(g, w, h);
			overChar.render(g, worldViewport, w, h, getReverse);
		}
		else if(gameState == 1){
			cottage.renderBackground(g, w, h);
		}
		else if(gameState == 2){
			cave.renderBackground(g, w, h);
		}
		else if(gameState == 3){
			castle.renderBackground(g, w, h);
		}
		else if(gameState == 4){
			battle.renderBackground(g, w, h);
		}
		
		//only renders hitbox if b was pressed
		if(renderHitboxes == true){
			
			if(gameState == 0){
				island.renderHitboxes(g, worldViewport);
				overChar.renderHitboxes(g, worldViewport);
			}
			else if(gameState == 1){
				cottage.renderBackground(g, w, h);
			}
			else if(gameState == 2){
				cave.renderBackground(g, w, h);
			}
			else if(gameState == 3){
				castle.renderBackground(g, w, h);
			}
			else if(gameState == 4){
				battle.renderBackground(g, w, h);
			}
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