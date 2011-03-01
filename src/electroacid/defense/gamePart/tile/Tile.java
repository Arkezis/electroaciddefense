package electroacid.defense.gamePart.tile;

import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

/**
 * It's a tile on the game map
 * @author cilheo
 * @version 1.0b
 */
public abstract class Tile extends TMXTile{


	public Tile(int pGlobalTileID, int pTileColumn, int pTileRow,
			int pTileWidth, int pTileHeight, TextureRegion pTextureRegion) {
		super(pGlobalTileID, pTileColumn, pTileRow, pTileWidth, pTileHeight,
				pTextureRegion);
	}
	
	public Tile(TMXTile tile){
		super(tile.getGlobalTileID(), tile.getTileColumn(), tile.getTileRow(), 
				tile.getTileWidth(), tile.getTileHeight(),
				tile.getTextureRegion());
	}

	public int getMiddleX(){
		return this.getTileX()+this.getTileWidth()/2;
	}
	
	public int getMiddleY(){
		return this.getTileY()+this.getTileHeight()/2;
	}
}
