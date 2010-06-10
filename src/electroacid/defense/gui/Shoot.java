package electroacid.defense.gui;

import utils.AngleSegment;

import com.android.angle.AngleObject;

import electroacid.defense.enums.Element;

/**
 * Use for creating a shoot picture
 * @author Arkezis
 * @version 1.0b
 */
public class Shoot extends AngleSegment {

	/**
	 * The AngleObject used to add/remove the shoot
	 */
	AngleObject ogWork;
	float timeShoot=(float)0.3;
	float timeElapsed ;
	/**
	 * The constructor
	 * @param x1 The x of the tower
	 * @param y1 The y of the tower
	 * @param x2 The x of the creature
	 * @param y2 The y of the creature
	 * @param og The AngleObject used to add/remove the shoot
	 * @param element 
	 */
	public Shoot(float x1, float y1, float x2, float y2, AngleObject og, Element element) {
		super(x1+16,y1+16,x2,y2);
		switch(element){
			case Electricity:
				this.setRGB(1, (float)0.8, 0);
				break;
			case Fire:
				this.setRGB(1, 0, 0);
				break;
			case Iron:
				this.setRGB((float)0.4, (float)0.4, (float)0.4);
				break;
			case Water:
				this.setRGB(0, 0, 1);
				break;
		}
		og.addObject(this);
		this.ogWork = og;
	}
	/**
	 * At each step, the shoot is removed
	 */	
	public void step(float secondsElapsed){
		timeElapsed += secondsElapsed;
		if(timeElapsed >= timeShoot){
			this.ogWork.removeObject(this);
			timeElapsed=0;
		}
		super.step(secondsElapsed);
	}
	
}
