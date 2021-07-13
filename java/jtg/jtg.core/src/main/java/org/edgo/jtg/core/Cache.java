package org.edgo.jtg.core;

import java.util.Map;
import java.util.Set;

public class Cache {
	private Map<String, Source> sources;
	private Set<String> jars;

	public Cache() {

	}

	public Cache(Map<String, Source> sources, Set<String> jars) {
		this.sources = sources;
		this.jars = jars;
	}

	public Map<String, Source> getSources() {
		return sources;
	}

	public void getSources(Map<String, Source> sources) {
		this.sources = sources;
	}

	public Set<String> getJars() {
		return jars;
	}

	public void getJars(Set<String> jars) {
		this.jars = jars;
	}
}
