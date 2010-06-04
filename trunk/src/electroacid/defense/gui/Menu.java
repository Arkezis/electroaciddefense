package electroacid.defense.gui;




import com.android.angle.AngleFont;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.Game;

public  class Menu {

	private AngleString t_infosPlayerTitre,t_infosPlayerLevel,t_infosPlayerMoney,t_infosPlayerLives;
	/**
	 * The constructor for the menu of general informations
	 * @param game The informations about the game
	 * @param font The general font 
	 * @param fontTitle The title font
	 * @param mGLSurfaceView The view
	 */
	public Menu(Game game, AngleFont font, AngleFont fontTitle, AngleSurfaceView mGLSurfaceView){
		this.t_infosPlayerTitre = new AngleString(fontTitle,"Game",270, 427,AngleString.aCenter);
		this.t_infosPlayerLevel = new AngleString(font,"Wave : ",270,450,AngleString.aCenter);
		this.t_infosPlayerMoney = new AngleString(font,"Money : "+game.getMoney()+"$",270,460,AngleString.aCenter);
		this.t_infosPlayerLives = new AngleString(font,"Lives : "+game.getLives(),270,470,AngleString.aCenter);

		mGLSurfaceView.addObject(this.t_infosPlayerTitre);
		mGLSurfaceView.addObject(this.t_infosPlayerLevel);
		mGLSurfaceView.addObject(this.t_infosPlayerMoney);
		mGLSurfaceView.addObject(this.t_infosPlayerLives);	
	}
	/**
	 * The method to refresh the menu
	 * @param game The informationsa about the game
	 */
	public void refresh(Game game){
		this.t_infosPlayerLevel.set("Wave : "+game.getActualWave());
		this.t_infosPlayerMoney.set("Money : "+game.getMoney());
		this.t_infosPlayerLives.set("Lives : "+game.getLives());
	}
}
