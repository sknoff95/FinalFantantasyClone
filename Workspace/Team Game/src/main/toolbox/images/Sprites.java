package game.toolBox.images;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.toolbox.bounds.Vector2f;
import main.toolbox.util.Matrix3x3f;
/**
 * @author einst
 * the sprite objects superclass
 */
public class Sprites implements Drawable
{
	protected int IMG_WIDTH; //sudo final
	protected int IMG_HEIGHT; //sudo final
	
	protected int dir = 0;
	protected int steps = 0;
	protected boolean flipX = false;
	protected boolean flipY = false;
	protected BufferedImage renderedImage;
	protected BufferedImage[][] hero = new BufferedImage[4][3];
	protected BufferedImage sprite;
	protected Vector2f pos = new Vector2f(0, 0);
	protected Vector2f scale = new Vector2f(1, 1);
	protected String loadedFile;
	protected float rot = 0f;
	protected boolean type;
	private int w = IMG_WIDTH;
	private int h = IMG_HEIGHT;
	/**
	 * the general constructor for sprites
	 * @param type which type of sprite it is
	 */
	public Sprites(boolean type)
	{
		// true = background pavement
		// false = player
		this.type = type;
	}
	/**
	 * the sprites template constructor
	 * @param s the sprite it is based on
	 */
	public Sprites(Sprites s)
	{
		sprite = s.sprite;
		type = s.type;
		rot = s.rot;
		pos = s.pos;
		if (type) {
			loadImage("res/ground_asphalt_synth_03.png");
		}
		else
		{
			for (int y = 0; y < 3; y ++)
				for (int x = 0; x < 4; x ++)
				{
					hero[x][y] = SpriteSheet.hero(x, y);					
					//System.out.println(hero[x][y]);
				}
			sprite = hero[0][0];
			
		}
		IMG_HEIGHT = sprite.getHeight();
		IMG_WIDTH = sprite.getWidth();
		h = IMG_HEIGHT;
		w = IMG_WIDTH;
	}
	
	protected void loadImage(String path)
	{
		try {
			sprite = ImageIO.read(new File(path));
			loadedFile = "Loaded: " + path;
		} catch (IOException e) {
			e.printStackTrace();
			sprite = null;
		}
	}
	public void update(double delta, Matrix3x3f viewport)
	{
		
	}

	public void render(Graphics g)
	{	
		g.drawImage(renderedImage, 0, 0, null);	
	}
}
