package observ;

public interface ObservableCreature {
	public void addObservateur(ObservateurMenu obs);
	public void delObservateur(ObservateurMenu obs);
	public void delAllObservateur();
	public void updateObservateur();
}
