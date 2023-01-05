import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true){
            printMenu();
            int userCommand = scanner.nextInt();
            if (userCommand == 1){
                MonthlyReport.printAllMonthsCsv();
            }
            else if (userCommand == 2){
                YearlyReport.printYearCsv();
            }
            else if (userCommand == 3){
                YearlyReport.checkIncome();
            }
            else if (userCommand == 4){
                MonthlyReport.monthReport();
            }
            else if (userCommand == 5){
                YearlyReport.printYearReport();
            }
            else if (userCommand == 726){
                System.out.println("Выход...");
                break;
            }
        }
    }

    public static void printMenu(){
        System.out.println(
                "\n1. Считать все месячные отчёты.\n" +
                        "2. Считать годовой отчёт.\n"+
                        "3. Сверить отчёты.\n" +
                        "4. Вывести информацию о всех месячных отчётах.\n" +
                        "5. Вывести информацию о годовом отчёте.\n" +
                        "Для выхода введите данную комбинацию: 726");
    }

    public static String readFileByName (String fileName) {
        try{
            return Files.readString(Path.of(fileName));
        }
        catch (IOException ex){
            return null;
        }
    }
}

