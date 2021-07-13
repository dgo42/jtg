package org.edgo.jtg.ui;

import java.util.regex.Pattern;

import org.edgo.jtg.core.GeneratorCommand;

public final class Constants {

	private Constants() {
	}

	// common setting
	public static final String PROJECT_TYPE = "org.edgo.jtg.type";

	// leader settings
	public static final String L_SCHEMA_DIR_PREF = "org.edgo.jtg.leader.schema.dir";
	public static final String L_TEMPLATE_DIR_PREF = "org.edgo.jtg.leader.template.dir";
	public static final String L_SOURCE_OUT_DIR_PREF = "org.edgo.jtg.leader.source.output.dir";
	public static final String L_JAR_OUTPUT_DIR_PREF = "org.edgo.jtg.leader.jar.output.dir";
	public static final String L_SCHEMA_FILE_PREF = "org.edgo.jtg.leader.schema.file";
	public static final String L_START_TEMPLATE_FILE_PREF = "org.edgo.jtg.leader.template.file";
	public static final String L_SCHEMA_PACKAGE_PREF = "org.edgo.jtg.leader.schema.package";
	public static final String L_TEMPLATE_PACKAGE_PREF = "org.edgo.jtg.leader.template.package";
	public static final String L_MAIN_ARG_NAME = "org.edgo.jtg.leader.main.arg.name";

	// follower settings
	public static final String F_LEADER_PROJECT = "org.edgo.jtg.follower.leaderProject";
	public static final String F_PROJECT_DIR_PREF = "org.edgo.jtg.follower.project.dir";
	public static final String F_PROJECT_FILE_PREF = "org.edgo.jtg.follower.project.file";

	// standalone settings
	public static final String ST_SCHEMA_DIR_PREF = "org.edgo.jtg.schema.dir";
	public static final String ST_TEMPLATE_DIR_PREF = "org.edgo.jtg.template.dir";
	public static final String ST_SOURCE_OUT_DIR_PREF = "org.edgo.jtg.source.output.dir";
	public static final String ST_JAR_OUTPUT_DIR_PREF = "org.edgo.jtg.jar.output.dir";
	public static final String ST_SCHEMA_FILE_PREF = "org.edgo.jtg.schema.file";
	public static final String ST_PROJECT_FILE_PREF = "org.edgo.jtg.project.file";
	public static final String ST_START_TEMPLATE_FILE_PREF = "org.edgo.jtg.template.file";
	public static final String ST_SCHEMA_PACKAGE_PREF = "org.edgo.jtg.schema.package";
	public static final String ST_TEMPLATE_PACKAGE_PREF = "org.edgo.jtg.template.package";
	public static final String ST_USING_CACHE_PREF = "org.edgo.jtg.cache.using";
	public static final String ST_GOAL_PREF = "org.edgo.jtg.goal";

	// default values for leader type
	public static final String L_SCHEMA_DIR_DEFAULT = "src/main/schema";
	public static final String L_TEMPLATE_DIR_DEFAULT = "src/main/template";
	public static final String L_SCHEMA_FILE_DEFAULT = "schema.xsd";
	public static final String L_PROJECT_FILE_DEFAULT = "project.xml";
	public static final String L_START_TEMPLATE_FILE_DEFAULT = "main.jtg";
	public static final String L_SCHEMA_PACKAGE_DEFAULT = "org.edgo.jtg.schema";
	public static final String L_TEMPLATE_PACKAGE_DEFAULT = "org.edgo.jtg.template";

	// default values for follower type
	public static final String F_SCHEMA_DIR_DEFAULT = "src/main/schema";
	public static final String F_PROJECT_FILE_DEFAULT = "project.xml";

