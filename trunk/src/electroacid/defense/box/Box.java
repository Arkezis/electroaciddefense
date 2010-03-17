package electroacid.defense.box;

import android.util.Log;

import com.stickycoding.Rokon.Hotspot;
import com.stickycoding.Rokon.Sprite;

public abstract class Box {

		protected Sprite sprite;
		protected Hotspot hotSpot;
		
		protected int x;
		protected int y;
		
		protected int width;
		protected int height;
		
		/**
		 * @return the sprite
		 */
		public Sprite getSprite() {
			return this.sprite;
		}
	
		/**
		 * @param sprite the sprite to set
		 */
		public void setSprite(Sprite sprite) {
			this.sprite = sprite;
		}
		
		/**
		 * @return the hotSpot
		 */
		public Hotspot getHotSpot() {
			return this.hotSpot;
		}
	
		/**
		 * @param hotSpot the hotSpot to set
		 */
		public void setHotSpot(Hotspot hotSpot) {
			this.hotSpot = hotSpot;
		}
		
		/**
		 * @return the x
		 */
		public int getX() {
			return this.x;
		}
		
		/**
		 * @param x the x to set
		 */
		public void setX(int x) {
			this.x = x;
		}
		
		/**
		 * @return the y
		 */
		public int getY() {
			return this.y;
		}
		/**
		 * @param y the y to set
		 */
		public void setY(int y) {
			this.y = y;
		}
		
		/**
		 * @return the width
		 */
		public int getWidth() {
			return this.width;
		}
		
		/**
		 * @param width the width to set
		 */
		public void setWidth(int width) {
			this.width = width;
		}
		
		/**
		 * @return the height
		 */
		public int getHeight() {
			return this.height;
		}
		
		/**
		 * @param height the height to set
		 */
		public void setHeight(int height) {
			this.height = height;
		}

}
