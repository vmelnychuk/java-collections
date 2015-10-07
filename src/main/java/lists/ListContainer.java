package lists;

import java.util.*;

public class ListContainer {
    public enum ContainerType {
        ARRAY_LIST(new ArrayList<Integer>(), "ArrayList"),
        LINKED_LIST(new LinkedList<Integer>(), "LinkedList"),
        VECTOR(new Vector<Integer>(), "Vector");

        private List list;
        private String name;

        ContainerType(List<Integer> list, String name) {
            this.list = list;
            this.name = name;
        }

        public List<Integer> getList() {
            return list;
        }

        public String getName() {
            return name;
        }
    }

    private Map<String, ContainerType> containers = new HashMap<>();

    public ListContainer() {
        for(ContainerType type : ContainerType.values()) {
            containers.put(type.toString(), type);
        }
    }

    public Map<String, ContainerType> getContainers() {
        return containers;
    }
}
