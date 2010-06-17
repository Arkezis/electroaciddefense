package electroacid.defense.gamePart.observ;

public interface ObservableBoxPath {
	public void addObservateur(ObservateurTower obs);
	public void delObservateur(ObservateurTower obs);
	public void delAllObservateur();
	public void updateObservateurRemove(Object c);
	public void updateObservateurAdd(Object c);
	public void updateObservateurRemoveAndAdd(Object c);
}
