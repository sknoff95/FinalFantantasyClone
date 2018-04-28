package Character;

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
		int target = rand.nextInt(3) + 0;
		
		if(crit == 1)
			dmg += (dmg*.1);
		
		party[target].damage(dmg);
	}
}


