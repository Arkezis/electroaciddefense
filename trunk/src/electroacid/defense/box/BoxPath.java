package electroacid.defense.box;

import java.util.LinkedList;
import com.android.angle.AngleObject;
import electroacid.defense.Creature;
import electroacid.defense.Game;
import electroacid.defense.enums.Direction;

/**
 * It's a box which can host a creature
 * @author cilheo
 * @version 1.0b
 */
public class BoxPath extends Box {

	/** Creatures' list who are actually on the box */
	private LinkedList<Creature> listCreature;

	/** Direction that the creature must take */
	private Direction direction;

	/** the next path after this */
	private BoxPath nextPath;


	/**
	 * Constructor of a boxPath
	 * @param _x the x coordinate on the map
	 * @param _y the y coordinate on the map
	 * @param _width widht of the box
	 * @param _height height of the box
	 */
	public BoxPath(int _x, int _y, int _width, int _height) {
		super(_x,_y,_width,_height);
		this.listCreature = new LinkedList<Creature>();
	}

	/**
	 * Constructor of a boxPath
	 * @param _x the x coordinate on the map
	 * @param _y the y coordinate on the map
	 * @param _width widht of the box
	 * @param _height height of the box
	 * @param direction direction that the creature must take
	 */
	public BoxPath(int _x, int _y, int _width, int _height,Direction direction) {
		this(_x,_y,_width,_height);
		this.direction = direction;
	}

	/** add a creature to the listCreature */
	public void addCreature(Creature creature){
		this.listCreature.add(creature);
	}

	/**
	 * Action during one step
	 * @param game game's parameter
	 * @param container creature's container
	 */
	public void nextStep(Game game,AngleObject container){

		if (this.nextPath== null) {
			for (int i=0;i<this.listCreature.size();i++) {
				this.listCreature.get(i).destroy(game, container,false);
				this.listCreature.remove(i--);
				game.removeLives(1);
			}
		}
		for (int i=0;i<this.listCreature.size();i++){
			Creature creature = this.listCreature.get(i);
			if(creature.getLife()<=0){
				creature.destroy(game ,container, true);
				this.listCreature.remove(i--);
			}else{
				float nextY = creature.getSprite().mPosition.mY;
				float nextX = creature.getSprite().mPosition.mX;
				if (nextY>this.x+this.height || nextY<this.x || 
						nextX>this.y+this.width || nextX<this.y) {
					this.nextPath.addCreature(creature);
					this.listCreature.remove(i--);
				}else {
					switch(this.direction) {
					case Up   :nextY--;break;
					case Down :nextY++;break;
					case Left :nextX--;break;
					case Right:nextX++;break;
					}
					creature.getSprite().mPosition.set(nextX, nextY);
				}
			}
		}
		if (this.nextPath!=null) this.nextPath.nextStep(game,container);
	}
}
