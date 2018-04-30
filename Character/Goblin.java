package Character;

import SimpleFramework.*;

public class Goblin extends Character{
	
	//private int act;
	//private character tgt;
	
	public Goblin(int hp, int lv, int sp) {
		//For level, should probably input a random between characterLevel-2 and characterLevel+2 in the logic. Same goes for speed
		super("res2/goblin.png", 32, 24, hp, lv, "Goblin", 1+lv, sp, 1+lv, 1+(2*lv));
		
		//act = -1;
		//tgt;
	}
	
	/*public void generateAction(Character[] enemies, Character[] players)
	{
		act = 1;
		int target = rand.nextInt(3) + 0;
		tgt = players[target];
		for(int i = 0; i < enemies.length; i++)
		{
			if(enemies[i].getHp() < getMaxHp()/4)
			{
				act = 2;
				tgt = enemies[i];
				break;
			}
		}
	}*/
	
	@Override
	public void act(int action, Character[] targetArr, int target)
	{
		switch(action)
		{
		case -1:
			stunned = false;
			break;
		case 1:
			this.attack(targetArr);
			break;
		case 2:
			this.heal(targetArr, target);
			break;
		}
		//act = -1;
		//tgt = new Character();
	}
	
	private void attack(Character[] party)
	{
		int dmg = rand.nextInt(2) + 1;
		int target;
		do
		{
		target = rand.nextInt(3) + 0;
		}while(party[target].stealth);
		
		//if(!party[target].stealth)
			//party[target].damage(dmg);
		party[target].damage(dmg);
	}
	
	private void heal(Character[] enemies, int target)
	{
		int heal = rand.nextInt(5) + 2;
		heal += intel;
		
		enemies[target].heal(heal);
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
	
	/*public int getAct()
	{
		return act;
	}*/
	
	/*public character getTgt()
	{
		return tgt;
	}*/
}


