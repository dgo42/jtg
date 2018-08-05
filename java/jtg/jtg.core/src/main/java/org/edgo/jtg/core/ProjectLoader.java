package org.edgo.jtg.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.edgo.jtg.basics.TemplateException;

public class ProjectLoader {

    public static Object LoadProject(String projectFullPath, String sourceOutDir, String schemaPackage, ClassLoader classLoader)
            throws TemplateException {
        try {
            // create a JAXBContext capable of handling classes generated into
            // the primer.po package
            JAXBContext jc = JAXBContext.newInstance(schemaPackage, classLoader);
            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            // unmarshal a po instance document into a tree of Java content
            // objects composed of classes from the primer.po package.
            Object result = u.unmarshal(new FileInputStream(projectFullPath));
            if (JAXBElement.class.isInstance(result)) {
            	JAXBElement<?> data = (JAXBElement<?>)result;
            	return data.getValue();
            }
            
            return result;
        } catch (JAXBException ex) {
            throw new TemplateException(ex.getMessage(), ex, projectFullPath);
        } catch (FileNotFoundException ex) {
            throw new TemplateException(ex.getMessage(), ex, projectFullPath);
        }
    }

}
