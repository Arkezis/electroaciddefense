package electroacid.defense.gamePart;

import java.util.ArrayList;

import org.anddev.andengine.entity.layer.ILayer;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.modifier.BaseShapeModifier;
import org.anddev.andengine.entity.shape.modifier.IShapeModifier;
import org.anddev.andengine.entity.shape.modifier.PathModifier;
import org.anddev.andengine.entity.shape.modifier.PathModifier.IPathModifierListener;
import org.anddev.andengine.entity.shape.modifier.ease.EaseSineInOut;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.Path;

import android.util.Log;

import electroacid.defense.gamePart.enums.Element;
import electroacid.defense.gamePart.game.GenericGame;
import electroacid.defense.gamePart.observ.ObservableCreature;
import electroacid.defense.gamePart.observ.ObservateurMenu;
import electroacid.defense.gamePart.tile.TilePath;

/**
 * It's a simple creature
 * 
 * @author cilheo
 * @version 2.0
 */
public class Creature extends AnimatedSprite implements ObservableCreature {

	public ArrayList<ObservateurMenu> listObservateur = new ArrayList<ObservateurMenu>();

	/** Element of the creature */
	public Element element;

	/** actual Life of the creature */
	public int life;

	/** Life of the creature */
	public int maxLife;

	/** Speed of the creature */
	public float speed;

	/** Not implemented yet */
	public int fireRate;

	/** Gain when the creature die */
	public int rewardValue;

	/** Gain score when the creature die */
	public int scoreValue;

	/** Not implemented yet */
	public boolean fly;

	/**
	 * Constructor of a creature
	 * 
	 * @param _element
	 *            Element of the creature
	 * @param _life
	 *            Life of the creature
	 * @param speed2
	 *            Speed of the creature
	 * @param _fireRate
	 *            Not implemented yet
	 * @param _rewardValue
	 *            Gain when the creature die
	 * @param _fly
	 *            Not implemented yet
	 * @param layout
	 *            Layout of the creature
	 */
	public Creature(Element _element, int _life, float speed2, int _fireRate,
			int _rewardValue, int scoreValue, boolean _fly,
			TiledTextureRegion texture) {
		
		super(-10,-10,32,32,texture);
		
		this.element = _element;
		this.life = _life;
		this.speed = speed2;
		this.fireRate = _fireRate;
		this.rewardValue = _rewardValue;
		this.fly = _fly;
		this.maxLife = _life;

	}

	public void loseLife(int nbDamage) {
		this.life -= nbDamage;
		this.updateObservateur();
	}

	/**
	 * Destroy a creature add money if necessary remove live if necessary remove
	 * the sprite
	 * 
	 * @param game
	 *            game's parameter
	 * @param og
	 *            container of the creature (for the sprite)
	 * @param byTower
	 *            kill by a tower or not
	 */
	public void destroy(ILayer container, boolean byTower) {
		GenericGame game = GenericGame.getInstance();
		container.removeEntity(this);
		if (byTower) {
			game.addMoney(this.rewardValue);
			game.addScore(this.scoreValue);
		} else
			game.removeLives(1);
		game.removeOneCreatureInGame();

	}

	public void start(final Path path) {
		//debut.addCreature(this);
		//this.sprite.setPosition(debut.getTileX() + 16, debut.getTileY() + 16);

		
		Log.d("t","taille : "+path.getSize());
		
		
		final long pathLenght[] = new long[path.getSize()];
		for (int i = 0;i<path.getSize();i++) pathLenght[i]=200;
		
		
		
		this.addShapeModifier(new PathModifier(30, path, null, new IPathModifierListener() {
			@Override
			public void onWaypointPassed(final PathModifier pPathModifier, final IShape pShape, final int pWaypointIndex) {
				//Creature.this.animate(pathLenght, 0, path.getSize()-1, true);
			}
		}, EaseSineInOut.getInstance()));
		
		
		
		Log.d("r",this.isVisible()+"");
		
	}

	/**
	 * @param life
	 *            the life to set
	 */
	public void setLife(int _life) {
		this.life = _life;
		this.updateObservateur();
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
		for (ObservateurMenu obs : this.listObservateur)
			obs.refreshCreature();
	}
}
