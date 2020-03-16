package com.liuchao.TestNG;

import com.liuchao.utils.ExcelUtil;
import com.liuchao.utils.TestData;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;

public class DataProviderImp {

    @DataProvider(name = "loginParams")
    public static Object[][] loginParams() throws IOException {
        Object[][] UserdataIterm = ExcelUtil.getTestData();
        return UserdataIterm;
//		return new Object[][]{{"li","1111","用户名或密码有误"},{"li","","用户名或密码有误"},{"","123456","用户名或密码有误"}};
    }
}
