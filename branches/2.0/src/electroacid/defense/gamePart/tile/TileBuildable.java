package electroacid.defense.gamePart.tile;

import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.util.Log;

import electroacid.defense.gamePart.Tower;
import electroacid.defense.gamePart.game.GenericGame;
import electroacid.defense.gamePart.map.GenericMap;

/**
 * It's a box which can host a tower
 * @author cilheo
 * @version 1.0b
 */
public class TileBuildable extends Tile {

	/** A tower host by the box */
	public Tower tower=null; 

	public TileBuildable(int pGlobalTileID, int pTileColumn, int pTileRow,
			int pTileWidth, int pTileHeight, TextureRegion pTextureRegion) {
		super(pGlobalTileID, pTileColumn, pTileRow, pTileWidth, pTileHeight,
				pTextureRegion);
	}
	
	public TileBuildable(TMXTile tile){
		super(tile.getGlobalTileID(), tile.getTileColumn(), tile.getTileRow(), 
				tile.getTileWidth(), tile.getTileHeight(),
				tile.getTextureRegion());
	}

	/**
	 * Add a tower and change the actual tower host by the box
	 * @param _tower the tower to add or change
	 * @param game parametre of the game
	 * @return false if tower is null or tower is too much expensive
	 */
	public boolean changeTower(Tower _tower, GenericMap matrice){
		if (this.tower == null && _tower != null) {
			GenericGame game = GenericGame.getInstance();
			if(game.money > _tower.cost){
				this.tower = _tower;
				this.tower.changePosition(this.getTileX()-this.getTileHeight()/2,
						this.getTileY()-this.getTileWidth()/2);
				game.addMoney(-this.tower.cost);
				this.tower.setHeight(this.getTileHeight()); // set the size of the sprite
				this.tower.setWidth(this.getTileWidth());
				this.tower.setListDetection(this.getTileWidth(), this.getTileHeight(), matrice);
				return true;
			}
		}
		return false;
	}
}
