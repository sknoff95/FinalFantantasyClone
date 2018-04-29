package javagames.Sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javagames.util.Matrix3x3f;
import javagames.util.Vector2f;

public class Cave extends Sprite {
	
	private BufferedImage b;
	private ArrayList<Vector2f> rectPortalHitboxes = new ArrayList<Vector2f>();
	
	//grabs image from resources folder
	public Cave(String imageName){
		try {
			this.b = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Exit hitbox
		this.addRectPortalHitbox(new Vector2f(-1.15f, -4.5f), new Vector2f(1.35f, -5f));
		
		//Wall Hitboxes
		//Left
		this.addRectHitbox(new Vector2f(-8f, 4.5f), new Vector2f(-7.42f, -4.5f));
		//Top(Left to right order)
		this.addRectHitbox(new Vector2f(-7.42f, 4.5f), new Vector2f(-4.98f, 0.945f));
		this.addRectHitbox(new Vector2f(-4.98f, 4.5f), new Vector2f(-3.1f, 2.025f));
		this.addRectHitbox(new Vector2f(-3.1f, 4.5f), new Vector2f(2.455f, 3.85f));
		this.addRectHitbox(new Vector2f(2.455f, 4.5f), new Vector2f(4.855f, 2.025f));
		this.addRectHitbox(new Vector2f(4.855f, 4.5f), new Vector2f(7.35f, 1.02f));
		
		//Right
		this.addRectHitbox(new Vector2f(7.35f, 4.5f), new Vector2f(8f, -4.5f));
		
		//Bottom (Left to right order)
		this.addRectHitbox(new Vector2f(-7.42f, -1.435f), new Vector2f(-4.98f, -4.5f));
		this.addRectHitbox(new Vector2f(-4.98f, -3.1f), new Vector2f(-1.15f, -4.5f));
		this.addRectHitbox(new Vector2f(1.35f, -3.1f), new Vector2f(4.855f, -4.5f));
		this.addRectHitbox(new Vector2f(4.855f, -1.345f), new Vector2f(7.35f, -4.5f));
	}
	
	public Boolean checkPortalHitboxes(Vector2f topLeft, Vector2f bottomRight){
		
		//Rectangular Hit Box Detection
		for(int a = 0; a < rectPortalHitboxes.size(); a += 2){
			Vector2f boxTopLeft = rectPortalHitboxes.get(a);
			Vector2f boxBotRight = rectPortalHitboxes.get(a+1);
			
			if(topLeft.x > boxBotRight.x || bottomRight.x < boxTopLeft.x){
			}
			else if(topLeft.y < boxBotRight.y || bottomRight.y > boxTopLeft.y){
			}
			else{
				return true;
			}
		}
		return false;
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
		Matrix3x3f w = super.getWorld();
		Vector2f P = new Vector2f();
		Vector2f S = new Vector2f();
		
		g.setColor(Color.BLUE);
		if(rectPortalHitboxes.size() > 1){
			for(int x = 0; x < rectPortalHitboxes.size(); x += 2){
				P = view.mul(w.mul(rectPortalHitboxes.get(x)));
				S = view.mul(w.mul(rectPortalHitboxes.get(x+1)));
				g.drawRect((int)P.x, (int)P.y, (int)(S.x-P.x), (int)(S.y-P.y));
			}
		}
	}
	
	//adding different types of hitboxes
	public void addRectHitbox(Vector2f topLeft, Vector2f bottomRight){
		super.addRectHitbox(topLeft, bottomRight);
	}
	
	public void addCircleHitbox(Vector2f topLeft, Vector2f bottomRight){
		super.addCircleHitbox(topLeft, bottomRight);
	}
	
	public void addRectPortalHitbox(Vector2f topLeft, Vector2f bottomRight){
		rectPortalHitboxes.add(topLeft);
		rectPortalHitboxes.add(bottomRight);
	}
	
	public BufferedImage getBackground(){
		return b;
	}
}
