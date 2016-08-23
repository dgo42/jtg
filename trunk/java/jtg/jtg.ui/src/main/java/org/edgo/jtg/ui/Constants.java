package org.edgo.jtg.ui;

import java.util.regex.Pattern;

import org.edgo.jtg.core.GeneratorCommand;

public final class Constants {

	private Constants() {
	}

	public static final String	SCHEMA_DIR_PREF				= "org.edgo.jtg.schema.dir";
	public static final String	TEMPLATE_DIR_PREF			= "org.edgo.jtg.template.dir";
	public static final String	SOURCE_OUT_DIR_PREF			= "org.edgo.jtg.source.output.dir";
	public static final String	JAR_OUTPUT_DIR_PREF			= "org.edgo.jtg.jar.output.dir";
	public static final String	SCHEMA_FILE_PREF			= "org.edgo.jtg.schema.file";
	public static final String	PROJECT_FILE_PREF			= "org.edgo.jtg.project.file";
	public static final String	START_TEMPLATE_FILE_PREF	= "org.edgo.jtg.template.file";
	public static final String	SCHEMA_PACKAGE_PREF			= "org.edgo.jtg.schema.package";
	public static final String	TEMPLATE_PACKAGE_PREF		= "org.edgo.jtg.template.package";
	public static final String	USING_CACHE_PREF			= "org.edgo.jtg.cache.using";
	public static final String	GOAL_PREF					= "org.edgo.jtg.goal";

	public static final String	SCHEMA_DIR_DEFAULT			= "src/main/schema";
	public static final String	TEMPLATE_DIR_DEFAULT		= "src/main/template";
	public static final String	SOURCE_OUT_DIR_DEFAULT		= "target/gen-jtg-sources";
	public static final String	JAR_OUTPUT_DIR_DEFAULT		= "target/gen-jar";
	public static final String	SCHEMA_FILE_DEFAULT			= "schema.xsd";
	public static final String	PROJECT_FILE_DEFAULT		= "project.xml";
	public static final String	START_TEMPLATE_FILE_DEFAULT	= "main.jtg";
	public static final String	SCHEMA_PACKAGE_DEFAULT		= "org.edgo.jtg.schema";
	public static final String	TEMPLATE_PACKAGE_DEFAULT	= "org.edgo.jtg.template";
	public static final boolean	USING_CACHE_DEFAULT			= true;
	public static final GeneratorCommand GOAL_DEFAULT		= GeneratorCommand.COMPLETE;

	public final static Pattern	SCHEMA_FILE_MASK			= Pattern.compile("^[^\\.]*schema\\.xsd$");

	public final static Pattern	PROJECT_FILE_MASK			= Pattern.compile("^[^\\.]*project\\.xml$");

	public final static Pattern	TEMPLATE_FILE_MASK			= Pattern.compile("^[^\\.]+\\.jtg$");

	public final static Pattern	ALL_FILE_MASK				= Pattern.compile("^(.*schema\\.xsd|.*project\\.xml|.+\\.jtg)$");

	public final static String	CONSOLE_NAME				= "JTG Console";
}
