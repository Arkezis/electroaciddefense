package electroacid.defense.box;

import java.util.LinkedList;

import android.util.Log;

import electroacid.defense.Creature;
import electroacid.defense.Tower;
import electroacid.defense.enums.Direction;

public class BoxPath extends Box {

	private LinkedList<Tower> listCreature;
	
	private Direction direction;
	
	private BoxPath nextPath;
	
	
	/**
	 * @return the listCreature
	 */
	public LinkedList<Tower> getListCreature() {
		return listCreature;
	}

	/**
	 * @param listCreature the listCreature to set
	 */
	public void setListCreature(LinkedList<Tower> listCreature) {
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
		this.listCreature = new LinkedList<Tower>();
	}
	
	public BoxPath(int _x, int _y, int _width, int _height,Direction direction) {
		this(_x,_y,_width,_height);
		this.direction = direction;
	}
	
	public void addCreature(Tower creature){
		this.listCreature.add(creature);
	}
	
	public void nextStep(){

		if (this.nextPath== null) return;
		for (int i=0;i<this.listCreature.size();i++){
			
			Tower creature = this.listCreature.get(i);
			float nextY = creature.getSprite().mPosition.mY;
			float nextX = creature.getSprite().mPosition.mX;
			
			
			Log.d("CREATURE", nextX+"   "+nextY+"   "+x+"    "+y);
			
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

		if (this.nextPath!=null) this.nextPath.nextStep();
	
		
	}
}
