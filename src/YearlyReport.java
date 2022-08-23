import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class YearlyReport {
    public ArrayList<YearlyReportItem> getItems() {
        return items;
    }
    public int getYear() {
        return year;
    }
    public YearlyReport(int name) {
        this.items = new ArrayList<>();
        this.year = name;
    }
    private ArrayList<YearlyReportItem> items;
    private int year;

    public static YearlyReport parseCsv(File filename,int year)
            throws IOException {
        YearlyReport res = new YearlyReport(year);
        try (BufferedReader br =
                     new BufferedReader(new FileReader(filename))) {
            String line;

            // skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                // System.out.println(fields);

                int month = Integer.parseInt(fields[0]);
                int amount = Integer.parseInt(fields[1]);
                boolean is_expense = fields[2].equals("true");

                res.items.add(new YearlyReportItem(
                        month, amount, is_expense
                ));
            }
        }

        return res;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (YearlyReportItem yri: items) {
            sb.append(String.format("%5d%10d%10s\n",
                    yri.getMonth(),
                    yri.getAmount(),
                    yri.getIsExpense()));
        }
        return sb.toString();
    }


    public YearlyReportItem getMonthAccount(int month, boolean is_expense) {
        for (YearlyReportItem yri: items) {
            if (yri.getMonth() == month &&
                yri.getIsExpense() == is_expense)
                return yri;
        }
        return null;
    }

    public Set<Integer> getMonths() {
        Set<Integer> res = new HashSet<>();
        for (YearlyReportItem yri: items)
            res.add(yri.getMonth());

        return res;
    }
    public void printReport() {
        System.out.printf("Yearly Report (%d)\n",
                this.year);

        System.out.printf("%10s%15s\n",
                "month", "income");
        Set<Integer> months = this.getMonths();
        for (Integer m: months
             ) {
            YearlyReportItem m_revenue =
                getMonthAccount(m, false);
            YearlyReportItem m_expense =
                getMonthAccount(m, true);


            if (m_revenue == null ||
            m_expense == null) {
                System.out.printf("incomplete month (%d)!\n",
                        m);
            } else {
                int income = m_revenue.getAmount()
                        - m_expense.getAmount();
                System.out.printf("%10d%15d\n",
                        m, income);
            }
        }

        // average revenue / expense
        int sr = 0, se = 0;
        int nr = 0, ne = 0;

        for (YearlyReportItem yri: items) {
            if (yri.getIsExpense()) {
                se += yri.getAmount();
                ne += 1;
            } else {
                sr += yri.getAmount();
                nr += 1;
            }
        }

        double avg_revenue = (double) sr / nr;
        double avg_expense = (double) se / ne;

        System.out.printf("average revenue = %.2f\n", avg_revenue);
        System.out.printf("average expense = %.2f\n", avg_expense);

    }
}
