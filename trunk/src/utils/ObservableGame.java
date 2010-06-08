package utils;

import java.util.ArrayList;

public interface ObservableGame {
	
	public void addObservateur(ObservateurMenu obs);
	public void updateObservateurLive();
	public void updateObservateurMoney();
	public void delObservateur(ObservateurMenu obs);
	public void delAllObservateur();
}
