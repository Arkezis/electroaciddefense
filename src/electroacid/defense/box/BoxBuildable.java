package electroacid.defense.box;

import electroacid.defense.Game;
import electroacid.defense.Tower;
import electroacid.defense.map.GenericMap;

/**
 * It's a box which can host a tower
 * @author cilheo
 * @version 1.0b
 */
public class BoxBuildable extends Box {

	/** A tower host by the box */
	private Tower tower; 

	/**
	 * Constructor of the boxBuildable without a tower
	 * @param _x the x coordinate on the map
	 * @param _y the y coordinate on the map
	 * @param _width widht of the box
	 * @param _height height of the box
	 */
	public BoxBuildable(int _x, int _y, int _width, int _height) {
		super(_x,_y,_width,_height);
	}
	
	/**
	 * Constructor of the boxBuildable with a tower
	 * @param _x the x coordinate on the map
	 * @param _y the y coordinate on the map
	 * @param _width widht of the box
	 * @param _height height of the box
	 * @param _tower tower host by the box
	 */
	public BoxBuildable(int _x, int _y, int _width, int _height,Tower _tower) {
		this(_x,_y,_width,_height);
		this.tower=_tower;
	}
	
	/**
	 * Add a tower and change the actual tower host by the box
	 * @param _tower the tower to add or change
	 * @param game parametre of the game
	 * @return false if tower is null or tower is too much expensive
	 */
	public boolean changeTower(Tower _tower,Game game, int x, int y, GenericMap matrice){
		this.tower = _tower;
		if (this.tower != null) {
			if(game.getMoney() > this.tower.getCost()){
				this.tower.changePosition(this.y+this.width/2,this.x+this.height/2);
				game.addMoney(-this.tower.getCost());
				this.getTower().changePosition(y, x);
				this.getTower().setListDetection(x, y, matrice);
				return true;
			}
		}
		return false;
	}
	
	/** Remove the tower of the box */
	public void removeTower() {this.tower = null;}

	/** @return the tower */
	public Tower getTower() {return tower;}
}
