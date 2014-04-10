package org.edgo.jtg.core.xjc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.edgo.jtg.basics.TemplateException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CElementPropertyInfo;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.CTypeInfo;
import com.sun.tools.xjc.model.CTypeRef;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIDeclaration;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIXPluginCustomization;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BindInfo;
import com.sun.xml.xsom.XSAnnotation;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSSchema;

public class XjcListMapMapperPlugin implements XjcMapperPlugin {

	private final static String	JTG_HASHMAP			= "hashMap";

	private final static String	JTG_NAMESPACE		= "http://edgo.org/jtg/ext";

	private final static QName	JTG_BINDING_QNAME	= QName.valueOf("{" + JTG_NAMESPACE + "}binding");

	private Model				model;

	private String				schema;

	private String				schemaPackage;

	private String				sourceOutDir;

	public XjcListMapMapperPlugin(Model model, String schema, String schemaPackage, String sourceOutDir) {
		this.model = model;
		this.schema = schema;
		this.schemaPackage = schemaPackage;
		this.sourceOutDir = sourceOutDir;
	}

	public void process() throws TemplateException {
		Collection<XSSchema> schematas = model.schemaComponent.getSchemas();
		XSSchema parsedSchema = null;
		for (XSSchema xsschema : schematas) {
			if (xsschema.getLocator().getSystemId().endsWith(schema)) {
				parsedSchema = xsschema;
				break;
			}
		}

		if (parsedSchema != null) {
			XSAnnotation annotation = parsedSchema.getAnnotation();
			if (annotation == null) {
				// there is no annotations - nothing to do
				return;
			}
			Object bi = annotation.getAnnotation();
			if (bi != null && bi instanceof BindInfo) {
				BindInfo bindInfo = (BindInfo) bi;
				for (BIDeclaration decl : bindInfo.getDecls()) {
					if (decl.getName().equals(JTG_BINDING_QNAME) && decl instanceof BIXPluginCustomization) {
						processBinding(decl);
					}
				}
			}
		} else {
			throw new TemplateException("Can't find parsed schema", null, schema, -1);
		}

	}

	private void processBinding(BIDeclaration decl) throws TemplateException {
		BIXPluginCustomization cust = (BIXPluginCustomization) decl;
		String fileName = cust.getLocation().getSystemId();
		int lineNumber = cust.getLocation().getLineNumber();
		String type = cust.element.getAttribute("type");
		String field = cust.element.getAttribute("field");
		NodeList nodes = cust.element.getChildNodes();
		for (int i = 0, size = nodes.getLength(); i < size; i++) {
			Node node = nodes.item(i);
			if (JTG_HASHMAP.equals(node.getLocalName()) && JTG_NAMESPACE.equals(node.getNamespaceURI())) {
				processHashMapBinding(fileName, lineNumber, type, field, node);
			}
		}
	}

	private void processHashMapBinding(String fileName, int lineNumber, String type, String field, Node node)
			throws TemplateException {
		String key = null;
		String value = null;
		NamedNodeMap attrs = node.getAttributes();
		Node attr = attrs.getNamedItem("key");
		if (attr != null) {
			key = attr.getNodeValue();
		} else {
			throw new TemplateException("There is no \"key\" attribute", null, fileName, lineNumber);
		}
		attr = attrs.getNamedItem("value");
		if (attr != null) {
			value = attr.getNodeValue();
		} else {
			throw new TemplateException("There is no \"value\" attribute", null, fileName, lineNumber);
		}
		if (type != null && field != null && key != null && value != null) {
			// search for map modeller class
			Map<NClass, CClassInfo> beans = model.beans();
			String fqcn = schemaPackage + "." + type;
			CClassInfo modeller = null;
			for (Map.Entry<NClass, CClassInfo> entry : beans.entrySet()) {
				if (fqcn.equals(entry.getKey().fullName())) {
					modeller = entry.getValue();
					break;
				}
			}
			String listName = null;
			String keyType = null;
			String valueType = null;
			String listType = null;
			if (modeller != null) {
				List<CPropertyInfo> props = modeller.getProperties();
				CPropertyInfo propList = null;
				for (CPropertyInfo prop : props) {
					XSParticle p = (XSParticle) prop.getSchemaComponent();
					if (field.equals(p.getTerm().asElementDecl().getName())) {
						propList = prop;
						break;
					}
				}
				if (propList != null) {
					Collection<? extends CTypeInfo> modellerList = propList.ref();
					CClassInfo ti = (CClassInfo) modellerList.iterator().next();
					listType = ti.fullName();
 					listName = propList.getName(false);

					CElementPropertyInfo keyProp = (CElementPropertyInfo) ti.getProperty(key);
					if (keyProp != null) {
						CTypeRef typeRef = keyProp.getTypes().get(0);
						keyType = typeRef.getTarget().getType().fullName();
					} else {
						throw new TemplateException("Can't find field \"" + key + "\" in the the class \"" + field + "\"",
								null, fileName, lineNumber);
					}
					CElementPropertyInfo valueProp = (CElementPropertyInfo) ti.getProperty(value);
					if (valueProp != null) {
						CTypeRef typeRef = valueProp.getTypes().get(0);
						valueType = typeRef.getTarget().getType().fullName();
					} else {
						throw new TemplateException("Can't find field \"" + value + "\" in the the class \"" + field + "\"",
								null, fileName, lineNumber);
					}
					generateAdapter(fileName, lineNumber, type, field, listName, listType, key, value, keyType, valueType);
				} else {
					throw new TemplateException("Can't find list field \"" + field + "\" in the class \"" + type + "\" ",
							null, fileName, lineNumber);
				}
			} else {
				throw new TemplateException("Can't find class \"" + type + "\"", null, fileName, lineNumber);
			}
		}
	}

