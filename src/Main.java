
// business object classes
// parse csv
// menu
// print monthly reports

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // Поехали!

        System.out.println("hello");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        // https://stackoverflow.com/questions/18551251/how-to-open-a-text-file
        File f1=new File("resources\\m.202101.csv");
        System.out.println(f1.canRead());

        MonthlyReport mr;
        try {
            mr = MonthlyReport.parseCsv(f1, "january");
            System.out.println(mr);
            mr.printReport();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

