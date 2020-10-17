//Entirely refurbishing Duke to OOP based in the nxt version :)
package duke;

import duke.DukeException;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static boolean isExit = false;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.displayError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run(){
        ui.displayWelcome();

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(ui.drawLine());
                Parser.parse(fullCommand,tasks, ui, storage);
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            } finally{
                System.out.println(ui.drawLine());
            }
        }
        ui.displayExit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
