package electroacid.defense;

import java.util.LinkedList;

import observ.ObservateurTower;

import com.android.angle.AngleFont;
import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleString;

import electroacid.defense.box.BoxPath;
import electroacid.defense.enums.Element;
import electroacid.defense.enums.ShootPriority;
import electroacid.defense.game.GenericGame;
import electroacid.defense.gui.Shoot;
import electroacid.defense.map.GenericMap;


/**
 * The tower's class
 * @author Arkezis
 * @version 1.0b
 */
public  class Tower implements Cloneable,ObservateurTower{
	/**
	 * The shoot shooted by the tower
	 */
	Shoot fire;
	/**
	 * Cost of the tower
	 */
	private int cost;
	/**
	 * @return the destroy
	 */
	public double getDestroy() {
		return destroy;
	}

	/**
	 * @param destroy the destroy to set
	 */
	public void setDestroy(double destroy) {
		this.destroy = destroy;
	}

	/**
	 * Damage of the tower
	 */
	private int damage;
	/**
	 * Level of the tower 
	 */
	private int level;
	/** 
	 * Sprite of the tower
	 */
	private AngleSprite sprite;
	/**
	 * Coefficient used for the tower upgrade
	 */
	private double upgrade =  1.25;
	/**
	 * Coefficient used to destruct the tower
	 */
	private double destroy = 0.25;
	/**
	 * The shooting area
	 */
	private int shootArea;
	/**
	 * The shootable box
	 */
	private LinkedList<BoxPath> boxDetectionList;
	/**
	 * Coordonate of the tower
	 */
	
	private LinkedList<Creature> listTarget;
	
	private int x,y;
	
	private ShootPriority targetPriority ;
	private Creature weakest_highest;
	
	// TODO : Not implemented yet
	private int targetNb;
	
	private Element element;	
	/**
	 * The capacity to shoot a flying creature
	 */
	private boolean canTargetFly;
	/**
	 * The speed as a tower can shoot
	 */
	private int fireRate;
	
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
	public Tower(Element _element, int _fireRate, int _cost, boolean _fly, int _damage, int _targetNb, ShootPriority _targetPriority, int _level,AngleSpriteLayout _layout,int _shootArea){
		this.element = _element;
		this.fireRate = _fireRate;
		this.cost = _cost;
		this.canTargetFly = _fly;
		this.damage = _damage;
		this.targetNb = _targetNb;
		this.targetPriority = _targetPriority;
		this.level = _level;
		this.sprite = new AngleSprite(_layout);
		this.shootArea = _shootArea;
	}
	
	/**
	 * @return the shootArea
	 */
	public int getshootArea() {
		return shootArea;
	}

	/**
	 * The constructor of the tower (with a Sprite !)
	 * @param _element  Not implemented yet
	 * @param _life The life of the tower
	 * @param _fireRate The speed as a tower can shoot
	 * @param _cost The cost of the tower
	 * @param _fly The capacity to shoot a flying creature
	 * @param _damage The damage done by the box
	 * @param _targetNb Not implemented yet
	 * @param _targetPriority Not implemented yet
	 * @param _level Level of the tower 
	 * @param _sprite Sprite of the tower 
	 * @param _shootArea The shooting area
	 */
	public Tower(Element _element, int _fireRate, int _cost, boolean _fly, int _damage, int _targetNb, ShootPriority _targetPriority, int _level,AngleSprite _sprite,int _shootArea){
		this.element = _element;
		this.fireRate = _fireRate;
		this.cost = _cost;
		this.canTargetFly = _fly;
		this.damage = _damage;
		this.targetNb = _targetNb;
		this.targetPriority = _targetPriority;
		this.level = _level;
		this.sprite = _sprite;
		this.shootArea = _shootArea;
	}	
	
	/**
	 * This method create the LinkedList used to shoot the creature (depending of the shootArea of the box)
	 * @param ogField The AngleObject where the shoot should be add
	 */
	public void detection(AngleObject ogField){
/*		this.listTarget = new LinkedList<Creature>();
		for(int i=0;i<this.boxDetectionList.size();i++){
			if(!this.boxDetectionList.get(i).getListCreature().isEmpty()){
				listTarget.addAll(boxDetectionList.get(i).getListCreature());
			}
		}*/
		if(!listTarget.isEmpty()) this.attack(listTarget,ogField); 
	}
	/**
	 * This method is used to shoot the creature. An Shoot's instance is created and the life is lost by the creature 
	 * @param linkedList The creature's list to shoot
	 * @param ogField
	 */
	private void attack(LinkedList<Creature> listTarget, AngleObject ogField) {
		
		if (!this.listTarget.isEmpty()){			
			Creature target;
			if (this.targetPriority!=ShootPriority.FIRSTIN_FIRSTDIE)
				target=this.weakest_highest;
			else
				target = this.listTarget.get(0);
			
			

			switch(this.element){
			case Electricity:
				fire = new Shoot(this.x, this.y,target.getSprite().mPosition.mX,target.getSprite().mPosition.mY,
						ogField,1,(float)0.8,0,3);
				break;
			case Fire:
				fire = new Shoot(this.x, this.y,target.getSprite().mPosition.mX,target.getSprite().mPosition.mY,
						ogField,1,0,0,3);
				break;
			case Iron:
				fire = new Shoot(this.x, this.y, target.getSprite().mPosition.mX,target.getSprite().mPosition.mY,
						ogField,(float)0.4, (float)0.4, (float)0.4,3);
				break;
			case Water:
				fire = new Shoot(this.x, this.y, target.getSprite().mPosition.mX, target.getSprite().mPosition.mY,
						ogField,0, 0, 1,3);
				break;
		}
			// Apply the element vs element modifiers
			double modifiers = this.element.getModifier(target.getElement());
			target.loseLife((int)(this.damage*modifiers));		
		}
	}


