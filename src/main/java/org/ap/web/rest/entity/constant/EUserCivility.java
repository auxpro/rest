package org.ap.web.rest.entity.constant;

public enum EUserCivility {

	MR ("Mr"),
	MS ("Mme"),
	;
	
	private String _id;
	private EUserCivility(String s) { _id = s; }
	
	public String getId() { return _id; }
	
	public static EUserCivility fromString(String id) { 
		for (EUserCivility civ : EUserCivility.values()) {
			if (civ.getId().equals(id)) return civ;
		}
		return null;
	}
}
