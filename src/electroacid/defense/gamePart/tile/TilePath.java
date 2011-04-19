package electroacid.defense.gamePart.tile;

import java.util.ArrayList;

import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.opengl.texture.region.TextureRegion;


import electroacid.defense.gamePart.Creature;
import electroacid.defense.gamePart.Tower;
import electroacid.defense.gamePart.enums.Direction;
import electroacid.defense.gamePart.observ.ObservableBoxPath;
import electroacid.defense.gamePart.observ.ObservateurTower;

/**
 * It's a box which can host a creature
 * @author cilheo
 * @version 1.0b
 */
public class TilePath extends Tile implements ObservableBoxPath{

	/** Creatures' list who are actually on the box */
	public ArrayList<Creature> listCreature = new ArrayList<Creature>();
	public ArrayList<ObservateurTower> listTower = new ArrayList<ObservateurTower>();

	/** Direction that the creature must take */
	public Direction direction=null;

	
	
	
	
	public TilePath(int pGlobalTileID, int pTileColumn, int pTileRow,
			int pTileWidth, int pTileHeight, TextureRegion pTextureRegion) {
		super(pGlobalTileID, pTileColumn, pTileRow, pTileWidth, pTileHeight,
				pTextureRegion);
		this.listCreature = new ArrayList<Creature>();
	}
	public TilePath(TMXTile tile){
		this(tile.getGlobalTileID(), tile.getTileColumn(), tile.getTileRow(), 
				tile.getTileWidth(), tile.getTileHeight(),
				tile.getTextureRegion());
	}

	
	
	/** add a creature to the listCreature */
	public void addCreature(Creature creature){
		this.listCreature.add(creature);
		this.updateObservateurAdd(creature);
	}
	
	public boolean removeCreature(Creature creature){
		this.updateObservateurRemove(creature);
		return this.listCreature.remove(creature);
	}
	
	@Override
	public void addObservateur(ObservateurTower obs) {this.listTower.add(obs);}
	@Override
	public void delObservateur(ObservateurTower obs) {this.listTower.remove(obs);}
	@Override
	public void delAllObservateur() {this.listTower.clear();}
	@Override
	public void updateObservateurRemove(Object c) {for (ObservateurTower t : this.listTower) {t.remove(c);}}
	@Override
	public void updateObservateurAdd(Object c) {for (ObservateurTower t : this.listTower){ t.add(c);}}
	@Override
	public void updateObservateurRemoveAndAdd(Object c) {
		// TODO Auto-generated method stub
		
	}
}
