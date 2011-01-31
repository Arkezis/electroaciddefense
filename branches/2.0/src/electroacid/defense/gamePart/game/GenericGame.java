package electroacid.defense.gamePart.game;

import java.util.Vector;


import org.w3c.dom.Document;

import electroacid.defense.gamePart.observ.ObservableGame;
import electroacid.defense.gamePart.observ.ObservateurMenu;
import electroacid.defense.utils.XmlUtil;

import android.content.Context;

/**
 * Read a xml file and create game's options
 * @author cilheo
 * @version 1.0b
 */
public class GenericGame implements ObservableGame{

	public int speedMultiplicator;
	public int difficulty;
	public int money;
	public int lives;
	public int timeBetweenEachWave;
	public float timeBetweenEachTowerTurn;
	public int levelTowerMax;

	public boolean gameStarted = false;
	public int actualWave = 0;
	public int nbCreatureInGame = 0;
	public int nbMaxWave = 0;
	public boolean gameEnd = false;
	public float menuRefreshTime = 1;
	public boolean pause = false;
	public int score = 0;
	public int maxNbTower = 25;
	public int maxLevelTower = 7;
	public Vector<ObservateurMenu> listObservateur = new Vector<ObservateurMenu>();
	static GenericGame instance = null;

	
	private GenericGame() {}

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

	public static void destroyInstance(){
		GenericGame.instance=null;
	}

	/**
	 * @param actualWave the actualWave to set
	 */
	public void setActualWave(int _actualWave) {
		this.actualWave = _actualWave;
		this.updateObservateurWave();
	}

	public void addMoney(int _money){
		this.money+=_money;
		this.updateObservateurMoney();
	}

	public void addScore(int _score){
		this.score+=_score;
		this.updateObservateurScore();
	}

	public void removeLives(int nbLive){
		this.lives-=nbLive;
		this.updateObservateurLive();
		if (this.lives<=0) this.gameStarted=false;
	}
	/**
	 * @param speedMultiplicator the speedMultiplicator to set
	 */
	public int nextSpeedMultiplicator() {
		int speed =(this.speedMultiplicator+1)%4;
		this.speedMultiplicator=speed!=0?speed:1;
		return this.speedMultiplicator;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(int _money) {
		this.money = _money;
		this.updateObservateurMoney();
	}
	/**
	 * @param lives the lives to set
	 */
	public void setLives(int _lives) {
		this.lives = _lives;
		this.updateObservateurLive();
	}

	/** remove one creature at the nbCreatureInGame parameter */
	public void removeOneCreatureInGame(){
		this.nbCreatureInGame--;
		this.gameEnd = !this.gameStarted || (this.nbCreatureInGame==0 && this.actualWave==this.nbMaxWave);

	}

	@Override
	public void addObservateur(ObservateurMenu obs) {
		this.listObservateur.add(obs);
	}

	@Override
	public void delAllObservateur() {
		this.listObservateur = new Vector<ObservateurMenu>(); 
	}

	@Override
	public void delObservateur(ObservateurMenu obs) {
		this.listObservateur.remove(obs);
	}

	@Override
	public void updateObservateurLive() {
		for (ObservateurMenu obs: this.listObservateur){obs.refreshLives();}
	}

	@Override
	public void updateObservateurMoney() {
		for (ObservateurMenu obs: this.listObservateur){obs.refreshMoney();}
	}

	@Override
	public void updateObservateurWave() {
		for (ObservateurMenu obs: this.listObservateur){obs.refreshWaves();}
	}

	@Override
	public void updateObservateurScore() {
		for (ObservateurMenu obs: this.listObservateur){obs.refreshScore();}		
	}

	/**
	 * @param pause the pause to set
	 */
	public void changePause() {
		this.pause = !this.pause;
	}
}