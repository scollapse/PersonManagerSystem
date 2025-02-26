package per.stu.pms.admin.model.vo.task;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import per.stu.pms.common.model.BasePageQuery;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询任务列表 VO")
public class FindTaskPageListReqVO extends BasePageQuery {
    private String taskName;
    private String projectId;
    private String ownerId;
    private String status;
    private Integer priority;
}