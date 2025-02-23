package per.stu.pms.admin.model.vo.task;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class DeleteTaskReqVO {
    @NotBlank(message = "任务ID不能为空")
    private String taskId;
}