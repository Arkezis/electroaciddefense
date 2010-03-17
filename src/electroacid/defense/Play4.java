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

		/* Textures */
		atlas = new TextureAtlas(512, 1024);
		atlas.insert(backgroundTexture = new Texture("graphics/backgrounds/Map01.png"));

		atlas.insert(tower1Texture = new Texture("graphics/tower/tower1.png"));
		atlas.insert(tower2Texture = new Texture("graphics/tower/tower2.png"));
		atlas.insert(b_DeleteTexture = new Texture("graphics/icons/delete.png"));
		TextureManager.load(atlas);

		/* Towers */
		tower1 = new Tower(this.eElec,10,10,10,10,false,10,10,10,10,tower1Texture);
		tower2 = new Tower(this.eFire,20,20,20,20,false,20,20,20,20,tower2Texture);

		background = new FixedBackground(backgroundTexture);
	}

	@Override
	public void onLoadComplete() {
		rokon.setBackground(background);

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
							boxBuildableSelected.changeTower(tower1);
							rokon.addSprite(boxBuildableSelected.getSprite());
						}else if (x >= 300 && x < 350){ /* Adding Tower 2 */
							boxBuildableSelected.changeTower(tower2);
							rokon.addSprite(boxBuildableSelected.getSprite());
						}

					}
				}else{
					/* A tower is already on this box ! */
					if(y >= 440 && y <= 480){
						if (x >= 250 && x <= 300){ 
							rokon.removeSprite(boxBuildableSelected.getSprite());
							boxBuildableSelected.removeTower();
							
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


