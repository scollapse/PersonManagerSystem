package per.stu.pms.common.domain.dtos.task;


import lombok.Data;
import lombok.EqualsAndHashCode;
import per.stu.pms.common.domain.dos.TaskDO;

/**
 * @description: 任务返回DTO
 * @author: syl
 * @create: 2025-02-24 12:33
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskDTO extends TaskDO {
    private String projectName; // 联查得到的项目名称
}
