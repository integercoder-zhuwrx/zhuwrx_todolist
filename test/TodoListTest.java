import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodoListTest {
    @Test
    void countTasks01() {
        TodoList list = TodoList.createEmptyList(5);
        TodoList.addTask(list, "");
        TodoList.addTask(list, "");
        TodoList.addTask(list, "");
        TodoList.addTask(list, "");
        TodoList.addTask(list, "");
        TodoList.markAsDone(list, 0);
        TodoList.markAsDone(list, 1);
        int allTasksCount = TodoList.countTasks(list, false);
        assertThat(allTasksCount).isEqualTo(5);
    }

    @Test
    void countTasks02() {
        TodoList list = TodoList.createEmptyList(5);
        TodoList.addTask(list, "");
        TodoList.addTask(list, "");
        TodoList.addTask(list, "");
        TodoList.addTask(list, "");
        TodoList.addTask(list, "");
        TodoList.markAsDone(list, 0);
        TodoList.markAsDone(list, 1);
        int doneTasksCount = TodoList.countTasks(list, true);
        assertThat(doneTasksCount).isEqualTo(2);
    }
}