	// default values for standalone type
	public static final String ST_SCHEMA_DIR_DEFAULT = "src/main/schema";
	public static final String ST_TEMPLATE_DIR_DEFAULT = "src/main/template";
	public static final String ST_SOURCE_OUT_DIR_DEFAULT = "target/gen-jtg-sources";
	public static final String ST_JAR_OUTPUT_DIR_DEFAULT = "target/gen-jar";
	public static final String ST_SCHEMA_FILE_DEFAULT = "schema.xsd";
	public static final String ST_PROJECT_FILE_DEFAULT = "project.xml";
	public static final String ST_START_TEMPLATE_FILE_DEFAULT = "main.jtg";
	public static final String ST_SCHEMA_PACKAGE_DEFAULT = "org.edgo.jtg.schema";
	public static final String ST_TEMPLATE_PACKAGE_DEFAULT = "org.edgo.jtg.template";
	public static final boolean ST_USING_CACHE_DEFAULT = true;
	public static final GeneratorCommand ST_GOAL_DEFAULT = GeneratorCommand.COMPLETE;

	public final static Pattern SCHEMA_FILE_MASK = Pattern.compile("^[^\\.]*schema\\.xsd$");

	public final static Pattern PROJECT_FILE_MASK = Pattern.compile("^[^\\.]*project\\.xml$");

	public final static Pattern TEMPLATE_FILE_MASK = Pattern.compile("^[^\\.]+\\.jtg$");

	public final static Pattern ALL_FILE_MASK = Pattern.compile("^(.*schema\\.xsd|.*project\\.xml|.+\\.jtg)$");

	public final static String CONSOLE_NAME = "JTG Console";

	public static final String DEFAULT_SHEMA = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n"
	// @formatter:off
			+ "<schema xmlns=\"http://www.w3.org/2001/XMLSchema\" \r\n"
			+ "	xmlns:ci=\"http://jaxb.dev.java.net/plugin/code-injector\"\r\n"
			+ "	xmlns:jaxb=\"http://java.sun.com/xml/ns/jaxb\" \r\n"
			+ "	xmlns:jtg=\"http://edgo.org/jtg/ext\"\r\n"
			+ "	xmlns:tns=\"http://edgo.org/javaproject\" \r\n"
			+ "	xmlns:xjc=\"http://java.sun.com/xml/ns/jaxb/xjc\"\r\n"
			+ "	xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\r\n"
			+ "	jaxb:extensionBindingPrefixes=\"xjc\" \r\n"
			+ "	jaxb:version=\"2.0\"\r\n"
			+ "	targetNamespace=\"http://edgo.org/javaproject\">\r\n"
			+ "\r\n"
			+ "	<element name=\"project\" type=\"tns:ProjectType\">\r\n"
			+ "	</element>\r\n"
			+ "\r\n"
			+ "	<complexType name=\"ProjectType\">\r\n"
			+ "		<annotation>\r\n"
			+ "			<documentation>Describes the entire project configuration. It's is the root tag of the project.</documentation>\r\n"
			+ "		</annotation>\r\n"
			+ "		<all>\r\n"
			+ "		</all>\r\n"
			+ "	</complexType>\r\n"
			+ "</schema>\r\n";
	// @formatter:off

	public static final String DEFAULT_MAIN_TEMPLATE = "<#@codeTemplate language=\"java\" targetLanguage=\"console\" #>\r\n"
	// @formatter:off
			+ "<#@property name=\"project\" type=\"Project\" description=\"Project model\" #>\r\n"
			+ "edgo.org Java Template Generator (JTG) (c) 2003 - 2021\r\n"
			+ "generated on <#= new Date().toLocaleString() #>\r\n"
			+ "\r\n"
			+ "<#\r\n"
			+ "\r\n"
			+ "#>\r\n"
			+ "done.\r\n";
	// @formatter:on

	public static final String DEFAULT_PROJECT = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n"
	// @formatter:off
			+ "<p:project xmlns:p=\"http://edgo.org/javaproject\" xmlns:xi=\"http://www.w3.org/2001/XInclude\"\r\n"
			+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n"
			+ "	xsi:schemaLocation=\"http://edgo.org/javaproject schema.xsd\">\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "</p:project>\r\n";
	// @formatter:on
}
