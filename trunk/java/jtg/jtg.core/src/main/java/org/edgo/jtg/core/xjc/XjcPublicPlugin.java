package org.edgo.jtg.core.xjc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JAnnotationValue;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JStringLiteral;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.addon.code_injector.Const;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;

public class XjcPublicPlugin extends Plugin {
	@Override
	public String getOptionName() {
		return "Xpm";
	}

	@Override
	public List<String> getCustomizationURIs() {
		return Collections.singletonList(Const.NS);
	}

	@Override
	public boolean isCustomizationTagName(String nsUri, String localName) {
		return nsUri.equals(Const.NS) && localName.equals("code");
	}

	@Override
	public String getUsage() {
		return "  -Xpm    : Change members visibility to public";
	}

	private String capitalize(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}

	@Override
	public boolean run(Outline model, Options opt, ErrorHandler errorHandler) throws SAXException {
		JCodeModel md = model.getCodeModel();
		for (ClassOutline co : model.getClasses()) {
			JDefinedClass jdc = co.implClass;
			// avoid concurrent modification by copying the fields in a new list
			List<JMethod> methods = new ArrayList<JMethod>(jdc.methods());
			for (JMethod method : methods) {
				if (method.name().startsWith("set") || method.name().startsWith("get") || method.name().startsWith("is")) {
					jdc.methods().remove(method);
				}
			}
			List<JFieldVar> fields = new ArrayList<JFieldVar>(jdc.fields().values());
			for (JFieldVar field : fields) {
				// never do something with serialVersionUID if it exists.
				if (!field.name().equalsIgnoreCase("serialVersionuid")) {
					// only try to change members that are not private
					if (field.mods().getValue() != JMod.PUBLIC) {
						Collection<JAnnotationUse> annotations = field.annotations();
						jdc.removeField(field);
						JFieldVar fld = null;
						if ("Boolean".equals(field.type().name())) {
							fld = jdc.field(JMod.PUBLIC, md.BOOLEAN, field.name());
						} else {
							fld = jdc.field(JMod.PUBLIC, field.type(), field.name());
						}
						for (JAnnotationUse annot : annotations) {
							try {
								JAnnotationUse newAnnot = fld.annotate(annot.getAnnotationClass());
								Map<String, JAnnotationValue> members = annot.getAnnotationMembers();
								for (String name : members.keySet()) {
									JAnnotationValue value = members.get(name);
									Class<?> clazz = value.getClass();
									if (clazz.getName().equals("com.sun.codemodel.JAnnotationStringValue")) {
										try {
											Field valueField = clazz.getDeclaredField("value");
											valueField.setAccessible(true);
											JExpression expr = (JExpression) valueField.get(value);
											if (expr instanceof JStringLiteral) {
												newAnnot.param(name, ((JStringLiteral) expr).str);
											} else if (expr.getClass().getName().equals("com.sun.codemodel.JAtom")) {
												clazz = expr.getClass();
												valueField = clazz.getDeclaredField("what");
												valueField.setAccessible(true);
												String atom = (String) valueField.get(expr);
												if ("true".equals(atom.toLowerCase()) || "false".equals(atom.toLowerCase())) {
													newAnnot.param(name, Boolean.valueOf(atom));
												} else {
													throw new SAXException("Can't get attribute atom value ");
												}
											} else {
												throw new SAXException("Can't get attribute value ");
											}
										} catch (Exception e) {
											throw new SAXException("Can't get attribute", e);
										}
									} else {
										throw new SAXException("Unknown attribut value " + clazz.getName());
									}
								}
							} catch (Exception e) {
								// there is no parameters
								continue;
							}
						}
					}
				}
			}
			for (JFieldVar field : fields) {
				JMethod setter = jdc.method(JMod.PUBLIC, md.VOID, "set" + capitalize(field.name()));
				setter.param(JMod.NONE, field.type(), field.name());
				setter.body().assign(JExpr._this().ref(field.name()), JExpr.ref(field.name()));
			}
		}
		return true;
	}

}
