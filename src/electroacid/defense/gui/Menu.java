package electroacid.defense.gui;

import com.stickycoding.Rokon.Font;
import com.stickycoding.Rokon.Text;

import electroacid.defense.Game;

public abstract class Menu {

	private Text t_infosPlayer;
	
	public Menu(Game game, Font font){
		//this.t_infosPlayer = new Text("## Game ## \n Speed : "+game.getSpeedMultiplicator(),font,240,416,32);
		//this.t_infosPlayer = new Text("Game Speed : ",font,240,416,32);
	}


	/**
	 * @return the t_infosPlayer
	 */
	public Text getT_infosPlayer() {
		return t_infosPlayer;
	}



	/**
	 * @param tInfosPlayer the t_infosPlayer to set
	 */
	public void setT_infosPlayer(Text tInfosPlayer) {
		t_infosPlayer = tInfosPlayer;
	}
	
}
