package com.liuchao.object;

import java.util.HashMap;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.liuchao.utils.ScreenShot;
//import com.liuchao.utils.LogUtil;
import com.liuchao.utils.UIExecutorImpl;
import com.liuchao.utils.XMLUtil;

/**
 * 基础页面类
 * 
 * @author liuc
 */
public class BasePage extends UIExecutorImpl {
	private WebDriver driver;
	private String pageName;// 页面名称
	private String xmlPath;// 页面元素配置文件路径
	private HashMap<String, Locator> locatorMap;//存储页面元素信息
//	public LogUtil log;
	private Logger logger = LogManager.getLogger(BasePage.class);

	public BasePage(WebDriver driver, String pageName) throws Exception {
		super(driver);
		this.driver = driver;
		this.pageName = pageName;
		// 获取page.xml路径，page.xml在同级目录
		xmlPath = this.getClass().getResource("").getPath() + "page.xml";
		locatorMap = XMLUtil.readXMLDocument(xmlPath, pageName);
	}

	public void click(String locatorName) {
		super.click(getLocator(locatorName));
	}

	public void sendKey(String locatorName, String value) {
		super.sendKey(getLocator(locatorName), value);
	}

	public String getText(String locatorName) {
		return super.getText(getLocator(locatorName));
	}

	public WebElement getElement(String locatorName) {
		return super.getElement(getLocator(locatorName));
	}

	public boolean isElementDisplayed(String locatorName) {
		return super.isElementDisplayed(getLocator(locatorName));
	}

	public void switchWindow(String title) {
		super.switchWindow(title);
	}

	public void switchFrame(String locatorName) {
		super.switchFrame(getLocator(locatorName));
	}

	/**
	 * 根据locatorName返回对应的locator
	 * 
	 * @author liuc
	 */
	public Locator getLocator(String locatorName) {
		Locator locator = null;
		if (locatorMap != null) {
			locator = locatorMap.get(locatorName);
		}
		return locator;
	}
}
