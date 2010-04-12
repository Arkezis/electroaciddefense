package electroacid.defense;

import java.util.LinkedList;

import utils.AngleSegment;

import android.util.Log;

import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;

import electroacid.defense.box.BoxPath;
import electroacid.defense.enums.Element;
import electroacid.defense.gui.Shoot;
import electroacid.defense.map.GenericMap;

public  class Tower implements Cloneable{
	private Element element; 
	private int life;
	private int fireRate;
	private int cost;
	private boolean canTargetFly;
	private int damage;
	private int targetNb;
	private int targetPriority ; // 1=nearest, 2=weakest, 3=strengtest
	private int level;
	private AngleSprite sprite;
	private double upgrade =  1.25;
	private double destroy = 0.25;
	private int boxArea;
	private LinkedList<BoxPath> boxDetectionList;
	private int x,y;
	
	public Tower(Element _element,int _life, int _fireRate, int _cost, boolean _fly, int _damage, int _targetNb, int _targetPriority, int _level,AngleSpriteLayout _layout,int _boxArea){
		this.element = _element;
		this.life = _life;
		this.fireRate = _fireRate;
		this.cost = _cost;
		this.canTargetFly = _fly;
		this.damage = _damage;
		this.targetNb = _targetNb;
		this.targetPriority = _targetPriority;
		this.level = _level;
		this.sprite = new AngleSprite(_layout);
		this.boxArea = _boxArea;
	}
	
	/**
	 * @return the boxArea
	 */
	public int getBoxArea() {
		return boxArea;
	}


	public Tower(Element _element,int _life, int _fireRate, int _cost, boolean _fly, int _damage, int _targetNb, int _targetPriority, int _level,AngleSprite _sprite,int _boxArea){
		this.element = _element;
		this.life = _life;
		this.fireRate = _fireRate;
		this.cost = _cost;
		this.canTargetFly = _fly;
		this.damage = _damage;
		this.targetNb = _targetNb;
		this.targetPriority = _targetPriority;
		this.level = _level;
		this.sprite = _sprite;
		this.boxArea = _boxArea;
	}	
	
	/**
	 * Detection method : Verify if a creature is fireable !
	 * @param ogField 
	 */
	public void detection(AngleObject ogField){
		Log.d("DETECTION", "Nb d'elements : "+this.boxDetectionList.size());
		for(int i=0;i<this.boxDetectionList.size();i++){
			//Log.d("ATTACK", "Are we under attack ? ");
			if(!this.boxDetectionList.get(i).getListCreature().isEmpty()){
				Log.d("ATTACK", this.toString()+"is under attack in "+this.boxDetectionList.get(i).getX()+","+this.boxDetectionList.get(i).getY());
				this.attack(this.boxDetectionList.get(i).getListCreature(),ogField);
			}
		}
	}
	
	
	private void attack(LinkedList<Creature> linkedList, AngleObject ogField) {
		/* Procédure d'attaque
		 *  - Récupération du type d'attaque (Nearest...)
		 *  - Répartition des tirs (en fonction du targetNb et du fireRate)
		 *  - Résolution des tirs
		 */
		LinkedList<Creature> listTarget = new LinkedList<Creature>();
		if(this.targetPriority==1){
			// comparer la position de la tour avec la position des créas
		}else if(this.targetPriority==2){
			// range dans une nouvelle liste, les créas par ordre de vie croissante
		}else if(this.targetPriority==3){
			// range dans une nouvelle liste, les créas par ordre de vie dec
		}
		
		// combien de créas à tirer 
		int nbCrea = Math.min(this.targetNb, linkedList.size());
		 
		// dégats par créas
		int nbDamagePerCrea = 0,reste = 0;
		nbDamagePerCrea = this.damage/nbCrea;
		if(this.targetNb%linkedList.size()!=0) reste = this.damage%nbCrea; // si tout les dégats ne peuvent etre repartis equitablement
		
		// on multiplie le nombre de dégats par le fireRate pour représenter le tir multiple
		nbDamagePerCrea *= fireRate;
		reste *= fireRate;
		
		for(int i=0;i<listTarget.size();i++){
			/*listTarget.get(i).loseLife(nbDamagePerCrea);
			AngleSegment fire = new AngleSegment(57, 80, 118, 42);
			ogField.addObject(fire);*/
		}
		
		// Et il faut gérer le Reste !!!
		
		
		
		for(int i=0;i<Math.min(this.targetNb, linkedList.size());i++){
			Log.d("ATTACK", "La créature "+ i +" est touchée");
			Shoot fire = new Shoot(this.y, this.x, linkedList.get(i).getSprite().mPosition.mX, linkedList.get(i).getSprite().mPosition.mY,ogField);
		}
	}


