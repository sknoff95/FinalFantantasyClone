package main.toolbox.input;

import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;
//TODO the mouse and the keyboard cant seem to receve input at the same time?
/**
 * @author JP
 * the class that has the input logic for the mouse
 */
public class RelativeMouseInput implements MouseListener, MouseMotionListener, MouseWheelListener {
	/**
	 * the number of buttons on the regular mouse
	 */
	public static final int MOUSEBUTTONS = 3;
	/**
	 * the mouses current position in points before being polled (TODO unsure)
	 */
	private Point mousePos;
	/**
	 * the fake mouses current position in points (TODO unsure)
	 */
	private Point currentPos;
	/**
	 * the boolean that represents if the mouse buttons are pressed or not
	 */
	private boolean[] mouse;
	/**
	 * the int that goes along with the each button and checks for how long the mouse has been pressed
	 */
	private int[] polled; 
	/**
	 * the amount the notch on the scroll wheel has changed
	 */
	private int notches;
	/**
	 * the amount the notches have changed since last poll
	 */
	private int polledNotches;
	/**
	 * the change the mouse did sense last poll in y and x respectivly
	 */
	private int dy, dx;
	/**
	 * the current componant the mouse is under
	 */
	private Component component;
	/**
	 * the thing that keeps the mouse in the center if relative is on
	 */
	private Robot robot;
	/**
	 * the boolean that if on keeps the mouse in the center and lets a fake mouse act as the real mouse
	 */
	private boolean relative;
	/**
	 * creates the mouse and sets most of its undefined variables
	 * @param component the componant the mouse is set to be on
	 */
	public RelativeMouseInput (Component component ) 
	{ 
		this.component = component; 
		try 
		{
			robot = new Robot(); 
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}    
		mousePos = new Point( 0, 0 );
		currentPos = new Point( 0, 0 );
		mouse = new boolean[ MOUSEBUTTONS ];
		polled = new int[ MOUSEBUTTONS ];
	}
	/**
	 * gets the componant's center
	 * @return the componant's center
	 */
	private Point getComponantCenter()
	{
		int w = component.getWidth();
		int h = component.getHeight();
		return new Point(w/2, h/2);
	}
	/**
	 * centers the mouse
	 */
	private void centerMouse()
	{
		if (robot != null && component.isShowing())
		{
			Point center = getComponantCenter();
			SwingUtilities.convertPointToScreen(center, component);
			robot.mouseMove(center.x, center.y);
		}
	}
	/**
	 * Basically the update method for the mouse
	 */
	public synchronized void poll()
	{
		if (relative)
		mousePos = new Point(dx, dy);
		else 
		mousePos = new Point(currentPos);
		dx = dy = 0;
		polledNotches = notches;
		notches = 0;
		for (int i = 0; i < mouse.length; i++)
		{
			if (mouse[i])
			{
				polled[i]++;
			}
			else
			{
				polled[i] = 0;
			}
		}
	}
	/**
	 * returns true if reletive
	 * @return if reletive
	 */
	public boolean isReletive()
	{
		return relative;
	}
	/**
	 * sets relative
	 * @param relative if the mouse is cloned
	 */
	public void setRelative(boolean relative)
	{
		this.relative = relative;
		if (relative)
		{
			centerMouse();
		}
	}
	/**
	 * gets the position of the mouse
	 * @return the position of the mouse
	 */
	public Point getPosition()
	{
		return mousePos;
	}
	/**
	 * gets the polledNotches
	 * @return polledNotches
	 */
	public int getNotches()
	{
		return polledNotches;
	}
	/**
	 * returns if the button is pressed down
	 * @param button the button number that is in question
	 * @return if the button is pressed down
	 */
	public boolean buttonDown(int button)
	{
		//TODO there was an error which didnt exist before
		return polled[button] > 0;
	}
	/**
	 * returns if the button is pressed but not if it is held for more than 1 poll
	 * @param button the button in question
	 * @return if the button meets the spesified requirements
	 */
	public boolean buttonDownOnce(int button)
	{
		return polled[button] == 1;
	}
	/**
	 * some internal mouse stuff I do not understand
	 */
	public synchronized void mousePressed(MouseEvent e)
	{
		int button = e.getButton() - 1;
		if(button >= 0 && button < mouse.length)
		{
			mouse[button] = true;
		}
	}
	/**
	 * some internal mouse stuff I do not understand
	 */
	public synchronized void mouseReleased(MouseEvent e)
	{
		int button = e.getButton() - 1;
		if(button >= 0 && button < mouse.length)
		{
			mouse[button] = false;
		}
	}
	/**
	 * some internal mouse stuff I do not understand
	 */
	public synchronized void mouseEntered(MouseEvent e) 
	{
		mouseMoved(e);
	}
	/**
	 * some internal mouse stuff I do not understand
	 */
	public synchronized void mouseExited(MouseEvent e) 
	{
		mouseMoved(e);
	}
	/**
	 * some internal mouse stuff I do not understand
	 */
	public synchronized void mouseDragged(MouseEvent e)
	{
		mouseMoved(e);
	}
	/**
	 * some internal mouse stuff I do not understand
	 */
	public synchronized void mouseMoved(MouseEvent e) 
	{
		if(isReletive())
		{
			Point p = e.getPoint();
			Point center = getComponantCenter();
			dx += p.x - center.x;
			dy += p.y - center.y;
			centerMouse();
		}
		else
		currentPos = e.getPoint(); 
	}
	/**
	 * sets the number of notches the mouse wheel has moved since last poll
	 */
	public synchronized void mouseWheelMoved(MouseWheelEvent e)
	{
		notches += e.getWheelRotation();
	}
	/**
	 * the method that does nothing
	 */
	public void mouseClicked(MouseEvent arg0) {
		
	}
}
