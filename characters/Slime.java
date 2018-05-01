package javagames.characters;

import javagames.util.*;

public class Slime extends Character{

	public Slime(int lv) {
		//For level, should probably input a random between characterLevel-2 and characterLevel+2 in the logic. Same goes for speed
		super("Character/slime.png", 22, 28, lv, "Slime", 1+lv, 1+(2*lv), 1+lv);
		setSp(rand.nextInt(25) + 15);
		setHp((rand.nextInt(40) + 30) + con);
		grabFrame(2, 10);
		setAlpha(1.0f);
	}
	
	@Override
	public void act(int action, Character target)
	{
		if (stunned || !isAlive()){
			action = -1;
		}
		switch(action)
		{
		case -1:
			if(!isAlive())
				System.out.println("Slime dead");
			else
				System.out.println("Slime Stunned");
			stunned = false;
			break;
		case 1:
			this.attack(target);
			break;
		}
	}
	
	private void attack(Character target)
	{
		int stun = rand.nextInt(10) + 1;
		int dmg = rand.nextInt(3) + 1;
		if(!target.stealth)
		{
			if(stun == 1)
				target.stunned = true;
			
			target.damage(dmg);
		}
		else {
			System.out.println("Missed");
		}
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


