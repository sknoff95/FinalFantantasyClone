package javagames.Sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javagames.util.Matrix3x3f;
import javagames.util.Vector2f;

public class Cottage extends Sprite{

private BufferedImage b;
private int selectedOption = 1;
//Buy health potion = 1
//Buy revive scroll = 2
//Rest at cottage = 3
//Exit cottage = 4
	
	//grabs image from resources folder
	public Cottage(String imageName){
		try {
			this.b = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//renders the image as the screen
	public void renderBackground(Graphics g, int w, int h, int potions, int scrolls, int gold){
		g.setColor(Color.WHITE);
		g.drawImage(b, 0, 0, w, h, null);
		//Draw the menu background
		g.fillRect((w/10), (h/8), (w/5), (h/2));
		//Select highlight behind text
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((w/10), (h/7)+(selectedOption*25)-10, (w/5), 14);
		//Text for select options
		g.setColor(Color.BLACK);
		g.drawString("Cottage Menu:", (w/10), (h/7));
		g.drawString("Buy Health Potion x 100 G", (w/10), (h/7)+25);
		g.drawString("Buy Revive Scroll x 1000 G", (w/10), (h/7)+50);
		g.drawString("Rest in cottage", (w/10), (h/7)+75);
		g.drawString("Leave cottage", (w/10), (h/7)+100);
		g.drawString("Inventory: ", (w/10), (h/7)+150);
		g.drawString("Health Potions: " + potions, (w/10), (h/7)+175);
		g.drawString("Revive Scrolls: " + scrolls, (w/10), (h/7)+200);
		g.drawString("Gold: " + gold, (w/10), (h/7)+225);
		
	}
	
	public void incrementSelectedOption(){
		selectedOption += 1;
		if(selectedOption > 4){
			selectedOption = 1;
		}
	}
	
	public void decrementSelectedOption(){
		selectedOption -= 1;
		if(selectedOption < 1){
			selectedOption = 4;
		}
	}
	
	public int getSelectedOption(){
		return selectedOption;
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
