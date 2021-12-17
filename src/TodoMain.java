
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TodoMain {
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

            // leer -> empty  -> create empty list
            // aufg -> up     -> new task
            // erl  -> erl    -> mark index as done
            // anz0 -> num0   -> returns the number of all tasks
            // anz1 -> num1   -> returns the number of completed items
            // ausg -> out    -> output everything
            // entf -> remove -> remove an entry
            String command = in.readLine();
            switch (command) {
                // leer -> empty  -> create empty list
                case "leer":
                    int size = Integer.parseInt(in.readLine());
                    list = TodoList.createEmptyList(size);
                    System.out.println(list.tasks.length);
                    break;
                // aufg -> up     -> new task
                case "aufg":
                    // lese Beschreibung
                    String description = in.readLine();
                    System.out.println(TodoList.addTask(list, description));
                    break;
                // erl  -> erl    -> mark index as done
                case "erl":
                    int index = Integer.parseInt(in.readLine());
                    TodoList.markAsDone(list, index);
                    System.out.println(list.tasks[index].done);
                    break;
                // anz0 -> num0   -> returns the number of all tasks
                case "anz0":
                    System.out.println(TodoList.countTasks(list, false));
                    break;
                // anz1 -> num1   -> returns the number of completed items
                case "anz1":
                    System.out.println(TodoList.countTasks(list, true));
                    break;
                // entf -> remove -> remove an entry
                case "entf":
                    int index1 = Integer.parseInt(in.readLine());
                    TodoList.removeTask(list, index1);
                    break;
                // ausg -> out    -> output everything
                case "ausg":
                    int widthInCharacters = Integer.parseInt(in.readLine());
                    TodoList.output(list, widthInCharacters);
                    break;
                default:
                    return;
            }
        } while (true);
    }
}
