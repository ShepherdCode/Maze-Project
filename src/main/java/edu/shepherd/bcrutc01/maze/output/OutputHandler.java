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
    private Sheet[] outputSheets;
    private String sheetName;
    private int[] rowCounters;

    public OutputHandler() {
        outputSpreadsheet = new HSSFWorkbook();
        sheetName = "output-" + System.currentTimeMillis();
        outputSheets = new Sheet[4];
        rowCounters = new int[4];
        outputSheets[0] = outputSpreadsheet.createSheet(sheetName + "-dijkstras");
        outputSheets[1] = outputSpreadsheet.createSheet(sheetName + "-astar-euclid");
        outputSheets[2] = outputSpreadsheet.createSheet(sheetName + "-bellmanford");
        outputSheets[3] = outputSpreadsheet.createSheet(sheetName + "-astar-manhattan");


        for(int i = 0; i < outputSheets.length; i++) {
            Row row = outputSheets[i].createRow(0);

            row.createCell(0).setCellValue("Size");
            row.createCell(1).setCellValue("Elapsed Time");
            row.createCell(2).setCellValue("Used Memory");
            row.createCell(3).setCellValue("Algorithm");

            rowCounters[i] = 1;
        }

    }

    /**
     * Log a data entry in the spreadsheet with the following values
     *
     * @param data the data to output
     */
    public void writeEntry(AlgorithmType type, OutputData data) {
        Row row = outputSheets[type.ordinal()].createRow(rowCounters[type.ordinal()]);

        row.createCell(0).setCellValue(data.getSize());
        row.createCell(1).setCellValue(data.getElapsedTime());
        row.createCell(2).setCellValue(data.getUsedMemory());
        row.createCell(3).setCellValue(type.toString());
        rowCounters[type.ordinal()]++;
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
