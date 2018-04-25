package BoundingObject;

import java.awt.*;
import SimpleFramework.*;

public class BoundingCircle extends BoundingObject{

	private Vector2f center;
	private float radius;
	
	/**
	   BoundingCircle.java Constructor
	   Input		: Vector2f c, the center, float r, the radius
	   Constructor for BoundingCircle.java
	 */
	public BoundingCircle(Vector2f c, float r)
	{
		center = c;
		radius = r;
	}
	
	/**
	   intersectCircle
	   Inputs		: Vector2f c0, the first center, float r0, the first radius, float c1, the second center, float r1, the second radius
	   Outputs		: A boolean, whether they are intersecting 
	   Checks if two boundingCircles are intersecting
	 */
	private boolean intersectCircle(Vector2f c0, float r0, Vector2f c1, float r1)
	{
		Vector2f c = c0.sub(c1);
		float r = r0 + r1;
		return c.lenSqr() < r * r;
	}
	
	/**
	   pointInCircle
	   Inputs		: Vector2f p, the point to check, Vector2f c, the center of the boundingCircle float r, the radius of the BoundingCircle
	   Outputs		: A boolean, whether the point intersects the circle  
	   Checks if the provided point intersects the boundingCircle
	 */
	private boolean pointInCircle(Vector2f p, Vector2f c, float r)
	{
		Vector2f dist = p.sub(c);
		return dist.lenSqr() < r * r;
	}
	
	/**
	   getCenter
	   Inputs		: None
	   Outputs		: A Vector2f, the center 
	   Returns the center Vector2f
	 */
	public Vector2f getCenter()
	{
		return center;
	}
	
	/**
	   intersectCircleAABB
	   Inputs		: BoundingBox box, the box to be checked
	   Outputs		: A boolean, whether they are intersecting  
	   Checks if the box is being intersected by the circle
	 */
	private boolean intersectCircleAABB(BoundingBox box)
	{
		float d = 0.0f;
		if(center.x < box.getMin().x) d += (center.x - box.getMin().x) * (center.x - box.getMin().x);
		if(center.x > box.getMax().x) d += (center.x - box.getMax().x) * (center.x - box.getMax().x);
		if(center.y < box.getMin().y) d += (center.y - box.getMin().y) * (center.y - box.getMin().y);
		if(center.y > box.getMax().y) d += (center.y - box.getMax().y) * (center.y - box.getMax().y);
		return d < radius * radius;
	}
	
	/**
	   getXMax
	   Inputs		: None
	   Outputs		: A float, the highest x value 
	   Returns the highest x value
	 */
	public float getXMax()
	{
		return center.x+radius;
	}
	
	/**
	   getXMin
	   Inputs		: None
	   Outputs		: A float, the lowest x value 
	   Returns the lowest x value
	 */
	public float getXMin()
	{
		return center.x-radius;
	}
	
	/**
	   getYMax
	   Inputs		: None
	   Outputs		: A float, the highest y value 
	   Returns the highest y value
	 */
	public float getYMax()
	{
		return center.y+radius;
	}
	
	/**
	   getYMin
	   Inputs		: None
	   Outputs		: A float, the lowest y value 
	   Returns the lowest y value
	 */
	public float getYMin()
	{
		return center.y-radius;
	}
	
	/**
	   render
	   Inputs		: Graphics g
	   Outputs 		: None
	   Draws the boundingCircle by calling on drawOval
	 */
	@Override
	public void render(Graphics g)
	{
		g.drawOval((int)(center.x-(radius/2)), (int)(center.y-(radius/2)), (int)radius, (int)radius);
	}
	
	/**
	   transform
	   Inputs		: Matrix3x3f transform, the matrix to transform by
	   Outputs 		: None
	   Translates the boundingCircle
	 */
	@Override
	public void transform(Matrix3x3f transform)
	{
		center = transform.mul(center);
	}
}


