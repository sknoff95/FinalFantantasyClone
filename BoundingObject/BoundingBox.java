package BoundingObject;

import java.awt.*;
import SimpleFramework.*;

public class BoundingBox extends BoundingObject{
	
	private Vector2f min, max;
	
	/**
	   BoundingBox.java Constructor
	   Input		: Vector2f min, the minimum x and y, Vector2f max, the maximum x and y
	   Constructor for BoundingBox.java
	 */
	public BoundingBox(Vector2f min, Vector2f max)
	{
		this.min = min;
		this.max = max;
	}
	
	/**
	   intersectAABB
	   Inputs		: Vector2f minA, Vector2f maxA, the min and max of the first boundingBox Vector2f minB, Vector2f maxB, the min and max of the second
	   boundingBox
	   Outputs		: A boolean, whether they are intersecting  
	   Checks if two boundingBoxes are intersecting
	 */
	public boolean intersectAABB(Vector2f minA, Vector2f maxA, Vector2f minB, Vector2f maxB)
	{
		if(minA.x > maxB.x || minB.x > maxA.x)
			return false;
		if(minA.y > maxB.y || minB.y > maxA.y)
			return false;
		return true;
	}
	
	/**
	   pointInAABB
	   Inputs		: Vector2f p, the point to be checked, Vector2f min, Vector2f max, the min and max of the boundingBox
	   Outputs		: A boolean, whether the point intersects the box 
	   Checks if the given point intersects the boundingBox
	 */
	public boolean pointInAABB(Vector2f p, Vector2f min, Vector2f max)
	{
		return p.x > min.x && p.x < max.x && p.y > min.y && p.y < max.y;
	}

	/**
	   intersectCircleAABB
	   Inputs		: Vector2f c, float r, the circle's center and radius, Vector2f min, Vector2f max, the boxes min and max
	   Outputs		: A boolean, whether they are intersecting  
	   Checks if the given boundingCircle intersects the box
	 */
	public boolean intersectCircleAABB(Vector2f c, float r, Vector2f min, Vector2f max)
	{
		float d = 0.0f;
		if(c.x < min.x) d += (c.x - min.x) * (c.x - min.x);
		if(c.x > max.x) d += (c.x - max.x) * (c.x - max.x);
		if(c.y < min.y) d += (c.y - min.y) * (c.y - min.y);
		if(c.y > max.y) d += (c.y - max.y) * (c.y - max.y);
		return d< r * r;
	}
	
	/**
	   getMin
	   Inputs		: None
	   Outputs		: A Vector2f, the min Vector2f 
	   Returns the min Vector2f
	 */
	public Vector2f getMin()
	{
		return min;
	}
	
	/**
	   getMax
	   Inputs		: None
	   Outputs		: A Vector2f, the max Vector2f 
	   Returns the max Vector2f
	 */
	public Vector2f getMax()
	{
		return max;
	}
	
	/**
	   transform
	   Inputs		: Matrix3x3f transformation
	   Outputs 		: None
	   Applies the transformation matrix to both min and max
	 */
	@Override
	public void transform(Matrix3x3f transformation)
	{
		min = transformation.mul(min);
		max = transformation.mul(max);
	}
	
	/**
	   render
	   Inputs		: Graphics g
	   Outputs 		: None
	   Renders the boundingBox by drawing a line between each point in the min and max
	 */
	@Override
	public void render(Graphics g)
	{
		g.drawLine((int)min.x, (int)min.y, (int)min.x, (int)max.y);
		g.drawLine((int)min.x, (int)max.y, (int)max.x, (int)max.y);
		g.drawLine((int)max.x, (int)max.y, (int)max.x, (int)min.y);
		g.drawLine((int)max.x, (int)min.y, (int)min.x, (int)min.y);
	}
}



