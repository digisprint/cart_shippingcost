package com.java.spring.api.entity;
import java.util.Set;
public enum Site {
	LP("LP","MX"),
	GAP("GAP","MX"),
	WS("WS","MX");

	private String siteId;
	private String defaultCountry;
	

private Site(String siteId, String defaultCountry)
{
	this.siteId = siteId;
	this.defaultCountry = defaultCountry;
}
public static Site find(String type) {
	return Set.of(Site.values()).stream()
			.filter(itemType -> itemType.name().equalsIgnoreCase(type))
			.findFirst().orElse(LP);
}
}

