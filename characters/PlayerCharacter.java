package javagames.characters;

public class PlayerCharacter extends Character{

	private int xp, xpmax;
	
	public PlayerCharacter(String fileName, int h, int w, String name, int str, int con, int intel) {
		super(fileName, h, w, 1, name, str, con, intel);
		
		xpmax = 6;
		xp = 0;
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
}


