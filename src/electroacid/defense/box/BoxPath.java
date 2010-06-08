package electroacid.defense.box;

import java.util.ArrayList;
import java.util.LinkedList;

import utils.Observable;
import utils.Observateur;

import com.android.angle.AngleObject;
import electroacid.defense.Creature;
import electroacid.defense.Game;
import electroacid.defense.Tower;
import electroacid.defense.enums.Direction;

/**
 * It's a box which can host a creature
 * @author cilheo
 * @version 1.0b
 */
public class BoxPath extends Box implements Observable{

	/** Creatures' list who are actually on the box */
	private LinkedList<Creature> listCreature;

	/** Direction that the creature must take */
	private Direction direction;

	/** the next path after this */
	private BoxPath nextPath;

	/** Observator's list */
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();

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
		this.updateObservateurAdd(creature);
	}

	/**
	 * Action during one step
	 * @param game game's parameter
	 * @param container creature's container
	 */
	public void nextStep(Game game,AngleObject container){
		if (this.nextPath== null) {
			for (int i=0;i<this.listCreature.size();i++) {
				this.updateObservateurRemove(this.listCreature.get(i));
				this.listCreature.get(i).destroy(game, container,false);
				this.listCreature.remove(i--);
			}
		}
		for (int i=0;i<this.listCreature.size();i++){
			Creature creature = this.listCreature.get(i);
			if(creature.getLife()<=0){
				this.updateObservateurRemove(creature);
				creature.destroy(game ,container, true);
				this.listCreature.remove(i--);
			}else{
				float nextY = creature.getSprite().mPosition.mY;
				float nextX = creature.getSprite().mPosition.mX;

				if (!creatureInBox(nextX, nextY)){
					this.updateObservateurRemoveAndAdd(creature);
					this.nextPath.addCreature(creature);
					this.listCreature.remove(i--);
				}else {
					switch(this.direction) {
					case Up   :nextY -= creature.getSpeed();break;
					case Down :nextY += creature.getSpeed();break;
					case Left :nextX -= creature.getSpeed();break;
					case Right:nextX += creature.getSpeed();break;
					}
					creature.getSprite().mPosition.set(nextX, nextY);
				}
			}
		}
		if (this.nextPath!=null) this.nextPath.nextStep(game,container);
	}

	private boolean creatureInBox(float x,float y){
		boolean test = false;
		switch(this.direction) {
			case Up   :
				test = y>this.y-this.height/2.0 && y>0;
				break;
			case Down :
				test = this.nextPath==null ? y<this.y+this.height : y<this.y+1.5*this.height;
				break;
			case Left :
				test = x>this.x-this.width/2.0 && x>0;
				break;
			case Right:
				test = this.nextPath==null ? x<this.x+this.width : x<this.x+1.5*this.width;
				break;
		}
		return test;
	}
	
	/** @return the listCreature */
	public LinkedList<Creature> getListCreature() {return listCreature;}

	/** @param listCreature the listCreature to set */
	public void setListCreature(LinkedList<Creature> listCreature) {this.listCreature = listCreature;}

	/** @return the direction */
	public Direction getDirection() {return direction;}

	/** @param direction the direction to set */
	public void setDirection(Direction direction) {this.direction = direction;}

	/** @return the nextPath */
	public BoxPath getNextPath() {return nextPath;}

	/** @param nextPath the nextPath to set */
	public void setNextPath(BoxPath nextPath) {this.nextPath = nextPath;}

	@Override
	public void addObservateur(Observateur obs) {
		this.listObservateur.add(obs);
		for(Creature c :this.listCreature) obs.add(c);
	}

	@Override
	public void delAllObservateur() {
		this.listObservateur = new ArrayList<Observateur>();
	}

	@Override
	public void delObservateur(Observateur obs) {
			this.listObservateur.remove(obs);
	}

	@Override
	public void updateObservateurAdd(Object c) {
			for (Observateur obs : this.listObservateur) {
					obs.add(c);
			}
	}

	@Override
	public void updateObservateurRemoveAndAdd(Object c){
		if (this.nextPath!=null){
			for (Observateur obs : this.listObservateur) {
				if (!this.nextPath.listObservateur.contains(obs)){
					obs.remove(c);
				}
			}
		}else 
			this.updateObservateurRemove(c);
	}
	
	@Override
	public void updateObservateurRemove(Object c) {
		for (Observateur obs : this.listObservateur) obs.remove(c);
	}
	
}
