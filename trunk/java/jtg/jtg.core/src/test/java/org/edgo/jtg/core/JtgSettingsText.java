package org.edgo.jtg.core;

import junit.framework.TestCase;

import org.edgo.jtg.core.JtgSettings;
import org.edgo.jtg.core.ConfigurationException;

public class JtgSettingsText extends TestCase {

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public void testLoadConfig() {
        try {
            JtgSettings.loadConfig();
        } catch (ConfigurationException e) {
            fail();
        }
    }

}
