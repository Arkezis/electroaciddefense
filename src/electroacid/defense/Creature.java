package electroacid.defense;

public abstract class Creature {

		private Element element; 
		private int life;
		private int speed;
		private int fireRate;
		private int cost;
		private boolean fly;
		
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
		public int setLife(int nb){
			this.life = nb;
			return this.life;
		}
}
