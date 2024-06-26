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
            byte reportsMonth = Byte.parseByte(columns[0]);
            int reportsAmount = Integer.parseInt(columns[1]);
            Boolean reportsIsExpense = Boolean.parseBoolean(columns[2]);

            YearInformation yearsContent = new YearInformation(reportsMonth, reportsAmount, reportsIsExpense);
            yearlyReports.add(yearsContent);
        }
    }

   public static int findAverageValue (int yearNumber, boolean isExpense){
        int sum = 0;
        int amountOfMonths = 0;
        for (YearInformation report : FileReader.yearReports.get(yearNumber).yearlyReports){
            if (report.isExpense.equals(isExpense)){
                sum += report.amount;
                amountOfMonths++;
            }
        }
        return sum/amountOfMonths;
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
            System.out.println("Средняя прибыль за " + yearNumber + " год " + ": " + findAverageValue(yearNumber, false));
            System.out.println("Средняя трата за " + yearNumber + " год " + ": " + findAverageValue(yearNumber, true));
        }
    }
}
