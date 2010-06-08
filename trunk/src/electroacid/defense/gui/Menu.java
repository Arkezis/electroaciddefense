package electroacid.defense.gui;




import observ.ObservateurMenu;

import com.android.angle.AngleFont;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.Game;

public  class Menu implements ObservateurMenu{

	private AngleString t_infosPlayerTitre,t_infosPlayerLevel,t_infosPlayerMoney,t_infosPlayerLives,t_infosPlayerNext;
	private AngleString t_infosPlayerNextText,t_infosPlayerLivesText,t_infosPlayerMoneyText,t_infosPlayerLevelText;
	/**
	 * The constructor for the menu of general informations
	 * @param game The informations about the game
	 * @param font The general font 
	 * @param fontTitle The title font
	 * @param mGLSurfaceView The view
	 */
	public Menu(Game game, AngleFont font, AngleFont fontTitle, AngleSurfaceView mGLSurfaceView){
		this.t_infosPlayerTitre = new AngleString(fontTitle,"Game",270, 427,AngleString.aCenter);
		this.t_infosPlayerNext = new AngleString(font,"",300,440,AngleString.aCenter);
		this.t_infosPlayerNextText = new AngleString(font,"Next : ",270,440,AngleString.aCenter);
		
		this.t_infosPlayerLevelText = new AngleString(font,"Wave : ",270,450,AngleString.aCenter);
		this.t_infosPlayerLevel = new AngleString(font,"",300,450,AngleString.aCenter);
		
		this.t_infosPlayerMoney = new AngleString(font,""+game.getMoney(),300,460,AngleString.aCenter);
		this.t_infosPlayerMoneyText = new AngleString(font,"Money : ",265,460,AngleString.aCenter);
		this.t_infosPlayerLives = new AngleString(font,""+game.getLives(),300,470,AngleString.aCenter);
		this.t_infosPlayerLivesText = new AngleString(font,"Lives : ",270,470,AngleString.aCenter);

		mGLSurfaceView.addObject(this.t_infosPlayerTitre);
		mGLSurfaceView.addObject(this.t_infosPlayerNext);mGLSurfaceView.addObject(this.t_infosPlayerNextText);
		mGLSurfaceView.addObject(this.t_infosPlayerLevel);mGLSurfaceView.addObject(this.t_infosPlayerLevelText);
		mGLSurfaceView.addObject(this.t_infosPlayerMoney);mGLSurfaceView.addObject(this.t_infosPlayerMoneyText);
		mGLSurfaceView.addObject(this.t_infosPlayerLives);mGLSurfaceView.addObject(this.t_infosPlayerLivesText);
	}
	
	/**
	 * The method to refresh the menu
	 * @param game The informationsa about the game
	 */
	public void refresh(Game game,int lastWave){
		this.t_infosPlayerNext.set(""+ (game.getTimeBetweenEachWave()-lastWave));
	}
	
	/**
	 * The method to refresh the menu of money
	 * @param game The informationsa about the game
	 */
	public void refreshMoney(Game game){
		this.t_infosPlayerMoney.set(""+game.getMoney());
	}
	
	/**
	 * The method to refresh the number of waves
	 * @param game The informations about the game
	 */
	public void refreshWaves(Game game){
		this.t_infosPlayerLevel.set(""+game.getActualWave());
	}
	
	/**
	 * The method to refresh the menu of lives
	 * @param game The informationsa about the game
	 */
	public void refreshLives(Game game){
		this.t_infosPlayerLives.set(""+game.getLives());
	}


	
}
