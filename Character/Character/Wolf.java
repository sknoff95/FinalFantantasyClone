package Character;

import SimpleFramework.Matrix3x3f;
import SimpleFramework.Vector2f;

public class Wolf extends Character{

	public Wolf(int h, int w, int hp, int lv, int sp) {
		//For level, should probably input a random between characterLevel-2 and characterLevel+2 in the logic. Same goes for speed
		super("res2/wolf.png", 30, 62, hp, lv, "Wolf", 1+(2*lv), sp, 1+lv, 1+lv);
	}

	@Override
	public void act(int action, Character[] targetArr)
	{
		switch(action)
		{
		case -1:
			stunned = false;
			break;
		case 1:
			this.attack(targetArr);
			break;
		}
	}
	
	private void attack(Character[] party)
	{
		int crit = rand.nextInt(10) + 1;
		int dmg = rand.nextInt(5) + 2;
		int target;
		do{
		target = rand.nextInt(3) + 0;
		}while(party[target].stealth);
		
		if(crit == 1)
			dmg += (dmg*.1);
		
		party[target].damage(dmg);
	}
	
	@Override
	public void stepUp(Matrix3x3f viewport)
	{
		//The last two values will change depending on what we scale the wolf by
		transform(new Vector2f(-2.0f, 0.0f), 0.0f, viewport, 1.0f, 1.0f);
	}
	
	@Override
	public void stepBack(Matrix3x3f viewport)
	{
		//The last two values will change depending on what we scale the wolf by
		transform(new Vector2f(2.0f, 0.0f), 0.0f, viewport, 1.0f, 1.0f);
	}
}


