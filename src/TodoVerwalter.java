
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TodoVerwalter {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        TodoList list = new TodoList();

        do {
            //leer -> erzeuge leere liste
            //aufg -> neue aufgabe
            //erl  -> markiere index als erledigt
            //anz0 -> gibt anzahl aller aufgaben zurück
            //anz1 -> gibt anzahl der erledigten zurück

            //ausg -> alles ausgeben
            //entf -> entferne einen Eintrag

            // -------------------------------------------------------

            // empty -> create empty list
            // up -> new task
            // erl -> mark index as done
            // num0 -> returns the number of all tasks
            // num1 -> returns the number of completed items

            // out -> output everything
            // remove -> remove an entry
            switch (in.readLine()) {
                case "leer":
                    list = TodoList.createEmptyList(Integer.parseInt(in.readLine()));
                    System.out.println(list.tasks.length);
                    break;
                case "aufg":
                    // lese Beschreibung
                    System.out.println(TodoList.addTask(list, in.readLine()));
                    break;
                case "erl":
                    int index = Integer.parseInt(in.readLine());
                    TodoList.markAsDone(list, index);
                    System.out.println(list.tasks[index].done);
                    break;
                case "anz0":
                    System.out.println(TodoList.countTasks(list, false));
                    break;
                case "anz1":
                    System.out.println(TodoList.countTasks(list, true));
                    break;
                case "entf":
                    TodoList.removeTask(list, Integer.parseInt(in.readLine()));
                    break;
                case "ausg":
                    TodoList.output(list, Integer.parseInt(in.readLine()));
                    break;
                default:
                    return;
            }
        } while (true);
    }
}
