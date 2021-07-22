package utils;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import static org.apache.commons.io.FileUtils.getFile;

public class Files {

    public static String readTextFromDocxPath(String path) throws IOException {
        return readTextFromDocxFile(getFile(path));
    }

    public static String readTextFromDocxFile(File docxFilePath) {
        String result = "";
        try {
            FileInputStream fis = new FileInputStream(docxFilePath);
            XWPFDocument document = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);

            result = extractor.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String readTextFromXlsxPath(String path) throws IOException {
        return readTextFromXlsxFile(getFile(path));
    }

    private static String readTextFromXlsxFile(File file) {
        //инициализируем потоки
        String result = "";
        InputStream inputStream = null;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(file);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //разбираем первый лист входного файла на объектную модель
        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        //проходим по всему листу
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                //перебираем возможные типы ячеек
                switch (cellType) {
                    case Cell.CELL_TYPE_STRING:
                        result += cell.getStringCellValue() + " ";
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        result += cell.getNumericCellValue() + " ";
                        break;

                    case Cell.CELL_TYPE_FORMULA:
                        result += cell.getNumericCellValue() + " ";
                        break;
                    default:
                        result += "|";
                        break;
                }
            }
            result += "\n";
        }
        return result;
    }

    public static String readTextFromPdfPath(String path) throws IOException {
        return readTextFromPdfFile(getFile(path));
    }

    private static String readTextFromPdfFile(File pdfFilePath) {
        String result = "";
        try {
            PDDocument doc = PDDocument.load(pdfFilePath);
            PDFTextStripper stripper = new PDFTextStripper();
            result = stripper.getText(doc);
        }
            catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

    public static String readTextFromPath(String path) throws IOException {
        return readTextFromFile(getFile(path));
    }
    public static String readTextFromFile(File file) throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
