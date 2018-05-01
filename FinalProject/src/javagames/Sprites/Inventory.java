package javagames.Sprites;

public class Inventory {

	private int numPotions;
	private int numScrolls;
	private int gold;
	
	public Inventory(){
		this.numPotions = 0;
		this.numScrolls = 0;
		this.gold = 0;
	}
	
	//Using potions and scrolls costs one each, checks to make sure one is available to use
	public boolean usePotion(){
		if(numPotions > 0){
			numPotions -= 1;
			return true;
		}
		return false;
	}
	
	public boolean useScroll(){
		if(numScrolls > 0){
			numScrolls -= 1;
			return true;
		}
		return false;
	}
	
	//Makes sure user has enough gold to buy scroll or potion, decrements gold accordingly
	public boolean buyPotion(){
		if(gold >= 100){
			numPotions += 1;
			gold -= 100;
			return true;
		}
		return false;
	}
	
	public boolean buyScroll(){
		if(gold > 1000){
			numScrolls += 1;
			gold -= 1000;
			return true;
		}
		return false;
	}
	
	//Increase gold by value of x
	public void gainGold(int x){
		gold += x;
	}
	
	public int getNumPotions(){
		return numPotions;
	}
	
	public int getNumScrolls(){
		return numScrolls;
	}
	
	public int getGold(){
		return gold;
	}
}
