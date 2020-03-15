package com.liuchao.TestNG;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//TestNGRetry类，implements IRetryAnalyzer接口，这个类里面确定重跑次数，以及分析每次失败是否需要重新运行
public class TestNGRetry implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = 3;

    @Override
    public boolean retry(ITestResult result) {
        if(!result.isSuccess()) {
            if (retryCount < maxRetryCount) {
                System.out.println("Retrying test " + result.getName() + " with status "
                        + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
                retryCount++;
                result.setStatus(ITestResult.FAILURE);
                return true;    //true是告诉TestNG重新运行Test
            }
            else{
                result.setStatus(ITestResult.FAILURE);   //如果达到 Max尝试次数，则放回false，TestNG不会重运行
            }
        }
        else{
            result.setStatus(ITestResult.SUCCESS);
            resetRetrycount(); // 每次跑完一条用例后，重置retryCount为0，这样dataProvider 数据驱动测试叶支持
        }
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }

    public boolean isRetryAvailable() {
        return retryCount < maxRetryCount;
    }

    public void resetRetrycount() {
        retryCount = 0;
    }

}