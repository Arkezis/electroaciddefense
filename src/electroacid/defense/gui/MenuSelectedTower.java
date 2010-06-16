package electroacid.defense.gui;

import java.util.LinkedList;

import observ.ObservateurMenu;

import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.R;
import electroacid.defense.Tower;
import electroacid.defense.box.BoxBuildable;
import electroacid.defense.enums.ShootPriority;
import electroacid.defense.game.GenericGame;

public class MenuSelectedTower implements ObservateurMenu{

	public AngleString t_infosTowerText,t_infosTowerValue,t_infosTowerUpgrade,t_infosTowerDestroy;
	public AngleSprite bDeleteTower,bUpgradeTower;
	public AngleSprite bWeak, bStrong, bFirstIn;
	private int cashNeedForUpgradeTower;

	/**
	 * Constructor 
	 * @param font The font for the classic text 
	 * @param fontTitle The font for the title text
	 * @param mGLSurfaceView The view
	 */
	public MenuSelectedTower( AngleFont font,AngleFont fontTitle, AngleSurfaceView mGLSurfaceView){
		this.t_infosTowerText = new AngleString(font,"Element : \nLevel : \nDamage : \nShoot : ",0, 427,AngleString.aLeft);
		this.t_infosTowerValue = new AngleString(font,"",81, 427,AngleString.aLeft);	
		this.t_infosTowerUpgrade = new AngleString(font,"",180,440,AngleString.aLeft);
		this.t_infosTowerDestroy = new AngleString(font,"",180,470,AngleString.aLeft);
		this.bUpgradeTower = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,0,160,32,32));
		this.bUpgradeTower.mPosition.set(160, 432); 
		this.bDeleteTower = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,32,160,32,32));
		this.bDeleteTower.mPosition.set(160, 464); 

		this.bWeak = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,160,160,32,32));
		this.bWeak.mPosition.set(252, 432);
		this.bStrong = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,192,160,32,32));
		this.bStrong.mPosition.set(290, 432);
		this.bFirstIn = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,64,32,R.drawable.tilemap,224,160,64,32));
		this.bFirstIn.mPosition.set(268, 464);
		
		mGLSurfaceView.addObject(t_infosTowerValue);
		mGLSurfaceView.addObject(t_infosTowerText);
		mGLSurfaceView.addObject(bDeleteTower);
		mGLSurfaceView.addObject(bUpgradeTower);
		mGLSurfaceView.addObject(t_infosTowerDestroy);
		mGLSurfaceView.addObject(t_infosTowerUpgrade);
		mGLSurfaceView.addObject(bWeak);
		mGLSurfaceView.addObject(bStrong);
		mGLSurfaceView.addObject(bFirstIn);
		this.t_infosTowerDestroy.mAlpha=0;
		this.t_infosTowerUpgrade.mAlpha=0;
		this.t_infosTowerText.mAlpha = 0;
		this.t_infosTowerValue.mAlpha = 0;
		this.bDeleteTower.mAlpha = 0;
		this.bUpgradeTower.mAlpha = 0;
		this.bWeak.mAlpha = 0;
		this.bStrong.mAlpha = 0;
		this.bFirstIn.mAlpha = 0;
	}

	/**
	 * Hide the general menu
	 */
	public void hide(){
		GenericGame.getInstance().delObservateur(this);
		this.t_infosTowerText.mAlpha = 0;
		this.t_infosTowerValue.mAlpha = 0;
		this.bDeleteTower.mAlpha = 0;
		this.bUpgradeTower.mAlpha = 0;
		this.t_infosTowerDestroy.mAlpha=0;
		this.t_infosTowerUpgrade.mAlpha=0;
		this.bWeak.mAlpha = 0;
		this.bStrong.mAlpha = 0;
		this.bFirstIn.mAlpha = 0;
	}
	/**
	 * Show the general menu
	 * @param tower The tower to upgrade
	 * @param g The game informations
	 */
	public void show(Tower tower){
		this.t_infosTowerValue.set(tower.getElement().toString()+"\n"+tower.getLevel()+"=>"+(tower.getLevel()+1)+"\n"+tower.getDamage()+"=>"+(int)(tower.getDamage()*tower.getUpgrade())+"\n"+tower.getFireRate());
		this.t_infosTowerValue.mAlpha = 1;
		this.t_infosTowerDestroy.set((int)(tower.getCost()*tower.getDestroy())+"$");
		this.t_infosTowerUpgrade.set((int)(tower.getCost()*tower.getUpgrade())+"$");
		this.t_infosTowerText.mAlpha = 1;
		this.bDeleteTower.mAlpha = 1;
		this.t_infosTowerDestroy.mAlpha=1;
		this.t_infosTowerUpgrade.mAlpha=1;
		this.bWeak.mAlpha = 1;
		this.bStrong.mAlpha = 1;
		this.bFirstIn.mAlpha = 1;
		if(tower.getTargetPriority().equals(ShootPriority.WEAKEST)){
			this.bWeak.mAlpha = (float)0.5;	
		}else if(tower.getTargetPriority().equals(ShootPriority.HIGHEST)){
			this.bStrong.mAlpha = (float)0.5;	
		}else if(tower.getTargetPriority().equals(ShootPriority.FIRSTIN_FIRSTDIE)){
			this.bFirstIn.mAlpha = (float)0.5;	
		}
		this.cashNeedForUpgradeTower=(int)Math.ceil((tower.getCost()*tower.getUpgrade()));
		GenericGame game = GenericGame.getInstance();
		if (game.getMoney() >= this.cashNeedForUpgradeTower){
			this.bUpgradeTower.mAlpha = 1;
		}else {
			this.bUpgradeTower.mAlpha = (float)0.4;
			game.addObservateur(this);
		}
	}

	/**
	 * This method return true if the tower is upgraded or deleted
	 * @param x The coordonate where the player has touched
	 * @param y The coordonate where the player has touched
	 * @param box The box where the tower is
	 * @param g The informationsa about the game
	 * @param ogField The object where the tower are added
	 * @param towerList The list of tower in the game
	 * @param mGLSurfaceView 
	 * @return True if the tower is upgraded or deleted
	 */

	public boolean isUpgradedOrDeletedTower(int x,int y, BoxBuildable box,AngleObject ogField,LinkedList<BoxBuildable> towerList, AngleSurfaceView mGLSurfaceView,AngleObject ogUpgradeTower){
		if (x > 144 && x < 176 ){
			GenericGame game = GenericGame.getInstance();
			if (y > 416 && y < 448){
				
				if (game.getMoney() > (int)Math.ceil((box.getTower().getCost()*box.getTower().getUpgrade()))){
					if (game.getMaxLevelTower()!=box.getTower().getLevel())
						box.getTower().upgrade(mGLSurfaceView,ogUpgradeTower);
					return true;
				}
			}else if(y > 448 & y < 480 ){
				towerList.remove(box.getTower());
				box.getTower().destroy(ogField,ogUpgradeTower);
				box.removeTower();
				return true;
			}
		}
		if (y > 416 && y < 448){
			if  (x > 236 && x < 268){
				box.getTower().setTargetPriority(ShootPriority.WEAKEST);
				return true;
			}else if(x > 274 && x < 306 ){
				box.getTower().setTargetPriority(ShootPriority.HIGHEST);
				return true;
			}
		}else if (y > 448 && y < 496){
			if( x > 220 && x < 284){
				box.getTower().setTargetPriority(ShootPriority.FIRSTIN_FIRSTDIE);
				return true;
			}
		}
		return false;
	}

	@Override
	public void refreshCreature() {}
	@Override
	public void refreshLives() {}
	@Override
	public void refreshMoney() {
		if (GenericGame.getInstance().getMoney() >= this.cashNeedForUpgradeTower){
			this.bUpgradeTower.mAlpha = 1;
			GenericGame.getInstance().delObservateur(this);
		}
	}
	@Override
	public void refreshScore() {}
	@Override
	public void refreshWaves() {}
}
