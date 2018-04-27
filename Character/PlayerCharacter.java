package Character;

import SimpleFramework.*;

public class PlayerCharacter extends Character{

	private int xp, xpmax;
	public boolean stealth;
	
	public PlayerCharacter(String fileName, int h, int w, int hp, String name, int str, int sp, int con, int intel) {
		super(fileName, h, w, hp, 1, name, str, sp, con, intel);
		
		xpmax = 6;
		xp = 0;
		
		stealth = false;
	}
	
	public int getXp()
	{
		return xp;
	}
	
	public int getXpMax()
	{
		return xpmax;
	}
	
	protected int lvUp()
	{
		lv++;
		return lv;
	}
	
	public int xpUp(int exp)
	{
		if(xp + exp >= xpmax)
		{
			xp = 0;
			xpmax = 10+(lvUp()^2)/2;
		}
		else
			xp += exp;
		
		return xp;
	}
	
	@Override
	public void stepUp(Matrix3x3f viewport)
	{
		transform(new Vector2f(2.0f, 0.0f), viewport);
	}
	
	@Override
	public void stepBack(Matrix3x3f viewport)
	{
		transform(new Vector2f(-2.0f, 0.0f), viewport);
	}
}


