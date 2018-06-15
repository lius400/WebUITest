package com.liuchao.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * 监听测试过程
 * 
 * @author liuc
 */
public class TestNGListener extends TestListenerAdapter {

	private static WebDriver driver;

	Logger logger = LogManager.getLogger(TestNGListener.class);

	public static void setDriver(WebDriver driver) {
		TestNGListener.driver = driver;
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		logger.info("Test Success");
		super.onTestSuccess(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		logger.error("Test Failure");
		super.onTestFailure(tr);
		ScreenShot screenShot = new ScreenShot(driver);  
		//获取当前project目录
		String path = System.getProperty("user.dir").replace("\\", "/");
		//加上时间戳以区分截图
		String curTime = TimeUtil.getDate("yyyyMMddHHmmss");
		screenShot.saveScreenShot(path + "/img/", "testFail" + curTime + ".png");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		logger.error("Test Skipped");
		super.onTestSkipped(tr);
	}

	@Override
	public void onStart(ITestContext testContext) {
		logger.info("Test Start");
		super.onStart(testContext);
	}

	@Override
	public void onFinish(ITestContext testContext) {
		logger.info("Test Finish");
		super.onFinish(testContext);
	}

}
