package per.stu.pms.admin.model.vo.task;

import lombok.Data;
import per.stu.pms.common.model.BasePageQuery;

@Data
public class FindTaskPageListReqVO extends BasePageQuery {
    private String taskName;
    private String projectId;
    private String ownerId;
    private String status;
    private Integer priority;
}