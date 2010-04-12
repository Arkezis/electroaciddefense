package electroacid.defense;

import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;

import electroacid.defense.box.BoxPath;
import electroacid.defense.enums.Element;

public  class Creature implements Cloneable{

		private Element element; 
		private int life;
		private int speed;
		private int fireRate;
		private int rewardValue;
		private boolean fly;
		private AngleSprite sprite;
		
		
		public Creature(Element e,int l,int s, int fR, int c, boolean f,AngleSpriteLayout _layout){
			this.element = e;
			this.life = l;
			this.speed = s;
			this.fireRate = fR;
			this.rewardValue = c;
			this.fly = f;
			this.sprite= new AngleSprite(_layout);
		}


		public Object clone() {
			Creature creature = null;
			try {
				creature = (Creature) super.clone();
			} catch(CloneNotSupportedException cnse){
				cnse.printStackTrace(System.err);
			}
			
			creature.sprite = new AngleSprite(this.sprite.roLayout);
			return creature;
		}
		
		public void destroy(Game g,AngleObject og,boolean byTower){
			og.removeObject(this.sprite);
			if (byTower){
				g.addMoney(this.rewardValue);
			}else {
				g.removeLives(1);
			}
			
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
			return rewardValue;
		}


		/**
		 * @param cost the cost to set
		 */
		public void setCost(int cost) {
			this.rewardValue = cost;
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


		public void show() {
			// TODO Auto-generated method stub
			
		}


		public void start(AngleObject og, BoxPath debut) {
			debut.addCreature(this);
			this.sprite.mPosition.set(
					debut.getY(), 
					debut.getX());
			og.addObject(this.sprite);
		}
}
