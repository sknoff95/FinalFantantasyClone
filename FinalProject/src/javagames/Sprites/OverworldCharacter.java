package javagames.Sprites;

import java.awt.Color;
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
	private Vector2f center = new Vector2f();
	
	//grabs image
	public OverworldCharacter(String imageName){
		try {
			this.c = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		position.x = (float)0;
		position.y = (float)-2;
		deltaX = 0;
		deltaY = 0;
		
		//Character Hitbox
		this.addRectHitbox(new Vector2f((float)(position.x+.15), (float)(position.y-.04)), new Vector2f((float)(position.x + .52), (float)(position.y - .77)));
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
		this.setCenter(position);
	}
	
	public void updatePosition(float time){
		position.x += deltaX*time;
		position.y += deltaY*time;
		this.setCenter(position);
	}
	
	public Vector2f getPosition(){
		return position;
	}
	
	public void updateHitbox(){
		this.getRectHitboxes().set(0, new Vector2f((position.x), (position.y)));
		this.getRectHitboxes().set(1, new Vector2f((position.x + .59f), (position.y - .62f)));
	}
	
	public void setCenter(Vector2f v){
		center.x = v.x + 0.15f + (float)((.45-.15)/2);
		center.y = v.y - .035f - (float)((.62-0.035)/2);
	}
	
	public Vector2f getCenter(){
		return center;
	}
	
	//chooses which frames to render based on user input
	public void render(Graphics g, Matrix3x3f view, int height, int width, boolean reverse){
		Vector2f tempPos = new Vector2f();
		Graphics2D g2d = (Graphics2D)g;
		//idle
		if(action == 0){
			sub = c.getSubimage(0, 0, 32, 32);
		}
		//run down
		else if(action == 1){
			if(time <= .15){
				sub = c.getSubimage(0, 0, 32, 32);
			}
			else if(time <= .3){
				sub = c.getSubimage(32, 0, 32, 32);
			}
			else if(time <= .45){
				sub = c.getSubimage(32*2, 0, 32, 32);
			}
			else if(time <= .6){
				sub = c.getSubimage(32*3, 0, 32, 32);
				time -= .6;
			}
		}
		//run up
		else if(action == 2){
			if(time <= .15){
				sub = c.getSubimage(0, 32, 32, 32);
			}
			else if(time <= .3){
				sub = c.getSubimage(32, 32, 32, 32);
			}
			else if(time <= .45){
				sub = c.getSubimage(32*2, 32, 32, 32);
			}
			else if(time <= .6){
				sub = c.getSubimage(32*3, 32, 32, 32);
				time -= .6;
			}
		}
		//run right
		else if(action == 3){
			if(time <= .15){
				sub = c.getSubimage(0, 32*3, 32, 32);
			}
			else if(time <= .3){
				sub = c.getSubimage(32, 32*3, 32, 32);
			}
			else if(time <= .45){
				sub = c.getSubimage(32*2, 32*3, 32, 32);
			}
			else if(time <= .6){
				sub = c.getSubimage(32*3, 32*3, 32, 32);
				time -= .6;
			}
		}
		//run left
		else if(action == 4){
			if(time <= .15){
				sub = c.getSubimage(0, 32*2, 32, 32);
			}
			else if(time <= .3){
				sub = c.getSubimage(32, 32*2, 32, 32);
			}
			else if(time <= .45){
				sub = c.getSubimage(32*2, 32*2, 32, 32);
			}
			else if(time <= .6){
				sub = c.getSubimage(32*3, 32*2, 32, 32);
				time -= .6;
			}
		}
		
		
		//reverses image if needed
		tempPos = view.mul(Matrix3x3f.identity().mul(position));
		g2d.drawImage(sub, (int)tempPos.x, (int)tempPos.y, width/15, height/25, null);
		g2d.setColor(Color.CYAN);
		//g2d.fillRect((int)(view.mul(Matrix3x3f.identity().mul(center))).x, (int)(view.mul(Matrix3x3f.identity().mul(center))).y, 1, 1);
	}
	
	public void renderHitboxes(Graphics g, Matrix3x3f view){
		super.render(g, view);
	}
	
}