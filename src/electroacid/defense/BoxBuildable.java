package electroacid.defense;

import com.stickycoding.Rokon.Texture;
import com.stickycoding.Rokon.TextureAtlas;
import com.stickycoding.Rokon.TextureManager;

public class BoxBuildable extends Box {


		//private SubstanceBuildable sub; The substance of the box
		private Tower tower; 
	
	public BoxBuildable(Texture t){
		this.createBoxBuildable(t);
		tower=null;
	}
	public BoxBuildable(Texture t,Tower tow){
		this.createBoxBuildable(t);
		tower=tow;
	}

	public Tower getTower(){
		return tower;
	}
	
	public void setTower(Tower t){
		tower = t;
	}
	
}
