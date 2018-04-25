package javagames.Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javagames.util.Matrix3x3f;
import javagames.util.Vector2f;

public class IslandMap extends Sprite {
	
	private BufferedImage b;
	
	//grabs image from resources folder
	public IslandMap(String imageName){
		try {
			this.b = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//renders the image as the screen
	public void renderBackground(Graphics g, int w, int h){
		g.drawImage(b, 0, 0, w, h, null);
	}
	
	//hitbox rendering
	public void renderHitboxes(Graphics g){
		super.render(g);
	}
	
	public void renderHitboxes(Graphics g, Matrix3x3f view){
		super.render(g, view);
	}
	
	//adding different types of hitboxes
	public void addRectHitbox(Vector2f topLeft, Vector2f bottomRight){
		super.addRectHitbox(topLeft, bottomRight);
	}
	
	public void addCircleHitbox(Vector2f topLeft, Vector2f bottomRight){
		super.addCircleHitbox(topLeft, bottomRight);
	}
	
	public BufferedImage getBackground(){
		return b;
	}
}
