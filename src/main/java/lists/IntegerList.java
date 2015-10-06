package lists;

public interface IntegerList {
    public static final int LISTS_LENGTH = 1_000_000;
    void add(Integer element);
    void addWithIndex(Integer element, int index);
    Integer getRandomElement();
    Integer getElementWithIndex(int index);
    void updateElementWithIndex(Integer element, int index);
    void updateRandomElement(Integer element);
    void deleteElementWithIndex(int index);
    void deleteRandomElement();
    void generateList();
}
