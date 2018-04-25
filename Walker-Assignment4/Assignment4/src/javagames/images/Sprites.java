package javagames.images;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javagames.util.BoundingShapes;
import javagames.util.Matrix3x3f;
import javagames.util.Vector2f;

public class Sprites {

	private final int restPos = 5; // Resting position for each sprite

	private BufferedImage SpriteL; // Loads Sprites for left side movement
	private BufferedImage SpriteR; // Loads Sprites for right side movement
	private BufferedImage SpriteE; // Loads Sprites if non specific movement set is given

	private ArrayList<BufferedImage> SpritesLeft = new ArrayList<BufferedImage>(); // Images for left side movement
	private ArrayList<BufferedImage> SpritesRight = new ArrayList<BufferedImage>(); // Images for Right side movement
	private ArrayList<BufferedImage> SpritesBoth = new ArrayList<BufferedImage>(); // Images for non specific movement

	private Vector2f SpriteLoc = new Vector2f(0, 0); // Upper left of sprite location
	private Vector2f SpriteLocL = new Vector2f(1, -1); // Bottom right of sprite location

	private BoundingShapes hitbox = new BoundingShapes(); // Stores the hitbox

	private AffineTransform aT = new AffineTransform(); // Used to transform the image

	public int currentAnim = 4; // Determines which Sprite gets played for animation

	private int sType = 0; // Checks what type of sprite is being loaded
	private int lastMoved = 0; // checks what direction the sprite was last facing
	private int degR = 0; // checks how many degrees are being moved
	private int imgW; // Width of an image
	private int imgH; // Height of an image
	private int jumpC = 0; // Counter for number of jumps

	private float counter = 0; // counter for switching animations
	private float jumpA = 0; // Acceleration of current jump speed

	private boolean Jumping = false; // Flag for if jumping
	private boolean Falling = false; // Flag for if falling
	public boolean Hit = false; // Flag for if hit
	private boolean Pipe = false; // Flag for if on pipe

	public boolean eMove = false; // Flag for if the enemy can move

	/*
	 * Initializes the Sprites Class and is given an int that tells it what image
	 * type to grab Grabs the image file, sets the starting location for the image
	 * then splits the image into different subimages for animations Sets the hitbox
	 * for the image based upon size of image Can be refined to be passed the string
	 * for the image location rather than hard coding a specific sprite location
	 * Would allow for scalability with future programs
	 */
	public Sprites(int MxE) {
		if (MxE == 0) {
			sType = 0;
			SpriteLoc.x = -5f;
			SpriteLoc.y = -2.6f;
			SpriteLocL.x = -4f;
			SpriteLocL.y = -3.6f;
			hitbox.addPointLine(new Vector2f(-5f, -2.6f));
			hitbox.addPointLine(new Vector2f(-4f, -3.6f));
			try {
				SpriteL = ImageIO.read(new File("Resources/MarioLeft.png"));
				SpriteR = ImageIO.read(new File("Resources/MarioRight.png"));
			} catch (IOException e) {
				System.out.println("Mario Left Failed to read");
				e.printStackTrace();
			}
			SpritesLeft.add(SpriteL.getSubimage(0, 0, 14, 13));
			SpritesLeft.add(SpriteL.getSubimage(18, 0, 10, 13));
			SpritesLeft.add(SpriteL.getSubimage(33, 0, 11, 13));
			SpritesLeft.add(SpriteL.getSubimage(49, 0, 9, 13));
			SpritesLeft.add(SpriteL.getSubimage(61, 0, 12, 13));
			SpritesLeft.add(SpriteL.getSubimage(75, 0, 10, 13));
			SpritesRight.add(SpriteR.getSubimage(71, 0, 14, 13));
			SpritesRight.add(SpriteR.getSubimage(57, 0, 10, 13));
			SpritesRight.add(SpriteR.getSubimage(41, 0, 11, 13));
			SpritesRight.add(SpriteR.getSubimage(27, 0, 9, 13));
			SpritesRight.add(SpriteR.getSubimage(12, 0, 12, 13));
			SpritesRight.add(SpriteR.getSubimage(0, 0, 10, 13));

		} else if (MxE == 1) {
			sType = 1;
			SpriteLoc.x = 3f;
			SpriteLoc.y = -2.5f;
			SpriteLocL.x = 3.5f;
			SpriteLocL.y = -3f;
			hitbox.addCirclePoint(SpriteLoc);
			hitbox.setCircleDim(new Vector2f(1, 1));
			try {
				SpriteE = ImageIO.read(new File("Resources/Enemy.png"));
			} catch (IOException e) {
				System.out.println("Enemy Failed to read");
				e.printStackTrace();
			}
			SpritesBoth.add(SpriteE.getSubimage(0, 0, 8, 8));
			SpritesBoth.add(SpriteE.getSubimage(20, 0, 8, 8));
			SpritesBoth.add(SpriteE.getSubimage(10, 0, 8, 8));
		}
	}

