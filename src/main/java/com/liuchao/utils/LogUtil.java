package com.liuchao.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
//import org.apache.log4j.PropertyConfigurator;

public class LogUtil {
	
	private static Logger logger;
	
	Class<?> Load_Class = null;
	
	public LogUtil(String classname) throws ClassNotFoundException {
		this.Load_Class = Class.forName(classname);
	}
	
    private void InitLog4jConfig() {
        Properties props = null;
        FileInputStream fis = null;
        try {
            // 从配置文件dbinfo.properties中读取配置信息
            props = new Properties();
            fis = new FileInputStream("log4j.properties");
            props.load(fis);
            PropertyConfigurator.configure(props);//装入log4j配置信息
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            fis = null;
        }
    }
    
    //  相对路径的配置文件加载  
//    public static void test2(){
//        //这里需要注意路径中不要出现中文和空格，如果存在中文，请使用url转码
//    	ConfigurationSource source;
//        try {
//
//            // 使用ClassLoader.getSystemResource
//            String config = ClassLoader.getSystemResource("").toString();
//            System.out.println(config+"\\config\\log4j2.xml");
//            File configFile = new File(config+"\\config\\log4j2.xml");
//            source = new ConfigurationSource(new FileInputStream(configFile),configFile);
//            Configurator.initialize(null, source);
//
////            logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
//            logger = LogManager.getLogger(LogUtil.class);
//            logger.trace("trace...");
//            logger.debug("debug...");
//            logger.info("info...");
//            logger.warn("warn...");
//            logger.error("error...");
//            logger.fatal("fatal...");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
//    public static void main(String[] args) {
//        test2();
//    }

	/*
	public void test() {
		
		Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
		ConfigurationSource source;
		try {
		      //方法1  使用getResource()
		      String path="/com/liuchao/config/log4j2.xml";
		      URL url=LogUtil.class.getResource(path);
		      source = new ConfigurationSource(new FileInputStream(new File(url.getPath())),url);
		      Configurator.initialize(null, source);	
		      
		      //方法2 使用ClassLoader.getSystemResource
		      String config=ClassLoader.getSystemResource("").toString();
		      source = new ConfigurationSource(new FileInputStream(config+"\\src\\com\\liuchao\\config\\Log4j.properties"));
		      Configurator.initialize(null, source);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}*/

}
