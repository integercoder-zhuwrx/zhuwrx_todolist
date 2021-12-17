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

    public static int countTasks(TodoList list, boolean onlyDone) {
        int count = 0;
        for (Task task : list.tasks) {
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

    public static void output(TodoList list, int widthInCharacters) {
        for (Task task : list.tasks) {
            if (task != null) {
                if (task.done) {
                    System.out.println("[x] " + task.description);
                } else {
                    System.out.println("[ ] " + task.description);
                }
            }
        }
        int allTasksCount = countTasks(list, false);
        int doneTasksCount = countTasks(list, true);
        ProgressBar progressBar = new ProgressBar(allTasksCount, doneTasksCount);
        int percent = ProgressBar.getPercent(progressBar);
        String progress = ProgressBar.getProgressBar(progressBar, widthInCharacters);
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

    public static void markAsDone(TodoList list, int index) {
        Task[] tasks = list.tasks;
        if (index < tasks.length && tasks[index] != null) {
            Task task = tasks[index];
            task.done = true;
        }
    }
}
