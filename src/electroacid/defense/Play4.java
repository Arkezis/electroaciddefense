package electroacid.defense;

import com.stickycoding.Rokon.Debug;
import com.stickycoding.Rokon.Hotspot;
import com.stickycoding.Rokon.RokonActivity;
import com.stickycoding.Rokon.Sprite;
import com.stickycoding.Rokon.Texture;
import com.stickycoding.Rokon.TextureAtlas;
import com.stickycoding.Rokon.TextureManager;
import com.stickycoding.Rokon.Backgrounds.FixedBackground;
import com.stickycoding.Rokon.Menu.Menu;
import com.stickycoding.Rokon.Menu.MenuObject;
import com.stickycoding.Rokon.Menu.Objects.MenuButton;
import com.stickycoding.RokonExamples.Example14.MyMenu;
import com.stickycoding.RokonExamples.Example14.MyMenu2;

import electroacid.defense.box.Box;
import electroacid.defense.box.BoxBuildable;
import electroacid.defense.enums.Element;
import electroacid.defense.gui.MatriceBox;

import android.app.*;
import android.content.Context;
import android.os.*;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class Play4 extends RokonActivity{

	/* ELEMENTS */
	public Element eFire = Element.Fire;
	public Element eElec = Element.Electricity;
	public Element eWater = Element.Water;    
	public Element eIron= Element.Iron;      

	/* TOWERS */
	public Tower tower1 , tower2;	

	/* TEXTURES */
	public Texture buildableTexture,backgroundTexture,tower1Texture,tower2Texture, b_DeleteTexture;
	public FixedBackground background;
	public TextureAtlas atlas;


	/* Matrice */
	MatriceBox matrice = new MatriceBox(320, 400, 20, 20);

	/* SELECTED BOX MENU */
	public Sprite b_newTower1, b_newTower2, b_delete; // Sprite for the selectedBoxMenu
	public Hotspot b_newTower1Hotspot,b_newTower2Hotspot, b_deleteHotspot; // hotspot for the selectedBoxMenu


	public BoxBuildable boxBuildableSelected=null; // the BB selected to add a new tower or something else


	public void onCreate() {
		createEngine(320, 480, false);
		
	}


	public void onLoad() {

		/* Towers */
		tower1 = new Tower(this.eElec,10,10,10,10,false,10,10,10,10);
		tower2 = new Tower(this.eFire,20,20,20,20,false,20,20,20,20);

		/* Textures */
		atlas = new TextureAtlas(512, 1024);
		atlas.insert(backgroundTexture = new Texture("graphics/backgrounds/Map01.png"));

		atlas.insert(tower1Texture = new Texture("graphics/tower/tower1.png"));
		atlas.insert(tower2Texture = new Texture("graphics/tower/tower2.png"));
		atlas.insert(b_DeleteTexture = new Texture("graphics/icons/delete.png"));
		TextureManager.load(atlas);



		background = new FixedBackground(backgroundTexture);
	}

	@Override
	public void onLoadComplete() {
		rokon.setBackground(background);

		/* Matrix */
		//		rokon.addSprite(bb1.getSpr());rokon.addHotspot(bb1.getHpt());
		//		rokon.addSprite(bb2.getSpr());rokon.addHotspot(bb2.getHpt());
		//		rokon.addSprite(bb3.getSpr());rokon.addHotspot(bb3.getHpt());
		//		rokon.addSprite(bb4.getSpr());rokon.addHotspot(bb4.getHpt());
		//		rokon.addSprite(bb5.getSpr());rokon.addHotspot(bb5.getHpt());
		//		rokon.addSprite(bb6.getSpr());rokon.addHotspot(bb6.getHpt());
		//		rokon.addSprite(bb7.getSpr());rokon.addHotspot(bb7.getHpt());
		//		rokon.addSprite(bb8.getSpr());rokon.addHotspot(bb8.getHpt());
		//		rokon.addSprite(bb9.getSpr());rokon.addHotspot(bb9.getHpt());
		//		rokon.addSprite(bb10.getSpr());rokon.addHotspot(bb10.getHpt());
		//		rokon.addSprite(bb11.getSpr());rokon.addHotspot(bb11.getHpt());
		//		rokon.addSprite(bb12.getSpr());rokon.addHotspot(bb12.getHpt());
		//		rokon.addSprite(bb13.getSpr());rokon.addHotspot(bb13.getHpt());
		//		rokon.addSprite(bb14.getSpr());rokon.addHotspot(bb14.getHpt());
		//		rokon.addSprite(bb15.getSpr());rokon.addHotspot(bb15.getHpt());
		//		rokon.addSprite(bb16.getSpr());rokon.addHotspot(bb16.getHpt());
		//		bb1.getSpr().setVisible(true);bb2.getSpr().setVisible(true);
		//		bb3.getSpr().setVisible(true);bb4.getSpr().setVisible(true);
		//		bb5.getSpr().setVisible(true);bb6.getSpr().setVisible(true);
		//		bb7.getSpr().setVisible(true);bb8.getSpr().setVisible(true);
		//		bb9.getSpr().setVisible(true);bb10.getSpr().setVisible(true);
		//		bb11.getSpr().setVisible(true);bb12.getSpr().setVisible(true);
		//		bb13.getSpr().setVisible(true);bb14.getSpr().setVisible(true);
		//		bb15.getSpr().setVisible(true);bb16.getSpr().setVisible(true);

		/* Selected box menu */		
		/* Menu to add the new towers */
		b_newTower1 = new Sprite(440,300,tower1Texture);b_newTower2 = new Sprite(440,300,tower2Texture);
		rokon.addSprite(b_newTower1);rokon.addSprite(b_newTower2);
		rokon.addHotspot(b_newTower1Hotspot = new Hotspot(b_newTower1));rokon.addHotspot(b_newTower2Hotspot= new Hotspot(b_newTower2));
		b_newTower1.setVisible(false);b_newTower2.setVisible(false);
		/* Menu to modify the towers already created */
		b_delete = new Sprite(440,300,b_DeleteTexture);rokon.addSprite(b_delete);
		rokon.addHotspot(b_deleteHotspot = new Hotspot(b_delete));
		b_delete.setVisible(false);
		
		
		Log.d("DEBUGTAG", "Everything is loaded ?");
	}

	@Override
	public void onHotspotTouch(Hotspot h){
		Log.d("DEBUGTAG", "HOTSPOT : ("+h.x +")"+h.y+")");
	}

	public void onTouch(int x,int y, boolean h) { 

		Log.d("DEBUGTAG","onTouch "+x+" "+y+" "+h);

			BoxBuildable boxBuildapleMap = null;
			Log.d("DEBUGTAG", "HOTSPOT : ("+x +")"+y+"). so, which one ??? :p");

			/* -------------------- */
			/*    MODIFICATIONS     */
			/* -------------------- */
			if(boxBuildableSelected != null){
				/* We had previously selected a boxBuildable */
				if(boxBuildableSelected.getTower() == null){ 
					/* Creating a new tower on a virgin box */
					if(y >= 440 && y < 480){
						if (x >= 250 && x < 300){ /* Adding Tower 1 */
							boxBuildableSelected.setTower(tower1);
							boxBuildableSelected.getSpr().setTexture(tower1Texture);
							rokon.addSprite(boxBuildableSelected.getSpr());
						}else if (x >= 300 && x < 350){ /* Adding Tower 2 */
							boxBuildableSelected.setTower(tower2);
							boxBuildableSelected.getSpr().setTexture(tower2Texture);
							rokon.addSprite(boxBuildableSelected.getSpr());
						}
						Log.d("DEBUGTAG", "We change the box "+boxBuildableSelected.getNumBox());
					}
				}else{
					/* A tower is already on this box ! */
					if(y >= 440 && y <= 480){
						if (x >= 250 && x <= 300){ 
							boxBuildableSelected.setTower(null);
							boxBuildableSelected.getSpr().setTexture(null);
						}
					}
				}
				/* Modifications done : show off the menus and reset boxBuildableSelected */
				b_delete.setVisible(false);
				b_newTower1.setVisible(false);
				b_newTower2.setVisible(false);
				boxBuildableSelected = null;
			}
			/* END OF MODIFICATIONS */


			/* -------------------- */
			/*   TOUCHING THE MAP   */
			/* -------------------- */
			Box box = this.matrice.getBox(x, y); 
			boxBuildapleMap = box instanceof BoxBuildable ? (BoxBuildable) box : null;

			if(boxBuildapleMap != null){
				/* --------------------------------------- */
				/* A box was touched, which menu to show ? */
				/* --------------------------------------- */
				Log.d("DEBUGTAG", "In ("+x+","+y+"), there is a tower => "+boxBuildapleMap.getTower()+"???");	
				if(boxBuildapleMap.getTower() == null){
					/* If it's buildable and without tower, let's go to assignate a tower !! */
					b_newTower1.setVisible(true);
					b_newTower2.setVisible(true);
				}else{
					/* We have to display the informations about the tower constructed here */
					b_delete.setVisible(true);
				}
				boxBuildableSelected = boxBuildapleMap; // we save which box was touched
			}
	}

	public void onKey(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
	}



}


