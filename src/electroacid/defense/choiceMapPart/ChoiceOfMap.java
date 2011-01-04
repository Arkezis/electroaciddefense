package electroacid.defense.choiceMapPart;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import electroacid.defense.R;
import electroacid.defense.gamePart.Play;


public class ChoiceOfMap extends Activity implements OnClickListener {
	private SharedPreferences pref;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choicemap);
		((ImageButton)this.findViewById(R.id.ImgButton1)).setOnClickListener(this);
		((ImageButton)this.findViewById(R.id.ImgButton2)).setOnClickListener(this);
		((ImageButton)this.findViewById(R.id.ImgButton3)).setOnClickListener(this);
		((ImageButton)this.findViewById(R.id.ImgButton4)).setOnClickListener(this);
		((ImageButton)this.findViewById(R.id.ImgButton5)).setOnClickListener(this);
		((ImageButton)this.findViewById(R.id.ImgButton6)).setOnClickListener(this);
		((ImageButton)this.findViewById(R.id.ImgButton7)).setOnClickListener(this);
		((Button)this.findViewById(R.id.Button1)).setOnClickListener(this);
		
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		if(pref.getInt("level", 0) == 0) pref.edit().putInt("level", 1);	// the player play for the first time ? Levelmax = 1
		Toast.makeText(this.getApplicationContext(), "Mon niveau max : "+pref.getInt("level", 1), 10000).show();

	}

	@Override
	public void onClick(View arg0) {
		Intent i = null;
		switch(arg0.getId()){
		case R.id.ImgButton1 :
			i = new Intent(this,Play.class);
			i.putExtra("map", "tutomap");
			if(unlockedMap(1))			this.startActivity(i);
			else 	Toast.makeText(this.getApplicationContext(), "Level locked ! Play more ;-)", 10000).show();
			break;
		case R.id.ImgButton2 :
			i = new Intent(this,Play.class);
			i.putExtra("map", "map1");
			if(unlockedMap(2))			this.startActivity(i);
			else 	Toast.makeText(this.getApplicationContext(), "Level locked ! Play more ;-)", 10000).show();
			break;
		case R.id.ImgButton3 :
			i = new Intent(this,Play.class);
			i.putExtra("map", "map2");
			if(unlockedMap(3))			this.startActivity(i);
			else 	Toast.makeText(this.getApplicationContext(), "Level locked ! Play more ;-)", 10000).show();
			break;
		case R.id.Button1 :
			finish();
			break;
		}
	}

	public boolean unlockedMap(int map){
		if(map==2){  pref.edit().putInt("level", 2).commit(); // utilisé pour les tests : à virer
		}
		if( pref.getInt("level", 1) >= map)
			return true;
		else
			return false;
		
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