package gui;

import electroacid.defense.Box;

public class matriceBox {

	private Box[][] matrice;
	
	int xMax;
	int offsetX;
	
	int yMax;
	int offsetY;
	
	public matriceBox(int _xMax,int _yMax,int _offsetX,int _offsetY){
		this.xMax = _xMax;
		this.yMax = _yMax;
		this.offsetX = _offsetX;
		this.offsetY = _offsetY;
		
		this.matrice = new Box[this.xMax/this.offsetX][this.yMax/this.offsetY];
	}
	
	public Box getBox(int x, int y){
		return this.matrice[x/this.offsetX][y/this.offsetY];
	}
	
}
