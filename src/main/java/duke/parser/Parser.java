package duke.parser;

import duke.Duke;
import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class Parser {
    /**
     * Returns the <code>command</code> of a user input.
     * @param userInput user input.
     * @return First part of the user input, which is the command word.
     */
    private static String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    /**
     * Returns the <code>Description</code> of a <code>Task</code> from the user input.
     * @param userInput user input.
     * @return <code>Description</code> part of the user input.
     * @throws DukeException when the user input is of a wrong format.
     */
    private static String getTaskDescription(String userInput) throws DukeException {
        String command = getCommand(userInput);

        if (userInput.split(command).length < 2
                || userInput.split(command)[1].equals(" ")) {
            throw new DukeException(":( OOPS!!! Missing task description!");
        } else {
            switch (command) {
            case "todo":
                return userInput.substring(userInput.indexOf(' ') + 1);
            case "deadline":
                if (!userInput.contains("/by ") || userInput.contains("deadline /by")) {
                    throw new DukeException(":( OOPS!!! Missing or incorrect /by statement");
                }
                return userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf("/by ") - 1);
            case "event":
                if (!userInput.contains("/at ") || userInput.contains("deadline /at")) {
                    throw new DukeException(":( OOPS!!! Missing or incorrect /at statement");
                }
                return userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf("/at ") - 1);
            default:
                return null;
            }
        }
    }

    /**
     * Returns the <code>taskDate</code> of a <code>Task</code> from the user input.
     * @param userInput user input.
     * @return <code>taskDate</code> part of the user input.
     * @throws DukeException when the user input is of a wrong format.
     */
    private static LocalDate getTaskDate(String userInput) throws DukeException {
        String taskDateString;
        switch (getCommand(userInput)) {
        case "deadline":
            taskDateString = userInput.split("/by ")[1];
            try {
                return LocalDate.parse(taskDateString);
            } catch (Exception e) {
                throw new DukeException("Date specified should be of YYYY-MM-DD format!");
            }
        case "event":
            taskDateString = userInput.split("/at ")[1];
            try {
                return LocalDate.parse(taskDateString);
            } catch (Exception e) {
                throw new DukeException("Date specified should be of YYYY-MM-DD format!");
            }
        case "occur":
            taskDateString = userInput.split(" ")[1];
            try {
                return LocalDate.parse(taskDateString);
            } catch (Exception e) {
                throw new DukeException("Date specified should be of YYYY-MM-DD format!");
            }
        default:
            return null;
        }
    }

    /**
     * Returns the <code>commandIndex</code> of a user input, e.g. done 2 or delete 3.
     * Returns 2 or 3 respectively.
     * @param userInput user input.
     * @return <code>commandIndex</code> part of the user input.
     * @throws DukeException when the user input is of a wrong format.
     */
    private static int getCommandIndex(String userInput) throws DukeException {
        String command = getCommand(userInput);

        if (userInput.split(command).length < 2 || userInput.split(command)[1].equals(" ")) {
            throw new DukeException(":( OOPS!!! Missing index of task!");
        }
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(":( OOPS!!! No integer index detected!");
        }
    }

    /**
     * Returns the <code>targetString</code> when the find command is input.
     * @param userInput user input.
     * @return <code>String</code> of the targetString to be found.
     * @throws DukeException when the user input is of a wrong format.
     */
    public static String getParam(String userInput) throws DukeException {
        String command = getCommand(userInput);

        if (userInput.split(command).length < 2
                || userInput.split(command)[1].equals(" ")) {
            throw new DukeException(":( OOPS!!! Missing target!");
        } else {
            return userInput.substring(userInput.indexOf(' ') + 1);
        }
    }

    /**
     * Makes sense of the user input and returns the command object accordingly to the command given.
     * @param userInput user input.
     * @return <code>Command</code> of sub-type depending on the command given.
     * @throws DukeException when the program does not recognize the command given.
     */
    public static void parse(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (getCommand(userInput)) {
        case "bye":
            try {
                Duke.isExit = true;
                ui.displaySavingMessage();
                storage.saveFile(tasks);
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
            return;
        case "list":
            tasks.printList();
            return;
        case "todo":
            tasks.addTask(new Todo(getTaskDescription(userInput)));
            ui.displayAddTask(new Todo(getTaskDescription(userInput)),tasks);
            return;
        case "deadline":
            tasks.addTask(new Deadline(getTaskDescription(userInput), getTaskDate(userInput)));
            ui.displayAddTask(new Deadline(getTaskDescription(userInput), getTaskDate(userInput)),tasks);
            return;
        case "event":
            tasks.addTask(new Event(getTaskDescription(userInput), getTaskDate(userInput)));
            ui.displayAddTask(new Event(getTaskDescription(userInput), getTaskDate(userInput)), tasks);
            return ;
        case "done":
            tasks.doneTask(getCommandIndex(userInput));
            ui.displayMarkAsDone(tasks, getCommandIndex(userInput));
            return;
        case "delete":
            ui.displayDelete(tasks, getCommandIndex(userInput));
            tasks.deleteTask(getCommandIndex(userInput));
            return;
        case "find":
            ui.displayFind(tasks, getParam(userInput));
            tasks.findTasks(getParam(userInput));
            return;
        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means!");
        }
    }
}
