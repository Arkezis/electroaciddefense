package observ;

import electroacid.defense.game.GenericGame;

public interface ObservateurMenu{
	public void refreshMoney(GenericGame g);
	public void refreshLives(GenericGame g);
	public void refreshWaves(GenericGame g);
}
