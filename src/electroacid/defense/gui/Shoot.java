package electroacid.defense.gui;

import utils.AngleSegment;

import com.android.angle.AngleObject;

public class Shoot extends AngleSegment {

	/**
	 * The AngleObject used to add/remove the shoot
	 */
	AngleObject ogWork;
	/**
	 * The constructor
	 * @param x1 The x of the tower
	 * @param y1 The y of the tower
	 * @param x2 The x of the creature
	 * @param y2 The y of the creature
	 * @param og The AngleObject used to add/remove the shoot
	 */
	public Shoot(float x1, float y1, float x2, float y2, AngleObject og) {

		super(x1+16,y1+16,x2+16,y2+16);
		og.addObject(this);
		this.ogWork = og;
	}
	/**
	 * At each step, the shoot is removed
	 */	
	public void step(float secondsElapsed){
		this.ogWork.removeObject(this);
		super.step(secondsElapsed);
	}
	
}