package com.liuchao.utils;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

@Slf4j
public class BrowserUtil {
	private static WebDriver driver;

	/**
	 * 启动ie浏览器
	 * 
	 * @param browserDriverUrl
	 *            浏览器驱动url
	 * @param sec
	 *            所有页面操作的等待超时时长，此处为隐式等待，超时后找不到元素则抛出异常NoSuchElementException
	 * @author liuc
	 */
	public static WebDriver ie(String browserDriverUrl, long sec) {
		log.info("启动IE浏览器");
		System.setProperty("webdriver.ie.driver", browserDriverUrl);
		// 关闭IE保护模式
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		InternetExplorerOptions Options = new InternetExplorerOptions(ieCapabilities);
		driver = new InternetExplorerDriver(Options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * 启动chrome浏览器
	 * 
	 * @param browserDriverUrl
	 *            浏览器驱动url
	 * @param sec
	 *            所有页面操作的等待超时时长
	 * @author liuc
	 */
	public static WebDriver chrome(String browserDriverUrl, long sec) {
		log.info("启动chrome浏览器");
		System.setProperty("webdriver.chrome.driver", browserDriverUrl);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * 启动fireFox浏览器
	 * 
	 * @param browserDriverUrl
	 *            浏览器驱动url
	 * @param sec
	 *            所有页面操作的等待超时时长
	 * @author liuc
	 */
	public static WebDriver fireFox(String browserDriverUrl, long sec) {
		log.info("启动fireFox浏览器");
		System.setProperty("webdriver.firefox.bin", browserDriverUrl);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * 启动htmlUnitDriver,不会打开实际游览器，运行速度快 但当页面有负责js时，会定位不到元素，不建议使用
	 * 
	 * @author liuc
	 */
	public static WebDriver htmlUnitDriver(long sec) {
		log.info("启动htmlUnitDriver");
//		driver = new HtmlUnitDriver();
		HtmlUnitDriver driver = new HtmlUnitDriver();
//		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		driver.setJavascriptEnabled(true);//允许JS操作
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		return driver;
	}
}
