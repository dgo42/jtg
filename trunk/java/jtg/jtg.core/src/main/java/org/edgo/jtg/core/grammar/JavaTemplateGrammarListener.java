// Generated from ..\grammar\JavaTemplateGrammar.g4 by ANTLR 4.5.3


package org.edgo.jtg.core.grammar;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JavaTemplateGrammarParser}.
 */
public interface JavaTemplateGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#template}.
	 * @param ctx the parse tree
	 */
	void enterTemplate(JavaTemplateGrammarParser.TemplateContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#template}.
	 * @param ctx the parse tree
	 */
	void exitTemplate(JavaTemplateGrammarParser.TemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_template}.
	 * @param ctx the parse tree
	 */
	void enterDirective_template(JavaTemplateGrammarParser.Directive_templateContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_template}.
	 * @param ctx the parse tree
	 */
	void exitDirective_template(JavaTemplateGrammarParser.Directive_templateContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_import}.
	 * @param ctx the parse tree
	 */
	void enterDirective_import(JavaTemplateGrammarParser.Directive_importContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_import}.
	 * @param ctx the parse tree
	 */
	void exitDirective_import(JavaTemplateGrammarParser.Directive_importContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_include}.
	 * @param ctx the parse tree
	 */
	void enterDirective_include(JavaTemplateGrammarParser.Directive_includeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_include}.
	 * @param ctx the parse tree
	 */
	void exitDirective_include(JavaTemplateGrammarParser.Directive_includeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_extends}.
	 * @param ctx the parse tree
	 */
	void enterDirective_extends(JavaTemplateGrammarParser.Directive_extendsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_extends}.
	 * @param ctx the parse tree
	 */
	void exitDirective_extends(JavaTemplateGrammarParser.Directive_extendsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_jar}.
	 * @param ctx the parse tree
	 */
	void enterDirective_jar(JavaTemplateGrammarParser.Directive_jarContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_jar}.
	 * @param ctx the parse tree
	 */
	void exitDirective_jar(JavaTemplateGrammarParser.Directive_jarContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_property}.
	 * @param ctx the parse tree
	 */
	void enterDirective_property(JavaTemplateGrammarParser.Directive_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_property}.
	 * @param ctx the parse tree
	 */
	void exitDirective_property(JavaTemplateGrammarParser.Directive_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_language}.
	 * @param ctx the parse tree
	 */
	void enterDirective_attr_language(JavaTemplateGrammarParser.Directive_attr_languageContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_language}.
	 * @param ctx the parse tree
	 */
	void exitDirective_attr_language(JavaTemplateGrammarParser.Directive_attr_languageContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_target_language}.
	 * @param ctx the parse tree
	 */
	void enterDirective_attr_target_language(JavaTemplateGrammarParser.Directive_attr_target_languageContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_target_language}.
	 * @param ctx the parse tree
	 */
	void exitDirective_attr_target_language(JavaTemplateGrammarParser.Directive_attr_target_languageContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_description}.
	 * @param ctx the parse tree
	 */
	void enterDirective_attr_description(JavaTemplateGrammarParser.Directive_attr_descriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_description}.
	 * @param ctx the parse tree
	 */
	void exitDirective_attr_description(JavaTemplateGrammarParser.Directive_attr_descriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_name}.
	 * @param ctx the parse tree
	 */
	void enterDirective_attr_name(JavaTemplateGrammarParser.Directive_attr_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_name}.
	 * @param ctx the parse tree
	 */
	void exitDirective_attr_name(JavaTemplateGrammarParser.Directive_attr_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_parent}.
	 * @param ctx the parse tree
	 */
	void enterDirective_attr_parent(JavaTemplateGrammarParser.Directive_attr_parentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_parent}.
	 * @param ctx the parse tree
	 */
	void exitDirective_attr_parent(JavaTemplateGrammarParser.Directive_attr_parentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_type}.
	 * @param ctx the parse tree
	 */
	void enterDirective_attr_type(JavaTemplateGrammarParser.Directive_attr_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_type}.
	 * @param ctx the parse tree
	 */
	void exitDirective_attr_type(JavaTemplateGrammarParser.Directive_attr_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_file}.
	 * @param ctx the parse tree
	 */
	void enterDirective_attr_file(JavaTemplateGrammarParser.Directive_attr_fileContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_file}.
	 * @param ctx the parse tree
	 */
	void exitDirective_attr_file(JavaTemplateGrammarParser.Directive_attr_fileContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_arg}.
	 * @param ctx the parse tree
	 */
	void enterDirective_attr_arg(JavaTemplateGrammarParser.Directive_attr_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_arg}.
	 * @param ctx the parse tree
	 */
	void exitDirective_attr_arg(JavaTemplateGrammarParser.Directive_attr_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_params}.
	 * @param ctx the parse tree
	 */
	void enterDirective_attr_params(JavaTemplateGrammarParser.Directive_attr_paramsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr_params}.
	 * @param ctx the parse tree
	 */
	void exitDirective_attr_params(JavaTemplateGrammarParser.Directive_attr_paramsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr}.
	 * @param ctx the parse tree
	 */
	void enterDirective_attr(JavaTemplateGrammarParser.Directive_attrContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#directive_attr}.
	 * @param ctx the parse tree
	 */
	void exitDirective_attr(JavaTemplateGrammarParser.Directive_attrContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(JavaTemplateGrammarParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(JavaTemplateGrammarParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#placeholder}.
	 * @param ctx the parse tree
	 */
	void enterPlaceholder(JavaTemplateGrammarParser.PlaceholderContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#placeholder}.
	 * @param ctx the parse tree
	 */
	void exitPlaceholder(JavaTemplateGrammarParser.PlaceholderContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#macrocode}.
	 * @param ctx the parse tree
	 */
	void enterMacrocode(JavaTemplateGrammarParser.MacrocodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#macrocode}.
	 * @param ctx the parse tree
	 */
	void exitMacrocode(JavaTemplateGrammarParser.MacrocodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(JavaTemplateGrammarParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(JavaTemplateGrammarParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaTemplateGrammarParser#targetcode}.
	 * @param ctx the parse tree
	 */
	void enterTargetcode(JavaTemplateGrammarParser.TargetcodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaTemplateGrammarParser#targetcode}.
	 * @param ctx the parse tree
	 */
	void exitTargetcode(JavaTemplateGrammarParser.TargetcodeContext ctx);
}