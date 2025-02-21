package per.stu.pms.common.enums;

public enum Priority {
    URGENT(1), HIGH(2), MEDIUM(3), LOW(4);

    private final int value;

    Priority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}