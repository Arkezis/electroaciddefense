package electroacid.defense.box;

import com.stickycoding.Rokon.Sprite;
import com.stickycoding.Rokon.Texture;
import com.stickycoding.Rokon.TextureAtlas;
import com.stickycoding.Rokon.TextureManager;

import electroacid.defense.Tower;

public class BoxBuildable extends Box {


		//private SubstanceBuildable sub; The substance of the box
		private Tower tower; 
	
	public BoxBuildable(Texture t){
	

		tower=null;
	}
	public BoxBuildable(Texture t,Tower tow){
		this.createBoxBuildable(t);
		tower=tow;
	}

	public BoxBuildable(int x, int y, int width, int height) {
		spr = new Sprite(x,y,width,height);
		spr.setVisible(true);
	}
	
	public BoxBuildable(int x, int y, int width, int height,Tower _tower) {
		this(x,y,width,height);
		this.tower=_tower;
		
	}
	
	public Tower getTower(){
		return tower;
	}
	
	public void setTower(Tower t){
		tower = t;
	}
	
}
