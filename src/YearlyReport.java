import java.util.ArrayList;

public class YearlyReport {

    ArrayList<YearInformation> yearlyReports = new ArrayList<>();

    public YearlyReport(String path){
        String list = FileReader.readFileByName(path);
        String[] lines = new String[0];
        if (!list.equals("")){
            lines = list.split("\r?\n");
        }
        for (int i = 1; i < lines.length; i++){
            String line = lines[i];
            String[] columns = line.split(",");
            int reportsMonth = Integer.parseInt(columns[0]);
            int reportsAmount = Integer.parseInt(columns[1]);
            Boolean reportsIsExpense = Boolean.parseBoolean(columns[2]);

            YearInformation yearsContent = new YearInformation(reportsMonth, reportsAmount, reportsIsExpense);
            yearlyReports.add(yearsContent);
        }
    }

   public static double findAverageValue (int yearNumber, boolean isExpense){
        double sum = 0;
        for (YearInformation report : FileReader.yearReports.get(yearNumber).yearlyReports){
            if (report.isExpense.equals(isExpense)){
                sum += report.amount;
            }
        }
        return sum/12;
   }

   public static void getDifference (int yearNumber){
       int falseValue = 0;
       int trueValue = 0;
       int diff = 0;
       for (int monthNumber : FileReader.allMonthlyReports.keySet()){
           for (YearInformation report : FileReader.yearReports.get(yearNumber).yearlyReports){
               if (report.month == monthNumber){
                   if (report.isExpense.equals(true)){
                       trueValue = report.amount;
                   }
                   if (report.isExpense.equals(false)){
                       falseValue = report.amount;
                   }
               }
               diff = falseValue - trueValue;
           }
           System.out.println("Доход за " + monthNumber + " месяц: " + diff);
       }
   }

    public static void yearReport (){
        for (int yearNumber : FileReader.yearReports.keySet()){
            System.out.println("\n" + yearNumber + " год:");
            getDifference(yearNumber);
            System.out.println("Средний доход за " + yearNumber + " год " + ": " + findAverageValue(yearNumber, false));
            System.out.println("Средний расход за " + yearNumber + " год " + ": " + findAverageValue(yearNumber, true));
        }
    }
}
