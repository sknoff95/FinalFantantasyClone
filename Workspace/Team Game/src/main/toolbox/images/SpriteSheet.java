package main.toolbox.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author einst
 * the sprite for the hero
 */
public class SpriteSheet {
	/**
	 * the path that the hero is in
	 */
	private static String heroPath = "res/Hero.png";
	private static boolean done = false;
	private static BufferedImage sprite;
	/**
	 * parses the heroSpriteSheet into indivigual sprites
	 * @param x the top left x position
	 * @param y the top left y position
	 * @return the parsed sprite
	 */
	public static BufferedImage hero(int x, int y, int xSize, int ySize)
	{
		if (!done)
		{
			try {
				sprite = ImageIO.read(new File(heroPath));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			done = true;
		}
		return sprite.getSubimage(x * xSize, y * ySize, xSize, ySize);
	}
}
