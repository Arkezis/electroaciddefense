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

import android.app.*;
import android.content.Context;
import android.os.*;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class Play3 extends RokonActivity{

	/* ELEMENTS */
    public Element fire = new Element("Fire");
    public Element elec = new Element("Electricity");
    public Element water = new Element("Water",elec,fire);    
    public Element iron= new Element("Iron",fire,elec);       

    /* TOWERS */
    public Tower tower1 , tower2;	
    
    /* TEXTURES */
	public Texture buildableTexture,backgroundTexture, tower1BuildTexture,tower2BuildTexture,tower1Texture,tower2Texture, b_DeleteTexture;
	public FixedBackground background;
	public TextureAtlas atlas;
	
	/* MATRIX */
	public BoxBuildable bb1,bb2,bb3,bb4,bb5,bb6,bb7,bb8,bb9,bb10,bb11,bb12,bb13,bb14,bb15,bb16 ; // the matrix (where is neo ?)
	
	/* SELECTED BOX MENU */
	public Sprite b_newTower1, b_newTower2, b_delete; // Sprite for the selectedBoxMenu
	public Hotspot b_newTower1Hotspot,b_newTower2Hotspot, b_deleteHotspot; // hotspot for the selectedBoxMenu
	
	
	public BoxBuildable boxBuildableSelected=null; // the BB selected to add a new tower or something else
	

    public void onCreate() {
        createEngine(480, 320, true);
        
    }


	public void onLoad() {
	    /* Finishing creating the elements */
	    this.fire.setStrength(iron); this.fire.setWeakness(water);
	    this.elec.setStrength(water);this.elec.setWeakness(iron); 
	    
	    /* Towers */
		tower1 = new Tower(10,10,10,10,false,10,10,10,10);
		tower2 = new Tower(20,20,20,20,false,20,20,20,20);
		
	    /* Textures */
		atlas = new TextureAtlas(512, 1024);
		atlas.insert(buildableTexture =  new Texture("graphics/backgrounds/grass.png"));
		atlas.insert(tower1BuildTexture =  new Texture("graphics/sprites/tower1Cons.png"));
		atlas.insert(tower2BuildTexture =  new Texture("graphics/sprites/tower2Cons.png"));
		atlas.insert(backgroundTexture = new Texture("graphics/backgrounds/sky.png"));
		atlas.insert(tower1Texture = new Texture("graphics/tower/tower1.png"));
		atlas.insert(tower2Texture = new Texture("graphics/tower/tower2.png"));
		atlas.insert(b_DeleteTexture = new Texture("graphics/icons/delete.png"));
		TextureManager.load(atlas);
		
		/* Matrix */
		bb1 = new BoxBuildable(buildableTexture);
		bb2 = new BoxBuildable(buildableTexture);
		bb3 = new BoxBuildable(buildableTexture);
		bb4 = new BoxBuildable(tower2BuildTexture,tower2);
		bb5 = new BoxBuildable(buildableTexture);
		bb6 = new BoxBuildable(buildableTexture);
		bb7 = new BoxBuildable(buildableTexture);
		bb8 = new BoxBuildable(buildableTexture);
		bb9 = new BoxBuildable(tower1BuildTexture,tower1);
		bb10 = new BoxBuildable(tower1BuildTexture,tower1);
		bb11 = new BoxBuildable(buildableTexture);
		bb12 = new BoxBuildable(buildableTexture);
		bb13 = new BoxBuildable(buildableTexture);
		bb14 = new BoxBuildable(buildableTexture);
		bb15 = new BoxBuildable(buildableTexture);
		bb16 = new BoxBuildable(buildableTexture);
		
		background = new FixedBackground(backgroundTexture);
	}

	@Override
	public void onLoadComplete() {
		rokon.setBackground(background);
		
		/* Matrix */
		rokon.addSprite(bb1.getSpr());rokon.addHotspot(bb1.getHpt());
		rokon.addSprite(bb2.getSpr());rokon.addHotspot(bb2.getHpt());
		rokon.addSprite(bb3.getSpr());rokon.addHotspot(bb3.getHpt());
		rokon.addSprite(bb4.getSpr());rokon.addHotspot(bb4.getHpt());
		rokon.addSprite(bb5.getSpr());rokon.addHotspot(bb5.getHpt());
		rokon.addSprite(bb6.getSpr());rokon.addHotspot(bb6.getHpt());
		rokon.addSprite(bb7.getSpr());rokon.addHotspot(bb7.getHpt());
		rokon.addSprite(bb8.getSpr());rokon.addHotspot(bb8.getHpt());
		rokon.addSprite(bb9.getSpr());rokon.addHotspot(bb9.getHpt());
		rokon.addSprite(bb10.getSpr());rokon.addHotspot(bb10.getHpt());
		rokon.addSprite(bb11.getSpr());rokon.addHotspot(bb11.getHpt());
		rokon.addSprite(bb12.getSpr());rokon.addHotspot(bb12.getHpt());
		rokon.addSprite(bb13.getSpr());rokon.addHotspot(bb13.getHpt());
		rokon.addSprite(bb14.getSpr());rokon.addHotspot(bb14.getHpt());
		rokon.addSprite(bb15.getSpr());rokon.addHotspot(bb15.getHpt());
		rokon.addSprite(bb16.getSpr());rokon.addHotspot(bb16.getHpt());
		bb1.getSpr().setVisible(true);bb2.getSpr().setVisible(true);
		bb3.getSpr().setVisible(true);bb4.getSpr().setVisible(true);
		bb5.getSpr().setVisible(true);bb6.getSpr().setVisible(true);
		bb7.getSpr().setVisible(true);bb8.getSpr().setVisible(true);
		bb9.getSpr().setVisible(true);bb10.getSpr().setVisible(true);
		bb11.getSpr().setVisible(true);bb12.getSpr().setVisible(true);
		bb13.getSpr().setVisible(true);bb14.getSpr().setVisible(true);
		bb15.getSpr().setVisible(true);bb16.getSpr().setVisible(true);
		
		/* Selected box menu */		
		/* Menu to add the new towers */
		b_newTower1 = new Sprite(250,250,tower1Texture);b_newTower2 = new Sprite(300,250,tower2Texture);
		rokon.addSprite(b_newTower1);rokon.addSprite(b_newTower2);
		rokon.addHotspot(b_newTower1Hotspot = new Hotspot(b_newTower1));rokon.addHotspot(b_newTower2Hotspot= new Hotspot(b_newTower2));
		b_newTower1.setVisible(false);b_newTower2.setVisible(false);
		/* Menu to modify the towers already created */
		b_delete = new Sprite(250,250,b_DeleteTexture);rokon.addSprite(b_delete);
		rokon.addHotspot(b_deleteHotspot = new Hotspot(b_delete));
		b_delete.setVisible(false);
		
		Log.d("DEBUGTAG", "Everything is loaded ?");
	}

	@Override
	public void onHotspotTouch(Hotspot h){
		Log.d("DEBUGTAG", "HOTSPOT : ("+h.x +")"+h.y+")");
	}

	public void onTouch(int x,int y, boolean h) { 
		if (h) {
			BoxBuildable boxBuildapleMap = null;
			Log.d("DEBUGTAG", "HOTSPOT : ("+x +")"+y+"). so, which one ??? :p");
			
			/* -------------------- */
			/*    MODIFICATIONS     */
			/* -------------------- */
			if(boxBuildableSelected != null){
				if(boxBuildableSelected.getClass().toString().equalsIgnoreCase("class electroacid.defense.BoxBuildable")){
					/* We had previously selected a boxBuildable */
					if(boxBuildableSelected.getTower() == null){ 
						/* Creating a new tower on a virgin box */
						if(y > 250 && y < 300){
							if (x > 250 && x < 300){ /* Adding Tower 1 */
								boxBuildableSelected.setTower(tower1);
								boxBuildableSelected.getSpr().setTexture(tower1BuildTexture);
							}else if (x > 300 && x < 350){ /* Adding Tower 2 */
								boxBuildableSelected.setTower(tower2);
								boxBuildableSelected.getSpr().setTexture(tower2BuildTexture);
							}
							Log.d("DEBUGTAG", "We change the box "+boxBuildableSelected.getNumBox());
						}
					}else{
						/* A tower is already on this box ! */
						if(y > 250 && y < 300){
							if (x > 250 && x < 300){ 
								boxBuildableSelected.setTower(null);
								boxBuildableSelected.getSpr().setTexture(buildableTexture);
							}
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
			if(y < 50){
				if		(x<50) 	boxBuildapleMap = bb1;
				else if (x<100) boxBuildapleMap = bb2;
				else if (x<150) boxBuildapleMap = bb3;
				else if (x<200) boxBuildapleMap = bb4;
			}else if (y < 100){
				if		(x<50) 	boxBuildapleMap = bb5;
				else if (x<100) boxBuildapleMap = bb6;
				else if (x<150) boxBuildapleMap = bb7;
				else if (x<200) boxBuildapleMap = bb8;
			}else if (y < 150){
				if		(x<50) 	boxBuildapleMap = bb9;
				else if (x<100) boxBuildapleMap = bb10;
				else if (x<150) boxBuildapleMap = bb11;
				else if (x<200) boxBuildapleMap = bb12;
			}else if (y < 200){
				if		(x<50) 	boxBuildapleMap = bb13;
				else if (x<100) boxBuildapleMap = bb14;
				else if (x<150) boxBuildapleMap = bb15;
				else if (x<200) boxBuildapleMap = bb16;
			}
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
			
		}else{ /* not an hotspot, what the fuck ?? */
			
		}
	}
	
	public void onKey(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
	}
	
	
	
}


