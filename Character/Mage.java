package Character;

public class Mage extends PlayerCharacter{

	public Mage(String fileName, int h, int w, int hp, int xp) {
		super(/*Mage's fileName*/fileName, h, w, 20, "Mage name", 1, 15, 1, 2);
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
}


