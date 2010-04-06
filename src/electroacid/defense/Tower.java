package electroacid.defense;

import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;

import electroacid.defense.enums.Element;

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
	
	public Tower(Element _element,int _life, int _fireRate, int _cost, boolean _fly, int _damage, int _targetNb, int _targetPriority, int _level,AngleSpriteLayout _layout){
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
	}
	
	public Tower(Element _element,int _life, int _fireRate, int _cost, boolean _fly, int _damage, int _targetNb, int _targetPriority, int _level,AngleSprite _sprite){
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

}
