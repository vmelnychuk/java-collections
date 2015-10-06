package lists;

import java.util.List;
import java.util.Random;

public abstract class ListImpl implements IntegerList {
    private Random random = new Random();
    protected List<Integer> list;

    @Override
    public void add(Integer element) {
        list.add(element);
    }

    @Override
    public void addWithIndex(Integer element, int index) {
        list.add(index, element);
    }

    @Override
    public Integer getRandomElement() {
        return list.get(getRandomIndex());
    }

    @Override
    public Integer getElementWithIndex(int index) {
        return list.get(index);
    }

    @Override
    public void updateElementWithIndex(Integer element, int index) {
        list.set(index, element);
    }

    @Override
    public void updateRandomElement(Integer element) {
        list.set(getRandomIndex(), element);
    }

    @Override
    public void deleteElementWithIndex(int index) {
        list.remove(index);
    }

    @Override
    public void deleteRandomElement() {
        list.remove(getRandomIndex());
    }

    @Override
    public void generateList() {
        for(int i = 0; i < LISTS_LENGTH; i++) {
            list.add(new Integer(random.nextInt()));
        }
    }

    private int getRandomIndex() {
        return random.nextInt(list.size());
    }
}
