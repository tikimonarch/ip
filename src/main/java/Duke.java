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
        while (true) {
            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
            command = read.readLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.print(border + " " + command + "\n" + border);
        }
        System.out.print(border + " Bye. Hope to see you again soon!\n" + border);
    }
}
