package electroacid.defense.box;

import com.stickycoding.Rokon.Sprite;
import com.stickycoding.Rokon.Texture;
import com.stickycoding.Rokon.TextureAtlas;
import com.stickycoding.Rokon.TextureManager;

import electroacid.defense.Tower;

public class BoxBuildable extends Box {


		//private SubstanceBuildable sub; The substance of the box
		private Tower tower; 

	public BoxBuildable(int _x, int _y, int _width, int _height) {
		this.sprite = new Sprite(_x,_y,_width,_height);
		this.x = _x;
		this.y = _y;
		this.width = _width;
		this.height = _height;
		this.sprite.setVisible(true);
	}
	
	public BoxBuildable(int _x, int _y, int _width, int _height,Tower _tower) {
		this(_x,_y,_width,_height);
		this.tower=_tower;
		this.sprite.setTexture(this.tower.getTexture());
		
	}
	
	public Tower getTower(){
		return this.tower;
	}
	
	public void changeTower(Tower _tower){
		this.tower = _tower;
		this.sprite.setTexture(this.tower.getTexture());
	}
	
	public void removeTower() {
		if (this.tower!=null) {
			this.tower = null;
			this.sprite.setTexture(null);
		}
	}
	
}
