package electroacid.defense;

import com.stickycoding.Rokon.Texture;

import electroacid.defense.enums.Element;

public  class Tower {
	private Element element; 
	private int life;
	private int speed;
	private int fireRate;
	private int cost;
	private boolean fly;
	private int damage;
	private int targetNb;
	private int targetPriority ; // 1=nearest, 2=weakest, 3=strengtest
	private int level;
	private Texture texture;
	
	public Tower(Element _element,int _life, int _speed, int _fireRate, int _cost, boolean _fly, int _damage, int _targetNb, int _targetPriority, int _level,Texture _texture){
		this.element = _element;
		this.life = _life;
		this.speed = _speed;
		this.fireRate = _fireRate;
		this.cost = _cost;
		this.fly = _fly;
		this.damage = _damage;
		this.targetNb = _targetNb;
		this.targetPriority = _targetPriority;
		this.level = _level;
		this.texture = _texture;
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
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
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
	 * @return the fly
	 */
	public boolean isFly() {
		return fly;
	}

	/**
	 * @param fly the fly to set
	 */
	public void setFly(boolean fly) {
		this.fly = fly;
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
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * @param texture the texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	





}
