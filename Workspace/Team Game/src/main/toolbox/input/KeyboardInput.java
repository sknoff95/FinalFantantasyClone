package main.toolbox.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * @author JP
 * the keyboard class for all your keyboards and keyboard accesories
 */
public class KeyboardInput implements KeyListener {
	//this is a lot of code just to check if "c" is pressed
	private boolean[] keys = new boolean [256];
	private int[] polled = new int [keys.length];
	/**
	 * false when space has not been pressed, true otherwise
	 */
	public KeyboardInput() 
	{
	}
	/**
	 * if a key is pressed down
	 * @param keyCode the code that represents the key
	 * @return if the key is currently pressed down
	 */
	public boolean keyDown (int keyCode)
	{
		if (polled[keyCode] > 0)
		{
			return true;
		}
		return false;
	}
	/**
	 * returns if the key is pressed down for one poll
	 * @param keyCode the code that that represents the key in question
	 * @return if the key has been pressed for no more than one poll
	 */
	public boolean keyDownOnce (int keyCode)
	{
		return polled[keyCode] == 1;
	}
	/**
	 * the method that is called once per update and does all the logic for the keyboard
	 */
	public synchronized void poll()
	{
		for(int i = 0; i < keys.length; i++)
		{
			if(keys[i])
				polled[i]++;
			else
				polled[i] = 0;
		}
	}
	/**
	 * a method that does stuff related to the keyboard
	 */
	public void keyPressed(KeyEvent e)
	{
		int KeyCode = e.getKeyCode();
		if (KeyCode >= 0 && KeyCode < keys.length)
		{
			keys[KeyCode] = true;
		}
	}
	/**
	 * a method that does stuff related to the keyboard
	 */
	public synchronized void keyReleased(KeyEvent e)
	{
		int KeyCode = e.getKeyCode();
		if (KeyCode >= 0 && KeyCode < keys.length)
		{
			keys[KeyCode] = false;
		}
	}
	/**
	 * a method that does stuff related to the keyboard that currently has no use
	 */
	public void keyTyped(KeyEvent arg0) {
		// TODO what does this do?		
	}	
}
