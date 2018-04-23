package main.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.toolbox.images.Sprites;
/**
 * @author einst
 * the class where the main method is
 */
public class SimpleFrameworkTemplate extends SimpleFramework{
	/**
	 * a thing that exists
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * some of the variables that are set to be unique for this game
	 */
	public SimpleFrameworkTemplate()
	{
		appBackground = Color.WHITE;
		appBoarder = Color.LIGHT_GRAY;
		appFont = new Font("Courier New", Font.PLAIN, 14);
		appBoarderScale = 0.9f;
		appFPSColor = Color.BLACK;
		appWidth = 640;
		appHeight = 640;
		appMaintainAspectRatio = true;
		appSleep = 10l;
		appTitle = "Cannon Comand"; // rename for every game
		appWorldWidth = 2.0f;
		appWorldHeight = 2.0f;
		
	}
	/**
	 * a boolean that is there to prevent you from initializing from this class until the game has already initialized once
	 */
	boolean init = true;
	@Override
	/**
	 * a method that does some of the initializing work
	 */
	protected void initialize() 
	{
		super.initialize();
	}
	
	@Override
	/**
	 * a method that processes your input
	 */
	protected void processInput(float delta)
	{
		super.processInput(delta);
	}
	@Override
	/**
	 * updates the game logic and the objects
	 * @param delta the number of milliseconds since last update
	 */
	protected void updateObjects(float delta)
	{
		super.updateObjects(delta);
	}
	@Override
	/**
	 * renders the text and tells the VectorObject class to render the objects
	 * @param g the graphics
	 */
	protected void render(Graphics g)
	{
		g.clearRect(-WIDTH/2, -HEIGHT/2, WIDTH/2, HEIGHT/2);
		
	}
	@Override
	/**
	 * called at the end of each round
	 */
	protected void terminate()
	{
		super.terminate();
	}
	/**
	 * the main method
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		launchApp(new SimpleFrameworkTemplate());
	}

}
