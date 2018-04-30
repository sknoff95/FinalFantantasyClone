package Character;

import SimpleFramework.*;

public class Goblin extends Character{
	
	public Goblin(int lv) {
		//For level, should probably input a random between characterLevel-2 and characterLevel+2 in the logic. Same goes for speed
		super("src/Character/goblin.png", 32, 24, lv, "Goblin", 1+lv, 1+lv, 1+(2*lv));
		setSp(rand.nextInt(40) + 20);
		setHp((rand.nextInt(20) + 10) + con);
		grabFrame(4, 96);
	}
	
	@Override
	public void generateAction(Character[] enemies, Character[] players)
	{
		if(stunned)
			act = -1;
		else
		{
			act = 1;
			int target = rand.nextInt(3) + 0;
			tgt = players[target];
			
			for(int i = 0; i < enemies.length; i++)
			{
				if(enemies[i].getHp() < getHpMax() / 4)
				{
					act = 2;
					tgt = enemies[i];
					break;
				}
			}
		}
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
			this.attack(target);
			break;
		case 2:
			this.heal(target);
			break;
		}
	}
	
	private void attack(Character target)
	{
		int dmg = rand.nextInt(2) + 1;
		if(!target.stealth)
			target.damage(dmg);
	}
	
	private void heal(Character target)
	{
		int heal = rand.nextInt(5) + 2;
		heal += intel;
		
		target.heal(heal);
	}
	
	@Override
	public void stepUp(Matrix3x3f viewport)
	{
		//The last two values will change depending on what we scale the goblin by
		transform(new Vector2f(-2.0f, 0.0f), 0.0f, viewport, 1.0f, 1.0f);
	}
	
	@Override
	public void stepBack(Matrix3x3f viewport)
	{
		//The last two values will change depending on what we scale the goblin by
		transform(new Vector2f(2.0f, 0.0f), 0.0f, viewport, 1.0f, 1.0f);
	}
}


