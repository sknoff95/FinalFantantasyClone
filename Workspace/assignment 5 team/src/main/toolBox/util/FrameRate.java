package game.toolBox.util;
/**
 * @author einst
 * the class that calculates the framerate
 */
public class FrameRate {
	/**
	 * the current frameRate.
	 */
	private String frameRate = "FPS 5000";
	/**
	 * the last specified time.
	 */
	private long lastTime;
	/**
	 * the current time - the last time.
	 */
	private long delta;
	/**
	 * the number of frames run per second, should be around 30 or 60.
	 */
	private int frameCount;
	/**
	 * runs when the object is initialized.
	 */
	public void initialize()
	{
	  	lastTime = System.currentTimeMillis();
    }
	/**
	 * returns the formated frame rate.
	 * @return the formated frame rate.
	 */
	public String getFrameRate()
	{
		return frameRate;
	}
	/**
	 * run once per frame.
	 */
	public void calculate() 
	{
		long current = System.currentTimeMillis();
		delta += current - lastTime;
		lastTime = current;
		frameCount++;
		if (delta >= 1000)
		{
			delta -= 1000;
			frameRate = String.format("FPS %d", frameCount);
			frameCount = 0;
		}
	}
}
