import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    ArrayList<MonthlyReport> mrs;
    YearlyReport yr;

    final int YEAR = 2021;
    final int MONTH_START = 1;
    final int MONTH_END = 3;

    public Menu() {
         mrs = null;
         yr = null;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int input = scanner.nextInt();

        while(true){
            if(input == 1){
                readMonthly();
            }
            else if (input == 2) {
                readYearly();
            }
            else if(input == 3){
                verify();
            }
            else if(input == 4){
                printMonthly();
            }
            else if(input == 5){
                printYearly();
            }
            else if(input == 6){
                break;
            }

            printMenu();
            input = scanner.nextInt();
        }
    }

    private void printYearly() {
        if (yr == null) {
            System.out.println("Yearly Report not loaded");
            return;
        }

        yr.printReport();
    }

    private void printMonthly() {
        if (mrs == null) {
            System.out.println("Monthly Reports not loaded");
            return;
        }
        for (MonthlyReport mr: mrs
        ) {
            mr.printReport();
        }
    }

    private void verify() {
        if (yr == null) {
            System.out.println("Yearly Report not loaded");
            return;
        }

        if (mrs == null) {
            System.out.println("Monthly Reports not loaded");
            return;
        }

        Accounting.verify(mrs, yr);
    }

    private void readYearly() {
        String year_file = String.format("resources/y.%d.csv", YEAR);
        try {
            yr = YearlyReport.parseCsv(new File(year_file), YEAR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void readMonthly() {
        mrs = new ArrayList<>();
        for (int i = MONTH_START; i <= MONTH_END; i++) {
            String month_file = String.format("resources/m.%d%02d.csv", YEAR, i);
            try {
                MonthlyReport mr =
                        MonthlyReport.parseCsv(new File(month_file), i);
                mrs.add(mr);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private void printMenu() {
        System.out.println( "1-Считать все месячные отчёты\n" +
                "2-Считать годовой отчёт\n" +
                "3-Сверить отчёты\n" +
                "4-Вывести информацию о всех месячных отчётах\n" +
                "5-Вывести информацию о годовом отчёте\n"+
                "6-Выход");
    }
}
