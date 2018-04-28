package Character;

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
		int target = rand.nextInt(3) + 0;
		
		if(stun == 1)
			party[target].stunned = true;
		
		party[target].damage(dmg);
	}
}


