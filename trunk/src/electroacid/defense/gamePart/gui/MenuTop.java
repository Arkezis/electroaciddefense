package electroacid.defense.gamePart.gui;


import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.R;
import electroacid.defense.gamePart.game.GenericGame;
import electroacid.defense.gamePart.observ.ObservateurMenu;

public class MenuTop implements ObservateurMenu{

	private AngleString t_infosPlayerLevel,
	t_infosPlayerMoney,
	t_infosPlayerLives,
	t_infosPlayerNext;

	private AngleSprite t_infosPlayerNextSpr,
	t_infosPlayerLivesSpr,
	t_infosPlayerMoneySpr,
	t_infosPlayerLevelSpr,
	t_infosPlayerSpeedSpr;

	private AngleSpriteLayout speed1,speed2,speed3;

	public MenuTop(AngleFont font, AngleSurfaceView mGLSurfaceView,AngleObject og){

		AngleSpriteLayout a = new AngleSpriteLayout(mGLSurfaceView,320,32,R.drawable.tilemap,32,0,32,32);
		AngleSprite b = new AngleSprite(160, 16,(float)0.85, a);
		og.addObject(b);

		GenericGame game = GenericGame.getInstance();

		this.t_infosPlayerLivesSpr = new AngleSprite(16, 16,
				new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,0,192,32,32));

		this.t_infosPlayerLives = new AngleString(font,""+game.getLives(),48,22,AngleString.aCenter);


		this.t_infosPlayerMoneySpr = new AngleSprite(82, 16,
				new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,32,192,32,32));	

		this.t_infosPlayerMoney = new AngleString(font,""+game.getMoney(),114,22,AngleString.aCenter);


		this.t_infosPlayerLevelSpr = new AngleSprite(176, 16,
				new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,160,192,32,32));	

		this.t_infosPlayerLevel = new AngleString(font,"",208,22,AngleString.aCenter);


		this.speed1 = new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,130,192,32,32);
		this.speed2 = new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,192,192,32,32);
		this.speed3 = new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,224,192,32,32);


		this.t_infosPlayerSpeedSpr = new AngleSprite(240, 16,this.speed1);


		this.t_infosPlayerNextSpr = new AngleSprite(274, 16,
				new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,64,192,32,32));

		this.t_infosPlayerNext = new AngleString(font,"",306,22,AngleString.aCenter);


		og.addObject(this.t_infosPlayerNext);og.addObject(this.t_infosPlayerNextSpr);
		og.addObject(this.t_infosPlayerLevel);og.addObject(this.t_infosPlayerLevelSpr);
		og.addObject(this.t_infosPlayerMoney);og.addObject(this.t_infosPlayerMoneySpr);
		og.addObject(this.t_infosPlayerLives);og.addObject(this.t_infosPlayerLivesSpr);
		og.addObject(this.t_infosPlayerSpeedSpr);

		game.addObservateur(this);

	}


	/**
	 * The method to refresh the menu
	 * @param game The informations about the game
	 */
	public void refresh(int lastWave){
		this.t_infosPlayerNext.set(""+ (GenericGame.getInstance().getTimeBetweenEachWave()-lastWave));
	}
	/**
	 * The method to refresh the menu of money
	 * @param game The informationsa about the game
	 */
	@Override
	public void refreshMoney(){
		this.t_infosPlayerMoney.set(""+GenericGame.getInstance().getMoney());
	}
	/**
	 * The method to refresh the number of waves
	 * @param game The informations about the game
	 */
	@Override
	public void refreshWaves(){
		this.t_infosPlayerLevel.set(""+GenericGame.getInstance().getActualWave());
	}
	/**
	 * The method to refresh the menu of lives
	 * @param game The informationsa about the game
	 */
	@Override
	public void refreshLives(){
		this.t_infosPlayerLives.set(""+GenericGame.getInstance().getLives());
	}
	/**
	 * The method to test if the user has touched the button to launch next wave
	 */
	public boolean nextWaveButtonIsTouched(int x, int y){
		return ( (x>=274 && x<=306) && (y>=0 && y<=32) );
	}
	/**
	 * The method to test if the user has touched the button to accelerate
	 */
	public boolean accelerateButtonIsTouched(int x, int y){
		if ( (x>=224 && x<=256) && (y>=0 && y<=32) ){
			int speed = GenericGame.getInstance().nextSpeedMultiplicator();
			switch (speed){
			case 1:
				this.t_infosPlayerSpeedSpr.setLayout(this.speed1);
				break;
			case 2:
				this.t_infosPlayerSpeedSpr.setLayout(this.speed2);
				break;
			case 3:
				this.t_infosPlayerSpeedSpr.setLayout(this.speed3);
				break;
			}
			return true;
		}
		return false;
	}
	@Override
	public void refreshScore() {
		// TODO Auto-generated method stub
	}

	@Override
	public void refreshCreature() {return;}

}
