package electroacid.defense.choiceMapPart;

import electroacid.defense.R;
import electroacid.defense.gamePart.Play;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;


public class ChoiceOfMap extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choicemap);
		((ImageButton)this.findViewById(R.id.ImgButton1)).setOnClickListener(this);
		((ImageButton)this.findViewById(R.id.ImgButton2)).setOnClickListener(this);
		((ImageButton)this.findViewById(R.id.ImgButton3)).setOnClickListener(this);
		((ImageButton)this.findViewById(R.id.ImgButton4)).setOnClickListener(this);
		((Button)this.findViewById(R.id.Button1)).setOnClickListener(this);    
	}

	@Override
	public void onClick(View arg0) {
		Intent i = null;
		switch(arg0.getId()){
		case R.id.ImgButton1 :
			i = new Intent(this,Play.class);
			i.putExtra("map", "tutomap");
			this.startActivity(i);
			break;
		case R.id.ImgButton2 :
			i = new Intent(this,Play.class);
			i.putExtra("map", "map1");
			this.startActivity(i);
			break;
		case R.id.ImgButton3 :
			i = new Intent(this,Play.class);
			i.putExtra("map", "map2");
			this.startActivity(i);
			break;
		case R.id.ImgButton4 :
			i = new Intent(this,Play.class);
			i.putExtra("map", "map3");
			this.startActivity(i);
			break;
		case R.id.Button1 :
			finish();
			break;
		}
	}

	/**
	 * Managing some keys
	 * @param keyCode Code of the key pressed
	 * @param event Event generated
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			this.onDestroy();
			return true;
		}
		return false;
	}

	/**
	 * Managing the end of the activity.
	 */
	protected void onDestroy(){
		super.onDestroy();
		// The activity is totally killed ! 
		//android.os.Process.killProcess(android.os.Process.myPid());
	}


}