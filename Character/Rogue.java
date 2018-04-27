package Character;

public class Rogue extends PlayerCharacter{
	
	public Rogue(String fileName, int h, int w, int hp, String name) {
		super(/*Thief's fileName*/fileName, h, w, 30, "Thief name", 1, 35, 1, 1);
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
	
	@Override
	public void act(int action, Character[] targetArr, int target)
	{
		switch(action)
		{
		case -1:
			stunned = false;
			break;
		case 1: 
			this.attack(targetArr, target);
			break;
		case 2:
			this.stealth();
			break;
		//Use health potion
		case 4:
			targetArr[target].heal(25);
			break;
		//Use revive scroll
		case 5:
			if(!targetArr[target].isAlive())
			{
				targetArr[target].alive = true;
				targetArr[target].heal(25);
			}
			break;
		}
	}
	
	private void attack(Character[] enemies, int target)
	{
		int dmg = (rand.nextInt(5) + 3) + str;
		int crit = rand.nextInt(10) + 1;
		
		if(crit < 3)
			dmg += (dmg*.2);
		
		if(stealth)
		{
			dmg += 3;
			stealth = false;
		}
		
		enemies[target].damage(dmg);
	}
	
	private void stealth()
	{
		stealth = true;
	}
}


