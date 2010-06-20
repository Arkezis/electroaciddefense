package electroacid.defense;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import electroacid.defense.choiceMapPart.ChoiceOfMap;
import electroacid.defense.optionsPart.Options;

public class electroaciddefense extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		SharedPreferences settings = getSharedPreferences(Options.getPrefsName(), 0);
		if(settings.getInt("language", 999) == 999){
			Toast.makeText(this.getApplicationContext(),R.string.languageNotDefined, 5000).show();
		}else{ // vérifier que le language choisi est le même que le locale, sinon, on force le locale à changer
			switch(settings.getInt("language", 999)){
				case 0: setLocal(Locale.ENGLISH);break;
				case 1: setLocal(Locale.FRENCH);break;
			}
		}
		
		Toast.makeText(this.getApplicationContext(), "Language : "+this.getResources().getConfiguration().locale, 10000).show();
		
		((Button) this.findViewById(R.id.Button01)).setOnClickListener(this);
		((Button) this.findViewById(R.id.ButtonHowTo)).setOnClickListener(this);
		((Button) this.findViewById(R.id.ButtonOptions)).setOnClickListener(this);
		((Button) this.findViewById(R.id.ButtonQuit)).setOnClickListener(this);
		
	}

	private void setLocal(Locale loc){
		Resources res = getResources();
		Configuration conf = res.getConfiguration();
		conf.locale = loc;
		res.updateConfiguration(conf, res.getDisplayMetrics());
	}
	
	@Override
	public void onClick(View arg0) {
		Intent i = null;
		switch (arg0.getId()) {
		case R.id.Button01:
			i = new Intent(this, ChoiceOfMap.class);
			this.startActivity(i);
			break;
		case R.id.ButtonHowTo:
			//i = new Intent(this, ChoiceOfMap.class);
			//this.startActivity(i);
			break;
		case R.id.ButtonOptions:
			i = new Intent(this, Options.class);
			this.startActivityForResult(i, 1000);
			break;
		case R.id.ButtonQuit:
			finish();
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		Toast.makeText(this.getApplicationContext(), "Change langue !  step 2 "+requestCode+" et "+resultCode, 1000).show();
		Log.d("DEBUGTAG","step 2 !");
		if(requestCode==1000 && resultCode==2000){
			// la langue a changé, il faut recharcher l'activité ! 
			Toast.makeText(this.getApplicationContext(), "Change langue !  step 3 ", 1000).show();
			Log.d("DEBUGTAG","step 3 !");
			Intent i = new Intent(this,electroaciddefense.class);
			//Intent i = new Intent(this.createPackageContext(this.getPackageName(), Context.CONTEXT_INCLUDE_CODE),electroaciddefense.class);
			//startActivity(i);
			//Log.d("DEBUGTAG","step 4 !");

			finish();
			Log.d("DEBUGTAG","step 5 !");
		}
	}
	
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuItem m1 = menu.add(0, 1, 0, "Parameters");
		MenuItem m2 = menu.add(0, 2, 0, "High Scores");
		MenuItem m3 = menu.add(0, 3, 0, "Instructions");
		MenuItem m4 = menu.add(0, 4, 0, "About");
		m1.setIcon(android.R.drawable.ic_menu_preferences);
		m2.setIcon(android.R.drawable.ic_menu_agenda);
		m3.setIcon(android.R.drawable.ic_menu_directions);
		m4.setIcon(android.R.drawable.ic_menu_zoom);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, R.string.RFU, 2000)
					.show();
			break;
		case 2:
			Toast.makeText(this, R.string.RFU, 2000)
					.show();
			break;
		case 3:
			InstructionsAlertDialog();
			break;
		case 4:
			Toast
					.makeText(
							this,
							"Game developed by Mathieu Deschamps (aka cilheo) and Tom Dubin (aka Arkezis). \n Contact : ElectroAcidDefense@gmail.com . \n All rights reserved.",
							2000).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void InstructionsAlertDialog() {
		AlertDialog.Builder dial = new AlertDialog.Builder(this);
		dial.setTitle("Instructions");
		dial
				.setMessage("The rules are following classic tower defense games ! \n You have to create tower on the way of the creatures to destroy them. You have a number of lives and each creatures who will go until the end of the path will cost you a life ! \n When you're lifes reach 0, you lose ! ");

		dial.setOnCancelListener(new DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface dialog) {
			}
		});
		dial.setPositiveButton("OK",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		dial.show();

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
			return true;
		return false;

	}

	/**
	 * Managing the end of the activity.
	 */
	protected void onDestroy() {
		super.onDestroy();
		// The activity is totally killed !
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}