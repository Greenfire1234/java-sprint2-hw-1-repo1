import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MonthlyReport {
    private MonthlyReport(String name) {
        this.items = new ArrayList<>();
        this.name = name;
    }

    public ArrayList<MonthlyReportItem> getItems() {
        return items;
    }
    private ArrayList<MonthlyReportItem> items;

    public String getName() {
        return name;
    }

    private String name;

    public static MonthlyReport parseCsv(File filename, String name)
            throws IOException {
        MonthlyReport res = new MonthlyReport(name);
        try (BufferedReader br =
                     new BufferedReader(new FileReader(filename))) {
            String line;

            // skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                // System.out.println(fields);

                String item_name = fields[0];
                boolean is_expense = fields[1].equals("TRUE");
                int quantity = Integer.parseInt(fields[2]);
                int sum_of_one = Integer.parseInt(fields[3]);

                res.items.add(new MonthlyReportItem(
                        item_name, is_expense, quantity, sum_of_one
                ));
            }
        }

        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MonthlyReportItem mri: items) {
            sb.append(String.format("%20s%10s%10d%10d%10d\n",
                    mri.getItem_name(),
                    mri.isIs_expense(),
                    mri.getQuantity(),
                    mri.getSum_of_one(),
                    mri.value()));
        }
        return sb.toString();
    }

    private MonthlyReportItem getMax(boolean is_expense) {
        // уже видели хотя бы одну строку категории is_expense
        boolean found = false;

        // лучший кандидат из тех, что видели до сих пор
        MonthlyReportItem max_item = null;

        for (MonthlyReportItem mri : items) {
            if (mri.isIs_expense() != is_expense)
                continue;

            if (!found) {
                max_item = mri;
                found = true;
            } else {
                if (mri.value() > max_item.value())
                    max_item = mri;
            }
        }

        return max_item;
    }

    public void printReport() {
        System.out.println(name);
        MonthlyReportItem largestRevenue = getMax(false);
        System.out.printf("Самый прибыльный товар: %s",
                largestRevenue.getItem_name());
        System.out.printf(" Стоимость: %d\n",
                largestRevenue.value());

        MonthlyReportItem largestExpense = getMax(true);
        System.out.printf("Самая большая трата: %s",
                largestExpense.getItem_name());
        System.out.printf(" Стоимость: %d",
                largestExpense.value());

    }
}
