package Character;

import SimpleFramework.Matrix3x3f;
import SimpleFramework.Vector2f;

public class Slime extends Character{

	public Slime(int h, int w, int hp, int lv,  int sp) {
		//For level, should probably input a random between characterLevel-2 and characterLevel+2 in the logic. Same goes for speed
		super("res2/slime.png", 22, 28, hp, lv, "Slime", 1+lv, sp, 1+(2*lv), 1+lv);
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
		int stun = rand.nextInt(10) + 1;
		int dmg = rand.nextInt(3) + 1;
		int target;
		do{
		target = rand.nextInt(3) + 0;
		}while(party[target].stealth);
		
		if(stun == 1)
			party[target].stunned = true;
		
		party[target].damage(dmg);
	}
	
	@Override
	public void stepUp(Matrix3x3f viewport)
	{
		//The last two values will change depending on what we scale the slime by
		transform(new Vector2f(-2.0f, 0.0f), 0.0f, viewport, 1.0f, 1.0f);
	}
	
	@Override
	public void stepBack(Matrix3x3f viewport)
	{
		//The last two values will change depending on what we scale the slime by
		transform(new Vector2f(2.0f, 0.0f), 0.0f, viewport, 1.0f, 1.0f);
	}
}


