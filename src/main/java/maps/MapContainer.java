package maps;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapContainer {
    public enum ContainerType {
        HASH_MAP(new HashMap<Integer, Integer>(), "HashMap"),
        TREE_MAP(new TreeMap<Integer, Integer>(), "TreeMap"),
        LINKEDHASH_MAP(new TreeMap<Integer, Integer>(), "LinkedHashMap"),
        CONCURRENT_HASH_MAP(new ConcurrentHashMap<Integer, Integer>(), "ConcurrentHashMap");

        private Map map;
        private String name;

        ContainerType(Map<Integer, Integer> map, String name) {
            this.map = map;
            this.name = name;
        }

        public Map<Integer,Integer> getMap() {
            return map;
        }

        public String getName() {
            return name;
        }
    }

    private Map<String, ContainerType> containers = new HashMap<>();

    public MapContainer() {
        for(ContainerType type : ContainerType.values()) {
            containers.put(type.getName(), type);
        }
    }

    public Map<String, ContainerType> getContainers() {
        return containers;
    }
}
