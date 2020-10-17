package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(){
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list){
        this.list = list;
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return this.list;
    }

    public void deleteTask(int index) {
        this.list.remove(index);
    }

    public  ArrayList findTasks(String keyword) {
        int searchSize = 0;
        ArrayList<String> searchResult = new ArrayList<String>();
        String inspectItem;
        for (int i = 0; i < this.list.size(); i++){
            inspectItem = this.list.get(i).getDescription();
            if (inspectItem.contains(keyword)){
                searchResult.add(inspectItem);
                searchSize++;
            }
        }
        for (int j = 0; j < searchSize; j++) {
            System.out.println(" " + Integer.toString(j + 1) + "." + searchResult.get(j));
        }
        return searchResult;
    }

    public void addTask(Task task){
        this.list.add(task);
    }

    public void doneTask(int index){
        this.list.get(index).setIsDone();
    }

    public void printList() {
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
