package electroacid.defense.box;

import android.util.Log;

import com.stickycoding.Rokon.Hotspot;
import com.stickycoding.Rokon.Sprite;
import com.stickycoding.Rokon.Texture;
import com.stickycoding.Rokon.TextureAtlas;
import com.stickycoding.Rokon.TextureManager;

public abstract class Box {

		protected Sprite spr;
		private Hotspot hpt;
		private int x, y;
		private int heightGame=480, widthGame=320;
		private int height = 50, width=50, boxPerLine = heightGame/height, boxPerColumn=widthGame/width;
		private static int nbBox=1;
		private int numBox;

		
		
 		public Sprite getSpr(){
 			
			return spr;
		}
		public Hotspot getHpt(){
			return hpt;
		}
		public int getX(){
			return x;
		}	
		public int getY(){
			return y;
		}
		public int getHeight(){
			return height;
		}
		public int getWidth(){
			return width;
		}
		public int getNumBox(){
			return this.numBox;
		}
			
		@Deprecated
		public void createBoxBuildable(Texture t){
			/* The game will be 4x4 */
			/* Create the Sprite of the box */
			numBox = nbBox;
			int myX=0,myY ;
			if (nbBox > 1) {
				myX = ((nbBox % 4)-1) * height;
				if (myX < 0) myX = 3*height;
			}	
			if (nbBox < 5) myY = 0;
			else if(nbBox < 9) myY = width;
			else if(nbBox < 13) myY = width *2;
			else myY = width *3;

			
			
			Log.d("DEBUGTAG", "Box " + nbBox + " in ("+myX + ","+ myY+")");
			spr = new Sprite(myX, myY,t);
			/* Assign it a Hotspot */
			this.hpt  = new Hotspot(spr);
			
			nbBox ++;
		}

}
