package game.toolBox.util;
/**
 * @author JP
 * the vectors that all objects are made of with three variables so that they can be multiplied against 3*3 matrixes
 */
public class Vector2f{
	/**
	 * x signifys the x position
	 */
	public float x;
	/**
	 * y signifys the y position
	 */
	public float y;
	/**
	 * w is a placeholder
	 */
	public float w;
	/**
	 * the default constructor
	 */
	public Vector2f()
	{
		x = 0.0f;
		y = 0.0f;
		w = 1.0f;
	}
	/**
	 * the constructor that bases a new vector off of a old vector 
	 * @param v
	 */
	public Vector2f(Vector2f v)
	{
		x = v.x;
		y = v.y;
		w = v.w;
	}
	/**
	 * the constructor that creates a new vector based on a given x and y
	 * @param xa the given x
	 * @param ya the given y
	 */
	public Vector2f(float xa, float ya)
	{
		x = xa;
		y = ya;
		w = 1.0f;
	}
	/**
	 * the constructor that creates a new vector based on a given x, y, and w
	 * @param xa the given x
	 * @param ya the given y
	 * @param wa the given w
	 */
	public Vector2f(float xa, float ya, float wa)
	{
		x = xa;
		y = ya;
		w = wa;
	}
	/**
	 * a method that translates a vector
	 * @param xt the x it translates
	 * @param yt the y it translates
	 */
	public void translate(float xt, float yt)
	{
		x += xt;
		y += yt;
	}
	/**
	 * a method that scales a vector
	 * @param sx the x it scales
	 * @param sy the y it scales
	 */
	public void scale(float sx, float sy)
	{
		x *= sx;
		y *= sy;
	}
	/**
	 * the method that rotates the vector, which is confusing because its just defighned as a point
	 * @param rad the amount that is rotated in radians
	 */
	public void rotate(float rad)
	{
		float tmp = (float)(x * Math.cos(rad) - y * Math.sin(rad));
		y = (float)(x * Math.sin(rad) + y * Math.cos(rad));
		x = tmp;
	}
	/**
	 * the method that shears the vector, which I also dont understand how it can be done to one point
	 * @param sx the amount it is sheared in the x
	 * @param sy the amount it is sheared in the y
	 */
	public void shear(float sx, float sy)
	{
		float tmp = x + sx* y;
		y = y + sy *x;
		x = tmp;
	}
	/**
	 * returns the inverse of the vector
	 * @return a vector that is an inverse
	 */
	public Vector2f inv()
	{
		return new Vector2f(-x, -y);
	}
	/**
	 * the summerisation of the two vectors
	 * @param v the vector being added to this vector.
	 * @return the sum of the two vectors.
	 */
	public Vector2f add(Vector2f v)
	{
		return new Vector2f(x + v.x, y + v.y);
	}
	/**
	 * subtracts the given vector from the vector
	 * @param v the subtracting vector 
	 * @return the difference between the origional and the new vector
	 */
	public Vector2f sub(Vector2f v)
	{
		return new Vector2f(x - v.x, y - v.y);
	}
	/**
	 * the product of a number and the vector
	 * @param scalar what is multiplied to the vector
	 * @return the product
	 */
	public Vector2f mul(float scalar)
	{
		return new Vector2f(scalar * x, scalar * y);
	}
	/**
	 * the quotent of the vector and the number
	 * @param scalar what is multiplied
	 * @return the product
	 */
	public Vector2f div(float scalar)
	{
		return new Vector2f(x / scalar, y / scalar);
	}	
	/**
	 * the length from the origin
	 * @return the length
	 */
	public float len()
	{
		return (float) Math.sqrt(x * x + y * y);
	}
	/**
	 * the square of the length to the origin 
	 * @return the number that represents what is subscribed
	 */
	public float lensSqr()
	{
		return x * x + y * y;
	}
	/**
	 * the vector that is the same in angle but has a length of 1
	 * @return a vector that is length one but angled x or y
	 */
	public Vector2f norm()
	{
		return div(len());
	}
	/**
	 * gives the perpendicular of a calling vector
	 * @return the perpendicular
	 */
	public Vector2f perp()
	{
		return new Vector2f(-y, x);
	}
	/**
	 * something 
	 * @param v the new vector that if added to the origional vector makes 90 degrees
	 * @return something
	 */
	public float dot(Vector2f v)
	{
		return x * v.x + y * v.y;
	}
	/**
	 * the angle in radians
	 * @return the angle
	 */
	public float angle()
	{
		return (float) Math.atan2(y,x);
	}
	
	/**
	 * gives an angle given a radius and angle
	 * @param angle the angle in radians
	 * @param radius the radius of the new vector
	 * @return a new vector
	 */
	public static Vector2f polar(float angle, float radius)
	{
		return new Vector2f(radius * (float) Math.cos(angle), radius * (float) Math.sin(angle));
	}
}
