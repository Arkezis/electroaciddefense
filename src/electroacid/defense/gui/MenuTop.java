package electroacid.defense.gui;

import observ.ObservateurMenu;

import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.R;
import electroacid.defense.game.GenericGame;

public class MenuTop implements ObservateurMenu{

	private AngleString t_infosPlayerLevel,
		t_infosPlayerMoney,
		t_infosPlayerLives,
		t_infosPlayerNext;
	
	private AngleSprite t_infosPlayerNextSpr,
		t_infosPlayerLivesSpr,
		t_infosPlayerMoneySpr,
		t_infosPlayerLevelSpr;
	
	public MenuTop(GenericGame game,AngleFont font, AngleSurfaceView mGLSurfaceView,AngleObject og){
		
		AngleSpriteLayout a = new AngleSpriteLayout(mGLSurfaceView,320,32,R.drawable.tilemap,32,0,32,32);
		AngleSprite b = new AngleSprite(160, 16,(float)0.85, a);
		og.addObject(b);
		
		
		
		this.t_infosPlayerLivesSpr = new AngleSprite(16, 16,
				new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,0,192,32,32));
		
		this.t_infosPlayerLives = new AngleString(font,""+game.getLives(),48,22,AngleString.aCenter);
		

		this.t_infosPlayerMoneySpr = new AngleSprite(82, 16,
				new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,32,192,32,32));	
		
		this.t_infosPlayerMoney = new AngleString(font,""+game.getMoney(),114,22,AngleString.aCenter);
		
		
		this.t_infosPlayerLevelSpr = new AngleSprite(208, 16,
				new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,160,192,32,32));	
		
		this.t_infosPlayerLevel = new AngleString(font,"",240,22,AngleString.aCenter);
		
		this.t_infosPlayerNextSpr = new AngleSprite(274, 16,
				new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,64,192,32,32));

		this.t_infosPlayerNext = new AngleString(font,"",306,22,AngleString.aCenter);
		

		og.addObject(this.t_infosPlayerNext);og.addObject(this.t_infosPlayerNextSpr);
		og.addObject(this.t_infosPlayerLevel);og.addObject(this.t_infosPlayerLevelSpr);
		og.addObject(this.t_infosPlayerMoney);og.addObject(this.t_infosPlayerMoneySpr);
		og.addObject(this.t_infosPlayerLives);og.addObject(this.t_infosPlayerLivesSpr);
	}

	
	/**
	 * The method to refresh the menu
	 * @param game The informations about the game
	 */
	public void refresh(GenericGame game,int lastWave){
		this.t_infosPlayerNext.set(""+ (game.getTimeBetweenEachWave()-lastWave));
	}
	/**
	 * The method to refresh the menu of money
	 * @param game The informationsa about the game
	 */
	@Override
	public void refreshMoney(GenericGame g){
		this.t_infosPlayerMoney.set(""+g.getMoney());
	}
	/**
	 * The method to refresh the number of waves
	 * @param game The informations about the game
	 */
	@Override
	public void refreshWaves(GenericGame g){
		this.t_infosPlayerLevel.set(""+g.getActualWave());
	}
	/**
	 * The method to refresh the menu of lives
	 * @param game The informationsa about the game
	 */
	@Override
	public void refreshLives(GenericGame g){
		this.t_infosPlayerLives.set(""+g.getLives());
	}
	/**
	 * The method to test if the user has touched the button to launch next wave
	 * 
	 */
	public boolean nextWaveButtonIsTouched(int x, int y){
		return ( (x>=274 && x<=306) && (y>=0 && y<=32) );
	}

	@Override
	public void refreshScore(GenericGame g) {
		// TODO Auto-generated method stub
	}

	@Override
	public void refreshCreature() {return;}
	
}
