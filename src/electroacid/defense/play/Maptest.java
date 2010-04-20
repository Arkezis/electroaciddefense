package electroacid.defense.play;

import android.os.Bundle;
import electroacid.defense.R;

public class Maptest extends GenericGame{

	/**
	 * Called at the beginning of the activity
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myGame=new GenericPlay(this,R.raw.testmap,R.raw.testwave,R.raw.testtower);
		setUI(myGame);

	}
	
}
