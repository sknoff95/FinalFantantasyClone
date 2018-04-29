package Character;

import BoundingObject.*;
import SimpleFramework.*;

public class Rogue extends PlayerCharacter{
	
	public Rogue(Matrix3x3f viewport) {
		super("src/Character/rouge.png", 30, 18, 30, "Gary the Thief", 1, 35, 1, 1);
		grabFrame(102, 34);
		setAlpha(1.0f);
		bound = new BoundingBox(new Vector2f(-0.97f, 0.30f), new Vector2f(-1.43f, 1.05f));
		setBound(bound, viewport);
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
	public void act(int action, Character[] targetArr, int target)
	{
		switch(action)
		{
		case -1:
			stunned = false;
			break;
		case 1: 
			this.attack(targetArr, target);
			break;
		case 2:
			this.stealth();
			break;
		//Use health potion
		case 4:
			targetArr[target].heal(25);
			break;
		//Use revive scroll
		case 5:
			if(!targetArr[target].isAlive())
			{
				targetArr[target].alive = true;
				targetArr[target].heal(25);
			}
			break;
		}
	}
	
	private void attack(Character[] enemies, int target)
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
		
		enemies[target].damage(dmg);
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


