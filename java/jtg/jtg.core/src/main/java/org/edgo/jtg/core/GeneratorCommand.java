package org.edgo.jtg.core;

import java.util.HashMap;
import java.util.Map;

public final class GeneratorCommand {

	public final static GeneratorCommand SCHEMA_ONLY = new GeneratorCommand("JAVA", 0);

	public final static GeneratorCommand TEMPLATES_ONLY = new GeneratorCommand("TEMPLATES_ONLY", 1);

	public final static GeneratorCommand SOURCES_ONLY = new GeneratorCommand("SOURCES_ONLY", 2);

	public final static GeneratorCommand COMPILE_ONLY = new GeneratorCommand("COMPILE_ONLY", 3);

	public final static GeneratorCommand JAR_ONLY = new GeneratorCommand("JAR_ONLY", 4);

	public final static GeneratorCommand COMPLETE = new GeneratorCommand("COMPLETE", 5);

	public final static GeneratorCommand[] values = new GeneratorCommand[] { SCHEMA_ONLY, TEMPLATES_ONLY, SOURCES_ONLY,
			COMPILE_ONLY, JAR_ONLY, COMPLETE };

	private String value;

	private int index;

	private final static Map<Integer, GeneratorCommand> indexMap = new HashMap<Integer, GeneratorCommand>();
	private final static Map<String, GeneratorCommand> valueMap = new HashMap<String, GeneratorCommand>();

	static {
		indexMap.put(SCHEMA_ONLY.index, SCHEMA_ONLY);
		indexMap.put(TEMPLATES_ONLY.index, TEMPLATES_ONLY);
		indexMap.put(SOURCES_ONLY.index, SOURCES_ONLY);
		indexMap.put(COMPILE_ONLY.index, COMPILE_ONLY);
		indexMap.put(JAR_ONLY.index, JAR_ONLY);
		indexMap.put(COMPLETE.index, COMPLETE);

		valueMap.put(SCHEMA_ONLY.value, SCHEMA_ONLY);
		valueMap.put(TEMPLATES_ONLY.value, TEMPLATES_ONLY);
		valueMap.put(SOURCES_ONLY.value, SOURCES_ONLY);
		valueMap.put(COMPILE_ONLY.value, COMPILE_ONLY);
		valueMap.put(JAR_ONLY.value, JAR_ONLY);
		valueMap.put(COMPLETE.value, COMPLETE);
	}

	private GeneratorCommand(String value, int index) {
		this.value = value;
		this.index = index;
	}

	public static GeneratorCommand parse(String value) {
		if (valueMap.containsKey(value)) {
			return valueMap.get(value);
		}
		return null;
	}

	public static GeneratorCommand parse(int index) {
		if (indexMap.containsKey(index)) {
			return indexMap.get(index);
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public int getIndex() {
		return index;
	}

	public int hashCode() {
		return value.hashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof GeneratorCommand)) {
			return false;
		}
		return value.equals(((GeneratorCommand) obj).value);
	}
}
