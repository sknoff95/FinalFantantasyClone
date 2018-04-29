package Character;

public class Boss extends Character{
	
	public Boss()
	{
		super("res/death_knight.png", 60, 26, 200, 50, "Jonathan Von Fragglerock", 30, 45, 30, 30);
	}
	
	public void act(int action, Character[] players, int target)
	{
		switch(action)
		{
		case -1:
			stunned = false;
			break;
		case 1: 
			this.attack(players);
			break;
		case 2:
			this.groupAttack(players);
			break;
		case 3: 
			this.drain(players);
			break;
		}
	}
	
	private void attack(Character[] players)
	{
		int dmg = rand.nextInt(50) + 35;
		int target;
		do{
			target = rand.nextInt(3) + 0;
		}while(players[target].stealth);
		
		players[target].damage(dmg);
	}
	
	private void groupAttack(Character[] players)
	{
		int dmg = rand.nextInt(35) + 20;
		
		for(int i = 0; i < 3; i++)
		{
			if(!players[i].stealth)
				players[i].damage(dmg);
		}
	}
	
	private void drain(Character[] players)
	{
		int dmg = rand.nextInt(30) + 20;
		int target;
		do{
			target = rand.nextInt(3) + 0;
		}while(players[target].stealth);
		
		players[target].damage(dmg);
		heal(dmg);
	}

}


