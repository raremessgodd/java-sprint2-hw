import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true){
            printMenu();
            int userCommand = scanner.nextInt();

            if (userCommand > 5 && userCommand != 726){
                System.out.println("Такой команды пока нет.");
            } else if (userCommand == 1){
                FileReader.readMonthFiles();
            } else if (userCommand == 2){
                FileReader.readYearFiles();
            }

            else if (userCommand == 3){
                if (FileReader.yearReports.isEmpty() || FileReader.allMonthlyReports.isEmpty()){
                    System.out.println("Сначала нужно считать файлы с отчетами.");
                } else {
                    Checker.check();
                }
            }

            else if (userCommand == 4){
                if (FileReader.allMonthlyReports.isEmpty()) {
                    System.out.println("Сначала нужно считать файлы с отчетами за месяц.");
                } else {
                    MonthlyReport.monthReport();
                }
            }

            else if (userCommand == 5){
                if (FileReader.yearReports.isEmpty()) {
                    System.out.println("Сначала нужно считать файлы с отчетами за год.");
                } else {
                    YearlyReport.yearReport();
                }
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
}

