package org.edgo.jtg.core;

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

	private GeneratorCommand(String value, int index) {
		this.value = value;
		this.index = index;
	}

	public static GeneratorCommand parse(String value) {
		for (GeneratorCommand ind : values) {
			if (ind.value.equals(value)) {
				return ind;
			}
		}
		return null;
	}

	public static GeneratorCommand parse(int index) {
		for (GeneratorCommand ind : values) {
			if (ind.index == index) {
				return ind;
			}
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
