package utils;

public interface Observable {

	public void addObservateur(Observateur obs);
	public void updateObservateurRemove(Object c);
	public void updateObservateurAdd(Object c);
	public void delObservateur(Observateur obs);
	public void delAllObservateur();
}
