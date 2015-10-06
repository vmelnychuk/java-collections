package lists;


import java.util.LinkedList;

public class IntegerLinkedList extends ListImpl {
    public IntegerLinkedList() {
        this.list = new LinkedList<>();
        generateList();
    }
}
