package electroacid.defense.gui;



import com.android.angle.AngleFont;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.Game;

public abstract class Menu {

	private AngleString t_infosPlayer;
	
	public Menu(Game game, AngleFont font, AngleSurfaceView mGLSurfaceView){
		this.t_infosPlayer.set("Game");
		//this.t_infosPlayer = new Text("## Game ## \n Speed : "+game.getSpeedMultiplicator(),font,240,416,32);
		//this.t_infosPlayer = new Text("Game Speed : ",font,240,416,32);
		t_infosPlayer.mAlignment = AngleString.aCenter;
		t_infosPlayer.mPosition.set(240, 416); 
		mGLSurfaceView.addObject(this.t_infosPlayer);
	}


	/**
	 * @return the t_infosPlayer
	 */
	public AngleString getT_infosPlayer() {
		return t_infosPlayer;
	}



	/**
	 * @param tInfosPlayer the t_infosPlayer to set
	 */
	public void setT_infosPlayer(AngleString tInfosPlayer) {
		t_infosPlayer = tInfosPlayer;
	}
	
}
