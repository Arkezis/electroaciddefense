package electroacid.defense.gamePart.map;

import java.util.ArrayList;

import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLoader;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperties;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTileProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.anddev.andengine.entity.layer.tiled.tmx.util.exception.TMXLoadException;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;

import electroacid.defense.gamePart.Play;
import electroacid.defense.gamePart.enums.Direction;
import electroacid.defense.gamePart.tile.Tile;
import electroacid.defense.gamePart.tile.TileBuildable;
import electroacid.defense.gamePart.tile.TilePath;

/**
 * Read a xml file and create map create the path
 * 
 * @author cilheo
 * @version 2.0
 */
public class GenericMap {

	TMXTiledMap tmxTiledMap;

	int nbLine;
	int nbColumn;

	/** width of box */
	int offsetX;
	/** height of box */
	int offsetY;

	Tile[] firstBoxPath;

	TilePath lastTilePath;

	/**
	 * Creation of a generic map, create the matrice
	 * 
	 * @param _xMax
	 *            max x coordinate
	 * @param _yMax
	 *            max y coordinate
	 * @param _offsetX
	 *            width of box
	 * @param _offsetY
	 *            height of box
	 */
	public GenericMap(int _xMax, int _yMax, int _offsetX, int _offsetY) {
		this.nbLine = _xMax;
		this.nbColumn = _yMax;
		this.offsetX = _offsetX;
		this.offsetY = _offsetY;
	}

	public void buildMap(Play play, TextureManager textureManager)
			throws TMXLoadException {
		final TMXLoader tmxLoader = new TMXLoader(play, textureManager,
				TextureOptions.DEFAULT, new ITMXTilePropertiesListener() {
					@Override
					public void onTMXTileWithPropertiesCreated(
							final TMXTiledMap pTMXTiledMap,
							final TMXLayer pTMXLayer,
							final TMXTile pTMXTile,
							final TMXProperties<TMXTileProperty> pTMXTileProperties) {
					}
				});
		this.tmxTiledMap = tmxLoader.loadFromAsset(play, "tmx/testmap2.tmx");
		this.buildPath();
	}

	public TMXTiledMap getTmxTiledMap() {
		return tmxTiledMap;
	}

	/** build the path */
	private void buildPath() {
		this.changeTile();

		ArrayList<TilePath> listTile = new ArrayList<TilePath>();
		listTile.add(this.lastTilePath);

		while (listTile.size() != 0) {

			TilePath lastTile = listTile.remove(0);
			listTile.addAll(previousPath(lastTile));

		}
	}

	private ArrayList<TilePath> previousPath(TilePath lastTile) {
		ArrayList<TilePath> listTile = new ArrayList<TilePath>();

		TMXLayer a = this.tmxTiledMap.getTMXLayers().get(0);

		int column = lastTile.getTileColumn();
		int line = lastTile.getTileRow();
		TilePath tile;

		if (column > 0 && a.getTMXTile(column - 1, line) instanceof TilePath) {
			tile = (TilePath) a.getTMXTile(column - 1, line);
			if (tile.getDirection() == null) {
				tile.setDirection(Direction.Right);
				listTile.add(tile);
			}
		}
		if (column < a.getTileColumns() - 1
				&& a.getTMXTile(column + 1, line) instanceof TilePath) {
			tile = (TilePath) a.getTMXTile(column + 1, line);
			if (tile.getDirection() == null) {
				tile.setDirection(Direction.Left);
				listTile.add(tile);
			}
		}
		if (line > 0 && a.getTMXTile(column, line - 1) instanceof TilePath) {
			tile = (TilePath) a.getTMXTile(column, line - 1);
			if (tile.getDirection() == null) {
				tile.setDirection(Direction.Down);
				listTile.add(tile);
			}
		}
		if (column < a.getTileRows() - 1
				&& a.getTMXTile(column, line + 1) instanceof TilePath) {
			tile = (TilePath) a.getTMXTile(column, line + 1);
			if (tile.getDirection() == null) {
				tile.setDirection(Direction.Up);
				listTile.add(tile);
			}
		}
		return listTile;
	}

	private void changeTile() {
		TMXLayer a = this.tmxTiledMap.getTMXLayers().get(0);
		TMXTile[][] b = a.getTMXTiles();
		for (int i = 0; i < a.getTileRows(); i++) {
			for (int j = 0; j < a.getTileColumns(); j++) {

				TMXTile c = b[i][j];
				if (c.getGlobalTileID() < 17 || c.getGlobalTileID() > 25) {
					b[i][j] = new TileBuildable(b[i][j]);
				} else {
					b[i][j] = new TilePath(b[i][j]);
					if (c.getGlobalTileID() == 18) {
						this.lastTilePath = (TilePath) b[i][j];
						this.lastTilePath.setDirection(Direction.Right);
					}
				}
				int y = 0;
				y = y + 1;
			}
		}
	}

	/**
	 * get the box
	 * 
	 * @param x
	 *            coordinate of the wanted box
	 * @param y
	 *            coordinate of the wanted box
	 * @return the box wanted or null
	 */
	public Tile getBox(int x, int y) {
		// the box is not in the matrice
		if (x >= this.nbColumn * this.offsetX
				|| y >= this.nbLine * this.offsetY || x < 0 || y < 32)
			return null;
		return (Tile) this.tmxTiledMap.getTMXLayers().get(0).getTMXTiles()[y
				/ this.offsetY][x / this.offsetX];
	}

}
