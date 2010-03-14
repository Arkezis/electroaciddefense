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

public class Play2 extends RokonActivity{

	/* Creating the Elements     */
    public Element fire = new Element("Fire");
    public Element elec = new Element("Electricity");
    public Element water = new Element("Water",elec,fire);    
    public Element iron= new Element("Iron",fire,elec);       

	public Texture buildableTexture,backgroundTexture, tower1ConsTexture,tower2ConsTexture,tower1,tower2;
	public FixedBackground background;
	public BoxBuildable bb1,bb2,bb3,bb4,bb5,bb6,bb7,bb8,bb9,bb10,bb11,bb12 ;
	public TextureAtlas atlas;
	
	public MyMenu myMenu;
	public boolean inMenu ;
	

    public void onCreate() {
        createEngine(480, 320, true);
        
    }

	@Override
	public void onLoad() {
	    /* Finishing creating the elements */
	    this.fire.setStrength(iron); this.fire.setWeakness(water);
	    this.elec.setStrength(water);this.elec.setWeakness(iron); 
		
	    /* Graphical creations */
		atlas = new TextureAtlas(512, 1024);
		atlas.insert(buildableTexture =  new Texture("graphics/backgrounds/grass.png"));
		atlas.insert(tower1ConsTexture =  new Texture("graphics/sprites/tower1Cons.png"));
		atlas.insert(tower2ConsTexture =  new Texture("graphics/sprites/tower2Cons.png"));
		atlas.insert(backgroundTexture = new Texture("graphics/backgrounds/sky.png"));
		atlas.insert(tower1 = new Texture("graphics/tower/tower1.png"));
		atlas.insert(tower2 = new Texture("graphics/tower/tower2.png"));
		TextureManager.load(atlas);
		bb1 = new BoxBuildable(buildableTexture);
		bb2 = new BoxBuildable(buildableTexture);
		bb3 = new BoxBuildable(tower1ConsTexture);
		bb4 = new BoxBuildable(tower2ConsTexture);
		bb5 = new BoxBuildable(tower1ConsTexture);
		bb6 = new BoxBuildable(buildableTexture);
		bb7 = new BoxBuildable(buildableTexture);
		bb8 = new BoxBuildable(tower2ConsTexture);
		bb9 = new BoxBuildable(tower1ConsTexture);
		bb10 = new BoxBuildable(tower1ConsTexture);
		bb11 = new BoxBuildable(buildableTexture);
		bb12 = new BoxBuildable(tower2ConsTexture);

		background = new FixedBackground(backgroundTexture);
	}

	@Override
	public void onLoadComplete() {
		rokon.setBackground(background);
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
		bb1.getSpr().setVisible(true);bb2.getSpr().setVisible(true);
		bb3.getSpr().setVisible(true);bb4.getSpr().setVisible(true);
		bb5.getSpr().setVisible(true);bb6.getSpr().setVisible(true);
		bb7.getSpr().setVisible(true);bb8.getSpr().setVisible(true);
		bb9.getSpr().setVisible(true);bb10.getSpr().setVisible(true);
		bb11.getSpr().setVisible(true);bb12.getSpr().setVisible(true);
		Log.d("DEBUGTAG", "Everything is loaded ?");

	}

	@Override
	public void onHotspotTouch(Hotspot h){
		Log.d("DEBUGTAG", "HOTSPOT : ("+h.x +")"+h.y+")");

	}

	public void onTouch(int x,int y, boolean h) { 
		if (!this.inMenu){ // we are not in the menu, so we have to treat the action
			if (h) {
				Box box = null;
				Log.d("DEBUGTAG", "HOTSPOT : ("+x +")"+y+"). so, which one ??? :p");
				/* Calculating which box it is thanks to the x and y */
				if(y < 50){
					if(x<50) 		box = bb1;
					else if (x<100) box = bb2;
					else if (x<150) box = bb3;
					else if (x<200) box = bb4;
				}else if (y < 100){
					if(x<50) 		box = bb5;
					else if (x<100) box = bb6;
					else if (x<150) box = bb7;
					else if (x<200) box = bb8;
				}else if (y < 150){
					if(x<50) 		box = bb9;
					else if (x<100) box = bb10;
					else if (x<150) box = bb11;
					else if (x<200) box = bb12;
				}else if (y < 200){
					/*if(x<50) 		box = bb13.getSpr();
					else if (x<100) box = bb14.getSpr();
					else if (x<150) box = bb15.getSpr();
					else if (x<200) box = bb16.getSpr();*/
				}else{
					// Not touching the map
				}
				
				Log.d("DEBUGTAG", "Menu for the box : " + box.getNumBox());
				this.inMenu = true;
				if (box != null) rokon.showMenu( myMenu = new MyMenu(box));
				this.inMenu = false;
			}else{
				
			}
		}else{
			//We are in the menu, the class Menu can manage this by itself
		}
	}
	
	public void onKey(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
	}
	
	

	public class MyMenu extends Menu{
		MenuButton b_tower1,b_tower2;
		Sprite box;
		
		public MyMenu(Box myBox){
			box = myBox.getSpr();
			setBackground(background);
			addMenuObject(b_tower1=new MenuButton(1,100,100,50,50,tower1));
			addMenuObject(b_tower2=new MenuButton(2,100,150,50,50,tower2));
			Log.d("DEBUGTAG", "Menu loaded ? ");
		}
		
		public void onShow() {
			b_tower1.fadeIn(1000);
			b_tower2.fadeIn(1000);
		}
		
		public void onKey(int keyCode, KeyEvent event) {
			if(keyCode == KeyEvent.KEYCODE_BACK)
				finish();
		}
		
		public void onMenuObjectTouchUp(MenuObject menuObject) {
			switch(menuObject.getId()){
			case 1:
				box.setTexture(tower1ConsTexture);
			case 2:
				box.setTexture(tower2ConsTexture);
			}
			this.closeMenu();
		}
		
	}
	
}


