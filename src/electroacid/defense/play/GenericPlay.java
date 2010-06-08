package electroacid.defense.play;

import java.util.LinkedList;

import android.graphics.Typeface;
import android.view.MotionEvent;

import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleTileBank;
import com.android.angle.AngleTileMap;
import com.android.angle.AngleUI;

import electroacid.defense.Game;
import electroacid.defense.R;
import electroacid.defense.Tower;
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

public class GenericPlay extends  AngleUI{
	
	
	/* TOWERS */
	/** The tower choosen to add*/
	public Tower towerChoice=null;
	/** The are where a tower can shoot*/
	public AngleSprite shootArea;
	
	/* TEXTURES */
	public AngleSpriteLayout fireAreaLayout,fireAreaLayout2;
	//AngleSpriteLayout  bnewTower2Layout,bnewTower1Layout;
	AngleSprite backgroundEndGame;
	private AngleObject ogField,ogWave,ogShoot,ogCreature,ogEndGame;

	/* Matrice */
	GenericMap matrice;
	GenericWave genericWave;
	GenericTower genericTower;
	

	/* MENU */
	AngleFont fontEndGame;
	Menu menu;
	MenuNewTower menuNewTower;
	MenuSelectedTower menuSelectedTower;
	
	BoxBuildable boxBuildableSelected=null;

	/** The list of towers on the game */
	LinkedList<BoxBuildable> towerList;
	/** Game's information */
	Game game;
	
	/** Boolean for the game */
	AngleSprite pointerNewTower;
	public AngleSprite endGameSprite;
	
	/** The variable to avoid a high touching map */
	long time= System.currentTimeMillis();
	
	/* Informations used in the step */
	float lastWave = 0;
	float timeBetweenEachTowerTurn=0;
	float lastRefreshMenu=0;
	
	GenericGame activity;
	
