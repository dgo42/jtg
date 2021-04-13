package org.edgo.jtg.core;

import java.util.HashMap;
import java.util.Map;

public final class ProjectType {

	public final static ProjectType MASTER = new ProjectType("MASTER", 0);

	public final static ProjectType SLAVE = new ProjectType("SLAVE", 1);

	public final static ProjectType STANDALONE = new ProjectType("STANDALONE", 2);

	public final static ProjectType[] values = new ProjectType[] { MASTER, SLAVE, STANDALONE };

	private String value;

	private int index;
	
	private final static Map<Integer, ProjectType> indexMap = new HashMap<Integer, ProjectType>();
	private final static Map<String, ProjectType> valueMap = new HashMap<String, ProjectType>();

	static {
		indexMap.put(MASTER.index, MASTER);
		indexMap.put(SLAVE.index, SLAVE);
		indexMap.put(STANDALONE.index, STANDALONE);

		valueMap.put(MASTER.value, MASTER);
		valueMap.put(SLAVE.value, SLAVE);
		valueMap.put(STANDALONE.value, STANDALONE);
	}
	
	private ProjectType(String value, int index) {
		this.value = value;
		this.index = index;
	}

	public static ProjectType parse(String value) {
		if (valueMap.containsKey(value)) {
			return valueMap.get(value);
		}
		return null;
	}

	public static ProjectType parse(int index) {
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
		if (!(obj instanceof ProjectType)) {
			return false;
		}
		return value.equals(((ProjectType) obj).value);
	}
}
