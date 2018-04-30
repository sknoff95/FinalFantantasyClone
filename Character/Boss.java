package Character;

public class Boss extends Character{
	
	public Boss()
	{
		super("src/Character/death_knight.png", 60, 26, 50, "Jonathan Von Fragglerock", 30, 30, 30);
		setSp(45);
		setHp(200);
		grabFrame(62, 196);
	}
	
	@Override
	public void generateAction(Character[] players)
	{
		if(stunned)
			act = -1;
		else
			act = rand.nextInt(3) + 1;
		
		int target = rand.nextInt(3) + 0;
		tgt = players[target];
	}
	
	public void act(int action, Character target)
	{
		switch(action)
		{
		case -1:
			stunned = false;
			break;
		case 1: 
			attack(target);
			break;
		case 3: 
			this.drain(target);
			break;
		}
	}
	
	@Override
	public void act(Character[] players)
	{
		groupAttack(players);
	}
	
	private void attack(Character target)
	{
		int dmg = rand.nextInt(50) + 35;
		
		if(!target.stealth)
			target.damage(dmg);
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
	
	private void drain(Character target)
	{
		int dmg = rand.nextInt(30) + 20;
		
		if(!target.stealth)
		{
			target.damage(dmg);
			heal(dmg);
		}
	}

}


