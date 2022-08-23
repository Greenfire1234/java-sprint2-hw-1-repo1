import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
}
