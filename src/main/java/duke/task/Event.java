package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
