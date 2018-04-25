package Character;

public class Knight extends PlayerCharacter{

	public boolean defenseUp;
	
	public Knight(String fileName, int h, int w, int hp, String name) {
		super(/*Knight's fileName*/fileName, h, w, 45, "Knight name", 1, 10, 2, 1);
	}

	@Override
	public int lvUp()
	{
		super.lvUp();
		str++; 
		con += 2;
		intel++;
		//Tentative hp upgrade equation
		hp = hp + con*2;
		
		defenseUp = false;
		
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
			this.groupAttack(targetArr);
			break;
		case 3:
			this.buff();
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
	
	public void die()
	{
		//I actually have no idea what these numbers will be 
		this.grabFrame(30, 45);
	}

	private void attack(Character[] enemies, int target)
	{
		int dmg = (rand.nextInt(3) + 1) + str;
		
		enemies[target].damage(dmg);
	}
	
	private void groupAttack(Character[] enemies)
	{
		int dmg = 2 + str;
		
		for(int i = 0; i < enemies.length; i++)
			enemies[i].damage(dmg);
	}
	
	private void buff()
	{
		defenseUp = true;
	}
}


