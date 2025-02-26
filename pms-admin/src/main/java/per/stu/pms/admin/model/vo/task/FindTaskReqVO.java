package per.stu.pms.admin.model.vo.task;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询任务 VO")
public class FindTaskReqVO {
    private String taskName;
    private String projectId;
    private String ownerId;
    private String status;
    private Integer priority;
}