package com.pzg.code.commons.excel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName :  ExcelToData
 * @Author : PZG
 * @Date : 2019-07-08   11:37
 * @Description :
 */
public class ExcelToData {

    public static <T> List<T> excel2Data(T t) {
        // 导出列查询。
        ExportConfig currentExportConfig;
        ExportItem currentExportItem;
        List<ExportItem> exportItems = new ArrayList<>();
        for (Field field : t.getClass().getDeclaredFields()) {
            currentExportConfig = field.getAnnotation(ExportConfig.class);
            if (currentExportConfig != null) {
                currentExportItem = new ExportItem().setField(field.getName())
                        .setDisplay("field".equals(currentExportConfig.value()) ? field.getName()
                                : currentExportConfig.value())
                        .setColumnName(currentExportConfig.columnName()).setColumnType(field.getType().toString());
                exportItems.add(currentExportItem);
            }
        }
        try {
            String excelPath = "C:\\Users\\Administrator\\Desktop\\部门表.xlsx";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb = null;
                //根据文件后缀（xls/xlsx）进行判断
                if ("xls".equals(split[1])) {
                    InputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    InputStream fis = new FileInputStream(excel);
                    wb = new XSSFWorkbook(fis);
                } else {
                    System.out.println("文件类型错误!");
                }
                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
                Integer noRead = 1;//不需要读取的行数
                Integer firstRowIndex = sheet.getFirstRowNum() + noRead;   //第一行是列名，所以不读
                Integer lastRowIndex = sheet.getLastRowNum();
                //属性字段行
                Row title = sheet.getRow(firstRowIndex - 1);
                List<T> list = new ArrayList<>();
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    T t1 = (T) t.getClass().newInstance();
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = title.getFirstCellNum();
                        int lastCellIndex = title.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) { //遍历列
                            String tname = title.getCell(cIndex).toString();
                            List<ExportItem> collect = exportItems.stream().filter(u -> u.getDisplay().equals(tname)).collect(Collectors.toList());
                            if (collect != null && collect.size() != 0) {
                                String field = collect.get(0).getField();
                                String columnType = collect.get(0).getColumnType();
                                Cell cell = row.getCell(cIndex);
                                if (cell != null && !"".equals(cell.toString().trim())) {
                                    Object object = cell;
                                    if (columnType.contains("Date")) {
                                        String s1 = object.toString();
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        object = format.parse(s1);
                                    }
                                    BeanUtils.setProperty(t1, field, object);
                                }
                            }
                        }
                    }
                    list.add(t1);
                }
                return list;
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

