public class TodoList {
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

    public static boolean addTask(TodoList list, String description) {
        if (list.nextTaskIndex < list.tasks.length) {
            Task task = new Task(description, false);
            list.tasks[list.nextTaskIndex] = task;
            list.nextTaskIndex++;
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
