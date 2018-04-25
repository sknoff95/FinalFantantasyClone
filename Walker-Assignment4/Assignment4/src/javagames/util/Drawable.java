package javagames.util;

import java.awt.Graphics;

public interface Drawable {
	void updateWorld(); // Update the World Matrix

	void renderLine(Graphics g); // Draw the object with passed Graphics

	void renderMatrix(Graphics g, Matrix3x3f m); // Draw the Object with Passed Graphics and Matrix

	void renderOval(Graphics g, Matrix3x3f m); // Draws the Oval

	public void renderHitbox(Graphics g, Matrix3x3f m); // Used for drawing hitboxes
}
