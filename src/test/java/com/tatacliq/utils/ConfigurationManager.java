package com.tatacliq.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    static Properties properties;

    public static void initializeConfigReader(){
        properties = new Properties();

        try {
            properties.load(new FileInputStream("src/test/resources/configuration/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getConfigValues(String key){
        return properties.getProperty(key);
    }

    public static void setConfigValue(String key, String value) {
        properties.setProperty(key, value);
    }

    public static void loadProperties(String data) {
        try (InputStream input = new FileInputStream("src/test/resources/configuration/config.properties")) {
            properties.store(input);
        } catch (IOException e) {
            System.err.println("Warning: Unable to load config.properties. A new one will be created if needed.");
        }
    }

    public static byte[] attachScreenShot(WebDriver driver){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }
}
