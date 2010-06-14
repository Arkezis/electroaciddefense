package electroacid.defense.gui;

import java.util.LinkedList;

import observ.ObservateurMenu;


import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.game.GenericGame;
import electroacid.defense.R;
import electroacid.defense.Tower;
import electroacid.defense.box.BoxBuildable;

public class MenuSelectedTower implements ObservateurMenu{
	
	public AngleString t_infosTowerText,t_infosTowerValue,t_infosTowerUpgrade,t_infosTowerDestroy;
	public AngleSprite bDeleteTower,bUpgradeTower;
	
	private int cashNeedForUpgradeTower;
	
	/**
	 * Constructor 
	 * @param font The font for the classic text 
	 * @param fontTitle The font for the title text
 	 * @param mGLSurfaceView The view
	 */
	public MenuSelectedTower( AngleFont font,AngleFont fontTitle, AngleSurfaceView mGLSurfaceView){
		this.t_infosTowerText = new AngleString(font,"Element : \n Level : \n Damage :",2, 427,AngleString.aLeft);
		this.t_infosTowerValue = new AngleString(font,"",80, 427,AngleString.aLeft);	
		this.t_infosTowerUpgrade = new AngleString(font,"",170,440,AngleString.aLeft);
		this.t_infosTowerDestroy = new AngleString(font,"",170,470,AngleString.aLeft);
		this.bUpgradeTower = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,0,160,32,32));
		this.bUpgradeTower.mPosition.set(150, 432); 
		this.bDeleteTower = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,32,160,32,32));
		this.bDeleteTower.mPosition.set(150, 464); 
		
		mGLSurfaceView.addObject(t_infosTowerValue);
		mGLSurfaceView.addObject(t_infosTowerText);
		mGLSurfaceView.addObject(bDeleteTower);
		mGLSurfaceView.addObject(bUpgradeTower);
		mGLSurfaceView.addObject(t_infosTowerDestroy);
		mGLSurfaceView.addObject(t_infosTowerUpgrade);
		this.t_infosTowerDestroy.mAlpha=0;
		this.t_infosTowerUpgrade.mAlpha=0;
		this.t_infosTowerText.mAlpha = 0;
		this.t_infosTowerValue.mAlpha = 0;
		this.bDeleteTower.mAlpha = 0;
		this.bUpgradeTower.mAlpha = 0;
	}
	
	/**
	 * Hide the general menu
	 */
	public void hide(GenericGame g){
		this.t_infosTowerText.mAlpha = 0;
		this.t_infosTowerValue.mAlpha = 0;
		this.bDeleteTower.mAlpha = 0;
		this.bUpgradeTower.mAlpha = 0;
		this.t_infosTowerDestroy.mAlpha=0;
		this.t_infosTowerUpgrade.mAlpha=0;
		g.delObservateur(this);
	}
	/**
	 * Show the general menu
	 * @param tower The tower to upgrade
	 * @param g The game informations
	 */
	public void show(Tower tower,GenericGame g){
		
		this.t_infosTowerValue.set(tower.getElement().toString()+"\n"+tower.getLevel()+"=>"+(tower.getLevel()+1)+"\n"+tower.getDamage()+"=>"+(int)(tower.getDamage()*tower.getUpgrade())+"\n");
		this.t_infosTowerValue.mAlpha = 1;
		this.t_infosTowerDestroy.set((int)(tower.getCost()*tower.getDestroy())+"$");
		this.t_infosTowerUpgrade.set((int)(tower.getCost()*tower.getUpgrade())+"$");
		this.t_infosTowerText.mAlpha = 1;
		this.bDeleteTower.mAlpha = 1;
		this.t_infosTowerDestroy.mAlpha=1;
		this.t_infosTowerUpgrade.mAlpha=1;
		this.cashNeedForUpgradeTower=(int)Math.ceil((tower.getCost()*tower.getUpgrade()));
		if (g.getMoney() >= this.cashNeedForUpgradeTower){
			this.bUpgradeTower.mAlpha = 1;
		}else {
			g.addObservateur(this);
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
	 * @return True if the tower is upgraded or deleted
	 */
	public boolean isUpgradedOrDeletedTower(int x,int y, BoxBuildable box,GenericGame g,AngleObject ogField,LinkedList<BoxBuildable> towerList){
		if (x > 134 && x < 166 ){
			if (y > 416 && y < 448){
				if (g.getMoney() > box.getTower().getCost()*box.getTower().getUpgrade()){
					box.getTower().upgrade(g);
					return true;
				}
			}else if(y > 448 & y < 480 ){
				towerList.remove(box.getTower());
				box.getTower().destroy(g,ogField);
				box.removeTower();
				return true;
			}
		}
		return false;
	}

	@Override
	public void refreshCreature() {}
	@Override
	public void refreshLives(GenericGame g) {}
	@Override
	public void refreshMoney(GenericGame g) {
		if (g.getMoney() >= this.cashNeedForUpgradeTower){
			this.bUpgradeTower.mAlpha = 1;
		}
	}
	@Override
	public void refreshScore(GenericGame g) {}
	@Override
	public void refreshWaves(GenericGame g) {}
}
