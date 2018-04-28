package Character;

import Sprite.*;
import SimpleFramework.*;
import java.util.Random;

public class Character extends Sprite{

	protected int hp, hpmax;
	protected int lv;
	protected String name;
	protected boolean alive, stunned;
	protected int str, sp, con, intel;
	protected Random rand;
	
	public Character(String fileName, int h, int w, int hp, int lv, String name, int str, int sp, int con, int intel) {
		super(fileName, h, w);
		
		this.hp = hpmax = hp;
		this.lv = lv;
		this.name = name;
		alive = true;
		stunned = false;
		this.str = str;
		this.sp = sp;
		this.con = con;
		this.intel = intel;
		
		rand = new Random();
	}
	
	//This one is only used for wolf and slime
	public void act(int action, Character[] targetArr)
	{
	}
	
	//This one is used for all characters and also the goblin, that's why it's in character rather than playerCharacter
	public void act(int action, Character[] targetArr, int target)
	{
	}

	public boolean damage(int dmg)
	{
		flashOn();
		
		if(hp - dmg <= 0)
		{
			hp = 0;
			alive = false;
		}
		else
			hp -= dmg;
		
		return alive;
	}
	
	public void heal(int heal)
	{
		if(hp + heal >= hpmax)
			hp = hpmax;
		else
			hp += heal;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public void flashOn()
	{
		setAlpha(0);
	}
	
	//Now this one might change depending on whether or not we want their alpha to actually be 100 or if this changes for different sprites
	public void flashOff()
	{
		setAlpha(1.0f);
	}
	
	public void stepUp(Matrix3x3f viewport)
	{
		transform(new Vector2f(-2.0f, 0.0f), viewport);
	}
	
	public void stepBack(Matrix3x3f viewport)
	{
		transform(new Vector2f(2.0f, 0.0f), viewport);
	}
}