	/*
	 * Renders the current sprite. Checks for different animations based upon if
	 * moving or not Scales images based upon aspect ratio, and positions them based
	 * upon the viewport matrix Checks for which type of image is being displayed
	 * and does the render logic based upon that Could be scaled to take more
	 * variables that would allow for different animation checks to happen This
	 * would remove a lot of the hardcoded variables and would enable scalability
	 */
	public void render(Graphics g, int canvasW, int canvasH, Matrix3x3f view, boolean toggle, boolean moving) {
		Vector2f A = view.mul(SpriteLoc); // Is the viewport matrix vector

		if (sType == 0) { // checks if the sprite to be rendered is mario
			hitbox.setPoint(SpriteLoc, 0); // sets the hitbox's location to the sprite location
			hitbox.setPoint(SpriteLocL, 1);
			if (SpritesLeft.size() > 0 && SpritesRight.size() > 0) { // redundant check just to make sure that the
																		// sprite array list is not empty

				if (lastMoved == 0) { // checks which direction the sprite is supposed to be facing (left)
					if (Jumping == true || Falling == true && Pipe == false) { // checks if the falling or jumping
																				// animations should be
						// played
						if (currentAnim != 1 && counter < .15) {
							currentAnim = 1;
						}
						if (counter >= .25) {
							currentAnim = 0;

						}
						g.drawImage(SpritesLeft.get(currentAnim), (int) A.x, (int) A.y, canvasW / 16, canvasH / 9,
								null); // draws the jumping sprite
					} else if (moving) { // cycles the running animations
						if (counter >= .1) {
							counter -= .1;
							currentAnim -= 1;
							if (currentAnim < 2) {
								currentAnim = 4;
							}

						}
						g.drawImage(SpritesLeft.get(currentAnim), (int) A.x, (int) A.y, canvasW / 16, canvasH / 9,
								null); // draws the running sprite going left
					} else {
						g.drawImage(SpritesLeft.get(restPos), (int) A.x, (int) A.y, canvasW / 16, canvasH / 9, null);
						counter = 0; // draws the resting sprite for left
					}
				} else if (lastMoved == 1) { // checks which direction the sprite was last facing (right)
					if (Jumping == true || Falling == true && Pipe == false) { // checks if the falling or jumping
																				// animations should be
						// played
						if (currentAnim != 1 && counter < .15) {
							currentAnim = 1;
						}
						if (counter >= .25) {
							currentAnim = 0;

						}
						g.drawImage(SpritesRight.get(currentAnim), (int) A.x, (int) A.y, canvasW / 16, canvasH / 9,
								null); // draws the jumping animation
					} else if (moving) { // cycles running animations
						if (counter >= .1) {
							counter -= .1;
							currentAnim -= 1;
							if (currentAnim < 2) {
								currentAnim = 4;
							}

						}
						g.drawImage(SpritesRight.get(currentAnim), (int) A.x, (int) A.y, canvasW / 16, canvasH / 9,
								null); // draws the running sprite going right
					} else {
						g.drawImage(SpritesRight.get(restPos), (int) A.x, (int) A.y, canvasW / 16, canvasH / 9, null);
						counter = 0; // draw the resting sprite for right
					}
				}
				if (toggle) {
					hitbox.renderHitbox(g, view); // draws the hitbox if b has been pressing
				}
			}
		} else if (sType == 1) { // checks if the alternate sprite is being displayed
			if (Hit == true) { // checks if the sprite has been hit, if so plays the hit animation
				currentAnim = 2;
				if (degR >= 360) { // code used to rotate the sprite
					degR = 0;
				} else {
					degR += 10;
				}
			} else {
				currentAnim = 0;
			}
			hitbox.setCirclePoint(new Vector2f(SpriteLoc.x, SpriteLoc.y), 0); // sets the circular hitbox around the
																				// sprite

			g.drawImage(rotate(SpritesBoth.get(currentAnim)), (int) A.x, (int) A.y, canvasW / 16, canvasH / 9, null); // draws
																														// the
																														// enemy
																														// sprite
			if (toggle) {
				hitbox.renderOval(g, view); // renders the hitbox
			}
		}
	}

	/*
	 * Updates the world for the sprites and their hitboxes Uses flags for if the
	 * sprite should be moving left, right or jumping, as well as time passed Can be
	 * scaled very easily to deal with additional movements, as well as AI movement
	 * instead of player
	 */
	public void updateWorld(boolean Left, boolean Right, boolean jump, float delta) {
		if (sType == 0) {
			if (Left) { // moves the sprite left
				SpriteLoc.x = SpriteLoc.x - .1f;
				SpriteLocL.x = SpriteLocL.x - .1f;
				lastMoved = 0;
			}
			if (Right) { // moves the sprite right
				SpriteLoc.x = SpriteLoc.x + .1f;
				SpriteLocL.x = SpriteLocL.x + .1f;
				lastMoved = 1;
			}
			if (jump) { // jumps the sprite, and checks if a jump is allowed
				if (jumpC >= 2) {
					Falling = true;
				} else {
					jumpA += .6f;
					Jumping = true;
					jumpC++;
				}
			}
			if (Jumping) { // logic for jumping speed
				SpriteLoc.y += jumpA / 10;
				SpriteLocL.y += jumpA / 10;
				jumpA -= .01f;
				if (jumpA < 0) {
					jumpA = 0;
					Jumping = false;
					Falling = true;
				}
			}
			if (Falling && !Jumping) { // logic for falling speed
				SpriteLoc.y -= jumpA;
				SpriteLocL.y -= jumpA;
				jumpA += .003f;
			}
		}
		hitbox.updateWorld(); // updates the hitboxes
		counter += delta;

	}

