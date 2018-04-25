package javagames.util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class BoundingShapes implements Drawable {
	private int colorSelection; // Color Selection index

	public ArrayList<Vector2f> points; // Arraylist for Points
	public ArrayList<Vector2f> circle; // Arraylist for Circles
	public Matrix3x3f pointmat; // Point matrix
	public Vector2f center; // Center of Arraylists

	private float deltar = 0; // change in rotation
	public float mover = 0; // total movement of rotation

	private float deltax = 0; // change in x values
	private float deltay = 0; // change in y values

	public float movex = 0; // total x movement
	public float movey = 0; // total y movement

	private Color[] COLORS = { Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.YELLOW }; // Array for color
																								// choice

	/*
	 * Initializes the Arraylists and Vector2fs
	 */
	public BoundingShapes() {
		points = new ArrayList<Vector2f>();
		center = new Vector2f();
		circle = new ArrayList<Vector2f>();
	}

	/*
	 * Sets the color
	 */
	public void setColor(int selection) {
		colorSelection = selection;
	}

	/*
	 * Sets the rotation
	 */
	public void setRotation(float r) {
		mover = r;
	}

	/*
	 * Sets change in rotation
	 */
	public void changeRotation(float r) {
		deltar += r;
	}

	/*
	 * Reverse X movement
	 */
	public void reverseX() {
		deltax = -deltax;
	}

	/*
	 * reverses Y movement
	 */
	public void reverseY() {
		deltay = -deltay;
	}

	/*
	 * sets x and y change in movement
	 */
	public void setVelocity(float x, float y) {
		deltax = x;
		deltay = y;
	}

	/*
	 * Changes x movement
	 */
	public void changeX(float x) {
		deltax = x;
	}

	/*
	 * Changes y movement
	 */
	public void changeY(float y) {
		deltay = y;
	}

	/*
	 * Adds a point to the point array list
	 */
	public void addPointLine(Vector2f p) {
		points.add(p);
	}

	/*
	 * Changes point x to given point
	 */
	public void setPoint(Vector2f p, int x) {
		points.get(x).x = p.x;
		points.get(x).y = p.y;
	}

	/*
	 * changes circle point x to given point
	 */
	public void setCirclePoint(Vector2f p, int x) {
		circle.get(x).x = p.x;
		circle.get(x).y = p.y;
	}

	/*
	 * Adds a circle point
	 */
	public void addCirclePoint(Vector2f p) {
		circle.add(p);
	}

	/*
	 * Used to differentiate between the first and second point in the circle array
	 * list Only used to visible clarity
	 */
	public void setCircleDim(Vector2f p) {
		circle.add(p);
	}

	/*
	 * reverse the rotation
	 */
	public void reverseRotation() {
		deltar = -deltar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javagames.util.Drawable#updateWorld() Updates the world, and the
	 * matrixes
	 */
	@Override
	public void updateWorld() {
		movex += deltax;
		movey += deltay;
		mover += deltar;
		pointmat = Matrix3x3f.identity();
		pointmat = pointmat.mul(Matrix3x3f.rotate(mover));
		pointmat = pointmat.mul(Matrix3x3f.translate(movex, movey));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javagames.util.Drawable#renderHitbox(java.awt.Graphics,
	 * javagames.util.Matrix3x3f) Used for Sprites to render their hitbox, may have
	 * re-invented the wheel for drawRect
	 */
	@Override
	public void renderHitbox(Graphics g, Matrix3x3f view) {
		Vector2f A = view.mul(pointmat.mul(points.get(0)));
		Vector2f B = view.mul(pointmat.mul(points.get(1)));
		g.setColor(COLORS[colorSelection]);
		g.drawLine((int) B.x, (int) B.y, (int) A.x, (int) B.y);
		g.drawLine((int) A.x, (int) B.y, (int) A.x, (int) A.y);
		g.drawLine((int) A.x, (int) A.y, (int) B.x, (int) A.y);
		g.drawLine((int) B.x, (int) A.y, (int) B.x, (int) B.y);

	}

	/*
	 * Similar to the above, only with less code
	 */
	public void bounds(Graphics g, Matrix3x3f view) {
		Vector2f A = view.mul(pointmat.mul(points.get(0)));
		Vector2f B = view.mul(pointmat.mul(points.get(1)));
		g.setColor(COLORS[colorSelection]);
		g.drawRect((int) A.x, (int) A.y, (int) B.x, (int) B.y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javagames.util.Drawable#renderMatrix(java.awt.Graphics,
	 * javagames.util.Matrix3x3f) Renders x number of points for a given object
	 * based upon a viewport matrix
	 */
	@Override
	public void renderMatrix(Graphics g, Matrix3x3f view) {
		Vector2f A = null;
		Vector2f B = view.mul(pointmat.mul(points.get(points.size() - 1)));
		g.setColor(COLORS[colorSelection]);
		for (int x = 0; x <= points.size() - 1; x++) {
			A = view.mul(pointmat.mul(points.get(x)));
			g.drawLine((int) B.x, (int) B.y, (int) A.x, (int) A.y);
			B = A;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javagames.util.Drawable#renderLine(java.awt.Graphics) Draws lines using
	 * absolute coordinates
	 */
	@Override
	public void renderLine(Graphics g) {
		Vector2f A = pointmat.mul(points.get(0));
		Vector2f B = pointmat.mul(points.get(1));
		g.setColor(COLORS[colorSelection]);
		g.drawLine((int) B.x, (int) B.y, (int) A.x, (int) A.y);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javagames.util.Drawable#renderOval(java.awt.Graphics,
	 * javagames.util.Matrix3x3f) Draws Ovals or Circles based upon viewport
	 * matrixes
	 */
	@Override
	public void renderOval(Graphics g, Matrix3x3f view) {
		Vector2f A = view.mul(pointmat.mul(circle.get(0)));
		Vector2f B = view.mul(pointmat.mul(circle.get(1)));
		g.setColor(COLORS[colorSelection]);
		g.drawOval((int) A.x, (int) A.y, 90, 90);
	}
}
