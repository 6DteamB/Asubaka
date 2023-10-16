package model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final String CONFIG_FILE = "/config.properties";

    public static String getApiKey() {
        String apiKey = null;

        try (InputStream input = ConfigLoader.class.getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + CONFIG_FILE);
                return null;
            }

            Properties prop = new Properties();
            prop.load(input);

            apiKey = prop.getProperty("catapi.apikey");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return apiKey;
    }
}
