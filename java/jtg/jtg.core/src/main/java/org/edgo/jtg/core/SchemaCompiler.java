package org.edgo.jtg.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.edgo.jtg.basics.LogMessage;
import org.edgo.jtg.basics.LogMessage.MessageType;
import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.xjc.XjcListMapMapperPlugin;
import org.edgo.jtg.core.xjc.XjcMapperPlugin;
import org.edgo.jtg.core.xjc.XjcPublicPlugin;
import org.jvnet.jaxb2_commons.plugin.annotate.AnnotatePlugin;
import org.xml.sax.SAXParseException;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.writer.FileCodeWriter;
import com.sun.tools.xjc.ErrorReceiver;
import com.sun.tools.xjc.Language;
import com.sun.tools.xjc.ModelLoader;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.Model;

public class SchemaCompiler {

	private String schemaPackage;

	public SchemaCompiler(String schemaPackage) {
		this.schemaPackage = schemaPackage;
	}

	public void compile(String schemaDir, String schema, String sourceOutDir, JarCompiler jarCompiler)
			throws TemplateException {
		FileCodeWriter writer = null;
		try {
			Options options = new Options();
			options.defaultPackage = schemaPackage;
			File sourceOutFile = new File(sourceOutDir);
			if (!sourceOutFile.exists()) {
				sourceOutFile.mkdirs();
			}
			File schemaSourceFile = new File(GeneratorUtils.Package2OutFullPath(sourceOutDir, schemaPackage));
			File schemaModels[] = new File[] {};
			File schemaFile = new File(schemaDir, schema);
			if (jarCompiler.cacheExists() && schemaSourceFile.exists()) {
				schemaModels = Files.walk(schemaSourceFile.toPath())
						.filter(path -> path.toFile().isFile() && path.toString().toLowerCase().endsWith(".java"))
						.map(path -> path.toFile()).toArray(File[]::new);
				if (schemaModels.length > 0) {
					File firstModel = schemaModels[0];
					if (firstModel.lastModified() > schemaFile.lastModified()) {
						// generated schema source files are newer as schema file - skip generation
						return;
					}
				}
			}
			options.targetDir = sourceOutFile;
			options.addGrammar(schemaFile);
			options.setSchemaLanguage(Language.XMLSCHEMA);
			options.strictCheck = false;
			options.compatibilityMode = Options.EXTENSION;
			AnnotatePlugin plugin = new AnnotatePlugin();
			plugin.parseArgument(null, new String[] { "-Xannotate" }, 0);
			options.pluginURIs.addAll(plugin.getCustomizationURIs());
			options.activePlugins.add(plugin);
			options.activePlugins.add(new XjcPublicPlugin());

			ErrorReceiverImpl errorReceiver = new ErrorReceiverImpl();

			Model model = ModelLoader.load(options, new JCodeModel(), errorReceiver);

			if (model == null) {
				if (errorReceiver.getMessages().size() > 0) {
					throw new TemplateException("Schema", errorReceiver.getMessages());
				} else {
					String message = "unable to parse the schema. Error messages should have been provided";
					throw new TemplateException(message, new Exception(message), schema);
				}
			}

			XjcMapperPlugin hashMapPlugn = new XjcListMapMapperPlugin(model, schema, schemaPackage, sourceOutDir);
			hashMapPlugn.process();

			if (model.generateCode(options, errorReceiver) == null) {
				String message = "failed to compile a schema";
				throw new TemplateException(message, errorReceiver.getMessages());
			}

			writer = new FileCodeWriter(options.targetDir, false);
			model.codeModel.build(writer);
		} catch (TemplateException e) {
			throw e;
		} catch (IOException e) {
			throw new TemplateException("unable to write files: " + e.getMessage(), e, schema);
		} catch (Exception ex) {
			throw new TemplateException(ex.getMessage(), ex, schema);
		} catch (Throwable ex) {
			throw new TemplateException(ex.getMessage(), ex, schema);
		} finally {
			if (null != writer)
				try {
					writer.close();
				} catch (IOException e) {
					throw new TemplateException("unable to close files: " + e.getMessage(), e, schema);
				}

		}
	}

	class ErrorReceiverImpl extends ErrorReceiver {

		private List<LogMessage> messages;

		public ErrorReceiverImpl() {
			messages = new ArrayList<LogMessage>();
		}

		public void warning(SAXParseException e) {
			messages.add(new LogMessage(MessageType.WARNING, e.getSystemId(), e.getLineNumber(), e.getMessage()));
		}

		public void error(SAXParseException e) {
			messages.add(new LogMessage(MessageType.ERROR, e.getSystemId(), e.getLineNumber(), e.getMessage()));
		}

		public void fatalError(SAXParseException e) {
			messages.add(new LogMessage(MessageType.FATAL_ERROR, e.getSystemId(), e.getLineNumber(), e.getMessage()));
		}

		public void info(SAXParseException e) {
			messages.add(new LogMessage(MessageType.INFO, e.getSystemId(), e.getLineNumber(), e.getMessage()));
		}

		public List<LogMessage> getMessages() {
			return messages;
		}

	}

}
