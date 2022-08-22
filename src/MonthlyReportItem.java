public class MonthlyReportItem {
    private final String item_name;
    private final boolean is_expense;
    private final int quantity;
    private final int sum_of_one;


    public String getItem_name() {
        return item_name;
    }

    public boolean isIs_expense() {
        return is_expense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSum_of_one() {
        return sum_of_one;
    }

    public MonthlyReportItem(String item_name, boolean is_expense, int quantity, int sum_of_one) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
    }

    public int value() {
        return quantity * sum_of_one;
    }
}
