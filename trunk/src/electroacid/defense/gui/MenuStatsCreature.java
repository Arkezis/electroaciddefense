package electroacid.defense.gui;

import com.android.angle.AngleFont;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.Creature;
import electroacid.defense.Game;

public class MenuStatsCreature {
	

	private AngleString t_infosCreaText, t_infosCreaValue;
	/**
	 * Constructor 
	 * @param font The font for the classic text 
	 * @param fontTitle The font for the title text
 	 * @param mGLSurfaceView The view
	 */
	public MenuStatsCreature(AngleFont font,AngleFont fontTitle, AngleSurfaceView mGLSurfaceView){
		this.t_infosCreaText = new AngleString(font,"Element : \n Speed : \n Reward : \n Life : ",16, 427,AngleString.aLeft);	
		this.t_infosCreaValue = new AngleString(font,"",100,427,AngleString.aLeft);
		
		mGLSurfaceView.addObject(t_infosCreaText);
		mGLSurfaceView.addObject(t_infosCreaValue);
		this.t_infosCreaText.mAlpha = 0;
		this.t_infosCreaValue.mAlpha = 0;
	}
	
	/**
	 * Hide the general menu
	 * @param mGLSurfaceView The view
	 */
	public void hide(AngleSurfaceView mGLSurfaceView){
		this.t_infosCreaText.mAlpha = 0;
		this.t_infosCreaValue.mAlpha = 0;
	}
	/**
	 * Show the general menu
	 * @param mGLSurfaceView The view
	 * @param Crea The creature
	 * @param g The game informations
	 */
	public void show(AngleSurfaceView mGLSurfaceView,Creature crea,Game g){
		this.t_infosCreaValue.set(crea.getElement().toString()+"\n"+(int)crea.getSpeed()+"\n"+crea.getRewardValue()+"\n"+crea.getLife());
		this.t_infosCreaText.mAlpha = 1;
		this.t_infosCreaValue.mAlpha = 1;
	}

	
}
