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

import electroacid.defense.enums.Element;

public class Play extends RokonActivity{

	/* Creating the Elements     */
    public Element fire = Element.Fire;
    public Element elec = Element.Electricity;
    public Element water = Element.Water;    
    public Element iron= Element.Iron;       

	public Texture backgroundTexture, tower1Texture;
	public TextureAtlas atlas;
	public Sprite tower1Sprite,tower2Sprite,tower3Sprite ;
	public Hotspot tower1Hpt,tower2Hpt,tower3Hpt;
	public FixedBackground background;
	
    public void onCreate() {
        createEngine(480, 320, true);
        
    }


	public void onLoad() {

	    /* Graphical creations */
		atlas = new TextureAtlas(512, 1024);
		atlas.insert(backgroundTexture = new Texture("graphics/backgrounds/sky.png"));
		atlas.insert(tower1Texture = new Texture("graphics/sprites/car.png"));
		TextureManager.load(atlas);
		Log.d("DEBUGTAG", "Init du tower1sprite ");

		tower1Sprite = new Sprite(0,0,tower1Texture); /* Size of the tower : 83x68 */
		tower2Sprite = new Sprite(83,0,tower1Texture); /* Size of the tower : 83x68 */
		tower3Sprite = new Sprite(166,0,tower1Texture); /* Size of the tower : 83x68 */
		tower1Hpt = new Hotspot(tower1Sprite);
		tower2Hpt = new Hotspot(tower2Sprite);
		tower3Hpt = new Hotspot(tower3Sprite);
		
		Log.d("DEBUGTAG", "Après Init du tower1sprite ");

		background = new FixedBackground(backgroundTexture);
	}

	@Override
	public void onLoadComplete() {
		rokon.setBackground(background);
		rokon.addSprite(tower1Sprite);rokon.addSprite(tower2Sprite);rokon.addSprite(tower3Sprite);
		rokon.addHotspot(tower1Hpt);rokon.addHotspot(tower2Hpt);rokon.addHotspot(tower3Hpt);
		tower1Sprite.setVisible(true);tower2Sprite.setVisible(true);tower3Sprite.setVisible(true);
		
	}

	@Override
	public void onHotspotTouch(Hotspot h){
		if (h.x <83 && h.y <68){
			tower1Sprite.setVisible(false);
			tower1Sprite.setXY(0, 68);
			tower1Sprite.setVisible(true);
		}else if (h.x <83 && h.y > 68 && h.y < 136){
			tower1Sprite.setVisible(false);
			tower1Sprite.setXY(0, 0);
			tower1Sprite.setVisible(true);
		}
		Log.d("DEBUGTAG", "HOTSPOT : ("+h.x +")"+h.y+")");
	}


}


