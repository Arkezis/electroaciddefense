package electroacid.defense.gui;

import android.util.Log;

import com.android.angle.AngleObject;

import utils.AngleSegment;

public class Shoot extends AngleSegment {

	float waitErase=(float) 0.01;
	float timeElapsed=0;
	AngleObject ogWork;
	
	public Shoot(float x1, float y1, float x2, float y2, AngleObject og) {
		
		//super(x1+16, y1+16, (y1>y2) ? x2-x1 : x1-x2, (x1>x2) ? y2-y1 : y1-y2);
		//super(x1,y1,(x2>x1) ? x1-x2 : x2-x1 ,(y2>y1) ? y1-x2 : y2-y1);
		super(x1+16,y1+16,x2+16,y2+16);
		Log.d("SHOOT", "Tower : ("+x1+","+y1+") et  créa ("+x2+","+y2+")");
		og.addObject(this);
		this.ogWork = og;
	}

	public void step(float secondsElapsed){
	
		this.ogWork.removeObject(this);
		//Log.d("SHOOT", "Step de Shoot ! avec"+timeElapsed);
		super.step(secondsElapsed);

	}
	
}
