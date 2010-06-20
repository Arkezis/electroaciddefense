package electroacid.defense.optionsPart;

import java.util.Locale;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;
import electroacid.defense.R;

public class Options extends Activity     {

	public static String PREFS_NAME = "Prefs";
	CheckBox cb_vibrator;
	Spinner spinner;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		setContentView(R.layout.options);
		cb_vibrator = (CheckBox) findViewById(R.id.CheckBox01);
		cb_vibrator.setChecked(settings.getBoolean("vibrator", false));
		
	    spinner = (Spinner) findViewById(R.id.spinner);
	    ArrayAdapter<CharSequence>  adapter = ArrayAdapter.createFromResource(
	            this, R.array.Language_array, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    if(settings.getInt("language", 999) != 999)
	    	spinner.setSelection(settings.getInt("language", 1));
	    else
	    	spinner.setSelection(0); // english by default

		this.setResult(2000); // modifications in the parameters

	}
	
	/**
	 * @return the prefsName
	 */
	public static String getPrefsName() {
		return PREFS_NAME;
	}

	/**
	 * @param prefsName the prefsName to set
	 */
	public static void setPrefsName(String prefsName) {
		PREFS_NAME = prefsName;
	}

	

	
	protected void onStop(){
		super.onStop();
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("vibrator", cb_vibrator.isChecked() );
		if(settings.getInt("language",999) != spinner.getSelectedItemPosition()){ // the user change the language
			editor.putInt("language",spinner.getSelectedItemPosition());
			switch(settings.getInt("language", 999)){
				case 0: setLocal(Locale.ENGLISH);break;
				case 1: setLocal(Locale.FRENCH);break;
			}
			Log.d("DEBUGTAG","step 1 !");
			Toast.makeText(this.getApplicationContext(), "Change langue !  ", 1000).show();
		}		
		if(!editor.commit()){ 
			Toast.makeText(this.getApplicationContext(), "A problem happened during the save of your preferences ! ", 1000).show();
		}
		
	}
	
	private void setLocal(Locale loc){
		Resources res = getResources();
		Configuration conf = res.getConfiguration();
		conf.locale = loc;
		res.updateConfiguration(conf, res.getDisplayMetrics());
	}

	
	
}
