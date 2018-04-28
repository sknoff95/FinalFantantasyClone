package Ability;

import SimpleFramework.*;
import Sprite.Sprite;

public class Bulwark extends Sprite{

	private int framey;
	
	public Bulwark()
	{
		super("src/Ability/fevor.png", 8, 32);
		
		framey = 0;
	}
	
	public void sparkle()
	{
		if(framey + 8 > 32)
			framey = 0;
		
		grabFrame(0, framey);
		
		framey += 8;
	}
	
	public void move(Vector2f trans, Matrix3x3f viewport)
	{
		transform(trans, viewport);
	}
}