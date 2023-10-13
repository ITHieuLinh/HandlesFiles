package DataAccess;

import common.Library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import model.Person;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class PersonDAO {

    private static PersonDAO instance = null;
    Library l;

    public PersonDAO() {
        l = new Library();
    }

    public static PersonDAO Instance() {
        if (instance == null) {
            synchronized (PersonDAO.class) {
                if (instance == null) {
                    instance = new PersonDAO();
                }
            }
        }
        return instance;
    }

    public void findPersonInfo() {
        ArrayList<Person> lp = new ArrayList<>();
        String pathFile = l.checkInputPathFile("Enter path file: ");
        double money = l.checkInputDouble("Enter number");
        lp = getListPerson(pathFile, money);
        if (lp == null) {
            return;
        }
        printListPerson(lp);
    }

    public void copyNewFile() {
        String pathFileInput = l.checkInputPathFile("Enter Source: ");
        String pathFileOutput = l.checkInputPathFile("Enter new file name: ");
        String content = getNewContent(pathFileInput);
        System.out.println(content);
        writeNewContent(pathFileOutput, content);
    }

    public ArrayList<Person> getListPerson(String pathFile, double money) {
        ArrayList<Person> lp = new ArrayList<>();
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(pathFile))) {
            String line;
            while ((line = bufferReader.readLine()) != null) {
                String[] infoPerson = line.split(";");
                if (infoPerson.length >= 3 && getSalary(infoPerson[2]) > money) {
                    lp.add(new Person(infoPerson[0], infoPerson[1], getSalary(infoPerson[2])));
                }
            }
        } catch (IOException e) {
            System.err.println("Can't read file.");
            return null;
        }
        return lp;
    }

    public double getSalary(String salary) {
        double salaryResult = 0;
        try {
            salaryResult = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            salaryResult = 0;
        } finally {
            return salaryResult;
        }
    }

    public void printListPerson(ArrayList<Person> lp) {
        if (lp.isEmpty()) {
            System.out.println("No result");
            return;
        }
        System.out.printf("%-20s%-20s%-20s\n", "Name", "Address", "Money");
        for (Person person : lp) {
            System.out.printf("%-20s%-20s%-20.1f\n", person.getName(),
                    person.getAddress(), person.getMoney());
        }
        Collections.sort(lp);
        System.out.println("Max: " + lp.get(0).getName());
        System.out.println("Min: " + lp.get(lp.size() - 1).getName());
    }

    public String getNewContent(String pathFileInput) {
        HashSet<String> newContent = new HashSet<>();
        File file = new File(pathFileInput);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                newContent.add(line + " ");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Can’t read file");
        }
        StringBuilder content = new StringBuilder();
        for (String line : newContent) {
            content.append(line).append("\n");
        }
        return content.toString();
    }

    public void writeNewContent(String pathFileOutput, String content) {
        File file = new File(pathFileOutput);
        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file))) {
            String[] lines = content.split("\n");
            for (String line : lines) {
                bufferWriter.write(line);
                bufferWriter.newLine();
            }
            System.err.println("Write successful");
        } catch (IOException ex) {
            System.err.println("Can’t write file");
        }
    }

}
