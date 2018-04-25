package javagames.Sprites;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javagames.util.*;

public class OverworldCharacter extends Sprite{

	private BufferedImage c;
	private BufferedImage sub;
	private float time = 0;
	private Vector2f position = new Vector2f();
	private float deltaX;
	private float deltaY;
	private int action = 0;
	private boolean hit = false;
	
	//grabs image
	public OverworldCharacter(String imageName){
		try {
			this.c = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		position.x = (float)2;
		position.y = (float)0;
		deltaX = 0;
		deltaY = 0;
	}
	
	public void addRectHitbox(Vector2f topLeft, Vector2f bottomRight){
		super.addRectHitbox(topLeft, bottomRight);
	}
	
	public void addCircleHitbox(Vector2f topLeft, Vector2f bottomRight){
		super.addCircleHitbox(topLeft, bottomRight);
	}
	
	public void setDeltaX(float f){
		deltaX = f;
	}
	
	public void setDeltaY(float f){
		deltaY = f;
	}
	
	public float getDeltaX(){
		return deltaX;
	}
	
	public float getDeltaY(){
		return deltaY;
	}
	
	public void addTime(float t){
		time += t;
	}
	
	public void resetTime(){
		time = 0;
	}
	
	public void setAction(int x){
		action = x;
	}
	
	public void setPosition(float x, float y){
		position.x = x;
		position.y = y;
	}
	
	public void updatePosition(float time){
		position.x += deltaX*time;
		position.y += deltaY*time;
	}
	
	public Vector2f getPosition(){
		return position;
	}
	
	public void setHit(boolean b){
		hit = b;
	}
	
	public boolean getHit(){
		return hit;
	}
	
	//chooses which frames to render based on user input
	public void render(Graphics g, Matrix3x3f view, int height, int width, boolean reverse){
		Vector2f tempPos = new Vector2f();
		Graphics2D g2d = (Graphics2D)g;
		//idle
		if(action == 0){
			sub = c.getSubimage(0, 0, 45, 49);
		}
		//run right
		else if(action == 1){
			if(time <= .1){
				sub = c.getSubimage(0, 150, 46, 50);
			}
			else if(time <= .2){
				sub = c.getSubimage(46, 150, 46, 50);
			}
			else if(time <= .3){
				sub = c.getSubimage(46*2, 150, 46, 50);
			}
			else if(time <= .4){
				sub = c.getSubimage(46*3, 150, 46, 50);
			}
			else if(time <= .5){
				sub = c.getSubimage(46*4, 150, 46, 50);
			}
			else if(time <= .6){
				sub = c.getSubimage(46*5, 150, 46, 50);
			}	
			else if(time <= .7){
				sub = c.getSubimage(46*6, 150, 46, 50);
			}
			else if(time <= .8){
				sub = c.getSubimage(46*7, 150, 46, 50);
				time -= .8;
			}
		}
		//run left
		else if(action == 2){
			if(time <= .1){
				sub = c.getSubimage(0, 150, 46, 50);
			}
			else if(time <= .2){
				sub = c.getSubimage(46, 150, 46, 50);
			}
			else if(time <= .3){
				sub = c.getSubimage(46*2, 150, 46, 50);
			}
			else if(time <= .4){
				sub = c.getSubimage(46*3, 150, 46, 50);
			}
			else if(time <= .5){
				sub = c.getSubimage(46*4, 150, 46, 50);
			}
			else if(time <= .6){
				sub = c.getSubimage(46*5, 150, 46, 50);
			}	
			else if(time <= .7){
				sub = c.getSubimage(46*6, 150, 46, 50);
			}
			else if(time <= .8){
				sub = c.getSubimage(46*7, 150, 46, 50);
				time -= .8;
			}
		}
		tempPos = view.mul(Matrix3x3f.identity().mul(position));
		
		//reverses image if needed
		if(reverse == true){
			if(hit == false){
				g2d.drawImage(sub, (int)tempPos.x+51, (int)tempPos.y, -width/13, height/20, null);
			}
			else{
				g2d.drawImage(sub, (int)tempPos.x+25, (int)tempPos.y, -width/26, height/40, null);
			}
		}
		else{
			if(hit == false){
				g2d.drawImage(sub, (int)tempPos.x, (int)tempPos.y, width/13, height/20, null);
			}
			else{
				g2d.drawImage(sub, (int)tempPos.x, (int)tempPos.y, width/26, height/40, null);
			}
		}
	}
	
	public void renderHitboxes(Graphics g, Matrix3x3f view){
		super.render(g, view);
	}
}