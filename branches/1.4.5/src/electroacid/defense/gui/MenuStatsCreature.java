package electroacid.defense.gui;

import observ.ObservateurMenu;

import com.android.angle.AngleFont;
import com.android.angle.AngleSprite;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.Creature;

public class MenuStatsCreature implements ObservateurMenu{


	private AngleString t_infosCreaText, t_infosCreaValue;
	private AngleSprite spriteInfoCrea;

	Creature actualCrea;
	/**
	 * Constructor 
	 * @param font The font for the classic text 
	 * @param fontTitle The font for the title text
	 * @param mGLSurfaceView The view
	 */
	public MenuStatsCreature(AngleFont font,AngleFont fontTitle, AngleSurfaceView mGLSurfaceView){
		this.t_infosCreaText = new AngleString(font,"Element : \n Speed : \n Reward : \n Life : ",16, 427,AngleString.aLeft);	
		this.t_infosCreaValue = new AngleString(font,"",100,427,AngleString.aLeft);
		this.spriteInfoCrea = new AngleSprite(null);
		this.spriteInfoCrea.mPosition.set(170, 450);
		mGLSurfaceView.addObject(spriteInfoCrea);
		mGLSurfaceView.addObject(t_infosCreaText);
		mGLSurfaceView.addObject(t_infosCreaValue);
		this.t_infosCreaText.mAlpha = 0;
		this.t_infosCreaValue.mAlpha = 0;
		this.spriteInfoCrea.mAlpha = 0;
	}

	/**
	 * Hide the general menu
	 */
	public void hide(){
		this.t_infosCreaText.mAlpha = 0;
		this.t_infosCreaValue.mAlpha = 0;
		this.spriteInfoCrea.mAlpha = 0;
		if (this.actualCrea!=null)
			this.actualCrea.delObservateur(this);
	}
	/**
	 * Show the general menu
	 * @param mGLSurfaceView The view
	 * @param Crea The creature
	 * @param g The game informations
	 */
	public void show(AngleSurfaceView mGLSurfaceView,Creature crea){
		this.t_infosCreaValue.set(crea.getElement().toString()+"\n"+(int)crea.getSpeed()+"\n"+crea.getRewardValue()+"\n"+crea.getLife());
		this.t_infosCreaText.mAlpha = 1;
		this.t_infosCreaValue.mAlpha = 1;
		this.spriteInfoCrea.setLayout(crea.getSprite().roLayout);
		this.spriteInfoCrea.mAlpha = 1;
		if (this.actualCrea!=null)
			this.actualCrea.delObservateur(this);
		this.actualCrea=crea;
		crea.addObservateur(this);
	}

	@Override
	public void refreshCreature() {
		if (this.actualCrea.getLife()<=0) {
			this.hide();
		}else {
			this.t_infosCreaValue.set(
					this.actualCrea.getElement().toString()+"\n"
					+(int)this.actualCrea.getSpeed()+"\n"
					+this.actualCrea.getRewardValue()+"\n"
					+this.actualCrea.getLife());
		}

	}
	@Override
	public void refreshLives() {}
	@Override
	public void refreshMoney() {}
	@Override
	public void refreshScore() {}
	@Override
	public void refreshWaves() {}


}
