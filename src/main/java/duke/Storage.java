package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filepath) {
        this.filePath = filepath;
    }

    public ArrayList<Task> loadFile() throws DukeException, FileNotFoundException{
        ArrayList<Task> list = new ArrayList<>();

        File data = new File("List.txt");
        if (data.exists()) {
            Scanner load = new Scanner(data);
            while (load.hasNextLine()) {
                list.add(new Task(load.nextLine()));
            }
        }
        return list;
    }
    public void saveFile(TaskList list) throws IOException {
        FileWriter saveFile = new FileWriter("List.txt");
        for (int i = 0; i < list.size(); i++) {
            saveFile.write(list.get(i).getDescription()+ "\n");
        }
        saveFile.close();
    }
}
