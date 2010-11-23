package electroacid.defense.gamePart.gui;

import java.util.HashMap;

import org.anddev.andengine.entity.layer.DynamicCapacityLayer;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import electroacid.defense.gamePart.Play;
import electroacid.defense.gamePart.Tower;

public class MenuManager {

	private DynamicCapacityLayer layerMenuNewTower,layerMenuStatsTower,layerMenuStatsCrea;
	private TiledSprite sTower1,sTower2,sTower3,sTower4,sButtonAdd,sStatsTower,sButtonUpgrade,sButtonDestroy;
	private ChangeableText tNewTowerPrice, tNewTowerElem,tNewTowerDamage, tNewTowerArea,tStatsPriceUpgrade,tStatsPriceDestroy,tStatsDamage,tStatsLevel,tStatsElem,tStatsArea;
	private Texture mFontTexture;
	private Font mFontMenu;

	
	public MenuManager(Play base, HashMap towers,HashMap divers){
		layerMenuNewTower = new DynamicCapacityLayer(15);
		layerMenuStatsTower = new DynamicCapacityLayer(15);
		layerMenuStatsCrea = new DynamicCapacityLayer(15);
		mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR);
		mFontMenu = new Font(mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 20, true, Color.WHITE);
		base.getMEngine().getTextureManager().loadTextures(this.mFontTexture);
		base.getMEngine().getFontManager().loadFont(mFontMenu);	
		
		this.loadMenuNewTower(base,towers,divers);
		this.loadMenuStatsTower(base,divers);
		
