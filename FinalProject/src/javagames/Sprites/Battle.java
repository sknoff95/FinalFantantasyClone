package javagames.Sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javagames.util.Matrix3x3f;
import javagames.util.Vector2f;

public class Battle extends Sprite {
	private BufferedImage b;
	private String biome = "Prairie";
	
	//grabs image from resources folder based on biome
	public void setImage(){
		try {
			this.b = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + biome + "Battle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setBiome(String s){
		biome = s;
	}
	
	//Returns integer value corresponding to previous gameState
	public int exitBattle(){
		if(biome == "Prairie" || biome == "Forest"){
			return 0;
		}
		else if(biome == "Cave"){
			return 1;
		}
		return 3;
	}
	
	//renders the image as the screen
	public void renderBackground(Graphics g, int w, int h){
		this.setImage();
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