	/*
	 * Checks if the sprite is trying to leave the bounds of the screen Not to much
	 * to scale with this, since it a simple out of bounds check Would implement a
	 * different check system for any objects on the screen and leave this as a
	 * simple OOB check
	 */
	public void BoundsCheck(BoundingShapes bounds) {
		if (bounds.points.get(0).x >= SpriteLoc.x && bounds.points.get(0).x <= SpriteLocL.x) {
			SpriteLoc.x = bounds.points.get(0).x;
			SpriteLocL.x = SpriteLoc.x + 1;
		}
		if (bounds.points.get(1).x >= SpriteLoc.x && bounds.points.get(1).x <= SpriteLocL.x) {
			SpriteLocL.x = bounds.points.get(1).x;
			SpriteLoc.x = SpriteLocL.x - 1;
		}
		if (bounds.points.get(1).y <= SpriteLoc.y) {
			SpriteLoc.y = bounds.points.get(1).y;
			SpriteLocL.y = SpriteLoc.y - 1;
		}
		if (bounds.points.get(0).y >= SpriteLocL.y) {
			SpriteLocL.y = bounds.points.get(0).y;
			SpriteLoc.y = SpriteLocL.y + 1;
			Falling = false;
			jumpC = 0;
		}
	}

	/*
	 * Checks if the Sprite is attempting to enter the bounds of an object that is
	 * rendered on the screen, for a rectangle hit box
	 * 
	 */
	public boolean ObjectCheck(BoundingShapes bounds) {
		if (bounds.points.get(0).y > SpriteLocL.y && bounds.points.get(0).y < SpriteLoc.y
				&& bounds.points.get(0).x <= SpriteLocL.x && bounds.points.get(1).x >= SpriteLocL.x) {
			SpriteLoc.y = bounds.points.get(0).y + 1;
			SpriteLocL.y = SpriteLoc.y - 1;
			jumpA = .1f;
			jumpC = 0;
			Pipe = true;
			System.out.println("Mario was on pipe 1");
			return true;
		} else if (bounds.points.get(0).y > SpriteLocL.y && bounds.points.get(0).y < SpriteLoc.y
				&& bounds.points.get(0).x <= SpriteLoc.x && bounds.points.get(1).x >= SpriteLoc.x) {
			SpriteLoc.y = bounds.points.get(0).y + 1;
			SpriteLocL.y = SpriteLoc.y - 1;
			jumpA = .1f;
			jumpC = 0;
			Pipe = true;
			System.out.println("Mario was on pipe 2");
			return true;
		} else if (bounds.points.get(0).x <= SpriteLocL.x && bounds.points.get(1).x >= SpriteLocL.x
				&& SpriteLocL.y < bounds.points.get(0).y) {
			SpriteLoc.x = bounds.points.get(0).x - 1;
			SpriteLocL.x = SpriteLoc.x + 1;
			System.out.println("Mario was inside the pipe 1");
			Pipe = false;
			return true;
		} else if (bounds.points.get(0).x <= SpriteLoc.x && bounds.points.get(1).x >= SpriteLoc.x
				&& SpriteLocL.y < bounds.points.get(0).y) {
			SpriteLoc.x = bounds.points.get(1).x;
			SpriteLocL.x = SpriteLoc.x + 1;
			System.out.println("Mario was inside the pipe 2");
			Pipe = false;
			return true;
		}
		return false;
	}

	/*
	 * Checks for collision between the mario sprite and the goomba sprite This is a
	 * scalable function as well since it checks for hitbox interaction between
	 * circles and rectangles
	 */
	public boolean CollisionCheck(BoundingShapes bounds) {
		if (SpriteLocL.x >= bounds.circle.get(0).x && SpriteLoc.x <= bounds.circle.get(0).x
				&& SpriteLocL.y <= bounds.circle.get(0).y) {
			return true;
		}
		return false;
	}

	/*
	 * code used to determine how the Affine Transformation should rotate the sprite
	 * 
	 */
	private BufferedImage rotate(BufferedImage currentImg) {
		BufferedImage sprite = currentImg;
		imgW = sprite.getWidth();
		imgH = sprite.getHeight();
		aT.rotate(Math.toRadians(degR), imgW / 2, imgH / 2);
		return sprite;
	}

	/*
	 * Returns the hitbox of a given sprite
	 * 
	 */
	public BoundingShapes getHitBox() {
		return hitbox;
	}
}
