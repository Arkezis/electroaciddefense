package electroacid.defense.enums;

/**
 * Element of tower or creature
 * @author cilheo
 * @version 1.0b
 */
public enum Element {
	Fire,
	Water,
	Electricity,
	Iron;

	/**
	 * Compared two element and assign a modifier
	 * @param _otherElement element to compared with this
	 * @return the modifier (1 if no modifier)
	 */
	public double getModifier(Element _otherElement) {
		double modifier = 1;
		switch (this) {
		case Fire :
			if (_otherElement == Element.Water) modifier = 0.50;
			else if (_otherElement == Element.Iron) modifier = 1.50;
			break;	
		case Water :
			if (_otherElement == Element.Electricity) modifier = 0.50;
			else if (_otherElement == Element.Iron) modifier = 1.50;
			break;
		case Electricity :
			if (_otherElement == Element.Iron) modifier = 0.50;
			else if (_otherElement == Element.Water) modifier = 1.50;
			break;
		case Iron :
			if (_otherElement == Element.Fire) modifier = 0.50;
			else if (_otherElement == Element.Electricity) modifier = 1.50;
			break;
		}
		return modifier;
	}

	/**
	 * Create a element with the type name
	 * Exemple : name = "elecricity" return the element electricity
	 * @param name type of the element
	 * @return a element or null if the element name don't exist
	 */
	public static Element getElement(String name){
		if (name.equalsIgnoreCase("fire")) return Element.Fire;
		else if (name.equalsIgnoreCase("water")) return Element.Water;
		else if (name.equalsIgnoreCase("electricity")) return Element.Electricity;
		else if (name.equalsIgnoreCase("iron")) return Element.Iron;
		return null;
	}
}
