package electroacid.defense.play;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.android.angle.AngleActivity;

import electroacid.defense.R;

public abstract class GenericGame extends AngleActivity{ 


	/** The AngleUI */
	protected GenericPlay myGame;

	/**
	 * Called at the beginning of the activity
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FrameLayout mMainLayout=new FrameLayout(this);
		mMainLayout.addView(mGLSurfaceView);
		setContentView(mMainLayout);

		myGame=new GenericPlay(this,R.raw.testmap,R.raw.testwave,R.raw.testtower);
		setUI(myGame);

	}
	
	/**
	 * Managing some keys
	 * @param keyCode Code of the key pressed
	 * @param event Event generated
	 */
	public void onKey(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
	}



}