package com.liuchao.utils;

import org.openqa.selenium.WebElement;

import com.liuchao.object.Locator;

/**
 * webDriver常见的API
 * 
 * @author liuc
 */
public interface UIExecutor {
    //点击
	public void click(Locator locator);
	//输入文本
	public void sendKey(Locator locator,String value);
	//获取元素文本
	public String getText(Locator locator);
	//获取元素
	public WebElement getElement(Locator locator) throws Exception;
	//判断元素是否显示
	public boolean isElementDisplayed(Locator locator);
	//切换页面
	public void switchWindow(String title);
	//切换frame
	public void switchFrame(Locator locator);
	//跳出frame
	public void jump_outFrame();
	//智能等待
	public void waitElement(Locator locator);
}
