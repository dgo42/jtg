package org.edgo.jtg.core;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Comparator;

import org.edgo.jtg.basics.TemplateException;

import junit.framework.TestCase;

public class GeneratorTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testNewGenerator() {
		try {
			File base = new File("src\\test\\scripts\\");
			File sub = new File(base, "javaGeneratorOutput");
			if (sub.exists()) {
				Files.walk(sub.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			}
			sub = new File(base, "javaJarOutput");
			if (sub.exists()) {
				Files.walk(sub.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			}
			sub = new File(base, "javaSourceOutput");
			if (sub.exists()) {
				Files.walk(sub.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			}

			double start = System.nanoTime();
			try {
				Generator generator = new Generator(new String[] {}, "src\\test\\scripts\\jtg_jar.config");
				generator.generate(this.getClass().getClassLoader());
			} finally {
				double end = System.nanoTime();
				double elapsed = (end - start) / 1000000;
				String message = "=====> Compilation time " + new DecimalFormat("#.###").format(elapsed) + "ms";
				System.out.println(message);
			}
			start = System.nanoTime();
			int i = 0;
			try {
				Generator generator = null;
				for (i = 0; i < 10; i++) {
					generator = new Generator(new String[] {}, "src\\test\\scripts\\jtg.config");
					generator.generate(this.getClass().getClassLoader());
				}
				generator = null;
			} finally {
				double end = System.nanoTime();
				double elapsed = (end - start) / 1000000 / i;
				String message = "=====> Avarage generation time " + new DecimalFormat("#.###").format(elapsed) + "ms";
				System.out.println(message);
			}
		} catch (TemplateException ex) {
			if (ex.getCause() instanceof TemplateException) {
				System.out.println(((TemplateException) ex.getCause()).getTemplateMessage());
			} else {
				System.out.println(ex.getTemplateMessage());
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/*
	public void testDeSerialisaton() {
		try {
			// create a JAXBContext capable of handling classes generated into
			// the primer.po package
			JAXBContext jc = JAXBContext.newInstance("org.edgo.jtg.schema", this.getClass().getClassLoader());
			// create an Unmarshaller
			Unmarshaller u = jc.createUnmarshaller();
			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.
			XMLInputFactory factory = XMLInputFactory.newInstance();
			FileReader reader = new FileReader(
					"C:\\home\\runtime-EclipseApplication\\jtgTest\\src\\main\\schema\\project.xml");
			XMLStreamReader xmlReader = factory.createXMLStreamReader(reader);
			JAXBElement<?> data = (JAXBElement<?>) u.unmarshal(xmlReader, Project.class);
			Project project = (Project) data.getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void testSerialisaton() {
		try {
			Project project = new Project();
			project.setMainDescription(new MainDescriptionType());
			// create a JAXBContext capable of handling classes generated into
			// the primer.po package
			JAXBContext jc = JAXBContext.newInstance("org.edgo.jtg.schema", this.getClass().getClassLoader());
			// create an Unmarshaller
			Marshaller m = jc.createMarshaller();
			// marshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.
			m.marshal(project, new FileOutputStream(
					"C:\\home\\runtime-EclipseApplication\\jtgTest\\src\\main\\schema\\project_out.xml"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	*/
}
