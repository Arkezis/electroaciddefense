package observ;

public interface ObservableGame {
	public void addObservateur(ObservateurMenu obs);
	public void delObservateur(ObservateurMenu obs);
	public void delAllObservateur();
	public void updateObservateurLive();
	public void updateObservateurMoney();
	public void updateObservateurWave();
	public void updateObservateurScore();
}
