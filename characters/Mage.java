package javagames.characters;

import javagames.util.*;

public class Mage extends PlayerCharacter{

	public Mage() {
		super("Character/mage.png", 24, 16, "Tim the Enchanter", 1, 1, 2);
		setSp(15);
		setHp(20);
		grabFrame(6, 36);
		isMob = false;
		setAlpha(1.0f);
	}
	
	@Override
	public int lvUp()
	{
		super.lvUp();
		str++;
		con++;
		intel+=2;
		//Tentative hp upgrade equation
		hp = hp + con*2;
		return lv;
	}
	
	@Override
	public void act(int action, Character target)
	{
		System.out.println("Mage Alive: " + isAlive());
		if (stunned || !isAlive()){
			action = -1;
		}
		switch(action)
		{
		case -1:
			if(!isAlive())
				System.out.println("Mage dead");
			else
				System.out.println("Mage Stunned");
			stunned = false;
			break;
		case 1:
			attack(target);
			break;
		case 2:
			stun(target);
			break;
		case 3:
			if(target.isAlive())
				heal(target);
			else
				System.out.println("Target dead");
			break;
		//Use health potion
		case 4:
			heal(25);
			break;
		//Use revive scroll
		case 5:
			if(!target.isAlive())
			{
				target.alive = true;
				target.heal(25);
			}
			break;
		}
	}
	
	private void stun(Character target)
	{
		target.stunned = true;
	}
	
	private void attack(Character target)
	{
		int dmg = (rand.nextInt(3) + 1) + intel;
		
		target.damage(dmg);
	}
	
	private void heal(Character target)
	{
		int heal = (rand.nextInt(6) + 3) + intel;
		
		target.heal(heal);
	}
	
	@Override
	public void stepUp(Matrix3x3f viewport)
	{
		//Last two will change with mage scale
		transform(new Vector2f(2.0f, 0.0f), 0.0f, viewport, 2.0f, 2.0f);
	}
	
	@Override
	public void stepBack(Matrix3x3f viewport)
	{
		//Last two will change with mage scale
		transform(new Vector2f(-2.0f, 0.0f), 0.0f, viewport, 2.0f, 2.0f);
	}
}


