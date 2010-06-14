package observ;

import electroacid.defense.game.GenericGame;

public interface ObservateurMenu{
	public void refreshMoney(GenericGame g);
	public void refreshLives(GenericGame g);
	public void refreshWaves(GenericGame g);
	public void refreshScore(GenericGame g);
	public void refreshCreature();
}
