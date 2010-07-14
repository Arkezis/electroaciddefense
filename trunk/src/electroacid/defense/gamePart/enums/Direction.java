package electroacid.defense.gamePart.enums;

/**
 * it's a simple direction
 * @author cilheo
 * @version 1.0b
 */
public enum Direction {
	Up,
	Down,
	Right,
	Left;


	public static Direction getDirection(String name){
		if (name.equalsIgnoreCase("up") || name.equalsIgnoreCase("u")) return Direction.Up;
		else if (name.equalsIgnoreCase("down") || name.equalsIgnoreCase("d")) return Direction.Down;
		else if (name.equalsIgnoreCase("right") || name.equalsIgnoreCase("r")) return Direction.Right;
		else if (name.equalsIgnoreCase("left") || name.equalsIgnoreCase("l")) return Direction.Left;
		return null;
	}

}
