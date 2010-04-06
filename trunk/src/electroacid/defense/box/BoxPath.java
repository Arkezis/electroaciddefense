package electroacid.defense.box;

import java.util.LinkedList;

import electroacid.defense.Creature;

public class BoxPath extends Box {

	private LinkedList<Creature> listCreature;
	
	private BoxPath nextPath;
	
	
	/**
	 * @return the nextPath
	 */
	public BoxPath getNextPath() {
		return nextPath;
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
	
	public void addCreature(Creature creature){
		this.listCreature.add(creature);
	}
	
	
}
