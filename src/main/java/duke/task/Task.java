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

    public char statusIsDone(){
        return (this.isDone?'✓':'✗');
    }

    public void setIsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.statusIsDone() + " " + this.getDescription();
    }
}
