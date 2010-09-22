//package electroacid.defense.gamePart.gui;
//
//
//import com.android.angle.AngleObject;
//
//import electroacid.defense.gamePart.game.GenericGame;
//import electroacid.defense.utils.AngleSegment;
//
///**
// * Use for creating a shoot picture
// * @author Arkezis
// * @version 1.0b
// */
//public class Shoot extends AngleSegment {
//
//	/**
//	 * The AngleObject used to add/remove the shoot
//	 */
//	AngleObject ogWork;
//	float timeShoot=(float)0.3;
//	float timeElapsed ;
//
//	/**
//	 * The constructor
//	 * @param x1 The x of the tower
//	 * @param y1 The y of the tower
//	 * @param x2 The x of the creature
//	 * @param y2 The y of the creature
//	 * @param og The AngleObject used to add/remove the shoot
//	 * @param element 
//	 */
//	public Shoot(float x1, float y1, float x2, float y2, AngleObject og, float R, float G, float B, int width) {
//		super(x1+16,y1+16,x2,y2);
//		this.setRGB(R, G, B);
//		this.setWidth(width);
//		og.addObject(this);
//		this.ogWork = og;
//
//	}
//	/**
//	 * At each step, the shoot is removed
//	 */	
//	public void step(float secondsElapsed){
//		GenericGame game = GenericGame.getInstance();
//		for (int speedMult=0;speedMult<game.getSpeedMultiplicator();speedMult++){
//			timeElapsed += secondsElapsed;
//
//			if(timeElapsed >= timeShoot){
//				this.ogWork.removeObject(this);
//				timeElapsed=0;
//			}}
//		super.step(secondsElapsed);
//	}
//
//}
