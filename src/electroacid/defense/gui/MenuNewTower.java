package electroacid.defense.gui;


import java.util.LinkedList;

import observ.ObservateurMenu;

import com.android.angle.AngleFont;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.game.GenericGame;
import electroacid.defense.R;
import electroacid.defense.Tower;

public class MenuNewTower implements ObservateurMenu{
	
	public AngleString t_infosTowerTitle,t_infosTowerElement,t_infosTowerFireRate;
	public AngleString t_infosTowerCanTargetFly, t_infosTowerDamage, t_infosTowerCost;
	public AngleSpriteLayout bGoLayout,bNGoLayout;
	public AngleSprite bGo,bNGo;
	private boolean isPossible; /* enough money to add the tower ? */
	
	private int cashNeedForAddTower;
	
	/**
	 * The constructor
	 * @param font The font for the classic text 
	 * @param fontTitle The font for the title text
	 * @param fontMenuRed 
 	 * @param mGLSurfaceView The view
	 */
	public MenuNewTower(AngleFont font,AngleFont fontTitle, AngleSurfaceView mGLSurfaceView,LinkedList<Tower> listTower){
		this.t_infosTowerTitle = new AngleString(fontTitle,"Tower",160, 427, AngleString.aCenter);
		this.t_infosTowerElement = new AngleString(font,"",160,440,AngleString.aCenter);		
		this.t_infosTowerDamage = new AngleString(font,"",160,450,AngleString.aCenter);
		this.t_infosTowerFireRate = new AngleString(font,"",160,460,AngleString.aCenter);	
		this.t_infosTowerCost = new AngleString(font,"",85,460,AngleString.aCenter);
		
		listTower.get(0).changePosition(0, 416);
		listTower.get(0).getSprite().mAlpha = 0;

		listTower.get(1).changePosition(0, 448);
		listTower.get(1).getSprite().mAlpha = 0;
				
		listTower.get(2).changePosition(32, 416);
		listTower.get(2).getSprite().mAlpha = 0;
		
		listTower.get(3).changePosition(32, 448);
		listTower.get(3).getSprite().mAlpha = 0;
		
		this.bGoLayout = new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,128,160,32,32);
		this.bNGoLayout = new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,160,160,32,32); 
		this.bGo = new AngleSprite(this.bGoLayout);
		this.bGo.mPosition.set(85, 430);
		
		this.t_infosTowerTitle.mAlpha = 0;
		
		this.bGo.mAlpha=0;
		this.t_infosTowerElement.mAlpha = 0;
		this.t_infosTowerDamage.mAlpha = 0;
		this.t_infosTowerFireRate.mAlpha = 0;
		this.t_infosTowerCost.mAlpha = 0;
		mGLSurfaceView.addObject(this.t_infosTowerTitle);
		mGLSurfaceView.addObject(bGo);
		mGLSurfaceView.addObject(t_infosTowerElement);
		mGLSurfaceView.addObject(t_infosTowerDamage);
		mGLSurfaceView.addObject(t_infosTowerFireRate);
		mGLSurfaceView.addObject(t_infosTowerCost);	
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
			if(y > 416 && y < 448)		ret = 3;
			else if(y > 448 && y < 480)	ret = 4;
		}
		return ret;
	}
	/**
	 * This function hide the menu 
	 * @param mGLSurfaceView The view
	 */
	public void hide(AngleSurfaceView mGLSurfaceView,LinkedList<Tower> listTower){
		this.t_infosTowerTitle.mAlpha = 0;
		for (int i=0;i<listTower.size();i++) listTower.get(i).getSprite().mAlpha=0;
	}
	
	/**
	 * This function show the menu the towers
	 * @param mGLSurfaceView
	 */
	public void show(GenericGame g,LinkedList<Tower> listTower){
		this.t_infosTowerTitle.mAlpha = 1;
		for (int i=0;i<listTower.size();i++) listTower.get(i).getSprite().mAlpha=1;
		this.hideValidateTower(g); // if you want to show the selection, the validation must be hidden (for the moment !)
	}
	
	/**
	 * This function show the informations about the tower selected 
	 * @param tower
	 */
	public void showValidateTower(GenericGame g,Tower tower){
		this.cashNeedForAddTower=tower.getCost();
		this.isPossible=tower.getCost() <= g.getMoney();
		
		
		if (this.isPossible) 	{this.bGo.setLayout(this.bGoLayout);}
		else 	{
			this.bGo.setLayout(this.bNGoLayout);
			g.addObservateur(this);
		}
		this.bGo.mAlpha=1;
		
		this.t_infosTowerElement.mAlpha = 1;
		this.t_infosTowerDamage.mAlpha =1 ;
		this.t_infosTowerFireRate.mAlpha = 1;
		this.t_infosTowerCost.mAlpha = 1;
		
		this.t_infosTowerElement.set("Element : "+tower.getElement().toString());	
		this.t_infosTowerDamage.set("Damage : "+tower.getDamage());
		this.t_infosTowerFireRate.set("Shoot : "+tower.getFireRate());
		this.t_infosTowerCost.set(tower.getCost()+"$");
	}

	/**
	 * This function hide the tower selected by the user
	 * @param mGLSurfaceView
	 */
	public void hideValidateTower(GenericGame g){
		this.bGo.mAlpha=0;
		this.t_infosTowerElement.mAlpha = 0;
		this.t_infosTowerDamage.mAlpha = 0;
		this.t_infosTowerFireRate.mAlpha = 0;
		this.t_infosTowerCost.mAlpha = 0;
		g.delObservateur(this);
	}

	/**
	 * This function return true if the user touched the validation button
	 * @param x The x coordonate where the user touch the screen
	 * @param y The y coordonate where the user touch the screen
	 * @return True if the user wanted to add the tower
	 */
	public boolean isValidationTower(int x,int y){
		if ((x > 53 && x < 117 ) && (this.isPossible)){
			if (y > 414 && y < 446){
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
		this.isPossible=this.cashNeedForAddTower <= g.getMoney();
		if (this.isPossible) {
			this.bGo.setLayout(this.bGoLayout);
		}
	}
	@Override
	public void refreshScore(GenericGame g) {}
	@Override
	public void refreshWaves(GenericGame g) {}
}
