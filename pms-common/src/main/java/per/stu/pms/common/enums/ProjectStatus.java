package per.stu.pms.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectStatus {
    //'planning','in_progress','paused','completed','deprecated'
    planning("planning"),         // 常量名称与数据库值一致（全小写）
    in_progress("in_progress"),
    paused("paused"),
    completed("completed"),
    deprecated("deprecated");

    private final String value;

    ProjectStatus(String value) {
        this.value = value;
    }

    @JsonValue // 序列化时使用此值
    public String getValue() {
        return value;
    }

    // 反序列化方法：根据 value 返回枚举
    public static ProjectStatus fromValue(String value) {
        for (ProjectStatus status : ProjectStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ProjectStatus: " + value);
    }
}