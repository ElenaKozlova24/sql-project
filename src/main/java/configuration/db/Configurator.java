package configuration.db;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configurator {

    private static final String DB_PROPERTIES_FILE = "/resources/dbproperties";


    public Properties getConfigurator() throws IDException, IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("/Users/kirill/IdeaProjects/sql3/src/resources/dbproperties");
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }
}
