package per.stu.pms.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
    deprecated("deprecated"),     // 废弃
    wait("wait"),               // 等待中
    todo("todo"),               // 待处理
    in_progress("in_progress"), // 进行中
    completed("completed");     // 已完成

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    @JsonValue // 序列化时使用此值
    public String getValue() {
        return value;
    }

    // 反序列化方法：根据 value 返回枚举
    public static TaskStatus fromValue(String value) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid TaskStatus: " + value);
    }
}