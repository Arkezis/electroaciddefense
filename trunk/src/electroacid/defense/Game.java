package electroacid.defense;

public class Game {

	private int speedMultiplicator =1;
	private int difficulty;
	@Deprecated private int level; 
	private int money=275;
	private int lives=10;
	private float timeBetweenEachWave=5;
	boolean gameStarted;
	private float timeBetweenEachTowerTurn=(float)0.5;
	private int actualWave = 0;
	private int nbCreatureInGame=0;
	private int nbMaxWave=0;
	private boolean gameEnd;
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
		if (this.lives<=0) this.gameStarted=false;
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

	/**
	 * @return the gameStarted
	 */
	public boolean isGameStarted() {
		return gameStarted;
	}

	/**
	 * @param gameStarted the gameStarted to set
	 */
	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	/**
	 * @return the nbCreatureInGame
	 */
	public int getNbCreatureInGame() {
		return nbCreatureInGame;
	}

	/**
	 * @param nbCreatureInGame the nbCreatureInGame to set
	 */
	public void setNbCreatureInGame(int nbCreatureInGame) {
		this.nbCreatureInGame = nbCreatureInGame;
	}
	
	/** add one creature at the nbCreatureInGame parameter */
	public void addOneCreatureInGame(){
		this.nbCreatureInGame++;
	}
	/** remove one creature at the nbCreatureInGame parameter */
	public void removeOneCreatureInGame(){
		this.nbCreatureInGame--;
		this.gameEnd = !this.gameStarted || (this.nbCreatureInGame==0 && this.actualWave==this.getNbMaxWave());
		
	}
	
	public boolean isGameEnd(){
		return this.gameEnd;
	}

	/**
	 * @return the nbMaxWave
	 */
	public int getNbMaxWave() {
		return nbMaxWave;
	}

	/**
	 * @param nbMaxWave the nbMaxWave to set
	 */
	public void setNbMaxWave(int nbMaxWave) {
		this.nbMaxWave = nbMaxWave;
	}
	

}
