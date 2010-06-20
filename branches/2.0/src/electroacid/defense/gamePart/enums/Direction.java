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
		if (name.equalsIgnoreCase("up")) return Direction.Up;
		else if (name.equalsIgnoreCase("down")) return Direction.Down;
		else if (name.equalsIgnoreCase("right")) return Direction.Right;
		else if (name.equalsIgnoreCase("left")) return Direction.Left;
		return null;
	}

}
