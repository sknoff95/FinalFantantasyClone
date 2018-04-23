package main.toolbox.util;

import main.toolbox.bounds.Vector2f;

/**
 * @author JP
 * the matrix object that holds all the information regarding a single matrix
 */
public class Matrix3x3f {
	/**
	 * the 3 columns and rows values
	 */
	private float [][] m = new float[3][3];
	/**
	 * the constructor that creates a matrix off of a given matrix
	 * @param m
	 */
	public Matrix3x3f( float[][] m) 
	{
		this.m = m;
	}
	/**
	 * adds each point from m to each respective point from m1 
	 * and returns a new matrix with the result
	 * @param m1 the matrix that is being added to the caller
	 * @return the new matrix that is their sum
	 */
	public Matrix3x3f add (Matrix3x3f m1)
	{
		return new Matrix3x3f(new float[][] {
		{	this.m[0][0] + m1.m[0][0],
			this.m[0][1] + m1.m[0][1],
			this.m[0][2] + m1.m[0][2]},
		{	this.m[1][0] + m1.m[1][0],
			this.m[1][1] + m1.m[1][1],
			this.m[1][2] + m1.m[1][2]},
		{	this.m[2][0] + m1.m[2][0],
			this.m[2][1] + m1.m[2][1],
			this.m[2][2] + m1.m[2][2]}});
	}
	/**
	 * subtracts each point from m from each respective point from m1 
	 * and returns a new matrix with the result
	 * @param m1 the matrix that will be substracted from the calling matrix
	 * @return a new matrix with the subtraction done
	 */
	public Matrix3x3f sub (Matrix3x3f m1)
	{
		return new Matrix3x3f(new float[][] {
		{	this.m[0][0] - m1.m[0][0],
			this.m[0][1] - m1.m[0][1],
			this.m[0][2] - m1.m[0][2]},
		{	this.m[1][0] - m1.m[0][0],
			this.m[1][1] - m1.m[0][1],
			this.m[1][2] - m1.m[0][2]},
		{	this.m[2][0] - m1.m[0][0],
			this.m[2][1] - m1.m[0][1],
			this.m[2][2] - m1.m[0][2]}});
	}
	/**
	 * multiplys the calling matrix with m1 in the unique way matrixes multiply
	 * @param m1 the new matrix to be multiplyed to
	 * @return a new matrix that is is the result
	 */
	public Matrix3x3f mul(Matrix3x3f m1)
	{
		return new Matrix3x3f(new float[][] {
			{ this.m [0][0] * m1.m [0][0]// fills out m[0][0]
			+ this.m [0][1] * m1.m [1][0]
			+ this.m [0][2] * m1.m [2][0],
			  this.m [0][0] * m1.m [0][1]// fills out m[0][1]
			+ this.m [0][1] * m1.m [1][1]
			+ this.m [0][2] * m1.m [2][1],
			  this.m [0][0] * m1.m [0][2]// fills out m[0][2]
			+ this.m [0][1] * m1.m [1][2]
			+ this.m [0][2] * m1.m [2][2]},
			{ this.m [1][0] * m1.m [0][0]// fills out m[1][0]
			+ this.m [1][1] * m1.m [1][0]
			+ this.m [1][2] * m1.m [2][0],
			  this.m [1][0] * m1.m [0][1]// fills out m[1][1]
			+ this.m [1][1] * m1.m [1][1]
			+ this.m [1][2] * m1.m [2][1],
			  this.m [1][0] * m1.m [0][2]// fills out m[1][2]
			+ this.m [1][1] * m1.m [1][2]
			+ this.m [1][2] * m1.m [2][2]},
			{ this.m [2][0] * m1.m [0][0]// fills out m[2][0]
			+ this.m [2][1] * m1.m [1][0]
			+ this.m [2][2] * m1.m [2][0],
			  this.m [2][0] * m1.m [0][1]// fills out m[2][1]
			+ this.m [2][1] * m1.m [1][1]
			+ this.m [2][2] * m1.m [2][1],
			  this.m [2][0] * m1.m [0][2]// fills out m[2][2]
			+ this.m [2][1] * m1.m [1][2]
			+ this.m [2][2] * m1.m [2][2]}			
		});
	}
	/**
	 * sets the matrix's array
	 * @param m1 the array to be set
	 */
	public void setMatrix(float[][] m1)
	{
		m = m1;
	}
	/**
	 * creates a matrix that is all zeros
	 * @return the new matrix
	 */
	public static Matrix3x3f zero()
	{
		return new Matrix3x3f(new float[][] {
			{0.0f, 0.0f, 0.0f},
			{0.0f, 0.0f, 0.0f},
			{0.0f, 0.0f, 0.0f}
		});
	}
	/**
	 * creates the identity matrix
	 * @return the identity matrix
	 */
	public static Matrix3x3f identity() 
	{
		return new Matrix3x3f(new float[][] {
			{1.0f, 0.0f, 0.0f},
			{0.0f, 1.0f, 0.0f},
			{0.0f, 0.0f, 1.0f}			
		});
	}
	/**
	 * the method for translating the matrix
	 * @param v the vector that the matrix translates by
	 * @return the translated matrix
	 */
	public static Matrix3x3f translate(Vector2f v)
	{
		return translate(v.x,v.y);
	}
	/**
	 * the helper method for the translate method
	 * @param x the x amount the matrix needs to translate by
	 * @param y the y amount the matrix needs to translate by
	 * @return the translated matrix
	 */
	public static Matrix3x3f translate(float x, float y)
	{
		return new Matrix3x3f(new float[][] {
			{1.0f, 0.0f, 0.0f},
			{0.0f, 1.0f, 0.0f},
			{   x,    y, 1.0f}
		});
	}
	/**
	 * the method for scaling the matrix
	 * @param v the vector that the matrix scale by
	 * @return the scaled matrix
	 */
	public static Matrix3x3f scale(Vector2f v)
	{
		return scale(v.x,v.y);
	}
	/**
	 * the helper method for the scale method
	 * @param x the x amount the matrix needs to scale by
	 * @param y the y amount the matrix needs to scale by
	 * @return the scaled matrix
	 */
	public static Matrix3x3f scale(float x, float y)
	{
		return new Matrix3x3f(new float[][] {
			{   x, 0.0f, 0.0f},
			{0.0f,    y, 0.0f},
			{0.0f, 0.0f, 1.0f}
		});
	}
	/**
	 * the method for shearing the matrix
	 * @param v the vector that the matrix sheared by
	 * @return the sheared matrix
	 */
	public static Matrix3x3f shear(Vector2f v)
	{
		return shear(v.x,v.y);
	}
	/**
	 * the helper method for the shear method
	 * @param x the x amount the matrix needs to shear by
	 * @param y the y amount the matrix needs to shear by
	 * @return the scaled matrix
	 */
	public static Matrix3x3f shear(float x, float y)
	{
		return new Matrix3x3f(new float[][] {
			{1.0f,    y, 0.0f},
			{   x, 1.0f, 0.0f},
			{0.0f, 0.0f, 1.0f}
		});
	}
	/**
	 * the method that rotates the object: uses radians
	 * @param rad the amount in radians that needs to rotate
	 * @return the rotation matrix
	 */
	public static Matrix3x3f rotate(float rad) // in radians
	{
		return new Matrix3x3f(new float[][] {
			{ (float) Math.cos(rad), (float) Math.sin(rad), 0.0f},
			{ (float)-Math.sin(rad), (float) Math.cos(rad), 0.0f},
			{                  0.0f,                  0.0f, 1.0f}
		});
	}
	/**
	 * the vector that multiples the matrix into the vector
	 * @param vec the vector to be multiplied
	 * @return the multiplied vector
	 */
	public Vector2f mul(Vector2f vec)
	{
		return new Vector2f
		( vec.x*this.m[0][0]
		+ vec.y*this.m[1][0]
		+ vec.w*this.m[2][0],
		  vec.x*this.m[0][1]
		+ vec.y*this.m[1][1]
		+ vec.w*this.m[2][1],
		  vec.x*this.m[0][2]
		+ vec.y*this.m[1][2]
		+ vec.w*this.m[2][2]);
	}
	@Override
	/**
	 * converts the origional toString to return a string that is understandable
	 * @return a string that summerizes this itteration of the class
	 */
	public String toString()
	{
		StringBuilder buf = new StringBuilder();
		for(int i = 0; i < 3; i++)
		{
			buf.append("[");
			buf.append(m[i][0]);
			buf.append(",\t");
			buf.append(m[i][1]);
			buf.append(",\t");
			buf.append(m[i][2]);
			buf.append("]");

		}
		return buf.toString();
	}
}
