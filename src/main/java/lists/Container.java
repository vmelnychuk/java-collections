package lists;

import java.util.*;

public class Container {
    public enum ContainerType {
        ARRAY_LIST(new ArrayList<Integer>()),
        LINKED_LIST(new LinkedList<Integer>()),
        VECTOR(new Vector<Integer>());

        private List list;

        ContainerType(List<Integer> list) {
            this.list = list;
        }

        public List<Integer> getList() {
            return list;
        }
    }

    private Map<String, ContainerType> containers = new HashMap<>();

    public Container() {
        for(ContainerType type : ContainerType.values()) {
            containers.put(type.toString(), type);
        }
    }

    public Map<String, ContainerType> getContainers() {
        return containers;
    }
}
