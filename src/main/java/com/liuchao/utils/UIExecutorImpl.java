package com.liuchao.utils;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.liuchao.object.Locator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * UIExecutor接口实现类
 * 
 * @author Liuc
 */
@Slf4j
public class UIExecutorImpl implements UIExecutor {
	private WebDriver driver;
//	public Logger logger = LogManager.getLogger(UIExecutorImpl.class);

	public UIExecutorImpl(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 点击元素
	 * 
	 * @author Liuc
	 * @param locator
	 */
	public void click(Locator locator) {
		WebElement element = getElement(locator);
		element.click();
	}

	/**
	 * 输入文本
	 * 
	 * @author Liuc
	 */
	public void sendKey(Locator locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public String getText(Locator locator) {
		WebElement element = getElement(locator);
		return element.getText();
	}

	public void selectRadioButton(Locator locator) {
		WebElement element = getElement(locator);
		element.click();
	}

	public void selectCheckbox(Locator locator) {
		WebElement element = getElement(locator);
		element.click();
	}

	public void acceptAlert(Boolean isAccept) {
		Alert alert = driver.switchTo().alert();
		if(isAccept){
			alert.accept();
		}
		else{
			alert.dismiss();
		}

	}

	public void UploadFile(Locator locator, String fileName) {
		WebElement FileUpload = getElement(locator);
		String filePath = ClassLoader.getSystemResource("")+"/uploadFile/"+fileName;
		FileUpload.sendKeys(filePath);
	}

	public void selectByValue(Locator locator, String value) {
		// 找到下拉选择框的元素
		Select select = new Select(getElement(locator));
		// 选择对应的选择项
		select.selectByValue(value);
	}

	/**
	 * 获取元素
	 * 
	 * @author Liuc
	 * 
	 */
	public WebElement getElement(Locator locator) {
		WebElement element = null;
		String address = locator.getAddress();
//		long tinkTime = locator.getWaitSec() * 1000;
//		try {
//			// 思考时间，等待元素加载
//			Thread.sleep(tinkTime);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		switch (locator.getByType()) {
		case xpath:
			element = driver.findElement(By.xpath(address));
			break;
		case id:
			element = driver.findElement(By.id(address));
			break;
		case className:
			element = driver.findElement(By.className(address));
			break;
		case linkText:
			element = driver.findElement(By.linkText(address));
			break;
		case css:
			element = driver.findElement(By.cssSelector(address));
			break;
		default:
			break;
		}
		return element;
	}

	/**
	 * 根据等待时间判断元素是否显式显示
	 * 
	 * @author Liuc
	 */
	public boolean isElementDisplayed(final Locator locator, int timeOut) {
//		boolean flag = false;
//		WebElement element = getElement(locator);
//		flag = element.isDisplayed();
//		return flag;

		WebDriverWait wait = new WebDriverWait(driver,10,1);
		return wait.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver driver) {
				WebElement element = getElement(locator);
				return element.isDisplayed();
			}
		});
	}

	/**
	 * 切换窗口
	 * 
	 * @author Liuc
	 */
	public void switchWindow(String title) {
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (handle.equals(driver.getWindowHandle())) {
				continue;
			} else {
				driver.switchTo().window(handle);
				if (title.contains(driver.getTitle())) {
					break;
				} else {
					continue;
				}
			}
		}
	}

	/**
	 * 切换frame
	 * 
	 * @author Liuc
	 */
	public void switchFrame(Locator locator) {
		driver.switchTo().frame(locator.getAddress());
	}

	/**
	 * 跳出frame
	 *
	 * @author Liuc
	 */
	public void jump_outFrame(){
		driver.switchTo().defaultContent();
	}

	/**
	 * 智能等待，超过该时长抛出异常
	 * 
	 * @author Liuc
	 */
	public void waitElement(Locator locator) {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, locator.getWaitSec()).until(ExpectedConditions.presenceOfElementLocated(byType(locator)));

	}

	public By byType(Locator locator) {
		switch (locator.getByType()){
			case id:
				return By.id(locator.getAddress());
			case name:
				return By.name(locator.getAddress());
			case css:
				return By.cssSelector(locator.getAddress());
			case className:
				return By.className(locator.getAddress());
			case linkText:
				return  By.linkText(locator.getAddress());
			default:
				return By.xpath(locator.getAddress());
		}
	}
}
