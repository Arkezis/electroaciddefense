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
import electroacid.defense.enums.Direction;

/**
 * Read a xml file and create map
 * create the path
 * @author cilheo
 * @version 1.0b
 */
public class GenericMap {

	/** it's the map */
	private Box[][] matrice;
	
	/** max x coordinate*/
	int xMax;
	/** width of box*/
	int offsetX;
	/** max y coordinate*/
	int yMax;
	/** height of box */
	int offsetY;
	/** box which start the path */
	public BoxPath firstBoxPath;
	
	/**
	 * Creation of a generic map, create the matrice
	 * @param _xMax max x coordinate
	 * @param _yMax max y coordinate
	 * @param _offsetX width of box
	 * @param _offsetY height of box
	 */
	public GenericMap(int _xMax,int _yMax,int _offsetX,int _offsetY){
		this.xMax = _xMax;
		this.yMax = _yMax;
		this.offsetX = _offsetX;
		this.offsetY = _offsetY;
		
		this.matrice = new Box[this.yMax/this.offsetY][this.xMax/this.offsetX];
		
	}
	
	/**
	 * Read a xml file and create the map, load texture, and create path
	 * @param context
	 * @param map contains all texture
	 * @param xmlResourceId id of the xml file
	 * @throws Exception
	 */
	public void buildMap(Context context,AngleTileMap map,int xmlResourceId) throws Exception{
		Document mapXml = XmlUtil.getDocumentFromResource(context, xmlResourceId);

		NodeList listBox = mapXml.getDocumentElement().getElementsByTagName("box");
		
		int numberOfCasePerLigne = this.xMax/this.offsetX;
		for (int i=0;i<listBox.getLength();i++){
			Node node = listBox.item(i);
			
			int line = i/numberOfCasePerLigne;
			int column = i - line*numberOfCasePerLigne;
			
			int idTexture = XmlUtil.getAttributeIntFromNode(node, "texture");
			String type = XmlUtil.getAttributeFromNode(node, "type");
			
			if (type.equalsIgnoreCase("buildable")) {
				this.matrice[line][column] = new BoxBuildable(line*this.offsetX, column*this.offsetY, this.offsetX, this.offsetY);
			}else if (type.equalsIgnoreCase("path")){
				boolean firstBoxPath = XmlUtil.getAttributeBooleanFromNode(node, "startPath");
				this.matrice[line][column] = new BoxPath(line*this.offsetX, column*this.offsetY, this.offsetX, this.offsetY);
				if (firstBoxPath) this.firstBoxPath=(BoxPath)this.matrice[line][column];
			} else {
				//Your xml was so bad
			}
			
			map.mMap[i] = idTexture;
		}
		this.buildPath();
	}
	
	/** build the path */
	private void buildPath(){
		BoxPath actual = this.firstBoxPath;
		do {
			actual = getNextBoxPath(actual.getX(),actual.getY());
		} while (actual != null);
	}
	
	/**
	 * search the nextPath
	 * @param x coordinate of the actual boxPath
	 * @param y coordinate of the actual boxPath
	 * @return the next boxPath or null if it the end
	 */
	private BoxPath getNextBoxPath(int x, int y){
		BoxPath actual = (BoxPath) this.getBox(y, x);
		BoxPath boxPath;
		Box box = this.getBox(y, x+offsetX);
		if (box instanceof BoxPath) {
			boxPath = (BoxPath) box;
			if (boxPath.getNextPath() == null) {;
				actual.setDirection(Direction.Down);
				actual.setNextPath(boxPath);;
				return boxPath;
			}
		}
		
		box = this.getBox(y, x-offsetX);
		if (box instanceof BoxPath) {
			boxPath = (BoxPath) box;
			if (boxPath.getNextPath() == null) {
				actual.setDirection(Direction.Up);
				actual.setNextPath(boxPath);
				return boxPath;
			}
		}
		
		box = this.getBox(y-offsetY, x);
		if (box instanceof BoxPath) {
			boxPath = (BoxPath) box;
			if (boxPath.getNextPath() == null) {
				actual.setDirection(Direction.Left);
				actual.setNextPath(boxPath);
				return boxPath;
			}
		}
		
		box = this.getBox( y+offsetY,x);
		if (box instanceof BoxPath) {
			boxPath = (BoxPath) box;
			if (boxPath.getNextPath() == null) {
				actual.setDirection(Direction.Right);
				actual.setNextPath(boxPath);
				return boxPath;
			}
		}
		return null;
	}
	
	/**
	 * get the box
	 * @param x coordinate of the wanted box
	 * @param y coordinate of the wanted box
	 * @return the box wanted or null
	 */
	public Box getBox(int x, int y){
		if (x>=this.xMax || y>=this.yMax || x<0 || y<0) return null;
		return this.matrice[y/this.offsetY][x/this.offsetX];
	}
	
}
