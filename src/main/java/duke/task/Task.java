package duke.task;

public class Task {
    private boolean isDone;
    private final String description;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String statusIsDone(){
        return (this.isDone?"\u2713":"\u2717");
    }

    public void setIsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.statusIsDone() + "] "+ this.getDescription();
    }
}
