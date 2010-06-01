package electroacid.defense;

import java.util.LinkedList;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.android.angle.AngleActivity;
import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleTileBank;
import com.android.angle.AngleTileMap;
import com.android.angle.AngleUI;

import electroacid.defense.box.Box;
import electroacid.defense.box.BoxBuildable;
import electroacid.defense.box.BoxPath;
import electroacid.defense.gui.Menu;
import electroacid.defense.gui.MenuNewTower;
import electroacid.defense.gui.MenuSelectedTower;
import electroacid.defense.map.GenericMap;
import electroacid.defense.tower.GenericTower;
import electroacid.defense.wave.GenericWave;
import electroacid.defense.wave.Wave;

public class Play extends AngleActivity{ 

	/* TOWERS */
	/** The tower choosen to add*/
	public Tower towerChoice=null;
	/** The are where a tower can shoot*/
	public AngleSprite shootArea;
	/** Counter for the fireRate of the tower */
	public int counterFireRate=0;
	
	/* TEXTURES */
	public AngleSpriteLayout buildableTexture,backgroundTexture,tower1Texture,tower2Texture, b_DeleteTexture, fireAreaLayout,fireAreaLayout2,_bnewTower1Layout;
	AngleSprite backgroundEndGame;
	private AngleObject ogField,ogWave,ogShoot,ogCreature,ogEndGame;
	private AngleTileMap tmGround;

	/* Matrice */
	GenericMap matrice = new GenericMap(13, 10, 32, 32,9);
	GenericWave genericWave;
	GenericTower genericTower;
	

	/* MENU */
	AngleFont fontMenu,fontTitle,fontEndGame;
	Menu menu;
	MenuNewTower menuNewTower;
	MenuSelectedTower menuSelectedTower;
	int choiceMenu;
	BoxBuildable boxBuildableSelected=null;

	/** The list of towers on the game */
	LinkedList<BoxBuildable> towerList;
	/** Game's information */
	Game game;
	String mapChoosen;
	/** The AngleUI */
	MyGame myGame;
	/** Boolean for the game */
	
	public AngleString t_textEndGame;
	public AngleString t_textEndGame2;
	AngleSprite pointerNewTower;
	AngleSpriteLayout pointerNewTowerLayout;
	public AngleSprite endGameSprite;
	
	public class MyGame extends  AngleUI{
		/** The variable to avoid a high touching map */
		long time= System.currentTimeMillis();
		
		/* Informations used in the step */
		float lastWave = 0;
		float timeBetweenEachTowerTurn=0;
		float lastRefreshMenu=0;
		
		/**
		 * The constructor
		 * @param activity The parent AngleActivity 
		 */
		public MyGame(AngleActivity activity) {
			super(activity);
			game.setGameStarted(true);
			ogField=new AngleObject(); addObject(ogField);
			ogCreature=new AngleObject(); addObject(ogCreature);
			ogShoot = new AngleObject(); addObject(ogShoot);
			ogWave = new AngleObject(); addObject(ogWave);

			// TODO : passer le menu sur ogDashboard	
			
			/* Initialisation */
			towerList = new LinkedList<BoxBuildable>();
			this.createTowerForMenu();
			
			/* Create the map, the waves and the towers */
			AngleTileBank tbGround = new AngleTileBank(mActivity.mGLSurfaceView,R.drawable.tilemap,18,9,32,32);
			tmGround = new AngleTileMap(tbGround, 320, 416, 10, 13, false,false);
			ogField.addObject(tmGround);
			if(mapChoosen.equals("testmap")){
				try {
					matrice.buildMap(getWindow().getContext(),tmGround,R.raw.testmap);
					genericWave = new GenericWave(mGLSurfaceView);
					genericWave.build(getWindow().getContext(), R.raw.testwave,game);
					genericTower = new GenericTower();
					genericTower.build(getWindow().getContext(), R.raw.testtower,mGLSurfaceView);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(mapChoosen.equals("map1")){
				/*try {
					matrice.buildMap(getWindow().getContext(),tmGround,R.raw.testmap);
					genericWave = new GenericWave(mGLSurfaceView);
					genericWave.build(getWindow().getContext(), R.raw.testwave,game);
					matrice.buildMap(getWindow().getContext(),tmGround,R.raw.testmap);
					genericTower = new GenericTower();
					genericTower.build(getWindow().getContext(), R.raw.testtower,mGLSurfaceView);
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
			
			/* Menus' initialisation */
			fontMenu = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"nasaliza"), 222, 0, 0, 30, 200, 255, 255);
			fontTitle = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"chintzy.ttf"), 222, 1, 0, 30, 200, 255, 255);
			fontEndGame = new AngleFont(mActivity.mGLSurfaceView, 18, Typeface.createFromAsset(getAssets(),"chintzy.ttf"), 555, 0, 2, 0, 0, 0, 255);

