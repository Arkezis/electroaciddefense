package electroacid.defense;

public abstract class Tower {
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
	
	/* GETTER */
	public Element getElement(){
		return this.element;
	}
	public int getLife(){
		return this.life;
	}	
	public int getSpeed(){
		return this.speed;
	}
	public int getFireRate(){
		return this.fireRate;
	}
	public int getCost(){
		return this.cost;
	}
	public boolean getFly(){
		return this.fly;
	}
	public int getDamage(){
		return this.damage;
	}
	public int getTargetNb(){
		return this.targetNb;
	}
	public int getTargetPriority(){
		return this.targetPriority;
	}
	public int getStringTargetPriority(){
		switch(this.targetPriority){
		case 1:
			return R.string.targetPriority1;
		case 2: 
			return R.string.targetPriority2;
		case 3:
			return R.string.targetPriority3;	
		}
		return android.R.string.dialog_alert_title;	
	}
	public int getLevel(){
		return this.level;
	}

	/* SETTER */
	public int addLife(int nb){
		this.life += nb;
		return this.life;
	}
	public int removeLife(int nb){
		this.life -= nb;
		return this.life;
	}
	public int addSpeed(int nb){
		this.speed += nb;
		return this.speed;
	}
	public int removeSpeed(int nb){
		this.speed -= nb;
		return this.speed;
	}
	public int addFireRate(int nb){
		this.fireRate += nb;
		return this.fireRate;
	}
	public int removeFireRate(int nb){
		this.fireRate -= nb;
		return this.fireRate;
	}
	public int addCost(int nb){
		this.cost += nb;
		return this.cost;
	}
	public int removeCost(int nb){
		this.cost -= nb;
		return this.cost;
	}
	public int addDamage(int nb){
		this.damage += nb;
		return this.damage;
	}
	public int removeDamage(int nb){
		this.damage -= nb;
		return this.damage;
	}	
	public int addTargetNb(int nb){
		this.targetNb += nb;
		return this.targetNb;
	}
	public int removeTargetNb(int nb){
		this.targetNb -= nb;
		return this.targetNb;
	}	
	public int setTargetPriority(int nb){
		this.targetPriority = nb;
		return this.targetPriority;
	}
	public int addLevel(int nb){
		this.level += nb;
		return this.level;
	}
	public int removeLevel(int nb){
		this.level -= nb;
		return this.level;
	}



}
