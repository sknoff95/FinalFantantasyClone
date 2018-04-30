package Character;

import BoundingObject.*;
import SimpleFramework.*;

public class Rogue extends PlayerCharacter{
	
	public Rogue(Matrix3x3f viewport) {
		super("src/Character/rouge.png", 30, 18, "Gary the Thief", 1, 1, 1);
		setSp(35);
		setHp(30);
		grabFrame(102, 34);
	}

	@Override
	public int lvUp()
	{
		super.lvUp();
		str += 2; 
		con++;
		intel++;
		//Tentative hp upgrade equation
		hp = hp + con*2;
		
		return lv;		
	}
	
	@Override
	public void act(int action, Character target)
	{
		switch(action)
		{
		case -1:
			stunned = false;
			break;
		case 1: 
			attack(target);
			break;
		case 2:
			stealth();
			break;
		//Use health potion
		case 4:
			heal(25);
			break;
		//Use revive scroll
		case 5:
			if(!target.isAlive())
			{
				target.alive = true;
				target.heal(25);
			}
			break;
		}
	}
	
	private void attack(Character target)
	{
		int dmg = (rand.nextInt(5) + 3) + str;
		int crit = rand.nextInt(10) + 1;
		
		if(crit < 3)
			dmg += (dmg*.2);
		
		if(stealth)
		{
			dmg += 3;
			stealth = false;
			setAlpha(1.0f);
		}
		
		target.damage(dmg);
	}
	
	private void stealth()
	{
		stealth = true;
		setAlpha(0.5f);
	}
	
	@Override
	public void stepUp(Matrix3x3f viewport)
	{
		//Last two will change with rogue scale
		transform(new Vector2f(2.0f, 0.0f), 0.0f, viewport, 2.0f, 2.0f);
	}
	
	@Override
	public void stepBack(Matrix3x3f viewport)
	{
		//Last two will change with rogue scale
		transform(new Vector2f(-2.0f, 0.0f), 0.0f, viewport, 2.0f, 2.0f);
	}
}


