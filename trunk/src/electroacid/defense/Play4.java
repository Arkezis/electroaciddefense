package electroacid.defense;

import com.android.angle.AngleActivity;
import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleTileBank;
import com.android.angle.AngleTileMap;
import com.android.angle.AngleUI;


import electroacid.defense.box.Box;
import electroacid.defense.box.BoxBuildable;
import electroacid.defense.enums.Element;
import electroacid.defense.gui.Menu;
import electroacid.defense.gui.MenuNewTower;
import electroacid.defense.map.GenericMap;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class Play4 extends AngleActivity{

	/* ELEMENTS */
	public Element eFire = Element.Fire;
	public Element eElec = Element.Electricity;
	public Element eWater = Element.Water;    
	public Element eIron= Element.Iron;      

	/* TOWERS */
	public Tower tower1 , tower2,towerChoice;	

	/* TEXTURES */
	public AngleSpriteLayout buildableTexture,backgroundTexture,tower1Texture,tower2Texture, b_DeleteTexture;
	//public Font font;

	private AngleObject ogField;
	private AngleObject ogDashboard;
	private AngleTileMap tmGround;


	//public Text testText;

	/* Matrice */
	GenericMap matrice = new GenericMap(320, 416, 32, 32);
	
	

	/* SELECTED BOX MENU */
	AngleSpriteLayout _bnewTower1Layout ;
	AngleSprite _bnewTower1;
	public AngleSprite b_newTower1; // Sprite for the selectedBoxMenu
	AngleFont fontMenu,fontTitle;

	Menu menu;
	MenuNewTower menuNewTower;


	public BoxBuildable boxBuildableSelected=null; // the BB selected to add a new tower or something else
	public Game game;
	private MyGame myGame;
	public int choiceMenu;

	public class MyGame extends  AngleUI{
		long time= System.currentTimeMillis();
		float lastWave = 0;
		public MyGame(AngleActivity activity) {
			super(activity);
			ogField=new AngleObject();
			addObject(ogField);
			ogDashboard=new AngleObject();
			addObject(ogDashboard);

			AngleTileBank tbGround = new AngleTileBank(mActivity.mGLSurfaceView,R.drawable.tilemap,2,2,32,32);
			tmGround = new AngleTileMap(tbGround, 320, 416, 10, 13, false,false);
			
	
			try {
				matrice.buildMap(getWindow().getContext(),tmGround,R.raw.testmap);
			} catch (Exception e) {
				Log.d("testMAPXML","probleme with the xml");
				e.printStackTrace();
			}

			tower1 = new Tower(eFire,10,10,10,true,10,10,10,10,null);
			tower2 = new Tower(eFire,20,20,20,false,20,20,20,20,null);

			ogField.addObject(tmGround);		

			fontMenu = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"nasaliza.ttf"), 222, 0, 0, 30, 200, 255, 255);
			fontTitle = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"chintzy.ttf"), 222, 1, 0, 30, 200, 255, 255);

			
			
			_bnewTower1Layout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.tower1);
			_bnewTower1 = new AngleSprite(_bnewTower1Layout);
			_bnewTower1.mPosition.set(16, 16); 
			ogField.addObject(_bnewTower1);

			menu = new Menu(game,fontMenu,fontTitle,mGLSurfaceView);
			menuNewTower = new MenuNewTower(game,fontMenu,fontTitle,mGLSurfaceView);
		}
		
		public boolean onTouchEvent(MotionEvent event) {
			/* too much touch in one touch ! */
			if (System.currentTimeMillis() - time < 500){
				return true;
			}
			time = System.currentTimeMillis();

			int x = (int)event.getX();
			int y = (int)event.getY();
			Box box = matrice.getBox(x, y); /* the box touched */
			//BoxBuildable boxBuildapleMap = null;

			Log.d("DEBUGTAG","onTouch "+x+" "+y);

			/* -------------------- */
			/*   TOUCHING THE MAP   */
			/* -------------------- */
			if(y>0 && y<416){
				if(box instanceof BoxBuildable){
					boxBuildableSelected = (BoxBuildable) box; // BB touched 
					Log.d("DEBUGTAG", "In ("+x+","+y+"), there is a tower => "+boxBuildableSelected.getTower()+"???");	
					if(boxBuildableSelected.getTower() == null){
						menuNewTower.show(mGLSurfaceView);
					}else{
						menuNewTower.hide(mGLSurfaceView);
						//b_delete.setVisible(true);
					}
				}else{
					// it's not a BB, what to do ? 
					Log.d("DEBUGTAG", "Not a BB !");
				}
			}else{
			/* ------------------------ */
			/*    TOUCHING THE MENU     */
			/* ------------------------ */
				if(boxBuildableSelected != null){
					if(boxBuildableSelected.getTower() == null){ 
						choiceMenu = menuNewTower.getNewTowerFromMenuNewTower(x,y);
						/* Did the user confirm a new tower ? */
						if (menuNewTower.isValidationTower(x,y)){
							Log.d("DEBUGTAG", "Confirmation of new tower ! ");
							boxBuildableSelected.changeTower(towerChoice);
							menuNewTower.hideValidateTower(mGLSurfaceView);
							menuNewTower.hide(mGLSurfaceView);
						}else if(choiceMenu > 0){					
							/* Did the user choosen a tower in the menu ?  */
							towerChoice = null;
							switch(choiceMenu){
							case 1:
								Log.d("DEBUGTAG", "the choice "+choiceMenu);
								menuNewTower.showValidateTower(mGLSurfaceView, tower1);
								towerChoice = (Tower)tower1.clone();
								break;
							case 2:
								Log.d("DEBUGTAG", "the choice "+choiceMenu);
								menuNewTower.showValidateTower(mGLSurfaceView, tower2);
								towerChoice = (Tower)tower2.clone();
								break;
							}
							Log.d("DEBUGTAG", "new tower selected, the user need to confirm");
						}else{
							Log.d("DEBUGTAG", "The user touch the menu where there is nothing to touch ... stupid guy !");
							// The user touch the menu where there is nothing to touch ... stupid guy !
						}
					}else{
						/* A tower is already on this box ! */
						if(y >= 415 && y <= 465){
							if (x >= 5 && x <= 55){ 
								//rokon.removeSprite(boxBuildableSelected.getSprite());
								//boxBuildableSelected.removeTower();

							}
						}
					}
				}else{
					// We touched the menu, but nothing is in the menu because no box was selected before ! 
					// Nothing to do
					Log.d("DEBUGTAG", "We touched the menu, but nothing is in the menu because no box was selected before !");
				}
			}
			return true;					
		}
		
		@Override
		public void step(float secondsElapsed)
		{
			lastWave += secondsElapsed;
			if (lastWave > game.getTimeBetweenEachWave()){
				// RUN WAVE
				lastWave = 0;
			}
			
			super.step(secondsElapsed);
		}
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		game = new Game();

		FrameLayout mMainLayout=new FrameLayout(this);
		mMainLayout.addView(mGLSurfaceView);
		setContentView(mMainLayout);

		myGame=new MyGame(this);
		setUI(myGame);

	}



	public void onKey(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
	}



}


