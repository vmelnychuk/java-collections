package usage;

import lists.ListContainer;

import java.util.*;

public abstract class TestExecutor {
    public static final int LENGTH = 1_0000;

    public enum Operation {
        ADD("Add"), INSERT("Insert"), GET("Get"), NEXT("Next"), DELETE("Delete");

        private String name;

        Operation(String operationName) {
            this.name = operationName;
        }

        public String getName() {
            return name;
        }
    }

    private static Map<Operation, TestExecutor> tests = new HashMap<>();
    private static Random random = new Random();
    private static Map<String, Map<String, Long>> result = new HashMap<String, Map<String, Long>>();
    private static long[][] normalizedResults;
    private static long[][] resultsArray;
    private static List<String> opsName;
    private static List<String> collsName;

    static {
        tests.put(Operation.ADD, new TestExecutor() {
            @Override
            public void executeMethod(ListContainer.ContainerType container) {
                for(int i=0; i < LENGTH;i++) {
                    container.getList().add(new Integer(i));
                }
            }
        });
        tests.put(Operation.INSERT, new TestExecutor() {
            @Override
            public void executeMethod(ListContainer.ContainerType container) {
                for(int i=0; i < LENGTH; i++) {
                    container.getList().add(random.nextInt(LENGTH), new Integer(i));
                }
            }
        });
        tests.put(Operation.GET,new TestExecutor() {
            @Override
            public void executeMethod(ListContainer.ContainerType container) {
                int b = 0;
                for(int i=0; i < LENGTH; i++) {
                    b = container.getList().get(random.nextInt(container.getList().size())) >> 2;
                }
            }
        });
        tests.put(Operation.NEXT,new TestExecutor() {
            @Override
            public void executeMethod(ListContainer.ContainerType container) {
                int b = 0;
                Iterator<Integer> iterator = container.getList().iterator();
                while(iterator.hasNext()) {
                    b = iterator.next() >> 2;
                }
            }
        });
        tests.put(Operation.DELETE,new TestExecutor() {
            @Override
            public void executeMethod(ListContainer.ContainerType container) {
                for(int i=0; i < LENGTH; i++) {
                    container.getList().remove(random.nextInt(container.getList().size()));
                }
            }
        });
    }

    public long execute(ListContainer.ContainerType container) {
        long startTime = System.currentTimeMillis();
        executeMethod(container);
        return System.currentTimeMillis() - startTime;
    }

    public static void executeAll(ListContainer.ContainerType container) {
        Map<String,Long> operationResult = new HashMap<>();
        for (Operation operation : Operation.values()) {
            long timeElapsed = tests.get(operation).execute(container);
            operationResult.put(operation.getName(), timeElapsed);
        }
        result.put(container.getName(), operationResult);
    }

    public abstract void executeMethod(ListContainer.ContainerType container);

    public static void check(ListContainer container) {
        Map<String, ListContainer.ContainerType> containersMap = container.getContainers();
        for (String key : containersMap.keySet()) {
            executeAll(containersMap.get(key));
        }
        printResults();
        printNormalizedResults();
    }

    public static void printResults() {
        printHeader();
        for(String collectionName : result.keySet()) {
            System.out.printf("|%11s |", collectionName);
            Map<String, Long> operationResult = result.get(collectionName);
            for(String operation : operationResult.keySet()) {
                System.out.printf("%8s |", operationResult.get(operation));
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
                System.out.printf("|%11s |", collIt.next());
                for (int j = 0; j < resultsArray[i].length; j++) {
                    System.out.printf("%8s |", resultsArray[i][j]);
                }
                System.out.println();
        }
        printFooter();
    }

    //--------------------Print helpers

    private static void printHeader() {
        System.out.println("================================================================");
        System.out.printf("|%11s |", "Collection");
        for (String opName : getOperationsNames()) {
            System.out.printf("%8s |", opName);
        }
        System.out.println();
        System.out.println("================================================================");
    }

    private static void printFooter() {
        System.out.println("================================================================");
    }

    //--------------------

    // fix this
    public static void normalizeResults() {
        prepareResultsArray();
        for(int col = 0; col < resultsArray[0].length; col++) {
            int max_index_row = 0;
            int max_index_col = 0;

            int min_index_row = 0;
            int min_index_col = 0;
            for(int k = 0; k < 2; k++) {
                for (int row = 0; row < resultsArray.length; row++) {
                    if (k == 0) {
                        if (resultsArray[row][col] >= resultsArray[max_index_row][max_index_col]) {
                            max_index_row = row;
                            max_index_col = col;
                        }

                        if (resultsArray[row][col] <= resultsArray[min_index_row][min_index_col]) {
                            min_index_row = row;
                            min_index_col = col;
                        }
                    }
                    if(k == 1) {
                        if (resultsArray[row][col] < resultsArray[max_index_row][max_index_col]
                                && resultsArray[row][col] > resultsArray[min_index_row][min_index_col]) {
                            resultsArray[row][col] = 0;
                        }
                        if ((resultsArray[row][col] == resultsArray[max_index_row][max_index_col])
                                && row != max_index_row && col != max_index_col) {
                            resultsArray[row][col] = -1;
                        }
                        if (resultsArray[row][col] == resultsArray[min_index_row][min_index_col]
                                && row != min_index_row && col != min_index_col) {
                            resultsArray[row][col] = 1;
                        }
                    }
                }
            }
            resultsArray[max_index_row][max_index_col] = -1;
            resultsArray[min_index_row][min_index_col] = 1;
        }
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
}
