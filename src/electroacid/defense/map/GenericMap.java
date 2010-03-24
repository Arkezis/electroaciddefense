package electroacid.defense.map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.android.angle.AngleTileMap;

import utils.XmlUtil;
import android.content.Context;
import electroacid.defense.box.Box;
import electroacid.defense.box.BoxBuildable;
import electroacid.defense.box.BoxPath;

public class GenericMap {

	private Box[][] matrice;
	
	int xMax;
	int offsetX;
	
	int yMax;
	int offsetY;
	
	public GenericMap(int _xMax,int _yMax,int _offsetX,int _offsetY){
		this.xMax = _xMax;
		this.yMax = _yMax;
		this.offsetX = _offsetX;
		this.offsetY = _offsetY;
		
		this.matrice = new Box[this.yMax/this.offsetY][this.xMax/this.offsetX];
		
	}
	
	//texture not in charge
	public void buildMap(Context context,AngleTileMap map,int xmlResourceId) throws Exception{
		Document mapXml = XmlUtil.getDocumentFromResource(context, xmlResourceId);

		NodeList listBox = mapXml.getDocumentElement().getElementsByTagName("box");
		
		int numberOfCasePerLigne = this.xMax/this.offsetX;
		for (int i=0;i<listBox.getLength();i++){
			
			Node node = listBox.item(i);
			
			int line = i/numberOfCasePerLigne;
			int column = i - line*numberOfCasePerLigne;
			
			if (i==100) {
				int a =2;
				a = a+2;
			}
			
			int idTexture = XmlUtil.getAttributeIntFromNode(node, "texture");
			String type = XmlUtil.getAttributeFromNode(node, "type");
			
			if (type.equalsIgnoreCase("buildable")) {
				this.matrice[line][column] = new BoxBuildable(line*this.offsetX, column*this.offsetY, this.offsetX, this.offsetY);
			}else if (type.equalsIgnoreCase("path")){
				this.matrice[line][column] = new BoxPath(line*this.offsetX, column*this.offsetY, this.offsetX, this.offsetY);
			} else {
				//Your xml was so bad
			}
			map.mMap[i] = idTexture;
		}
	}
	
	public Box getBox(int x, int y){
		if (x>this.xMax || y>this.yMax) return null;
		return this.matrice[y/this.offsetY][x/this.offsetX];
	}
	
	public void setBox(Box box,int x, int y){
		this.matrice[y/this.offsetY][x/this.offsetX] = box;
	}
	
}
