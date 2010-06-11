package electroacid.defense;

import java.util.LinkedList;
import java.util.Random;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

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
import electroacid.defense.game.GenericGame;
import electroacid.defense.gui.Menu;
import electroacid.defense.gui.MenuNewTower;
import electroacid.defense.gui.MenuSelectedTower;
import electroacid.defense.gui.MenuStatsCreature;
import electroacid.defense.map.GenericMap;
import electroacid.defense.tower.GenericTower;
import electroacid.defense.wave.GenericWave;
import electroacid.defense.wave.Wave;

public class Play extends AngleActivity { 

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
    	MenuItem m1 = menu.add(0, 1, 0, "Parameters");
    	MenuItem m2 = menu.add(0,2,0,"High Scores");
    	MenuItem m3 = menu.add(0,3,0,"Instructions");
    	MenuItem m4 = menu.add(0,4,0,"About");
    	m1.setIcon(android.R.drawable.ic_menu_preferences);
    	m2.setIcon(android.R.drawable.ic_menu_agenda);
    	m3.setIcon(android.R.drawable.ic_menu_directions);
    	m4.setIcon(android.R.drawable.ic_menu_zoom);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    	case 1:
    		Toast.makeText(this, "This will be implemented... soon ! ", 2000).show();
    		break;
    	case 2:
    		Toast.makeText(this, "This will be implemented... soon ! ", 2000).show();
    		break;
    	case 3:
    		Toast.makeText(this, "This will be implemented... soon ! ", 2000).show();
    		break;
    	case 4:
    		Toast.makeText(this, "Game developed by Mathieu Deschamps (aka cilheo) and Tom Dubin (aka Arkezis). \n Contact : ElectroAcidDefense@gmail.com . \n All rights reserved.", 2000).show();
    		break;
    	}
    	return super.onOptionsItemSelected(item);
    }
	
	
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
	MenuStatsCreature menuStatsCreature;
	int choiceMenu;
	BoxBuildable boxBuildableSelected=null;		
	BoxPath boxPathSelected=null;


	/** The list of towers on the game */
	LinkedList<BoxBuildable> towerList;
	/** Game's information */
	GenericGame game;
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
			if(mapChoosen.equals("tutomap")){
				try {
					game = new GenericGame(getWindow().getContext(), R.raw.tutogame);
					matrice.buildMap(getWindow().getContext(),tmGround,R.raw.tutomap);
					genericWave = new GenericWave(mGLSurfaceView);
					genericWave.build(getWindow().getContext(), R.raw.tutowave,game);
					genericTower = new GenericTower();
					genericTower.build(getWindow().getContext(), R.raw.tower,mGLSurfaceView);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(mapChoosen.equals("map1")){
				try {
					game = new GenericGame(getWindow().getContext(), R.raw.game1game);
					matrice.buildMap(getWindow().getContext(),tmGround,R.raw.map1map);
					genericWave = new GenericWave(mGLSurfaceView);
					genericWave.build(getWindow().getContext(), R.raw.map1wave,game);
					genericTower = new GenericTower();
					genericTower.build(getWindow().getContext(), R.raw.tower,mGLSurfaceView);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(mapChoosen.equals("map2")){
				try {
					game=new GenericGame(getWindow().getContext(),R.raw.game2game);
					matrice.buildMap(getWindow().getContext(),tmGround,R.raw.map2map);
					genericWave = new GenericWave(mGLSurfaceView);
					genericWave.build(getWindow().getContext(), R.raw.map2wave,game);
					genericTower = new GenericTower();
					genericTower.build(getWindow().getContext(), R.raw.tower,mGLSurfaceView);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			game.setGameStarted(true);
			/* Menus' initialisation */
			fontMenu = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"nasaliza"), 222, 0, 0, 30, 200, 255, 255);
			fontTitle = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"chintzy.ttf"), 222, 1, 0, 30, 200, 255, 255);
			fontEndGame = new AngleFont(mActivity.mGLSurfaceView, 18, Typeface.createFromAsset(getAssets(),"chintzy.ttf"), 555, 0, 2, 0, 0, 0, 255);

			menu = new Menu(game,fontMenu,fontTitle,mGLSurfaceView);
			game.addObservateur(menu);
			menuNewTower = new MenuNewTower(fontMenu,fontTitle,mGLSurfaceView,genericTower.getListTower());
			menuSelectedTower = new MenuSelectedTower(fontMenu,fontTitle,mGLSurfaceView);
			menuStatsCreature = new MenuStatsCreature(fontMenu,fontTitle,mGLSurfaceView);
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
							menuStatsCreature.hide(mGLSurfaceView);
							shootArea.mAlpha=0;
							pointerNewTower.mPosition.set(boxBuildableSelected.getX()+16,boxBuildableSelected.getY()+16);
							pointerNewTower.mAlpha=1;
							mGLSurfaceView.addObject(pointerNewTower);						
						}else{
							menuStatsCreature.hide(mGLSurfaceView);
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
					}else{ // BoxPath
						menuSelectedTower.hide(mGLSurfaceView);
						menuNewTower.hide(mGLSurfaceView,genericTower.getListTower());
						menuNewTower.hideValidateTower(mGLSurfaceView);
						shootArea.mAlpha=0;
						pointerNewTower.mAlpha=0;
						boxPathSelected = (BoxPath) box;
						if(!boxPathSelected.getListCreature().isEmpty()){
							menuStatsCreature.show(mGLSurfaceView, boxPathSelected.getListCreature().get(0));
						}
					}
				}else{
				/* ------------------------ */
				/*    TOUCHING THE MENU     */
				/* ------------------------ */
					// Run the next wave ?
					if(menu.nextWaveButtonIsTouched(x,y)){
						if(game.getActualWave()<game.getNbMaxWave()){
							lastWave = game.getTimeBetweenEachWave();
							menu.refreshWaves(game);
						}
					}else if(boxBuildableSelected != null){
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
									menuStatsCreature.hide(mGLSurfaceView);
									towerChoice = null;
									shootArea.mAlpha =0;
									pointerNewTower.mAlpha=0;
								}else{
									/* Hide unused stuff */
									menuNewTower.hideValidateTower(mGLSurfaceView);
									menuNewTower.hide(mGLSurfaceView,genericTower.getListTower());
									menuStatsCreature.hide(mGLSurfaceView);
									towerChoice = null;
									shootArea.mAlpha =0;
									pointerNewTower.mAlpha=0;
								}
							}else if(choiceMenu > 0 && choiceMenu<genericTower.getListTower().size()+1){	
								Tower tower= genericTower.getListTower().get(choiceMenu-1);
								menuNewTower.showValidateTower(game,mGLSurfaceView, tower,(tower.getCost() < game.getMoney()));
								towerChoice = (Tower)tower.clone();
								menuStatsCreature.hide(mGLSurfaceView);
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
								menuStatsCreature.hide(mGLSurfaceView);
								shootArea.mAlpha=0;
								pointerNewTower.mAlpha=0;
							}
						}
					}else{
						// We touched the menu, but nothing to do !
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
			if(!game.isGameEnd() ){
				/* WAVES */
				if(game.getActualWave() == 0 && lastWave==0 ) { lastWave = game.getTimeBetweenEachWave()-10;}
				lastWave += secondsElapsed;
				
				if (lastWave > game.getTimeBetweenEachWave()){
					lastWave = 0;
					LinkedList<Wave> listWave = genericWave.getListWave();
					if (game.getActualWave()<listWave.size()){
						ogWave.addObject(listWave.get(game.getActualWave()));

						int whichEntry = new Random().nextInt(matrice.firstBoxPath.size());
						listWave.get(game.getActualWave()).start(ogCreature,matrice.firstBoxPath.get(whichEntry));
						game.setActualWave(game.getActualWave()+1);
					}
				}
				
				
				
				for (BoxPath first : matrice.firstBoxPath){
					first.nextStep(game, ogCreature);
				}
				/* SHOOTS */
				/* To manage tower shooting faster than other towers, we are using a counter incremented at each step.
				 * At each step, if (counter % fireRate == 0), the tower shoot !  
				 */
				
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
					menu.refresh(game,(int)lastWave);
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

		FrameLayout mMainLayout=new FrameLayout(this);
		mMainLayout.addView(mGLSurfaceView);
		setContentView(mMainLayout);

		Toast t = Toast.makeText(this, "Welcome to Electro Acid Defense ! ", 0);
		t.setGravity(Gravity.CENTER, 0, 0);
		t.show();
		
		myGame=new MyGame(this);
		setUI(myGame);
		if(mapChoosen.equals("tutomap")){
			t = Toast.makeText(this, "The game will start !  ", 0);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
			t = Toast.makeText(this, "Touch the map to select a place for your new tower  ", 0);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
			t = Toast.makeText(this, "Select the tower to create and validate it !", 0);
			t.setGravity(Gravity.BOTTOM, 0, 150);
			t.show();
			t = Toast.makeText(this, "Get ready, creatures are coming....", 0);
			t.setGravity(Gravity.BOTTOM, 0, 150);
			t.show();
		}
	}
	
	/**
	 * Managing some keys
	 * @param keyCode Code of the key pressed
	 * @param event Event generated
	 */
	public boolean onKey(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			finish();
			return true;
		}
		return false;
	}



}


