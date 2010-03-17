package electroacid.defense.gui;

import electroacid.defense.box.Box;
import electroacid.defense.box.BoxBuildable;

public class MatriceBox {

	private Box[][] matrice;
	
	int xMax;
	int offsetX;
	
	int yMax;
	int offsetY;
	
	public MatriceBox(int _xMax,int _yMax,int _offsetX,int _offsetY){
		this.xMax = _xMax;
		this.yMax = _yMax;
		this.offsetX = _offsetX;
		this.offsetY = _offsetY;
		
		this.matrice = new Box[this.xMax/this.offsetX][this.yMax/this.offsetY];
		
		for (int i=0;i<this.xMax/this.offsetX;i++) {
			for (int j=0;j<this.yMax/this.offsetY;j++) {
				this.matrice[i][j] = new BoxBuildable(i*this.offsetX,j*this.offsetY,this.offsetX,this.offsetY);
			}
		}
		
	}
	
	public Box getBox(int x, int y){
		if (x>this.xMax || y>this.yMax) return null;
		return this.matrice[x/this.offsetX][y/this.offsetY];
	}
	
	public void setBox(Box box,int x, int y){
		this.matrice[x/this.offsetX][y/this.offsetY] = box;
	}
	
}
