package per.stu.pms.admin.model.vo.task;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import per.stu.pms.admin.model.vo.tag.UpdateTagReqVO;
import per.stu.pms.common.enums.ProjectStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "新增任务请求 VO")
public class AddTaskRequestVO {
    @NotBlank(message = "任务名称不能为空")
    private String taskName;

    private String projectId;

    private String ownerId;

    @NotNull(message = "优先级不能为空")
    private String priority;

    private String description;

    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    @NotNull(message = "截止时间不能为空")
    private LocalDateTime endTime;

    private String tagIds; // 标签ID集合

    private String preTaskId; // 前置任务ID

    private Double estimateHours; // 预计工时

   private List<UpdateTagReqVO> tags; // 标签集合

}