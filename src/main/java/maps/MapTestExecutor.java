package maps;

import lists.ListContainer;

import java.util.*;

public abstract class MapTestExecutor {
    public static final int LENGTH = 1_000_000;

    public enum Operation {
        PUT("Put"), GET("Get"),
        //REMOVE("Remove"),
        REPLACE("Replace"),
        NEXT("Next");

        private String name;

        Operation(String operationName) {
            this.name = operationName;
        }

        public String getName() {
            return name;
        }
    }

    private static Map<Operation, MapTestExecutor> tests = new HashMap<>();
    private static Random random = new Random();
    private static Map<String, Map<String, Long>> result = new HashMap<String, Map<String, Long>>();
    private static long[][] resultsArray;
    private static List<String> opsName;
    private static List<String> collsName;

    static {
        tests.put(Operation.PUT, new MapTestExecutor() {
            @Override
            public void executeMethod(MapContainer.ContainerType container) {
                for(int i=0; i < LENGTH;i++) {
                    container.getMap().put(new Integer(i), random.nextInt(LENGTH));
                }
            }
        });
        tests.put(Operation.GET, new MapTestExecutor() {
            @Override
            public void executeMethod(MapContainer.ContainerType container) {
                for(int i=0; i < LENGTH; i++) {
                    container.getMap().get(random.nextInt(LENGTH));
                }
            }
        });
//        tests.put(Operation.REMOVE,new MapTestExecutor() {
//            @Override
//            public void executeMethod(MapContainer.ContainerType container) {
//                for(int i=0; i < LENGTH; i++) {
//                    container.getMap().remove(random.nextInt(LENGTH));
//                }
//            }
//        });
        tests.put(Operation.REPLACE,new MapTestExecutor() {
            @Override
            public void executeMethod(MapContainer.ContainerType container) {
                for(int i = 0; i < LENGTH; i++) {
                    container.getMap().put(random.nextInt(LENGTH), random.nextInt(LENGTH));
                }
            }
        });
        tests.put(Operation.NEXT,new MapTestExecutor() {
            @Override
            public void executeMethod(MapContainer.ContainerType container) {
                Iterator<Integer> iterator = container.getMap().values().iterator();
                int b = 0;
                while(iterator.hasNext()) {
                    b = iterator.next() >> 2;
                }
            }
        });
    }

    public long execute(MapContainer.ContainerType container) {
        long startTime = System.nanoTime();
        executeMethod(container);
        return System.nanoTime() - startTime;
    }

    public static void executeAll(MapContainer.ContainerType container) {
        Map<String,Long> operationResult = new HashMap<>();
        for (Operation operation : Operation.values()) {
            long timeElapsed = tests.get(operation).execute(container);
            operationResult.put(operation.getName(), timeElapsed);
        }
        result.put(container.getName(), operationResult);
    }

    public abstract void executeMethod(MapContainer.ContainerType container);

    public static void check(MapContainer container) {
        Map<String, MapContainer.ContainerType> containersMap = container.getContainers();
        for (String key : containersMap.keySet()) {
            executeAll(containersMap.get(key));
        }
        printResults();
        printNormalizedResults();
    }

    public static void printResults() {
        printHeader();
        for(String collectionName : result.keySet()) {
            System.out.printf("|%20s |", collectionName);
            Map<String, Long> operationResult = result.get(collectionName);
            for(String operation : operationResult.keySet()) {
                System.out.printf("%10s |", operationResult.get(operation) / 1000);
            }
            System.out.println();
        }
        printFooter();
    }


    public static void printNormalizedResults() {
        normalizeResults();
        printHeader();
        Iterator<String> collIt = collsName.iterator();
        for(int i = 0; i < resultsArray.length;i++) {
                System.out.printf("|%20s |", collIt.next());
                for (int j = 0; j < resultsArray[i].length; j++) {
                    System.out.printf("%10s |", resultsArray[i][j]);
                }
                System.out.println();
        }
        printFooter();
    }

    private static void prepareResultsArray() {
        opsName = getOperationsNames();
        collsName = getCollectionNames();
        resultsArray = new long[result.keySet().size()][];
        int i = 0;
        for(String collectionName : result.keySet()) {
            Map<String, Long> operationResult = result.get(collectionName);
            int j = 0;
            resultsArray[i] = new long[operationResult.keySet().size()];
            for(String operation : operationResult.keySet()) {
                resultsArray[i][j] = operationResult.get(operation);
                j++;
            }
            i++;
        }
    }

    private static List<String> getOperationsNames() {
        String element = result.keySet().iterator().next();
        return new ArrayList<String>(result.get(element).keySet());
    }

    private static List<String> getCollectionNames() {
        return new ArrayList<String>(result.keySet());
    }

    private static void normalizeResults() {
        prepareResultsArray();
        for(int col = 0; col < resultsArray[0].length; col++) {
            int max_index_row = 0;
            int max_index_col = col;

            int min_index_row = 0;
            int min_index_col = col;
            for(int k = 0; k < 2; k++) {
                for (int row = 0; row < resultsArray.length; row++) {
                    if (k == 0) {
                        if (resultsArray[row][col] > resultsArray[max_index_row][max_index_col]) {
                            max_index_row = row;
                            max_index_col = col;
                        }

                        if (resultsArray[row][col] < resultsArray[min_index_row][min_index_col]) {
                            min_index_row = row;
                            min_index_col = col;
                        }
                    }
                    if(k == 1) {
                        if (resultsArray[row][col] < resultsArray[max_index_row][max_index_col]
                                && resultsArray[row][col] > resultsArray[min_index_row][min_index_col]) {
                            resultsArray[row][col] = 0;
                        }
                    }
                }
            }
            resultsArray[max_index_row][max_index_col] = -1;
            resultsArray[min_index_row][min_index_col] = 1;
        }
    }

    //--------------------Print helpers

    private static void printHeader() {
        System.out.println("===================================================================================");
        System.out.printf("|%20s |", "Collection");
        for (String opName : getOperationsNames()) {
            System.out.printf("%10s |", opName);
        }
        System.out.println();
        System.out.println("===================================================================================");
    }

    private static void printFooter() {
        System.out.println("===================================================================================");
    }

    //--------------------Print helpers
}
