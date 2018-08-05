package org.edgo.jtg.core;

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
			Generator generator = new Generator(new String[] {}, "src\\test\\scripts\\jtg.config");
			for (int i = 0; i < 1; i++) {
				generator.generate(this.getClass().getClassLoader());
			}
			generator = null;
		} catch (TemplateException ex) {
			/*            if (ex.getCause() instanceof TemplateException) {
			                System.out.println(ex.getCause().getMessage());
			            } else {*/
			ex.printStackTrace();
			//            }
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
