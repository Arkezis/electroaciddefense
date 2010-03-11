package electroacid.defense;

import android.util.Log;

import com.stickycoding.Rokon.Hotspot;
import com.stickycoding.Rokon.Sprite;
import com.stickycoding.Rokon.Texture;
import com.stickycoding.Rokon.TextureAtlas;
import com.stickycoding.Rokon.TextureManager;

public abstract class Box {

		private Sprite spr;
		private Hotspot hpt;
		private int x, y;
		private int heightGame=480, widthGame=320;
		private int height = 83, width=68, boxPerLine = heightGame/height, boxPerColumn=widthGame/width;
		private static int nbBox=1;

		
		
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
		
		
		

		public void createBoxBuildable(Texture t){
			/* Create the Sprite of the box */
			int myX = ((nbBox * height) % heightGame) - height; 
			if (myX < 0) myX = 0;
			
			//int myY = (((nbBox * width)/boxPerLine) % widthGame);
			int myY = (((nbBox / boxPerLine))*width);
			if (nbBox % boxPerLine == 0) myY = (nbBox/boxPerLine -1)*width;
			Log.d("DEBUGTAG", "Box " + nbBox + " in ("+myX + ","+ myY+")");
			spr = new Sprite(myX, myY,t);
			/* Assign it a Hotspot */
			hpt  = new Hotspot(spr);
			
			nbBox ++;
		}

}
