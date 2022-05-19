package com.automation.interview.utill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeSuite;

public class Configaration {

	private static final String filepath = "resources/config.properties";
	private static HashMap<String, String> propertyMap;
	private static PrintStream out;
	private static String OS = System.getProperty("os.name").toLowerCase();
	protected static String baseCommand = "";
	public static WebDriver driver;

	public static String getPropValue(String name) {
		if (null == propertyMap) {
			init();
		}
		String value = propertyMap.get(name);
		if (null == value || value.equals("")) {
			Log.error(name + " is not defined in config.properties file.");
			return "";
		} else
			return value.trim();
	}

	private static HashMap<String, String> getconfigMap() {
		if (null == propertyMap) {
			init();
		}
		return propertyMap;
	}

	public static String getScreenOutDir() {
		if (getPropValue("screen.outdir").equals("")) {
			StringBuilder sb = new StringBuilder(System.getProperty("user.dir"));
			sb.setLength(sb.indexOf(String.valueOf(File.separatorChar)) + 1);
			sb.append("screens");
			Log.info("Output directory is not set in config.properties and set to " + sb.toString());
			return sb.toString();
		} else {
			return propertyMap.get("screen.outdir");
		}
	}

	public static String getDataDir() {
		if (null == propertyMap.get("datafile.dir")) {
			Log.error("Input Data directory is not set in config.properties. ");
			return "data";
		} else {
			return propertyMap.get("datafile.dir");
		}
	}
	
	public static String getUserName() {
		if (null == propertyMap.get("emailid")) {
			Log.error("Input Email is not set in config.properties. ");
			return "data";
		} else {
			return propertyMap.get("emailid");
		}
	}

	public static void init() {
		File infile = new File(filepath);
		Properties prop = new Properties();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(infile), "UTF-8"));
			prop.load(br);
		} catch (IOException e) {
			Log.error(e.toString());
		}
		propertyMap = new HashMap<String, String>((Map) prop);
	}

	public static String getAppURL() {
		if (null == getPropValue("app.url")) {
			Log.error("app.url is not set and it must be set.");
			return "";
		} else {
			return getPropValue("app.url");
		}
	}

	public static WebDriver getDriver() {
		String browser = getPropValue("test.browser");

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", propertyMap.get("driver.path"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			driver.get(propertyMap.get("app.url"));
		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.firefox.marionette", propertyMap.get("driver.path"));
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", propertyMap.get("driver.path"));
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

	public static void main(String[] args) throws FileNotFoundException {
		Configaration con = new Configaration();
		for (Map.Entry<String, String> entry : Configaration.propertyMap.entrySet()) {
			System.out.printf("Key : %s -- Value: %s %n", entry.getKey(), entry.getValue());

		}
	}
}
