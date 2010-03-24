package electroacid.defense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class electroaciddefense extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		Intent i = new Intent(getBaseContext(),Play4.class);
		Log.d("DEBUGTAG", "Before launching the activity Play4");
		startActivity(i);
    }
    
//    private OnClickListener b_NewGameListener4 = new OnClickListener() {
//    	public void onClick(View v){
//    		/*Context context = getApplicationContext();
//    		CharSequence txt = "Did you think something will happen ? Mouahahaha ! ";
//    		Toast toast = Toast.makeText(context, txt, Toast.LENGTH_LONG);
//    		toast.show();*/
//    		
//    		Intent i = new Intent(getBaseContext(),Play4.class);
//    		Log.d("DEBUGTAG", "Before launching the activity Play4");
//    		startActivity(i);
//    	}
//    };
}