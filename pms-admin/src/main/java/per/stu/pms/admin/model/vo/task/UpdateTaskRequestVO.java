package per.stu.pms.admin.model.vo.task;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @description: UpdateTaskRequestVO
 * @author: syl
 * @create: 2025-02-26 11:08
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "更新任务请求 VO")
public class UpdateTaskRequestVO {

    private String taskId;

    private String taskName;

    private String status;

    private String projectId;

    private String ownerId;

    private String priority;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String tagIds; // 标签ID集合

    private String preTaskId; // 前置任务ID

    private Double estimateHours; // 预计工时

}
