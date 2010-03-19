package electroacid.defense;

public class Game {

	private int speedMultiplicator =1;
	private int difficulty;
	private int level;
	private int money;
	private int lives;
	
	/* GETTER */
	public int getSpeedMultiplicator(){
		return this.speedMultiplicator;
	}
	public int getDifficulty(){
		return this.difficulty;
	}
	public int getLevel(){
		return this.level;
	}
	
	/* SETTER */
	public int setSpeedMultiplicator(int nb){
		this.speedMultiplicator = nb;
		return this.speedMultiplicator;
	}
	public int setDifficulty(int nb){
		this.difficulty = nb;
		return this.difficulty;
	}
	public int setLevel(int nb){
		this.level = nb;
		return this.level;
	}
}
