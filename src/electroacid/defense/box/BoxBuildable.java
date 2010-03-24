package electroacid.defense.box;

import electroacid.defense.Tower;

public class BoxBuildable extends Box {

	private Tower tower; 

	public BoxBuildable(int _x, int _y, int _width, int _height) {
		super(_x,_y,_width,_height);
	}
	
	public BoxBuildable(int _x, int _y, int _width, int _height,Tower _tower) {
		this(_x,_y,_width,_height);
		this.tower=_tower;
		this.sprite.setLayout(this.tower.getLayout()); 
	}
	
	public Tower getTower(){
		return this.tower;
	}
	
	public void changeTower(Tower _tower){
		this.tower = _tower;
		this.sprite.setLayout(this.tower.getLayout());
	}
	
	public void removeTower() {
		if (this.tower!=null) {
			this.tower = null;
			this.sprite.setLayout(null);
		}
	}
	
}
