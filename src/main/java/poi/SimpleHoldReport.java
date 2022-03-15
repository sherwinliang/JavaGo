package poi;

import common.ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleHoldReport {

    public static void main(String... args){
        //在try中声明实例由java负责关闭资源
        try(FileOutputStream fos =
                    new FileOutputStream("持仓报表.xlsx");
            Workbook wb = new XSSFWorkbook()){
            //获取数据
            List<SimpleHoldPojo> data = obtainSimpleHoldData();
            //生成报表
            createSimpleHoldReport(wb, data);
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<SimpleHoldPojo> obtainSimpleHoldData() {
        List<SimpleHoldPojo> result = Stream.of(new SimpleHoldPojo(1, "a101", LocalDate.parse("2022-03-15"), 100, 100, 10, 110),
                new SimpleHoldPojo(1, "a101", LocalDate.parse("2022-03-14"), 200, 200, 10, 210),
                new SimpleHoldPojo(2, "b109", LocalDate.parse("2022-03-13"), 150, 150, 20, 170),
                new SimpleHoldPojo(3, "a131", LocalDate.parse("2022-03-12"), 110, 110, 10, 110),
                new SimpleHoldPojo(4, "c101", LocalDate.parse("2022-03-11"), 120, 120, 15, 135),
                new SimpleHoldPojo(5, "a102", LocalDate.parse("2022-03-10"), 130, 130, 10, 140),
                new SimpleHoldPojo(6, "f101", LocalDate.parse("2022-03-09"), 140, 140, 10, 150),
                new SimpleHoldPojo(7, "a123", LocalDate.parse("2022-01-15"), 150, 150, 10, 160),
                new SimpleHoldPojo(8, "a321", LocalDate.parse("2022-02-15"), 160, 160, 10, 170),
                new SimpleHoldPojo(9, "f689", LocalDate.parse("2022-01-13"), 170, 170, 10, 180),
                new SimpleHoldPojo(10, "a811", LocalDate.parse("2022-03-13"), 180, 180, 10, 190),
                new SimpleHoldPojo(11, "z603", LocalDate.parse("2022-03-17"), 100, 100, 10, 110),
                new SimpleHoldPojo(12, "a001", LocalDate.parse("2022-03-18"), 190, 190, 20, 210),
                new SimpleHoldPojo(13, "a564", LocalDate.parse("2022-01-15"), 100, 100, 10, 110)).
                collect(Collectors.toList());
        return result;
    }

    private static Workbook createSimpleHoldReport(Workbook wb, List<SimpleHoldPojo> data) throws IOException {
        Sheet sheet = wb.createSheet();
        int rowIndex = 0;
        Row row = sheet.createRow(rowIndex++);
        Cell cell = null;
        //所需要的样式
        CellStyle headerStyle = ExcelUtils.customizeCellStyle(wb,(short)16,"宋体",
                IndexedColors.BLACK.index,true,true,false,null);
        CellStyle wordStyle = ExcelUtils.customizeCellStyle(wb,(short)14,"宋体",
                IndexedColors.BLACK.index,false,true,false,null);
        CellStyle dateStyle = ExcelUtils.customizeCellStyle(wb,(short)14,"宋体",
                IndexedColors.BLACK.index,false,true,false,"m/d/yy");
        CellStyle moneyStyle = ExcelUtils.customizeCellStyle(wb,(short)14,"宋体",
                IndexedColors.BLACK.index,false,true,false,"#,##0.00");
        CellStyle moneyWarningStyle = ExcelUtils.customizeCellStyle(wb,(short)14,"宋体",
                IndexedColors.RED.index,true,true,true,"#,##0.00");
        //表头
        String[] headerComments = {"序号","账户","日期","数量","成本","估增","市值"};
        for(int i=0;i<headerComments.length;i++){
            cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(headerComments[i]);
        }
        //填充数据
        for(SimpleHoldPojo simpleHold : data){
            int cellIndex = 0;
            row = sheet.createRow(rowIndex++);
            //ID
            cell = row.createCell(cellIndex++);
            cell.setCellStyle(wordStyle);
            cell.setCellValue(simpleHold.getId());
            //账户
            cell = row.createCell(cellIndex++);
            cell.setCellStyle(wordStyle);
            cell.setCellValue(simpleHold.getAccount());
            //日期
            cell = row.createCell(cellIndex++);
            cell.setCellStyle(dateStyle);
            cell.setCellValue(simpleHold.getHoldDate());
            //持仓数量
            cell = row.createCell(cellIndex++);
            cell.setCellStyle(moneyStyle);
            cell.setCellValue(simpleHold.getHoldUnits());
            //成本
            cell = row.createCell(cellIndex++);
            cell.setCellStyle(moneyStyle);
            cell.setCellValue(simpleHold.getCost());
            //估增
            cell = row.createCell(cellIndex++);
            cell.setCellStyle(moneyWarningStyle);
            cell.setCellValue(simpleHold.getIncrement());
            //市值
            cell = row.createCell(cellIndex++);
            cell.setCellStyle(moneyStyle);
            cell.setCellValue(simpleHold.getMarketValue());
        }
        //自动固定列宽，需填充完数据后调用，否则不准确
        for(short i=0;i<headerComments.length;i++){
            sheet.autoSizeColumn(i);
        }
        //设置冻结窗口
        sheet.createFreezePane(3,1,3,1);
        //设置下拉筛选过滤
        CellRangeAddress autoFilterRange = CellRangeAddress.valueOf("B1:C1");
        sheet.setAutoFilter(autoFilterRange);
        return wb;
    }
}
