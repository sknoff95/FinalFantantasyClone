package javagames.battle;

import java.awt.Color;
import java.awt.Graphics;
import javagames.util.*;
import javagames.characters.*;
import javagames.characters.Character;
import javagames.sound.SoundEngine;

public class BattleScreen {
	
	private float worldHeight;
	private Matrix3x3f viewport;
	private SoundEngine sound = new SoundEngine();
	
	// The arrays needed for separating characters from enemies
	private PlayerCharacter[] characters = new PlayerCharacter[3];
	private Character[] enemies = new Character[3];
	
	// Arrays that contain every character in the battle organized by speed, as well as actions and targets associated with each character
	private Character[] everyone;// The characters themselves			
	private int everyoneAtk[] = new int[characters.length + enemies.length];// The selected actions each character has chosen
	private Character[] everyoneTgt = new Character[characters.length + enemies.length];// array the holds the intended target of a characters action
	
	private int counter = 0; // Runs through the everyone arrays and keeps track of who is currently selecting options or performing actions
	private int selectedOption = 1; // Used for selecting options from the menu
	private int menuValue = 1; // Keeps track of what choices have been made and what menu to display
	private int totalOptions = 1; // This is the max value that selectedOption can be. totalOptions is the number of options on the menu
	private String[] options = {"if you can see this, something is wrong", "", "", "", ""}; // An array that contains the currently displayed menu options
	
	// Three boolean values that determine the state of the game.
	public boolean selectingAction = true;		// The player is selecting an action for a character
	public boolean selectingTgt = false;		// The player is selecting the target of an action
	public boolean performingActions = false;	// The characters of the battle are performing their chosen actions on their chosen targets
	
	/**
	 * Initializes the whole battle, calls the methods to places sprites on the screen and determine the turn order.
	 * @param viewport :
	 * @param appWorldHeight : Needed for calling the setPositions method
	 */
	public BattleScreen(Matrix3x3f viewport, float worldHeight){
		
		sound.initialize();
		sound.LoadClip(1);
		sound.FireLoop();
		this.viewport = viewport;
		this.worldHeight = worldHeight;
		everyone = new Character[characters.length + enemies.length];
		
		characters[0] = new Knight();
		characters[1] = new Rogue();
		characters[2] = new Mage();
		
		enemies[0] = new Goblin(1);
		enemies[1] = new Slime(1);
		enemies[2] = new Wolf(1);

		setPositions(worldHeight);		
		turnOrder();
	}

	/**
	 * Takes the number of units on each side, and evenly spaces them vertically
	 * @param worldHeight : a float that represents the vertical distance of the world coordinates (defined in the main class)
	 */
	public void setPositions(float worldHeight){	
		
		// The positioning float is the vertical distance between characters on the same side,
		// you must add 1 to the number of characters to evenly space them in the center of the screen
		float positioning = worldHeight/(characters.length+1);

		// The Sprite objects require a vector to pass the translation numbers
		Vector2f translate;

		// Go through the characters array and apply all the translations to place them properly on the screen
		for (int x = 0; x < characters.length; x++){
			translate = new Vector2f(-7.5f, 4.5f - positioning*(x+1));
			// Cut out the correct part of the sprite sheet and call the transformation method for the Sprite object.
			characters[x].transform(translate, 0, viewport, 2.0f, 2.0f);
		}
		
		// Do the same for the enemies
		positioning = worldHeight/(enemies.length+1);			
		
		for (int x = 0; x < enemies.length; x++){
			translate = new Vector2f(7.0f, 4.5f - positioning*(x+1));
			enemies[x].transform(translate, 0, viewport, 2.0f, 2.0f);
		}
	}
	
