package electroacid.defense.gamePart.wave;

import java.util.LinkedList;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import android.content.Context;
import electroacid.defense.R;
import electroacid.defense.gamePart.Creature;
import electroacid.defense.gamePart.enums.Element;
import electroacid.defense.gamePart.game.GenericGame;
import electroacid.defense.utils.XmlUtil;

/**
 * Read a xml file and create all wave
 * @author cilheo
 * @version 1.0b
 */
public class GenericWave {

	/** Waves' creature */
	private LinkedList<Wave> listWave = new LinkedList<Wave>();
	/** layouts' list, contain all possibility of creature layout*/
	private TiledTextureRegion[] textureRegion;

	public BaseGameActivity play;
	
	/**
	 * Constructor of genericWave
	 * init all layout
	 * @param mGLSurfaceView
	 */
	public GenericWave(BaseGameActivity context, TiledTextureRegion[] _textureRegion) {
		
		this.play = context;
		this.textureRegion = _textureRegion;
	}

	/**
	 * Read the xml file and create waves and creatures
	 * @param context use for read the xml file
	 * @param xmlResourceId id of the xml file
	 * @param game game's parameters
	 * @throws Exception
	 */
	public void build(final int xmlResourceId) throws Exception{
		Document waveXml = XmlUtil.getDocumentFromResource(this.play, xmlResourceId);
		NodeList listNodeWave = waveXml.getDocumentElement().getElementsByTagName("wave");
		GenericGame game = GenericGame.getInstance();
		for (int i=0;i<listNodeWave.getLength();i++){
			NodeList listCreature = listNodeWave.item(i).getChildNodes();
			Wave wave = new Wave();
			this.listWave.add(wave);
			game.nbMaxWave++;
			for (int j=0;j<listCreature.getLength();j++){
				Node creature = listCreature.item(j);
				if (creature.getNodeName().equalsIgnoreCase("creature")){

					Element element = Element.getElement(XmlUtil.getAttributeFromNode(creature, "element"));
					int life = XmlUtil.getAttributeIntFromNode(creature, "life");
					float speed = XmlUtil.getAttributeIntFromNode(creature, "speed");
					int fireRate = XmlUtil.getAttributeIntFromNode(creature, "fireRate");
					int rewardValue = XmlUtil.getAttributeIntFromNode(creature, "rewardValue");
					int scoreValue = XmlUtil.getAttributeIntFromNode(creature, "scoreValue");
					boolean fly = XmlUtil.getAttributeBooleanFromNode(creature, "fly");
					int idTexture = XmlUtil.getAttributeIntFromNode(creature, "textureId");
					int number = XmlUtil.getAttributeIntFromNode(creature, "number");

					for (int num=0;num<number;num++){
						Creature creat = new Creature(element,
								life,
								speed,
								fireRate,
								rewardValue,
								scoreValue,
								fly,
								this.textureRegion[idTexture]);
						wave.addEntity(creat);				
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
