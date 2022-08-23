public class YearlyReportItem {
    private final int month;
    private final int amount;
    private final boolean is_expense;

    public int getMonth() {
        return month;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getIsExpense() {
        return is_expense;
    }

    public YearlyReportItem(int name, int amount, boolean is_expense) {
        this.month = name;
        this.amount = amount;
        this.is_expense = is_expense;
    }
}
