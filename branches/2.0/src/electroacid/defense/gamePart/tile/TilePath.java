package electroacid.defense.gamePart.tile;

import java.util.ArrayList;
import java.util.LinkedList;

import org.anddev.andengine.entity.layer.ILayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.opengl.texture.region.TextureRegion;


import electroacid.defense.gamePart.Creature;
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
	private LinkedList<Creature> listCreature;

	/** Direction that the creature must take */
	private Direction direction=null;

	/** the next path after this */
	private TilePath nextPath;

	private int numberMaxPred=0;
	public long time=System.currentTimeMillis();
	private int numberPred=0;

	/** Observator's list */
	private ArrayList<ObservateurTower> listObservateur = new ArrayList<ObservateurTower>();

	public TilePath(int pGlobalTileID, int pTileColumn, int pTileRow,
			int pTileWidth, int pTileHeight, TextureRegion pTextureRegion) {
		super(pGlobalTileID, pTileColumn, pTileRow, pTileWidth, pTileHeight,
				pTextureRegion);
		this.listCreature = new LinkedList<Creature>();
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



	/**
	 * Action during one step
	 * @param game game's parameter
	 * @param container creature's container
	 */
	public void nextStep(ILayer container){

		boolean nextOk = this.numberPred==1;

		if (nextOk){
			if (this.nextPath== null) {
				for (int i=0;i<this.listCreature.size();i++) {
					this.updateObservateurRemove(this.listCreature.get(i));
					this.listCreature.get(i).destroy(container,false);
					this.listCreature.remove(i--);
				}
			}
			for (int i=0;i<this.listCreature.size();i++){
				Creature creature = this.listCreature.get(i);
				if(creature.getLife()<=0){
					this.updateObservateurRemove(creature);
					creature.destroy(container, true);
					this.listCreature.remove(i--);
				}else{



					float nextY = creature.getSprite().getX();
					float nextX = creature.getSprite().getY();

					if (!creatureInBox(nextX, nextY)){
						this.updateObservateurRemoveAndAdd(creature);
						this.nextPath.addCreature(creature);
						this.listCreature.remove(i--);
					}else {
						switch(this.direction) {
						case Up   :nextY -= creature.getSpeed();break;
						case Down :nextY += creature.getSpeed();break;
						case Left :nextX -= creature.getSpeed();break;
						case Right:nextX += creature.getSpeed();break;
						}
						creature.getSprite().setPosition(nextX, nextY);
						creature.getSprite().setRotation(creature.getSpeed()*5);
					}
				}
			}
		}
		if (nextOk){
			this.numberPred=this.numberMaxPred;
			if (this.nextPath!=null) this.nextPath.nextStep(container);
		} else {
			this.numberPred--;
		}
	}

	private boolean creatureInBox(float x,float y){
		boolean test = false;
		switch(this.direction) {
		case Up   :
			test = y>this.getTileY()-this.getTileHeight()/2.0 && y>0;
			break;
		case Down :
			test = this.nextPath==null ? 
					y<this.getTileY()+this.getTileHeight() 
					: y<this.getTileY()+1.5*this.getTileHeight();
			break;
		case Left :
			test = x>this.getTileX()-this.getTileWidth()/2.0 && x>0;
			break;
		case Right:
			test = this.nextPath==null ? 
					x<this.getTileX()+this.getTileWidth() 
					: x<this.getTileX()+1.5*this.getTileWidth();
			break;
		}
		return test;
	}

	/** @return the listCreature */
	public LinkedList<Creature> getListCreature() {return listCreature;}

	/** @param listCreature the listCreature to set */
	public void setListCreature(LinkedList<Creature> listCreature) {this.listCreature = listCreature;}

	/** @return the direction */
	public Direction getDirection() {return direction;}

	/** @param direction the direction to set */
	public void setDirection(Direction direction) {this.direction = direction;}

	/** @return the nextPath */
	public TilePath getNextPath() {return nextPath;}

	/** @param nextPath the nextPath to set */
	public void setNextPath(TilePath nextPath) {this.nextPath = nextPath;}

	@Override
	public void addObservateur(ObservateurTower obs) {
		this.listObservateur.add(obs);
		for(Creature c :this.listCreature) obs.add(c);
	}

	@Override
	public void delAllObservateur() {
		this.listObservateur = new ArrayList<ObservateurTower>();
	}

	@Override
	public void delObservateur(ObservateurTower obs) {
		this.listObservateur.remove(obs);
	}

	@Override
	public void updateObservateurAdd(Object c) {
		for (ObservateurTower obs : this.listObservateur) {
			obs.add(c);
		}
	}

	@Override
	public void updateObservateurRemoveAndAdd(Object c){
		if (this.nextPath!=null){
			for (ObservateurTower obs : this.listObservateur) {
				if (!this.nextPath.listObservateur.contains(obs)){
					obs.remove(c);
				}
			}
		}else 
			this.updateObservateurRemove(c);
	}

	@Override
	public void updateObservateurRemove(Object c) {
		for (ObservateurTower obs : this.listObservateur) obs.remove(c);
	}

	/**
	 * @return the numberMaxPred
	 */
	public int getNumberMaxPred() {
		return numberMaxPred;
	}

	public void addNumberMaxPred(){
		this.numberMaxPred++;
		this.numberPred++;
	}

	/**
	 * @return the numberPred
	 */
	public int getNumberPred() {
		return numberPred;
	}

}
