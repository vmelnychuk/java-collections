package usage;

import lists.Container;

import java.util.*;

public abstract class TestExecutor {
    public static final int LENGTH = 1_000_000;
    public static final int OPERATIONS_NUMBER = 100;
    public static final int GET_NUMBER = 1000;

    private List<Integer> delegate;
    private static Random random = new Random();

    public enum Operation {
        ADD, INSERT, GET, NEXT, DELETE
    }
    private static Map<Operation, TestExecutor> tests = new HashMap<>();
    static {
        tests.put(Operation.ADD, new TestExecutor() {
            @Override
            public void executeMethod(Container.ContainerType container) {
                for(int i=0; i < LENGTH;i++) {
                    container.getList().add(new Integer(i));
                }
            }
        });
        tests.put(Operation.INSERT, new TestExecutor() {
            @Override
            public void executeMethod(Container.ContainerType container) {
                for(int i=0; i < OPERATIONS_NUMBER; i++) {
                    container.getList().add(random.nextInt(LENGTH), new Integer(i));
                }
            }
        });
        tests.put(Operation.GET,new TestExecutor() {
            @Override
            public void executeMethod(Container.ContainerType container) {
                int b = 0;
                for(int i=0; i < GET_NUMBER; i++) {
                    b = container.getList().get(random.nextInt(container.getList().size())) >> 2;
                }
            }
        });
        tests.put(Operation.NEXT,new TestExecutor() {
            @Override
            public void executeMethod(Container.ContainerType container) {
                int b = 0;
                Iterator<Integer> iterator = container.getList().iterator();
                while(iterator.hasNext()) {
                    b = iterator.next() >> 2;
                }
            }
        });
        tests.put(Operation.DELETE,new TestExecutor() {
            @Override
            public void executeMethod(Container.ContainerType container) {
                for(int i=0; i < OPERATIONS_NUMBER; i++) {
                    container.getList().remove(random.nextInt(container.getList().size()));
                }
            }
        });
    }

    public long execute(Container.ContainerType container) {
        long startTime = System.currentTimeMillis();
        executeMethod(container);
        return System.currentTimeMillis() - startTime;
    }

    public abstract void executeMethod(Container.ContainerType container);

    public static List<Long> check(Container container) {
        List<Long> result = new ArrayList<>();
        Map<String, Container.ContainerType> containersMap = container.getContainers();
        for (String key : containersMap.keySet()) {
            System.out.println("============================================");
            System.out.println("Test for:     " + key);
            for (Operation operation : Operation.values()) {
                System.out.println("Operation:    " + operation);
                long timeElapsed = tests.get(operation).execute(containersMap.get(key));
                System.out.println("Time elapsed: " + timeElapsed);
                result.add(timeElapsed);
                System.out.println("--------------------------------------------");
            }
        }
        return result;
    }
}
