package electroacid.defense.gamePart.box;

/**
 * It's a box on the game map
 * @author cilheo
 * @version 1.0b
 */
public abstract class Box {


	/** The x coordinate on the map */
	protected int x;

	/** The y coordinate on the map */
	protected int y;

	/** Width of the box */
	protected int width;

	/** Height of the box */
	protected int height;

	/**
	 * Constructor of the box
	 * @param _x the x coordinate on the map
	 * @param _y the y coordinate on the map
	 * @param _width widht of the box
	 * @param _height height of the box
	 */
	public Box(int _x, int _y, int _width, int _height) {
		this.x = _x;
		this.y = _y;
		this.width = _width;
		this.height = _height;
	}

	/** @return the x */
	public int getX() {return x;}

	/** @param x the x to set */
	public void setX(int _x) {this.x = _x;}

	/** @return the y */
	public int getY() {return y;}

	/** @param y the y to set */
	public void setY(int _y) {this.y = _y;}

	/** @return the width */
	public int getWidth() {return width;}

	/** @param width the width to set */
	public void setWidth(int _width) {this.width = _width;}

	/** @return the height */
	public int getHeight() {return height;}

	/** @param height the height to set */
	public void setHeight(int _height) {this.height = _height;}

}
