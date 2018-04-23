package Character;
public class Goblin extends Character{
	public Goblin(String fileName, int h, int w, int hp, int lv, String name, int str, int sp, int con, int intel) {
		//For level, should probably input a random between characterLevel-2 and characterLevel+2 in the logic. Same goes for speed
		super(fileName, h, w, hp, lv, "Goblin", 1+lv, sp, 1+lv, 1+(2*lv));
	}
	
	public int attack(Character[] party)
	{
		int dmg = rand.nextInt(2) + 1;
		int target = rand.nextInt(3) + 0;
		
		party[target].damage(dmg);
		
		return dmg;
	}
	
	public int heal(Character[] enemies, int target)
	{
		int heal = rand.nextInt(5) + 2;
		heal += intel;
		
		enemies[target].heal(heal);
		
		return heal;
	}
}


