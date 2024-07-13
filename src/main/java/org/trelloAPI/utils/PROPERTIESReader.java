package org.trelloAPI.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PROPERTIESReader {
    public static Properties getConfigs(String configFile) throws IOException {
        File src = new File(configFile);
        FileInputStream fis = new FileInputStream(src);
        Properties prop = new Properties();
        prop.load(fis);
        return prop;
    }
}
