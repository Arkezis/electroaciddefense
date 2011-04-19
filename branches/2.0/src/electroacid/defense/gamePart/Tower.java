package electroacid.defense.gamePart;

import java.util.LinkedList;

import org.anddev.andengine.entity.layer.ILayer;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.modifier.BaseShapeDurationModifier;
import org.anddev.andengine.entity.shape.modifier.IShapeModifier;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import electroacid.defense.gamePart.enums.Element;
import electroacid.defense.gamePart.enums.ShootPriority;
import electroacid.defense.gamePart.game.GenericGame;
import electroacid.defense.gamePart.map.GenericMap;
import electroacid.defense.gamePart.observ.ObservateurTower;
import electroacid.defense.gamePart.tile.TilePath;


/**
 * The tower's class
 * @author Arkezis
 * @version 1.0b
 */
public  class Tower extends AnimatedSprite implements Cloneable,ObservateurTower{
	/**
	 * The shoot shooted by the tower
	 */
	public Line fire = new Line(-10, -10, -10, -10,3);
	/**
	 * Cost of the tower
	 */
	public int cost;

	/**
	 * Damage of the tower
	 */
	public int damage;
	/**
	 * Level of the tower 
	 */
	public int level;

	public Sprite upgradeSprite;
	/**
	 * Coefficient used for the tower upgrade
	 */
	public double upgrade =  1.25;
	/**
	 * Coefficient used to destruct the tower
	 */
	public double destroy = 0.25;
	/**
	 * The shooting area
	 */
	public int shootArea;
	/**
	 * The shootable box
	 */
	public LinkedList<TilePath> boxDetectionList;
	/**
	 * Coordonate of the tower
	 */

	public LinkedList<Creature> listTarget;
	
	public ShootPriority targetPriority ;
	public Creature weakest_highest;

	// TODO : Not implemented yet
	public int targetNb;

	public Element element;	
	/**
	 * The capacity to shoot a flying creature
	 */
	public boolean canTargetFly;
	/**
	 * The speed as a tower can shoot
	 */
	public int fireRate;
	public int initialFireRate;
	

	
	/**
	 * The constructor of the towers
	 * @param _element  Not implemented yet
	 * @param _life The life of the tower
	 * @param _fireRate The speed as a tower can shoot
	 * @param _cost The cost of the tower
	 * @param _fly The capacity to shoot a flying creature
	 * @param _damage The damage done by the box
	 * @param _targetNb Not implemented yet
	 * @param _targetPriority Not implemented yet
	 * @param _level Level of the tower 
	 * @param _layout Layout of the tower 
	 * @param _shootArea The shooting area
	 */
	public Tower(Element _element, int _fireRate, int _cost, boolean _fly,
			int _damage, int _targetNb, ShootPriority _targetPriority, int _level,TextureRegion texture,int _shootArea){
		
		
		super(0, 0, 32, 32, (TiledTextureRegion) texture);
		this.element = _element;
		this.fireRate = _fireRate;
		this.initialFireRate = _fireRate;
		this.cost = _cost;
		this.canTargetFly = _fly;
		this.damage = _damage;
		this.targetNb = _targetNb;
		this.targetPriority = _targetPriority;
		this.level = _level;
		this.shootArea = _shootArea;
	}

	protected void onManagedUpdate(final float pSecondsElapsed) {
		this.initialFireRate-=pSecondsElapsed;
		if (this.initialFireRate<=0){
			this.initialFireRate=this.fireRate;
			
			this.attack(listTarget, Play.scene.getLayer(Play.LAYER_CREA));
		}
		
	}

	/**
	 * This method create the LinkedList used to shoot the creature (depending of the shootArea of the box)
	 * @param ogField The AngleObject where the shoot should be add
	 */
	public void detection(ILayer container){
		if(!listTarget.isEmpty()) this.attack(listTarget,container); 
	}
	/**
	 * This method is used to shoot the creature. An Shoot's instance is created and the life is lost by the creature 
	 * @param linkedList The creature's list to shoot
	 * @param ogField
	 */
	private void attack(LinkedList<Creature> listTarget, final ILayer container) {

		if (!this.listTarget.isEmpty()){			
			Creature target;
			if (this.targetPriority!=ShootPriority.FIRSTIN_FIRSTDIE)
				target=this.weakest_highest;
			else
				target = this.listTarget.get(0);

			// TODO : Il  faut faire pivoter la tour !  this.getSprite().setRotation(A_CALCULER)
	
			fire.setPosition(this.getX(), this.getY(),target.getX(), target.getY());
			
			
			switch(this.element){
			case Electricity:
				fire.setColor(1, (float) 0.8, 0);
				break;
			case Fire:
				fire.setColor(1,0,0);
				break;
			case Iron:
				fire.setColor((float)0.4, (float)0.4, (float)0.4);
				break;
			case Water:
				fire.setColor(0, 0, 1);
				break;
			}
			container.addEntity(fire);
			// Apply the element vs element modifiers
			double modifiers = this.element.getModifier(target.element);
			target.loseLife((int)(this.damage*modifiers));		
		}
	}

