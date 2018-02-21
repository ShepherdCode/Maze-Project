package edu.shepherd.bcrutc01.maze.output;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Singleton that handles the writing of data to a spreadsheet
 *
 * @author Brian Crutchley
 * @version 1.0
 */
public class OutputHandler {

    private Workbook outputSpreadsheet;
    private Sheet outputSheet;
    private String sheetName;
    private int rowCounter = 1;

    public OutputHandler() {
        outputSpreadsheet = new HSSFWorkbook();
        sheetName = "output-" + System.currentTimeMillis();
        outputSheet = outputSpreadsheet.createSheet(sheetName);
        Row row = outputSheet.createRow(0);
        row.createCell(0).setCellValue("Type");
        row.createCell(1).setCellValue("Bias");
        row.createCell(2).setCellValue("Complexity");
        row.createCell(3).setCellValue("Elapsed Time");

    }

    /**
     * Log a data entry in the spreadsheet with the following values
     *
     * @param type the algorithm this data point is for
     * @param data the data to output
     */
    public void writeEntry(AlgorithmType type, OutputData data) {
        Row row = outputSheet.createRow(rowCounter);

        row.createCell(0).setCellValue(type.ordinal());
        row.createCell(1).setCellValue(data.getBiasValue());
        row.createCell(2).setCellValue(data.getComplexity());
        row.createCell(3).setCellValue(data.getElapsedTime());
        rowCounter++;
    }

    /**
     * Dump all output
     */
    public void writeToFile() {
        try (FileOutputStream out = new FileOutputStream(sheetName + ".xls")){
            outputSpreadsheet.write(out);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
