package sets;

import java.util.HashMap;

public class SetTestExecutor {
    public static final int LENGTH = 1000;

    public enum Operation {
        ADD("Add"), CONTAINS("Insert"), GET("Get"), NEXT("Next"), DELETE("Delete");

        private String name;

        Operation(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private HashMap<Operation, SetTestExecutor> tests = new HashMap<Operation, SetTestExecutor>();

    static {

    }
}
