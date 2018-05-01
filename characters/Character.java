package javagames.characters;

import javagames.util.*;
import java.util.Random;
import javagames.boundingobject.*;

public class Character extends Sprite{

	protected int hp, hpmax;
	protected int lv;
	protected String name;
	protected boolean alive, stunned;
	public boolean stealth;
	protected int str, sp, con, intel;
	protected Random rand;
	protected BoundingObject bound;
	protected int act;
	protected Character tgt;
	public boolean isMob = true;
	
	public Character(String fileName, int h, int w, int lv, String name, int str, int con, int intel) {
		super(fileName, h, w);
		
		hp = hpmax = 0;
		this.lv = lv;
		this.name = name;
		alive = true;
		stunned = false;
		stealth = false;
		this.str = str;
		sp = 0;
		this.con = con;
		this.intel = intel;
		
		act = -1;
		tgt = null;
		
		rand = new Random();
	}
	
	//Everybody should use this one
	public void act(int action, Character target)
	{
	}
	
	//This is used for the two group attacks, this one might need to be changed
	public void act(Character[] targetArr)
	{
	}
	
	//This is only here cause I'm pretty sure that the array is only a character array
	public void generateAction(Character[] players)
	{
		if(stunned)
			act = -1;
		else
			act = 1;
		int target;
		do{
		target = rand.nextInt(3) + 0;
		}while(!players[target].isAlive());
		tgt = players[target];
	}
	
	//Same as ^^
	public void generateAction(Character[] players, Character[] enemies)
	{
	}

	public boolean damage(int dmg)
	{
		//flashOn();
		
		if(hp - dmg <= 0)
		{
			hp = 0;
			alive = false;
		}
		else
			hp -= dmg;
		
		System.out.println(name + ": " + getHp() + '/' + getHpMax());
		
		return alive;
	}
	
	public void heal(int heal)
	{
		if(hp + heal >= hpmax)
			hp = hpmax;
		else
			hp += heal;
		
		System.out.println(name + " Healed: " + getHp() + '/' + getHpMax());
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public void flashOn()
	{
		setAlpha(0);
	}
	
	//Now this one might change depending on whether or not we want their alpha to actually be 100 or if this changes for different sprites
	public void flashOff()
	{
		setAlpha(1.0f);
	}
	
	public void stepUp(Matrix3x3f viewport)
	{	
	}
	
	public void stepBack(Matrix3x3f viewport)
	{	
	}
	
	public void setAct(int act)
	{
		this.act = act;
	}
	
	public int getAct()
	{
		return act;
	}
	
	public void setTgt(Character target)
	{
		tgt = target;
	}
	
	public Character getTgt()
	{
		return tgt;
	}
	
	public void setSp(int speed)
	{
		sp = speed;
	}
	
	public int getSp(){
		return sp;
	}
	
	public void setHp(int health)
	{
		hp = hpmax = health;
	}
	
	public int getHp()
	{
		return hp;
	}
	
	public int getHpMax()
	{
		return hpmax;
	}
	
	public void reset()
	{
		act = -1;
		tgt = null;
	}
}


