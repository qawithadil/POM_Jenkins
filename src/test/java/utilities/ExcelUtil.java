package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static void readSingleColData(String filPath, int colNum, String sheet) {

		File file = new File(filPath);

		try {
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheet);
			int RowCount = sh.getLastRowNum();
			System.out.println("Total Rows:=" + RowCount);
			System.out.println((colNum + 1) + "   Column Values Are::\n");
			System.out.println("**************");
			for (int i = 0; i < RowCount; i++) {
				XSSFRow row = sh.getRow(i);
				XSSFCell cell = row.getCell(colNum);
				String data = cell.toString();
				System.out.println(" " + data + "  ");
			}
			System.out.println("**************");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}

	public static void writeSingle_col(String filePath, int col, String sheet, int[] values) {

		int TotalRows;

		File file = new File(filePath);

		try {
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheet);
			TotalRows = sh.getLastRowNum();
			for (int i = 1; i <= TotalRows; i++) {
				XSSFRow row = sh.getRow(i);
				XSSFCell cell = row.createCell(col);
				cell.setCellValue(values[i]);
				FileOutputStream fos = new FileOutputStream(filePath);
				wb.write(fos);

			}
			System.out.println("Actual Result Written Successfully....");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public int getRowCount(String filePath) throws InvalidFormatException, IOException
	{
	File file = new File(Constant.filPath);
	FileInputStream fis = new FileInputStream(file);
	XSSFWorkbook wb = new XSSFWorkbook(file);
	XSSFSheet sheet = wb.getSheet("Sheet1");
	int Rows_count=sheet.getLastRowNum();
	fis.close();
	return Rows_count;
	}
	
	public int getColCount(String filePath) throws InvalidFormatException, IOException {
		File file = new File(Constant.filPath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		XSSFRow row = sheet.getRow(0);
		int cell_count = row.getLastCellNum();
		fis.close();
		return cell_count;
	}
	
	public String readSingleCell_Data(String filePath, String Sheet, int rowNum, int colNum) throws InvalidFormatException, IOException {
		File file = new File(Constant.filPath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet(Sheet);
		XSSFRow row = sheet.getRow(rowNum);
		String cell_data = row.getCell(colNum).toString();
		fis.close();
		
		return cell_data;
		
	}

}
