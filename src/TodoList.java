//    Klasse TodoListe
public class TodoList {
    //            aufgaben
    public Task[] tasks;
    private int nextTaskIndex = 0;

    public TodoList() {
    }

    public TodoList(int size) {
        this.tasks = new Task[size];
    }

    public static TodoList createEmptyList(int size) {
        return new TodoList(size);
    }

    /**
     * Adds a new {@link Task} with the given {@link Task#description description}
     * to the end of the {@link TodoList todoList}'s {@link TodoList#tasks} array if there still has empty space.
     * Of course, the {@link Task} shouldn't be {@link Task#done done} yet.
     * Returns {@code true} if the {@link Task} could be inserted, {@code false} if not.
     * There must be no empty spaces between {@link TodoList#tasks} inside the array,
     * i.e., the {@link TodoList}'s {@link TodoList#tasks} array must be filled "consistently" from the beginning!
     *
     * @param todoList    The {@link TodoList} where the new {@link Task} is being added.
     * @param description The {@link Task#description description} of the new {@link Task}.
     * @return {@code true} if the {@link Task} could be inserted, {@code false} if not.
     */
    //         aufgabeHinzufuegen(TodoListe liste, String beschreibung)
    public static boolean addTask(TodoList todoList, String description) {
        if (todoList.nextTaskIndex < todoList.tasks.length) {
            Task task = new Task(description, false);
            todoList.tasks[todoList.nextTaskIndex] = task;
            todoList.nextTaskIndex++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the current number of {@link #tasks} inside the {@link TodoList todoList}.
     * If <code>onlyDone</code> is <code>true</code>, then only completed {@link #tasks} should be counted.
     * If <code>onlyDone</code> is <code>false</code>, then all tasks should be counted.
     *
     * @param todoList The {@link TodoList} containing {@link #tasks} being counted.
     * @param onlyDone The <code>boolean</code> value indicating
     *                 whether this method should count only {@link Task#done done} {@link #tasks}.
     * @return the number of {@link #tasks} corresponding to the counting rule.
     */
    //                 gibAnzahl(TodoListe liste, boolean nurErledigte)
    public static int countTasks(TodoList todoList, boolean onlyDone) {
        int count = 0;
        for (Task task : todoList.tasks) {
            if (task != null) {
                if (onlyDone) {
                    if (task.done) {
                        count++;
                    }
                } else {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * <p>
     * Display all details of a {@link TodoList}. The output should be placed on the console.
     * </p>
     *
     * <p>
     * Started by displaying details of each {@link Task} on each line.
     * Use the indicator <code>[ ]</code> to show that the {@link Task} is not yet {@link Task#done done}.
     * And use the indicator <code>[*]</code> to show that the {@link Task} is {@link Task#done done}.
     * After the completion indicator,
     * add a single space followed by the {@link Task} {@link Task#description description},
     * all on the same line.
     * </p>
     * <p>
     * For example,
     * <br /><br />
     * <code>
     * [x] OOP task-1
     * </code>
     * <br /><br />
     * </p>
     *
     * <p>
     * After the details of every task have been displayed,
     * start displaying the overall progress of the to-do list.
     * On a new line, display the percentage of the tasks that have been done (rounded down to a whole number).
     * </p>
     * <p>
     * For example,
     * <br /><br />
     * <code>
     * Erledigt: 50%
     * </code>
     * <br /><br />
     * </p>
     *
     * <p>
     * On the last line, display a progress bar indicating the percentage of tasks that have been done.
     * The total width of the progress bar must correspond to the parameter progressBarWidthInChars.
     * </p>
     * <p>
     * For example,
     * <br /><br />
     * <code>
     * Erledigt: [=========]
     * </code>
     * <br /><br />
     * </p>
     *
     * <p>
     * Below is an example output corresponding to progressBarWidthInChars = 6.
     * <br /><br />
     * <code>
     * [x] OOP-Aufgabe-1 <br />
     * [ ] OOP-Aufgabe-2 <br />
     * Erledigt: 50% <br />
     * Erledigt: [===&nbsp;&nbsp;&nbsp;]
     * </code>
     * <br /><br />
     * </p>
     *
     * @param todoList The {@link TodoList} being displayed.
     * @param progressBarWidthInChars The width of the progress bar being displayed.
     */
    //                ausgabe(TodoListe liste, int progressBarBreiteInZeichen)
    public static void output(TodoList todoList, int progressBarWidthInChars) {
        for (Task task : todoList.tasks) {
            if (task != null) {
                if (task.done) {
                    System.out.println("[x] " + task.description);
                } else {
                    System.out.println("[ ] " + task.description);
                }
            }
        }
        int allTasksCount = countTasks(todoList, false);
        int doneTasksCount = countTasks(todoList, true);
        ProgressBar progressBar = new ProgressBar(allTasksCount, doneTasksCount);
        int percent = ProgressBar.getPercent(progressBar);
        String progress = ProgressBar.getProgressBar(progressBar, progressBarWidthInChars);
        System.out.printf("Erledigt: %s%%\n", percent);
        System.out.printf("Erledigt: %s\n", progress);
    }

    public static void removeTask(TodoList list, int index) {
        Task[] tasks = list.tasks;
        if (index < tasks.length && tasks[index] != null) {
            tasks[index] = null;
            for (int i = index + 1; i < tasks.length; i++) {
                tasks[i - 1] = tasks[i];
            }
        }
    }

    /**
     * Marks the {@link Task} at the given index
     * in the {@link TodoList todoList}'s {@link #tasks} array as done.
     * If the index is outside the {@link #tasks} array or there is no {@link Task} at the index,
     * this method should do nothing.
     *
     * @param todoList The {@link TodoList} containing the {@link #tasks} array.
     * @param index    The index of the {@link #tasks} array inside the {@link TodoList todoList}
     *                 indicating the {@link Task} being marked as {@link Task#done done}.
     */
    //        markiereAlsErledigt(TodoListe liste, int index)
    public static void markAsDone(TodoList todoList, int index) {
        Task[] tasks = todoList.tasks;
        if (index < tasks.length && tasks[index] != null) {
            Task task = tasks[index];
            task.done = true;
        }
    }
}