	/**
	 * @param boxArea the boxArea to set
	 */
	public void setBoxArea(int boxArea) {
		this.boxArea = boxArea;
	}
	
	public void changePosition(int x, int y){
		this.sprite.mPosition.set(x, y);
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

	public void upgrade(Game g){
		this.life = (int)(this.life*upgrade);
		this.fireRate =(int)(this.fireRate*upgrade);
		this.cost =  (int)(this.cost*upgrade);
		this.damage = (int)(this.damage*upgrade);
		this.targetNb = (int)(this.targetNb*upgrade);
		this.level++;
		g.setMoney(g.getMoney()-this.cost);
	}
	
	public void destroy(Game g,AngleObject og){
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

	public Object clone() {
	    Tower tower = null;
	    try {
	      	tower = (Tower) super.clone();
	    } catch(CloneNotSupportedException cnse) {
	      	cnse.printStackTrace(System.err);
	    }
	    //Don't clone the texture, it's the same instance in the atlas
	    // u don't need to create a new
	    
	    tower.sprite = new AngleSprite(this.sprite.roLayout);
	    
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
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(int life) {
		this.life = life;
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
	public int getTargetPriority() {
		return targetPriority;
	}

	/**
	 * @param targetPriority the targetPriority to set
	 */
	public void setTargetPriority(int targetPriority) {
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
	public void setListDetection(int y,int x,  GenericMap matrice) {
		int mult=0;
		Log.d("ATTACK", "Set de la liste de detection pour la box "+x+","+y);
		this.boxDetectionList=new LinkedList<BoxPath>();
		for(int i=1;i<=this.boxArea;i++){
			mult+=32;
			if(matrice.getBox(x+mult, y) instanceof BoxPath) {
				Log.d("DETECTION", "Looking 1");
				this.boxDetectionList.add((BoxPath) matrice.getBox(x+mult, y));}
			if(matrice.getBox(x+mult, y+mult) instanceof BoxPath) {
				Log.d("DETECTION", "Looking 2");
				this.boxDetectionList.add((BoxPath) matrice.getBox(x+mult, y+mult));}
			if(matrice.getBox(x, y+mult) instanceof BoxPath) {
				Log.d("DETECTION", "Looking 3");
				this.boxDetectionList.add((BoxPath) matrice.getBox(x, y+mult));}
			if(matrice.getBox(x-mult, y+mult) instanceof BoxPath) {
				Log.d("DETECTION", "Looking 4");
				this.boxDetectionList.add((BoxPath) matrice.getBox(x-mult, y+mult));}
			if(matrice.getBox(x-mult, y) instanceof BoxPath) {
				Log.d("DETECTION", "Looking 5");
				this.boxDetectionList.add((BoxPath) matrice.getBox(x-mult, y));}
			if(matrice.getBox(x-mult, y-mult) instanceof BoxPath) {
				Log.d("DETECTION", "Looking 6");
				this.boxDetectionList.add((BoxPath) matrice.getBox(x-mult, y-mult));}
			if(matrice.getBox(x, y-mult) instanceof BoxPath) {
				Log.d("DETECTION", "Looking 7");
				this.boxDetectionList.add((BoxPath) matrice.getBox(x, y-mult));}
			if(matrice.getBox(x+mult, y-mult) instanceof BoxPath) {
				Log.d("DETECTION", "Looking 8");
				this.boxDetectionList.add((BoxPath) matrice.getBox(x+mult, y-mult));}
		}
	}

	public void setPosition(int _x, int _y){
		this.x=_x; this.y=_y;
		Log.d("DEBUGTAG", "New tower in ("+x+","+y+")");
	}
	
}
