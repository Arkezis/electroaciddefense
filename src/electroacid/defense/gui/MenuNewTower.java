package electroacid.defense.gui;


import java.util.LinkedList;

import com.android.angle.AngleFont;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.R;
import electroacid.defense.Tower;

public class MenuNewTower {
	
	public AngleString t_infosTowerTitle,t_infosTowerElement;
	public AngleString t_infosTowerCanTargetFly, t_infosTowerDamage;
	public AngleSpriteLayout bGoLayout;
	//public AngleSprite bnewTower1,bnewTower2,bGo;
	public AngleSprite bGo;
	
	/**
	 * The constructor
	 * @param font The font for the classic text 
	 * @param fontTitle The font for the title text
 	 * @param mGLSurfaceView The view
	 */
	public MenuNewTower(AngleFont font,AngleFont fontTitle, AngleSurfaceView mGLSurfaceView,LinkedList<Tower> listTower){
		this.t_infosTowerTitle = new AngleString(font,"Tower",160, 427, AngleString.aCenter);
		this.t_infosTowerElement = new AngleString(font,"",160,440,AngleString.aCenter);		
		this.t_infosTowerDamage = new AngleString(font,"",160,450,AngleString.aCenter);
		this.t_infosTowerCanTargetFly = new AngleString(font,"",160,460,AngleString.aCenter);		
		
		
		
		listTower.get(0).changePosition(0, 416);
		listTower.get(0).getSprite().mAlpha = 0;
		//this.bnewTower1 = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,0,128,32,32));
		//this.bnewTower1.mPosition.set(16, 432); 

		listTower.get(1).changePosition(0, 448);
		listTower.get(1).getSprite().mAlpha = 0;
		//this.bnewTower2 = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,32,128,32,32));
		//this.bnewTower2.mPosition.set(16, 464); 
				
		this.bGoLayout = new AngleSpriteLayout(mGLSurfaceView,64,32,R.drawable.tilemap,128,160,64,32);
		this.bGo = new AngleSprite(this.bGoLayout);
		this.bGo.mPosition.set(85, 430); 
		
		this.t_infosTowerTitle.mAlpha = 0;
		
		//this.bnewTower1.mAlpha = 0;
		//this.bnewTower2.mAlpha = 0;
		this.bGo.mAlpha=0;
		this.t_infosTowerElement.mAlpha = 0;
		this.t_infosTowerDamage.mAlpha = 0;
		this.t_infosTowerCanTargetFly.mAlpha = 0;
		mGLSurfaceView.addObject(this.t_infosTowerTitle);
		//mGLSurfaceView.addObject(bnewTower1);
		//mGLSurfaceView.addObject(bnewTower2);
		mGLSurfaceView.addObject(bGo);
		mGLSurfaceView.addObject(t_infosTowerElement);
		mGLSurfaceView.addObject(t_infosTowerDamage);
		mGLSurfaceView.addObject(t_infosTowerCanTargetFly);	
	}
	/**
	 * This method return the tower number selected by the user
	 * @param x The x coordonate where the user touch the screen
	 * @param y The y coordonate where the user touch the screen
	 * @return The number of tower chosen bu the user
	 */
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
	/**
	 * This function hide the menu 
	 * @param mGLSurfaceView The view
	 */
	public void hide(AngleSurfaceView mGLSurfaceView,LinkedList<Tower> listTower){
		this.t_infosTowerTitle.mAlpha = 0;
		//this.bnewTower1.mAlpha = 0;
		//this.bnewTower2.mAlpha = 0;
		for (int i=0;i<listTower.size();i++){
			listTower.get(i).getSprite().mAlpha=0;
		}
	}
	
	/**
	 * This function show the menu the towers
	 * @param mGLSurfaceView
	 */
	public void show(AngleSurfaceView mGLSurfaceView,LinkedList<Tower> listTower){
		this.t_infosTowerTitle.mAlpha = 1;
		//this.bnewTower1.mAlpha = 1;
		//this.bnewTower2.mAlpha = 1;
		for (int i=0;i<listTower.size();i++){
			listTower.get(i).getSprite().mAlpha=1;
		}
		this.hideValidateTower(mGLSurfaceView); // if you want to show the selection, the validation must be hidden (for the moment !)
	}
	
	/**
	 * This function show the informations about the tower selected 
	 * @param mGLSurfaceView
	 * @param tower
	 */
	public void showValidateTower(AngleSurfaceView mGLSurfaceView,Tower tower){
		this.bGo.mAlpha=1;
		this.t_infosTowerElement.mAlpha = 1;
		this.t_infosTowerDamage.mAlpha =1 ;
		this.t_infosTowerCanTargetFly.mAlpha = 1;
		
		this.t_infosTowerElement.set("Element : "+tower.getElement().toString());	
		this.t_infosTowerDamage.set("Damage : "+tower.getDamage());
		
		if(tower.isCanTargetFly()){
			this.t_infosTowerCanTargetFly.set("Can target fly");
		}else{
			this.t_infosTowerCanTargetFly.set("");
		}
	}

	/**
	 * This function hide the tower selected by the user
	 * @param mGLSurfaceView
	 */
	public void hideValidateTower(AngleSurfaceView mGLSurfaceView){
		this.bGo.mAlpha=0;
		this.t_infosTowerElement.mAlpha = 0;
		this.t_infosTowerDamage.mAlpha = 0;
		this.t_infosTowerCanTargetFly.mAlpha = 0;
	}

	/**
	 * This function return true if the user touched the validation button
	 * @param x The x coordonate where the user touch the screen
	 * @param y The y coordonate where the user touch the screen
	 * @return True if the user wanted to add the tower
	 */
	public boolean isValidationTower(int x,int y){
		if (x > 53 && x < 117 ){
			if (y > 414 && y < 446){
				return true;
			}
		}
		return false;
	}
}
