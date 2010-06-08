package electroacid.defense;

import com.android.angle.AngleObject;
import com.android.angle.AngleSprite;
import com.android.angle.AngleSpriteLayout;

import electroacid.defense.box.BoxPath;
import electroacid.defense.enums.Element;

/**
 * It's a simple creature
 * @author cilheo
 * @version 1.0b
 */
public  class Creature implements Cloneable{

		/** Element of the creature */
		private Element element; 
		
		/** Life of the creature */
		private int life;
		
		/** Speed of the creature */
		private float speed;
		
		/** Not implemented yet */
		private int fireRate;
		
		/** Gain when the creature die */
		private int rewardValue;
		
		/** Not implemented yet */
		private boolean fly;
		
		/** Sprite of the creature */
		private AngleSprite sprite;
		
		
		/**
		 * Constructor of a creature
		 * @param _element Element of the creature
		 * @param _life Life of the creature
		 * @param speed2 Speed of the creature
		 * @param _fireRate Not implemented yet
		 * @param _rewardValue Gain when the creature die
		 * @param _fly Not implemented yet
		 * @param layout Layout of the creature
		 */
		public Creature(Element _element,int _life,float speed2, int _fireRate, int _rewardValue,
				boolean _fly,AngleSpriteLayout layout){
			this.element = _element;
			this.life = _life;
			this.speed = speed2;
			this.fireRate = _fireRate;
			this.rewardValue = _rewardValue;
			this.fly = _fly;
			this.sprite= new AngleSprite(layout);
		}

		public void loseLife(int nbDamage){
			this.life -= nbDamage; 
		}

		/** clone all value of the creature excepted the sprite, create a new */
		public Object clone() {
			Creature creature = null;
			try { creature = (Creature) super.clone();
			} catch(CloneNotSupportedException cnse){cnse.printStackTrace(System.err);}
			creature.sprite = new AngleSprite(this.sprite.roLayout);
			return creature;
		}
		
		/**
		 * Destroy a creature
		 * add money if necessary
		 * remove live if necessary
		 * remove the sprite
		 * @param game game's parameter
		 * @param og container of the creature (for the sprite)
		 * @param byTower kill by a tower or not
		 */
		public void destroy(Game game,AngleObject og,boolean byTower){
			og.removeObject(this.sprite);
			if (byTower) game.addMoney(this.rewardValue);
			else game.removeLives(1);
			game.removeOneCreatureInGame();
			//menu.refreshLives(game);
		}
		
		public void start(AngleObject og, BoxPath debut) {
			debut.addCreature(this);
			this.sprite.mPosition.set(
					debut.getX()+16,
					debut.getY()+16);
			og.addObject(this.sprite);
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
		public float getSpeed() {
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
}