		layerMenuNewTower.setVisible(false);
		layerMenuStatsTower.setVisible(false);
	}
	
	/* Functions to load the menus */
	private void loadMenuStatsTower(Play base, HashMap divers) {
		
		sStatsTower= new TiledSprite(0, 400, 48, 48, null);
		sButtonUpgrade= new TiledSprite(0, 400, 48, 48, (TiledTextureRegion) divers.get(0)){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
				}	
				return true;
			}
		};
		sButtonDestroy= new TiledSprite(0, 400, 48, 48, (TiledTextureRegion) divers.get(1)){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
				}	
				return true;
			}
		};
		tStatsPriceUpgrade = new ChangeableText(150,400,this.mFontMenu,"Price : ");
		tStatsPriceDestroy = new ChangeableText(150,410,this.mFontMenu,"Price : ");
		tStatsLevel = new ChangeableText(150,420,this.mFontMenu,"Level : ");
		tStatsElem = new ChangeableText(150,430,this.mFontMenu,"Element : ");
		tStatsDamage = new ChangeableText(150,440,this.mFontMenu,"Damage : ");
		tStatsArea = new ChangeableText(150,450,this.mFontMenu,"Area : ");
		
		
		/*layerMenuStatsCrea.addEntity(sStatsTower);layerMenuStatsCrea.addEntity(sButtonUpgrade);
		layerMenuStatsCrea.addEntity(sButtonDestroy);*/layerMenuStatsTower.addEntity(tStatsPriceUpgrade);
		layerMenuStatsTower.addEntity(tStatsPriceDestroy);layerMenuStatsTower.addEntity(tStatsLevel);
		layerMenuStatsTower.addEntity(tStatsElem);layerMenuStatsTower.addEntity(tStatsDamage);
		layerMenuStatsTower.addEntity(tStatsArea);
		
	}
	public void loadMenuNewTower(Play base, HashMap towers,HashMap divers){
		/*tower1Sprite = new TiledSprite(0, 400, 48, 48, (TiledTextureRegion) towers.get(0)){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
					base.getMenuManager().showNewTowerPart2(this);
				}	
				return true;
			}
		};
		tower1Sprite.setCurrentTileIndex(0);
		tower2Sprite = new TiledSprite(0, 440, 48, 48, (TiledTextureRegion) towers.get(1)){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
					base.getMenuManager().showNewTowerPart2(this);
				}	
				return true;
			}
		};
		tower2Sprite.setCurrentTileIndex(0);
		tower3Sprite = new TiledSprite(48, 400, 48, 48, (TiledTextureRegion) towers.get(2)){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
					base.getMenuManager().showNewTowerPart2(this);
				}	
				return true;
			}
		};
		tower3Sprite.setCurrentTileIndex(0);
		tower4Sprite = new TiledSprite(48, 440, 48, 48, (TiledTextureRegion) towers.get(3)){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
					base.getMenuManager().showNewTowerPart2(this);
				}	
				return true;
			}
		};
		tower4Sprite.setCurrentTileIndex(0);
		*/
		sButtonAdd = new TiledSprite(96, 440, 48, 48, (TiledTextureRegion) divers.get(0)){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
					
				}	
				return true;
			}
		};
		sButtonAdd.setCurrentTileIndex(0);
		
		tNewTowerPrice = new ChangeableText(150,400,this.mFontMenu,"Price : ");
		tNewTowerElem = new ChangeableText(150,410,this.mFontMenu,"Elem : ");
		tNewTowerDamage = new ChangeableText(150,420,this.mFontMenu,"Damage : ");
		tNewTowerArea = new ChangeableText(150,430,this.mFontMenu,"Area : ");
		
		/*layerMenuNewTower.addEntity(tower1Sprite);	layerMenuNewTower.addEntity(tower2Sprite);
		layerMenuNewTower.addEntity(tower3Sprite);	layerMenuNewTower.addEntity(tower4Sprite);*/
		layerMenuNewTower.addEntity(sButtonAdd);
		layerMenuNewTower.addEntity(tNewTowerPrice);	layerMenuNewTower.addEntity(tNewTowerElem);
		layerMenuNewTower.addEntity(tNewTowerDamage);	layerMenuNewTower.addEntity(tNewTowerArea);
		/*layerMenuNewTower.registerTouchArea(tower1Sprite);	layerMenuNewTower.registerTouchArea(tower2Sprite);
		layerMenuNewTower.registerTouchArea(tower3Sprite);	layerMenuNewTower.registerTouchArea(tower4Sprite); */
		sButtonAdd.setVisible(false);

	}
	
	/* Functions to get the layers */
	public DynamicCapacityLayer getLayerMenuNewTower(){
		return layerMenuNewTower;
	}
	public DynamicCapacityLayer getLayerMenuStatsTower(){
		Log.d("TEST", "On load !");
		return layerMenuStatsTower;
	}
	public DynamicCapacityLayer getLayerMenuStatsCrea(){
		return layerMenuStatsCrea;
	}
	
	/* Functions to show or hide the layers (so, the menus !) */
	public boolean showNewTower(){
		layerMenuNewTower.setVisible(true);
		return true;
	}
	public boolean hideNewTower(){
		layerMenuNewTower.setVisible(false);
		return true;
	}
	public boolean showStatsTower(){
		Log.d("TEST", "On show");
		layerMenuStatsTower.setVisible(true);
		return true;
	}
	public boolean hideStatsTower(){
		layerMenuStatsTower.setVisible(false);
		return true;
	}
	public boolean showStatsCrea(){
		layerMenuStatsCrea.setVisible(true);
		return true;
	}
	public boolean hideStatsCrea(){
		layerMenuStatsCrea.setVisible(false);
		return true;
	}
	
	public boolean showNewTowerPart2(Tower t){
		sButtonAdd.setVisible(true);
		tNewTowerPrice.setText("Price : " + t.getCost());
		tNewTowerElem.setText("Price : " + t.getElement().toString());
		tNewTowerDamage.setText("Price : " + t.getDamage());
		tNewTowerArea.setText("Price : " + t.getshootArea());
		layerMenuNewTower.registerTouchArea(sButtonAdd);
		
		return true;
	}
}
