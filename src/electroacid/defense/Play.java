package electroacid.defense;

import com.stickycoding.Rokon.RokonActivity;
import com.stickycoding.Rokon.Sprite;
import com.stickycoding.Rokon.Texture;
import com.stickycoding.Rokon.TextureAtlas;
import com.stickycoding.Rokon.TextureManager;
import com.stickycoding.Rokon.Backgrounds.FixedBackground;

import android.app.*;
import android.os.*;
import android.util.Log;

public class Play extends RokonActivity{

	/* Creating the Elements     */
    public Element fire = new Element("Fire");
    public Element elec = new Element("Electricity");
    public Element water = new Element("Water",elec,fire);    
    public Element iron= new Element("Iron",fire,elec);       
    /* Finishing creating the elements 
    this.fire.setStrength(iron); this.fire.setWeakness(water);
    this.elec.setStrength(water);this.elec.setWeakness(iron); */
	public Texture backgroundTexture, tower1Texture;
	public TextureAtlas atlas;
	Sprite tower1Sprite ;
	
	public FixedBackground background;
	
    public void onCreate() {
        createEngine(480, 320, true);
        
    }

	@Override
	public void onLoad() {
		backgroundTexture = new Texture("graphics/loading.png");
		tower1Texture = new Texture("graphics/icon.png");
		atlas = new TextureAtlas(128, 128);
		atlas.insert(backgroundTexture);
		TextureManager.load(atlas);

		tower1Sprite = new Sprite(150,30,tower1Texture);
		
		background = new FixedBackground(backgroundTexture);
	}

	@Override
	public void onLoadComplete() {
		rokon.setBackground(background);
		rokon.addSprite(tower1Sprite);
		tower1Sprite.setVisible(true);
		Log.d("DEBUGTAG", "X of tower1Sprite : " + this.tower1Sprite.getX());
	}

	@Override
	public void onGameLoop() {
		tower1Sprite.setX(tower1Sprite.getX()+3);
	} 
	
	@Override
	public void onRestart() {
		super.onRestart();
		rokon.unpause();
	}
	
}


