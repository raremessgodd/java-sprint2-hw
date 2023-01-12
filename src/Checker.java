public class Checker {
    public static void check(){

        for (int yearNumber : FileReader.yearReports.keySet()){
            for (int monthNumber : FileReader.allMonthlyReports.keySet()){

                int yearFalseValue = getYearValue(yearNumber, monthNumber, false);
                int yearTrueValue = getYearValue(yearNumber, monthNumber, true);
                int monthFalseValue = getMonthValue(monthNumber, false);
                int monthTrueValue = getMonthValue(monthNumber, true);

                if (monthTrueValue != yearTrueValue || monthFalseValue != yearFalseValue){
                    System.out.println("Отчет за " + monthNumber + " месяц не совпадает с годовым.");
                }
                else{
                    System.out.println("Отчет за " + monthNumber + " месяц совпадает с годовым.");
                }
            }
        }
    }

    public static int getMonthValue (int monthNumber, boolean isExpense){
        int value = 0;
        for (MonthProduct product : FileReader.allMonthlyReports.get(monthNumber).products){
            if (product.isExpense.equals(isExpense)){
                value += product.quantity * product.sumOfOne;
            }
        }
        return value;
    }

    public static int getYearValue (int yearNumber, int monthNumber, boolean isExpense){
        int value = 0;
        for (YearInformation report : FileReader.yearReports.get(yearNumber).yearlyReports){
            if (report.isExpense.equals(isExpense) && report.month == monthNumber){
                value = report.amount;
            }
        }
        return value;
    }
}
