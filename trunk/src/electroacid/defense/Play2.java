package electroacid.defense;

import com.stickycoding.Rokon.Hotspot;
import com.stickycoding.Rokon.RokonActivity;
import com.stickycoding.Rokon.Sprite;
import com.stickycoding.Rokon.Texture;
import com.stickycoding.Rokon.TextureAtlas;
import com.stickycoding.Rokon.TextureManager;
import com.stickycoding.Rokon.Backgrounds.FixedBackground;

import android.app.*;
import android.content.Context;
import android.os.*;
import android.util.Log;
import android.widget.Toast;

public class Play2 extends RokonActivity{

	/* Creating the Elements     */
    public Element fire = new Element("Fire");
    public Element elec = new Element("Electricity");
    public Element water = new Element("Water",elec,fire);    
    public Element iron= new Element("Iron",fire,elec);       

	public Texture backgroundTexture;
	public FixedBackground background;
	public BoxBuildable bb1,bb2,bb3,bb4,bb5,bb6,bb7,bb8,bb9,bb10,bb11,bb12 ;
	public TextureAtlas atlas;
		public Texture buildableTexture;

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
		atlas.insert(buildableTexture =  new Texture("graphics/sprites/car.png"));
		atlas.insert(backgroundTexture = new Texture("graphics/backgrounds/sky.png"));
		TextureManager.load(atlas);
		bb1 = new BoxBuildable(buildableTexture);
		bb2 = new BoxBuildable(buildableTexture);
		bb3 = new BoxBuildable(buildableTexture);
		bb4 = new BoxBuildable(buildableTexture);
		bb5 = new BoxBuildable(buildableTexture);
		bb6 = new BoxBuildable(buildableTexture);
		bb7 = new BoxBuildable(buildableTexture);
		bb8 = new BoxBuildable(buildableTexture);
		bb9 = new BoxBuildable(buildableTexture);
		bb10 = new BoxBuildable(buildableTexture);
		bb11 = new BoxBuildable(buildableTexture);
		bb12 = new BoxBuildable(buildableTexture);

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


}


