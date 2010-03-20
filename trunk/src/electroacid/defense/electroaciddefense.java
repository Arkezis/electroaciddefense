package electroacid.defense;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class electroaciddefense extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);*/
        
    /*    setContentView(R.layout.menu);

        Button b_NewGame4 = (Button) findViewById(R.id.b_NewGame4);
        b_NewGame4.setOnClickListener(b_NewGameListener4);
        TextView tv_Welcome = (TextView) findViewById(R.id.tv_Welcome);
        
        */
        
		
		Intent i = new Intent(getBaseContext(),Play4.class);
		Log.d("DEBUGTAG", "Before launching the activity Play4");
		startActivity(i);
    }
    
    private OnClickListener b_NewGameListener4 = new OnClickListener() {
    	public void onClick(View v){
    		/*Context context = getApplicationContext();
    		CharSequence txt = "Did you think something will happen ? Mouahahaha ! ";
    		Toast toast = Toast.makeText(context, txt, Toast.LENGTH_LONG);
    		toast.show();*/
    		
    		Intent i = new Intent(getBaseContext(),Play4.class);
    		Log.d("DEBUGTAG", "Before launching the activity Play4");
    		startActivity(i);
    	}
    };
}