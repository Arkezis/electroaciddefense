package electroacid.defense;

import com.stickycoding.Rokon.RokonActivity;

import android.os.Bundle;

public class electroaciddefense extends RokonActivity {
    /** Called when the activity is first created. */
//    @Override
    
	/* Creating the Elements */
    public Element fire = new Element("Fire");
    public Element elec = new Element("Electricity");
    public Element water = new Element("Water",elec,fire);    
    public Element iron= new Element("Iron",fire,elec);
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        /* Finishing creating the elements */
        this.fire.setStrength(iron); this.fire.setWeakness(water);
        this.elec.setStrength(water);this.elec.setWeakness(iron);
        
        createEngine("graphics/loading.png", 480, 320, true);
        
    }
}