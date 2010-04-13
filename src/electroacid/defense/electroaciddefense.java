package electroacid.defense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class electroaciddefense extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnGame = (Button)this.findViewById(R.id.Button01);
        btnGame.setOnClickListener(b_NewGameListener);

    }
    
    private OnClickListener b_NewGameListener = new OnClickListener() {
    	public void onClick(View v){

    		Intent i = new Intent(getBaseContext(),Play4.class);
    		startActivity(i);
    	}
    };
}