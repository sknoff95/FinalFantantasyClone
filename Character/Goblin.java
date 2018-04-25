package Character;
public class Goblin extends Character{
	public Goblin(String fileName, int h, int w, int hp, int lv, String name, int str, int sp, int con, int intel) {
		//For level, should probably input a random between characterLevel-2 and characterLevel+2 in the logic. Same goes for speed
		super(fileName, h, w, hp, lv, "Goblin", 1+lv, sp, 1+lv, 1+(2*lv));
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
			this.attack(targetArr);
			break;
		case 2:
			this.heal(targetArr, target);
			break;
		}
	}
	
	private void attack(Character[] party)
	{
		int dmg = rand.nextInt(2) + 1;
		int target = rand.nextInt(3) + 0;
		
		party[target].damage(dmg);
	}
	
	private void heal(Character[] enemies, int target)
	{
		int heal = rand.nextInt(5) + 2;
		heal += intel;
		
		enemies[target].heal(heal);
	}
}


