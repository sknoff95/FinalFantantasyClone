package Sprite;

import java.awt.*;
import BoundingObject.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import SimpleFramework.*;

public class Sprite {

	private BufferedImage sprite, frame;
	private String loadedFile;
	private int frameHeight, frameWidth;
	private AffineTransform transform;
	private BoundingObject outerBound;
	private ArrayList<BoundingObject> innerBound;
	private AlphaComposite acomp;
	
	
	/**
	   Sprite.java Constructor
	   Input		: String fileName, the name of the file, int h, the height of the file, and int w, the width of the file
	   Constructor for Sprite.java
	 */
	public Sprite(String fileName, int h, int w)
	{
		loadFile(fileName);
		frameHeight = h;
		frameWidth = w;
		acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f);
		innerBound = new ArrayList<BoundingObject>();
	}
	
	/**
	   loadFile
	   Inputs		: String fileName, the fileName to be loaded
	   Outputs		: void 
	   Loads the file with the given string, if not found, catches the exception and doesn't set the sprite
	 */
	private void loadFile(String fileName)
	{
		try{
			sprite = ImageIO.read(new File(fileName));
			loadedFile = "Loaded: " + fileName;
		}catch(IOException e)
		{
			e.printStackTrace();
			sprite = null;
		}
	}
	
	/**
	   drawSprite
	   Inputs		: Graphics g
	   Outputs		: void 
	   Draws the sprite that has been loaded with the current AffineTransform
	 */
	public void drawSprite(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setComposite(acomp);
		g2d.drawImage(frame, transform, null);
	}
	
	/**
	   transform
	   Inputs		: Vector2f position, the amount to be translated by, float angle, the angle to be rotated, Matrix3x3f viewport, the current viewport 
	   matrix, float sx, the x scale value, float sy, the y scale value
	   Outputs		: void 
	   Calls on createTransform to create the AffineTransformation to be used
	 */
	public void transform(Vector2f position, float angle, Matrix3x3f viewport, float sx, float sy)
	{
		transform = createTransform(position, angle, viewport, sx, sy);
	}
	
	/**
	   createTransform
	   Inputs		: Vector2f position, the amount to be translated by, float angle, the angle to be rotated, Matrix3x3f viewport, the current viewport 
	   matrix, float sx, the x scale value, float sy, the y scale value
	   Outputs		: An AffineTransform, the AffineTransformation created with the given information 
	   Creates an AffineTransform to be used in drawing the sprite, can take scale, rotation, and translation
	 */
	private AffineTransform createTransform(Vector2f position, float angle, Matrix3x3f viewport, float sx, float sy)
	{
		Vector2f screen = viewport.mul(position);
		AffineTransform transform = AffineTransform.getTranslateInstance(screen.x, screen.y);
		
		transform.scale(sx, sy);
		transform.rotate(angle);
		transform.translate(-frame.getWidth() / 2, -frame.getHeight() / 2);
		
		return transform;
	}
	
	/**
	   grabFrame
	   Inputs		: int x, int y
	   Outputs		: void 
	   Creates a subimage starting at the given x and y values, used with sprite sheets
	 */
	public void grabFrame(int x, int y)
	{
		frame = sprite.getSubimage(x, y, frameWidth, frameHeight);
	}
	
	/**
	   setBound
	   Inputs		: BoundingObject bound, the object to be set as the outer bound, Matrix3xf viewport, the current viewport
	   Outputs		: void 
	   Sets the given boundingObject as the sprites outerbound, applies the viewport's transformation
	 */
	public void setBound(BoundingObject bound, Matrix3x3f viewport)
	{
		outerBound = bound;
		outerBound.transform(viewport);
	}
	
	/**
	   setAlpha
	   Inputs		: float a, the alpha value
	   Outputs		: void 
	   Sets the value to be used as the sprites alpha
	 */
	public void setAlpha(float a)
	{
		acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a);
	}
	
	/**
	   updateBound
	   Inputs		: Matrix3x3f viewport, the current viewport, float sx, the x amount to be translated, float sy, the y amount to be translated
	   Outputs		: void 
	   Translates the outer bound by calling on the BoundingObject transform function
	 */
	public void updateBound(Matrix3x3f viewport, float sx, float sy)
	{
		Matrix3x3f trans = Matrix3x3f.identity().mul(viewport).translate(new Vector2f(sx, sy));
		outerBound.transform(trans);
	}
	
	/**
	   addInnerBound
	   Inputs		: BoundingObject newBound, the new boundingObject to be input into the inner bounds, Matrix3x3f viewport, the current viewport matrix
	   Outputs		: void 
	   Adds a new boundingObject to the innerBound arraylist after applying the viewport matrix
	 */
	public void addInnerBound(BoundingObject newBound, Matrix3x3f viewport)
	{
		newBound.transform(viewport);
		innerBound.add(newBound);
	}
	
	/**
	   getBoundingBox
	   Inputs		: None
	   Outputs		: A BoundingBox, the outer bounding box 
	   Returns the bounding box outer bound
	 */
	public BoundingBox getBoundingBox()
	{
		return (BoundingBox)outerBound;
	}
	
	/**
	   getBoundingCircle
	   Inputs		: None
	   Outputs		: A BoundingCircle, the outer bounding circle 
	   Returns the bounding outer circle
	 */
	public BoundingCircle getBoundingCircle()
	{
		return (BoundingCircle)outerBound;
	}
	
	/**
	   renderBounds
	   Inputs		: Graphics g
	   Outputs		: void 
	   Calls on the render functions of the outerBound and all BoundObjects in the innerBound arraylist
	 */
	public void renderBounds(Graphics g)
	{
		if(outerBound != null)
			outerBound.render(g);
		
		for(int i = 0; i < innerBound.size(); i++)
		{
			if(innerBound.get(i) != null)
				innerBound.get(i).render(g);
		}
	}
}


