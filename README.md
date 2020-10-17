# User Guide

## Starting the program

Using Command Line
1. `Build` the project using IntelliJ
2. Open the `Terminal`/`Command Prompt`
3. `cd` into the project's `out/production/ip` directory
4. Type `java duke.Duke`, then `Enter` to execute
5. Now you can interact with the program through CLI

## Features 

### Listing all tasks : `list`

Shows a list of all the tasks, along with the task type (Todo, Deadline or Event), task description 
and date which is represented by `at` for `event` and `by` for `deadline`.  
Format: `list`

### Adding a ToDo task : `todo`

Adds a new ToDo task to the list of task.  
Format: `todo <description>`

Examples:
* `todo read book`  
Adds a new Todo task with the description `read book`.

### Adding a Deadline task : `deadline`

Adds a new Deadline task to the task list.  
Format: `deadline <description> /by <date>`

Put a `/by ` to indicate that the following string is the deadline to complete the task by.   

Examples:
* `deadline Complete ip project /by <date>`  
Adds a new task with the description `Complete ip project` and deadline `<date>`.

### Adding an event task : `event`

Adds a new Event task to the task list.  
Format: `event <description> /at <date>`

Put a `/at ` to indicate that the following string is the date of the event.   

Examples:
* `event Career fair /at <date>`  
Adds a new task with the description `Career fair` on `<date>`.


### Marking a task as done : `done`

Marks a task as done.  
Format: `done <index>`

Indicate the `<index>` of the task to mark as done.

Examples:
* `done 1`  
Marks the first item in the list as done.

### Deleting a task : `delete`

Deletes a task from the task list.  
Format: `delete <index>`

Indicate the `<index>` of the task to be deleted.

Examples:
* `delete 1`  
Deletes the first item in the task list.

### Finding a task : `find`

Find tasks from the task list containing the keyword given.  
Format: `find <String>`

Indicate the `<String>` which is the keyword to be searched in the task list.

Examples:
* `find book`  
Searches the task list for any task description containing the keyword `book` and prints the task(s) out.

### Exiting the program : `bye`

Exits the program and saves the task list to a local storage text file.  
Format: `bye`