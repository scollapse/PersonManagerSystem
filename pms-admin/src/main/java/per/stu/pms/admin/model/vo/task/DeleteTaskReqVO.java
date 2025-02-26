package per.stu.pms.admin.model.vo.task;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除任务请求 VO")
public class DeleteTaskReqVO {
    @NotBlank(message = "任务ID不能为空")
    private String taskId;
}