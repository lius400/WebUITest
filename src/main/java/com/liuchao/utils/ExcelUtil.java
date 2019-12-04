package com.liuchao.utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static ArrayList<TestData> getTestData(){
        ArrayList<TestData> data = new ArrayList<TestData>();
        try{
            String DataPath = ClassLoader.getSystemResource("")+"/testData/testData.xlsx";
            jxl.Workbook wb =null;
            InputStream is = new FileInputStream(DataPath);
            wb = Workbook.getWorkbook(is);

            int sheetSize = wb.getNumberOfSheets();
            Sheet sheet = wb.getSheet("loginPage");
            int row_total = sheet.getRows();
            for (int j = 1; j < row_total; j++) {
                Cell[] cells = sheet.getRow(j);

                data.add(new TestData(cells[0].getContents(),cells[1].getContents(),cells[2].getContents()));
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e){
            e.printStackTrace();
        }
        return data;
    }
}