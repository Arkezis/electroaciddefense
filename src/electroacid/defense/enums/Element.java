package electroacid.defense.enums;

public enum Element {
	Fire,
	Water,
	Electricity,
	Iron;
	
	
	public double getModifier(Element _otherElement) {
		double modifier = 1;
		switch (this) {
		case Fire :
			if (_otherElement == Element.Water) modifier = 0.75;
			else if (_otherElement == Element.Iron) modifier = 1.25;
			break;	
		case Water :
			if (_otherElement == Element.Electricity) modifier = 0.75;
			else if (_otherElement == Element.Iron) modifier = 1.25;
			break;
		case Electricity :
			if (_otherElement == Element.Iron) modifier = 0.75;
			else if (_otherElement == Element.Water) modifier = 1.25;
			break;
			
		case Iron :
			if (_otherElement == Element.Fire) modifier = 0.75;
			else if (_otherElement == Element.Electricity) modifier = 1.25;
			break;
		}
		return modifier;
	}
	
	
	
}
