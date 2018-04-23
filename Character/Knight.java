package Character;

public class Knight extends PlayerCharacter{

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
		return lv;		
	}
	
}


