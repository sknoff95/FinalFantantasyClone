package javagames.images;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javagames.util.BoundingShapes;
import javagames.util.Matrix3x3f;
import javagames.util.Vector2f;

public class BackGround {

	private BufferedImage background; // the background image
	private BoundingShapes bounds = new BoundingShapes(); // hitboxes for the bounds of the screen
	private Vector2f SpriteLoc = new Vector2f(0, 0); // Upper left of sprite location
	private Vector2f SpriteLocL = new Vector2f(1, -1); // Bottom right of sprite location
	private String path; // String for the path
	private int oType; // Type of background object

	public BackGround(String location, int type) {
		path = location;
		oType = type;
		initialize();
	}

	/*
	 * Grabs the background.png and loads it, and adds hitboxes at the edges of the
	 * screen Not very scalable since it assumes the background will always have the
	 * same dimensions As long as the above is true though, could be scaled to
	 * infinite backgrounds
	 */
	public void initialize() {
		try {
			background = ImageIO.read(new File("Resources/" + path + ".png"));
		} catch (IOException e) {
			System.out.print("Exception, failed to load back ground \n");
			e.printStackTrace();
		}
		if (oType == 0) {
			bounds.addPointLine(new Vector2f(-7.9f, -3.6f));
			bounds.addPointLine(new Vector2f(7.9f, 4.4f));
		} else if (oType == 1) {
			SpriteLoc.x = 0f;
			SpriteLoc.y = -2.6f;
			SpriteLocL.x = 1f;
			SpriteLocL.y = -3.6f;
			bounds.addPointLine(new Vector2f(0f, -2.6f));
			bounds.addPointLine(SpriteLocL);

		}

	}

	/*
	 * Updates the world for the hitbox
	 * 
	 */
	public void updateWorld() {
		bounds.updateWorld();
	}

	/*
	 * renders the background and the hitboxes if they are toggled
	 * 
	 */
	public void render(Graphics g, int cW, int cH, Matrix3x3f viewportMatrix, boolean toggle) {
		Vector2f A = viewportMatrix.mul(SpriteLoc);
		if (oType == 0) {
			g.drawImage(background, 0, 0, cW, cH, null);
		} else if (oType == 1) {
			g.drawImage(background, (int) A.x, (int) A.y, cW / 16, cH / 9, null);
		}
		if (toggle) {
			bounds.renderHitbox(g, viewportMatrix);
		}
	}

	/*
	 * returns the hitbox for the bounds of the screen
	 * 
	 */
	public BoundingShapes getHitbox() {
		return bounds;
	}

}
