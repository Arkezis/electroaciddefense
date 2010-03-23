package electroacid.defense.gui;

import com.android.angle.AngleFont;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.Game;

public class MenuNewTower {
	private AngleString t_infosPlayerTitre;

	public MenuNewTower(Game game, AngleFont font, AngleSurfaceView mGLSurfaceView){
		this.t_infosPlayerTitre = new AngleString(font);
		this.t_infosPlayerTitre.set("Tower");
		this.t_infosPlayerTitre.mAlignment = AngleString.aCenter;
		this.t_infosPlayerTitre.mPosition.set(240, 430); 
		mGLSurfaceView.addObject(this.t_infosPlayerTitre);
	}
}
