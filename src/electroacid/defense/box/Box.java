package electroacid.defense.box;


import com.android.angle.AngleSprite;

public abstract class Box {

	protected AngleSprite sprite;

	protected int x;
	protected int y;

	protected int width;
	protected int height;

	public Box(int _x, int _y, int _width, int _height) {
		this.sprite = new AngleSprite(null);
		this.x = _x;
		this.y = _y;
		this.width = _width;
		this.height = _height;
	}

	/**
	 * @return the sprite
	 */
	public AngleSprite getSprite() {
		return this.sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(AngleSprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return this.y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
