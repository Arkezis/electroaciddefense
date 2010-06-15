package electroacid.defense.game;

import java.util.ArrayList;

import observ.ObservableGame;
import observ.ObservateurMenu;

import org.w3c.dom.Document;

import utils.XmlUtil;
import android.content.Context;

/**
 * Read a xml file and create game's options
 * @author cilheo
 * @version 1.0b
 */
public class GenericGame implements ObservableGame{

	private int speedMultiplicator;
	private int difficulty;
	private int money;
	private int lives;
	private int timeBetweenEachWave;
	private float timeBetweenEachTowerTurn;
	private int levelTowerMax;
	
	private boolean gameStarted;
	private int actualWave = 0;
	private int nbCreatureInGame=0;
	private int nbMaxWave=0;
	private boolean gameEnd;
	private float menuRefreshTime=(float) 1;
	private boolean pause=false;
	private int score=0;
	
	private ArrayList<ObservateurMenu> listObservateur = new ArrayList<ObservateurMenu>();
	
	static GenericGame instance = null;
	
	/**
	 * Constructor of genericWave
	 * init all layout
	 * @param mGLSurfaceView
	 * @throws Exception 
	 */
	private GenericGame() {
	}
	
	public void build(final Context context, final int xmlResourceId) throws Exception {
		Document gameXml = XmlUtil.getDocumentFromResource(context, xmlResourceId);
	
		this.speedMultiplicator=XmlUtil.getAttributeIntFromNode(gameXml.getElementsByTagName("speedMultiplicator").item(0),"value");
		this.difficulty=XmlUtil.getAttributeIntFromNode(gameXml.getElementsByTagName("difficulty").item(0),"value");
		this.money=XmlUtil.getAttributeIntFromNode(gameXml.getElementsByTagName("money").item(0),"value");
		this.lives=XmlUtil.getAttributeIntFromNode(gameXml.getElementsByTagName("lives").item(0),"value");
		this.timeBetweenEachWave=XmlUtil.getAttributeIntFromNode(gameXml.getElementsByTagName("timeBetweenEachWave").item(0),"value");
		this.timeBetweenEachTowerTurn=XmlUtil.getAttributeFloatFromNode(gameXml.getElementsByTagName("timeBetweenEachTowerTurn").item(0),"value");
		this.levelTowerMax=XmlUtil.getAttributeIntFromNode(gameXml.getElementsByTagName("levelTowerMax").item(0),"value");
	}
	
	public static GenericGame getInstance(){
		if (GenericGame.instance!=null) return GenericGame.instance;
		return (GenericGame.instance=new GenericGame());
	}
	
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
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	public void addScore(int _score){
		this.score+=_score;
		this.updateObservateurScore();
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
			obs.refreshLives();
		}
	}

	@Override
	public void updateObservateurMoney() {
		for (ObservateurMenu obs: this.listObservateur){
			obs.refreshMoney();
		}
	}

	@Override
	public void updateObservateurWave() {
		for (ObservateurMenu obs: this.listObservateur){
			obs.refreshWaves();
		}
	}
	
	@Override
	public void updateObservateurScore() {
		for (ObservateurMenu obs: this.listObservateur){
			obs.refreshScore();
		}		
	}
	
	/**
	 * @return the pause
	 */
	public boolean isPause() {
		return pause;
	}

	/**
	 * @param pause the pause to set
	 */
	public void changePause() {
		this.pause = !this.pause;
	}

	/**
	 * @return the levelTowerMax
	 */
	public int getLevelTowerMax() {
		return levelTowerMax;
	}

	/**
	 * @param levelTowerMax the levelTowerMax to set
	 */
	public void setLevelTowerMax(int levelTowerMax) {
		this.levelTowerMax = levelTowerMax;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
}