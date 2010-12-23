package electroacid.defense.gamePart.gui;

import java.util.LinkedList;

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
import electroacid.defense.gamePart.game.GenericGame;
import electroacid.defense.gamePart.map.GenericMap;
import electroacid.defense.gamePart.observ.ObservateurMenu;
import electroacid.defense.gamePart.tile.Tile;
import electroacid.defense.gamePart.tile.TileBuildable;

public class MenuManager implements ObservateurMenu {

	private DynamicCapacityLayer layerMenuNewTower,layerMenuStatsTower,layerMenuStatsCrea, layerMenuTop;
	private TiledSprite sTower1,sTower2,sTower3,sTower4,sButtonAdd,sButtonUpgrade,sButtonDestroy;
	private TiledSprite sIconLife,sIconMoney,sIconWave,sButtonNextWave,sButtonSpeed; // menuTop
	private ChangeableText tNewTowerTitle,tNewTowerPrice, tNewTowerElem,tNewTowerDamage, tNewTowerArea,tStatsPriceUpgrade,tStatsPriceDestroy,tStatsDamage,tStatsLevel,tStatsElem,tStatsArea;
	private ChangeableText tMenuTopLife,tMenuTopMoney,tMenuTopWave,tMenuTopSpeed; // menuTop
	private Texture mFontTexture,mFontTexture2;
	private Tile tileSelected;
	private GenericMap genericMapUsed;
	private Font mFontMenu,mFontMenuTop;
	private Tower towerSelected;
	static MenuManager instance = null;

	
	public MenuManager(Play base, LinkedList<Tower> towers,LinkedList<TiledTextureRegion> divers){
		if(instance == null){
			layerMenuNewTower = new DynamicCapacityLayer(15);
			layerMenuStatsTower = new DynamicCapacityLayer(15);
			layerMenuStatsCrea = new DynamicCapacityLayer(15);
			layerMenuTop= new DynamicCapacityLayer(15);
			mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR);
			mFontTexture2 = new Texture(256, 256, TextureOptions.BILINEAR);
			mFontMenu = new Font(mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 20, true, Color.WHITE);
			mFontMenuTop = new Font(mFontTexture2, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 18, true, Color.BLACK);
			base.getMEngine().getTextureManager().loadTextures(this.mFontTexture,this.mFontTexture2);
			base.getMEngine().getFontManager().loadFont(mFontMenu);base.getMEngine().getFontManager().loadFont(mFontMenuTop);		
			
			this.loadMenuNewTower(base,towers,divers);
			this.loadMenuStatsTower(base,divers);
			this.loadMenuTop(divers);
			
			layerMenuNewTower.setVisible(true);
			layerMenuStatsTower.setVisible(false);
			layerMenuTop.setVisible(true);
			
			/* Subscribe to the observer ! */
			GenericGame.getInstance().addObservateur(this);
			
			MenuManager.instance = this;
		}
	}
	
	public static MenuManager getInstance(){
		return MenuManager.instance;
	}

	public static void destroyInstance(){
		MenuManager.instance=null;
	}
	
	
	/* Functions to load the menus */
	public  void loadMenuStatsTower(Play base, LinkedList<TiledTextureRegion> divers) {
		
		sButtonUpgrade= new TiledSprite(0, 400, 48, 48, (TiledTextureRegion) divers.get(0)){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
				}	
				return true;
			}
		};
		sButtonUpgrade.setCurrentTileIndex(0);	
		sButtonDestroy= new TiledSprite(0, 400, 48, 48, (TiledTextureRegion) divers.get(1)){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
				}	
				return true;
			}
		};
		sButtonDestroy.setCurrentTileIndex(0);

		tStatsPriceUpgrade = new ChangeableText(150,400,this.mFontMenu,"Price : ");
		tStatsPriceDestroy = new ChangeableText(150,410,this.mFontMenu,"Price : ");
		tStatsLevel = new ChangeableText(150,420,this.mFontMenu,"Level : ");
		tStatsElem = new ChangeableText(150,430,this.mFontMenu,"Element : ");
		tStatsDamage = new ChangeableText(150,440,this.mFontMenu,"Damage : ");
		tStatsArea = new ChangeableText(150,450,this.mFontMenu,"Area : ");
		
		
		layerMenuStatsTower.addEntity(sButtonUpgrade);
		layerMenuStatsTower.addEntity(sButtonDestroy);layerMenuStatsTower.addEntity(tStatsPriceUpgrade);
		layerMenuStatsTower.addEntity(tStatsPriceDestroy);layerMenuStatsTower.addEntity(tStatsLevel);
		layerMenuStatsTower.addEntity(tStatsElem);layerMenuStatsTower.addEntity(tStatsDamage);
		layerMenuStatsTower.addEntity(tStatsArea);
		
	}
	public void loadMenuNewTower(final Play base, final LinkedList<Tower> towers,LinkedList<TiledTextureRegion> divers){
		sTower1 = new TiledSprite(0, 400, 32, 32, (TiledTextureRegion) towers.get(0).getSprite().getTextureRegion()){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					MenuManager.getInstance().showNewTowerPart2(towers.get(0));
				}	
				return true;
			}
		};
		sTower1.setCurrentTileIndex(0);
		sTower2 = new TiledSprite(0, 440, 32, 32, (TiledTextureRegion) towers.get(1).getSprite().getTextureRegion()){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					MenuManager.getInstance().showNewTowerPart2(towers.get(1));
				}	
				return true;
			}
		};
		sTower2.setCurrentTileIndex(0);
		sTower3 = new TiledSprite(48, 400, 32, 32, (TiledTextureRegion) towers.get(2).getSprite().getTextureRegion()){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					MenuManager.getInstance().showNewTowerPart2(towers.get(2));
				}	
				return true;
			}
		};
		sTower3.setCurrentTileIndex(0);
		sTower4 = new TiledSprite(48, 440, 32, 32, (TiledTextureRegion) towers.get(3).getSprite().getTextureRegion()){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					MenuManager.getInstance().showNewTowerPart2(towers.get(3));
				}	
				return true;
			}
		};
		sTower4.setCurrentTileIndex(0);
		
		sButtonAdd = new TiledSprite(80, 440, 32, 32, (TiledTextureRegion) divers.get(0)){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
					Log.d("DEBUGTAG", "Test4");
					base.getListTower().add(towerSelected);
					GenericGame.getInstance().setMoney(GenericGame.getInstance().getMoney() - towerSelected.getCost()); 
					((TileBuildable) tileSelected).changeTower(towerSelected, tileSelected.getTileX(), tileSelected.getTileY(), genericMapUsed);// why give x, y and matrice ? 
					MenuManager.getInstance().hideNewTower();
				}	
				return true;
			}
		};
		sButtonAdd.setCurrentTileIndex(0);
		
		tNewTowerTitle = new ChangeableText(150,380,this.mFontMenu,"Add a new tower");
		tNewTowerPrice = new ChangeableText(150,400,this.mFontMenu,"",15);
		tNewTowerElem = new ChangeableText(150,420,this.mFontMenu,"Choose a tower",15);
		tNewTowerDamage = new ChangeableText(150,440,this.mFontMenu,"",15);
		tNewTowerArea = new ChangeableText(150,460,this.mFontMenu,"",15);
		
		layerMenuNewTower.addEntity(sTower1);	layerMenuNewTower.addEntity(sTower2);
		layerMenuNewTower.addEntity(sTower3);	layerMenuNewTower.addEntity(sTower4);
		layerMenuNewTower.addEntity(sButtonAdd); 	sButtonAdd.setVisible(false);	
		layerMenuNewTower.addEntity(tNewTowerTitle);
		layerMenuNewTower.addEntity(tNewTowerPrice);	layerMenuNewTower.addEntity(tNewTowerElem);
		layerMenuNewTower.addEntity(tNewTowerDamage);	layerMenuNewTower.addEntity(tNewTowerArea);
		layerMenuNewTower.registerTouchArea(sTower1);	layerMenuNewTower.registerTouchArea(sTower2);
		layerMenuNewTower.registerTouchArea(sTower3);	layerMenuNewTower.registerTouchArea(sTower4); 
	}
	public void loadMenuTop(LinkedList<TiledTextureRegion> divers){
		sIconLife = new TiledSprite(0, 0, 32, 32, (TiledTextureRegion) divers.get(0)); // TODO : divers.get(8)
		sIconLife.setCurrentTileIndex(0);
		sIconMoney = new TiledSprite(64,0,32,32,(TiledTextureRegion) divers.get(0)); // TODO : divers.get(9)
		sIconMoney.setCurrentTileIndex(0);
		sIconWave = new TiledSprite(160,0,32,32,(TiledTextureRegion) divers.get(0)); // TODO : divers.get(13)
		sIconWave.setCurrentTileIndex(0);
		sButtonNextWave = new TiledSprite(224,0,32,32,(TiledTextureRegion) divers.get(0)){ // TODO : divers.get(10)
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					// TODO : run next wave
					tMenuTopWave.setText(Integer.toString(GenericGame.getInstance().getActualWave()));
				}	
				return true;
			}
		};
		sButtonNextWave.setCurrentTileIndex(0);
		sButtonSpeed = new TiledSprite(256,0,32,32,(TiledTextureRegion) divers.get(0)){ // TODO : divers.get(10)
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					GenericGame.getInstance().nextSpeedMultiplicator();
					tMenuTopSpeed.setText(Integer.toString(GenericGame.getInstance().getSpeedMultiplicator()));
				}	
				return true;
			}
		};
		sButtonSpeed.setCurrentTileIndex(0);
		
		tMenuTopLife = new ChangeableText(32,8,this.mFontMenuTop,Integer.toString(GenericGame.getInstance().getLives()),3);
		tMenuTopMoney = new ChangeableText(96,8,this.mFontMenuTop,Integer.toString(GenericGame.getInstance().getMoney()),10);
		tMenuTopWave = new ChangeableText(192,8,this.mFontMenuTop,Integer.toString(GenericGame.getInstance().getActualWave()),3);
		tMenuTopSpeed = new ChangeableText(288,8,this.mFontMenuTop,Integer.toString(GenericGame.getInstance().getSpeedMultiplicator()),3);
		
		layerMenuTop.addEntity(sIconLife);layerMenuTop.addEntity(sIconMoney);layerMenuTop.addEntity(sIconWave);
		layerMenuTop.addEntity(sButtonNextWave);layerMenuTop.addEntity(sButtonSpeed);
		layerMenuTop.addEntity(tMenuTopLife);layerMenuTop.addEntity(tMenuTopMoney);layerMenuTop.addEntity(tMenuTopWave);layerMenuTop.addEntity(tMenuTopSpeed);
		layerMenuTop.registerTouchArea(sButtonNextWave);layerMenuTop.registerTouchArea(sButtonSpeed);
		
	}
	
	/* Functions to get the layers */
	public DynamicCapacityLayer getLayerMenuNewTower(){
		return layerMenuNewTower;
	}
	public DynamicCapacityLayer getLayerMenuStatsTower(){
		return layerMenuStatsTower;
	}
	public DynamicCapacityLayer getLayerMenuStatsCrea(){
		return layerMenuStatsCrea;
	}
	public DynamicCapacityLayer getLayerMenuTop(){
		return layerMenuTop;
	}
	
	/* Functions to show or hide the layers (so, the menus !) */
	public boolean showNewTower(TileBuildable t, GenericMap gmap){
		tileSelected = t;
		genericMapUsed = gmap;
		Log.d("DEBUGTAG", "Test3");
		layerMenuNewTower.setVisible(true);
		return true;
	}
	public boolean hideNewTower(){
		layerMenuNewTower.setVisible(false); 
		tNewTowerPrice.setText(""); tNewTowerDamage.setText(""); 
		tNewTowerElem.setText("Choose a tower");tNewTowerArea.setText("");		
		sButtonAdd.setVisible(false); layerMenuNewTower.unregisterTouchArea(sButtonAdd);
		return true;
	}
	public boolean showStatsTower(Tower t){
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
		if(GenericGame.getInstance().getMoney() >= t.getCost()){
			sButtonAdd.setVisible(true); layerMenuNewTower.registerTouchArea(sButtonAdd);
			towerSelected = (Tower) t.clone();
		}else{
			sButtonAdd.setVisible(false); layerMenuNewTower.unregisterTouchArea(sButtonAdd);
		}
		tNewTowerPrice.setText("Price : " + t.getCost());
		tNewTowerElem.setText("Element : " + t.getElement().toString());
		tNewTowerDamage.setText("Damage : " + t.getDamage());
		tNewTowerArea.setText("Area : " + t.getshootArea());
		return true;
	}
	
	public boolean showMenuTop(){
		layerMenuTop.setVisible(true);
		return true;
	}
	
	public boolean hideMenuTop(){ // this menu will never be hidden !
		layerMenuTop.setVisible(false);
		return true;
	}

	@Override
	public void refreshCreature() {
		// TODO : WTF ??
	}

	@Override
	public void refreshLives() {
		tMenuTopLife.setText(Integer.toString(GenericGame.getInstance().getLives()));
	}

	@Override
	public void refreshMoney() {
		tMenuTopMoney.setText(Integer.toString(GenericGame.getInstance().getMoney()));
	}

	@Override
	public void refreshScore() {
		//tMenuTopScore.setText(Integer.toString(GenericGame.getInstance().getScore()));
	}

	@Override
	public void refreshWaves() {
		tMenuTopWave.setText(Integer.toString(GenericGame.getInstance().getActualWave()));
	}
}