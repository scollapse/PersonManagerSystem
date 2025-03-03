package per.stu.pms.admin.model.vo.task;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import per.stu.pms.common.domain.dtos.task.TaskQuery;
import per.stu.pms.common.enums.TaskStatus;
import per.stu.pms.common.model.BasePageQuery;
@Data
@AllArgsConstructor
@Builder
@ApiModel(value = "查询任务列表 VO")
public class FindTaskPageListReqVO extends TaskQuery {

}