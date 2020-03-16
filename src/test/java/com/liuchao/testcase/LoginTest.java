package com.liuchao.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.liuchao.action.Login;
import com.liuchao.object.BasePage;
import com.liuchao.utils.BrowserUtil;
import com.liuchao.TestNG.TestNGListener;
import com.liuchao.TestNG.DataProviderImp;

import java.util.ArrayList;

@Listeners({TestNGListener.class})
public class LoginTest {
	private WebDriver driver;
	private Login login;
//	@DataProvider(name = "loginParams")
//	public Object[] loginParams(){
//		ArrayList<TestData> userdata = ExcelUtil.getTestData();
//		TestData[] userdataA = new TestData[userdata.size()];
//		userdata.toArray(userdataA);
//		return userdataA;
////		return new Object[][]{{"li","1111","用户名或密码有误"},{"li","","用户名或密码有误"},{"","123456","用户名或密码有误"}};
//	}

	@BeforeClass
	@Parameters({"browserDriverUrl","url"})
//	public void beforeClass(String browserDriverUrl,String url) {
//		driver = BrowserUtil.chrome(browserDriverUrl,30);
	public void beforeClass(String browserDriverUrl,String url) {
		//在内存中运行，不用启用浏览器，使用该方法无法使用截图功能
		driver = BrowserUtil.htmlUnitDriver(30);
		driver.get(url);
	}
	@BeforeMethod
	public void BeforeMethod(){
		login = new Login(driver);
		TestNGListener.setDriver(driver);
	}

	
	@Test(dataProvider = "loginParams",dataProviderClass = DataProviderImp.class,description = "异常用户信息登录")
	public void login02(String username,String pwd,String Expect) throws Exception{
		login.login(username, pwd);
		String tip = new BasePage(driver, "loginPage").getText("错误提示");
        System.out.println(driver.manage().getCookieNamed("JSESSIONID").getValue());
		Assert.assertEquals(tip,Expect);
	}
	
	@Test(dependsOnMethods = "login02",description = "正常用户登录")
	@Parameters({ "username", "pwd" })
	public void login(String username,String pwd) throws Exception {
		login.login(username, pwd);
		String tip = new BasePage(driver, "minePage").getText("修改密码");
		Assert.assertEquals(tip, "修改密码");
	}

	@AfterClass
	public void afterClass() {

	}
	@AfterTest
	public void afterTest(){
	    driver.quit();
	}
}
