package org.edgo.jtg.core;

public enum GeneratorCommand {
	// @formatter:off
	SCHEMA_ONLY(0),
	TEMPLATES_ONLY(1),
	SOURCES_ONLY(2),
	COMPILE_ONLY(3),
	JAR_ONLY(4),
	COMPLETE(5),
	GENERATE(6);
	// @formatter:on

	private final static GeneratorCommand[] values = new GeneratorCommand[] { 
		// @formatter:off
		SCHEMA_ONLY, 
		TEMPLATES_ONLY, 
		SOURCES_ONLY,
		COMPILE_ONLY, 
		JAR_ONLY, 
		COMPLETE,
		GENERATE 
		// @formatter:on
	};

	private int index;

	private GeneratorCommand(int index) {
		this.index = index;
	}

	public static GeneratorCommand valueOfIndex(int index) {
		if (index >= 0 && index < values.length) {
			return values[index];
		}
		return null;
	}

	public int getIndex() {
		return index;
	}
}
