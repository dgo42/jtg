package org.edgo.jtg.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedAction;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.edgo.jtg.basics.TemplateException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ProjectLoader {

	public static Object LoadProject(String projectFullPath, String sourceOutDir, String schemaPackage, ClassLoader classLoader) throws TemplateException {
		try {
			// create a JAXBContext capable of handling classes generated into
			// the primer.po package
			JAXBContext jc = JAXBContext.newInstance(schemaPackage, classLoader);

			// create SAX parser factory 
			SAXParserFactory spf = SAXParserFactory.newInstance();

			// set xi:include aware
			spf.setXIncludeAware(true);
			// set namespace aware
			spf.setNamespaceAware(true);
			// enable validation
			spf.setValidating(true); // Not required for JAXB/XInclude

			String projectPath = Paths.get(projectFullPath).getParent().toString();

			XMLReader xr = (XMLReader) spf.newSAXParser().getXMLReader();
			
			// implement entity resolver for include
			xr.setEntityResolver(new EntityResolver() {

				@Override
				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					String userDir = "";
					try {
						userDir = getSystemProperty("user.dir");
					} catch (SecurityException se) {
					}
					Path systemIdPath = Paths.get("");
					try {
						systemIdPath = Paths.get(new URI(systemId));
					} catch (URISyntaxException e) {
					}
					if (systemIdPath.startsWith(userDir)) {
						String systemIdFile = systemIdPath.toString().substring(userDir.length() + 1);
						systemIdPath = Paths.get(projectPath, systemIdFile);
					}
					if (!systemIdPath.startsWith(projectPath)) {
						String systemIdFile = systemIdPath.getFileName().toString();
						systemIdPath = Paths.get(projectPath, systemIdFile);
					}
					InputSource source = new InputSource(new FileInputStream(systemIdPath.toFile()));
					source.setSystemId(systemIdPath.toUri().toString());
					return source;
				}
			});
			SAXSource source = new SAXSource(xr, new InputSource(new FileInputStream(projectFullPath)));

			// create an Unmarshaller
			Unmarshaller u = jc.createUnmarshaller();
			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.
			Object result = u.unmarshal(source);
			if (JAXBElement.class.isInstance(result)) {
				JAXBElement<?> data = (JAXBElement<?>) result;
				return data.getValue();
			}

			return result;
		} catch (JAXBException ex) {
			throw new TemplateException(ex.getMessage(), ex, projectFullPath);
		} catch (FileNotFoundException ex) {
			throw new TemplateException(ex.getMessage(), ex, projectFullPath);
		} catch (SAXException ex) {
			throw new TemplateException(ex.getMessage(), ex, projectFullPath);
		} catch (ParserConfigurationException ex) {
			throw new TemplateException(ex.getMessage(), ex, projectFullPath);
		}
	}

	public static String getSystemProperty(final String propName) {
		return AccessController.doPrivileged(new PrivilegedAction<String>() {
			public String run() {
				return System.getProperty(propName);
			}
		});
	}
}
