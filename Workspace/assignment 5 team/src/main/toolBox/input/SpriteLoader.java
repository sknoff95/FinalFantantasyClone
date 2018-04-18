package game.toolBox.input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.imageio.stream.ImageInputStream;

/**
 * @author einst
 * loads the sprite based on the given path
 */
public class SpriteLoader {
	/**
	 * loads the file
	 * @param classy
	 * @param filePath for if the images is in the file directory 
	 * @param resPath for if the image is in the recorces directory
	 * @return the way to load the image
	 */
	public static ImageInputStream load(Class<?> classy, String filePath, String resPath)
	{
		ImageInputStream in = null;
		if (!(resPath == null || resPath.isEmpty()))
		{
			in = (ImageInputStream) classy.getResourceAsStream(resPath);
		}
		else
		{
			try
			{
				in = (ImageInputStream) new FileInputStream(filePath);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		return in;
	}
}
