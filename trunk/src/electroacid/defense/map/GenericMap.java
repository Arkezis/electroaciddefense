package electroacid.defense.map;

import java.util.LinkedList;

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
	
	int nbLine;
	int nbColumn;
	
	/** width of box*/
	int offsetX;
	/** height of box */
	int offsetY;
	/** box which start the path */
	public LinkedList<BoxPath> firstBoxPath;
	
	private int nbDiffTexture=0;
	
	/**
	 * Creation of a generic map, create the matrice
	 * @param _xMax max x coordinate
	 * @param _yMax max y coordinate
	 * @param _offsetX width of box
	 * @param _offsetY height of box
	 */
	public GenericMap(int _xMax,int _yMax,int _offsetX,int _offsetY,int _nbDiffTexture){
		this.nbLine = _xMax;
		this.nbColumn = _yMax;
		this.offsetX = _offsetX;
		this.offsetY = _offsetY;
		this.nbDiffTexture= _nbDiffTexture;
		
		this.matrice = new Box[this.nbLine][this.nbColumn];
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
		this.firstBoxPath = new LinkedList<BoxPath>();
		
		NodeList listBox = mapXml.getDocumentElement().getElementsByTagName("box");
		
		for (int i=0;i<listBox.getLength();i++){
			Node node = listBox.item(i);
			
			int line = i/this.nbColumn;
			int column = i - line*this.nbColumn;
			
			int idTexture = XmlUtil.getAttributeIntFromNode(node, "texture");
			String type = XmlUtil.getAttributeFromNode(node, "type");
			
			if (type.equalsIgnoreCase("buildable")) {
				this.matrice[line][column] = new BoxBuildable(column*this.offsetX, line*this.offsetY, this.offsetX, this.offsetY);
				map.mMap[i] = idTexture;
			}else if (type.equalsIgnoreCase("path")){
				boolean firstBoxPath = XmlUtil.getAttributeBooleanFromNode(node, "startPath");
				BoxPath b = new BoxPath(column*this.offsetX, line*this.offsetY, this.offsetX, this.offsetY);
				b.setDirection(Direction.getDirection(XmlUtil.getAttributeFromNode(node, "direction")));
				this.matrice[line][column] = b;
				if (firstBoxPath){
					this.firstBoxPath.add((BoxPath)this.matrice[line][column]);
				}
				map.mMap[i] = idTexture+this.nbDiffTexture;
			} else {
				//Your xml was so bad
			}
		}
		this.buildPath();
	}
	
	/** build the path */
	private void buildPath(){
		for (BoxPath first : this.firstBoxPath){
			setNextBoxPath(first);
			first.addNumberMaxPred();
		}
	}
	
	/**
	 * set the next box after the actuel
	 * @param actual the actual box
	 */
	private void setNextBoxPath(BoxPath actual){
		if (actual==null || actual.getNextPath()!=null ) return;
		int x = actual.getX();
		int y = actual.getY();
		Box box;
		switch(actual.getDirection()){
			case Up:
				box = this.getBox(x,y-offsetY);
				if (box instanceof BoxPath){
					actual.setNextPath((BoxPath) box);
					if (box != null)((BoxPath) box).addNumberMaxPred();
				}
				break;
			case Down:
				box = this.getBox(x, y+offsetY);
				if (box instanceof BoxPath){
					actual.setNextPath((BoxPath) box);
					if (box != null)((BoxPath) box).addNumberMaxPred();
				}
				break;
			case Right:
				box = this.getBox(x+offsetX,y);
				if (box instanceof BoxPath){
					actual.setNextPath((BoxPath) box);
					if (box != null)((BoxPath) box).addNumberMaxPred();
				}
				break;
			case Left:
				box = this.getBox(x-offsetX,y);
				if (box instanceof BoxPath){
					actual.setNextPath((BoxPath) box);
					if (box != null)((BoxPath) box).addNumberMaxPred();
				}
				break;
		}
		setNextBoxPath(actual.getNextPath());
	}
	
	/**
	 * get the box
	 * @param x coordinate of the wanted box
	 * @param y coordinate of the wanted box
	 * @return the box wanted or null
	 */
	public Box getBox(int x, int y){
		//the box is not in the matrice
		if (x>=this.nbColumn*this.offsetX || y>=this.nbLine*this.offsetY ||
			x<0 || y<0) return null;
		return this.matrice[y/this.offsetY][x/this.offsetX];
	}
	
}
