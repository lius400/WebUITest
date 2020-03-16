package com.liuchao.utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static Object[][] getTestData() throws IOException {
//        ArrayList<TestData> data = new ArrayList<TestData>();
        InputStream is = null;
        Object[][] UserdataIterm = new Object[0][];
        try {
//            String DataPath = ClassLoader.getSystemResource("")+"/testData/testData.xlsx";
//            String DataPath = ExcelUtil.class.getClassLoader().getResource("").toString().replace("test-classes","classes")+"testData/testData.xlsx";
            String DataPath = ExcelUtil.class.getClassLoader().getResource("testData.xls").getPath();
            File Datafile = new File(DataPath);
//            System.out.println("测试数据文件路径："+Datafile.getPath());
            if (!Datafile.exists()) {
                throw new FileNotFoundException(Datafile.getPath());
            }
            jxl.Workbook wb = null;
            is = new FileInputStream(Datafile);
            wb = Workbook.getWorkbook(is);

            int sheetSize = wb.getNumberOfSheets();
            Sheet sheet = wb.getSheet("loginPage");
            int row_total = sheet.getRows();
            UserdataIterm = new Object[row_total-1][];
            for (int j = 1; j < row_total; j++) {
                Cell[] cells = sheet.getRow(j);
                UserdataIterm[j-1] = new Object[]{cells[0].getContents(), cells[1].getContents(), cells[2].getContents()};
            }
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {
            if (is != null)
                is.close();
        }
        return UserdataIterm;
    }
}