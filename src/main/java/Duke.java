//Entirely refurbishing Duke to OOP based in the nxt version :)
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) throws EmptyTodoException, IncorrectInputException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String border = "____________________________________________________________\n";
        System.out.print(border + " Hello! I'm Duke\n" + " What can I do for you?\n" + border);
        String command;
        int size = 0;
        String temp;
        String tag = "[ ]";
        String[] temp2 = new String[2];
        String[] list = new String[100];
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
                        System.out.print(border + "Here are the tasks in your list:\n");
                        for (int i = 0; i < size; i++) {
                            System.out.println(" " + Integer.toString(i + 1) + "." + list[i]);
                        }
                        System.out.print(border);
                    }
                    continue;
                } else if (command.contains("done")) {
                    String[] arrOfStr = command.split(" ", 2);
                    temp = list[Integer.valueOf(arrOfStr[1]) - 1].replace('✗', '✓');
                    list[Integer.valueOf(arrOfStr[1]) - 1] = temp;
                    System.out.print(border + " Nice! I've marked this task as done:\n");
                    System.out.println("  " + list[Integer.valueOf(arrOfStr[1]) - 1]);
                    System.out.print(border);
                    continue;
                } else {
                    if (command.contains("todo")) {
                        if (command.equals("todo")){
                            throw new EmptyTodoException();
                        }
                        tag = "[T]";
                        command = command.replaceAll("todo ", "");
                        list[size] = tag + "[✗] " + command;
                    } else if (command.contains("deadline")) {
                        tag = "[D]";
                        command = command.replaceAll("deadline ", "");
                        temp2 = command.split(" /by", 2);
                        list[size] = tag + "[✗] " + temp2[0] + " (by:" + temp2[1] + ")";
                    } else if (command.contains("event ")) {
                        tag = "[E]";
                        command = command.replaceAll("event ", "");
                        temp2 = command.split(" /at", 2);
                        list[size] = tag + "[✗] " + temp2[0] + " (at:" + temp2[1] + ")";
                    } else {
                        throw new IncorrectInputException();
                    }
                    System.out.println(border + " Got it. I've added this task:\n  " + list[size]);
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
        System.out.print(border + " Bye. Hope to see you again soon!\n" + border);
    }
}
