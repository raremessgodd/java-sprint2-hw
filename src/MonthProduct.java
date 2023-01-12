public class MonthProduct {
    String itemName;
    Boolean isExpense;
    int quantity, sumOfOne;
    public MonthProduct(String productsName, Boolean productsIsExpense, int productsQuantity, int productsSumOfOne) {
        this.itemName = productsName;
        this.isExpense = productsIsExpense;
        this.quantity = productsQuantity;
        this.sumOfOne = productsSumOfOne;
    }
}
