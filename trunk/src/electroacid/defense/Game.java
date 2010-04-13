package electroacid.defense;

public class Game {

	private int speedMultiplicator =1;
	private int difficulty;
	private int level;
	private int money=500;
	private int lives;
	private float timeBetweenEachWave=5;

	private float timeBetweenEachTowerTurn=(float)0.5;
	private int actualWave = 0;
	/**
	 * @return the actualWave
	 */
	public int getActualWave() {
		return actualWave;
	}

	/**
	 * @param actualWave the actualWave to set
	 */
	public void setActualWave(int actualWave) {
		this.actualWave = actualWave;
	}
	private float menuRefreshTime=(float) 1.5;
	
	/**
	 * @return the menuRefreshTime
	 */
	public float getMenuRefreshTime() {
		return menuRefreshTime;
	}

	/**
	 * @param menuRefreshTime the menuRefreshTime to set
	 */
	public void setMenuRefreshTime(float menuRefreshTime) {
		this.menuRefreshTime = menuRefreshTime;
	}

	/**
	 * @return the timeBetweenEachWave
	 */
	public float getTimeBetweenEachWave() {
		return timeBetweenEachWave;
	}

	/**
	 * @param timeBetweenEachWave the timeBetweenEachWave to set
	 */
	public void setTimeBetweenEachWave(float timeBetweenEachWave) {
		this.timeBetweenEachWave = timeBetweenEachWave;
	}

	public Game(){
		this.difficulty = 0;
	}
	
	/**
	 * @return the speedMultiplicator
	 */
	public int getSpeedMultiplicator() {
		return speedMultiplicator;
	}
	/**
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}
	
	public void addMoney(int _money){
		this.money+=_money;
	}
	
	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}
	
	public void removeLives(int nbLive){
		this.lives-=nbLive;
	}
	/**
	 * @param speedMultiplicator the speedMultiplicator to set
	 */
	public void setSpeedMultiplicator(int speedMultiplicator) {
		this.speedMultiplicator = speedMultiplicator;
	}
	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * @param lives the lives to set
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * @return the timeBetweenEachTowerTurn
	 */
	public float getTimeBetweenEachTowerTurn() {
		return timeBetweenEachTowerTurn;
	}

	/**
	 * @param timeBetweenEachTowerTurn the timeBetweenEachTowerTurn to set
	 */
	public void setTimeBetweenEachTowerTurn(float timeBetweenEachTowerTurn) {
		this.timeBetweenEachTowerTurn = timeBetweenEachTowerTurn;
	}
	

}
