package electroacid.defense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class electroaciddefense extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnGame1 = (Button)this.findViewById(R.id.Button01);
        btnGame1.setOnClickListener(b_NewGameListener);
        Button btnGame2 = (Button)this.findViewById(R.id.Button02);
        btnGame2.setOnClickListener(b_NewGameListener2);
        Button btnQuit = (Button)this.findViewById(R.id.ButtonQuit);
        btnQuit.setOnClickListener(b_QuitListener);
    }
    
    private OnClickListener b_NewGameListener = new OnClickListener() {
    	public void onClick(View v){
    		Intent i = new Intent(getBaseContext(),Play.class);
    		startActivity(i);
    	}
    };
    
    private OnClickListener b_NewGameListener2 = new OnClickListener() {
    	public void onClick(View v){
    		Intent i = new Intent(getBaseContext(),Play.class);
    		startActivity(i);
    	}
    };
    
    private OnClickListener b_QuitListener = new OnClickListener() {
    	public void onClick(View v){
    		finish();
    	}
    };
    
    
	/**
	 * Managing some keys
	 * @param keyCode Code of the key pressed
	 * @param event Event generated
	 */
	public void onKey(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
	}
	
	/**
	 * Managing the end of the activity.
	 */
	protected void onDestroy(){
		super.onDestroy();
		// The activity is totally killed ! 
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}