	/**
	 * Change the position of the tower (used when a tower is created (we use cloning !!))
	 * @param x
	 * @param y
	 */
	public void changePosition(int _x, int _y){
		this.setPosition(_x+16, _y+16);
	}
	
	/**
	 * Upgrading a tower (thanks to upgrade coefficient)
	 * @param ogField 
	 * @param g Game's information
	 */
/*	public void upgrade(AngleSurfaceView mgl, ILayer container){
		GenericGame game = GenericGame.getInstance();
		//mGl.removeObject(this.upgradeSprite);
		
		
		
		this.upgradeSprite = new AngleSprite(new AngleSpriteLayout(mgl,32,level*32,R.drawable.tilemap,128,128,32,32));
		this.upgradeSprite.setPosition(this.x+16, this.y+(16*level)-(32*(level-1)));
		container.addEntity(this.upgradeSprite);
		this.cost =  (int)Math.ceil((this.cost*upgrade));
		this.damage = (int)Math.ceil((this.damage*upgrade));
		this.level++;
		game.setMoney(game.getMoney()-this.cost);
	}*/

	/**
	 * Destroy a tower : Remove the object from the AngleObject corresponding and earning money
	 * @param g
	 * @param og
	 */
	public void destroy(ILayer container, ILayer containerUpgradeTower){
		GenericGame game = GenericGame.getInstance();
		for (TilePath tile:this.boxDetectionList) tile.delObservateur(this);
		container.removeEntity(this);
		containerUpgradeTower.removeEntity(this.upgradeSprite);
		game.setMoney((int)(game.money+this.cost*this.destroy));
	}

	/**
	 * Cloning the tower
	 * @param bnewTower2Layout 
	 */
	public Object clone( ) {
		Tower tower = null;
		try {
			tower = (Tower) super.clone();
		} catch(CloneNotSupportedException cnse) {
			cnse.printStackTrace(System.err);
		}	    

		this.upgradeSprite=null;
		tower.weakest_highest=null;
		return tower;
	}

	/**
	 * @param targetPriority the targetPriority to set
	 */
	public void setTargetPriority(ShootPriority _targetPriority) {
		this.targetPriority = _targetPriority;
		if (this.targetPriority!=ShootPriority.FIRSTIN_FIRSTDIE){
			this.recalculWH();
		}
	}

	/**
	 * Set the boxpath where the tower have to focus if a creature is coming ...
	 * @param x The x of the boxbuildable containing the tower
	 * @param y The y of the boxbuildable containing the tower
	 * @param matrice The matrice
	 */
	public void setListDetection(int width,int height,  GenericMap matrice) {
		this.boxDetectionList=new LinkedList<TilePath>();
		this.listTarget = new LinkedList<Creature>();
		this.weakest_highest=null;
		int maxAreaForX = this.shootArea*width;
		int maxAreaForY = this.shootArea*height;

		for (int i = (int)this.getY()-maxAreaForY;i<=this.getY()+maxAreaForY;i+=height){
			for (int j = (int)this.getX()-maxAreaForX;j<=this.getX()+maxAreaForX;j+=width){
				if (matrice.getBox(j, i) instanceof TilePath){
					this.boxDetectionList.add((TilePath) matrice.getBox(j,i));
					TilePath box =(TilePath) matrice.getBox(j, i);
					box.addObservateur(this);
				}
			}
		}
	}

	@Override
	public void add(Object c) {
		if (!this.listTarget.contains(c)){
			Creature crea = (Creature) c;
			this.listTarget.add(crea);

			switch(this.targetPriority){
			case WEAKEST:
				if (this.weakest_highest==null || crea.maxLife<this.weakest_highest.maxLife) this.weakest_highest=crea;
				break;
			case HIGHEST:
				if (this.weakest_highest==null || crea.maxLife>this.weakest_highest.maxLife) this.weakest_highest=crea;
				break;
			case FIRSTIN_FIRSTDIE:
				break;
			}
		}
	}

	@Override
	public void remove(Object c) {
		this.listTarget.remove(c);
		if (this.weakest_highest==c){
			this.recalculWH();
		}
	}

	private void recalculWH(){
		this.weakest_highest=null;
		for (Creature c : this.listTarget){
			switch(this.targetPriority){
			case WEAKEST:
				if (this.weakest_highest==null || c.maxLife<this.weakest_highest.maxLife)
					this.weakest_highest=c;
				break;
			case HIGHEST:
				if (this.weakest_highest==null || c.maxLife>this.weakest_highest.maxLife)
					this.weakest_highest=c;
				break;
			case FIRSTIN_FIRSTDIE:
				break;
			}			
		}
	}

	/**
	 * @return the listTarget
	 */
	public LinkedList<Creature> getListTarget() {
		return listTarget;
	}

	/**
	 * @param listTarget the listTarget to set
	 */
	public void setListTarget(LinkedList<Creature> _listTarget) {
		this.listTarget = _listTarget;
		this.weakest_highest=null;
		this.recalculWH();
	}



}
