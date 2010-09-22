package electroacid.defense.gamePart;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
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
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;

import electroacid.defense.gamePart.map.GenericMap;

public class Play extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 320;
	private static final int CAMERA_HEIGHT = 480;

	private BoundCamera mBoundChaseCamera;

	private Texture mTexture;
	private TMXTiledMap mTMXTiledMap;

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
		this.mTexture = new Texture(32, 32, TextureOptions.DEFAULT);

		this.mEngine.getTextureManager().loadTexture(this.mTexture);
	}

	@Override
	public Scene onLoadScene() {
		final Scene scene = new Scene(1);
		
		
		
		GenericMap genericMap = new GenericMap(13, 10, 32, 32);
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

		return scene;
	}

	@Override
	public void onLoadComplete() {

	}
}
