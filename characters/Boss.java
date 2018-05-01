package javagames.characters;

public class Boss extends Character{
	
	public Boss()
	{
		super("Character/death_knight.png", 60, 26, 50, "Jonathan Von Fragglerock", 30, 30, 30);
		setSp(45);
		setHp(200);
		grabFrame(62, 196);
		setAlhpa(1.0f);
	}
	
	@Override
	public void generateAction(Character[] players)
	{
		int target;
		if(stunned)
		{
			//50% chance to avoid being stunned
			int skipStun = rand.nextInt(10) + 1;
			if(skipStun < 6)
				act = -1;
		}
		else
			act = rand.nextInt(3) + 1;
		
		do{
		target = rand.nextInt(3) + 0;
		}while(!players[target].isAlive())
			
		tgt = players[target];
	}
	
	public void act(int action, Character target)
	{
		if (stunned){
			//Same as about, chance to avoid stun
			int skipStun = rand.nextInt(10) + 1;
			if(skipStun < 6)
				action = -1;
		}
		switch(action)
		{
		case -1:
			System.out.println("Jonathan Stunned");
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


