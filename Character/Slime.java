package Character;

public class Slime extends Character{

	public Slime(String fileName, int h, int w, int hp, int lv, String name, int str, int sp, int con, int intel) {
		//For level, should probably input a random between characterLevel-3 and characterLevel+3 in the logic. Same goes for speed
		super(fileName, h, w, hp, lv, "Slime", 1+lv, sp, 1+(2*lv), 1+lv);
	}

	public int attack(Character[] party, int target)
	{
		int stun = rand.nextInt(10) + 1;
		int dmg = rand.nextInt(3) + 1;
		
		//if(stun == 1)
			//party[target].stun();  <-- Once we've figured out the timing/stun mechanic, this will be filled out
		
		party[target].damage(dmg);
		
		return dmg;
	}
}


