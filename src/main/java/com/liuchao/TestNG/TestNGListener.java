package com.liuchao.TestNG;

import com.liuchao.utils.ScreenShot;
import com.liuchao.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.*;

/**
 * 监听测试过程
 * 
 * @author liuc
 */
@Slf4j
public class TestNGListener extends TestListenerAdapter {

	private static WebDriver driver;


	public static void setDriver(WebDriver driver) {
		TestNGListener.driver = driver;
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		log.info("Test Success");
		super.onTestSuccess(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		log.error("Test Failure");
		super.onTestFailure(tr);
		String drivertype=driver.getClass().getName();
		log.error(drivertype);
		if(!drivertype.equals("org.openqa.selenium.htmlunit.HtmlUnitDriver")){
			ScreenShot screenShot = new ScreenShot(driver);
			//获取当前project目录
			String path = ClassLoader.getSystemResource("").toString().replace("\\", "/");
			//加上时间戳以区分截图
			String curTime = TimeUtil.getDate("yyyyMMddHHmmss");
			screenShot.saveScreenShot(path + "/Failure_img/", "testFail" + curTime + ".png");
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log.error("Test Skipped");
		super.onTestSkipped(tr);
	}

	@Override
	public void onStart(ITestContext testContext) {
		log.info("Test Start");
		super.onStart(testContext);
	}

//	@Override
//	public void onFinish(ITestContext testContext) {
//		log.info("Test Finish");
//		super.onFinish(testContext);
//	}

//	测试结果处理，testng运行结果中，去掉重复运行的用例，即不论这个用例跑多少遍，都算一个用例
	@Override
	public void onFinish(ITestContext testContext) {
		// super.onFinish(testContext);
		log.info("Test Finish");

		// List of test results which we will delete later
		ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
		// collect all id's from passed test
		Set<Integer> passedTestIds = new HashSet<Integer>();
		for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {
			log.info("PassedTests = " + passedTest.getName());
			passedTestIds.add(getId(passedTest));
		}

		Set<Integer> failedTestIds = new HashSet<Integer>();
		for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {
			log.info("failedTest = " + failedTest.getName());
			// id = class + method + dataprovider
			int failedTestId = getId(failedTest);

			// if we saw this test as a failed test before we mark as to be
			// deleted
			// or delete this failed test if there is at least one passed
			// version
			if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
				testsToBeRemoved.add(failedTest);
			} else {
				failedTestIds.add(failedTestId);
			}
		}

		// finally delete all tests that are marked
		for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator
				.hasNext();) {
			ITestResult testResult = iterator.next();
			if (testsToBeRemoved.contains(testResult)) {
				log.info("Remove repeat Fail Test: " + testResult.getName());
				iterator.remove();
			}
		}

	}

	private int getId(ITestResult result) {
		int id = result.getTestClass().getName().hashCode();
		id = id + result.getMethod().getMethodName().hashCode();
		id = id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
		return id;
	}

}
