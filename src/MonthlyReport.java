import java.util.ArrayList;

public class MonthlyReport {

    ArrayList<MonthProduct> products = new ArrayList<>();

    public MonthlyReport(String path){
        String list = FileReader.readFileByName(path);
        String[] lines = new String[0];
        if (!list.equals("")){
            lines = list.split("\r?\n");
        }
        for (int i = 1; i < lines.length; i++){
            String line = lines[i];
            String[] columns = line.split(",");
            String productsName = columns[0];
            Boolean productsIsExpense = Boolean.parseBoolean(columns[1]);
            int productsQuantity = Integer.parseInt(columns[2]);
            int productsSumOfOne = Integer.parseInt(columns[3]);

            MonthProduct monthsProducts = new MonthProduct(productsName, productsIsExpense, productsQuantity, productsSumOfOne);
            products.add(monthsProducts);
        }
    }

    public static String getMaxItemName (int monthNumber, boolean isExpense){
        String maxItemName = null;
        int maxItemPrice = 0;
        for (MonthProduct product : FileReader.allMonthlyReports.get(monthNumber).products) {
            int itemPrice = product.sumOfOne * product.quantity;
            if (product.isExpense.equals(isExpense) && itemPrice > maxItemPrice){
                maxItemPrice = itemPrice;
                maxItemName = product.itemName;
            }
        }
        return maxItemName;
    }

    public static int getMaxSum (int monthNumber, boolean isExpense){
        int sum = 0;
        for (MonthProduct product : FileReader.allMonthlyReports.get(monthNumber).products){
            if (product.itemName.equals(getMaxItemName(monthNumber, isExpense))){
                sum = product.sumOfOne * product.quantity;
            }
        }
        return sum;
    }

    public static void monthReport(){
        for (int monthNumber : FileReader.allMonthlyReports.keySet()){
            if (monthNumber == 1) {
                System.out.println("\nЯнварь:");
            }
            if (monthNumber == 2){
                System.out.println("\nФевраль:");
            }
            if (monthNumber == 3){
                System.out.println("\nМарт:");
            }
            System.out.println("Самый прибыльный товар: " + getMaxItemName(monthNumber, false) + " - " + getMaxSum(monthNumber, false));
            System.out.println("Самая большая трата: " + getMaxItemName(monthNumber, true) + " - " + getMaxSum(monthNumber, true));
        }
    }
}
