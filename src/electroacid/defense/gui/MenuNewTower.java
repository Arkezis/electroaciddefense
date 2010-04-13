package electroacid.defense.gui;

import android.util.Log;

import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.Game;
import electroacid.defense.R;
import electroacid.defense.Tower;

public class MenuNewTower {
	
	public AngleString t_infosTowerTitle,t_infosTowerElement, t_infosTowerLife, t_infosTowerFireRate;
	public AngleString t_infosTowerCanTargetFly, t_infosTowerDamage;
	public AngleSpriteLayout bnewTower1Layout,bnewTower2Layout,bGoLayout;
	public AngleSprite bnewTower1,bnewTower2,bGo;
	
	public MenuNewTower(Game game, AngleFont font,AngleFont fontTitle, AngleSurfaceView mGLSurfaceView){
		//this.t_infosTowerTitle = new AngleString(fontTitle);
		this.t_infosTowerTitle = new AngleString(font,"Tower \n test",160, 427, AngleString.aCenter);
		/*this.t_infosTowerTitle.set("Tower \n test");
		this.t_infosTowerTitle.mAlignment = AngleString.aCenter;
		this.t_infosTowerTitle.mPosition.set(160, 427); 
		*/
		this.t_infosTowerElement = new AngleString(font);		
		this.t_infosTowerLife = new AngleString(font);		
		this.t_infosTowerFireRate = new AngleString(font);
		this.t_infosTowerCanTargetFly = new AngleString(font);		
		this.t_infosTowerDamage = new AngleString(font);
		
	
		this.bnewTower1Layout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.tower1);
		this.bnewTower1 = new AngleSprite(this.bnewTower1Layout);
		this.bnewTower1.mPosition.set(16, 432); 

		this.bnewTower2Layout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.tower2);
		this.bnewTower2 = new AngleSprite(this.bnewTower2Layout);
		this.bnewTower2.mPosition.set(16, 464); 
				
		this.bGoLayout = new AngleSpriteLayout(mGLSurfaceView, 64, 32, R.drawable.go);
		this.bGo = new AngleSprite(this.bGoLayout);
		this.bGo.mPosition.set(85, 430); 
		
		mGLSurfaceView.addObject(this.t_infosTowerTitle);
		mGLSurfaceView.addObject(bnewTower1);
		mGLSurfaceView.addObject(bnewTower2);
		mGLSurfaceView.addObject(bGo);
		mGLSurfaceView.addObject(t_infosTowerElement);
		mGLSurfaceView.addObject(t_infosTowerLife);
		mGLSurfaceView.addObject(t_infosTowerFireRate);
		mGLSurfaceView.addObject(t_infosTowerDamage);
		mGLSurfaceView.addObject(t_infosTowerCanTargetFly);
		
		this.t_infosTowerTitle.mAlpha = 0;
		this.bnewTower1.mAlpha = 0;
		this.bnewTower2.mAlpha = 0;
		this.bGo.mAlpha=0;
		this.t_infosTowerElement.mAlpha = 0;
		this.t_infosTowerLife.mAlpha = 0;
		this.t_infosTowerFireRate.mAlpha = 0;
		this.t_infosTowerDamage.mAlpha = 0;
		this.t_infosTowerCanTargetFly.mAlpha = 0;
		
	}
	
	public  int getNewTowerFromMenuNewTower(int x,int y){
		int ret = 0;
		if(x > 0 && x < 32){ // 1st column
			if(y > 416 && y < 448)		ret = 1;
			else if(y > 448 && y < 480)	ret = 2;
		}else if(x > 32 && x < 64){ // 2nd column
				//...
		}
		return ret;
	}

	public void hide(AngleSurfaceView mGLSurfaceView){
		Log.d("DEBUGTAG", "HIDDING NewTowerMenu");
		this.t_infosTowerTitle.mAlpha = 0;
		this.bnewTower1.mAlpha = 0;
		this.bnewTower2.mAlpha = 0;
	}
	
	public void show(AngleSurfaceView mGLSurfaceView){
		Log.d("DEBUGTAG", "SHOWING NewTowerMenu");
		this.t_infosTowerTitle.mAlpha = 1;
		this.bnewTower1.mAlpha = 1;
		this.bnewTower2.mAlpha = 1;
		this.hideValidateTower(mGLSurfaceView); // if you want to show the selection, the validation must be hidden (for the moment !)
	}
	
	public void showValidateTower(AngleSurfaceView mGLSurfaceView,Tower tower){
		
		this.bGo.mAlpha=1;
		this.t_infosTowerElement.mAlpha = 1;
		this.t_infosTowerLife.mAlpha = 1;
		this.t_infosTowerFireRate.mAlpha = 1;
		this.t_infosTowerDamage.mAlpha =1 ;
		this.t_infosTowerCanTargetFly.mAlpha = 1;
		
		this.t_infosTowerElement.set("Element : "+tower.getElement().toString());
		this.t_infosTowerElement.mAlignment = AngleString.aCenter;
		this.t_infosTowerElement.mPosition.set(160, 440); 
		
		this.t_infosTowerLife.set("Life : "+tower.getLife());
		this.t_infosTowerLife.mAlignment = AngleString.aCenter;
		this.t_infosTowerLife.mPosition.set(160, 450); 
		
		this.t_infosTowerFireRate.set("Fire rate : "+tower.getFireRate());
		this.t_infosTowerFireRate.mAlignment = AngleString.aCenter;
		this.t_infosTowerFireRate.mPosition.set(160, 460); 
		
		this.t_infosTowerDamage.set("Damage : "+tower.getDamage());
		this.t_infosTowerDamage.mAlignment = AngleString.aCenter;
		this.t_infosTowerDamage.mPosition.set(160, 470); 
		
		if(tower.isCanTargetFly()){
			this.t_infosTowerCanTargetFly.set("Can target fly");
		}else{
			this.t_infosTowerCanTargetFly.set("");
		}
		this.t_infosTowerCanTargetFly.mAlignment = AngleString.aCenter;
		this.t_infosTowerCanTargetFly.mPosition.set(160, 480); 


	}

	public void hideValidateTower(AngleSurfaceView mGLSurfaceView){
		this.bGo.mAlpha=0;
		this.t_infosTowerElement.mAlpha = 0;
		this.t_infosTowerLife.mAlpha = 0;
		this.t_infosTowerFireRate.mAlpha = 0;
		this.t_infosTowerDamage.mAlpha = 0;
		this.t_infosTowerCanTargetFly.mAlpha = 0;
		Log.d("DEBUGTAG", "On cache le sous menu");
	}

	public boolean isValidationTower(int x,int y){
		if (x > 53 && x < 117 ){
			if (y > 414 && y < 446){
				return true;
			}
		}
		return false;
	}
}
