package lists;

import java.util.ArrayList;

public class IntegerArrayList extends ListImpl {
    public IntegerArrayList() {
        this.list = new ArrayList<>();
        generateList();
    }
}