	/**
	 * @param shootArea the shootArea to set
	 */
	public void setshootArea(int shootArea) {
		this.shootArea = shootArea;
	}
	/**
	 * Change the position of the tower (used when a tower is created (we use cloning !!))
	 * @param x
	 * @param y
	 */
	public void changePosition(int _x, int _y){
		this.sprite.mPosition.set(_x+16, _y+16);
		this.x=_x; this.y=_y;
	}
	
	/**
	 * @return the sprite
	 */
	public AngleSprite getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(AngleSprite sprite) {
		this.sprite = sprite;
	}
	/**
	 * Upgrading a tower (thanks to upgrade coefficient)
	 * @param g Game's information
	 */
	public void upgrade(GenericGame g){
		this.cost =  (int)Math.ceil((this.cost*upgrade));
		this.damage = (int)Math.ceil((this.damage*upgrade));
		this.level++;
		g.setMoney(g.getMoney()-this.cost);
	}
	
	/**
	 * Destroy a tower : Remove the object from the AngleObject corresponding and earning money
	 * @param g
	 * @param og
	 */
	public void destroy(GenericGame g,AngleObject og){
		for (BoxPath box:this.boxDetectionList) box.delObservateur(this);
		og.removeObject(this.sprite);
		g.setMoney((int)(g.getMoney()+this.cost*this.destroy));
	}
	
	/**
	 * @return the upgrade
	 */
	public double getUpgrade() {
		return upgrade;
	}

	/**
	 * @param upgrade the upgrade to set
	 */
	public void setUpgrade(double upgrade) {
		this.upgrade = upgrade;
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
	    tower.sprite = new AngleSprite(this.sprite.roLayout);   
	    tower.weakest_highest=null;
	    return tower;
	}

	/**
	 * @return the element
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(Element element) {
		this.element = element;
	}

	/**
	 * @return the fireRate
	 */
	public int getFireRate() {
		return fireRate;
	}

	/**
	 * @param fireRate the fireRate to set
	 */
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the canTargetFly
	 */
	public boolean isCanTargetFly() {
		return canTargetFly;
	}

	/**
	 * @param canTargetFly the canTargetFly to set
	 */
	public void setCanTargetFly(boolean canTargetFly) {
		this.canTargetFly = canTargetFly;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * @return the targetNb
	 */
	public int getTargetNb() {
		return targetNb;
	}

	/**
	 * @param targetNb the targetNb to set
	 */
	public void setTargetNb(int targetNb) {
		this.targetNb = targetNb;
	}

	/**
	 * @return the targetPriority
	 */
	public ShootPriority getTargetPriority() {
		return targetPriority;
	}

	/**
	 * @param targetPriority the targetPriority to set
	 */
	public void setTargetPriority(ShootPriority targetPriority) {
		this.targetPriority = targetPriority;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Set the boxpath where the tower have to focus if a creature is coming ...
	 * @param x The x of the boxbuildable containing the tower
	 * @param y The y of the boxbuildable containing the tower
	 * @param matrice The matrice
	 */
	public void setListDetection(int width,int height,  GenericMap matrice) {
		this.boxDetectionList=new LinkedList<BoxPath>();
		this.listTarget = new LinkedList<Creature>();
		this.weakest_highest=null;
		int maxAreaForX = this.shootArea*width;
		int maxAreaForY = this.shootArea*height;
		
		for (int i = this.y-maxAreaForY;i<=this.y+maxAreaForY;i+=height){
			for (int j = this.x-maxAreaForX;j<=this.x+maxAreaForX;j+=width){
				if (matrice.getBox(j, i) instanceof BoxPath){
					this.boxDetectionList.add((BoxPath) matrice.getBox(j,i));
					BoxPath box =(BoxPath) matrice.getBox(j, i);
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
				if (this.weakest_highest==null || crea.getMaxLife()<this.weakest_highest.getMaxLife()) this.weakest_highest=crea;
				break;
			case HIGHEST:
				if (this.weakest_highest==null || crea.getMaxLife()>this.weakest_highest.getMaxLife()) this.weakest_highest=crea;
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
				if (this.weakest_highest==null || c.getMaxLife()<this.weakest_highest.getMaxLife())
					this.weakest_highest=c;
				break;
			case HIGHEST:
				if (this.weakest_highest==null || c.getMaxLife()>this.weakest_highest.getMaxLife())
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
	public void setListTarget(LinkedList<Creature> listTarget) {
		this.listTarget = listTarget;
		this.weakest_highest=null;
		this.recalculWH();
	}


	
}
