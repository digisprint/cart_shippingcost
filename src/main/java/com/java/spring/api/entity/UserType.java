package com.java.spring.api.entity;

import java.util.Set;

public enum UserType {
	 GUEST,
	 REGISTERED;
		
		public static UserType find(String type) {
			return Set.of(UserType.values()).stream()
					.filter(itemType -> itemType.name().equalsIgnoreCase(type))
					.findFirst().orElse(GUEST);
		}
}
