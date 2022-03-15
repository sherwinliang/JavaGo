package common;


import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {
    public static CellStyle customizeCellStyle(Workbook wb, short fontSize, String fontName, short fontColor,
                                               boolean isBold, boolean needBorder, boolean isWarning, String dateFormat){
        Font font = wb.createFont();
        font.setFontHeightInPoints(fontSize);
        font.setFontName(fontName);
        font.setColor(fontColor);
        font.setBold(isBold);

        CellStyle style = wb.createCellStyle();
        style.setFont(font);

        if(needBorder){
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);

            style.setBottomBorderColor(IndexedColors.BLACK.index);
            style.setLeftBorderColor(IndexedColors.BLACK.index);
            style.setRightBorderColor(IndexedColors.BLACK.index);
            style.setTopBorderColor(IndexedColors.BLACK.index);
        }

        if(isWarning){
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(IndexedColors.YELLOW.index);
        }

        if(dateFormat!=null){
            DataFormat df = wb.createDataFormat();
            style.setDataFormat(df.getFormat(dateFormat));
        }
        return style;
    }
}
