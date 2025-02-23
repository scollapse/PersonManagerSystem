package per.stu.pms.admin.model.vo.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import per.stu.pms.common.enums.TaskStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTaskPageListResVO {
    private String taskId;
    private String taskName; // 任务名称
    private String projectId; // 关联项目ID
    private String ownerId; // 负责人ID
    private TaskStatus status; // 状态
    private Integer priority; // 优先级
    private String tagIds; // 标签ID集合（JSON 格式）
    private String preTaskId; // 前置任务ID
    private Double estimateHours; // 预计工时
    private Date startTime; // 计划开始时间
    private Date endTime; // 计划截止时间
    private Date completionTime; // 实际完成时间
    private Integer version; // 乐观锁版本号
    private Boolean isDeleted; // 软删除标记
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间
}
