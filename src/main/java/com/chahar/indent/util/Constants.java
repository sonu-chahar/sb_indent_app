package com.chahar.indent.util;

import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
	public static final Logger LOGGER = LogManager.getLogger(Constants.class);
	public static final String APPLICTION_PROFILE_PROP_FILE_NAME = getPropFileName("applicationPropFileName");
	public static final String APPLICTION_BASE_PROP_FILE_NAME = "/application.properties";

	public static final Random RANDOM_NUMBER = new Random();
	public static final String ALPHANUMBERIC_NUMBER = "0123456789";

	private Constants() {
	}
	
	public static String pathString(HttpServletRequest request) {
		return "http://" + request.getLocalName() + ":" + request.getLocalPort() + request.getContextPath();
	}

	public static String pathString(String property) {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream is = loader.getResourceAsStream(APPLICTION_PROFILE_PROP_FILE_NAME);) {
			prop.load(is);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
		}
		return prop.getProperty(property);
	}

	private static String getPropFileName(String property) {
		Properties properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream is = loader.getResourceAsStream(APPLICTION_BASE_PROP_FILE_NAME);) {
			properties.load(is);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
		}
		return properties.getProperty(property);
	}
}
