package controller;

import Repository.PersonRepository;
import view.Menu;

public class PersonController extends Menu<String> {

    static String[] mc = {"Find person info", "Copy Text to new file", "Exit"};
    PersonRepository program;

    public PersonController() {
        super("       File Processing", mc);
        program = new PersonRepository();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                program.findPersonInfo();
                break;
            case 2:
                program.copyNewFile();
                break;
            case 3:
                System.exit(0);

        }

    }
}
