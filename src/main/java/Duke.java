import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Duke {
    public static void main(String[] args) throws IOException {
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
        String[] list = new String[100];
        while (true) {
            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
            command = read.readLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                if (size == 0) {
                    System.out.print(border + "Empty\n" + border);
                }
                else {
                    System.out.print(border + "Here are the tasks in your list:\n");
                    for (int i = 0; i < size; i++) {
                        System.out.println(" " + Integer.toString(i + 1) + "." + list[i]);
                    }
                    System.out.print(border);
                }
                continue;
            } else if (command.contains("done")){
                String[] arrOfStr = command.split(" ", 2);
                temp = list[Integer.valueOf(arrOfStr[1])-1].replace('✗','✓');
                list[Integer.valueOf(arrOfStr[1])-1] = temp;
                System.out.print(border + " Nice! I've marked this task as done:\n" );
                System.out.println("  " + list[Integer.valueOf(arrOfStr[1])-1]);
                System.out.print(border);
                continue;
            }
            list[size] = "[✗] " + command;
            size++;
            System.out.print(border + " added: " + command + "\n" + border);
        }
        System.out.print(border + " Bye. Hope to see you again soon!\n" + border);
    }
}