	private void generateAdapter(String fileName, int lineNumber, String type, String field, String listName,
			String listType, String key, String value, String keyType, String valueType) throws TemplateException {
		PrintWriter pw = null;
		File schemeOutputDir = new File(sourceOutDir, schemaPackage.replace(".", File.separator));
		try {
			pw = new PrintWriter(new FileOutputStream(new File(schemeOutputDir, type + "Map.java")));
			pw.println("package org.edgo.jtg.schema;");
			pw.println();
			pw.println("import java.util.HashMap;");
			pw.println();
			pw.println("import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;");
			pw.println();
			pw.println("@XmlJavaTypeAdapter(" + type + "Adapter.class)");
			pw.println("public class " + type + "Map<K, T> extends HashMap<K, T>{");
			pw.println("}");
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pw != null) {
				try {
					pw.close();
				} catch (Exception e) {
					// nothing to do
				}
			}
		}
		try {
			// @formatter:off
			pw = new PrintWriter(new FileOutputStream(new File(schemeOutputDir, type + "Adapter.java")));

			pw.println("package org.edgo.jtg.schema;");
			pw.println();
			pw.println("import java.util.Iterator;");
			pw.println("import java.util.Map;");
			pw.println("import java.util.Set;");
			pw.println();
			pw.println("import javax.xml.bind.annotation.adapters.XmlAdapter;");
			pw.println();
			pw.println("public class " +  type + "Adapter extends XmlAdapter<" +  type + ", " +  type + "Map<" + keyType + ", " + valueType + ">> {");
			pw.println();
			pw.println("	@Override");
			pw.println("	public " +  type + " marshal(" +  type + "Map<" + keyType + ", " + valueType + "> map) throws Exception {");
			pw.println("		" +  type + " list = new " +  type + "();");
			pw.println();
			pw.println("		Set<Map.Entry<" + keyType + ", " + valueType + ">> entries = map.entrySet();");
			pw.println("		Iterator<Map.Entry<" + keyType + ", " + valueType + ">> i = entries.iterator();");
			pw.println("		while (i.hasNext()) {");
			pw.println("			Map.Entry<" + keyType + ", " + valueType + "> s = i.next();");
			pw.println("			" +  listType + " e = new " +  listType + "();");
			pw.println("			e." + key + " = s.getKey();");
			pw.println("			e." + value + " = s.getValue();");
			pw.println("			list." + listName + ".add(e);");
			pw.println("		}");
			pw.println("		return list;");
			pw.println("	}");
			pw.println();
			pw.println("	@Override");
			pw.println("	public " +  type + "Map<" + keyType + ", " + valueType + "> unmarshal(" +  type + " list) throws Exception {");
			pw.println("		" +  type + "Map<" + keyType + ", " + valueType + "> map = new " +  type + "Map<" + keyType + ", " + valueType + ">();");
			pw.println("		for (" +  listType + " entry : list." + listName + ") {");
			pw.println("			map.put(entry." + key + ", entry." + value + ");");
			pw.println("		}");
			pw.println("		return map;");
			pw.println("	}");
			pw.println();
			pw.println("}");
			pw.close();
			// @formatter:on

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pw != null) {
				try {
					pw.close();
				} catch (Exception e) {
					// nothing to do
				}
			}
		}
	}

}
