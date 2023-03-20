package state;

import java.util.LinkedList;
import java.util.ListIterator;

public class History {
    LinkedList<String> history;




    public History() {
        history = new LinkedList<>();
    }

    public void addCommand(String command) {
        history.addFirst(command);

    }

    public String getHistory() {
        StringBuilder strHistory = new StringBuilder();
        ListIterator<String> iterator = history.listIterator(history.size());
        while(iterator.hasPrevious()) {
            strHistory.append(iterator.previous());
            strHistory.append("\n");
        }
        return strHistory.toString();
    }



}
