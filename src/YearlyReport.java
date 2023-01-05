import java.util.HashMap;

public class YearlyReport {
    public static HashMap<Integer, HashMap<Integer, Integer>> getYearHashMapByIsExpense(boolean isExpense){
        HashMap<Integer, HashMap<Integer, Integer>> allYearsReport = new HashMap<>();
        HashMap<Integer,Integer> yearReport = new HashMap<>();
        String list = Main.readFileByName("resources/y.2021.csv");
        String[] lines = new String[0];
        if (list != null){
            lines = list.split("\n");
        }
        if (isExpense){
            writeReportInHashMap(lines, yearReport, "true");
        }
        else{
            writeReportInHashMap(lines, yearReport, "false");
        }
        allYearsReport.put(2021, yearReport);
        return allYearsReport;
    }

    public static void writeReportInHashMap (String[] lines, HashMap<Integer,Integer> yearReport, String isExpense){
        for (int i = 1; i < lines.length; i++){
            String line = lines[i];
            String[] columns = line.split(",");
            if (columns[2].equals(isExpense)){
                yearReport.put(Integer.parseInt(columns[0]), Integer.parseInt(columns[1]));
            }
        }
    }
    public static void checkIncome () {
        for (int i = 1; i <= 3; i++) {
            int yearIncome = getYearHashMapByIsExpense(false).get(2021).get(i);
            int yearExpense = getYearHashMapByIsExpense(true).get(2021).get(i);
            int monthIncome = MonthlyReport.getMonthIncomeOrExpense(false, i);
            int monthExpense = MonthlyReport.getMonthIncomeOrExpense(true, i);
            if (yearIncome != monthIncome || yearExpense != monthExpense) {
                System.out.println("Обнаружена ошибка в отчетах. Отчет за " + i + " месяц не сходится с годовым.");
            } else {
                System.out.println("Отчет за " + i + " месяц сходится с годовым.");
            }
        }
    }
    public static void printYearCsv (){
        System.out.println("\n2021 год :");
        for (int i = 1; i <=3; i++){
            System.out.println("Доход за " + i +" месяц " + ": " + getYearHashMapByIsExpense(false).get(2021).get(i));
            System.out.println("Траты за " + i +" месяц " + ": " + getYearHashMapByIsExpense(true).get(2021).get(i));
        }
    }
    public static void printYearReport (){
        System.out.println("\n2021 год:");
        for (int i = 1; i <=3; i++){
            int totalIncome = getYearHashMapByIsExpense(false).get(2021).get(i) - getYearHashMapByIsExpense(true).get(2021).get(i);
            System.out.println("Общая прибыль за " + i +" месяц " + ": " + totalIncome);
        }
        System.out.println("Средний доход за 2021 год " + ": " + getAverage(false));
        System.out.println("Средний расход за 2021 год " + ": " + getAverage(true));
    }
    public static double getAverage (boolean isExpense){
        double average = 0;
        for (int i = 1; i <=3; i++){
            average += getYearHashMapByIsExpense(isExpense).get(2021).get(i);
        }
        return average/12;
    }
}
