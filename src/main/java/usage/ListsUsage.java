package usage;

import lists.IntegerArrayList;
import lists.IntegerLinkedList;
import lists.ListImpl;
import utils.StopWatch;

public class ListsUsage {
    public static final int OPERATION_NUMBER = 1000;
    private ListImpl list;
    public ListsUsage() {
        list = new IntegerArrayList();
        randomAccess();
        list = new IntegerLinkedList();
        randomAccess();
    }
    public void randomAccess() {
        StopWatch timer = new StopWatch();
        timer.start();
        int b = 0;
        for(int i = 0; i < OPERATION_NUMBER; i++) {
            b = list.getRandomElement() >> 1;
        }
        timer.stop();
    }
}
