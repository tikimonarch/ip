//Entirely refurbishing Duke to OOP based in the nxt version :)
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {
    public static String border = "____________________________________________________________\n";
    public  static int size = 0;
    public  static int searchSize = 0;
    public static ArrayList<String> list = new ArrayList<String>();
    public static ArrayList<String> searchResult = new ArrayList<String>();

    public static void findList(String input) {
        String inspectItem;
        for (int i = 0; i < size; i++){
            inspectItem = list.get(i);
            if (inspectItem.contains(input)){
                searchResult.add(inspectItem);
                searchSize++;
            }
        }
        System.out.print(border + "Here are the matching tasks in your list:\n");
        for (int j = 0; j < searchSize; j++) {
            System.out.println(" " + Integer.toString(j + 1) + "." + searchResult.get(j));
        }
        System.out.print(border);
        searchSize = 0;
    }

    public static void printList() {
        System.out.print(border + "Here are the tasks in your list:\n");
        for (int i = 0; i < size; i++) {
            System.out.println(" " + Integer.toString(i + 1) + "." + list.get(i));
        }
        System.out.print(border);
    }

    public static void delete(int index) {
        System.out.println(border + "Noted. I've removed this task:");
        System.out.println("  " + list.get(index));
        list.remove(index);
        size--;
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.print(border);
    }

    public static void main(String[] args) throws EmptyTodoException, IncorrectInputException, Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.print(border + " Hello! I'm Duke\n" + " What can I do for you?\n" + border);
        String command;
        String temp;
        String[] processInput = new String[2];
        String tag = "[ ]";
        File data = new File("List.txt");
        if (data.exists()) {
            Scanner load = new Scanner(data);
            while (load.hasNextLine()) {
                list.add(load.nextLine());
                size++;
            }
        }
        while (true) {
            try {
                Scanner read = new Scanner(System.in);
                command = read.nextLine();
                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    if (size == 0) {
                        System.out.print(border + "Empty\n" + border);
                    } else {
                        printList();
                    }
                    continue;
                } else if (command.contains("done")) {
                    processInput = command.split(" ", 2);
                    temp = list.get(Integer.valueOf(processInput[1]) - 1).replace("[\u2718]", "[\u2713]");
                    list.set(Integer.valueOf(processInput[1]) - 1, temp);
                    System.out.print(border + " Nice! I've marked this task as done:\n");
                    System.out.println("  " + list.get(Integer.valueOf(processInput[1]) - 1));
                    System.out.print(border);
                    continue;
                } else if (command.contains("delete")) {
                    processInput = command.split(" ", 2);
                    delete(Integer.valueOf(processInput[1]) - 1);
                } else if (command.contains("find")) {
                    processInput = command.split(" ", 2);
                    findList(processInput[1]);
                }else {
                    if (command.contains("todo")) {
                        if (command.equals("todo")){
                            throw new EmptyTodoException();
                        }
                        tag = "[T]";
                        command = command.replaceAll("todo ", "");
                        list.add(tag + "[\u2718] " + command);
                    } else if (command.contains("deadline")) {
                        tag = "[D]";
                        command = command.replaceAll("deadline ", "");
                        processInput = command.split(" /by", 2);
                        list.add(tag + "[\u2718] " + processInput[0] + " (by:" + processInput[1] + ")");
                    } else if (command.contains("event ")) {
                        tag = "[E]";
                        command = command.replaceAll("event ", "");
                        processInput = command.split(" /at", 2);
                        list.add(tag + "[\u2718] " + processInput[0] + " (at:" + processInput[1] + ")");
                    } else {
                        throw new IncorrectInputException();
                    }
                    System.out.println(border + " Got it. I've added this task:\n  " + list.get(size));
                    size++;
                    System.out.println(" Now you have " + Integer.toString(size) + " tasks in the list.\n" + border);
                }
            } catch (EmptyTodoException e) {
                System.out.print(border);
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.print(border);
            } catch (IncorrectInputException e) {
                System.out.print(border);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.print(border);
            }
        }
        FileWriter saveFile = new FileWriter("List.txt");
        for (String item : list){
            saveFile.write(item+ "\n");
        }
        saveFile.close();
        System.out.print(border + " Bye. Hope to see you again soon!\n" + border);
    }
}
