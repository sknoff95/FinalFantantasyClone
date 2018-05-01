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
	private Inventory inventory = new Inventory();
	private OverworldCharacter overChar = new OverworldCharacter("knight.png");
	private boolean getReverse = false;
	private boolean renderHitboxes = true;
	private int gameState = 3;
	private float xSpeed = 0;
	private float ySpeed = 0;
	private final int NUMBER_OF_GAMESTATES = 6;
	//Gamestate 0 = overworld
	//Gamestate 1 = cave
	//Gamestate 2 = cottage
	//Gamestate 3 = castle
	//Gamestate 4 = battle
	//Gamestate 5 = main menu
	
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
		
		if(keyboard.keyDownOnce(KeyEvent.VK_B)){
			gameState = 4;
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_SPACE)){
			renderHitboxes = !renderHitboxes;
		}
		
		//keyboard input for overworld character movement
		if(gameState != 2 && gameState != 4){
			xSpeed = 0;
			ySpeed = 0;
			
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
		//Keyboard input for cottage menu selection
		else if(gameState == 2){
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
				if(cottage.getSelectedOption() == 1){
					inventory.buyPotion();
				}
				else if(cottage.getSelectedOption() == 2){
					inventory.buyScroll();
				}
				else if(cottage.getSelectedOption() == 4){
					gameState = 0;
					overChar.setPosition(0, 0);
				}
			}
		}
		//Keyboard input for battle menu selection
		else if(gameState == 4){
			if(keyboard.keyDownOnce(KeyEvent.VK_E)){
				gameState = battle.exitBattle();
			}
		}
	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
		boolean intersect;
		//Overworld character movement speed updates
		if(gameState != 2 && gameState != 4 && gameState != 5){
			if(xSpeed != 0 && ySpeed != 0){
				xSpeed = xSpeed*0.707f;
				ySpeed = ySpeed*0.707f;
			}
			
			if(ySpeed < 0){
				overChar.setAction(1);
				overChar.addTime(delta);
			}
			else if(ySpeed > 0){
				overChar.setAction(2);
				overChar.addTime(delta);
			}
			else if(xSpeed < 0){
				overChar.setAction(3);
				overChar.addTime(delta);
			}
			else if(xSpeed > 0){
				overChar.setAction(4);
				overChar.addTime(delta);
			}
			else{
				overChar.setAction(0);
			}
			
			overChar.setDeltaX(xSpeed);
			overChar.setDeltaY(ySpeed);
			overChar.updatePosition(delta);
			overChar.updateHitbox();
			
			//Check if character is going into doorway and swap screen accordingly
			//Doorways in the overworld
			if(gameState == 0 && island.checkPortalHitboxes(overChar.getRectHitboxes().get(0), overChar.getRectHitboxes().get(1))){
				//Player entered cave (send to cave)
				if(island.getPortal() == "Cave"){
					gameState = 1; 
					overChar.setPosition(-0.3f,-3.5f);
					overChar.updateHitbox();
				}
				//Player entered cottage (send to cottage menu)
				else if(island.getPortal() == "Cottage"){
					gameState = 2;
				}
				//Player entered castle (send to castle)
				else if(island.getPortal() == "Castle"){
					gameState = 3;
					overChar.setPosition(0, -3.5f);
					overChar.updateHitbox();
				}
			}
			//Doorway in the cave (send back to overworld)
			else if(gameState == 1 && cave.checkPortalHitboxes(overChar.getRectHitboxes().get(0), overChar.getRectHitboxes().get(1))){
				gameState = 0;
				overChar.setPosition(-5.3f,0.4f);
				overChar.updateHitbox();
			}
			//Doorway in the castle (send back to overworld
			else if(gameState == 3 && castle.checkPortalHitboxes(overChar.getRectHitboxes().get(0), overChar.getRectHitboxes().get(1))){
				if(castle.getBossBattle()){
					gameState = 4;
					overChar.setPosition(0, -1f);
				}
				else{
					gameState = 0;
					overChar.setPosition(5.3f, 2.3f);
					overChar.updateHitbox();
				}
			}
			
			//Check if character is clipping into any boundary hitboxes and move accordingly so that they no longer collide
			//Overworld intersection
			if(gameState == 0){
				do{
					intersect = island.checkHitboxes(overChar.getRectHitboxes().get(0), overChar.getRectHitboxes().get(1));
					if(intersect == true){
						overChar.setPosition((float)(overChar.getPosition().x-overChar.getDeltaX()*.001), (float)(overChar.getPosition().y-overChar.getDeltaY()*.001));
						overChar.updateHitbox();
					}
				}while(intersect == true);
				
				island.checkBiomeHitboxes(overChar.getCenter());
				//Set biome for selecting battle background
				battle.setBiome(island.getBiome());
			}
			//Cave intersection
			else if(gameState == 1){
				do{
					intersect = cave.checkHitboxes(overChar.getRectHitboxes().get(0), overChar.getRectHitboxes().get(1));
					if(intersect == true){
						overChar.setPosition((float)(overChar.getPosition().x-overChar.getDeltaX()*.001), (float)(overChar.getPosition().y-overChar.getDeltaY()*.001));
						overChar.updateHitbox();
					}
				}while(intersect == true);
				//Set biome for selecting battle background
				battle.setBiome("Cave");
			}
			//Castle intersection
			else if(gameState == 3){
				do{
					intersect = castle.checkHitboxes(overChar.getRectHitboxes().get(0), overChar.getRectHitboxes().get(1));
					if(intersect == true){
						overChar.setPosition((float)(overChar.getPosition().x-overChar.getDeltaX()*.001), (float)(overChar.getPosition().y-overChar.getDeltaY()*.001));
						overChar.updateHitbox();
					}
				}while(intersect == true);
				//Set biome for selecting battle background
				battle.setBiome("Castle");
			}
		}
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);
		Matrix3x3f worldViewport = getViewportTransform();
		int w = canvas.getWidth();
		int h = canvas.getHeight();
		
		//renders background, then everything else
		if(gameState == 0 || gameState == 5){
			island.renderBackground(g, w, h);
		}
		else if(gameState == 1){
			cave.renderBackground(g, w, h);
		}
		else if(gameState == 2){
			cottage.renderBackground(g, w, h, inventory.getNumPotions(), inventory.getNumScrolls(), inventory.getGold());
		}
		else if(gameState == 3){
			castle.renderBackground(g, w, h);
		}
		else if(gameState == 4){
			battle.renderBackground(g, w, h);
		}
		
		//Renders overworld character in appropriate gameStates
		if(gameState != 2 && gameState != 4 && gameState != 5){
			overChar.render(g, worldViewport, w, h, getReverse);
		}
		
		//Renders start menu
		if(gameState == 5){
			
		}
		
		
		//only renders hitbox if spacebar was pressed
		if(renderHitboxes == true){
			
			if(gameState == 0){
				island.renderHitboxes(g, worldViewport);
				overChar.renderHitboxes(g, worldViewport);
			}
			else if(gameState == 1){
				cave.renderHitboxes(g, worldViewport);
				overChar.renderHitboxes(g, worldViewport);
			}
			else if(gameState == 3){
				castle.renderHitboxes(g, worldViewport);
				overChar.renderHitboxes(g, worldViewport);
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