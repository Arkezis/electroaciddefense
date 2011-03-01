package electroacid.defense.gamePart.wave;

import java.util.LinkedList;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.layer.DynamicCapacityLayer;
import org.anddev.andengine.util.Path;

import electroacid.defense.gamePart.Creature;
import electroacid.defense.gamePart.game.GenericGame;
import electroacid.defense.gamePart.tile.TilePath;

/**
 * it's a wave of creatures
 * @author cilheo
 * @version 1.0b
 */
public class Wave extends DynamicCapacityLayer {

	/** creatures' list */
	private LinkedList<Creature> listCreature ;

	/** wave is start or not */
	private boolean start = false;

	/** time between creatures' start */
	private float timeBetweenCreature = 2;

	/** first box for the creatures' path */
	private Path path;

	/** position in the list of the actual creature */
	private int actualCreature = 0;

	/**
	 * Constructor of wave
	 * initialise the creatures' list
	 */
	public Wave(){
		this.listCreature = new LinkedList<Creature>();
	}

	public void addEntity(IEntity entity){
		super.addEntity(entity);
		GenericGame.getInstance().nbCreatureInGame++;
	}
	
	/**
	 * Start the wave
	 * @param ogCreature container for creature
	 * @param boxpath first boxPath
	 */
	public void start(Path _path){
		this.path = _path;
		this.start = true;
	}

	/**
	 * action during a step
	 */
	public void step(float secondsElapsed){
		if (this.start){
			for (int timeMult=0;timeMult<GenericGame.getInstance().speedMultiplicator;timeMult++){
				this.timeBetweenCreature-=secondsElapsed;
				if (this.timeBetweenCreature<=0){
					
					
					
					this.timeBetweenCreature=2;
					if (this.actualCreature<this.getEntityCount()){
						((Creature) this.getEntity(this.actualCreature)).start(
								this.path);
						this.actualCreature++;
					}
				}
			}}
		//super.step(secondsElapsed);
	}

}