			menu = new Menu(game,fontMenu,fontTitle,mGLSurfaceView);
			menuNewTower = new MenuNewTower(fontMenu,fontTitle,mGLSurfaceView,genericTower.getListTower());
			menuSelectedTower = new MenuSelectedTower(fontMenu,fontTitle,mGLSurfaceView);
		}
		
		/**
		 * The action to do when the user touch the screen
		 * @param event The event 
		 */
		public boolean onTouchEvent(MotionEvent event) {
			if(!game.isGameEnd()){
				/* To prevent a lot of touch on the screen */
				if (System.currentTimeMillis() - time < 100){
					return true;
				}
				time = System.currentTimeMillis();
	
				
				int x = (int)event.getX();
				int y = (int)event.getY();
				Box box = matrice.getBox(x, y); /* The box touched */
	
				/* -------------------- */
				/*   TOUCHING THE MAP   */
				/* -------------------- */
				if(y>0 && y<416){
					if(box instanceof BoxBuildable){
						boxBuildableSelected = (BoxBuildable) box; 
						if(boxBuildableSelected.getTower() == null){
							menuNewTower.show(mGLSurfaceView,genericTower.getListTower());
							menuSelectedTower.hide(mGLSurfaceView);
							shootArea.mAlpha=0;
							pointerNewTower.mPosition.set(boxBuildableSelected.getX()+16,boxBuildableSelected.getY()+16);
							pointerNewTower.mAlpha=1;
							mGLSurfaceView.addObject(pointerNewTower);						
						}else{
							menuNewTower.hide(mGLSurfaceView,genericTower.getListTower());
							menuNewTower.hideValidateTower(mGLSurfaceView);
							menuSelectedTower.show(mGLSurfaceView,boxBuildableSelected.getTower(),game);
							/* Show the shootArea of the tower */
							if(boxBuildableSelected.getTower().getshootArea() == 2) shootArea.setLayout(fireAreaLayout2);
							else if(boxBuildableSelected.getTower().getshootArea() == 1) shootArea.setLayout(fireAreaLayout);
							shootArea.mPosition.set(boxBuildableSelected.getX()+16,boxBuildableSelected.getY()+16);
							mGLSurfaceView.addObject(shootArea);
							shootArea.mAlpha = (float) 0.60;
							pointerNewTower.mPosition.set(boxBuildableSelected.getX()+16,boxBuildableSelected.getY()+16);
							pointerNewTower.mAlpha=1;
						}
					}else{
						menuSelectedTower.hide(mGLSurfaceView);
						menuNewTower.hide(mGLSurfaceView,genericTower.getListTower());
						shootArea.mAlpha=0;
						pointerNewTower.mAlpha=0;
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
								if(boxBuildableSelected.changeTower(towerChoice,game,boxBuildableSelected.getX(),boxBuildableSelected.getY(),matrice)){
									ogField.addObject(boxBuildableSelected.getTower().getSprite());
									towerList.add(boxBuildableSelected);
									/* Hide unused stuff */
									menuNewTower.hideValidateTower(mGLSurfaceView);
									menuNewTower.hide(mGLSurfaceView,genericTower.getListTower());
									towerChoice = null;
									shootArea.mAlpha =0;
									pointerNewTower.mAlpha=0;
								}
							}else if(choiceMenu > 0 && choiceMenu<genericTower.getListTower().size()+1){	
								Tower tower= genericTower.getListTower().get(choiceMenu-1);
								menuNewTower.showValidateTower(mGLSurfaceView, tower);
								towerChoice = (Tower)tower.clone();
					
								/* shootArea */
								if(towerChoice.getshootArea() == 2) shootArea.setLayout(fireAreaLayout2);
								else if(towerChoice.getshootArea() == 1) shootArea.setLayout(fireAreaLayout);
								shootArea.mPosition.set(boxBuildableSelected.getX()+16,boxBuildableSelected.getY()+16);
								shootArea.mAlpha = (float) 0.60;
								mGLSurfaceView.addObject(shootArea); 
							}else{
								// The user has touched the menu where there is nothing to touch ... stupid guy !
							}
						}else{
							/* A tower is already on this box ! */
							if(menuSelectedTower.isUpgradedOrDeletedTower(x, y, boxBuildableSelected,game,ogField,towerList)){
								menuSelectedTower.hide(mGLSurfaceView);
								shootArea.mAlpha=0;
								pointerNewTower.mAlpha=0;
							}
						}
					}else{
						// We touched the menu, but nothing is in the menu because no box was selected before ! Nothing to do
					}
				}
			}
			return true;	
		}
		
		/**
		 * Called every frame 
		 * @param secondsElapsed Seconds elapsed since last frame
		 */
		@Override
		public void step(float secondsElapsed)
		{
			if(!game.isGameEnd()){
				/* WAVES */
				lastWave += secondsElapsed;
				BoxPath boxpath = matrice.firstBoxPath;
				
				if (lastWave > game.getTimeBetweenEachWave()){
					lastWave = 0;
					LinkedList<Wave> listWave = genericWave.getListWave();
					if (game.getActualWave()<listWave.size()){
						ogWave.addObject(listWave.get(game.getActualWave()));
						listWave.get(game.getActualWave()).start(ogCreature,boxpath);
						game.setActualWave(game.getActualWave()+1);
					}
	
				}
				/* SHOOTS */
				/* To manage tower shooting faster than other towers, we are using a counter incremented at each step.
				 * At each step, if (counter % fireRate == 0), the tower shoot !  
				 */
				boxpath.nextStep(game,ogCreature);
				timeBetweenEachTowerTurn += secondsElapsed;
				counterFireRate++; 
				if(timeBetweenEachTowerTurn > game.getTimeBetweenEachTowerTurn()){
					timeBetweenEachTowerTurn =0;
					for(int i=0;i<towerList.size();i++){
						if(towerList.get(i).getTower() != null){
							if(counterFireRate%towerList.get(i).getTower().getFireRate()==0){
								towerList.get(i).getTower().detection(ogShoot);
							}
						}
					}
				}
				/* MENUS */
				lastRefreshMenu += secondsElapsed;
				if(lastRefreshMenu > game.getMenuRefreshTime()) {
					lastRefreshMenu = 0;
					menu.refresh(game);
				}
			}else{
				// Game finished
				ogEndGame=new AngleObject(); addObject(ogEndGame);
				t_textEndGame = new AngleString(fontEndGame,"",160, 208, AngleString.aCenter);
				endGameSprite.mAlpha=(float)0.07;
				if(game.getLives()==0){
					t_textEndGame.set("Oh, you lose ! \n You've survived to \n only "+game.getActualWave()+" waves !");
				}else{ 
					t_textEndGame.set("Congratulations, \n you won the game ! \n You've survived to "+genericWave.getListWave().size()+" waves !");
				}
				ogEndGame.addObject(endGameSprite);
				ogEndGame.addObject(t_textEndGame);
			}
			super.step(secondsElapsed);
		}
		
		
		/**
		 * This method create the tower which will be used in the menu and to create the new tower (clone). The shootArea's Sprite is also created here
		 */
		private void createTowerForMenu(){
			fireAreaLayout = new AngleSpriteLayout(mGLSurfaceView, 96, 96, R.drawable.tilemap,96,160,32,32);
			fireAreaLayout2 = new AngleSpriteLayout(mGLSurfaceView, 160, 160, R.drawable.tilemap,96,160,32,32);
			endGameSprite = new AngleSprite(160,208,new AngleSpriteLayout(mGLSurfaceView,320,416,R.drawable.tilemap,96,160,32,32));
			shootArea = new AngleSprite(fireAreaLayout);
			pointerNewTower = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,64,160,32,32));	
		}
		
	}
	/**
	 * Called at the beginning of the activity
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Getting the informations about the game choosen previously */
		if(this.getIntent().getExtras() != null){
			mapChoosen = this.getIntent().getExtras().getString("map");
			//TODO Il faudrait vérifier que cette ressource existe bien ! 		
		}else finish();
			
		game = new Game();

		FrameLayout mMainLayout=new FrameLayout(this);
		mMainLayout.addView(mGLSurfaceView);
		setContentView(mMainLayout);

		myGame=new MyGame(this);
		setUI(myGame);

	}
	
	/**
	 * Managing some keys
	 * @param keyCode Code of the key pressed
	 * @param event Event generated
	 */
	public void onKey(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
	}



}


