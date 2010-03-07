package electroacid.defense;

public class Element {

	private String name;
	private Element weakness = null;
	private Element strength = null;
	
	public Element(String myName){
		this.name = myName;
	}	
	public Element(String myName, Element myWeakness, Element myStrength){
		this.name = myName;
		this.weakness = myWeakness;
		this.strength = myStrength;
	}
	
	public boolean assignWeakness(Element myWeakness){
		if(this.weakness == null){
			return false;
		}else{
			this.weakness = myWeakness;
			return true;
		}
	}
	public boolean assignStrength(Element myStrength){
		if(this.strength == null){
			return false;
		}else{
			this.strength = myStrength;
			return true;
		}
	}
	public String getName(){
		return this.name;
	}
	public Element getWeakness(){
		return this.weakness;
	}
	public Element getstrengths(){
		return this.strength;
	}
}
