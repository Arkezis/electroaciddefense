package electroacid.defense.gamePart.tower;

import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;

import com.android.angle.AngleSpriteLayout;
import com.android.angle.AngleSurfaceView;

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

	/**
	 * Read the xml file and create waves and creatures
	 * @param context use for read the xml file
	 * @param xmlResourceId id of the xml file
	 * @param game game's parameters
	 * @throws Exception
	 */
	public void build(final Context context,final int xmlResourceId,AngleSurfaceView mGl) throws Exception{
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
					new AngleSpriteLayout(mGl,32,32,R.drawable.tilemap,idTexture*32,128,32,32),
					shootArea);
			mGl.addObject(tow.getSprite());
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
