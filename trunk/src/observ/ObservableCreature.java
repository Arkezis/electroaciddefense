package observ;

import electroacid.defense.Creature;

public interface ObservableCreature {
	public void addObservateur(ObservateurMenu obs);
	public void delObservateur(ObservateurMenu obs);
	public void delAllObservateur();
	public void updateObservateur();
}
