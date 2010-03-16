package electroacid.defense;

import electroacid.defense.enums.Element;

public  class Creature {

		private Element element; 
		private int life;
		private int speed;
		private int fireRate;
		private int cost;
		private boolean fly;
		
		public Creature(Element e,int l,int s, int fR, int c, boolean f){
			this.element = e;
			this.life = l;
			this.speed = s;
			this.fireRate = fR;
			this.cost = c;
			this.fly = f;			
		}
		
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
		public Element setElement(Element e){
			this.element = e;
			return this.element;
		}
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
		public boolean setFly(boolean f){
			this.fly = f;
			return this.fly;
		}
}
