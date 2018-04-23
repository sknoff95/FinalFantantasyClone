package main.toolbox.images;

import java.awt.Graphics;

import main.toolbox.util.Matrix3x3f;
/**
 * @author JP
 * the interface that every object that is rendered or updated should have
 */
public interface Drawable 
{
/**
 * Update the World Matrix
 * @param delta the change in time since the last update
 * @param viewport the way to view the game
 */
public void update(double delta, Matrix3x3f viewport); 
/**
 * renders the object in the world matrix
 * @param g the graphics object
 */
public void render(Graphics g);
}
