package electroacid.defense;

import java.util.ArrayList;

import observ.ObservableGame;
import observ.ObservateurMenu;


public class Game implements ObservableGame{

	private int speedMultiplicator =1;
	private int difficulty;
	@Deprecated private int level; 
	private int money=8000;
	private int lives=20;
	private int timeBetweenEachWave=50;
	boolean gameStarted;
	private float timeBetweenEachTowerTurn=(float)0.5;
	private int actualWave = 0;
	private int nbCreatureInGame=0;
	private int nbMaxWave=0;
	private boolean gameEnd;
	
	private ArrayList<ObservateurMenu> listObservateur = new ArrayList<ObservateurMenu>();
	
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
		this.updateObservateurWave();
	}
	private float menuRefreshTime=(float) 1;
	
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
	public int getTimeBetweenEachWave() {
		return timeBetweenEachWave;
	}

	/**
	 * @param timeBetweenEachWave the timeBetweenEachWave to set
	 */
	public void setTimeBetweenEachWave(int timeBetweenEachWave) {
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
		this.updateObservateurMoney();
	}
	
	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}
	
	public void removeLives(int nbLive){
		this.lives-=nbLive;
		this.updateObservateurLive();
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
		this.updateObservateurMoney();
	}
	/**
	 * @param lives the lives to set
	 */
	public void setLives(int lives) {
		this.lives = lives;
		this.updateObservateurLive();
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

	@Override
	public void addObservateur(ObservateurMenu obs) {
		this.listObservateur.add(obs);
	}

	@Override
	public void delAllObservateur() {
		this.listObservateur = new ArrayList<ObservateurMenu>(); 
	}

	@Override
	public void delObservateur(ObservateurMenu obs) {
		this.listObservateur.remove(obs);
	}

	@Override
	public void updateObservateurLive() {
		for (ObservateurMenu obs: this.listObservateur){
			obs.refreshLives(this);
		}
	}

	@Override
	public void updateObservateurMoney() {
		for (ObservateurMenu obs: this.listObservateur){
			obs.refreshMoney(this);
		}
	}

	@Override
	public void updateObservateurWave() {
		for (ObservateurMenu obs: this.listObservateur){
			obs.refreshWaves(this);
		}
	}
	

}
