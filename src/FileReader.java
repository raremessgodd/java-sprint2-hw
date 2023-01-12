import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class FileReader {
    static HashMap<Integer, MonthlyReport> allMonthlyReports = new HashMap<>();
    static HashMap<Integer, YearlyReport> yearReports = new HashMap<>();

    public static void readMonthFiles (){
        System.out.println("Считывание месячных файлов...");
        int amountOfMonths = 3;
        for (int i = 1; i <= amountOfMonths; i++){
            String fileName = "m.20210" + i + ".csv";
            String filePath = ("resources/" + fileName);
            allMonthlyReports.put(i, new MonthlyReport(filePath));
        }
        System.out.println("Считывание месячных файлов прошло успешно!");
    }

    public static void readYearFiles (){
        System.out.println("Считывание годовых файлов...");
        int currentYear = 2021;
        for (int i = 2021; i <= currentYear; i++){
            String fileName = "y." + i + ".csv";
            String filePath = ("resources/" + fileName);
            yearReports.put(i, new YearlyReport(filePath));
        }
        System.out.println("Считывание годовых файлов прошло успешно!");
    }

    public static String readFileByName (String filePath) {
        try{
            String file = Files.readString(Path.of(filePath));
            if (!file.equals("")){
                System.out.println("Файл " + filePath + " загружен.");
            }
            return file;
        }
        catch (IOException ex){
            System.out.println("Файл " + filePath + " не найден.");
            return "";
        }
    }
}
