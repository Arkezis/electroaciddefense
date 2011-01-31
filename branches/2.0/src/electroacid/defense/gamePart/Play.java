package electroacid.defense.gamePart;

import java.util.LinkedList;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.layer.DynamicCapacityLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.layer.tiled.tmx.util.exception.TMXLoadException;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.util.Log;
import electroacid.defense.R;
import electroacid.defense.gamePart.game.GenericGame;
import electroacid.defense.gamePart.gui.MenuManager;
import electroacid.defense.gamePart.map.GenericMap;
import electroacid.defense.gamePart.tile.TileBuildable;
import electroacid.defense.gamePart.tower.GenericTower;

public class Play extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 320;
	private static final int CAMERA_HEIGHT = 480;
	public static final int LAYER_MAP = 0;
	public static final int LAYER_MENU_NEW_TOWER = LAYER_MAP + 1;
	public static final int LAYER_MENU_STATS_TOWER = LAYER_MENU_NEW_TOWER + 1;
	public static final int LAYER_MENU_STATS_CREA = LAYER_MENU_STATS_TOWER + 1;
	public static final int LAYER_MENU_TOP = LAYER_MENU_STATS_CREA + 1;
	public static final int LAYER_TEST = LAYER_MENU_TOP +1; // impossible to add this layer to the scene ! Too much layers, kill the layers ... :(



	private BoundCamera mBoundChaseCamera;
	static Scene scene;
	private Texture mTextureTowersCreaturesSprite,mTextureDiversSprite;
	private TMXTiledMap mTMXTiledMap;
	private TiledTextureRegion mTower1TextureRegion,mTower2TextureRegion,mTower3TextureRegion,mTower4TextureRegion;
	private TiledTextureRegion mButtonAddTextureRegion,mButtonDestroyTextureRegion,mTouchPointerTextureRegion,mLogoHeartTextureRegion,mLogoMoneyTextureRegion,mLogoNextWaveTextureRegion,mLogoSpeed1TextureRegion,mLogoWavesTextureRegion,mLogoSpeed2TextureRegion,mLogoSpeed3TextureRegion;
	private TiledTextureRegion mCrea1TextureRegion,mCrea2TextureRegion,mCrea3TextureRegion,mCrea4TextureRegion;
	private LinkedList<TiledTextureRegion> listDiversTextureRegion;
	private GenericGame gameData;
	private LinkedList<Tower> listTower;
	private DynamicCapacityLayer layerTest; 
	TiledSprite s1,s12,s2,s22,sTouchPointer;

	@Override
	public Engine onLoadEngine() {
		this.mBoundChaseCamera = new BoundCamera(0, 0, CAMERA_WIDTH,
				CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.mBoundChaseCamera));
	}

	@Override
	public void onLoadResources() {
		TextureRegionFactory.setAssetBasePath("gfx/");
		this.mTextureTowersCreaturesSprite = new Texture(256, 128, TextureOptions.BILINEAR);
		this.mTextureDiversSprite = new Texture(512,128,TextureOptions.BILINEAR);
		
		/* Towers */
		this.mTower1TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 0, 0, 4, 2);
		this.mTower2TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 64, 0, 4, 2);
		this.mTower3TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 128, 0, 4, 2);
		this.mTower4TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 196, 0, 4, 2);
		
		/* Creatures */
		this.mCrea1TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 0, 64, 4, 2);
		this.mCrea2TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 64, 64, 4, 2);
		this.mCrea3TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 128, 64, 4, 2);
		this.mCrea4TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 196, 64, 4, 2);
		
		/* Divers (menu, sidebar...) */
		this.mButtonAddTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureDiversSprite, this, "buttons.png", 0, 0, 8, 2);
		this.mButtonDestroyTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureDiversSprite, this, "buttons.png", 64, 0, 8, 2);
		this.mTouchPointerTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureDiversSprite, this, "buttons.png", 128, 0, 8, 2);
		this.mLogoHeartTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureDiversSprite, this, "buttons.png", 0, 64, 8, 2);
		this.mLogoMoneyTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureDiversSprite, this, "buttons.png", 64, 64, 8, 2);
		this.mLogoNextWaveTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureDiversSprite, this, "buttons.png", 128, 64, 8, 2);
		this.mLogoSpeed1TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureDiversSprite, this, "buttons.png", 256, 64, 8, 2);
		this.mLogoWavesTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureDiversSprite, this, "buttons.png", 320, 64, 8, 2);
		this.mLogoSpeed2TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureDiversSprite, this, "buttons.png", 384, 64, 8, 2);
		this.mLogoSpeed3TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureDiversSprite, this, "buttons.png", 448, 64, 8, 2);
		
		listDiversTextureRegion = new  LinkedList<TiledTextureRegion>();
		listDiversTextureRegion.add(this.mButtonAddTextureRegion);listDiversTextureRegion.add(this.mButtonDestroyTextureRegion);
		listDiversTextureRegion.add(this.mTouchPointerTextureRegion);listDiversTextureRegion.add(this.mLogoHeartTextureRegion);
		listDiversTextureRegion.add(this.mLogoMoneyTextureRegion);listDiversTextureRegion.add(this.mLogoNextWaveTextureRegion);
		listDiversTextureRegion.add(this.mLogoSpeed1TextureRegion);listDiversTextureRegion.add(this.mLogoWavesTextureRegion);
		listDiversTextureRegion.add(this.mLogoSpeed2TextureRegion);listDiversTextureRegion.add(this.mLogoSpeed3TextureRegion);
		listDiversTextureRegion.add(this.mButtonAddTextureRegion);listDiversTextureRegion.add(this.mButtonDestroyTextureRegion);
		/* Fonts */
		//mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR);
		//mFontMenu = new Font(mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 20, true, Color.WHITE);
		
		/* Add everything in the engine's manager */
		this.mEngine.getTextureManager().loadTextures(this.mTextureTowersCreaturesSprite,this.mTextureDiversSprite);
		//this.mEngine.getFontManager().loadFont(mFontMenu);	
	}

	public Engine getMEngine(){
		return this.mEngine;
	}
	
	public LinkedList<Tower> getListTower(){
		return listTower;
	}
	
	@Override
	public Scene onLoadScene() {
		scene = new Scene(6); // 6 layers : normal, menuNewTower, menuStatsTower, menuStatsCrea, menuTop, test
		
		
		/* TMX part */
		final GenericMap genericMap = new GenericMap();
		try {
			genericMap.buildMap(this,this.mEngine.getTextureManager());
		} catch (TMXLoadException e) { e.printStackTrace();	}
		this.mTMXTiledMap=genericMap.tmxTiledMap;
		final TMXLayer tmxLayer = this.mTMXTiledMap.getTMXLayers().get(0);
		scene.getLayer(LAYER_MAP).addEntity(tmxLayer);
		/*  Register touchArea to the tileBuidable */
		/*TMXTile[][] tile = tmxLayer.getTMXTiles();
		for(int i=0; i<tmxLayer.getTileRows();i++)
			for(int j=0;j<tmxLayer.getTileColumns();j++)
				if(tile[i][j].getClass().isInstance(new TileBuildable(null)))
					scene.getLayer(LAYER_MAP).registerTouchArea(tile[i][j].get);
		*/
		/* Make the camera not exceed the bounds of the TMXLayer. */
		this.mBoundChaseCamera.setBounds(0, tmxLayer.getWidth(), 0, tmxLayer
				.getHeight());
		this.mBoundChaseCamera.setBoundsEnabled(true);

		scene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));

		/* ------------------------------- Initialisations ------------------------------- */
		GenericTower genericTower = new GenericTower(this, this.mTextureTowersCreaturesSprite); 
		try {
			genericTower.build(getWindow().getContext(), R.raw.tower);
		} catch (Exception e) {	e.printStackTrace();}
		listTower = genericTower.listTower;
		
		gameData = GenericGame.getInstance();
		try {
			gameData.build(getWindow().getContext(), R.raw.game1game);
		} catch (Exception e) {	e.printStackTrace();}
		
		/* Useful sprite */
		sTouchPointer= new TiledSprite(0, 0, 32, 32, this.mTouchPointerTextureRegion);
		sTouchPointer.setZIndex(Integer.MIN_VALUE); // TODO : faire apparaître le point au dessus des tours sélectionnées... 
		scene.getLayer(LAYER_MAP).addEntity(sTouchPointer);
		
		
		
		initMenu(scene);
		scene.setTouchAreaBindingEnabled(true);
		scene.setOnSceneTouchListener(new IOnSceneTouchListener() {
			
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
				final TMXTile tileTouched = tmxLayer.getTMXTileAt(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
				Log.d("DEBUGTAG", "Test0");
				// TODO : if(tileTouched.getClass().isInstance(TileBuildable.class)){ // not working, WTF ???
				if(tileTouched instanceof TileBuildable){ 
					TileBuildable tileSelected = (TileBuildable) tileTouched;
					sTouchPointer.setPosition(tileSelected.getTileX(), tileSelected.getTileY());
					if(tileSelected.tower!=null){ // already a tower
						MenuManager.getInstance().showStatsTower(tileSelected);
					}else{
						MenuManager.getInstance().showNewTower(tileSelected,genericMap);
					}
				}
				return false;
			}
		});
		
		/* Starting the game and initialising everything for the game */
		gameData.gameStarted = true;
		
		testMenu(scene);
		
		/**
		 *     Called every frame 
		 */
		scene.registerUpdateHandler(new TimerHandler(0.5f, true, new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if(gameData.gameStarted) {
					if(! gameData.pause){
						/* WAVES */
						
						/* SHOOTS */
						
						/* MENUS */
						// No need to refresh the menus, it's automatically done thanks to listeners
					}

				}else{
					
				}
			}
		}));
		
		
		
		
		return scene;
	}
	
	private void testMenu(Scene s) {
		//Le layerTest est utilisé pour afficher des boutons pour tester les interactions
		layerTest = new DynamicCapacityLayer(15);
		/*
		s1 = new TiledSprite(160, 320, 32, 32, this.mTower1TextureRegion){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
					Log.d("TEST", "TAP");
					MenuManager.getInstance().showNewTower(null, null);
				}
				return true;
			}
		};
		s12 = new TiledSprite(160, 352, 32, 32, this.mTower2TextureRegion){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
					Log.d("TEST", "TAP");
					MenuManager.getInstance().hideNewTower();
				}
				return true;
			}
		};
		s2 = new TiledSprite(192, 320, 32, 32, this.mTower3TextureRegion){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
					Log.d("TEST", "TAP");
					MenuManager.getInstance().showStatsTower(null);
				}
				return true;
			}
		};
		s22 = new TiledSprite(192, 352, 32, 32, this.mTower4TextureRegion){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
					Log.d("TEST", "TAP");
					MenuManager.getInstance().hideStatsTower();
				}
				return true;
			}
		};
		
		layerTest.addEntity(s1);layerTest.registerTouchArea(s1);
		layerTest.addEntity(s12);layerTest.registerTouchArea(s12);
		layerTest.addEntity(s2);layerTest.registerTouchArea(s2);
		layerTest.addEntity(s22);layerTest.registerTouchArea(s22);
		*/
		s.setLayer(LAYER_TEST, layerTest);
		
	}

	private void initMenu(Scene scene){
		new MenuManager(this,listTower,listDiversTextureRegion);  // only one instanciation ! 
		scene.setLayer(LAYER_MENU_NEW_TOWER, MenuManager.getInstance().getLayerMenuNewTower());
		scene.setLayer(LAYER_MENU_STATS_TOWER, MenuManager.getInstance().getLayerMenuStatsTower());
		scene.setLayer(LAYER_MENU_STATS_CREA, MenuManager.getInstance().getLayerMenuStatsCrea());
		scene.setLayer(LAYER_MENU_TOP, MenuManager.getInstance().getLayerMenuTop());
	}

	@Override
	public void onLoadComplete() {

	}
}
