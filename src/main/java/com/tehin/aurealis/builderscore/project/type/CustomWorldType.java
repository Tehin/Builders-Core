package com.tehin.aurealis.builderscore.project.type;

public enum CustomWorldType {
	
	VOID("void", "2;0;1;"),
	FLAT("flat", "3;minecraft:bedrock,2*minecraft:dirt,minecraft:grass;1;");
	
	private String 
	generatorSettings,
	name;

	CustomWorldType(String name, String generatorSettings) {
		this.generatorSettings = generatorSettings;
		this.name = name;
	}
	
	public String getGeneratorSettings() {
		return this.generatorSettings;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static CustomWorldType fromName(String name) throws IllegalArgumentException {
		for (CustomWorldType type : CustomWorldType.values()) {
			if (type.getName().equalsIgnoreCase(name)) return type;
		};
		
		throw new IllegalArgumentException("Value " + name + " not found");
	}

}
