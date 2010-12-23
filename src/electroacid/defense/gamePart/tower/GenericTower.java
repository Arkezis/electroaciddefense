package electroacid.defense.gamePart.tower;

import java.util.LinkedList;

import org.anddev.andengine.entity.layer.ILayer;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;

import electroacid.defense.R;
import electroacid.defense.gamePart.Tower;
import electroacid.defense.gamePart.enums.Element;
import electroacid.defense.gamePart.enums.ShootPriority;
import electroacid.defense.utils.XmlUtil;

/**
 * Read a xml file and create all wave
 * @author cilheo
 * @version 1.0b
 */
public class GenericTower {

	/** Waves' creature */
	private LinkedList<Tower> listTower = new LinkedList<Tower>();

	private BaseGameActivity play;
	private Texture texture;
	private String file;
	
	
	
	public GenericTower(BaseGameActivity _play,Texture _texture,String _file){
		// TODO : File parameter is useless ??
		this.play = _play;
		this.texture = _texture;
		this.file = _file;
		
	}
	
	
	/**
	 * Read the xml file and create waves and creatures
	 * @param context use for read the xml file
	 * @param xmlResourceId id of the xml file
	 * @param game game's parameters
	 * @throws Exception
	 */
	public void build(final Context context,final int xmlResourceId,ILayer mGl) throws Exception{
		// TODO : mGl parameter is useless ???
		Document towerXml = XmlUtil.getDocumentFromResource(context, xmlResourceId);

		NodeList listNodeTower = towerXml.getDocumentElement().getElementsByTagName("tower");
		for (int i=0;i<listNodeTower.getLength();i++){
			Node tower = listNodeTower.item(i);
			Element element = Element.getElement(XmlUtil.getAttributeFromNode(tower, "element"));
			int fireRate = XmlUtil.getAttributeIntFromNode(tower, "fireRate");
			int cost = XmlUtil.getAttributeIntFromNode(tower, "cost");
			boolean fly = XmlUtil.getAttributeBooleanFromNode(tower, "fly");
			int damage = XmlUtil.getAttributeIntFromNode(tower, "damage");
			int targetNb = XmlUtil.getAttributeIntFromNode(tower, "targetNb");
			ShootPriority targetPriority=ShootPriority.getShootPriority(XmlUtil.getAttributeFromNode(tower, "targetPriority"));
			int level = XmlUtil.getAttributeIntFromNode(tower, "level");
			int idTexture = XmlUtil.getAttributeIntFromNode(tower, "idTexture");
			int shootArea = XmlUtil.getAttributeIntFromNode(tower, "shootArea");

			Tower tow = new Tower(element,
					fireRate,
					cost,
					fly,
					damage,
					targetNb,
					targetPriority,
					level,
					TextureRegionFactory.createTiledFromAsset(
							this.texture, this.play, "towers_creatures.png", idTexture*64, 64, 4, 2),
					shootArea);
			listTower.add(tow);				
		}
	}

	/**
	 * @return the listTower
	 */
	public LinkedList<Tower> getListTower() {
		return listTower;
	}

	/**
	 * @param listTower the listTower to set
	 */
	public void setListTower(LinkedList<Tower> listTower) {
		this.listTower = listTower;
	}
}
