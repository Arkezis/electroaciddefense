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
        
        setContentView(R.layout.menu);
        Button b_NewGame = (Button) findViewById(R.id.b_NewGame);
        b_NewGame.setOnClickListener(b_NewGameListener);
        Button b_NewGame2 = (Button) findViewById(R.id.b_NewGame2);
        b_NewGame2.setOnClickListener(b_NewGameListener2);
        TextView tv_Welcome = (TextView) findViewById(R.id.tv_Welcome);
        
        
    }
    
    private OnClickListener b_NewGameListener = new OnClickListener() {
    	public void onClick(View v){
    		/*Context context = getApplicationContext();
    		CharSequence txt = "Did you think something will happen ? Mouahahaha ! ";
    		Toast toast = Toast.makeText(context, txt, Toast.LENGTH_LONG);
    		toast.show();*/
    		
    		Intent i = new Intent(getBaseContext(),Play.class);
    		Log.d("DEBUGTAG", "ARK : Before launching the activity Play");
    		startActivity(i);
    	}
    };
    private OnClickListener b_NewGameListener2 = new OnClickListener() {
    	public void onClick(View v){
    		/*Context context = getApplicationContext();
    		CharSequence txt = "Did you think something will happen ? Mouahahaha ! ";
    		Toast toast = Toast.makeText(context, txt, Toast.LENGTH_LONG);
    		toast.show();*/
    		
    		Intent i = new Intent(getBaseContext(),Play2.class);
    		Log.d("DEBUGTAG", "Before launching the activity Play2");
    		startActivity(i);
    	}
    };
}