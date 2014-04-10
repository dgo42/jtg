package org.edgo.jtg.core;

public final class Constants {

	private Constants() {
	}

	public static final boolean	USING_CHACHE			= true;

	public static final String	SCHEMA_DIR				= "jtgSchema";
	public static final String	TEMPLATE_DIR			= "jtgTemplates";
	public static final String	GENERATOROUT_DIR		= "jtgGeneratorOutput";
	public static final String	SOURCEOUT_DIR			= "jtgSourceOutput";
	public static final String	JAR_OUT_DIR				= "jtgJarOutput";

	public static final String	SCHEMA					= "jtgSchema.jgsd";
	public static final String	START_TEMPLATE			= "jtgMain.jtg";
	public static final String	PROJECT_FILE			= "jtgdata.xml";

	public static final String	SCHEMA_PACKAGE			= "org.edgo.jtg.schema";
	public static final String	GENERATED_PACKAGE		= "org.edgo.jtg.generated";
	public static final String	CODEGEN_BASICS_PACKAGE	= "org.edgo.jtg.basics";

	public static final String	START_LINE_DIRECTIVE	= "#line 2 \"{0}\"" + GeneratorUtils.EOL;
	public static final String	INDENT					= "            ";
	public static final String	INDENT_MACRO			= "        ";
	public static final String	INDENT_SCRIPT			= "    ";
}
