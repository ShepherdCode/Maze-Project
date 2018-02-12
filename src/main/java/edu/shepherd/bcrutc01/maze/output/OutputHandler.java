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

    private static OutputHandler instance;

    private Workbook outputSpreadsheet;
    private Sheet outputSheet;
    private String sheetName;
    private int rowCounter = 0;

    private OutputHandler() {
        outputSpreadsheet = new HSSFWorkbook();
        sheetName = "output-" + System.currentTimeMillis();
        outputSheet = outputSpreadsheet.createSheet(sheetName);
    }

    public static OutputHandler getInstance() {
        if (instance == null) {
            instance = new OutputHandler();
        }

        return instance;
    }

    /**
     * Log a data entry in the spreadsheet with the following values
     *
     * @param type the algorithm this data point is for
     * @param biasValue the bias value tested at
     * @param distance the distance value of the start and end point
     * @param time the time it took to complete
     */
    public void writeEntry(AlgorithmType type, double biasValue, double distance, long time) {
        Row row = outputSheet.createRow(rowCounter);

        row.createCell(0).setCellValue(type.ordinal());
        row.createCell(1).setCellValue(biasValue);
        row.createCell(2).setCellValue(distance);
        row.createCell(3).setCellValue(time);
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
