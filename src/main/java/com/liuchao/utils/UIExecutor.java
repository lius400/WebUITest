package com.liuchao.utils;

import org.openqa.selenium.By;
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
	//下拉选择框,通过value 属性值去选择
	public void selectByValue(Locator locator,String value);
	//单选框选择
	public void selectRadioButton(Locator locator);
	//多选框选择
	public void selectCheckbox(Locator locator);
	//弹出框选择确定
	public void acceptAlert(Boolean isAccept);
	//上传文件
	public void UploadFile(Locator locator,String fileName);
	//获取元素
	public WebElement getElement(Locator locator) throws Exception;
	//判断元素是否显示
	public boolean isElementDisplayed(final Locator locator, int timeOut);
	//切换页面
	public void switchWindow(String title);
	//切换frame
	public void switchFrame(Locator locator);
	//跳出frame
	public void jump_outFrame();
	//智能等待
	public void waitElement(Locator locator);
	//定位方式自动匹配
	public By byType(Locator locator);
}
