package javagames.Sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javagames.util.*;
import javagames.Vectors.*;

public class Sprite implements Drawable{

	private ArrayList<Vector2f> rectHitboxes = new ArrayList<Vector2f>();
	private ArrayList<Vector2f> circleHitboxes = new ArrayList<Vector2f>();
	private Matrix3x3f world = Matrix3x3f.identity();
	
	//variables defining behavior of vector objects
	private float rot = 0;
	private float deltaRot = 0;
	private float transX = 0;
	private float deltaTransX = 0;
	private float transY = 0;
	private float deltaTransY = 0;
	private float scaleX = 1;
	private float scaleY = 1;
	private float deltaScaleX = 0;
	private float deltaScaleY = 0;
	private Color color = Color.BLACK;
	
	//Set and get functions for the stored variables
	public void addRectHitbox(Vector2f topLeft, Vector2f bottomRight){
		rectHitboxes.add(topLeft);
		rectHitboxes.add(bottomRight);
	}
	
	public void addCircleHitbox(Vector2f topLeft, Vector2f bottomRight){
		circleHitboxes.add(topLeft);
		circleHitboxes.add(bottomRight);
	}
	
	public void setRot(float f){
		rot = f;
	}
	
	public void setDeltaRot(float f){
		deltaRot = f;
	}
	
	public float getDeltaRot(){
		return deltaRot;
	}
	
	public void setTransX(float f){
		transX = f;
	}
	
	public float getTransX(){
		return transX;
	}
	
	public void setDeltaTransX(float f){
		deltaTransX = f;
	}
	
	public float getDeltaTransX(){
		return deltaTransX;
	}
	
	public void setTransY(float f){
		transY = f;
	}
	
	public float getTransY(){
		return transY;
	}
	
	public void setDeltaTransY(float f){
		deltaTransY = f;
	}
	
	public float getDeltaTransY(){
		return deltaTransY;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public void setScaleX(float f){
		scaleX = f;
	}
	
	public float getScaleX(){
		return scaleX;
	}
	
	public void setScaleY(float f){
		scaleY = f;
	}
	
	public float getScaleY(){
		return scaleY;
	}
	
	public void setDeltaScaleX(float f){
		deltaScaleX = f;
	}
	
	public float getDeltaScaleX(){
		return deltaScaleX;
	}
	
	public void setDeltaScaleY(float f){
		deltaScaleY = f;
	}
	
	public float getDeltaScaleY(){
		return deltaScaleY;
	}
	
	public Matrix3x3f getWorld(){
		return world;
	}
	
	public Color getColor(){
		return color;
	}
	
	//update's the world matrix with rotation and translation and changes variable values
	@Override
	public void updateWorld(){
		world = Matrix3x3f.identity();
		world = world.mul(Matrix3x3f.scale(scaleX, scaleY));
		world = world.mul(Matrix3x3f.rotate((float)Math.toRadians(rot)));
		world = world.mul(Matrix3x3f.translate(transX, transY));
		rot += deltaRot;
		transX += deltaTransX;
		transY += deltaTransY;
		scaleX += deltaScaleX;
		scaleY += deltaScaleY;
	}
	
	public ArrayList<Vector2f> getRectHitboxes(){
		return rectHitboxes;
	}
	
	public ArrayList<Vector2f> getCircleHitboxes(){
		return circleHitboxes;
	}
	
	//Applies the world matrix to the points stored as Vector2f's for an object
	@Override
	public void render(Graphics g){
		Vector2f P = new Vector2f();
		Vector2f S = new Vector2f();
		g.setColor(color);
			
		if(rectHitboxes.size() > 1){
			for(int x = 0; x < rectHitboxes.size(); x += 2){
				P = world.mul(rectHitboxes.get(x));
				S = world.mul(rectHitboxes.get(x+1));
				g.drawRect((int)P.x, (int)P.y, (int)(S.x-P.x), (int)(S.y-P.y));
			}
		}
		
		if(circleHitboxes.size()>1){
			for(int x = 0; x < circleHitboxes.size(); x += 2){
				P = world.mul(circleHitboxes.get(x));
				S = world.mul(circleHitboxes.get(x+1));
				g.drawOval((int)P.x, (int)P.y, (int)(S.x-P.x), (int)(P.y-S.y));
			}
		}
		
	}
	
	//Applies the viewport matrix and the world matrix to the points store as Vector2f's for an object
	//Useful for translating world coordinate points to screen coordinates for rendering
	public void render(Graphics g, Matrix3x3f viewport){
		Vector2f P = new Vector2f();
		Vector2f S = new Vector2f();
		g.setColor(color);
		
		if(rectHitboxes.size() > 1){
			for(int x = 0; x < rectHitboxes.size(); x += 2){
				P = viewport.mul(world.mul(rectHitboxes.get(x)));
				S = viewport.mul(world.mul(rectHitboxes.get(x+1)));
				g.drawRect((int)P.x, (int)P.y, (int)(S.x-P.x), (int)(S.y-P.y));
			}
		}
		
		if(circleHitboxes.size()>1){
			for(int x = 0; x < circleHitboxes.size(); x += 2){
				P = viewport.mul(world.mul(circleHitboxes.get(x)));
				S = viewport.mul(world.mul(circleHitboxes.get(x+1)));
				g.drawOval((int)P.x, (int)P.y, (int)(S.x-P.x), (int)(S.y-P.y));
			}
		}
	}
	
	//Get functions that get values from vectors array list
	
	public int rectHitboxesSize(){
		return rectHitboxes.size();
	}
	
	public int circleHitboxesSize(){
		return circleHitboxes.size();
	}
}