package utils;

import java.util.ArrayList;

public interface ObservableCreature {
	
	public void addObservateur(ObservateurTower obs);
	public void updateObservateurRemove(Object c);
	public void delObservateur(ObservateurTower obs);
	public void delAllObservateur();
}
