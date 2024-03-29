package electroacid.defense.gamePart;

import java.util.ArrayList;


import com.android.angle.AngleObject;
import com.android.angle.AngleRotatingSprite;
import com.android.angle.AngleSpriteLayout;

import electroacid.defense.gamePart.box.BoxPath;
import electroacid.defense.gamePart.enums.Element;
import electroacid.defense.gamePart.game.GenericGame;
import electroacid.defense.gamePart.observ.ObservableCreature;
import electroacid.defense.gamePart.observ.ObservateurMenu;

/**
 * It's a simple creature
 * @author cilheo
 * @version 1.0b
 */
public  class Creature implements Cloneable,ObservableCreature{

	private ArrayList<ObservateurMenu> listObservateur = new ArrayList<ObservateurMenu>();

	/**
	 * @return the rewardValue
	 */
	public int getRewardValue() {
		return rewardValue;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * @param rewardValue the rewardValue to set
	 */
	public void setRewardValue(int rewardValue) {
		this.rewardValue = rewardValue;
	}


	/** Element of the creature */
	private Element element; 

	/** actual Life of the creature */
	private int life;

	/** Life of the creature */
	private int maxLife;

	/** Speed of the creature */
	private float speed;

	/** Not implemented yet */
	private int fireRate;

	/** Gain when the creature die */
	private int rewardValue;

	/** Gain score when the creature die */
	private int scoreValue;

	/** Not implemented yet */
	private boolean fly;

	/** Sprite of the creature */
	private AngleRotatingSprite sprite;

	/**
	 * Constructor of a creature
	 * @param _element Element of the creature
	 * @param _life Life of the creature
	 * @param speed2 Speed of the creature
	 * @param _fireRate Not implemented yet
	 * @param _rewardValue Gain when the creature die
	 * @param _fly Not implemented yet
	 * @param layout Layout of the creature
	 */
	public Creature(Element _element,int _life,float speed2, int _fireRate, int _rewardValue,
			int scoreValue,boolean _fly,AngleSpriteLayout layout){
		this.element = _element;
		this.life = _life;
		this.speed = speed2;
		this.fireRate = _fireRate;
		this.rewardValue = _rewardValue;
		this.fly = _fly;
		this.sprite= new AngleRotatingSprite(layout);
		this.maxLife=_life;
		
	}

	public void loseLife(int nbDamage){
		this.life -= nbDamage; 
		this.updateObservateur();
	}

	/** clone all value of the creature excepted the sprite, create a new */
	public Object clone() {
		Creature creature = null;
		try { creature = (Creature) super.clone();
		} catch(CloneNotSupportedException cnse){cnse.printStackTrace(System.err);}
		creature.sprite = new AngleRotatingSprite(this.sprite.roLayout);
		return creature;
	}

	/**
	 * Destroy a creature
	 * add money if necessary
	 * remove live if necessary
	 * remove the sprite
	 * @param game game's parameter
	 * @param og container of the creature (for the sprite)
	 * @param byTower kill by a tower or not
	 */
	public void destroy(AngleObject og,boolean byTower){
		GenericGame game = GenericGame.getInstance();
		og.removeObject(this.sprite);
		if (byTower) {
			game.addMoney(this.rewardValue);
			game.addScore(this.scoreValue);
		}
		else
			game.removeLives(1);
		game.removeOneCreatureInGame();
	}

	public void start(AngleObject og, BoxPath debut) {
		debut.addCreature(this);
		this.sprite.mPosition.set(
				debut.getX()+16,
				debut.getY()+16);
		og.addObject(this.sprite);
	}

	/**
	 * @return the element
	 */
	public Element getElement() {
		return element;
	}


	/**
	 * @param element the element to set
	 */
	public void setElement(Element element) {
		this.element = element;
	}


	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}


	/**
	 * @param life the life to set
	 */
	public void setLife(int life) {
		this.life = life;
		this.updateObservateur();
	}


	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}


	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}


	/**
	 * @return the fireRate
	 */
	public int getFireRate() {
		return fireRate;
	}


	/**
	 * @param fireRate the fireRate to set
	 */
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}


	/**
	 * @return the cost
	 */
	public int getCost() {
		return rewardValue;
	}


	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.rewardValue = cost;
	}


	/**
	 * @return the fly
	 */
	public boolean isFly() {
		return fly;
	}


	/**
	 * @param fly the fly to set
	 */
	public void setFly(boolean fly) {
		this.fly = fly;
	}


	/**
	 * @return the sprite
	 */
	public AngleRotatingSprite getSprite() {
		return sprite;
	}


	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(AngleRotatingSprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the maxLife
	 */
	public int getMaxLife() {
		return maxLife;
	}

	/**
	 * @param maxLife the maxLife to set
	 */
	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	/**
	 * @return the scoreValue
	 */
	public int getScoreValue() {
		return scoreValue;
	}

	/**
	 * @param scoreValue the scoreValue to set
	 */
	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
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
	public void updateObservateur() {
		for( ObservateurMenu obs : this.listObservateur)
			obs.refreshCreature();
	}
}
