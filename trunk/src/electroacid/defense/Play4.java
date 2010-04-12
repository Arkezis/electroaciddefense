package electroacid.defense;

import com.android.angle.AngleActivity;
import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleTileBank;
import com.android.angle.AngleTileMap;
import com.android.angle.AngleUI;
import com.android.angle.AngleVector;


import electroacid.defense.box.Box;
import electroacid.defense.box.BoxBuildable;
import electroacid.defense.box.BoxPath;
import electroacid.defense.enums.Element;
import electroacid.defense.gui.Menu;
import electroacid.defense.gui.MenuNewTower;
import electroacid.defense.gui.MenuSelectedTower;
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
	public Tower tower1 , tower2,towerChoice=null;
	public AngleSprite fireArea;	
	public Creature creature1=null;
	
	/* TEXTURES */
	public AngleSpriteLayout buildableTexture,backgroundTexture,tower1Texture,tower2Texture, b_DeleteTexture, fireAreaLayout,fireAreaLayout2;
	//public Font font;

	private AngleObject ogField;
	private AngleObject ogDashboard;
	private AngleTileMap tmGround;
	private AngleObject ogCreature;

	
	private boolean test = false;

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
	MenuSelectedTower menuSelectedTower;


	public BoxBuildable boxBuildableSelected=null; // the BB selected to add a new tower or something else
	public Game game;
	private MyGame myGame;
	public int choiceMenu;

	public class MyGame extends  AngleUI{
		long time= System.currentTimeMillis();
		float lastWave = 0;
		float lastRefreshMenu;
		public MyGame(AngleActivity activity) {
			super(activity);
			ogField=new AngleObject();
			addObject(ogField);
			ogDashboard=new AngleObject();
			addObject(ogDashboard);
			ogCreature=new AngleObject();
			addObject(ogCreature);
			// TODO : passer le menu sur ogDashboard
			
			this.createTowerForMenu();
			

			AngleTileBank tbGround = new AngleTileBank(mActivity.mGLSurfaceView,R.drawable.tilemap,2,2,32,32);
			tmGround = new AngleTileMap(tbGround, 320, 416, 10, 13, false,false);
			
			
			AngleSpriteLayout bnewTower1Layout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.creature1);
			creature1 = new Creature(eFire,10,10,10,10,true,bnewTower1Layout);
			
	
			try {
				matrice.buildMap(getWindow().getContext(),tmGround,R.raw.testmap);
			} catch (Exception e) {
				Log.d("testMAPXML","probleme with the xml");
				e.printStackTrace();
			}

			ogField.addObject(tmGround);		

			fontMenu = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"nasaliza.ttf"), 222, 0, 0, 30, 200, 255, 255);
			fontTitle = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"chintzy.ttf"), 222, 1, 0, 30, 200, 255, 255);
			
			menu = new Menu(game,fontMenu,fontTitle,mGLSurfaceView);
			menuNewTower = new MenuNewTower(game,fontMenu,fontTitle,mGLSurfaceView);
			menuSelectedTower = new MenuSelectedTower(game,fontMenu,fontTitle,mGLSurfaceView);
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
						menuSelectedTower.hide(mGLSurfaceView);
						fireArea.mAlpha =0;
					}else{
						menuNewTower.hide(mGLSurfaceView);
						menuNewTower.hideValidateTower(mGLSurfaceView);
						menuSelectedTower.show(mGLSurfaceView,boxBuildableSelected.getTower(),game);
						/* Show the area of the tower */
						if(boxBuildableSelected.getTower().getBoxArea() == 2) fireArea.setLayout(fireAreaLayout2);
						else if(boxBuildableSelected.getTower().getBoxArea() == 1) fireArea.setLayout(fireAreaLayout);
						fireArea.mPosition.set(boxBuildableSelected.getY()+16, boxBuildableSelected.getX()+16);
						mGLSurfaceView.addObject(fireArea);
						fireArea.mAlpha = (float) 0.60;
					}
				}else{
					// it's not a BB, what to do ? 
					Log.d("DEBUGTAG", "Not a BB !");
					menuSelectedTower.hide(mGLSurfaceView);
					menuNewTower.hide(mGLSurfaceView);
					fireArea.mAlpha =0;
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
							if(boxBuildableSelected.changeTower(towerChoice,game)){
								menuNewTower.hideValidateTower(mGLSurfaceView);
								menuNewTower.hide(mGLSurfaceView);
								ogField.addObject(boxBuildableSelected.getTower().getSprite());
								towerChoice = null;
								fireArea.mAlpha =0;
								/* =============================  Fire testing ! ============================= */
								
								AngleSpriteLayout fireSpriteLayout = new AngleSpriteLayout(mGLSurfaceView, 4, 64, R.drawable.fireblack);
								AngleSprite fireSprite = new AngleSprite(fireSpriteLayout);
								fireSprite.mPosition.set(150, 150); 
								fireSprite.mPosition.rotate((float) 0.34); 
								
								
								ogField.addObject(fireSprite);
								
								
								/* ============================= End fire testing ============================= */
								
							}
						}else if(choiceMenu > 0){					
							/* Did the user choosen a tower in the menu ?  */
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
							if(towerChoice.getBoxArea() == 2) fireArea.setLayout(fireAreaLayout2);
							else if(towerChoice.getBoxArea() == 1) fireArea.setLayout(fireAreaLayout);
							fireArea.mPosition.set(boxBuildableSelected.getY()+16, boxBuildableSelected.getX()+16);
							mGLSurfaceView.addObject(fireArea);
							fireArea.mAlpha = (float) 0.60;
							Log.d("DEBUGTAG", "new tower selected, the user need to confirm");
						}else{
							Log.d("DEBUGTAG", "The user touch the menu where there is nothing to touch ... stupid guy !");
							// The user touch the menu where there is nothing to touch ... stupid guy !
						}
					}else{
						/* A tower is already on this box ! */
						if(menuSelectedTower.isUpgradedOrDeletedTower(x, y, boxBuildableSelected,game,ogField)){
							menuSelectedTower.hide(mGLSurfaceView);
							fireArea.mAlpha =0;
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
			BoxPath boxpath = (BoxPath) matrice.getBox(0, 32);
			if (lastWave > game.getTimeBetweenEachWave()){
				// RUN WAVE
				test = false;
				lastWave = 0;
				
				if (!test) {
					Creature creatureToAdd = (Creature) creature1.clone();
				
					boxpath.addCreature(creatureToAdd);
					creatureToAdd.getSprite().mPosition.set(
							boxpath.getY(), 
							boxpath.getX());
					ogCreature.addObject(creatureToAdd.getSprite());
					test = true;
				}
			}
			
			boxpath.nextStep(game,ogCreature);
			
			
			
			lastRefreshMenu += secondsElapsed;
			if(lastRefreshMenu > game.getMenuRefreshTime()) {
				menu.refresh(game);
				lastRefreshMenu = 0;
				//Log.d("DEBUGTAGMenu", "Menu refresh");
			}
			super.step(secondsElapsed);
		}
		
		
		private void createTowerForMenu(){
			
			AngleSpriteLayout bnewTower1Layout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.tower1);
			AngleSpriteLayout bnewTower2Layout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.tower2);
			fireAreaLayout = new AngleSpriteLayout(mGLSurfaceView, 96, 96, R.drawable.firearea);
			fireAreaLayout2 = new AngleSpriteLayout(mGLSurfaceView, 160, 160, R.drawable.firearea);
			tower1 = new Tower(eFire,10,10,10,true,10,10,10,10,bnewTower1Layout,2);
			tower2 = new Tower(eIron,50,50,50,false,50,50,50,50,bnewTower2Layout,1);
			fireArea = new AngleSprite(fireAreaLayout);
			
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


