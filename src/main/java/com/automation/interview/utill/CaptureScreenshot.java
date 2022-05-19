package com.automation.interview.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	
	public static void capture(String filepath) throws IOException, InterruptedException {
		capture(Configaration.getDriver(), filepath);
	}
	
	public static void capture(WebDriver driver, String filepath) {
		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileInputStream fis = new FileInputStream(screen);
			FileChannel inputChannel = fis.getChannel();
			FileOutputStream fos = new FileOutputStream(new File(filepath));
			FileChannel outputChannel = fos.getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
			inputChannel.close();
			fis.close();
			outputChannel.close();
			fos.close();
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
	}
}
