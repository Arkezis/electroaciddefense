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
import electroacid.defense.enums.Element;
import electroacid.defense.gui.Menu;
import electroacid.defense.gui.MenuNewTower;
import electroacid.defense.gui.MenuSelectedTower;
import electroacid.defense.map.GenericMap;
import electroacid.defense.wave.GenericWave;
import electroacid.defense.wave.Wave;

public class Play extends AngleActivity{

	/* ELEMENTS */
	/* Not implemented yet */
	public Element eFire = Element.Fire;
	public Element eElec = Element.Electricity;
	public Element eWater = Element.Water;    
	public Element eIron= Element.Iron;      

	/* TOWERS */
	/** The first kind of tower	 */
	public Tower tower1;
	/** The second kind of tower	 */
	public Tower tower2;
	/** The tower choosen to add*/
	public Tower towerChoice=null;
	/** The are where a tower can shoot*/
	public AngleSprite shootArea;
	
	/* TEXTURES */
	public AngleSpriteLayout buildableTexture,backgroundTexture,tower1Texture,tower2Texture, b_DeleteTexture, fireAreaLayout,fireAreaLayout2,_bnewTower1Layout;
	AngleSpriteLayout  bnewTower2Layout,bnewTower1Layout;
	AngleSprite backgroundEndGame;
	private AngleObject ogField,ogWave,ogShoot,ogCreature,ogEndGame;
	private AngleTileMap tmGround;

	/* Matrice */
	GenericMap matrice = new GenericMap(13, 10, 32, 32);
	GenericWave genericWave;
	

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
	/** The AngleUI */
	MyGame myGame;
	/** Boolean for the game */
	
	public AngleString t_textEndGame;
	public AngleString t_textEndGame2;
	AngleSprite pointerNewTower;
	AngleSpriteLayout pointerNewTowerLayout;

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
			// TODO : Passer le fireArea sur ogUtil + un sprite pour préciser la case pointée
			
			/* Initialisation */
			towerList = new LinkedList<BoxBuildable>();
			this.createTowerForMenu();
			
			/* Create the map */
			AngleTileBank tbGround = new AngleTileBank(mActivity.mGLSurfaceView,R.drawable.tilemap,2,2,32,32);
			tmGround = new AngleTileMap(tbGround, 320, 416, 10, 13, false,false);
			ogField.addObject(tmGround);		
			try {
				matrice.buildMap(getWindow().getContext(),tmGround,R.raw.testmap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			/* Read the waves' informations for this game */
			try {
				genericWave = new GenericWave(mGLSurfaceView);
				genericWave.build(getWindow().getContext(), R.raw.testwave,game);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			/* Menus' initialisation */
			fontMenu = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"nasaliza"), 222, 0, 0, 30, 200, 255, 255);
			fontTitle = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"chintzy.ttf"), 222, 1, 0, 30, 200, 255, 255);
			fontEndGame = new AngleFont(mActivity.mGLSurfaceView, 20, Typeface.createFromAsset(getAssets(),"chintzy.ttf"), 555, 0, 2, 0, 0, 0, 255);

			menu = new Menu(game,fontMenu,fontTitle,mGLSurfaceView);
			menuNewTower = new MenuNewTower(fontMenu,fontTitle,mGLSurfaceView);
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
							menuNewTower.show(mGLSurfaceView);
							menuSelectedTower.hide(mGLSurfaceView);
							shootArea.mAlpha=0;
							pointerNewTower.mPosition.set(boxBuildableSelected.getX()+16,boxBuildableSelected.getY()+16);
							pointerNewTower.mAlpha=1;
							mGLSurfaceView.addObject(pointerNewTower);						
						}else{
							menuNewTower.hide(mGLSurfaceView);
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
						menuNewTower.hide(mGLSurfaceView);
						shootArea.mAlpha=0;
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
									menuNewTower.hide(mGLSurfaceView);
									towerChoice = null;
									shootArea.mAlpha =0;
									pointerNewTower.mAlpha=0;
								}
							}else if(choiceMenu > 0){					
								/* Did the user has chosen a tower in the menu ?  */
								switch(choiceMenu){
								case 1:
									menuNewTower.showValidateTower(mGLSurfaceView, tower1);
									towerChoice = (Tower)tower1.clone();
									break;
								case 2:
									menuNewTower.showValidateTower(mGLSurfaceView, tower2);
									towerChoice = (Tower)tower2.clone();
									break;
								}							
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
			}else{
				// Game finished
				ogEndGame=new AngleObject(); addObject(ogEndGame);
				t_textEndGame = new AngleString(fontEndGame,"",160, 208, AngleString.aCenter);
				if(game.getActualWave() == genericWave.getListWave().size()){
					t_textEndGame.set("Congratulations, you won the game ! \n You've survived to "+genericWave.getListWave().size()+" waves !");
				}else{ 
					t_textEndGame.set("Oh, you lose ! \n You've survived to only "+genericWave.getListWave().size()+" waves !");
				}
				ogEndGame.addObject(t_textEndGame);
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
				boxpath.nextStep(game,ogCreature);
				timeBetweenEachTowerTurn += secondsElapsed;
				if(timeBetweenEachTowerTurn > game.getTimeBetweenEachTowerTurn()){
					timeBetweenEachTowerTurn =0;
					for(int i=0;i<towerList.size();i++){
						if(towerList.get(i).getTower() != null){
							towerList.get(i).getTower().detection(ogShoot);
						}
					}
				}
				/* MENUS */
				lastRefreshMenu += secondsElapsed;
				if(lastRefreshMenu > game.getMenuRefreshTime()) {
					lastRefreshMenu = 0;
					menu.refresh(game);
				}
				
				// Gérer la fin de toutes les Waves pour voir si la partie est finie

			}
			super.step(secondsElapsed);
		}
		
		
		/**
		 * This method create the tower which will be used in the menu and to create the new tower (clone). The shootArea's Sprite is also created here
		 */
		private void createTowerForMenu(){
			bnewTower1Layout = new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,0,128,32,32);
			bnewTower2Layout = new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,32,128,32,32);
			fireAreaLayout = new AngleSpriteLayout(mGLSurfaceView, 96, 96, R.drawable.tilemap,96,160,32,32);
			fireAreaLayout2 = new AngleSpriteLayout(mGLSurfaceView, 160, 160, R.drawable.tilemap,96,160,32,32);
			tower1 = new Tower(eFire,5,5,5,true,5,5,5,1,bnewTower1Layout,2);
			tower2 = new Tower(eIron,2,2,2,false,2,2,2,1,bnewTower2Layout,1);
			shootArea = new AngleSprite(fireAreaLayout);
			pointerNewTower = new AngleSprite(new AngleSpriteLayout(mGLSurfaceView,32,32,R.drawable.tilemap,64,160,32,32));
			
		}
		
	}
	/**
	 * Called at the beginning of the activity
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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


