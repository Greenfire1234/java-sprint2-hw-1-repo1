
// business object classes
// parse csv
// menu
// print monthly reports


// main goal: monthly vs. yearly report verification
// -- yearly report + yearly report item
// --   toString
// --   csvParse
// --   *print

import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        Menu m = new Menu();
        m.run();

        /*
        File f1=new File("resources\\m.202101.csv");
        System.out.println("hello");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
         */

        // testMonthlyReport();
        // testYearlyReport();
        // testVerify();
    }

    private static void testMonthlyReport() {
        // https://stackoverflow.com/questions/18551251/how-to-open-a-text-file
        File f1=new File("resources\\m.202101.csv");
        System.out.println(f1.canRead());

        MonthlyReport mr;
        try {
            mr = MonthlyReport.parseCsv(f1, 1);
            System.out.println(mr);
            mr.printReport();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testYearlyReport() {
        File f1=new File("resources\\y.2021.csv");
        System.out.println(f1.canRead());

        YearlyReport yr;
        try {
            yr = YearlyReport.parseCsv(f1, 2021);
            System.out.println(yr);
            yr.printReport();



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testVerify() {
        ArrayList<MonthlyReport> mrs = new ArrayList<>();

        int year = 2021;
        int month_start = 1;
        int month_end = 3;

        String year_file = String.format("resources/y.%d.csv", year);
        YearlyReport yr = null;
        try {
            yr = YearlyReport.parseCsv(new File(year_file), year);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(yr);

        for (int i = month_start; i <= month_end; i++) {
            String month_file = String.format("resources/m.%d%02d.csv", year, i);
            try {
                MonthlyReport mr =
                        MonthlyReport.parseCsv(new File(month_file), i);
                mrs.add(mr);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        for (MonthlyReport mr :
                mrs) {
            System.out.println(mr);
        }


        Accounting.verify(mrs, yr);

    }

}

