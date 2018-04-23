package main.toolbox.util;
/**
 * @author einst
 * the class that creates the viewports
 */
public class Utility 
{
	/**
	 * creates a basic viewport
	 * @param worldWidth the width of the unscaled world
	 * @param worldHeight the heigth of the unscaled world
	 * @param screenWidth the width of the screen
	 * @param screenHeight the height of the screen
	 * @return the matrix that is used to modify the movements of the objects so that they orient and resize correctly
	 */
	public static Matrix3x3f createViewport(float worldWidth, float worldHeight, float screenWidth, float screenHeight)
	{
		float sx = (screenWidth - 1) / worldWidth;
		float sy = (screenHeight - 1) / worldHeight;
		float tx = (screenWidth - 1) / 2.0f;
		float ty = (screenHeight - 1) / 2.0f;
		Matrix3x3f viewport = Matrix3x3f.scale(sx, -sy);
		viewport = viewport.mul(Matrix3x3f.translate(tx, ty));
		return viewport;
	}
	/**
	 * creates the reverce viewport used for the mouse
	 * @param worldWidth the width of the unscaled world
	 * @param worldHeight the heigth of the unscaled world
	 * @param screenWidth the width of the screen
	 * @param screenHeight the height of the screen
	 * @return the matrix that is used to modify the movements of the mouse so that it orients and scales correctly
	 */
	public static Matrix3x3f createReverseViewport(float worldWidth, float worldHeight, float screenWidth, float screenHeight)
	{
		float sx = worldWidth / (screenWidth - 1);
		float sy = worldHeight / (screenHeight - 1);
		float tx = (screenWidth - 1) / 2.0f;
		float ty = (screenHeight - 1) / 2.0f;
		Matrix3x3f viewport = Matrix3x3f.translate(-tx, -ty);
		viewport = viewport.mul(Matrix3x3f.scale(sx, -sy));
		return viewport;
	}
}
