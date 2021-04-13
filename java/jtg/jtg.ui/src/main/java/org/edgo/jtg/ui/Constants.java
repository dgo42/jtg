package org.edgo.jtg.ui;

import java.util.regex.Pattern;

import org.edgo.jtg.core.GeneratorCommand;

public final class Constants {

	private Constants() {
	}

	// common setting
	public static final String	PROJECT_TYPE				= "org.edgo.jtg.type";

	// master settings
	public static final String	M_SCHEMA_DIR_PREF			= "org.edgo.jtg.master.schema.dir";
	public static final String	M_TEMPLATE_DIR_PREF			= "org.edgo.jtg.master.template.dir";
	public static final String	M_SCHEMA_FILE_PREF			= "org.edgo.jtg.master.schema.file";
	public static final String	M_START_TEMPLATE_FILE_PREF	= "org.edgo.jtg.master.template.file";
	public static final String	M_SCHEMA_PACKAGE_PREF		= "org.edgo.jtg.master.schema.package";
	public static final String	M_TEMPLATE_PACKAGE_PREF		= "org.edgo.jtg.master.template.package";
	
	// slave settings
	public static final String	S_MASTER_PROJECT			= "org.edgo.jtg.slave.masterProject";
	public static final String	S_PROJECT_DIR_PREF			= "org.edgo.jtg.slave.project.dir";
	public static final String	S_PROJECT_FILE_PREF			= "org.edgo.jtg.slave.project.file";
	
	// standalone settings
	public static final String	ST_SCHEMA_DIR_PREF			= "org.edgo.jtg.schema.dir";
	public static final String	ST_TEMPLATE_DIR_PREF		= "org.edgo.jtg.template.dir";
	public static final String	ST_SOURCE_OUT_DIR_PREF		= "org.edgo.jtg.source.output.dir";
	public static final String	ST_JAR_OUTPUT_DIR_PREF		= "org.edgo.jtg.jar.output.dir";
	public static final String	ST_SCHEMA_FILE_PREF			= "org.edgo.jtg.schema.file";
	public static final String	ST_PROJECT_FILE_PREF		= "org.edgo.jtg.project.file";
	public static final String	ST_START_TEMPLATE_FILE_PREF	= "org.edgo.jtg.template.file";
	public static final String	ST_SCHEMA_PACKAGE_PREF		= "org.edgo.jtg.schema.package";
	public static final String	ST_TEMPLATE_PACKAGE_PREF	= "org.edgo.jtg.template.package";
	public static final String	ST_USING_CACHE_PREF			= "org.edgo.jtg.cache.using";
	public static final String	ST_GOAL_PREF				= "org.edgo.jtg.goal";

	// default values for master type
	public static final String	M_SCHEMA_DIR_DEFAULT		= "src/main/schema";
	public static final String	M_TEMPLATE_DIR_DEFAULT		= "src/main/template";
	public static final String	M_SCHEMA_FILE_DEFAULT		= "schema.xsd";
	public static final String	M_PROJECT_FILE_DEFAULT		= "project.xml";
	public static final String	M_START_TEMPLATE_FILE_DEFAULT	= "main.jtg";
	public static final String	M_SCHEMA_PACKAGE_DEFAULT	= "org.edgo.jtg.schema";
	public static final String	M_TEMPLATE_PACKAGE_DEFAULT	= "org.edgo.jtg.template";
	
	// default values for slave type
	public static final String	S_SCHEMA_DIR_DEFAULT		= "src/main/schema";
	public static final String	S_PROJECT_FILE_DEFAULT		= "project.xml";

	// default values for standalone type
	public static final String	ST_SCHEMA_DIR_DEFAULT		= "src/main/schema";
	public static final String	ST_TEMPLATE_DIR_DEFAULT		= "src/main/template";
	public static final String	ST_SOURCE_OUT_DIR_DEFAULT	= "target/gen-jtg-sources";
	public static final String	ST_JAR_OUTPUT_DIR_DEFAULT	= "target/gen-jar";
	public static final String	ST_SCHEMA_FILE_DEFAULT		= "schema.xsd";
	public static final String	ST_PROJECT_FILE_DEFAULT		= "project.xml";
	public static final String	ST_START_TEMPLATE_FILE_DEFAULT	= "main.jtg";
	public static final String	ST_SCHEMA_PACKAGE_DEFAULT	= "org.edgo.jtg.schema";
	public static final String	ST_TEMPLATE_PACKAGE_DEFAULT	= "org.edgo.jtg.template";
	public static final boolean	ST_USING_CACHE_DEFAULT		= true;
	public static final GeneratorCommand ST_GOAL_DEFAULT	= GeneratorCommand.COMPLETE;

	
	public final static Pattern	SCHEMA_FILE_MASK			= Pattern.compile("^[^\\.]*schema\\.xsd$");

	public final static Pattern	PROJECT_FILE_MASK			= Pattern.compile("^[^\\.]*project\\.xml$");

	public final static Pattern	TEMPLATE_FILE_MASK			= Pattern.compile("^[^\\.]+\\.jtg$");

	public final static Pattern	ALL_FILE_MASK				= Pattern.compile("^(.*schema\\.xsd|.*project\\.xml|.+\\.jtg)$");

	public final static String	CONSOLE_NAME				= "JTG Console";
}
