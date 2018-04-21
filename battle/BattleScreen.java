package javagames.battle;

import java.awt.Graphics;
import javagames.util.*;

public class BattleScreen {
	
	//Temporary Testing Objects, when the character class is finally used these will be deleted
	private VectorObject[] characters = new VectorObject[3];
	private VectorObject[] enemies = new VectorObject[5];
	private Vector2f[] vectors = new Vector2f[] { 
									new Vector2f(0.0f, 0.0f),
									new Vector2f(-0.25f, 0.5f),
									new Vector2f(0.25f, 0.5f),
									new Vector2f(0.25f, -0.5f),
									new Vector2f(-0.25f, -0.5f)
									};
	// End Temporary Testing Objects
	
	// Initializes the whole battle, calls the methods for places sprites on the screen and determining turn order.
	public BattleScreen(Matrix3x3f viewport, float appWorldHeight){
		
		// These for loops simply create VectorObjects for rendering, 
		// when the actual character class is created this will no longer be necessary.
		for (int x = 0; x < characters.length; x++ ) {
			characters[x] = new VectorObject(viewport, vectors);
		}
		
		for (int x = 0; x < enemies.length; x++ ) {
			enemies[x] = new VectorObject(viewport, vectors);
		}// End VectorObject creation
		
		setPositions(appWorldHeight);
		
	}

	// The number of characters and enemies changes how they are positioned on the battlefield. This method determines and sets those positions.
	public void setPositions(float worldHeight){	
		
		float positioning = worldHeight/(characters.length+1); 	// The positioning float is the vertical distance between characters on one side of the screen,  
																// you must add 1 to the number of characters so that they do not draw partially off screen
		
		for (int x = 0; x < characters.length; x++){
			characters[x].translate(-7.5f, 4.5f - positioning*(x+1));	// Slide characters to the left side of the screen.
		}																// Then slide them down from the top of the screen using the positioning float
		
		positioning = worldHeight/(enemies.length+1);			// Do the same for the enemies
	
		for (int x = 0; x < enemies.length; x++){
			enemies[x].translate(7.5f, 4.5f - positioning*(x+1));
		}
	}
	
	// At the beginning of each turn, the order in which characters go will be determined by speed.
	public void turnOrder(){
		
		// I'll just create an array of all the creatures and use a sorting algorithm to get the turn order.
	
		// If for some reason speed values change in the middle of the battle, this method can just be called again.
	}
	
	// This will bring up the battle menu for the characters and will return the selected action.
	public int selectAttack(){		
		
		// Actions are character specific and are hard coded into sprites.							
		int choice = 0;
		
		/* some code for selecting an action goes here */
		
		return choice;
	}
	
	public void setViewport (Matrix3x3f viewport){
			
		for (int x=0; x < characters.length; x++){
			characters[x].setViewport(viewport);
		}

		for (int x=0; x < enemies.length; x++){
			enemies[x].setViewport(viewport);
		}
	}
	
	public void updateObjects(float delta){
		for (int x=0; x < characters.length; x++){
			characters[x].updateWorld();
		}
		
		for (int x=0; x < enemies.length; x++){
			enemies[x].updateWorld();
		}
	}
	
	public void render(Graphics g){
		
		for (int x=0; x < characters.length; x++){
			characters[x].render(g);
		}
		
		for (int x=0; x < enemies.length; x++){
			enemies[x].render(g);
		}
	}
	
}
