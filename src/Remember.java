import java.util.Arrays;
import java.util.Scanner;

public class Remember {

    private static final String INPUT_MODE = "Выберите режим работы. (Ввод - A, Вывод - S, Выход - E): ";
    private static final String INPUT_DAY = "Выберите день недели. (Понедельник - 0, ... , Воскресенье - 6): ";
    private static final String INPUT_TASK = "Введите следующую задачу: ";
    private static final String YES_NEXT_TASK = "Желаете ввести следующую задачу (Y/N): ";
    private static final String FAULT_MODE = "Неверный режим!!! Повторите ввод.";
    private static final String FAULT_DAY = "Неверный день недели!!! Повторите ввод.";
    private static final String GOODBYE = "Завершение программы.";
    private static final String NO_TASKS = "У Вас нет запланированых задач на эту неделю.";
    private static final String NO_TASKS_DAY = "У Вас нет запланированых задач на этот день";

    Scanner scanner;
    String[][] remember = new String[7][0];
    int countTasks = 0;
    boolean work = true;
    char mode;

    public void run() {
        scanner = new Scanner(System.in);
        while (work) {
            mode = choiceMode();
            if (mode == 'E' || mode == 'e') {
                work = false;
            } else if (mode == 'S' || mode == 's') {
                showTasks();
            } else if (mode == 'A' || mode == 'a') {
                addTasks();
            }
        }
        System.out.println(GOODBYE);
        scanner.close();
    }

    private void addTasks() {
        String newTask;
        String[] newDayTasks;
        char addingDay;
        boolean addingTask = true;
        addingDay = choiceDay();
        int currentNumTask = remember[addingDay - '0'].length;
        while (addingTask) {
            newDayTasks = new String[++currentNumTask];
            if (currentNumTask > 1) {
                newDayTasks = Arrays.copyOf(remember[addingDay - '0'], currentNumTask);
            }
            System.out.println(INPUT_TASK);
            newTask = scanner.nextLine();
            newDayTasks[currentNumTask - 1] = newTask;
            countTasks++;
            remember[addingDay - '0'] = newDayTasks;
            addingTask = choiceNextTask();
        }
    }

    private boolean choiceNextTask() {
        System.out.println(YES_NEXT_TASK);
        char next = scanner.next().charAt(0);
        scanner.nextLine();// Считываем перевод строки
        return next == 'Y' || next == 'y';
    }

    private void showTasks() {
        char curDay;
        int numberTasks;
        if (countTasks == 0) {
            System.out.println(NO_TASKS);
        } else {
            curDay = choiceDay();
            numberTasks = remember[curDay - '0'].length;
            if (numberTasks == 0) {
                System.out.println(NO_TASKS_DAY);
            } else {
                for (int i = 0; i < numberTasks; i++) {
                    System.out.println(" - " + remember[curDay - '0'][i]);
                }
            }
        }
    }

    private char choiceDay() {
        char day;
        boolean faultDay = true;
        do {
            System.out.println(INPUT_DAY);
            day = scanner.next().charAt(0);
            switch (day) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6': {
                    faultDay = false;
                    break;
                }
                default: {
                    System.out.println(FAULT_DAY);
                }
            }
        } while (faultDay);
        scanner.nextLine();// Считываем перевод строки
        return day;
    }

    private char choiceMode() {
        char operation;
        boolean faultOperation = true;
        do {
            System.out.println(INPUT_MODE);
            operation = scanner.next().charAt(0);
            switch (operation) {
                case 's':
                case 'S':
                case 'E':
                case 'e':
                case 'A':
                case 'a': {
                    faultOperation = false;
                    break;
                }
                default: {
                    System.out.println(FAULT_MODE);
                }
            }
        } while (faultOperation);
        return operation;
    }
}

