package electroacid.defense;

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
	
	public Tower(int l, int s, int fr, int c, boolean f, int d, int tn, int tp, int le){
		life = l;
		speed = s;
		fireRate = fr;
		cost = c;
		fly = f;
		damage = d;
		targetNb = tn;
		targetPriority = tp;
		level = le;
	}
	
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
	public int setLife(int nb){
		this.life = nb;
		return this.life;
	}
	public int setSpeed(int nb){
		this.speed = nb;
		return this.speed;
	}
	public int setFireRate(int nb){
		this.fireRate = nb;
		return this.fireRate;
	}
	public int setCost(int nb){
		this.cost = nb;
		return this.cost;
	}
	public int setDamage(int nb){
		this.damage = nb;
		return this.damage;
	}
	public int setTargetNb(int nb){
		this.targetNb = nb;
		return this.targetNb;
	}
	public int setTargetPriority(int nb){
		this.targetPriority = nb;
		return this.targetPriority;
	}
	public int setLevel(int nb){
		this.level = nb;
		return this.level;
	}




}
