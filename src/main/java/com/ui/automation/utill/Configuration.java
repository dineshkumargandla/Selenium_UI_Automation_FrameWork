package com.ui.automation.utill;

import java.io.*;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeSuite;

public class Configuration {


	private static final String filepath = "resources/config.properties";

	public static WebDriver driver;
	static InputStream input;
	static Properties properties =  loadProperties(filepath);

	private static Properties loadProperties(String filepath) {
		try {
			input = new FileInputStream(filepath);
			properties = new Properties();
			properties.load(input);
			return properties;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	public static String getScreenOutDir() {
		if (properties.getProperty("screen.outdir").equals("")) {
			StringBuilder sb = new StringBuilder(System.getProperty("user.dir"));
			sb.setLength(sb.indexOf(String.valueOf(File.separatorChar)) + 1);
			sb.append("screens");
			Log.info("Output directory is not set in config.properties and set to " + sb.toString());
			return sb.toString();
		} else {
			return properties.getProperty("screen.outdir");
		}
	}

	public static String getDataDir() {
		if (null == properties.getProperty("datafile.dir")) {
			Log.error("Input Data directory is not set in config.properties. ");
			return "data";
		} else {
			return properties.getProperty("datafile.dir");
		}
	}
	
	public static String getUserName() {
		if (null == properties.getProperty("emailid")) {
			Log.error("Input Email is not set in config.properties. ");
			return "data";
		} else {
			return properties.getProperty("emailid");
		}
	}

	public static String getAppURL() {
		if (null == properties.getProperty("app.url")) {
			Log.error("app.url is not set and it must be set.");
			return "";
		} else {
			return properties.getProperty("app.url");
		}
	}

	public static WebDriver getDriver() {
		String browser = properties.getProperty("test.browser");

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", properties.getProperty("driver.path"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			driver.get(properties.getProperty("app.url"));
		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.firefox.marionette", properties.getProperty("driver.path"));
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", properties.getProperty("driver.path"));
			EdgeOptions options = new EdgeOptions();
			driver = new EdgeDriver();
		} else if (browser.isEmpty()) {
			Log.error("Enter the browser need to use for automation");
		}

		return driver;
	}

	@BeforeSuite
	public void createRandomUserName() throws IOException {

		int length = 5;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers).toLowerCase();
		FileInputStream in = new FileInputStream("resources/config.properties");
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream("resources/config.properties");
		props.setProperty("emailid", generatedString + "@gmail.com");
		props.store(out, null);
		out.close();
	}
}
