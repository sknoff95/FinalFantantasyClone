package game.toolBox.boundingObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.toolBox.images.Drawable;
import game.toolBox.images.Objects.SquareBb;
import game.toolBox.util.Matrix3x3f;
import game.toolBox.util.Vector2f;
/**
 * @author JP
 * the main class for objects made of vectors
 */
public class VectorObject implements Drawable{
	/**
	 * the bounding box
	 */
	public SquareBb box;
	/**
	 * the way we see the object
	 */
	public Matrix3x3f viewport;
	/**
	 * dir(x, y)
	 */
	public Vector2f dir = new Vector2f (0, 0);
	/**
	 * rot(angle, delta)
	 */
	public Vector2f rot = new Vector2f (0, 0.1f);
	/**
	 * a boolean that determines the rotation of the meteor
	 */
	public boolean clock;
	/**
	 * the color of the object
	 */
	public Color color;
	/**
	 * the vectors that make up the shape of the object
	 */
	public Vector2f[] vectors;
	/**
	 * the world matrix that determins how the object is in relation to the world
	 */
	public Matrix3x3f World = Matrix3x3f.identity(); 
	/**
	 * determins what is the construct of the object
	 */
	public int type;
	/**
	 * a constructor that based on the provided type determines how the object is shaped
	 * @param type the type of object 
	 */
	
	public VectorObject(int type) 
	{
		// type 0 = city block
		// type 1 = meteor
		// type 2 = bounding box used by all objects
		if (type == 0)
		{
			vectors    = new Vector2f[8];
			vectors[0] = new Vector2f( 0.05000f,-0.0500f);
			vectors[1] = new Vector2f(-0.05000f,-0.0500f);
			vectors[2] = new Vector2f(-0.05000f, 0.0000f);
			vectors[3] = new Vector2f(-0.01667f, 0.0000f);
			vectors[4] = new Vector2f(-0.01667f,-0.0175f);
			vectors[5] = new Vector2f( 0.01667f,-0.0175f);
			vectors[6] = new Vector2f( 0.01667f, 0.0150f);
			vectors[7] = new Vector2f( 0.05000f, 0.0150f);
		}
		if (type == 1)
		{
			vectors = new Vector2f[6];
			vectors[0] = new Vector2f(-0.050f, 0.0000f);
			vectors[1] = new Vector2f(-0.025f, 0.0433f);
			vectors[2] = new Vector2f( 0.025f, 0.0433f);
			vectors[3] = new Vector2f( 0.050f, 0.0000f);
			vectors[4] = new Vector2f( 0.025f,-0.0433f);
			vectors[5] = new Vector2f(-0.025f,-0.0433f);
		}
		if (type == 2)
		{
			vectors = new Vector2f[4];
			vectors[0] = new Vector2f(-0.05f, 0.05f);
			vectors[1] = new Vector2f( 0.05f, 0.05f);
			vectors[2] = new Vector2f( 0.05f,-0.05f);
			vectors[3] = new Vector2f(-0.05f,-0.05f);
		}
		this.type = type;
	}
	/**
	 * the vectorObject that creates itself off of another's parameters
	 * @param v the object that the vectorObject is creating itself off of.
	 * @param num the block number if city, random if meteor
	 * @param cities the list of objects, only used by meteors
	 * @param box the bounding box
	 */
	public VectorObject(VectorObject v, int num, ArrayList<VectorObject> cities, SquareBb box)
	{
		dir = new Vector2f(v.dir);
		if (v.type == 0 || num < 20)
		{
			dir.x = -1.0f + 0.1f * num + 0.05f;
			dir.y = -0.95f;
			rot = new Vector2f(v.rot.x, v.rot.y + (float)Math.random());
			if ((float)Math.random() > 0.5f) clock = true; // turns right
			else clock = false; // turns left
			color = color.RED;
		}
		else 
		{
			dir.x = -1.0f + 0.1f * (int) (Math.random() * 20) + 0.05f;
			dir.y = 1.0f;
			if (box != null)
			{
				box.dir = dir;
			}
			color = Color.BLACK;
		}
		
		vectors = new Vector2f[v.vectors.length];
		for (int i = 0; i < vectors.length; i ++)
		{
			vectors[i] = new Vector2f(v.vectors[i]);
		}
	}
	/**
	 * the update method that is overridden by the individual objects
	 */
	public void update(double delta, Matrix3x3f viewport)
	{
	}
	/**
	 * the universal render object that is the same for all vector objects
	 * @param g the graphics object that allows you to draw lines between points
	 */
	public void render(Graphics g)
	{
		
		for(int n = 0; n < this.vectors.length; n++)
		{
			this.World.mul(this.vectors[n]);
			Vector2f pos;
			Vector2f pos2;
			// setting pos and pos2 to be the same as the vectors
			if(n < this.vectors.length - 1)
			{
				pos = World.mul(new Vector2f(this.vectors[n].x, this.vectors[n].y));
				pos2= World.mul(new Vector2f(this.vectors[n + 1].x, this.vectors[n + 1].y));
			}
			else
			{
				pos = World.mul(new Vector2f(this.vectors[n].x, this.vectors[n].y));
				pos2= World.mul(new Vector2f(this.vectors[0].x, this.vectors[0].y));
			}
			g.setColor(this.color);
			g.drawLine((int)pos.x, (int)pos.y, (int)pos2.x, (int)pos2.y);
		}
		
	}
}
