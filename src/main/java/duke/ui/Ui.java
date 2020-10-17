package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * User interface class used for reading user inputs and output messages.
 */

public class Ui {
    private static Scanner s = new Scanner(System.in);

    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n" + drawLine());
    }

    public void displayExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return s.nextLine();
    }

    public void displayError(String message) {
        System.out.println(message);
    }

    public void displayAddTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.getTaskList().size() + " tasks in the list.");
    }

    public void displayMarkAsDone(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTask(index).toString());
    }

    public void displayDelete(TaskList tasks, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.getTask(index).toString());
        System.out.println("Now you have " + (tasks.getTaskList().size() - 1) + " tasks in the list.");
    }

    public String drawLine() {
        return("____________________________________________________________");
    }

    public void displaySavingMessage() {
        System.out.println("Saving task list...");
    }

    public void displayFind(TaskList tasks, String keyword) {
        System.out.println("Here are the matching tasks in your list:");
    }
}
