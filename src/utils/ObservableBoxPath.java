package utils;

public interface ObservableBoxPath {

	public void addObservateur(ObservateurTower obs);
	public void updateObservateurRemove(Object c);
	public void updateObservateurAdd(Object c);
	public void delObservateur(ObservateurTower obs);
	public void delAllObservateur();
	void updateObservateurRemoveAndAdd(Object c);
}
