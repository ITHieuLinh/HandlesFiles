package common;

import java.io.File;

import java.util.Scanner;
import model.Person;

public class Library {

    private final Scanner sc = new Scanner(System.in);

    public int getInt(String promt, int m, int n) {
        int a = -1;
        while (true) {
            System.out.print(promt + ": ");
            try {
                String s = sc.nextLine();
                a = Integer.parseInt(s);
                if (a >= m && a <= n) {
                    break;
                } else {
                    System.out.println("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
        return a;
    }

    public int getIntNoLimit(String promt) {
        int a = -1;
        while (true) {
            System.out.print(promt + ": ");
            try {
                String s = sc.nextLine();
                a = Integer.parseInt(s);
                return a;
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
    }

    public String inputString(String mes) {
        System.out.print(mes);
        if (mes.equalsIgnoreCase("100000000")) {
            return mes;
        }
        while (true) {
            String result = sc.nextLine();
            if (result.isEmpty()) {
                System.err.println("Not empty!");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }

    }

    public double checkInputDouble(String promt) {
        //loop until user input correct
        while (true) {
            System.out.print(promt + ": ");
            try {
                double result = Double.parseDouble(sc.nextLine());
                if (result > 0) {
                    return result;
                } else {
                    return 0;
                }
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    public String checkInputPathFile(String promt) {
        Person s = new Person();
        System.out.print(promt);
        while (true) {
            String result = sc.nextLine().trim();
            File file = new File(result);
//            String v = inputString("100000000");
            if (!file.exists() || !file.isFile()) {
                System.err.println("Path doesn't exist");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

}
