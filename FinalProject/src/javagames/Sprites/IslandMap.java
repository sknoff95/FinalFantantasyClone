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
	private ArrayList<Vector2f> rectPortalHitboxes = new ArrayList<Vector2f>();
	private ArrayList<Vector2f> rectBiomeHitboxes = new ArrayList<Vector2f>();
	private String portal = null;
	private String biome = "Prairie";
	
	//grabs image from resources folder
	public IslandMap(String imageName){
		try {
			this.b = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Cave Entrance
		this.addRectPortalHitbox(new Vector2f(-5.23f,1.08f), new Vector2f(-4.82f,0.62f));
		//Cottage Entrance
		this.addRectPortalHitbox(new Vector2f(0.14f,0.55f), new Vector2f(1.03f,0.075f));
		//Castle Entrance
		this.addRectPortalHitbox(new Vector2f(5.565f,2.82f), new Vector2f(5.82f,2.5f));
		
		//Boundary Hitboxes
		//Ocean Hitboxes
		//Left
		this.addRectHitbox(new Vector2f(-8f,4.5f), new Vector2f(-7.237f,-4.5f));
		//Right
		this.addRectHitbox(new Vector2f(7.18f,4.5f), new Vector2f(8f,-4.5f));
		//Top
		this.addRectHitbox(new Vector2f(-7.237f,4.5f), new Vector2f(7.18f,3.9f));
		//Bottom
		this.addRectHitbox(new Vector2f(-7.237f,-3.75f), new Vector2f(7.18f,-4.5f));
		
		//Mountain Hitboxes
		//Top-left mountains (Left to right order)
		this.addRectHitbox(new Vector2f(-7.237f,3.9f), new Vector2f(-5.64f,-0.78f));
		this.addRectHitbox(new Vector2f(-5.64f,3.9f), new Vector2f(-4.35f,0.62f));
		this.addRectHitbox(new Vector2f(-4.35f,3.9f), new Vector2f(-2.79f,1.41f));
		this.addRectHitbox(new Vector2f(-2.79f,3.9f), new Vector2f(-0.96f,2.54f));
		
		//Top-right mountains (Left to right order)
		this.addRectHitbox(new Vector2f(2.55f,3.9f), new Vector2f(3.575f,2.92f));
		this.addRectHitbox(new Vector2f(3.575f,3.9f), new Vector2f(5.25f,2.19f));
		this.addRectHitbox(new Vector2f(5.25f,3.9f), new Vector2f(6.115f,2.5f));
		this.addRectHitbox(new Vector2f(6.115f,3.9f), new Vector2f(7.18f,0.805f));
		this.addRectHitbox(new Vector2f(4.86f,1.33f), new Vector2f(6.115f,0.805f));
		
		//Forest Hitboxes
		//Bottom-Left forest (Left to right order)
		this.addRectBiomeHitbox(new Vector2f(-4.9f,-1.435f), new Vector2f(-3.555f,-3.11f));
		this.addRectBiomeHitbox(new Vector2f(-4.72f,-0.53f), new Vector2f(-3.38f,-1.435f));
		this.addRectBiomeHitbox(new Vector2f(-3.555f,-1.435f), new Vector2f(-2.033f,-3.27f));
		this.addRectBiomeHitbox(new Vector2f(-3.38f,-0.12f), new Vector2f(-1.84f,-1.8f));
		
		//Bottom-right forest (top to bottom order)
		this.addRectBiomeHitbox(new Vector2f(4.25f,0.805f), new Vector2f(7.18f,0.19f));
		this.addRectBiomeHitbox(new Vector2f(3.85f,0.19f), new Vector2f(7.18f,-0.46f));
		this.addRectBiomeHitbox(new Vector2f(3.4f,-0.46f), new Vector2f(7.18f,-1.03f));
		this.addRectBiomeHitbox(new Vector2f(2.67f,-1.03f), new Vector2f(7.18f,-2.97f));
		this.addRectBiomeHitbox(new Vector2f(3.62f,-2.97f), new Vector2f(7.18f,-3.75f));
		
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
				if(a == 0){
					portal = "Cave";
				}
				else if(a == 2){
					portal = "Cottage";
				}
				else if(a == 4){
					portal = "Castle";
				}
				return true;
			}
		}
		portal = null;
		return false;
	}
	
	public Boolean checkBiomeHitboxes(Vector2f center){
		
		//Rectangular Hit Box Detection
		for(int a = 0; a < rectBiomeHitboxes.size(); a += 2){
			Vector2f boxTopLeft = rectBiomeHitboxes.get(a);
			Vector2f boxBotRight = rectBiomeHitboxes.get(a+1);
			
			if(center.x > boxBotRight.x || center.x < boxTopLeft.x){
			}
			else if(center.y < boxBotRight.y || center.y > boxTopLeft.y){
			}
			else{
				biome = "Forest";
				return true;
			}
		}
		biome = "Prairie";
		return false;
	}
	
	public String getPortal(){
		return portal;
	}
	
	public String getBiome(){
		return biome;
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
		
		g.setColor(Color.RED);
		if(rectBiomeHitboxes.size() > 1){
			for(int x = 0; x < rectBiomeHitboxes.size(); x += 2){
				P = view.mul(w.mul(rectBiomeHitboxes.get(x)));
				S = view.mul(w.mul(rectBiomeHitboxes.get(x+1)));
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
	
	public void addRectBiomeHitbox(Vector2f topLeft, Vector2f bottomRight){
		rectBiomeHitboxes.add(topLeft);
		rectBiomeHitboxes.add(bottomRight);
	}
	
	public BufferedImage getBackground(){
		return b;
	}
}
