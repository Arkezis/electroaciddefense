package electroacid.defense.wave;

import java.util.LinkedList;
import com.android.angle.AngleObject;
import electroacid.defense.Creature;
import electroacid.defense.Game;
import electroacid.defense.box.BoxPath;
import electroacid.defense.gui.Menu;

/**
 * it's a wave of creatures
 * @author cilheo
 * @version 1.0b
 */
public class Wave extends AngleObject {

	/** creatures' list */
	private LinkedList<Creature> listCreature ;

	/** wave is start or not */
	private boolean start = false;

	/** time between creatures' start */
	private float timeBetweenCreature = 2;

	/** container of creature for print */
	private AngleObject og;

	/** first box for the creatures' path */
	private BoxPath debut;

	/** position in the list of the actual creature */
	private int actualCreature = 0;
	
	/**
	 * Constructor of wave
	 * initialise the creatures' list
	 */
	public Wave(){
		this.listCreature = new LinkedList<Creature>();
	}

	/**
	 * Add a creature to the list
	 * @param creature creature to add
	 * @return true if the add is correct
	 */
	public boolean addCreature(Creature creature,Game game){
		game.addOneCreatureInGame();
		return this.listCreature.add(creature);
	}

	/**
	 * Start the wave
	 * @param ogCreature container for creature
	 * @param boxpath first boxPath
	 */
	public void start(AngleObject ogCreature, BoxPath boxpath){
		this.debut = boxpath;
		this.og = ogCreature;
		this.start = true;
	}

	/**
	 * action during a step
	 */
	public void step(float secondsElapsed){
		if (this.start){
			this.timeBetweenCreature-=secondsElapsed;
			if (this.timeBetweenCreature<=0){
				this.timeBetweenCreature=2;
				if (this.actualCreature<this.listCreature.size()){
					this.listCreature.get(this.actualCreature).start(
							this.og,
							this.debut);
					this.actualCreature++;
				}
			}
		}
		super.step(secondsElapsed);
	}
	
}
