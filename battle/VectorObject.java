package javagames.battle;

import java.awt.Color;
import java.awt.Graphics;
import javagames.util.*;

public class VectorObject implements Drawable{

	private Vector2f[] vector;
	private Vector2f[] vectorCopy;
	private Matrix3x3f matrix;
	private Matrix3x3f viewport;
	
	public VectorObject ( Matrix3x3f viewportMatrix, Vector2f[] v ) {
		vector = v;
		vectorCopy = new Vector2f[vector.length];
		matrix = Matrix3x3f.identity();
		viewport = viewportMatrix;
	}

	public void translate( float x, float y ) {
		matrix = matrix.mul(Matrix3x3f.translate( x, y ));
	}
	
	public void scale( float x, float y ){
		matrix = matrix.mul(Matrix3x3f.scale( x, y ));
	}
	
	public void shear( float x, float y ){
		matrix = matrix.mul(Matrix3x3f.shear( x, y));
	}
	
	public void rotate( float rad ){
		matrix = matrix.mul(Matrix3x3f.rotate(rad));
	}
	
	public void setViewport( Matrix3x3f viewportMatrix){
		viewport = viewportMatrix;
	}
	
	@Override
	public void updateWorld() {
		
		for (int i = 0; i < vector.length; ++i) {
			vectorCopy[i] = matrix.mul(vector[i]);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < vector.length; ++i) {
			vectorCopy[i] = viewport.mul(vectorCopy[i]);
		}
		
		Vector2f P;
		Vector2f S = vectorCopy[vector.length - 1];
		for (int i = 1; i < vector.length; ++i){
			P = vectorCopy[i];
			g.drawLine((int) S.x, (int) S.y, (int) P.x, (int) P.y);
			S = P;
		}
	}
}