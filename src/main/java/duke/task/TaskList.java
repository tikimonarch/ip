package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(){
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList list){
        this.list = list;
    }

    public void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + this.list.get(index));
        this.list.remove(index);
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
    }

    public void addTask(Task task){
        this.list.add(task);
    }

    public void doneTask(int index){
        this.list.get(index).setIsDone();
    }

    public void printList(ArrayList tasks) {
        if (this.list.size() == 0) {
            System.out.println("There are currently no tasks.");
        } else {
            System.out.print("Here are the tasks in your list:\n");
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println(" " + Integer.toString(i + 1) + "." + this.list.get(i));
            }
        }
    }
}
