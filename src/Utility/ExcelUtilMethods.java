package Utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtilMethods {
	File f;
	FileInputStream fi;
	FileOutputStream fo;
	XSSFWorkbook wb;
	XSSFSheet ws;
	XSSFRow row;
	XSSFCell cell;
	XSSFCellStyle style;
	//it is constructor to access excel path
public ExcelUtilMethods(String inputpath) 
		{
	try {
	f=new File(inputpath);
	fi=new FileInputStream(f);
	wb=new XSSFWorkbook(fi);
	}catch(IOException i)
	{
		System.out.println(i.getMessage());
	}
		}
	//count no of rows from sheet
public int getRowCount(String sheetname) 
		{		
		int rowcount;
		ws=wb.getSheet(sheetname);
		rowcount=ws.getLastRowNum();
		return rowcount;		
		}
	//count no of columns in row
public int getCellCount(String sheetname) 
		{		
			int cellcount;
		ws=wb.getSheet(sheetname);
		row=ws.getRow(0);
		cellcount=row.getLastCellNum();
		return cellcount;
		}
	//get columns from sheet	
public String getcellData(String sheetname,int row,int column)
{
String data = "";
if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
{
int celldata = (int)(wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue());
//convert integer into string
data = String.valueOf(celldata);
}
else{
data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
}
return data;
	}

//writing into new File	
public void setCellDatanewfile(String sheetname, int rownum,int colnum,
		String data,String outputxlfile) 
	{
	try {
	ws=wb.getSheet(sheetname);
	row=ws.getRow(0);
	ws.getRow(rownum).createCell(colnum).setCellValue(data);
	fo=new FileOutputStream(new File(outputxlfile));
	wb.write(fo);
	}catch(IOException i)
	{
	System.out.println(i.getMessage());
	}
	}
public void fillGreenColor(String sheetname, int rownum,int colnum,
		String excelpath) 
{
	try {
ws=wb.getSheet(sheetname);
row=ws.getRow(0);
style=wb.createCellStyle();
style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
ws.getRow(rownum).getCell(colnum).setCellStyle(style);
fo=new FileOutputStream(excelpath);
wb.write(fo);
	}catch(IOException i)
	{
		System.out.println(i.getMessage());
	}
	}
public void fillRedColor(String sheetname, int rownum,int colnum,
		String excelpath) 
{
	try {
ws=wb.getSheet(sheetname);
row=ws.getRow(0);
style=wb.createCellStyle();
style.setFillForegroundColor(IndexedColors.RED.getIndex());
style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
ws.getRow(rownum).getCell(colnum).setCellStyle(style);
fo=new FileOutputStream(excelpath);
wb.write(fo);
	}catch(IOException i)
	{
		System.out.println(i.getMessage());
	}
	}
public void closefiles() 
{
	try {
fi.close();
fo.close();
wb.close();
	}catch(IOException i)
	{
System.out.println(i.getMessage());
	}
}
}
















