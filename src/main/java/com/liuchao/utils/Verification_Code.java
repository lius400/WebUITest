package com.liuchao.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

public class Verification_Code {
	
    //元素截图  
   public static void captureElement(WebDriver driver, WebElement element, String path){
       // 截图整个页面 
       File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       try {
           // 获得元素的高度和宽度  
           int width = element.getSize().getWidth();
           int height = element.getSize().getHeight();
           // 创建一个矩形使用上面的高度和宽度 
           Rectangle rect = new Rectangle(width, height);
           // 得到元素的坐标 
           Point p = element.getLocation();
           BufferedImage img = ImageIO.read(srcFile);
           BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width,rect.height);
           // 存为jpg格式  
           ImageIO.write(dest, "jpg", srcFile);
           Thread.sleep(1000);
           FileUtils.copyFile(srcFile, new File(path));
       } catch (Exception e) {
           e.printStackTrace();
       }  
   }  

   public static String Distin_Code(String image_Path)
   {
//   	图形验证码识别
	   String result = null;
       File imageFile = new File(image_Path);  
       ITesseract instance = new Tesseract();  
//       URL url = ClassLoader.getSystemResource("tessdata");
//       String path = url.getPath().substring(1);
       String path = System.getProperty("user.dir")+"\\tessdata";
//       System.out.println("tessdata地址："+path);
       instance.setDatapath(path);
       // 默认是英文（识别字母和数字），如果要识别中文(数字 + 中文），需要制定语言包  
//       instance.setLanguage("chi_sim");  
       try{
           result = instance.doOCR(imageFile);  
           System.out.println(result);
       }catch(TesseractException e){
           System.out.println(e.getMessage());
       }
	return result;
      
//       String path1 = System.getProperty("user.dir");
//       System.out.println("项目路径："+path1);
   }
   
   
//	public static void main(String[] args) throws IOException {
//
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//	    String driverpath = System.getProperty("user.dir")+"\\chromedriver.exe";
//	    System.out.println("项目路径："+driverpath);
//		System.setProperty("webdriver.chrome.driver",driverpath);
//
//		Date date_now = new Date();
//		String time = format.format(date_now.getTime());//这个就是把时间戳经过处理得到期望格式的时间
//		String filepath = System.getProperty("user.dir")+"\\Verification_Code"+time+".jpg";
//		System.out.println("图片保存地址："+filepath);
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://www.edianzu.com/login");
//		driver.manage().window().maximize();
//		WebElement element = driver.findElement(By.xpath(".//*[@id='codeImage']"));
//		String Attribute = element.getAttribute("src");
//		System.out.println("图片URL:"+Attribute);
//
//		captureElement(driver,element,filepath);
//		driver.quit();
//		System.out.println("验证码图片以保存");
//	}

}