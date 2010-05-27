package electroacid.defense;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import electroacid.defense.play.Maptest;


public class electroaciddefense extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       /* Button btnGame1 = (Button)this.findViewById(R.id.Button01);
        btnGame1.setOnClickListener(b_NewGameListener); */
        Button btnGame2 = (Button)this.findViewById(R.id.Button02);
        btnGame2.setOnClickListener(b_NewGameListener2);
        Button btnQuit = (Button)this.findViewById(R.id.ButtonQuit);
        btnQuit.setOnClickListener(b_QuitListener);
    }
    
    private OnClickListener b_NewGameListener = new OnClickListener() {
    	public void onClick(View v){
    		Intent i = new Intent(getBaseContext(),Maptest.class);
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
    
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
    	MenuItem m1 = menu.add(0, 1, 0, "Parameters");
    	MenuItem m2 = menu.add(0,2,0,"High Scores");
    	MenuItem m3 = menu.add(0,3,0,"Instructions");
    	MenuItem m4 = menu.add(0,4,0,"About");
    	m1.setIcon(android.R.drawable.ic_menu_preferences);
    	m2.setIcon(android.R.drawable.ic_menu_agenda);
    	m3.setIcon(android.R.drawable.ic_menu_directions);
    	m4.setIcon(android.R.drawable.ic_menu_zoom);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    	case 1:
    		Toast.makeText(this, "This will be implemented... soon ! ", 2000).show();
    		break;
    	case 2:
    		Toast.makeText(this, "This will be implemented... soon ! ", 2000).show();
    		break;
    	case 3:
    		InstructionsAlertDialog();
    		break;
    	case 4:
    		Toast.makeText(this, "Game developed by Mathieu Deschamps and Tom Dubin. All rights reserved.", 2000).show();
    		break;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
	private void InstructionsAlertDialog() {
		AlertDialog.Builder dial = new AlertDialog.Builder(this);
		dial.setTitle("Instructions");
		dial.setMessage("The rules are following classic tower defense games ! You have to create tower on the way of the creatures to destroy them. You have a number of lives and each creatures who will go until the end of the path will cost you a life ! When you're lifes reach 0, you lose ! ");
				
		dial.setOnCancelListener(new DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface dialog) {
			}
		});
		dial.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		dial.show();
		
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
	
	/**
	 * Managing the end of the activity.
	 */
	protected void onDestroy(){
		super.onDestroy();
		// The activity is totally killed ! 
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}