package org.edgo.jtg.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.edgo.jtg.core.config.JtgConfiguration;
import org.edgo.jtg.core.config.Configuration;



public class JtgSettings {

    public static JtgConfiguration loadConfig() throws ConfigurationException {
        String configFile = new File(".").getAbsolutePath() + File.separator + "jtg.config";
        return loadConfig(configFile);
    }

    public static JtgConfiguration loadConfig(String configFile) throws ConfigurationException {
        try {
            // create a JAXBContext capable of handling classes generated into
            // the primer.po package
            JAXBContext jc = JAXBContext.newInstance(Configuration.class);
            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            // unmarshal a po instance document into a tree of Java content
            // objects composed of classes from the primer.po package.
            Configuration config = (Configuration) u.unmarshal(new FileInputStream(configFile));
            return config.getJtgConfiguration();
        } catch (JAXBException e) {
            throw new ConfigurationException(e);
        } catch (FileNotFoundException e) {
            throw new ConfigurationException(e);
        }
    }

}
