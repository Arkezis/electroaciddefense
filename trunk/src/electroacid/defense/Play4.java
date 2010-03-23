package electroacid.defense;

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
	public Tower tower1 , tower2;	

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
	AngleFont fntCafe25;
	
	
	

	public BoxBuildable boxBuildableSelected=null; // the BB selected to add a new tower or something else
	public Game game;
	private MyGame myGame;
	
	public class MyGame extends  AngleUI{

		public MyGame(AngleActivity activity) {
			super(activity);
			ogField=new AngleObject();
			addObject(ogField);
			ogDashboard=new AngleObject();
			addObject(ogDashboard);
			
			
			
			AngleTileBank tbGround = new AngleTileBank(mActivity.mGLSurfaceView,R.drawable.tilemap,2,2,32,32);
			tmGround = new AngleTileMap(tbGround, 320, 416, 15, 13, false,false);
			for (int t=0;t<tmGround.mColumnsCount*tmGround.mRowsCount;t++)
				tmGround.mMap[t]=0;
			ogField.addObject(tmGround);		
			
			fntCafe25 = new AngleFont(mActivity.mGLSurfaceView, 13, Typeface.createFromAsset(getAssets(),"chintzy.ttf"), 222, 0, 0, 30, 200, 255, 255);
			
			_bnewTower1Layout = new AngleSpriteLayout(mGLSurfaceView, 32, 32, R.drawable.tower1);
			_bnewTower1 = new AngleSprite(_bnewTower1Layout);
			_bnewTower1.mPosition.set(16, 16); 
			ogField.addObject(_bnewTower1);
			
		
			//The dashboard background
//			AngleSpriteLayout slDash = new AngleSpriteLayout(mActivity.mGLSurfaceView, 320, 64, R.drawable.tilemap, 0, 32, 320, 64);
//			AngleSprite mDash=new AngleSprite (slDash);
//			mDash.mPosition.set(160, 480-slDash.roHeight/2);
//			mDash.mAlpha=0.5f;
//			ogDashboard.addObject(mDash);
			Menu menuNewTower = new Menu(game,fntCafe25,mGLSurfaceView);
			
			
			
		}
		
		@SuppressWarnings("null")
		public boolean onTouchEvent(MotionEvent event) { 

			int x = (int)event.getX();
			int y = (int)event.getY();
			
			
			Log.d("DEBUGTAG","onTouch "+x+" "+y);

				BoxBuildable boxBuildapleMap = null;

				/* -------------------- */
				/*    MODIFICATIONS     */
				/* -------------------- */
				if(boxBuildableSelected != null){
					/* We had previously selected a boxBuildable */
					if(boxBuildableSelected.getTower() == null){ 
						/* Creating a new tower on a virgin box */
						if(y >= 415 && y < 465){
							if (x >= 5 && x < 55){ /* Adding Tower 1 */
								boxBuildableSelected.changeTower((Tower)tower1.clone());
								//rokon.addSprite(boxBuildableSelected.getSprite());
							}else if (x >= 55 && x < 105){ /* Adding Tower 2 */
								boxBuildableSelected.changeTower((Tower)tower2.clone());
								//rokon.addSprite(boxBuildableSelected.getSprite());
							}

						}
					}else{
						/* A tower is already on this box ! */
						if(y >= 415 && y <= 465){
							if (x >= 5 && x <= 55){ 
								
								//rokon.removeSprite(boxBuildableSelected.getSprite());
								boxBuildableSelected.removeTower();
								
							}
						}
					}
					/* Modifications done : show off the menus and reset boxBuildableSelected */
					/*b_delete.setVisible(false);
					b_newTower1.setVisible(false);
					b_newTower2.setVisible(false);*/
					boxBuildableSelected = null;
				}
				/* END OF MODIFICATIONS */


				/* -------------------- */
				/*   TOUCHING THE MAP   */
				/* -------------------- */
				Box box = matrice.getBox(x, y); 
				boxBuildapleMap = box instanceof BoxBuildable ? (BoxBuildable) box : null;
				Log.d("DEBUGTAG","The box is touched ");

				if(boxBuildapleMap != null){
					/* --------------------------------------- */
					/* A box was touched, which menu to show ? */
					/* --------------------------------------- */
					Log.d("DEBUGTAG", "In ("+x+","+y+"), there is a tower => "+boxBuildapleMap.getTower()+"???");	
					if(boxBuildapleMap.getTower() == null){
						/* If it's buildable and without tower, let's go to assignate a tower !! */
						MenuNewTower menuNewTower = new MenuNewTower(game,fntCafe25,mGLSurfaceView);
					}else{
						/* We have to display the informations about the tower constructed here */
						//b_delete.setVisible(true);
					}
					boxBuildableSelected = boxBuildapleMap; // we save which box was touched
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


