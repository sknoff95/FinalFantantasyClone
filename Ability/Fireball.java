package Ability;

import SimpleFramework.*;
import Sprite.Sprite;

public class Fireball extends Sprite{

	private int framex, framey;
	
	public Fireball()
	{
		super("src/Ability/fireball.png", 14, 32);
		framex = 0; 
		framey = 0;
	}
	
	public void move()
	{
		if(framex + 32 > 96 && framey == 0)
		{
			updateHeight(12);
			framex = 0;
			framey = 20;
		}
		else if(framex + 32 > 96)
		{
			updateHeight(14);
			framex = 0;
			framey = 0;
		}
		grabFrame(framex, framey);
		
		framex += 32;
	}
	
	public void translate(Vector2f trans, Matrix3x3f viewport)
	{
		transform(trans, viewport);
	}
}


