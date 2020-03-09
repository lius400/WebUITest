package com.liuchao.TestNG;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

//RetryListener类，implements IAnnotationTransformer 主要功能是监听事件
public class TestNGRetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation testannotation, Class testClass,
                          Constructor testConstructor, Method testMethod)    {
        IRetryAnalyzer retry = testannotation.getRetryAnalyzer();

        if (retry == null)    {
            testannotation.setRetryAnalyzer(TestNGRetry.class);
        }

    }
}