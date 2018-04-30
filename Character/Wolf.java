package Character;

import SimpleFramework.Matrix3x3f;
import SimpleFramework.Vector2f;

public class Wolf extends Character{

	public Wolf(int lv) {
		//For level, should probably input a random between characterLevel-2 and characterLevel+2 in the logic. Same goes for speed
		super("src/Character/wolf.png", 30, 62, lv, "Wolf", 1+(2*lv), 1+lv, 1+lv);
		setSp(rand.nextInt(50) + 30);
		setHp((rand.nextInt(30) + 15) + con);
		grabFrame(64, 0);
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
		}
	}
	
	private void attack(Character target)
	{
		int crit = rand.nextInt(10) + 1;
		int dmg = rand.nextInt(5) + 2;
		
		if(crit == 1)
			dmg += (dmg*.5);
		
		if(!target.stealth)
			target.damage(dmg);
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


