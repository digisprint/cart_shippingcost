package com.java.spring.api.entity;
import java.util.Arrays;
public enum InventoryStatus {

	IN_STOCK(0, true), 
	LOW_STOCK(1, true), 
	OUT_OF_STOCK(2, false), 
	UNAVAILABLE(3, false);
	
	private boolean available;
	private int level;
	
	private InventoryStatus(int level, boolean available) {
		this.level = level;
		this.available = available;
	}
	
	public boolean available() {
		return this.available;
	}
	
	public int level() {
		return this.level;
	}
	
	public static InventoryStatus find(int inventoryLevel) {
		return Arrays.asList(InventoryStatus.values()).stream()
				.filter(invStatus -> invStatus.level() == inventoryLevel)
				.findAny().orElse(IN_STOCK);
	}
}
