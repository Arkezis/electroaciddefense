package electroacid.defense.gui;



import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.Game;

public  class Menu {

	private AngleString t_infosPlayerTitre,t_infosPlayerSpeed,t_infosPlayerLevel,t_infosPlayerMoney,t_infosPlayerLives;
	
	public Menu(Game game, AngleFont font, AngleFont fontTitle, AngleSurfaceView mGLSurfaceView){
		this.t_infosPlayerTitre = new AngleString(fontTitle);
		this.t_infosPlayerSpeed = new AngleString(font);
		this.t_infosPlayerLevel = new AngleString(font);
		this.t_infosPlayerMoney = new AngleString(font);
		this.t_infosPlayerLives = new AngleString(font);

		this.t_infosPlayerTitre.set("Game");
		this.t_infosPlayerSpeed.set("Speed : "+game.getSpeedMultiplicator());
		this.t_infosPlayerLevel.set("\n Level : "+game.getLevel());
		this.t_infosPlayerMoney.set("\n Money : "+game.getMoney());
		this.t_infosPlayerLives.set("\n Lives : "+game.getLives());

		this.t_infosPlayerTitre.mAlignment = AngleString.aCenter;
		this.t_infosPlayerTitre.mPosition.set(270, 427); 
		mGLSurfaceView.addObject(this.t_infosPlayerTitre);
	
		this.t_infosPlayerSpeed.mAlignment = AngleString.aCenter;
		this.t_infosPlayerSpeed.mPosition.set(270, 440); 
		mGLSurfaceView.addObject(this.t_infosPlayerSpeed);
		
		this.t_infosPlayerLevel.mAlignment = AngleString.aCenter;
		this.t_infosPlayerLevel.mPosition.set(270, 450); 
		mGLSurfaceView.addObject(this.t_infosPlayerLevel);
		
		this.t_infosPlayerMoney.mAlignment = AngleString.aCenter;
		this.t_infosPlayerMoney.mPosition.set(270, 460); 
		mGLSurfaceView.addObject(this.t_infosPlayerMoney);
		
		this.t_infosPlayerLives.mAlignment = AngleString.aCenter;
		this.t_infosPlayerLives.mPosition.set(270, 470); 
		mGLSurfaceView.addObject(this.t_infosPlayerLives);
		
		
		
	}

	public void refresh(Game game){
		this.t_infosPlayerTitre.set("Game");
		this.t_infosPlayerSpeed.set("Speed : "+game.getSpeedMultiplicator());
		this.t_infosPlayerLevel.set("\n Level : "+game.getLevel());
		this.t_infosPlayerMoney.set("\n Money : "+game.getMoney());
		this.t_infosPlayerLives.set("\n Lives : "+game.getLives());
	}
}
