import java.util.HashMap;

public class MonthlyReport {
    public static HashMap<Integer, HashMap<String, Integer>> getHashMapByIsExpense(boolean isExpense){
        HashMap<Integer, HashMap<String, Integer>> allMonthsReport = new HashMap<>();
        for (int monthNumber = 1; monthNumber <= 3; monthNumber++){
            HashMap<String, Integer> monthReport = new HashMap<>();
            String list = Main.readFileByName("resources/m.20210" + monthNumber + ".csv");
            String[] lines = new String[0];
            if (list != null){
                lines = list.split("\n");
            }
            if (isExpense){
                writeReportInHashMap(lines, monthReport, "TRUE");
            }
            else{
                writeReportInHashMap(lines, monthReport, "FALSE");
            }
            allMonthsReport.put(monthNumber, monthReport);
        }
        return allMonthsReport;
    }
    public static void writeReportInHashMap (String[] lines, HashMap<String, Integer> monthReport, String isExpense){
        for (int i = 1; i < lines.length; i++){
            String line = lines[i];
            String[] columns = line.split(",");
            if (columns[1].equals(isExpense)){
                int price = Integer.parseInt(columns[2]) * Integer.parseInt(columns[3]);
                monthReport.put(columns[0], price);
            }
        }
    }
    public static void printAllMonthsCsv (){
        for (int i = 1; i <=3; i++){
            System.out.println("\nМесяц №" + i + " :");
            for (String itemName: getHashMapByIsExpense(true).get(i).keySet()){
                System.out.println(itemName + ": " + getHashMapByIsExpense(true).get(i).get(itemName));
            }
            for (String itemName: getHashMapByIsExpense(false).get(i).keySet()){
                System.out.println(itemName + ": " + getHashMapByIsExpense(false).get(i).get(itemName));
            }
        }
    }
    public static void monthReport(){
        for (int i = 1; i <= 3; i++){
            if (i == 1) {
                System.out.println("\nЯнварь:");
            }
            if (i == 2){
                System.out.println("\nФевраль:");
            }
            if (i == 3){
                System.out.println("\nМарт:");
            }
            int maxIncome = getHashMapByIsExpense(false).get(i).get(geMaxIncomeOrExpense(false, i));
            System.out.println("Самый прибыльный товар: " + geMaxIncomeOrExpense(false, i) + " - " + maxIncome);
            int maxExpense = getHashMapByIsExpense(true).get(i).get(geMaxIncomeOrExpense(true, i));
            System.out.println("Самая большая трата: " + geMaxIncomeOrExpense(true, i) + " - " + maxExpense);
        }
    }
    public static String geMaxIncomeOrExpense (boolean isExpense, int i){
        String maxExpenseItemName = null;
        int maxExpense = 0;
        for (String itemName : getHashMapByIsExpense(isExpense).get(i).keySet()) {
            if (getHashMapByIsExpense(isExpense).get(i).get(itemName) > maxExpense) {
                maxExpense = getHashMapByIsExpense(isExpense).get(i).get(itemName);
                maxExpenseItemName = itemName;
            }
        }
        return maxExpenseItemName;
    }
    public static int getMonthIncomeOrExpense (boolean isExpense, int i){
        int monthIncomeOrExpense = 0;
        for (String itemName : MonthlyReport.getHashMapByIsExpense(isExpense).get(i).keySet()) {
            monthIncomeOrExpense += MonthlyReport.getHashMapByIsExpense(isExpense).get(i).get(itemName);
        }
        return monthIncomeOrExpense;
    }
}