	/** 
	 * 	turnOrder creates a Character array called everyone which stores every unit on the battlefield.
	 *	The array is then sorted by the speed values of the units (first = highest speed, last = lowest speed).
	 */
	public void turnOrder(){
		
		// 	I'll just create an array of all the creatures and use a sorting algorithm to get the turn order.

			for (int x = 0; x < characters.length; x++){
				everyone[x] = characters[x];
			}

			for (int y = 0; y <enemies.length; y++){
				everyone[y+characters.length] = enemies[y];
			}
		
		// Bubble Sort
		int n = everyone.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (everyone[i].getSp() < everyone[k].getSp()) {
                   
            		Character temp;
        			temp = everyone[i];
        			everyone[i] = everyone[k];
        			everyone[k] = temp;
                    
                }
            }
        }
		
	}

	/**
	 *  Depending on the current state of the battle, the player will either select an action, or select the target of an action
	 */
	public void selectOption(){
		if (selectingAction){
			everyoneAtk[counter] = selectedOption;
			selectTargetState();
			
					
					/* Knight */
			if (menuValue == 10){
				switch(everyoneAtk[counter]){
					// Attack
					case 1: menuValue = 1;
							break;
					// Attack All
					case 2: menuValue = 3;
							break;
					// Defense Up
					case 3: menuValue = 5;
							break;
					// Drink Potion
					case 4: menuValue = 7;
							break;
					// Use Revive Scroll		
					case 5: menuValue = 8;
							break;
				}
					
			} else {
						/* Rogue */
				if (menuValue == 11){
					switch(everyoneAtk[counter]){
						// Attack
						case 1: menuValue = 1;
					 			break;
					 	// Stealth
						case 2: menuValue = 2;
								break;
						// Drink Potion
						case 3: menuValue = 7;
								break;
						// Use Revive Scroll
						case 4: menuValue = 8;
								break;
					}
				} else {
							/* Mage */
					if (menuValue == 12){ 
						switch(everyoneAtk[counter]){
							// Attack
							case 1: menuValue = 1;
					 				break;
					 		// Stun
							case 2: menuValue = 6;
									break;
							// Heal
							case 3: menuValue = 4;
									break;
							// Drink Potion
							case 4: menuValue = 7;
									break;
							// Use Revive Scroll
							case 5: menuValue = 8;
									break;
						}
					}
				}
			}

		} 
		else {
			if (selectingTgt){
				switch(menuValue){
					case 1: // Attack
							everyoneTgt[counter] = enemies[selectedOption-1];
							counter++;
							break;
					case 2: // Stealth
							everyoneTgt[counter] = everyone[counter];
							counter++;
							break;
					case 3: //attack all enemies
							everyoneTgt[counter] = null;
							counter++;
							break;
					case 4: // Heal ally
							everyoneTgt[counter] = characters[selectedOption-1];
							counter++;
							break;
					case 5: // Defense Up
							everyoneTgt[counter] = everyone[counter];
							counter++;
							break;
					case 6: // Stun Enemy
							everyoneTgt[counter] = enemies[selectedOption-1];
							counter++;
							break;
					case 7: // Drink Potion
							everyoneTgt[counter] = everyone[counter];
							counter++;
							break;
					case 8: // Revive Scroll
							everyoneTgt[counter] = characters[selectedOption-1];
							counter++;
							break;
				}
				selectActionState();
				if (counter < everyone.length){	
					if (everyone[counter].isMob){
						setMobAttack();
					}
				}
			}
		}
		selectedOption = 1;
	}

	/*
	 * When the next character to choose an attack is an enemy, the proper methods are called to generate their actions and targets.
	 */
	public void setMobAttack(){
		
		// check if goblin and then set attack accordingly
		if (everyone[counter] instanceof Goblin){
			everyone[counter].generateAction(enemies, characters);
		} 
		else {
			everyone[counter].generateAction(characters);
		}
		everyoneAtk[counter] = everyone[counter].getAct();
		everyoneTgt[counter] = everyone[counter].getTgt();
		// check if this is the last mob, set performingActions to true
		// else if next character is a mob, if so call select mob attack again
		// otherwise nothing happens
		counter++;
		if (counter < everyone.length) {
			if (everyone[counter].isMob){
				setMobAttack();
			}
		}
	}


	public void updateObjects(float delta){
		setPositions(worldHeight);
		
		if (counter == 0 && everyone[counter].isMob){
			setMobAttack();
		}
		
		if (counter == everyone.length){
			performActionState();
		}
		// everyone does their selected action to their selected target
		if (performingActions){
			performActions();
		}
		
		menuSelect();
		
		if (allDeadCheck(0, enemies)){
			battleWon();
		}
		if (allDeadCheck(0, characters)){
			battleLost();
		}
		
	}
	
	public void render(Graphics g, int w, int h){
		
		//Draw the menu background
		g.setColor(Color.WHITE);
		g.fillRect((w/6), (3*h/4), (2*w/3), (h/4));
		//Select highlight behind text
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((w/6), (3*h/4)+(selectedOption*(h/32)), (2*w/3), (h/32));
		//Text for select options
		g.setColor(Color.BLACK);
		g.drawString("Battle Menu:", (w/6), (49*h/64));
		// The following are possible options a player may select, these change every time a player selects an option.
		g.drawString(options[0], (w/6), (51*h/64));
		g.drawString(options[1], (w/6), (53*h/64));
		g.drawString(options[2], (w/6), (55*h/64));
		g.drawString(options[3], (w/6), (57*h/64));
		g.drawString(options[4], (w/6), (59*h/64));
		
		for (int x=0; x < characters.length; x++){
			characters[x].drawSprite(g);
		}
		
		for (int x=0; x < enemies.length; x++){
			enemies[x].drawSprite(g);
		}
	}
	
	public void incrementSelectedOption(){
		selectedOption += 1;
		if(selectedOption > totalOptions){
			selectedOption = 1;
		}
	}
	
	public void decrementSelectedOption(){
		selectedOption -= 1;
		if(selectedOption < 1){
			selectedOption = totalOptions;
		}
	}
	
	public int getSelectedOption(){
		return selectedOption;
	}
	
	/*
	 * menuSelect changes the values of the options array using the menuValue
	 * The menuValue keeps track of what the player has chosen and determines which menu to use
	 */
	public void menuSelect(){
		// if an action is being selected, determine the current playerCharacter
		// set menuValue to the appropriate value
		if (counter < everyone.length){
			if (selectingAction){
				if (everyone[counter] instanceof Knight){
					menuValue = 10;
				} else {
					if (everyone[counter] instanceof Rogue){
						menuValue = 11;
					} else {
						if (everyone[counter] instanceof Mage){
							menuValue = 12;
						}
					}
				}
			}
			// Display the proper menu
			if (selectingAction){
						/* Action Menu */
				switch(menuValue){
					case 10: // Knight
							options[0] = "Attack";
							options[1] = "Attack All";
							options[2] = "Defense Up";
							options[3] = "Potion";
							options[4] = "Revive Scroll";
							totalOptions = 5;
							break;
					case 11: // Rogue
							options[0] = "Attack";
							options[1] = "Stealth";
							options[2] = "Potion";
							options[3] = "Revive Scroll";
							options[4] = "";
							totalOptions = 4;
							break;
					case 12: // Mage
							options[0] = "Attack";
							options[1] = "Stun";
							options[2] = "Heal";
							options[3] = "Potion";
							options[4] = "Revive Scroll";
							totalOptions = 5;
							break;
				}
			} 
			else{
				if (selectingTgt){
						/* Target Menu */
					switch (menuValue){
						case 1: // Characters Basic Attack
								for(int x = 0; x < enemies.length; x++){
									options[x] = enemies[x].getName();
								}
								totalOptions = enemies.length;
								if (totalOptions == 3){
									options[3] = "";
									options[4] = "";
								}
								if (totalOptions == 4){
									options[4] = "";
								}
								break;
						case 2: // Rogue Stealth
								options[0]= "Rogue";
								options[1]= "";
								options[2]= "";
								options[3]= "";
								options[4]= "";
								totalOptions = 1;
								break;
						case 3:	// Knight Attack All Enemies
								options[0]= "All Enemies";
								options[1]= "";
								options[2]= "";
								options[3]= "";
								options[4]= "";
								totalOptions = 1;
								break;
						case 4:	// Mage heal
								for(int x = 0; x < characters.length; x++){
									options[x] = characters[x].getName();
								}
								totalOptions = characters.length;
								options[3] = "";
								options[4] = "";
								break;
						case 5: // Knight defense up
								options[0]= "Knight";
								options[1]= "";
								options[2]= "";
								options[3]= "";
								options[4]= "";
								totalOptions = 1;
								break;
						case 6: // Mage stun enemies
								for(int x = 0; x < enemies.length; x++){
									options[x] = enemies[x].getName();
								}
								totalOptions = enemies.length;
								if (totalOptions == 3){
									options[3] = "";
									options[4] = "";
								}
								if (totalOptions == 4){
									options[4] = "";
								}
								break;
						case 7: // Characters drink potion
								options[0] = everyone[counter].getName();
								options[1]= "";
								options[2]= "";
								options[3]= "";
								options[4]= "";
								totalOptions = 1;
								break;
						case 8: // Characters revive scroll
								for(int x = 0; x < characters.length; x++){
									options[x] = characters[x].getName();
								}
								totalOptions = characters.length;
								options[3] = "";
								options[4] = "";
								break;
					}
				}	
			}
		}
	}

	/**
	 * the next three methods are responsible for setting the state of the game.
	 * At any moment in time, the player is either selecting an attack, selecting a target, or watching everyone perform their selected actions
	 * These methods were created to ensure that only one was true at any given time
	 */
	public void selectTargetState(){
		selectingTgt = true;
		selectingAction = false;
		performingActions = false;
	}
	public void selectActionState(){
		selectingTgt = false;
		selectingAction = true;
		performingActions = false;
	}
	public void performActionState(){
		selectingTgt = false;
		selectingAction = false;
		performingActions = true;
	}
	
	/**
	 * If everyone in the enemies array is dead, then the player has won the battle.
	 * Experience is distributed based on the number of enemies fought.
	 */
	public void battleWon(){
		System.out.println("The Battle Has Been Won!!! :)");
	}
	
	/**
	 * If everyone in the characters array is dead, then the player has lost the battle.
	 * The player is kicked back to the cottage and loses half their money.
	 */
	public void battleLost(){
		System.out.println("We have lost this battle!!! :(");
	}
	
	/**
	 *  Determines whether one side has died.
	 *  @param deadNum: An integer that always starts at zero, increments whenever a character is found to be dead
	 *  @param team: A Character array that is either the enemies array or characters array
	 */
	public boolean allDeadCheck(int deadNum, Character[] team){
		// If the deadNum matches the number of characters in team, that side is defeated and return true.
		if (deadNum == team.length){
			return true;
		}
		else { // Otherwise check to see if the next character is dead
			if (!team[deadNum].isAlive()){
				return allDeadCheck((deadNum + 1), team);
			}
			else { // If alive, the entire team is not defeated yet and return false.
				return false;
			}
		}
	}
	
	/*
	 * Calls all the necessary methods to apply the actions that everyone chose
	 */
	public void performActions(){
		for (int x = 0; x < everyone.length; x++){
			if (everyone[x] instanceof Knight || everyone[x] instanceof Boss){
				if (everyoneAtk[x] == 2){
					everyone[x].act(enemies);
				}
				else {
					everyone[x].act(everyoneAtk[x], everyoneTgt[x]);
				}
			}
			else {
				everyone[x].act(everyoneAtk[x], everyoneTgt[x]);
			}
		}
		counter = 0;
		System.out.println("\n Round Over \n");
		selectActionState();
	}

	public void setViewport (Matrix3x3f viewport){
		this.viewport = viewport;
	}
}

	

