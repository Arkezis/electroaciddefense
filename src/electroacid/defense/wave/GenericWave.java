package electroacid.defense.wave;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.XmlUtil;
import android.content.Context;

import com.android.angle.AngleTileMap;

import electroacid.defense.box.BoxBuildable;
import electroacid.defense.box.BoxPath;
import electroacid.defense.enums.Element;

public class GenericWave {

	
	
	public void GenericWave(Context context,AngleTileMap map,int xmlResourceId) throws Exception{
		Document waveXml = XmlUtil.getDocumentFromResource(context, xmlResourceId);

		NodeList listWave = waveXml.getDocumentElement().getElementsByTagName("wave");
		
		for (int i=0;i<listWave.getLength();i++){
			NodeList listCreature = listWave.item(i).getChildNodes();
			
			for (int j=0;j<listCreature.getLength();j++){
				Node creature = listCreature.item(j);
				
				Element element = Element.getElement(XmlUtil.getAttributeFromNode(creature, "element"));
				int life = XmlUtil.getAttributeIntFromNode(creature, "life");
				int speed = XmlUtil.getAttributeIntFromNode(creature, "speed");
				int fireRate = XmlUtil.getAttributeIntFromNode(creature, "life");
				int rewardValue = XmlUtil.getAttributeIntFromNode(creature, "life");
				int fly = XmlUtil.getAttributeIntFromNode(creature, "life");
				
				int idTexture = XmlUtil.getAttributeIntFromNode(creature, "texture");
				
	
				map.mMap[i] = idTexture;			
			}
			

		}
		//this.buildPath();
	}
	
	
}
