package electroacid.defense.gamePart;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.layer.DynamicCapacityLayer;
import org.anddev.andengine.entity.layer.ILayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLoader;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperties;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTileProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.anddev.andengine.entity.layer.tiled.tmx.util.exception.TMXLoadException;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import electroacid.defense.gamePart.map.GenericMap;

public class Play extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 320;
	private static final int CAMERA_HEIGHT = 480;
	private static final int LAYER_MAP = 0;
	private static final int LAYER_MENU = LAYER_MAP + 1;


	private BoundCamera mBoundChaseCamera;

	private Texture mTexture,mFontTexture,mTextureTowersCreaturesSprite;
	private TMXTiledMap mTMXTiledMap;
	private Font mFontMenu;
	private TiledTextureRegion mTower1TextureRegion,mTower2TextureRegion,mTower3TextureRegion,mTower4TextureRegion;
	private TiledSprite tower1Sprite, tower2Sprite, tower3Sprite, tower4Sprite;
	private Text menuPriceText, menuElementText,menuDamageText, menuAreaText;
	private DynamicCapacityLayer layerMenuNewTower;

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
		this.mTexture = new Texture(32, 32, TextureOptions.DEFAULT);
		this.mTextureTowersCreaturesSprite = new Texture(128, 16, TextureOptions.BILINEAR);
		
		/* Towers */
		this.mTower1TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 0, 0, 6, 1);
		this.mTower2TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 32, 0, 6, 1);
		this.mTower3TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 64, 0, 6, 1);
		this.mTower4TextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTextureTowersCreaturesSprite, this, "towers_creatures.png", 96, 0, 6, 1);

		/* Fonts */
		mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR);
		mFontMenu = new Font(mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 20, true, Color.WHITE);
		
		/* Add everything in the engine's manager */
		this.mEngine.getTextureManager().loadTextures(this.mFontTexture,this.mTexture,this.mTextureTowersCreaturesSprite);
		this.mEngine.getFontManager().loadFont(mFontMenu);	
	}

	@Override
	public Scene onLoadScene() {
		final Scene scene = new Scene(2);

		/* TMX part */
		GenericMap genericMap = new GenericMap();
		try {
			genericMap.buildMap(this,this.mEngine.getTextureManager());
		} catch (TMXLoadException e) {
			e.printStackTrace();
		}
		this.mTMXTiledMap=genericMap.getTmxTiledMap();
		final TMXLayer tmxLayer = this.mTMXTiledMap.getTMXLayers().get(0);	
		scene.getBottomLayer().addEntity(tmxLayer);

		/* Make the camera not exceed the bounds of the TMXLayer. */
		this.mBoundChaseCamera.setBounds(0, tmxLayer.getWidth(), 0, tmxLayer
				.getHeight());
		this.mBoundChaseCamera.setBoundsEnabled(true);

		scene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));

		/* Initialisations */
		initMenu(scene);
		
		scene.setTouchAreaBindingEnabled(true);
		return scene;
	}
	
	private void initMenu(Scene scene){
		
		tower1Sprite = new TiledSprite(0, 416, 32, 32, this.mTower1TextureRegion){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
				}	
				return true;
			}
		};
		tower1Sprite.setCurrentTileIndex(0);
		tower2Sprite = new TiledSprite(0, 448, 32, 32, this.mTower2TextureRegion){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
				}	
				return true;
			}
		};
		tower2Sprite.setCurrentTileIndex(0);
		tower3Sprite = new TiledSprite(32, 416, 32, 32, this.mTower3TextureRegion){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
				}	
				return true;
			}
		};
		tower3Sprite.setCurrentTileIndex(0);
		tower4Sprite = new TiledSprite(32, 448, 32, 32, this.mTower4TextureRegion){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setRotation(this.getRotation() + (float)90);
				}	
				return true;
			}
		};
		tower4Sprite.setCurrentTileIndex(0);
		
		layerMenuNewTower = new DynamicCapacityLayer(15);
		layerMenuNewTower.addEntity(tower1Sprite);	layerMenuNewTower.addEntity(tower2Sprite);
		layerMenuNewTower.addEntity(tower3Sprite);	layerMenuNewTower.addEntity(tower4Sprite);
		layerMenuNewTower.registerTouchArea(tower1Sprite);	layerMenuNewTower.registerTouchArea(tower2Sprite);
		layerMenuNewTower.registerTouchArea(tower3Sprite);	layerMenuNewTower.registerTouchArea(tower4Sprite);		
	}

	@Override
	public void onLoadComplete() {

	}
}
