package electroacid.defense.wave;

import java.util.LinkedList;

import android.util.Log;

import com.android.angle.AngleObject;
import electroacid.defense.Creature;
import electroacid.defense.box.BoxPath;

public class Wave extends AngleObject {

	private LinkedList<Creature> listCreature ;
	private boolean start = false;
	private int timeBeetweenCreature = 2;
	private AngleObject og;
	private BoxPath debut;
	private int actualCreature = 0;
	
	
	public Wave(){
		this.listCreature = new LinkedList<Creature>();
	}
	
	/**
	 * @return the listCreature
	 */
	public LinkedList<Creature> getListCreature() {
		return listCreature;
	}

	/**
	 * @param listCreature the listCreature to set
	 */
	public void setListCreature(LinkedList<Creature> listCreature) {
		this.listCreature = listCreature;
	}

	public boolean addCreature(Creature creature){
		return this.listCreature.add(creature);
	}
	
	public void start(AngleObject ogCreature, BoxPath boxpath){
		this.debut = boxpath;
		this.og = ogCreature;
		this.start = true;
	}
	
	public void show(int numberOfCreature){
		this.listCreature.get(numberOfCreature).show();
	}

	public void step(float secondsElapsed){
		if (this.start){
			this.timeBeetweenCreature-=secondsElapsed;
			if (this.timeBeetweenCreature<=0){
				this.timeBeetweenCreature=2;
				if (this.actualCreature<this.listCreature.size()){
					this.listCreature.get(this.actualCreature).start(
							this.og,
							this.debut);
					this.actualCreature++;
				}
				
			}
			
		}
		super.step(secondsElapsed);
	}
	
}
