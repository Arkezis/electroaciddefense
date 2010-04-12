package electroacid.defense.box;

import java.util.LinkedList;

import com.android.angle.AngleObject;

import android.util.Log;

import electroacid.defense.Creature;
import electroacid.defense.Game;
import electroacid.defense.Tower;
import electroacid.defense.enums.Direction;

public class BoxPath extends Box {

	private LinkedList<Creature> listCreature;
	
	private Direction direction;
	
	private BoxPath nextPath;
	
	
	/**
	 * @return the listCreature
	 */
	public LinkedList<Creature> getListCreature() {
		return listCreature;
	}

	/**
	 * @param listCreature the listCreature to set
	 */
	public void setListCreature(LinkedList<Creature> listCreature) {
		this.listCreature = listCreature;
	}

	/**
	 * @return the nextPath
	 */
	public BoxPath getNextPath() {
		return nextPath;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * @param nextPath the nextPath to set
	 */
	public void setNextPath(BoxPath nextPath) {
		this.nextPath = nextPath;
	}

	public BoxPath(int _x, int _y, int _width, int _height) {
		super(_x,_y,_width,_height);
		this.listCreature = new LinkedList<Creature>();
	}
	
	public BoxPath(int _x, int _y, int _width, int _height,Direction direction) {
		this(_x,_y,_width,_height);
		this.direction = direction;
	}
	
	public void addCreature(Creature creature){
		this.listCreature.add(creature);
	}
	
	public void nextStep(Game g,AngleObject o){

		if (this.nextPath== null) {
			for (int i=0;i<this.listCreature.size();i++) {
				this.listCreature.get(i).destroy(g, o,false);
				this.listCreature.remove(i);
				i--;
				g.removeLives(1);
			}
		}
		for (int i=0;i<this.listCreature.size();i++){
			
			Creature creature = this.listCreature.get(i);
			if(creature.getLife()<=0){
				creature.destroy(g, o, true);
				this.listCreature.remove(i--);
			}else{
				float nextY = creature.getSprite().mPosition.mY;
				float nextX = creature.getSprite().mPosition.mX;
				if (nextY>this.x+this.height || nextY<this.x || 
						nextX>this.y+this.width || nextX<this.y) {
						this.nextPath.addCreature(creature);
						this.listCreature.remove(i);
						i--;
				}else {
				
				switch(this.direction) {
				case Up:
					nextY--;
					break;
				case Down:
					nextY++;
					break;
				case Left:
					nextX--;
					break;
				case Right:
					nextX++;
					break;
				}
				
				creature.getSprite().mPosition.set(nextX, nextY);
				
				}

			}
			
		}

		if (this.nextPath!=null) this.nextPath.nextStep(g,o);
	
		
	}
}
