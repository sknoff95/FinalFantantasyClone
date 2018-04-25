package Character;

public class Wolf extends Character{

	public Wolf(String fileName, int h, int w, int hp, int lv, String name, int str, int sp, int con, int intel) {
		//For level, should probably input a random between characterLevel-2 and characterLevel+2 in the logic. Same goes for speed
		super(fileName, h, w, hp, lv, "Wolf", 1+(2*lv), sp, 1+lv, 1+lv);
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


