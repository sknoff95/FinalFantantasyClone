package javagames.images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javagames.util.Matrix3x3f;
import javagames.util.SimpleFramework;

public class SimpleFrameworkTemplate extends SimpleFramework {

	private Sprites Mario = new Sprites(0); // Creates a new sprite for Mario
	private Sprites Enemy = new Sprites(1); // Creates a new sprite for the goomba
	private BackGround background = new BackGround("background", 0); // Creates a new background
	private BackGround pipe = new BackGround("pipe", 1); // Creates a pipe Object
	private Matrix3x3f viewmul; // Creates the viewport matrix
	private Boolean left = false; // Flag for moving left
	private Boolean right = false; // Flag for moving right
	private Boolean jump = false; // Flag for jumping
	private Boolean togglehitbox = false; // Flag for toggling hitboxes
	private Boolean moving = false; // Flag for if moving
	private float counter = 0; // Counter for delta time

	/*
	 * Creates a new SimpleFrameWorkTemplate for the Sprite test
	 * 
	 */
	public SimpleFrameworkTemplate() {
		appBackground = Color.BLACK;
		appBorder = Color.BLACK;
		appFont = new Font("Courier New", Font.PLAIN, 14);
		appBorderScale = 0.9f;
		appFPSColor = Color.BLACK;
		appWidth = 1600;
		appHeight = 900;
		appMaintainRatio = true;
		appSleep = 10L;
		appTitle = "Sprite Test";
		appWorldWidth = 16f;
		appWorldHeight = 9f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javagames.util.SimpleFramework#initialize()
	 */
	@Override
	protected void initialize() {
		super.initialize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javagames.util.SimpleFramework#processInput(float) Throws flags for
	 * left, right, jump and toggling hitboxes
	 */
	@Override
	protected void processInput(float delta) {
		super.processInput(delta);
		if (keyboard.keyDown(KeyEvent.VK_A)) {
			left = true;
		}
		if (keyboard.keyDown(KeyEvent.VK_D)) {
			right = true;
		}
		if (keyboard.keyDownOnce(KeyEvent.VK_SPACE)) {
			jump = true;
		}
		if (keyboard.keyDownOnce(KeyEvent.VK_B)) {
			togglehitbox = !togglehitbox;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javagames.util.SimpleFramework#updateObjects(float) updates objects with
	 * movement calls, and calls hitbox checks
	 */
	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
		background.updateWorld();
		pipe.updateWorld();
		Mario.updateWorld(left, right, jump, delta);
		Enemy.updateWorld(left, right, jump, delta);
		if (left == true || right == true || jump == true) {
			moving = true;
			left = false;
			right = false;
			jump = false;
		}
		Mario.BoundsCheck(background.getHitbox());
		Mario.ObjectCheck(pipe.getHitbox());
		if (Mario.CollisionCheck(Enemy.getHitBox()) == true) {
			Enemy.Hit = true;
		} else if (Enemy.Hit == true) {
			counter += delta;
			if (counter >= 3) {
				Enemy.Hit = false;
				counter = 0;
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javagames.util.SimpleFramework#render(java.awt.Graphics) Renders the
	 * objects and resets the moving flag for the next cycle
	 */
	@Override
	protected void render(Graphics g) {
		super.render(g);
		viewmul = Matrix3x3f.identity();
		viewmul = super.getViewportTransform();
		background.render(g, canvas.getWidth(), canvas.getHeight(), viewmul, togglehitbox);
		pipe.render(g, canvas.getWidth(), canvas.getHeight(), viewmul, togglehitbox);
		Mario.render(g, canvas.getWidth(), canvas.getHeight(), viewmul, togglehitbox, moving);
		Enemy.render(g, canvas.getWidth(), canvas.getHeight(), viewmul, togglehitbox, moving);
		moving = false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javagames.util.SimpleFramework#terminate()
	 */
	@Override
	protected void terminate() {
		super.terminate();
	}

	public static void main(String[] args) {
		launchApp(new SimpleFrameworkTemplate());
	}
}