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
        return 0;
    }

    public static void output(TodoList list, int progressBarFullLength) {

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
