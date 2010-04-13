package electroacid.defense.wave;

import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.XmlUtil;
import android.content.Context;
import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleSurfaceView;
import electroacid.defense.Creature;
import electroacid.defense.R;
import electroacid.defense.enums.Element;

public class GenericWave {

	/** Waves' creature */
	private LinkedList<Wave> listWave = new LinkedList<Wave>();
	/** layouts' list, contain all possibility of creature layout*/
	private LinkedList<AngleSpriteLayout> listLayout;

	/**
	 * Constructor of genericWave
	 * init all layout
	 * @param mGLSurfaceView
	 */
	public GenericWave(AngleSurfaceView mGLSurfaceView) {
		this.listLayout = new LinkedList<AngleSpriteLayout>();
		this.listLayout.add(new AngleSpriteLayout(
				mGLSurfaceView,32,32,R.drawable.creature1));
	}
	
	/**
	 * Read the xml file and create waves and creatures
	 * @param context use for read the xml file
	 * @param xmlResourceId id of the xml file
	 * @throws Exception
	 */
	public void build(final Context context,final int xmlResourceId) throws Exception{
		Document waveXml = XmlUtil.getDocumentFromResource(context, xmlResourceId);
		NodeList listNodeWave = waveXml.getDocumentElement().getElementsByTagName("wave");
		for (int i=0;i<listNodeWave.getLength();i++){
			NodeList listCreature = listNodeWave.item(i).getChildNodes();
			Wave wave = new Wave();
			this.listWave.add(wave);	
			for (int j=0;j<listCreature.getLength();j++){
				Node creature = listCreature.item(j);
				if (creature.getNodeName().equalsIgnoreCase("creature")){

					Element element = Element.getElement(XmlUtil.getAttributeFromNode(creature, "element"));
					int life = XmlUtil.getAttributeIntFromNode(creature, "life");
					int speed = XmlUtil.getAttributeIntFromNode(creature, "speed");
					int fireRate = XmlUtil.getAttributeIntFromNode(creature, "fireRate");
					int rewardValue = XmlUtil.getAttributeIntFromNode(creature, "rewardValue");
					boolean fly = XmlUtil.getAttributeBooleanFromNode(creature, "fly");
					int idTexture = XmlUtil.getAttributeIntFromNode(creature, "textureId");
					int number = XmlUtil.getAttributeIntFromNode(creature, "number");

					for (int num=0;num<number;num++){
						Creature creat = new Creature(element,
								life,
								speed,
								fireRate,
								rewardValue,
								fly,
								this.listLayout.get(idTexture));
						wave.addCreature(creat);				
					}
				}
			}
		}
	}

	/** @return the listWave */
	public LinkedList<Wave> getListWave() {return listWave;}

	/** @param listWave the listWave to set */
	public void setListWave(LinkedList<Wave> listWave) {this.listWave = listWave;}

}