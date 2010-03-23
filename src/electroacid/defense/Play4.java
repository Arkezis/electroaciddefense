package electroacid.defense;

import com.android.angle.AngleActivity;
import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;
import com.android.angle.AngleSurfaceView;
import com.android.angle.AngleTileBank;
import com.android.angle.AngleTileMap;
import com.android.angle.AngleUI;


import electroacid.defense.box.Box;
import electroacid.defense.box.BoxBuildable;
import electroacid.defense.enums.Element;
import electroacid.defense.gui.MatriceBox;
import electroacid.defense.gui.Menu;
import electroacid.defense.gui.MenuNewTower;

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
	MatriceBox matrice = new MatriceBox(320, 400, 20, 20);

	/* SELECTED BOX MENU */
	AngleSpriteLayout _bnewTower1Layout ;
	AngleSprite _bnewTower1;
	public AngleSprite b_newTower1; // Sprite for the selectedBoxMenu
	//public Hotspot b_newTower1Hotspot,b_newTower2Hotspot, b_deleteHotspot; // hotspot for the selectedBoxMenu
	AngleFont fontMenu,fontTitle;

	Menu menu;
	MenuNewTower menuNewTower;


	public BoxBuildable boxBuildableSelected=null; // the BB selected to add a new tower or something else
	public Game game;
	private MyGame myGame;
	public int choiceMenu;

	public class MyGame extends  AngleUI{

		public MyGame(AngleActivity activity) {
			super(activity);
			ogField=new AngleObject();
			addObject(ogField);
			ogDashboard=new AngleObject();
			addObject(ogDashboard);

			tower1 = new Tower(eFire,10,10,10,true,10,10,10,10,null);
			tower2 = new Tower(eFire,20,20,20,false,20,20,20,20,null);

			AngleTileBank tbGround = new AngleTileBank(mActivity.mGLSurfaceView,R.drawable.tilemap,2,2,32,32);
			tmGround = new AngleTileMap(tbGround, 320, 416, 15, 13, false,false);
			for (int t=0;t<tmGround.mColumnsCount*tmGround.mRowsCount;t++)
				tmGround.mMap[t]=0;
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
		long time= System.currentTimeMillis();
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
						//b_delete.setVisible(true);
					}
				}else{
					// it's not a BB, what to do ? 
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
							Log.d("DEBUGTAG", "new tower selecteddddddddd, the user need to confirm__"+choiceMenu+"__");
							switch(choiceMenu){
							case 1:
								//boxBuildableSelected.changeTower((Tower)tower1.clone());
								menuNewTower.showValidateTower(mGLSurfaceView, tower1);
								towerChoice = (Tower)tower1.clone();
								break;
							case 2:
								//boxBuildableSelected.changeTower((Tower)tower2.clone());
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


	/*
	@Override
	public void onLoadComplete() {
		rokon.setBackground(background);

		/* Selected box menu 
		/* Menu to add the new towers 
		b_newTower1 = new Sprite(5,415,tower1Texture);b_newTower2 = new Sprite(55,415,tower2Texture);
		rokon.addSprite(b_newTower1);rokon.addSprite(b_newTower2);
		rokon.addHotspot(b_newTower1Hotspot = new Hotspot(b_newTower1));rokon.addHotspot(b_newTower2Hotspot= new Hotspot(b_newTower2));
		b_newTower1.setVisible(false);b_newTower2.setVisible(false);
		/* Menu to modify the towers already created 
		b_delete = new Sprite(5,415,b_DeleteTexture);rokon.addSprite(b_delete);
		rokon.addHotspot(b_deleteHotspot = new Hotspot(b_delete));
		b_delete.setVisible(false);

		testText = new Text("Test !",font,80, 416,32);
		rokon.addText(testText);

		Log.d("DEBUGTAG", "Everything is loaded ?");
	}
	 */



	public void onKey(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
	}



}


