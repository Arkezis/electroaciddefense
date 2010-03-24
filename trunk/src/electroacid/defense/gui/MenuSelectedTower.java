package electroacid.defense.gui;

import android.util.Log;

import com.android.angle.AngleFont;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.Game;
import electroacid.defense.R;
import electroacid.defense.Tower;

public class MenuSelectedTower {
	
	public AngleString t_infosTowerTitle,t_infosTowerLevel,t_infosTowerElement, t_infosTowerLife, t_infosTowerFireRate;
	public AngleString t_infosTowerCanTargetFly, t_infosTowerDamage;
	public AngleSpriteLayout bDeleteTowerLayout,bUpgradeTowerLayout;
	public AngleSprite bDeleteTower,bUpgradeTower;
	
	public MenuSelectedTower(Game game, AngleFont font,AngleFont fontTitle, AngleSurfaceView mGLSurfaceView){
		this.t_infosTowerTitle = new AngleString(fontTitle);
		this.t_infosTowerTitle.set("Upgrade Tower");
		this.t_infosTowerTitle.mAlignment = AngleString.aCenter;
		this.t_infosTowerTitle.mPosition.set(100, 427); 
		
		this.t_infosTowerLevel = new AngleString(font);
		this.t_infosTowerElement = new AngleString(font);		
		this.t_infosTowerLife = new AngleString(font);		
		this.t_infosTowerFireRate = new AngleString(font);
		this.t_infosTowerCanTargetFly = new AngleString(font);		
		this.t_infosTowerDamage = new AngleString(font);
		
		this.bDeleteTowerLayout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.upgradetower);
		this.bDeleteTower = new AngleSprite(this.bDeleteTowerLayout);
		this.bDeleteTower.mPosition.set(150, 432); 

		this.bUpgradeTowerLayout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.deletetower);
		this.bUpgradeTower = new AngleSprite(this.bUpgradeTowerLayout);
		this.bUpgradeTower.mPosition.set(150, 464); 
	}
	


	public void hide(AngleSurfaceView mGLSurfaceView){
		Log.d("DEBUGTAG", "HIDDING NewMenu");
		mGLSurfaceView.removeObject(this.t_infosTowerTitle);	
		mGLSurfaceView.removeObject(bDeleteTower);
		mGLSurfaceView.removeObject(bUpgradeTower);
		mGLSurfaceView.removeObject(t_infosTowerElement);
		mGLSurfaceView.removeObject(t_infosTowerLife);
		mGLSurfaceView.removeObject(t_infosTowerFireRate);
		mGLSurfaceView.removeObject(t_infosTowerDamage);
		mGLSurfaceView.removeObject(t_infosTowerCanTargetFly);
	}
	
	public void show(AngleSurfaceView mGLSurfaceView,Tower tower){
		Log.d("DEBUGTAG", "SHOWING NewMenu");
		mGLSurfaceView.addObject(this.t_infosTowerTitle);
		this.t_infosTowerElement.set("Element : "+tower.getElement().toString());
		this.t_infosTowerElement.mAlignment = AngleString.aLeft;
		this.t_infosTowerElement.mPosition.set(16, 440); 
		
		this.t_infosTowerLife.set("Life : "+tower.getLife()+"=>"+tower.getLife()*tower.getUpgrade());
		this.t_infosTowerLife.mAlignment = AngleString.aLeft;
		this.t_infosTowerLife.mPosition.set(16, 450); 
		
		this.t_infosTowerFireRate.set("Fire rate : "+tower.getFireRate()+"=>"+tower.getFireRate()*tower.getUpgrade());
		this.t_infosTowerFireRate.mAlignment = AngleString.aLeft;
		this.t_infosTowerFireRate.mPosition.set(16, 460); 
		
		this.t_infosTowerDamage.set("Damage : "+tower.getDamage()+"=>"+tower.getDamage()*tower.getUpgrade());
		this.t_infosTowerDamage.mAlignment = AngleString.aLeft;
		this.t_infosTowerDamage.mPosition.set(16, 470); 
		
		if(tower.isCanTargetFly()){
			this.t_infosTowerCanTargetFly.set("Can target fly");
		}else{
			this.t_infosTowerCanTargetFly.set("");
		}
		this.t_infosTowerCanTargetFly.mAlignment = AngleString.aLeft;
		this.t_infosTowerCanTargetFly.mPosition.set(16, 480); 

		mGLSurfaceView.addObject(t_infosTowerElement);
		mGLSurfaceView.addObject(t_infosTowerLife);
		mGLSurfaceView.addObject(t_infosTowerFireRate);
		mGLSurfaceView.addObject(t_infosTowerDamage);
		mGLSurfaceView.addObject(t_infosTowerCanTargetFly);
		mGLSurfaceView.addObject(bDeleteTower);
		mGLSurfaceView.addObject(bUpgradeTower);
	}


	public boolean isUpgradedOrDeletedTower(int x,int y, Tower tower){
		if (x > 134 && x < 166 ){
			if (y > 416 && y < 448){
				tower.upgrade();
			}else if(y > 448 & y < 480 ){
				// delete the tower
			}
		}
		return false;
	}
}
