package Character;

public class Rogue extends PlayerCharacter{
	
	public Rogue(String fileName, int h, int w, int hp, String name) {
		super(/*Knight's fileName*/fileName, h, w, 30, "Thief name", 1, 35, 1, 1);
	}

	@Override
	public int lvUp()
	{
		super.lvUp();
		str += 2; 
		con++;
		intel++;
		//Tentative hp upgrade equation
		hp = hp + con*2;
		return lv;		
	}
	
	public int attack(Character[] enemies, int target)
	{
		int dmg = rand.nextInt(5) + 3;
		int crit = rand.nextInt(10) + 1;
		
		if(crit < 3)
			dmg += (dmg*.2);
		
		enemies[target].damage(dmg);
		
		return dmg;
	}
}


