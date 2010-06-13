package electroacid.defense.enums;

public enum ShootPriority {
	HIGHEST,
	WEAKEST,
	FIRSTIN_FIRSTDIE;
	
	public static ShootPriority getShootPriority(String name){
		if (name.equalsIgnoreCase("highest")) return ShootPriority.HIGHEST;
		else if (name.equalsIgnoreCase("weakest")) return ShootPriority.WEAKEST;
		else if (name.equalsIgnoreCase("firstin_firstdie")) return ShootPriority.FIRSTIN_FIRSTDIE;
		return null;
	}
	
}