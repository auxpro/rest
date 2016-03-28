package org.ap.web.internal;

public enum EUserRole {

	/* MEMBERS */
	
	ADMIN,
	GUEST,
	AUXILIARY,
	SERVICE,
	PREMIUM,
	;
	
	/* ATTRIBUTES */
	
	
	/* CONSTRUCTOR */
	
	private EUserRole() { }
	
	/* METHODS */
	
	public static EUserRole byId(String id) {
		if (id == null) return null;
		for (EUserRole type : EUserRole.values()) {
			if (type.name().equals(id.toUpperCase())) {
				return type;
			}
		}
		return null;
	}
}
