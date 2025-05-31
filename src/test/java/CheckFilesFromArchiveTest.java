import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckFilesFromArchiveTest {
    private ClassLoader cl = CheckFilesFromArchiveTest.class.getClassLoader();
    private static final String zipname = "test.zip";
    private static final String zipnull = "null.zip";

    @Test
    @Tag("FilePasrsing")
    @DisplayName("Проверка .csv файла из архива")
    void shouldCheckCsvFromArchiveTest() throws Exception {
        boolean fileFound = false;
        try (InputStream is = cl.getResourceAsStream(zipname);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".csv")) {
                    fileFound = true;
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    assertThat(content).contains(
                            new String[]{"Movie", "Year", "Director", "Genre"},
                            new String[]{"Mission:Impossible", "1996", "Brian De Palma", "Action"},
                            new String[]{"The Matrix", "1999", "The Wachowskis", "Science fiction action"}
                    );
                }
            }
        }
        assertThat(fileFound).as(".csv файл не найден").isTrue();
    }

    @Test
    @Tag("FileParsing")
    @DisplayName("Проверка .xls файла из архива")
    void shouldCheckXlsFromArchiveTest() throws Exception {
        boolean fileFound = false;
        try (InputStream is = cl.getResourceAsStream(zipname);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".xlsx")) {
                    fileFound = true;
                    XLS xls = new XLS(zis);
                    String actualString1 = xls.excel.getSheetAt(0).getRow(2).getCell(0).getStringCellValue();
                    String actualString2 = xls.excel.getSheetAt(0).getRow(2).getCell(2).getStringCellValue();
                    double actualDoubleResult = xls.excel.getSheetAt(0).getRow(2).getCell(1).getNumericCellValue();
                    assertThat(actualString1).contains("Dmitriy Petrov");
                    assertThat(actualString2).contains("Novosibirsk");
                    assertThat(actualDoubleResult).isEqualTo(1993);
                }
            }
        }
        assertThat(fileFound).as(".xls файл не найден").isTrue();
    }

    @Test
    @Tag("FILE_TEST")
    @DisplayName("Проверка pdf файла из архива")
    void shouldCheckPdfFromArchive() throws Exception {
        boolean fileFound = false;
        try (InputStream is = cl.getResourceAsStream(zipname);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".pdf")) {
                    fileFound = true;
                    PDF pdf = new PDF(zis);
                    System.out.println();
                    assertThat(pdf.title).contains("Anatomy of the Somatosensory System");
                    assertThat(pdf.numberOfPages).isEqualTo(4);
                }
            }
            assertThat(fileFound).as(".pdf файл не найден").isTrue();
        }
    }
}