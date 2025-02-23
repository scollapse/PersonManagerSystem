package per.stu.pms.admin.model.vo.task;

import lombok.Data;

@Data
public class FindTaskReqVO {
    private String taskName;
    private String projectId;
    private String ownerId;
    private String status;
    private Integer priority;
}