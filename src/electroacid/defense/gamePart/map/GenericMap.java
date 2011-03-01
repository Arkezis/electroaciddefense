package electroacid.defense.gamePart.map;

import java.util.ArrayList;

import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLoader;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.layer.tiled.tmx.util.exception.TMXLoadException;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.util.Path;

import android.util.Log;

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

	public TMXTiledMap tmxTiledMap;
	public Tile[] firstBoxPath;
	public TilePath lastTilePath;
	public Path[] listPath;
	
	public GenericMap() {}

	public void buildMap(Play play, TextureManager textureManager)
			throws TMXLoadException {
		final TMXLoader tmxLoader = new TMXLoader(play, textureManager,
				TextureOptions.DEFAULT);
		this.tmxTiledMap = tmxLoader.loadFromAsset(play, "tmx/testmap2.tmx");
		this.buildPath();
	}

	/** build the path */
	private void buildPath() {
		this.changeTile();

		ArrayList<TilePath> listTile = new ArrayList<TilePath>();
		listTile.add(this.lastTilePath);

		
		ArrayList<ArrayList<TilePath>> listForPath = new ArrayList<ArrayList<TilePath>>();
		
		listForPath.add(new ArrayList<TilePath>());
		listForPath.get(0).add(this.lastTilePath);
		
		int previous = 0;
		
		while (listTile.size() != 0) {

			TilePath lastTile = listTile.remove(0);
			
			ArrayList<TilePath> allPath = previousPath(lastTile);
			
			if (allPath.size() == 0){
				previous++;
			}
			else if (allPath.size()==1){
				listForPath.get(previous).addAll(allPath);
			}else {
				ArrayList<TilePath> clone = (ArrayList<TilePath>) listForPath.get(previous).clone();
				for (int i=1;i<allPath.size();i++){
					ArrayList<TilePath> clone2 = (ArrayList<TilePath>) clone.clone();
					clone2.add(allPath.get(i));
					listForPath.add(clone2);
				}
				listForPath.get(previous).add(allPath.get(0));
			}
			listTile.addAll(0, allPath);
		}
		this.rewriteListPath(listForPath);
	}

	private void rewriteListPath(ArrayList<ArrayList<TilePath>> listForPath){
		this.listPath = new Path[listForPath.size()];
		
		int j=0;
		for (ArrayList<TilePath> list : listForPath){
			Log.d("pathNew","new path");
			Path path = new Path(list.size());
			for (int i = list.size()-1;i>=0;i--){
				TilePath tile = list.get(i);
				path.to(tile.getTileX(), tile.getTileY());
				Log.d("path", tile.getTileX()+" : "+tile.getTileY()+ " = "+tile.getTileColumn()+" : "+tile.getTileRow());
			}
			listPath[j] = path;
			j++;
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
			if (tile.direction == null) {
				tile.direction = Direction.Right;
				listTile.add(tile);
			}
		}
		if (column < a.getTileColumns() - 1
				&& a.getTMXTile(column + 1, line) instanceof TilePath) {
			tile = (TilePath) a.getTMXTile(column + 1, line);
			if (tile.direction == null) {
				tile.direction = Direction.Left;
				listTile.add(tile);
			}
		}
		if (line > 0 && a.getTMXTile(column, line - 1) instanceof TilePath) {
			tile = (TilePath) a.getTMXTile(column, line - 1);
			if (tile.direction == null) {
				tile.direction = Direction.Down;
				listTile.add(tile);
			}
		}
		if (column < a.getTileRows() - 1
				&& a.getTMXTile(column, line + 1) instanceof TilePath) {
			tile = (TilePath) a.getTMXTile(column, line + 1);
			if (tile.direction == null) {
				tile.direction = Direction.Up;
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
						this.lastTilePath.direction = Direction.Right;
					}
				}
				int y = 0;
				y = y + 1;
			}
		}
	}

	public Tile getBox(int x, int y) {
		return (Tile) this.tmxTiledMap.getTMXLayers().get(0).getTMXTileAt(x, y);
	}

}
