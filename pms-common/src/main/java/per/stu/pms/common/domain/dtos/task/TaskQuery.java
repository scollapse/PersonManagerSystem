package per.stu.pms.common.domain.dtos.task;


import lombok.Data;
import per.stu.pms.common.model.BasePageQuery;

/**
 * @description:  TaskQuery
 * @author: syl
 * @create: 2025-03-03 14:20
 **/
@Data
public class TaskQuery extends BasePageQuery {
    private String taskName;
    private String projectId;
    private String ownerId;
    private String status;
    private Integer priority;
    private String startDate;
    private String endDate;
}
