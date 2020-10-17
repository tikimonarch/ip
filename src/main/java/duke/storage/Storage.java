package duke.storage;

import duke.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    public ArrayList<Task> loadFile() throws DukeException{
        ArrayList<Task> list = new ArrayList<Task>();

        File data = new File("List.txt");
        if (data.exists()) {
            try {
                Scanner load = new Scanner(data);
                String next;
                String taskType;
                String isDoneStatus;
                String description;
                LocalDate taskDate;
                while (load.hasNextLine()) {
                    next = load.nextLine();
                    taskType = next.split("]")[0].substring(next.indexOf("[") + 1);
                    isDoneStatus = next.split("]")[1].substring(next.indexOf("[") + 1);
                    description = next.split("] ")[1];

                    switch (taskType) {
                    case "T":
                        list.add(new Todo(description));
                        break;
                    case "D":
                        taskDate = LocalDate.parse(description.split(" by: ")[1]);
                        list.add(new Deadline(description.split(" by: ")[0], taskDate));
                        break;
                    case "E":
                        taskDate = LocalDate.parse(description.split(" at: ")[1]);
                        list.add(new Event(description.split(" at: ")[0], taskDate));
                        break;
                    default:
                        throw new DukeException("There is something wrong with the format!");
                    }
                    if (isDoneStatus.equals("\u2713")) {
                        list.get(list.size() - 1).setIsDone();
                    }
                }
            }
            catch (FileNotFoundException e) {
                throw new DukeException(e.getMessage());
            }
        }
        return list;
    }
    public void saveFile(TaskList list) throws IOException {
        FileWriter saveFile = new FileWriter("List.txt");
        for (int i = 0; i < list.getTaskList().size(); i++) {
            if (list.getTask(i) instanceof Todo) {
                saveFile.write("[T]" + "[" + list.getTask(i).statusIsDone()
                        + "] " + list.getTask(i).getDescription());
            } else if (list.getTask(i) instanceof Deadline) {
                saveFile.write("[D]" + "[" + list.getTask(i).statusIsDone()
                        + "] " + list.getTask(i).getDescription() + " by: "
                        + ((Deadline) list.getTask(i)).getBy());
            } else if (list.getTask(i) instanceof Event) {
                saveFile.write("[E]" + "[" + list.getTask(i).statusIsDone()
                        + "] " + list.getTask(i).getDescription() + " at: "
                        + ((Event) list.getTask(i)).getAt());
            }
            saveFile.write(System.lineSeparator());
        }
        saveFile.close();
    }
}