	/**
	 * The constructor
	 * @param activity The parent AngleActivity 
	 */
	public GenericPlay(GenericGame activity,int map,int wave,int tower) {
		super(activity);
		
		this.activity = activity;
		game = new Game();
		
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
		AngleTileBank tbGround = new AngleTileBank(mActivity.mGLSurfaceView,R.drawable.tilemap,18,9,32,32);
		AngleTileMap tmGround = new AngleTileMap(tbGround, 320, 416, 10, 13, false,false);
		ogField.addObject(tmGround);		
		try {
			matrice  = new GenericMap(13, 10, 32, 32,9);
			matrice.buildMap(activity.getWindow().getContext(),tmGround,map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		/* Read the waves' informations for this game */
		try {
			genericWave = new GenericWave(activity.mGLSurfaceView);
			genericWave.build(activity.getWindow().getContext(), wave,game);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		/* Read the tower' informations for this game */
		try {
			genericTower = new GenericTower();
			genericTower.build(activity.getWindow().getContext(),tower,activity.mGLSurfaceView);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		/* Menus' initialisation */
		AngleFont fontMenu = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(activity.getAssets(),"nasaliza"), 222, 0, 0, 30, 200, 255, 255);
		AngleFont fontTitle = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(activity.getAssets(),"chintzy.ttf"), 222, 1, 0, 30, 200, 255, 255);
		fontEndGame = new AngleFont(mActivity.mGLSurfaceView, 18, Typeface.createFromAsset(activity.getAssets(),"chintzy.ttf"), 555, 0, 2, 0, 0, 0, 255);

		menu = new Menu(game,fontMenu,fontTitle,activity.mGLSurfaceView);
		menuNewTower = new MenuNewTower(fontMenu,fontTitle,activity.mGLSurfaceView,genericTower.getListTower());
		menuSelectedTower = new MenuSelectedTower(fontMenu,fontTitle,activity.mGLSurfaceView);
	}
	
	/**
	 * The action to do when the user touch the screen
	 * @param event The event 
	 */
	public boolean onTouchEvent(MotionEvent event) {
		if(!game.isGameEnd()){
			/* To prevent a lot of touch on the screen */
			if (System.currentTimeMillis() - time < 100) return true;
			time = System.currentTimeMillis();

			int x = (int)event.getX();
			int y = (int)event.getY();

			if(y>0 && y<416) actionTouchingMap(matrice.getBox(x, y));
			else actionTouchingMenu(x,y);
		}
		return true;	
	}
	
	private void actionTouchingMap(Box box){
		if(box instanceof BoxBuildable){
			boxBuildableSelected = (BoxBuildable) box; 
			if(boxBuildableSelected.getTower() == null){
				menuNewTower.show(activity.mGLSurfaceView,genericTower.getListTower());
				menuSelectedTower.hide(activity.mGLSurfaceView);
				shootArea.mAlpha=0;
				pointerNewTower.mPosition.set(boxBuildableSelected.getX()+16,boxBuildableSelected.getY()+16);
				pointerNewTower.mAlpha=1;
				activity.mGLSurfaceView.addObject(pointerNewTower);						
			}else{
				menuNewTower.hide(activity.mGLSurfaceView,genericTower.getListTower());
				menuNewTower.hideValidateTower(activity.mGLSurfaceView);
				menuSelectedTower.show(activity.mGLSurfaceView,boxBuildableSelected.getTower(),game);
				/* Show the shootArea of the tower */
				if(boxBuildableSelected.getTower().getshootArea() == 2) shootArea.setLayout(fireAreaLayout2);
				else if(boxBuildableSelected.getTower().getshootArea() == 1) shootArea.setLayout(fireAreaLayout);
				shootArea.mPosition.set(boxBuildableSelected.getX()+16,boxBuildableSelected.getY()+16);
				activity.mGLSurfaceView.addObject(shootArea);
				shootArea.mAlpha = (float) 0.60;
				pointerNewTower.mPosition.set(boxBuildableSelected.getX()+16,boxBuildableSelected.getY()+16);
				pointerNewTower.mAlpha=1;
			}
		}else{
			menuSelectedTower.hide(activity.mGLSurfaceView);
			menuNewTower.hide(activity.mGLSurfaceView,genericTower.getListTower());
			shootArea.mAlpha=0;
			pointerNewTower.mAlpha=0;
		}
	}
	
	private void actionTouchingMenu(int x,int y){
		if(boxBuildableSelected != null){
			if(boxBuildableSelected.getTower() == null){ 
				int choiceMenu = menuNewTower.getNewTowerFromMenuNewTower(x,y);
				/* Did the user confirm a new tower ? */
				if (menuNewTower.isValidationTower(x,y)){
					if(boxBuildableSelected.changeTower(towerChoice,game,boxBuildableSelected.getX(),boxBuildableSelected.getY(),matrice)){
						ogField.addObject(boxBuildableSelected.getTower().getSprite());
						towerList.add(boxBuildableSelected);
						/* Hide unused stuff */
						menuNewTower.hideValidateTower(activity.mGLSurfaceView);
						menuNewTower.hide(activity.mGLSurfaceView,genericTower.getListTower());
						towerChoice = null;
						shootArea.mAlpha =0;
						pointerNewTower.mAlpha=0;
					}
				}else if(choiceMenu > 0 && choiceMenu<genericTower.getListTower().size()+1){	
					Tower tower= genericTower.getListTower().get(choiceMenu-1);
					menuNewTower.showValidateTower(game,activity.mGLSurfaceView, tower,true);
					towerChoice = (Tower)tower.clone();
		
					/* shootArea */
					if(towerChoice.getshootArea() == 2) shootArea.setLayout(fireAreaLayout2);
					else if(towerChoice.getshootArea() == 1) shootArea.setLayout(fireAreaLayout);
					shootArea.mPosition.set(boxBuildableSelected.getX()+16,boxBuildableSelected.getY()+16);
					shootArea.mAlpha = (float) 0.60;
					activity.mGLSurfaceView.addObject(shootArea); 
				}else{
					// The user has touched the menu where there is nothing to touch ... stupid guy !
				}
			}else{
				/* A tower is already on this box ! */
				if(menuSelectedTower.isUpgradedOrDeletedTower(x, y, boxBuildableSelected,game,ogField,towerList)){
					menuSelectedTower.hide(activity.mGLSurfaceView);
					shootArea.mAlpha=0;
					pointerNewTower.mAlpha=0;
				}
			}
		}else{
			// We touched the menu, but nothing is in the menu because no box was selected before ! Nothing to do
		}
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
					listWave.get(game.getActualWave()).start(menu,ogCreature,boxpath);
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
				menu.refresh(game,(int)lastWave);
			}
		}else{
			// Game finished
			ogEndGame=new AngleObject(); addObject(ogEndGame);
			AngleString t_textEndGame = new AngleString(fontEndGame,"",160, 208, AngleString.aCenter);
			endGameSprite.mAlpha=(float)0.03;
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
		fireAreaLayout = new AngleSpriteLayout(activity.mGLSurfaceView, 96, 96, R.drawable.tilemap,96,160,32,32);
		fireAreaLayout2 = new AngleSpriteLayout(activity.mGLSurfaceView, 160, 160, R.drawable.tilemap,96,160,32,32);
		endGameSprite = new AngleSprite(160,208,new AngleSpriteLayout(activity.mGLSurfaceView,320,416,R.drawable.tilemap,192,160,32,32));
		shootArea = new AngleSprite(fireAreaLayout);
		pointerNewTower = new AngleSprite(new AngleSpriteLayout(activity.mGLSurfaceView,32,32,R.drawable.tilemap,64,160,32,32));	
	}
	
}