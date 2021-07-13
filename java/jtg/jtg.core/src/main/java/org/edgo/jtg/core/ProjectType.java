package org.edgo.jtg.core;

import java.util.HashMap;
import java.util.Map;

public enum ProjectType {
	// @formatter:on
	LEADER(0),
	FOLLOWER(1),
	STANDALONE(2);
	// @formatter:off

	public final static ProjectType[] values = new ProjectType[] { LEADER, FOLLOWER, STANDALONE };

	private int index;
	
	private final static Map<Integer, ProjectType> indexMap = new HashMap<Integer, ProjectType>();

	static {
		indexMap.put(LEADER.index, LEADER);
		indexMap.put(FOLLOWER.index, FOLLOWER);
		indexMap.put(STANDALONE.index, STANDALONE);
	}
	
	private ProjectType(int index) {
		this.index = index;
	}

	public static ProjectType parse(int index) {
		if (indexMap.containsKey(index)) {
			return indexMap.get(index);
		}
		return null;
	}

	public int getIndex() {
		return index;
	}

}
