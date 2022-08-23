import java.util.ArrayList;

public class Accounting {

    public static void verify(ArrayList<MonthlyReport> mrs,
                              YearlyReport yr) {
        // System.out.println("verification started...");
        boolean ok = true;

        for (MonthlyReport mr: mrs) {
            boolean res = verifyMonth(mr, yr);
            if (!res) {
                ok = false;
                System.out.printf("verification failed for %d\n",
                        mr.getMonth());
            }
        }
        if (ok) {
            System.out.println("verification completed successfully");
        }
    }

    private static boolean verifyMonth(MonthlyReport mr,
                                       YearlyReport yr) {
        if (!verifyMonthAccount(mr, yr, true)) {
            return false;
        }
        if (!verifyMonthAccount(mr, yr, false)) {
            return false;
        }
        return true;
    }

    private static boolean verifyMonthAccount(MonthlyReport mr,
        YearlyReport yr, boolean is_expense) {

        YearlyReportItem yri = yr.getMonthAccount(mr.getMonth(),
                is_expense);

        int mrTotal = mr.getAccountTotal(is_expense);
        int yrTotal = yri.getAmount();

        boolean res =  mrTotal == yrTotal;

        /*
        System.out.printf("verifying: m = %d, expense = %s\n",
                mr.getMonth(), is_expense);
        System.out.printf("mrTotal = %d\nyrTotal = %d\n",
                mrTotal, yrTotal);
        */

        return res;
    }
}
