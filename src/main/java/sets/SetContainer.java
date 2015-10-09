package sets;

import lists.ListContainer;

import java.util.*;

public class SetContainer {
    public enum ContainerType {
        HASH_SET(new HashSet<Integer>(), "HashSet"),
        LINKED_HASH_SET(new LinkedHashSet<Integer>(),"LinkedHashSet"),
        TREE_SET(new TreeSet<Integer>(), "TreeSet");

        private Set set;
        private String name;

        ContainerType(Set<Integer> set, String name) {
            this.set = set;
            this.name = name;
        }

        public Set<Integer> getSet() {
            return set;
        }

        public String getName() {
            return name;
        }
    }

    private Map<String, ContainerType> containers = new HashMap<>();

    public SetContainer() {
        for(ContainerType type : ContainerType.values()) {
            containers.put(type.getName(), type);
        }
    }

    public Map<String, ContainerType> getContainers() {
        return containers;
    }
}
