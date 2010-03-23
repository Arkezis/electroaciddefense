package electroacid.defense.gui;

import android.util.Log;

import com.android.angle.AngleFont;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;

import electroacid.defense.Game;
import electroacid.defense.R;

public class MenuNewTower {
	private AngleString t_infosPlayerTitre;
	AngleSpriteLayout bnewTower1Layout,bnewTower2Layout;
	AngleSprite bnewTower1,bnewTower2;
	public MenuNewTower(Game game, AngleFont font, AngleSurfaceView mGLSurfaceView){
		this.t_infosPlayerTitre = new AngleString(font);
		this.t_infosPlayerTitre.set("Tower");
		this.t_infosPlayerTitre.mAlignment = AngleString.aCenter;
		this.t_infosPlayerTitre.mPosition.set(150, 430); 
		
		this.bnewTower1Layout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.tower1);
		this.bnewTower1 = new AngleSprite(this.bnewTower1Layout);
		this.bnewTower1.mPosition.set(16, 432); 

		this.bnewTower2Layout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.tower2);
		this.bnewTower2 = new AngleSprite(this.bnewTower2Layout);
		this.bnewTower2.mPosition.set(16, 464); 
	}
	
	public  int createNewTowerFromMenuNewTower(int x,int y){
		Log.d("DEBUGTAG", "X, Y : ("+x+","+y+")");
		if(x > 0 && x < 16){
			if(y > 416 && y < 448){
				Log.d("DEBUGTAG", "TOWER 1  : ("+x+","+y+")");
				return 1;
			}
		}else if(x > 0 && x < 16){
			if(y > 448 && y < 480){
				Log.d("DEBUGTAG", "TOWER 2  : ("+x+","+y+")");

				return 2;
			}		
		}
		return 0;
	}

	public void hide(AngleSurfaceView mGLSurfaceView){
		Log.d("DEBUGTAG", "HIDDING NewMenu");

		mGLSurfaceView.removeObject(this.t_infosPlayerTitre);	
		mGLSurfaceView.removeObject(bnewTower1);
		mGLSurfaceView.removeObject(bnewTower2);

	}
	
	public void show(AngleSurfaceView mGLSurfaceView){
		Log.d("DEBUGTAG", "SHOWING NewMenu");
		mGLSurfaceView.addObject(this.t_infosPlayerTitre);
		mGLSurfaceView.addObject(bnewTower1);
		mGLSurfaceView.addObject(bnewTower2);
	}
	
}
